package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.ProductMapper;
import antigypt.springframework.api.v1.model.DepartmentDTO;
import antigypt.springframework.api.v1.model.ProductDTO;
import antigypt.springframework.api.v1.model.ProductTypeDTO;
import antigypt.springframework.domain.Department;
import antigypt.springframework.domain.Product;
import antigypt.springframework.exceptions.ResourceNotFoundException;
import antigypt.springframework.repositories.DepartmentRepository;
import antigypt.springframework.repositories.ProductRepository;
import antigypt.springframework.repositories.ProductTypeRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductTypeRepository productTypeRepository;
    private final DepartmentRepository departmentRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, ProductTypeRepository productTypeRepository, DepartmentRepository departmentRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productTypeRepository = productTypeRepository;
        this.departmentRepository = departmentRepository;
    }

    @SneakyThrows
    @Override
    public ProductDTO createNewProduct(ProductDTO productDTO,Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (!departmentOptional.isPresent()){
            throw new ResourceNotFoundException("id is invalid : "+ departmentId);
        }
        Department foundedDepartment = departmentOptional.get();
        Product savedProduct = productRepository.save(productMapper.productDTOToProduct(productDTO));
        savedProduct.getDepartmentList().add(foundedDepartment);
        ProductDTO returnedDTO = productMapper.productToProductDTO(savedProduct);
        returnedDTO.setProductUrl("/api/v1/products/"+savedProduct.getProductId());
        returnedDTO.getProductType().setProductTypeUrl("/api/v1/productTypes/"+savedProduct.getProductType().getProductTypeId());
        return returnedDTO;
    }

    @SneakyThrows
    @Override
    public ProductDTO findProductById(Long id) {
        Optional<Product> foundedProduct = productRepository.findById(id);
        if (!foundedProduct.isPresent()){
            throw new ResourceNotFoundException("id is not found : "+ id);
        }
        ProductDTO returnedDTO = productMapper.productToProductDTO(foundedProduct.get());
        returnedDTO.setProductUrl("/api/v1/products/"+id);
        returnedDTO.getProductType().setProductTypeUrl("/api/v1/productTypes/"+foundedProduct.get().getProductType().getProductTypeId());
        return returnedDTO;
    }

    @Override
    public ProductDTO updateProductByDTO(Long id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProducttById(Long id) {
        productRepository.deleteById(id);
    }

    @SneakyThrows
    @Override
    public boolean isNew(ProductDTO productDTO,Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (!departmentOptional.isPresent()){
            throw new ResourceNotFoundException("id is invalid : "+departmentId);
        }
        boolean isProductNew = true;
        for (Product product :
                productRepository.findAll().stream()
                        .filter(product -> product.getDepartmentList().contains(departmentOptional.get()))
                        .collect(Collectors.toList())){
            if (product.getProductName().equals(productDTO.getProductName())==true){
                isProductNew = false;
            }
        }
        return isProductNew;
    }

    @SneakyThrows
    @Override
    public List<ProductDTO> findAllByName(String productName , Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (!departmentOptional.isPresent()){
            throw new ResourceNotFoundException("id is invalid : "+departmentId);
        }
        return productRepository.findAllByProductName(productName)
                .stream()
                .filter(product ->
                            product.getDepartmentList().contains(departmentOptional.get()))
                .map(product -> {
                    ProductDTO returnedDTO = productMapper.productToProductDTO(product);
                    returnedDTO.setProductUrl("/api/v1/products/"+product.getProductId());
                    returnedDTO.getProductType().setProductTypeUrl("/api/v1/productTypes/"+product.getProductType().getProductTypeId());
                    return returnedDTO;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductDTO> findAllBbyDepartment(Long departmentId) {
        Department foundedDepartment = departmentRepository.findById(departmentId).get();
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getDepartmentList().contains(foundedDepartment))
                .map(product -> {
                    ProductDTO returnedDTO = productMapper.productToProductDTO(product);
                    returnedDTO.setProductUrl("/api/v1/products/"+product.getProductId());
                    returnedDTO.getProductType().setProductTypeUrl("/api/v1/productTypes/"+product.getProductType().getProductTypeId());
                    return returnedDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByProductType(Long productTypeId ,Long departmentId) {
        Department foundedDepartment = departmentRepository.findById(departmentId).get();
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getDepartmentList().contains(foundedDepartment))
                .filter(product -> product.getProductType().getProductTypeId().equals(productTypeId))
                .map(product -> {
                    ProductDTO returnedDTO = productMapper.productToProductDTO(product);
                    returnedDTO.setProductUrl("/api/v1/products/"+product.getProductId());
                    returnedDTO.getProductType().setProductTypeUrl("/api/v1/productTypes/"+product.getProductType().getProductTypeId());
                    return returnedDTO;
                })
                .collect(Collectors.toList());
    }
}
