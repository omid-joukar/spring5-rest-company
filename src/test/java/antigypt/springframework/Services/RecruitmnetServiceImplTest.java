package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.RecruitmentMapper;
import antigypt.springframework.api.v1.model.AddressDTO;
import antigypt.springframework.api.v1.model.RecruitmentDTO;
import antigypt.springframework.controllers.api.v1.RecruitmentController;
import antigypt.springframework.domain.Address;
import antigypt.springframework.domain.Gender;
import antigypt.springframework.domain.Recruitment;
import antigypt.springframework.domain.Title;
import antigypt.springframework.exceptions.ResourceNotFoundException;
import antigypt.springframework.repositories.RecruitmentRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RecruitmnetServiceImplTest {
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
    private static final String UPDATED_MOBILE_PHONE = "0987654321";
    private static final String UPDATED_POSTAL_CODE = "1120";
    private static final String UPDATED_REGION = "Wien mitte";
    private static final String TITLE = "ING";
    private static final MockMultipartFile SENDED_IMAGE =
            new MockMultipartFile("imagefile","imagefile","text.txt","this si a byte sample".getBytes());
    private static final MockMultipartFile SENDED_CV =
            new MockMultipartFile("cvfile","cvfile","text.txt","this si a byte sample".getBytes());

    @Mock
    RecruitmentRepository recruitmentRepository;
    RecruitmentMapper recruitmentMapper = RecruitmentMapper.INSTANCE;
    RecruitmnetServiceImpl recruitmentService;
    Byte[] getBytes;

    Address address;
    AddressDTO addressDTO;
    Address wantToUpdateAddress;
    AddressDTO wantToUpdateAddressDTO;
    Recruitment savedRecruitment;
    RecruitmentDTO returnedRecruitmentDTO;
    RecruitmentDTO returnedUpdatedRecruitmentDTO;
    Recruitment foundedRecruitment;
    Recruitment wantToUpdateRecruitment;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recruitmentService = new RecruitmnetServiceImpl(recruitmentRepository,recruitmentMapper);
         getBytes = new Byte[SENDED_IMAGE.getBytes().length];
        int i = 0 ;
        for (byte b : SENDED_IMAGE.getBytes()){
            getBytes[i++] = b;
        }

        address = new Address();
        address.setAddressLine(ADDRESS_LINE);
        address.setCity(CITY);
        address.setCountry(COUNTRY);
        address.setPostalCode(POSTAL_CODE);
        address.setRegion(REGION);

        addressDTO = new AddressDTO();
        addressDTO.setAddressLine(ADDRESS_LINE);
        addressDTO.setCity(CITY);
        addressDTO.setCountry(COUNTRY);
        addressDTO.setPostalCode(POSTAL_CODE);
        addressDTO.setRegion(REGION);

        wantToUpdateAddress = new Address();
        wantToUpdateAddress.setAddressLine(UPDATED_ADDRESS_LINE);
        wantToUpdateAddress.setCity(UPDATED_CITY);
        wantToUpdateAddress.setCountry(UPDATED_COUNTRY);
        wantToUpdateAddress.setPostalCode(UPDATED_POSTAL_CODE);
        wantToUpdateAddress.setRegion(UPDATED_REGION);

        wantToUpdateAddressDTO = new AddressDTO();
        wantToUpdateAddressDTO.setAddressLine(UPDATED_ADDRESS_LINE);
        wantToUpdateAddressDTO.setCity(UPDATED_CITY);
        wantToUpdateAddressDTO.setCountry(UPDATED_COUNTRY);
        wantToUpdateAddressDTO.setPostalCode(UPDATED_POSTAL_CODE);
        wantToUpdateAddressDTO.setRegion(UPDATED_REGION);

        savedRecruitment = new Recruitment();
        savedRecruitment.setRecruitmentId(1L);
        savedRecruitment.setAddress(address);
        savedRecruitment.setApplicationDate(LocalDate.now());
        savedRecruitment.setBirthDate(LocalDate.of(1989,9,5));
        savedRecruitment.setDesiredSalary(DESIRED_SALARY);
        savedRecruitment.setCv(getBytes);
        savedRecruitment.setDetail(DETAIL);
        savedRecruitment.setEmail(EMAIL);
        savedRecruitment.setFirstName(FIRST_NAME);
        savedRecruitment.setGender(Gender.MALE);
        savedRecruitment.setHomePhone(HOME_PHONE);
        savedRecruitment.setLastName(LAST_NAME);
        savedRecruitment.setMobilePhone(MOBILE_PHONE);
        savedRecruitment.setPhoto(getBytes);
        savedRecruitment.setTitle(Title.ING);

        foundedRecruitment = new Recruitment();
        foundedRecruitment.setRecruitmentId(1L);
        foundedRecruitment.setAddress(address);
        foundedRecruitment.setApplicationDate(LocalDate.now());
        foundedRecruitment.setBirthDate(LocalDate.of(1989,9,5));
        foundedRecruitment.setDesiredSalary(DESIRED_SALARY);
        foundedRecruitment.setCv(getBytes);
        foundedRecruitment.setDetail(DETAIL);
        foundedRecruitment.setEmail(EMAIL);
        foundedRecruitment.setFirstName(FIRST_NAME);
        foundedRecruitment.setGender(Gender.MALE);
        foundedRecruitment.setHomePhone(HOME_PHONE);
        foundedRecruitment.setLastName(LAST_NAME);
        foundedRecruitment.setMobilePhone(MOBILE_PHONE);
        foundedRecruitment.setPhoto(getBytes);
        foundedRecruitment.setTitle(Title.ING);


        wantToUpdateRecruitment = new Recruitment();
        wantToUpdateRecruitment.setRecruitmentId(1L);
        wantToUpdateRecruitment.setAddress(wantToUpdateAddress);
        wantToUpdateRecruitment.setApplicationDate(LocalDate.now());
        wantToUpdateRecruitment.setBirthDate(LocalDate.now());
        wantToUpdateRecruitment.setDesiredSalary(DESIRED_SALARY);
        wantToUpdateRecruitment.setCv(getBytes);
        wantToUpdateRecruitment.setDetail(DETAIL);
        wantToUpdateRecruitment.setEmail(UPDATED_EMAIL);
        wantToUpdateRecruitment.setFirstName(UPDATED_FIRST_NAME);
        wantToUpdateRecruitment.setGender(Gender.MALE);
        wantToUpdateRecruitment.setHomePhone(UPDATED_HOME_PHONE);
        wantToUpdateRecruitment.setLastName(UPDATED_LAST_NAME);
        wantToUpdateRecruitment.setMobilePhone(UPDATED_MOBILE_PHONE);
        wantToUpdateRecruitment.setPhoto(getBytes);
        wantToUpdateRecruitment.setTitle(Title.ING);

        returnedRecruitmentDTO  = new RecruitmentDTO();
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


        returnedUpdatedRecruitmentDTO = new RecruitmentDTO();
        returnedUpdatedRecruitmentDTO.setAddress(wantToUpdateAddressDTO);
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
    }

    @Test
    void createNewRecruitmnet() throws IOException {

       when(recruitmentRepository.save(any(Recruitment.class))).thenReturn(savedRecruitment);
       RecruitmentDTO savedReturnedDTO = recruitmentService.createNewRecruitmnet(returnedRecruitmentDTO);
       assertEquals(savedReturnedDTO.getCv().length,SENDED_CV.getBytes().length);
       assertEquals(savedReturnedDTO.getPhoto().length,SENDED_IMAGE.getBytes().length);
       assertEquals(savedReturnedDTO.getFirstName(),savedRecruitment.getFirstName());
       assertEquals(RecruitmentController.BASE_URL+"/1",savedReturnedDTO.getRecruitmentUrl());
    }

    @SneakyThrows
    @Test
    void findRecruitmentById() {
        when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.of(foundedRecruitment));
        RecruitmentDTO returnedDTO = recruitmentService.findRecruitmentById(1L);
        assertEquals(returnedDTO.getCv().length,SENDED_CV.getBytes().length);
        assertEquals(returnedDTO.getPhoto().length,SENDED_IMAGE.getBytes().length);
        assertEquals(returnedDTO.getFirstName(),foundedRecruitment.getFirstName());
        assertEquals(RecruitmentController.BASE_URL+"/1",returnedDTO.getRecruitmentUrl());
    }

    @Test
    public void findRecruitmentByIdNotFound()throws Exception{
        when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception ex = assertThrows(ResourceNotFoundException.class,()->{recruitmentService.findRecruitmentById(2L);});
        assertEquals("Id is invalid : " + 2L,ex.getMessage() );

    }

    @Test
    void getAllRecruitments() {
        List<RecruitmentDTO> recruitmentDTOList = new ArrayList<>();
        List<Recruitment> recruitmentList = new ArrayList<>();
        recruitmentList.add(foundedRecruitment);
        recruitmentList.add(savedRecruitment);
        when(recruitmentRepository.findAll()).thenReturn(recruitmentList);
        recruitmentDTOList.addAll(recruitmentService.getAllRecruitments());
        assertNotNull(recruitmentDTOList);
        assertEquals(recruitmentList.size(),recruitmentDTOList.size());

    }

    @Test
    void isNew() {
        List<Recruitment> recruitmentList = new ArrayList<>();
        recruitmentList.add(foundedRecruitment);
        recruitmentList.add(savedRecruitment);
        when(recruitmentRepository.findAll()).thenReturn(recruitmentList);
        boolean isNewObject = recruitmentService.isNew(returnedRecruitmentDTO);
        assertEquals(false,isNewObject);
    }

    @Test
    void deleteRecruitmentById() {
        Recruitment recruitment = new Recruitment();
        recruitment.setRecruitmentId(1L);
        recruitmentRepository.save(recruitment);
        recruitmentService.deleteRecruitmentById(1L);
        Recruitment foundedRecruitment = recruitmentRepository.findById(1L).orElse(null);
        assertNull(foundedRecruitment);
    }



    @Test
    void isNewTrue() {
        RecruitmentDTO recruitmentDTO = new RecruitmentDTO();
        recruitmentDTO.setFirstName("Ali");
        recruitmentDTO.setLastName("Joukar");
        recruitmentDTO.setBirthDate("05.09.1989");
        List<Recruitment> recruitmentList = new ArrayList<>();
        recruitmentList.add(foundedRecruitment);
        recruitmentList.add(savedRecruitment);
        when(recruitmentRepository.findAll()).thenReturn(recruitmentList);
        boolean isNewObject = recruitmentService.isNew(recruitmentDTO);
        assertEquals(true,isNewObject);
    }


    @Test
    void updateRecruitmnetByDTO() {
        when(recruitmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(foundedRecruitment));
        when(recruitmentRepository.save(any())).thenReturn(wantToUpdateRecruitment);
        RecruitmentDTO updatedRecruitmentDTO = recruitmentService.updateRecruitmnetByDTO(1L,returnedUpdatedRecruitmentDTO);
        assertNotNull(updatedRecruitmentDTO);
        assertEquals(UPDATED_ADDRESS_LINE,updatedRecruitmentDTO.getAddress().getAddressLine());
        assertEquals(UPDATED_CITY,updatedRecruitmentDTO.getAddress().getCity());
        assertEquals(UPDATED_COUNTRY,updatedRecruitmentDTO.getAddress().getCountry());
        assertEquals(UPDATED_REGION,updatedRecruitmentDTO.getAddress().getRegion());
        assertEquals(UPDATED_POSTAL_CODE,updatedRecruitmentDTO.getAddress().getPostalCode());
        assertEquals(UPDATED_FIRST_NAME,updatedRecruitmentDTO.getFirstName());
        assertEquals(UPDATED_LAST_NAME,updatedRecruitmentDTO.getLastName());
        assertEquals(UPDATED_EMAIL,updatedRecruitmentDTO.getEmail());
        assertEquals(UPDATED_MOBILE_PHONE,updatedRecruitmentDTO.getMobilePhone());
        assertEquals(UPDATED_HOME_PHONE,updatedRecruitmentDTO.getHomePhone());
        assertEquals("/api/v1/recruitments/1",updatedRecruitmentDTO.getRecruitmentUrl());
    }
}