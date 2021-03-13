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
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
    private static final String PRODUCT_NAME = "ORANGEN";
    private static final Long PRODUCT_TYPE_ID = 1L;
    private static final String PHONENUMBER = "12345678";
    private static final String EMAIL = "omid@gmail.com";
    private static final String ADDRESSLINE = "Elisenstarse 1";
    private static final String COUNTRY = "Austria";
    private static final String CITY = "Wien";
    private static final String POSTALCODE = "1230";
    private static final String REGION = "Liesing";
    private static final String UPDATED_ADDRESS_LINE = "Elisenstrasse 1";
    private static final String UPDATED_COUNTRY = "German";
    private static final String UPDATED_CITY = "Hamburg";
    private static final String UPDATED_EMAIL = "ali@gmail.com";
    private static final String UPDATED_POSTAL_CODE = "1120";
    private static final String UPDATED_REGION = "Wien mitte";
    private static final String UPDATED_PHONENUMBER = "87654321";
    private static final String BIRTH_DATE = LocalDate.of(1989,9,5).toString();
    private static final String HIRE_DATE = LocalDate.of(2012,9,5).toString();
    private static final String FIRST_NAME = "Omid";
    private static final String LAST_NAME = "Joukar";
    private static final String GENDER = "MALE";
    private static final String HOME_PHONE = "123456";
    private static final String MOBILE_PHONE = "1234567890";
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
    ProductTypeDTO productTypeDTO;
    ProductType productType;
    Department department;
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
    }

    @Test
    void updateProductByDTO() {
    }

    @Test
    void deleteProducttById() {
    }

    @Test
    void isNew() {
    }

    @Test
    void findAllByName() {
    }

    @Test
    void findAllBbyDepartment() {
    }

    @Test
    void findAllByProductType() {
    }
}