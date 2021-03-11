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

    @Override
    public ProductDTO createNewProduct(ProductDTO productDTO) {
        Product savedProduct = productRepository.save(productMapper.productDTOToProduct(productDTO));
        ProductDTO returnedDTO = productMapper.productToProductDTO(savedProduct);
        returnedDTO.setProductUrl("/api/v1/products/"+savedProduct.getProductId());
        return null;
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

    @Override
    public boolean isNew(ProductDTO productDTO) {
        boolean isProductNew = true;
        for (Product product : productRepository.findAll()){
            if (product.getProductName().equals(productDTO.getProductName())==true){
                isProductNew = false;
            }
        }
        return isProductNew;
    }

    @Override
    public List<ProductDTO> findAllByName(String productName , Long departmentId) {
        Department foundedDepartment = departmentRepository.findById(departmentId).get();
        return productRepository.findAllByProductName(productName)
                .stream()
                .filter(product ->
                            product.getDepartmentList().contains(foundedDepartment))
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductDTO> findAllBbyDepartment(Long departmentId) {
        Department foundedDepartment = departmentRepository.findById(departmentId).get();
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getDepartmentList().contains(foundedDepartment))
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByProductType(Long productTypeId ,Long departmentId) {
        Department foundedDepartment = departmentRepository.findById(departmentId).get();
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getDepartmentList().contains(foundedDepartment))
                .filter(product -> product.getProductType().getProductTypeId().equals(productTypeId))
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }
}
