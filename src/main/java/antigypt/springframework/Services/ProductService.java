package antigypt.springframework.Services;


import antigypt.springframework.api.v1.model.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createNewProduct(ProductDTO productDTO);
    ProductDTO findProductById(Long id);
    ProductDTO updateProductByDTO(Long id,ProductDTO productDTO);
    void deleteProducttById(Long id);
    boolean isNew(ProductDTO productDTO);
    List<ProductDTO> findAllByName(String productName,Long departmentId);
    List<ProductDTO> findAllBbyDepartment(Long departmentId);
    List<ProductDTO> findAllByProductType(Long productTypeId,Long departmentId);

}
