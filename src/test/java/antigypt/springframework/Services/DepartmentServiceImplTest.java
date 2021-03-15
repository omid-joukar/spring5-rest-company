package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.DepartmentMapper;
import antigypt.springframework.api.v1.mapper.EmployeeMapper;
import antigypt.springframework.api.v1.model.DepartmentDTO;
import antigypt.springframework.api.v1.model.EmployeeDTO;
import antigypt.springframework.domain.Address;
import antigypt.springframework.domain.Department;
import antigypt.springframework.domain.Employee;
import antigypt.springframework.exceptions.ResourceNotFoundException;
import antigypt.springframework.repositories.DepartmentRepository;
import antigypt.springframework.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
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
    private static final String FIRST_NAME = "Omid";
    private static final String LAST_NAME = "Joukar";
    private static final String HOME_PHONE = "123456";
    private static final String MOBILE_PHONE = "1234567890";

    @Mock
    DepartmentRepository departmentRepository;
    @Mock
    EmployeeRepository employeeRepository;
    DepartmentMapper departmentMapper;
    EmployeeMapper employeeMapper;
    DepartmentService departmentService;

    Department savedReturnedDepartment;
    Department updatedDepartment;
    DepartmentDTO sendedDepartmentDTO;
    DepartmentDTO sendedToUpdateDepartmentDTO;
    Address address;
    Address updatedAddress;
    Employee employee1;
    Employee employee2;
    EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        departmentMapper = DepartmentMapper.INSTANCE;
        employeeMapper = EmployeeMapper.INSTANCE;
        departmentService = new DepartmentServiceImpl(departmentMapper,employeeMapper,departmentRepository,employeeRepository);

        address = new Address();
        address.setAddressLine(ADDRESSLINE);
        address.setCity(CITY);
        address.setCountry(COUNTRY);
        address.setPostalCode(POSTALCODE);
        address.setRegion(REGION);

        updatedAddress = new Address();
        updatedAddress.setAddressLine(UPDATED_ADDRESS_LINE);
        updatedAddress.setCity(UPDATED_CITY);
        updatedAddress.setCountry(UPDATED_COUNTRY);
        updatedAddress.setPostalCode(UPDATED_POSTAL_CODE);
        updatedAddress.setRegion(UPDATED_REGION);

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
        employeeDTO.setAddressLine(ADDRESSLINE);
        employeeDTO.setRegion(REGION);
        employeeDTO.setCountry(COUNTRY);
        employeeDTO.setCity(CITY);
        employeeDTO.setPostalCode(POSTALCODE);


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
        sendedDepartmentDTO.setAddressLine(ADDRESSLINE);
        sendedDepartmentDTO.setCity(CITY);
        sendedDepartmentDTO.setCountry(COUNTRY);
        sendedDepartmentDTO.setDetail(DETAIL);
        sendedDepartmentDTO.setEmail(EMAIL);
        sendedDepartmentDTO.setPhoneNumber(PHONENUMBER);
        sendedDepartmentDTO.setPostalCode(POSTALCODE);
        sendedDepartmentDTO.setRegion(REGION);


        sendedToUpdateDepartmentDTO = new DepartmentDTO();
        sendedToUpdateDepartmentDTO.setAddressLine(UPDATED_ADDRESS_LINE);
        sendedToUpdateDepartmentDTO.setCity(UPDATED_CITY);
        sendedToUpdateDepartmentDTO.setCountry(UPDATED_COUNTRY);
        sendedToUpdateDepartmentDTO.setDetail(DETAIL);
        sendedToUpdateDepartmentDTO.setEmail(UPDATED_EMAIL);
        sendedToUpdateDepartmentDTO.setPhoneNumber(UPDATED_PHONENUMBER);
        sendedToUpdateDepartmentDTO.setPostalCode(UPDATED_POSTAL_CODE);
        sendedToUpdateDepartmentDTO.setRegion(UPDATED_REGION);
        employee1.setDepartment(savedReturnedDepartment);
        employee2.setDepartment(savedReturnedDepartment);




    }

    @Test
    void createNewDepartment() {
        when(departmentRepository.save(any(Department.class))).thenReturn(savedReturnedDepartment);
        DepartmentDTO savedReturnedDepartmentDTO = departmentService.createNewDepartment(sendedDepartmentDTO);
        assertEquals(savedReturnedDepartmentDTO.getEmail(),EMAIL);
        assertEquals(savedReturnedDepartmentDTO.getPhoneNumber(),PHONENUMBER);
        assertEquals(savedReturnedDepartmentDTO.getAddressLine(),ADDRESSLINE);

    }

    @Test
    void findDepartmentById() {
        when(departmentRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(savedReturnedDepartment));
        DepartmentDTO foundedDepartmentDTO = departmentService.findDepartmentById(1L);
        assertEquals(foundedDepartmentDTO.getEmail(),EMAIL);
        assertEquals(foundedDepartmentDTO.getPhoneNumber(),PHONENUMBER);
        assertEquals(foundedDepartmentDTO.getAddressLine(),ADDRESSLINE);



    }

    @Test
    void findDepartmentByIdThrowException() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->
        {
            DepartmentDTO foundedDepartmentDTO = departmentService.findDepartmentById(1L);
        });
    }



    @Test
    void getAllDepartments() {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(savedReturnedDepartment);
        when(departmentRepository.findAll()).thenReturn(departmentList);
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartments();
        assertEquals(departmentDTOList.size(),departmentList.size());
    }

    @Test
    void updateDepartmentByDTO() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(savedReturnedDepartment));
        when(departmentRepository.save(any())).thenReturn(updatedDepartment);
        DepartmentDTO updatedDepartmentDTO  = departmentService.updateDepartmentByDTO(1L,sendedToUpdateDepartmentDTO);
        assertEquals(updatedDepartmentDTO.getEmail(),UPDATED_EMAIL);
        assertEquals(updatedDepartmentDTO.getPhoneNumber(),UPDATED_PHONENUMBER);
        assertEquals(updatedDepartmentDTO.getCity(),UPDATED_CITY);
        assertEquals(updatedDepartmentDTO.getRegion(),UPDATED_REGION);
        assertEquals(updatedDepartmentDTO.getCountry(),UPDATED_COUNTRY);



    }

    @Test
    void deleteDepartmentById() {
        departmentService.deleteDepartmentById(1L);
        verify(departmentRepository,times(1)).deleteById(anyLong());
    }

    @Test
    void getAllDepartmentEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.addAll(Arrays.asList(employee1,employee2));
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<EmployeeDTO> employeeDTOList = departmentService.getAllDepartmentEmployees(1L);
        assertEquals(Long.valueOf(2),employeeDTOList.size());

    }

    @Test
    void isNew() {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(savedReturnedDepartment);
        when(departmentRepository.findAll()).thenReturn(departmentList);
        DepartmentDTO newDepartmentDTO = new DepartmentDTO();
        newDepartmentDTO.setEmail("ali@gmail.com");
        boolean result = departmentService.isNew(newDepartmentDTO);
        assertEquals(true , result);
    }

    @Test
    void isNotNew() {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(savedReturnedDepartment);
        when(departmentRepository.findAll()).thenReturn(departmentList);
        DepartmentDTO newDepartmentDTO = new DepartmentDTO();
        newDepartmentDTO.setEmail("omid@gmail.com");
        boolean result = departmentService.isNew(newDepartmentDTO);
        assertEquals(false , result);
    }
}