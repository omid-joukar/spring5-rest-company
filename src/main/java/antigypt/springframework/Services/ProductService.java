package antigypt.springframework.Services;

import antigypt.springframework.api.v1.model.DepartmentDTO;
import antigypt.springframework.api.v1.model.ProductDTO;
import antigypt.springframework.api.v1.model.ProductTypeDTO;
import java.util.List;

public interface ProductService {
    ProductDTO createNewProduct(ProductDTO productDTO);
    ProductDTO findProductById(Long id);
    ProductDTO updateProductByDTO(Long id,ProductDTO productDTO);
    void deleteProducttById(Long id);
    boolean isNew(ProductDTO productDTO);
    List<ProductDTO> findAllByName(String productName);
    List<ProductDTO> findAllBbyDepartment(DepartmentDTO departmentDTO);
    List<ProductDTO> findAllByProductType(ProductTypeDTO productType);

}
