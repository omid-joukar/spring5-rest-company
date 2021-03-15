package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.ProductMapper;
import antigypt.springframework.api.v1.model.DepartmentDTO;
import antigypt.springframework.api.v1.model.ProductDTO;
import antigypt.springframework.api.v1.model.ProductTypeDTO;
import antigypt.springframework.domain.*;
import antigypt.springframework.repositories.DepartmentRepository;
import antigypt.springframework.repositories.ProductRepository;
import antigypt.springframework.repositories.ProductTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private static final String DETAIL = "detail of product";
    private static final String DISCOUNT = "10.0";
    private static final String EXPIRATION_DATE = String.valueOf(LocalDate.of(2021,4,14));;
    private static final String IMAGE = "image of product";
    private static final String PRICE = "2.31";
    private static final String PRODUCE_DATE = String.valueOf(LocalDate.of(2020,4,14));
    private static final String PRODUCT_COUNT = "13";
    private static final String SERIAL_NUMBER = "serial of product";
    private static final String WEIGHT = "1.0";
    private static final String PRODUCT_NAME = String.valueOf(ProductTypeName.ORANGE);
    private static final Long PRODUCT_TYPE_ID = 1L;
    private static final String PHONENUMBER = "12345678";
    private static final String EMAIL = "omid@gmail.com";
    private static final String ADDRESSLINE = "Elisenstarse 1";
    private static final String COUNTRY = "Austria";
    private static final String CITY = "Wien";
    private static final String POSTALCODE = "1230";
    private static final String REGION = "Liesing";
    private static final Long PRODUCT_ID = 1L;

    @Mock
    ProductRepository productRepository;
    @Mock
    ProductTypeRepository productTypeRepository;
    @Mock
    DepartmentRepository departmentRepository;
    ProductMapper productMapper;
    ProductServiceImpl productService;
    ProductDTO sendedProductDTO;
    Product savedProduct;
    Product savedProduct2;
    ProductTypeDTO productTypeDTO;
    ProductType productType;
    Department department;
    Department department2;
    DepartmentDTO departmentDTO;
    Byte[] getImageBytes;
    Byte[] getSerialBytes;
    Address address;

    @BeforeEach
    void setUp() {
        int i = 0;
        getImageBytes = new Byte[IMAGE.getBytes().length];
        for (byte b : IMAGE.getBytes()){
            getImageBytes[i++] = b;
        }
        i=0;
        getSerialBytes = new Byte[SERIAL_NUMBER.getBytes().length];
        for (byte b : SERIAL_NUMBER.getBytes()){
            getSerialBytes[i++] = b;
        }

        productMapper = ProductMapper.INSTANCE;
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository,productMapper,productTypeRepository,departmentRepository);

        address = new Address();
        address.setAddressLine(ADDRESSLINE);
        address.setCity(CITY);
        address.setCountry(COUNTRY);
        address.setPostalCode(POSTALCODE);
        address.setRegion(REGION);

        department = new Department();
        department.setDepartmentId(1L);
        department.setEmail(EMAIL);
        department.setPhoneNumber(PHONENUMBER);
        department.setDetail(DETAIL);
        department.setAddress(address);

        department2 = new Department();
        department2.setDepartmentId(2L);
        department2.setEmail(EMAIL);
        department2.setPhoneNumber(PHONENUMBER);
        department2.setDetail(DETAIL);
        department2.setAddress(address);





        departmentDTO = new DepartmentDTO();
        departmentDTO.setAddressLine(ADDRESSLINE);
        departmentDTO.setCity(CITY);
        departmentDTO.setCountry(COUNTRY);
        departmentDTO.setDetail(DETAIL);
        departmentDTO.setEmail(EMAIL);
        departmentDTO.setPhoneNumber(PHONENUMBER);
        departmentDTO.setPostalCode(POSTALCODE);
        departmentDTO.setRegion(REGION);

        productType = new ProductType();
        productType.setProductTypeName(ProductTypeName.ORANGE);
        productType.setProductTypeId(PRODUCT_TYPE_ID);
        productType.setProductCategory(ProductCategory.FRUITS_VEGETABLES);
        productType.setDetail(DETAIL);

        productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setProductCategory(ProductCategory.FRUITS_VEGETABLES);
        productTypeDTO.setProductTypeName(ProductTypeName.ORANGE);
        productTypeDTO.setDetail(DETAIL);
        productTypeDTO.setProductTypeUrl("/api/v1/productTypes/1");

        sendedProductDTO = new ProductDTO();
        sendedProductDTO.setDetail(DETAIL);
        sendedProductDTO.setDiscount(DISCOUNT);
        sendedProductDTO.setExpirationDate(EXPIRATION_DATE);
        sendedProductDTO.setImage(getImageBytes);
        sendedProductDTO.setPrice(PRICE);
        sendedProductDTO.setProduceDate(PRODUCE_DATE);
        sendedProductDTO.setProductCount(PRODUCT_COUNT);
        sendedProductDTO.setProductType(productTypeDTO);
        sendedProductDTO.setSerialNumber(getSerialBytes);
        sendedProductDTO.setWeight(WEIGHT);
        sendedProductDTO.setProductName(PRODUCT_NAME);

        savedProduct = new Product();
        savedProduct.setProductId(PRODUCT_ID);
        savedProduct.setDetail(DETAIL);
        savedProduct.setDiscount(Double.valueOf(DISCOUNT));
        savedProduct.setExpirationDate(LocalDate.of(2021,4,14));
        savedProduct.setImage(getImageBytes);
        savedProduct.setPrice(Double.valueOf(PRICE));
        savedProduct.setProduceDate(LocalDate.of(2020,4,14));
        savedProduct.setProductCount(Long.valueOf(PRODUCT_COUNT));
        savedProduct.setProductType(productType);
        savedProduct.setSerialNumber(getSerialBytes);
        savedProduct.setWeight(Double.valueOf(WEIGHT));
        savedProduct.setProductName(PRODUCT_NAME);
        savedProduct.getDepartmentList().add(department);

        savedProduct2 = new Product();
        savedProduct2.setProductId(2L);
        savedProduct2.setDetail(DETAIL);
        savedProduct2.setDiscount(Double.valueOf(DISCOUNT));
        savedProduct2.setExpirationDate(LocalDate.of(2021,4,14));
        savedProduct2.setImage(getImageBytes);
        savedProduct2.setPrice(Double.valueOf(PRICE));
        savedProduct2.setProduceDate(LocalDate.of(2020,4,14));
        savedProduct2.setProductCount(Long.valueOf(PRODUCT_COUNT));
        savedProduct2.setProductType(productType);
        savedProduct2.setSerialNumber(getSerialBytes);
        savedProduct2.setWeight(Double.valueOf(WEIGHT));
        savedProduct2.setProductName(PRODUCT_NAME);
        savedProduct2.getDepartmentList().add(department2);

    }

    @Test
    void createNewProduct() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(savedProduct);
        ProductDTO returnedProductDTO = productService.createNewProduct(sendedProductDTO,1L);
        assertNotNull(returnedProductDTO);
        assertEquals(returnedProductDTO.getDiscount(),DISCOUNT);
        assertEquals(returnedProductDTO.getExpirationDate(),EXPIRATION_DATE);
        assertEquals(returnedProductDTO.getWeight(),WEIGHT);
        assertEquals(returnedProductDTO.getDetail(),DETAIL);
        assertEquals(returnedProductDTO.getProductUrl(),"/api/v1/products/1");
    }

    @Test
    void findProductById() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(savedProduct));
        ProductDTO returnedProductDTO = productService.findProductById(1L);
        assertNotNull(returnedProductDTO);
        assertEquals(returnedProductDTO.getDiscount(),DISCOUNT);
        assertEquals(returnedProductDTO.getExpirationDate(),EXPIRATION_DATE);
        assertEquals(returnedProductDTO.getWeight(),WEIGHT);
        assertEquals(returnedProductDTO.getDetail(),DETAIL);
        assertEquals(returnedProductDTO.getProductUrl(),"/api/v1/products/1");


    }

    @Test
    void updateProductByDTO() {
    }

    @Test
    void deleteProducttById() {
        productService.deleteProducttById(1L);
        verify(productRepository,times(1)).deleteById(anyLong());
    }

    @Test
    void isNewRepeated() {
      productRepository.save(savedProduct);
      when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
      when(productRepository.findAll()).thenReturn(Arrays.asList(savedProduct));
      boolean result = productService.isNew(sendedProductDTO,1L);
      assertEquals(false,result);
    }
    @Test
    void isNew() {
        Product product = new Product();
        product.setProductName(String.valueOf(ProductTypeName.MILK));
        product.getDepartmentList().add(department);
        productRepository.save(product);
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        boolean result = productService.isNew(sendedProductDTO,1L);
        assertEquals(true,result);
    }

    @Test
    void findAllByName() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(productRepository.findAllByProductName(anyString())).thenReturn(Arrays.asList(savedProduct,savedProduct2));
        List<ProductDTO> productDTOList = productService.findAllByName("ORANGE",1L);
        assertNotNull(productDTOList);
        assertEquals(1,productDTOList.size());

    }

    @Test
    void findAllBbyDepartment() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(productRepository.findAll()).thenReturn(Arrays.asList(savedProduct,savedProduct2));
        List<ProductDTO> productDTOList = productService.findAllBbyDepartment(1L);
        assertNotNull(productDTOList);
        assertEquals(Long.valueOf(1),productDTOList.size());
    }

    @Test
    void findAllByProductType() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(savedProduct2,savedProduct));
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        List<ProductDTO> productDTOList = productService.findAllByProductType(1L,1L);
        assertNotNull(productDTOList);
        assertEquals(1,productDTOList.size());
    }
}