package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.*;
import antigypt.springframework.domain.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private static final Double AMOUNT = 12.34;
    private static final LocalDate PAYMENT_DATE = LocalDate.of(20121,4,23);
    private static final LocalTime PAYMENT_TIME = LocalTime.now();
    private static final Double REBATE = 1.0;
    private static final PaymentType PAYMENT_TYPE = PaymentType.CREDIT_CARD;
    private static final Long ORDER_ID = 1L;
    private static final LocalDate ORDER_DATE = LocalDate.of(20121,4,23);
    private static final LocalTime ORDER_TIME = LocalTime.now();
    private static final LocalDate REQUIRED_DATE = LocalDate.of(20121,4,23);
    private static final LocalDate SHIPPED_DATE = LocalDate.of(20121,4,23);
    private static final LocalTime SHIPPED_TIME = LocalTime.now();
    private static final String COMMENTS = "The order was good";
    private static final OrderStatus ORDER_STATUS = OrderStatus.DELIVERED;
    private static final String PHONENUMBER = "12345678";
    private static final String EMAIL = "omid@gmail.com";
    private static final String DETAIL = "this is detail";
    private static final String ADDRESSLINE = "Elisenstarse 1";
    private static final String COUNTRY = "Austria";
    private static final String CITY = "Wien";
    private static final String POSTALCODE = "1230";
    private static final String REGION = "Liesing";

    private static final String BIRTH_DATE = LocalDate.of(1989,9,5).toString();
    private static final String FIRST_NAME = "Omid";
    private static final String LAST_NAME = "Joukar";
    private static final Long CUSTOMER_NUMBER = 12345L;
    private static final Long CUSTOMER_ID = 12345L;
    private static final String HOME_PHONE = "123456";
    private static final String MOBILE_PHONE = "1234567890";
    private static final LocalDate CREATION_DATE = LocalDate.of(20121,4,23);
    private static final Long BUYTROLLEY_ID = 1L;
    private static final Long PRODUCT_COUNT = 2L;
    private static final String DISCOUNT = ".10";
    private static final LocalDate EXPIRATION_DATE = LocalDate.of(2021,3,2);
    private static final String PRICE = "12";
    private static final LocalDate PRODUCT_DATE = LocalDate.of(2020,3,2);;
    private static final String PRODUCT_ID = "1";
    private static final Long PAYMENT_ID = 1L;
    private static final String PRODUCT_NAME = "shampoo";
    private static final String WEIGHT = "1";

    private static final MockMultipartFile SENDED_IMAGE =
            new MockMultipartFile("imagefile","imagefile","text.txt","this si a byte sample".getBytes());




    DepartmentMapper departmentMapper;
    EmployeeMapper employeeMapper;
    CustomerMapper customerMapper;
    OrderMapper orderMapper;
    PaymentMapper paymentMapper;
    BuyTrolleyMapper buyTrolleyMapper;
    ProductMapper productMapper;


    Department savedReturnedDepartment;
    DepartmentDTO sendedDepartmentDTO;
    Address address;
    Employee employee1;
    Employee employee2;
    EmployeeDTO employeeDTO;
    Payment payment;
    PaymentDTO paymentDTO;
    Order order;
    OrderDTO orderDTO;
    Customer customer;
    CustomerDTO customerDTO;
    BuyTrolley buyTrolley;
    BuyTrolleyDTO buyTrolleyDTO;
    Product product;
    ProductDTO productDTO;
    ProductType productType;
    ProductTypeDTO productTypeDTO;
    Byte[] getBytes;
    AddressDTO addressDTO;






    @SneakyThrows
    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        departmentMapper = DepartmentMapper.INSTANCE;
        employeeMapper = EmployeeMapper.INSTANCE;
        employeeMapper = EmployeeMapper.INSTANCE;
        customerMapper = CustomerMapper.INSTANCE;
        orderMapper = OrderMapper.INSTANCE;
        paymentMapper = PaymentMapper.INSTANCE;
        buyTrolleyMapper = BuyTrolleyMapper.INSTANCE;
        productMapper = ProductMapper.INSTANCE;

        getBytes = new Byte[SENDED_IMAGE.getBytes().length];
        int i = 0 ;
        for (byte b : SENDED_IMAGE.getBytes()){
            getBytes[i++] = b;
        }
        address = new Address();
        address.setAddressLine(ADDRESSLINE);
        address.setCity(CITY);
        address.setCountry(COUNTRY);
        address.setPostalCode(POSTALCODE);
        address.setRegion(REGION);

        addressDTO = new AddressDTO();
        addressDTO.setAddressLine(ADDRESSLINE);
        addressDTO.setCity(CITY);
        addressDTO.setCountry(COUNTRY);
        addressDTO.setPostalCode(POSTALCODE);
        addressDTO.setRegion(REGION);

        productType = new ProductType();
        productType.setDetail(DETAIL);
        productType.setProductCategory(ProductCategory.PAPER_PRODUCTS);
        productType.setProductTypeId(1L);
        productType.setProductTypeName(ProductTypeName.CUPS_PLATES);

        productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setDetail(DETAIL);
        productTypeDTO.setProductCategory(ProductCategory.PAPER_PRODUCTS);
        productTypeDTO.setProductTypeUrl("/api/v1/productTypes/1");
        productTypeDTO.setProductTypeName(ProductTypeName.CUPS_PLATES);

        employee1 = new Employee();
        employee1.setEmployeeId(1L);
        employee1.setFirstName(FIRST_NAME);
        employee1.setLastName(LAST_NAME);
        employee1.setBirthDate(LocalDate.of(1989,9,5));
        employee1.setHireDate(LocalDate.of(2012,9,5));
        employee1.setEmail(EMAIL);
        employee1.setHomePhone(HOME_PHONE);
        employee1.setMobilePhone(MOBILE_PHONE);
        employee1.setAddress(address);

        employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeUrl("/api/v1/employees/1");
        employeeDTO.setFirstName(FIRST_NAME);
        employeeDTO.setLastName(LAST_NAME);
        employeeDTO.setBirthDate(LocalDate.of(1989,9,5));
        employeeDTO.setHireDate(LocalDate.of(2012,9,5));
        employeeDTO.setEmail(EMAIL);
        employeeDTO.setHomePhone(HOME_PHONE);
        employeeDTO.setMobilePhone(MOBILE_PHONE);
        employeeDTO.setAddress(addressDTO);


        employee2 = new Employee();
        employee2.setEmployeeId(2L);
        employee2.setFirstName(FIRST_NAME);
        employee2.setLastName(LAST_NAME);
        employee2.setBirthDate(LocalDate.of(1989,9,5));
        employee2.setHireDate(LocalDate.of(2012,9,5));
        employee2.setEmail(EMAIL);
        employee2.setHomePhone(HOME_PHONE);
        employee2.setMobilePhone(MOBILE_PHONE);
        employee2.setAddress(address);

        savedReturnedDepartment = new Department();
        savedReturnedDepartment.setDepartmentId(1L);
        savedReturnedDepartment.setEmail(EMAIL);
        savedReturnedDepartment.setPhoneNumber(PHONENUMBER);
        savedReturnedDepartment.setDetail(DETAIL);
        savedReturnedDepartment.setAddress(address);
        savedReturnedDepartment.setEmployeeList(Arrays.asList(employee1,employee2));



        sendedDepartmentDTO = new DepartmentDTO();
        sendedDepartmentDTO.setAddress(addressDTO);
        sendedDepartmentDTO.setDetail(DETAIL);
        sendedDepartmentDTO.setEmail(EMAIL);
        sendedDepartmentDTO.setPhoneNumber(PHONENUMBER);
        employee1.setDepartment(savedReturnedDepartment);
        employee2.setDepartment(savedReturnedDepartment);

        product = new Product();
        product.setDetail(DETAIL);
        product.setDiscount(Double.valueOf(DISCOUNT));
        product.setExpirationDate(EXPIRATION_DATE);
        product.setImage(getBytes);
        product.setPrice(Double.valueOf(PRICE));
        product.setProduceDate(PRODUCT_DATE);
        product.setProductCount(Long.valueOf(PRODUCT_COUNT));
        product.setProductId(Long.valueOf(PRODUCT_ID));
        product.setProductName(PRODUCT_NAME);
        product.setSerialNumber(getBytes);
        product.setWeight(Double.valueOf(WEIGHT));

        product.setProductType(productType);

        productDTO = new ProductDTO();
        productDTO.setDetail(DETAIL);
        productDTO.setDiscount(DISCOUNT);
        productDTO.setExpirationDate(String.valueOf(EXPIRATION_DATE));
        productDTO.setImage(getBytes);
        productDTO.setPrice(PRICE);
        productDTO.setProduceDate(String.valueOf(PRODUCT_DATE));
        productDTO.setProductCount(String.valueOf(PRODUCT_COUNT));
        productDTO.setProductName(PRODUCT_NAME);
        productDTO.setSerialNumber(getBytes);
        productDTO.setWeight(WEIGHT);
        productDTO.setProductType(productTypeDTO);
        productDTO.setProductUrl("/api/v1/products/1");

        order = new Order();
        order.setOrderId(ORDER_ID);
        order.setComments(COMMENTS);
        order.setDeliverEmployee(employee1);
        order.setDepartment(savedReturnedDepartment);
        order.setOrderDate(ORDER_DATE);
        order.setOrderStatus(ORDER_STATUS);
        order.setOrderTime(ORDER_TIME);
        order.setRequiredDate(REQUIRED_DATE);
        order.setShippedDate(SHIPPED_DATE);
        order.setShippedTime(SHIPPED_TIME);

        buyTrolley = new BuyTrolley();
        buyTrolley.setBuyTrolleyId(BUYTROLLEY_ID);
        buyTrolley.setProductCount(PRODUCT_COUNT);
        buyTrolley.setProductList(Arrays.asList(product));

        customer = new Customer();
        customer.setAddress(address);
        customer.setBirthDate(LocalDate.parse(BIRTH_DATE));
        customer.setCreationDate(CREATION_DATE);
        customer.setCustomerId(CUSTOMER_ID);
        customer.setBuyTrolley(buyTrolley);
        customer.setCustomerNumber(CUSTOMER_NUMBER);
        customer.setEmail(EMAIL);
        customer.setFirstName(FIRST_NAME);
        customer.setHomePhone(HOME_PHONE);
        customer.setLastName(LAST_NAME);
        customer.setMobilePhone(MOBILE_PHONE);
        customer.getOrderList().add(order);
        buyTrolley.setCustomer(customer);
        order.setCustomer(customer);



        payment = new Payment();
        payment.setAmount(AMOUNT);
        payment.setPaymentDate(PAYMENT_DATE);
        payment.setPaymentTime(PAYMENT_TIME);
        payment.setRebate(REBATE);
        payment.setPaymentsId(PAYMENT_ID);
        payment.setOrder(order);
        payment.setPaymentType(PAYMENT_TYPE);
        order.setPayment(payment);
        buyTrolleyDTO = new BuyTrolleyDTO();
        buyTrolleyDTO.setBuyTrolleyUrl("/api/v1/buyTrolleys/1");
        buyTrolleyDTO.setProductCount(PRODUCT_COUNT);

        orderDTO = new OrderDTO();
        orderDTO.setOrderUrl("/api/v1/orders/1");
        orderDTO.setComments(COMMENTS);
        orderDTO.setDeliverEmployee(employeeDTO);
        orderDTO.setDepartment(sendedDepartmentDTO);
        orderDTO.setOrderDate(String.valueOf(ORDER_DATE));
        orderDTO.setOrderStatus(ORDER_STATUS);
        orderDTO.setOrderTime(String.valueOf(ORDER_TIME));
        orderDTO.setRequiredDate(String.valueOf(REQUIRED_DATE));
        orderDTO.setShippedDate(String.valueOf(SHIPPED_DATE));
        orderDTO.setShippedTime(String.valueOf(SHIPPED_TIME));

        customerDTO = new CustomerDTO();
        customerDTO.setAddress(addressDTO);
        customerDTO.setBirthDate(BIRTH_DATE);
        customerDTO.setCreationDate(String.valueOf(CREATION_DATE));
        customerDTO.setCustomerUrl("/api/v1/customers/1");
        customerDTO.setBuyTrolley(buyTrolleyDTO);
        customerDTO.setCustomerNumber(CUSTOMER_NUMBER);
        customerDTO.setEmail(EMAIL);
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setHomePhone(HOME_PHONE);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setMobilePhone(MOBILE_PHONE);
        //buyTrolleyDTO.setCustomer(customerDTO);
        orderDTO.setCustomer(customerDTO);






        paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(AMOUNT);
        paymentDTO.setPaymentDate(PAYMENT_DATE);
        paymentDTO.setPaymentTime(PAYMENT_TIME);
        paymentDTO.setRebate(REBATE);
        paymentDTO.setPaymentUrl("/api/v1/payments/1");
        paymentDTO.setOrder(orderDTO);
        paymentDTO.setPaymentType(PaymentType.CREDIT_CARD);
        order.setPayment(payment);
    }

    @Test
    void customerDTOToCustomer() {
        Customer customer1 = customerMapper.customerDTOToCustomer(customerDTO);
        assertNotNull(customer1);
    }

    @Test
    void customerToCustomerDTO() {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        assertNotNull(customerDTO);
    }
}