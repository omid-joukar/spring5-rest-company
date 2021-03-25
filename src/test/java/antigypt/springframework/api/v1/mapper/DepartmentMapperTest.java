package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.AddressDTO;
import antigypt.springframework.api.v1.model.DepartmentDTO;
import antigypt.springframework.api.v1.model.EmployeeDTO;
import antigypt.springframework.domain.Address;
import antigypt.springframework.domain.Department;
import antigypt.springframework.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentMapperTest {

    private static final String PHONENUMBER = "12345678";
    private static final String EMAIL = "omid@gmail.com";
    private static final String DETAIL = "this is detail";
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

    DepartmentMapper departmentMapper;
    EmployeeMapper employeeMapper;


    Department savedReturnedDepartment;
    Department updatedDepartment;
    DepartmentDTO sendedDepartmentDTO;
    DepartmentDTO sendedToUpdateDepartmentDTO;
    Address address;
    Address updatedAddress;
    Employee employee1;
    Employee employee2;
    EmployeeDTO employeeDTO;
    AddressDTO addressDTO;
    AddressDTO updatedAddressDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        departmentMapper = DepartmentMapper.INSTANCE;
        employeeMapper = EmployeeMapper.INSTANCE;


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

        updatedAddress = new Address();
        updatedAddress.setAddressLine(UPDATED_ADDRESS_LINE);
        updatedAddress.setCity(UPDATED_CITY);
        updatedAddress.setCountry(UPDATED_COUNTRY);
        updatedAddress.setPostalCode(UPDATED_POSTAL_CODE);
        updatedAddress.setRegion(UPDATED_REGION);

        updatedAddressDTO = new AddressDTO();
        updatedAddressDTO.setAddressLine(UPDATED_ADDRESS_LINE);
        updatedAddressDTO.setCity(UPDATED_CITY);
        updatedAddressDTO.setCountry(UPDATED_COUNTRY);
        updatedAddressDTO.setPostalCode(UPDATED_POSTAL_CODE);
        updatedAddressDTO.setRegion(UPDATED_REGION);

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

        updatedDepartment = new Department();
        updatedDepartment.setDepartmentId(1L);
        updatedDepartment.setEmail(UPDATED_EMAIL);
        updatedDepartment.setPhoneNumber(UPDATED_PHONENUMBER);
        updatedDepartment.setDetail(DETAIL);
        updatedDepartment.setAddress(updatedAddress);
        updatedDepartment.getEmployeeList().addAll(Arrays.asList(employee1,employee2));

        sendedDepartmentDTO = new DepartmentDTO();
        sendedDepartmentDTO.setAddress(addressDTO);
        sendedDepartmentDTO.setDetail(DETAIL);
        sendedDepartmentDTO.setEmail(EMAIL);
        sendedDepartmentDTO.setPhoneNumber(PHONENUMBER);


        sendedToUpdateDepartmentDTO = new DepartmentDTO();
        sendedToUpdateDepartmentDTO.setAddress(updatedAddressDTO);
        sendedToUpdateDepartmentDTO.setDetail(DETAIL);
        sendedToUpdateDepartmentDTO.setEmail(UPDATED_EMAIL);
        sendedToUpdateDepartmentDTO.setPhoneNumber(UPDATED_PHONENUMBER);
        employee1.setDepartment(savedReturnedDepartment);
        employee2.setDepartment(savedReturnedDepartment);




    }

    @Test
    void departmentToDepartmentDTO() {
        DepartmentDTO departmentDTO = departmentMapper.departmentToDepartmentDTO(savedReturnedDepartment);
        assertNotNull(departmentDTO);
    }

    @Test
    void departmentDTOToDepartment() {
        Department department = departmentMapper.departmentDTOToDepartment(sendedDepartmentDTO);
        assertNotNull(department);
    }
}