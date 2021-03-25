package antigypt.springframework.controllers.api.v1;

import antigypt.springframework.Services.DepartmentService;
import antigypt.springframework.Services.EmployeeService;
import antigypt.springframework.api.v1.model.AddressDTO;
import antigypt.springframework.api.v1.model.DepartmentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    private static final String ADDRESS_LINE = "Elisenstarse 1";
    private static final String COUNTRY = "Austria";
    private static final String CITY = "Wien";
    private static final String POSTAL_CODE = "1230";
    private static final String REGION = "Liesing";
    private static final String PHONE_NUMBER = "123456";
    private static final String DETAIL = "this is detail";
    private static final String EMAIL = "omid@gmail.com";

    private static final String FIRST_NAME = "Omid";
    private static final String LAST_NAME = "Joukar";
    private static final String GENDER = "MALE";
    private static final String HOME_PHONE = "123456";
    private static final String MOBILE_PHONE = "1234567890";
    private static final Double DESIRED_SALARY = 1000.0;
    private static final String APPLICATION_DATE = LocalDate.now().toString();
    private static final String BIRTH_DATE = LocalDate.of(1989,9,5).toString();
    private static final String UPDATED_ADDRESS_LINE = "Elisenstrasse 1";
    private static final String UPDATED_COUNTRY = "German";
    private static final String UPDATED_CITY = "Hamburg";
    private static final String UPDATED_EMAIL = "ali@gmail.com";
    private static final String UPDATED_FIRST_NAME = "Ali";
    private static final String UPDATED_LAST_NAME = "Masoomi";
    private static final String UPDATED_HOME_PHONE = "654321";
    private static final String UPDATED_MOBILE_PHONE = "0123456789";
    private static final String UPDATED_POSTAL_CODE = "1120";
    private static final String UPDATED_REGION = "Wien mitte";
    private static final String TITLE = "ING";
    private static final MockMultipartFile SENDED_IMAGE =
            new MockMultipartFile("imagefile","imagefile","text.txt","this si a byte sample".getBytes());
    private static final MockMultipartFile SENDED_CV =
            new MockMultipartFile("cvfile","cvfile","text.txt","this si a byte sample".getBytes());

    DepartmentController departmentController;

    @Mock
    DepartmentService departmentService;
    @Mock
    EmployeeService employeeService;


    MockMvc mockMvc;
    private DepartmentDTO createdDepartmentDTO;
    AddressDTO addressDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        departmentController = new DepartmentController(departmentService,employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController)
                .setControllerAdvice(new ExceptionHandlerController()).build();
        addressDTO = new AddressDTO();
        addressDTO.setAddressLine(ADDRESS_LINE);
        addressDTO.setCity(CITY);
        addressDTO.setCountry(COUNTRY);
        addressDTO.setPostalCode(POSTAL_CODE);
        addressDTO.setRegion(REGION);

        createdDepartmentDTO = new DepartmentDTO();
        createdDepartmentDTO.setEmail(EMAIL);
        createdDepartmentDTO.setDepartmetnUrl("/api/v1/departments/1");
        createdDepartmentDTO.setAddress(addressDTO);
        createdDepartmentDTO.setPhoneNumber(PHONE_NUMBER);
        createdDepartmentDTO.setDetail(DETAIL);


    }

    @Test
    void createNewDepartmentForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(DepartmentController.BASE_URL+"/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(DepartmentController.CREATE_UPDATE_FORM))
                .andExpect(model().attributeExists("departmentForm"));
    }

    @Test
    void processDepartmentCreationForm() throws Exception {
        when(departmentService.createNewDepartment(any(DepartmentDTO.class))).thenReturn(createdDepartmentDTO);
        when(departmentService.isNew(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post(departmentController.BASE_URL)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("phoneNumber",PHONE_NUMBER)
        .param("email",EMAIL)
        .param("addressLine",ADDRESS_LINE)
        .param("city",CITY)
        .param("region",REGION)
        .param("postalCode",POSTAL_CODE)
        .param("country",COUNTRY)
        ).andExpect(status().isCreated())
                .andExpect(view().name("redircet:/"+DepartmentController.BASE_URL+"/"+createdDepartmentDTO.getDepartmetnUrl().split("/")[4]));
    }
    @Test
    void processDepartmentCreationFormRejected() throws Exception {
        when(departmentService.isNew(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post(departmentController.BASE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("phoneNumber", PHONE_NUMBER)
                .param("email", EMAIL)
                .param("addressLine", ADDRESS_LINE)
                .param("city", CITY)
                .param("region", REGION)
                .param("postalCode", POSTAL_CODE)
                .param("country", COUNTRY)
        ).andExpect(status().isCreated())
                .andExpect(view().name(departmentController.CREATE_UPDATE_FORM));
    }

    @Test
    void showDepartmentDetails() throws Exception {
        when(departmentService.findDepartmentById(anyLong())).thenReturn(createdDepartmentDTO);
        mockMvc.perform(MockMvcRequestBuilders.get(departmentController.BASE_URL+"/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(departmentController.DEPARTMENTS_SHOW))
                .andExpect(model().attribute("department",hasProperty("email",equalTo(EMAIL))));
    }

    @Test
    void showAllDepartments() throws Exception {
    when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(createdDepartmentDTO));
    mockMvc.perform(MockMvcRequestBuilders.get(departmentController.BASE_URL))
            .andExpect(model().attributeExists("departments"))
            .andExpect(status().isOk())
            .andExpect(view().name(departmentController.DEPARTMENT_LIST))
            .andExpect(model().attribute("departments",hasSize(1)));

    }

    @Test
    void deleteDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(departmentController.BASE_URL+"/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"+DepartmentController.BASE_URL));
        verify(departmentService,times(1)).deleteDepartmentById(anyLong());

    }

    @Test
    void initialDepartmentUpdateForm() throws Exception {
        when(departmentService.findDepartmentById(anyLong())).thenReturn(createdDepartmentDTO);
        mockMvc.perform(MockMvcRequestBuilders.get(departmentController.BASE_URL+"/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name(departmentController.CREATE_UPDATE_FORM))
                .andExpect(model().attributeExists("departmentForm"));
    }

    @Test
    void processFindingAllDepartmentsByName() throws Exception {
        when(departmentService.findAllByEmail(anyString())).thenReturn(Arrays.asList(createdDepartmentDTO,new DepartmentDTO()));
        mockMvc.perform(MockMvcRequestBuilders.post(departmentController.BASE_URL+"/finddepartment")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("phoneNumber", PHONE_NUMBER)
                .param("email", EMAIL)
                .param("addressLine", ADDRESS_LINE)
                .param("city", CITY)
                .param("region", REGION)
                .param("postalCode", POSTAL_CODE)
                .param("country", COUNTRY))
                .andExpect(status().isOk())
                .andExpect(view().name(departmentController.DEPARTMENT_LIST));
    }
    @Test
    void processFindingAllDepartmentsByNameOneValue() throws Exception {
        when(departmentService.findAllByEmail(anyString())).thenReturn(Arrays.asList(createdDepartmentDTO));
        mockMvc.perform(MockMvcRequestBuilders.post(departmentController.BASE_URL+"/finddepartment")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("phoneNumber", PHONE_NUMBER)
                .param("email", EMAIL)
                .param("addressLine", ADDRESS_LINE)
                .param("city", CITY)
                .param("region", REGION)
                .param("postalCode", POSTAL_CODE)
                .param("country", COUNTRY))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/"+DepartmentController.BASE_URL+"/1"));
    }
    @Test
    void processFindingAllDepartmentsByNameNotFound() throws Exception {
        when(departmentService.findAllByEmail(anyString())).thenReturn(Arrays.asList());
        mockMvc.perform(MockMvcRequestBuilders.post(departmentController.BASE_URL+"/finddepartment")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("phoneNumber", PHONE_NUMBER)
                .param("email", EMAIL)
                .param("addressLine", ADDRESS_LINE)
                .param("city", CITY)
                .param("region", REGION)
                .param("postalCode", POSTAL_CODE)
                .param("country", COUNTRY))
                .andExpect(status().isOk())
                .andExpect(view().name(departmentController.DEPARTMENT_SEARCH_FORM));
    }

    @Test
    void initialCreationNewEmployeeForDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(departmentController.BASE_URL+"/1/employees"))
                .andExpect(status().isCreated())
                .andExpect(view().name("redirect:/"+DepartmentController.BASE_URL +"/1"));
    }
}