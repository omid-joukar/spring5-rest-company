package antigypt.springframework.controllers.api.v1;

import antigypt.springframework.Services.RecruitmentService;
import antigypt.springframework.api.v1.model.AddressDTO;
import antigypt.springframework.api.v1.model.RecruitmentDTO;

import antigypt.springframework.domain.Recruitment;
import antigypt.springframework.repositories.RecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecruitmentControllerTest {

    private static final String ADDRESS_LINE = "Elisenstarse 1";
    private static final String COUNTRY = "Austria";
    private static final String APPLICATION_DATE = LocalDate.now().toString();
    private static final String BIRTH_DATE = LocalDate.of(1989,9,5).toString();
    private static final String CITY = "Wien";
    private static final Double DESIRED_SALARY = 1000.0;
    private static final String DETAIL = "this is detail";
    private static final String EMAIL = "omid@gmail.com";
    private static final String FIRST_NAME = "Omid";
    private static final String LAST_NAME = "Joukar";
    private static final String GENDER = "MALE";
    private static final String HOME_PHONE = "123456";
    private static final String MOBILE_PHONE = "1234567890";
    private static final String POSTAL_CODE = "1230";
    private static final String REGION = "Liesing";
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

    @Mock
    RecruitmentService recruitmentService;
    @Mock
    RecruitmentRepository recruitmentRepository;
    @InjectMocks
    RecruitmentController recruitmentController;
    MockMvc mockMvc;
    Byte[] getBytes;

    RecruitmentDTO returnedRecruitmentDTO;
    RecruitmentDTO recruitmentDTONotSame;
    RecruitmentDTO returnedUpdatedRecruitmentDTO;
    AddressDTO addressDTO;
    AddressDTO updatedAddressDTO;

    @BeforeEach
    void setUp() throws IOException {

        mockMvc = MockMvcBuilders.standaloneSetup(recruitmentController).build();
        getBytes = new Byte[SENDED_IMAGE.getBytes().length];
        int i =0 ;
        for (byte b : SENDED_IMAGE.getBytes()){
            getBytes[i++] = b;
        }
        addressDTO = new AddressDTO();
        addressDTO.setAddressLine(ADDRESS_LINE);
        addressDTO.setCity(CITY);
        addressDTO.setCountry(COUNTRY);
        addressDTO.setPostalCode(POSTAL_CODE);
        addressDTO.setRegion(REGION);

        updatedAddressDTO = new AddressDTO();
        updatedAddressDTO.setAddressLine(UPDATED_ADDRESS_LINE);
        updatedAddressDTO.setCity(UPDATED_CITY);
        updatedAddressDTO.setCountry(UPDATED_COUNTRY);
        updatedAddressDTO.setPostalCode(UPDATED_POSTAL_CODE);
        updatedAddressDTO.setRegion(UPDATED_REGION);

        returnedRecruitmentDTO = new RecruitmentDTO();
        returnedRecruitmentDTO.setAddress(addressDTO);
        returnedRecruitmentDTO.setApplicationDate(APPLICATION_DATE);
        returnedRecruitmentDTO.setBirthDate(BIRTH_DATE);

        returnedRecruitmentDTO.setDesiredSalary(DESIRED_SALARY);
        returnedRecruitmentDTO.setCv(getBytes);
        returnedRecruitmentDTO.setDetail(DETAIL);
        returnedRecruitmentDTO.setEmail(EMAIL);
        returnedRecruitmentDTO.setFirstName(FIRST_NAME);
        returnedRecruitmentDTO.setGender(GENDER);
        returnedRecruitmentDTO.setHomePhone(HOME_PHONE);
        returnedRecruitmentDTO.setLastName(LAST_NAME);
        returnedRecruitmentDTO.setMobilePhone(MOBILE_PHONE);
        returnedRecruitmentDTO.setPhoto(getBytes);
        returnedRecruitmentDTO.setTitle(TITLE);
        returnedRecruitmentDTO.setRecruitmentUrl(RecruitmentController.BASE_URL+"/1");

        returnedUpdatedRecruitmentDTO = new RecruitmentDTO();
        returnedUpdatedRecruitmentDTO.setAddress(updatedAddressDTO);
        returnedUpdatedRecruitmentDTO.setApplicationDate(APPLICATION_DATE);
        returnedUpdatedRecruitmentDTO.setBirthDate(BIRTH_DATE);
        returnedUpdatedRecruitmentDTO.setDesiredSalary(DESIRED_SALARY);
        returnedUpdatedRecruitmentDTO.setCv(getBytes);
        returnedUpdatedRecruitmentDTO.setDetail(DETAIL);
        returnedUpdatedRecruitmentDTO.setEmail(UPDATED_EMAIL);
        returnedUpdatedRecruitmentDTO.setFirstName(UPDATED_FIRST_NAME);
        returnedUpdatedRecruitmentDTO.setGender(GENDER);
        returnedUpdatedRecruitmentDTO.setHomePhone(UPDATED_HOME_PHONE);
        returnedUpdatedRecruitmentDTO.setLastName(UPDATED_LAST_NAME);
        returnedUpdatedRecruitmentDTO.setMobilePhone(UPDATED_MOBILE_PHONE);
        returnedUpdatedRecruitmentDTO.setPhoto(getBytes);
        returnedUpdatedRecruitmentDTO.setTitle(TITLE);
        returnedUpdatedRecruitmentDTO.setRecruitmentUrl("/api/v1/recruitment/1");


        recruitmentDTONotSame = new RecruitmentDTO();
        recruitmentDTONotSame.setFirstName("Ali");
        recruitmentDTONotSame.setLastName("Masoomi");
        recruitmentDTONotSame.setBirthDate(LocalDate.of(1989,9,5).toString());

    }

    @Test
    void creatNewRecruitmentForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(RecruitmentController.BASE_URL+"/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recruitmentForm"))
                .andExpect(view().name(RecruitmentController.CREATE_RECRUITMENT_FORM));
    }

    @Test
    void showRecruitmentDetails() throws Exception {
        when(recruitmentService.findRecruitmentById(anyLong())).thenReturn(returnedRecruitmentDTO);
        mockMvc.perform(get(RecruitmentController.BASE_URL+"/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(RecruitmentController.RECRUITMENT_SHOW))
                .andExpect(model().attributeExists("recruitment"))
                .andExpect(model().attribute("recruitment",
                        hasProperty("firstName", equalTo(FIRST_NAME))))
                .andExpect(model().attribute("recruitment",
                        hasProperty("lastName", equalTo(LAST_NAME))))
                .andExpect(model().attribute("recruitment",
                        hasProperty("recruitmentUrl", equalTo(RecruitmentController.BASE_URL+"/1"))))
                .andExpect(model().attribute("recruitment"
                        ,hasProperty("desiredSalary",  equalTo(DESIRED_SALARY))));

    }

    @Test
    void showAllRecruitments() throws Exception {
        List<RecruitmentDTO> recruitmentDTOList = new ArrayList<>();
        recruitmentDTOList.add(returnedRecruitmentDTO);
        when(recruitmentService.getAllRecruitments()).thenReturn(recruitmentDTOList);
        mockMvc.perform(get("/api/v1/recruitments"))
                .andExpect(status().isOk())
                .andExpect(view().name(RecruitmentController.RECRUITMENT_SHOW_ALL))
                .andExpect(model().attributeExists("recruitments"))
                .andExpect(model().attribute("recruitments",hasSize(1)));
    }

    @Test
    void processCreationForm() throws Exception {
        when(recruitmentService.createNewRecruitmnet(any())).thenReturn(returnedRecruitmentDTO);
        when(recruitmentService.isNew(any())).thenReturn(true);
        mockMvc.perform(post(RecruitmentController.BASE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("firstName" , "Ali")
        .param("lastName" , "Masoomi")
        .param("birthDate" , LocalDate.of(1989,9,5).toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:"+RecruitmentController.BASE_URL+"/1"));
        verify(recruitmentService,times(1)).createNewRecruitmnet(any());

    }
    @Test
    void processCreationFormRejected() throws Exception {
        Recruitment recruitment = new Recruitment();
        recruitment.setFirstName("Omid");
        recruitment.setLastName("Joukar");
        recruitment.setApplicationDate(LocalDate.now());
        recruitmentRepository.save(recruitment);
        mockMvc.perform(post(RecruitmentController.BASE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName" , "Omid")
                .param("lastName" , "Jouakr")
                .param("applicationDate",LocalDate.now().toString())
                .param("mobilePhone","1234567890")
                .param("homePhone","123456")
                .param("postalCode","1230")
                .param("region","Liesing")
                .param("country","Austria")
                .param("city","Wien")
                .param("addressLine","Elisenstarse 1")
                .param("email","omid@gmail.com")
                .param("birthDate",LocalDate.of(1989,9,5).toString())
        )
                .andExpect(status().isOk())
                .andExpect(view().name(RecruitmentController.CREATE_RECRUITMENT_FORM));
    }



    @Test
    void showRecruitmentImage() throws Exception {
        when(recruitmentService.findRecruitmentById(anyLong())).thenReturn(returnedRecruitmentDTO);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/recruitments/1/showimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        byte[] responseAsByteArray = response.getContentAsByteArray();
        assertNotNull(responseAsByteArray);
        assertEquals(responseAsByteArray.length,getBytes.length);

    }

    @Test
    void showRecruitmentCV() throws Exception {
        when(recruitmentService.findRecruitmentById(anyLong())).thenReturn(returnedRecruitmentDTO);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/recruitments/1/showcv"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        byte[] responseAsByteArray = response.getContentAsByteArray();
        assertNotNull(responseAsByteArray);
        assertEquals(responseAsByteArray.length,getBytes.length);
    }

    @Test
    void processShowRecruitmentCV() throws Exception {
        when(recruitmentService.findRecruitmentById(anyLong())).thenReturn(returnedRecruitmentDTO);
        mockMvc.perform(get(RecruitmentController.BASE_URL+"/1/processcv"))
                .andExpect(status().isOk())
                .andExpect(view().name(RecruitmentController.RECRUITMENTS_RECRUITMENT_CV))
                .andExpect(model().attributeExists("recruitment"))
                .andExpect(model().attribute("recruitment",
                        hasProperty("firstName", equalTo(FIRST_NAME))))
                .andExpect(model().attribute("recruitment",
                        hasProperty("lastName", equalTo(LAST_NAME))))
                .andExpect(model().attribute("recruitment",
                        hasProperty("recruitmentUrl", equalTo(RecruitmentController.BASE_URL+"/1"))))
                .andExpect(model().attribute("recruitment"
                        ,hasProperty("desiredSalary",  equalTo(DESIRED_SALARY))));
    }


    @Test
    void initialupdateRecruitmentForm() throws Exception {
        when(recruitmentService.findRecruitmentById(anyLong())).thenReturn(returnedRecruitmentDTO);
        mockMvc.perform(get("/api/v1/recruitments/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name(RecruitmentController.UPDATE_RECRUITMENT_FORM))
                .andExpect(model().attributeExists("recruitmentUpdateForm"))
                .andExpect(model().attribute("recruitmentUpdateForm",
                        hasProperty("firstName" , equalTo(FIRST_NAME))))
                .andExpect(model().attribute("recruitmentUpdateForm",
                        hasProperty("lastName" , equalTo(LAST_NAME))))
                .andExpect(model().attribute("recruitmentUpdateForm",
                        hasProperty("recruitmentUrl" , equalTo("/api/v1/recruitments/1"))));
    }

    @Test
    void processUpdateForm() throws Exception {
        when(recruitmentService.updateRecruitmnetByDTO(anyLong(),any(RecruitmentDTO.class))).thenReturn(returnedUpdatedRecruitmentDTO);
        mockMvc.perform(post(RecruitmentController.BASE_URL+"/update")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("firstName","Ali")
        .param("lastName" , "Masoomi")
        .param("email","ali@gmail.com")
        .param("postalCode" , "1110")
        .param("region","Meidling")
        .param("country","German")
        .param("city" , "Hamburg")
        .param("addressLine" , "Elisenstrasse 1")
        .param("homePhone" , "654321")
        .param("mobilePhone" , "0123456789")
        .param("recruitmentUrl","/api/v1/recruitments/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:"+RecruitmentController.BASE_URL+"/1"));
        verify(recruitmentService,times(1)).updateRecruitmnetByDTO(anyLong(),any());

    }


    @Test
    void deleteRecruitment() throws Exception {
        mockMvc.perform(get("/api/v1/recruitments/1/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name(RecruitmentController.RECRUITMENT_SHOW_ALL));
        verify(recruitmentService,times(1)).deleteRecruitmentById(anyLong());
    }





}