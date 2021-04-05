package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.SubscribeMapper;
import antigypt.springframework.api.v1.model.SubscribeDTO;
import antigypt.springframework.domain.Subscribe;
import antigypt.springframework.exceptions.ResourceNotFoundException;
import antigypt.springframework.repositories.SubscribeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SubscribeServiceImplTest {
    private static final Long SUBSCRIBE_ID =1L;
    private static final String EMAIL = "omid@joukar.com";
    private static final String SUBSCRIBE_URL = "/api/v1/subscribes/1";
    @Mock
    SubscribeRepository subscribeRepository;

    SubscribeMapper subscribeMapper;
    SubscribeServiceImpl subscribeService;
    SubscribeDTO subscribeDTO;
    Subscribe subscribe;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        subscribeMapper = SubscribeMapper.INSTANCE;
        subscribeService = new SubscribeServiceImpl(subscribeRepository,subscribeMapper);
        subscribeDTO = new SubscribeDTO();
        subscribeDTO.setSubscribeUrl(SUBSCRIBE_URL);
        subscribeDTO.setEmail(EMAIL);

        subscribe = new Subscribe();
        subscribe.setEmail(EMAIL);
        subscribe.setSubscribeId(SUBSCRIBE_ID);

    }

    @Test
    void createNewSubscribe() {
        when(subscribeRepository.save(any())).thenReturn(subscribe);
        SubscribeDTO returnedDTO = subscribeService.createNewSubscribe(subscribeDTO);
        assertNotNull(returnedDTO);
        assertEquals(returnedDTO.getEmail() , EMAIL);
        assertEquals(returnedDTO.getSubscribeUrl(),SUBSCRIBE_URL);


    }

    @Test
    void findSubscribeById() {
        when(subscribeRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(subscribe));
        SubscribeDTO returnedDTO = subscribeService.findSubscribeById(1L);
        assertNotNull(returnedDTO);
        assertEquals(returnedDTO.getEmail() , EMAIL);
        assertEquals(returnedDTO.getSubscribeUrl(),SUBSCRIBE_URL);
    }
    @Test
    void findSubscribeByIdNotFound() {
        when(subscribeRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            SubscribeDTO returnedDTO = subscribeService.findSubscribeById(2L);
        });
    }

    @Test
    void getAllSubscribes() {
        when(subscribeRepository.findAll()).thenReturn(Arrays.asList(subscribe));
        List<SubscribeDTO> returnedList = subscribeService.getAllSubscribes();
        assertNotNull(returnedList);
        assertEquals(1 , returnedList.size());
    }

    @Test
    void deleteSubscribeById() {
        subscribeService.deleteSubscribeById(1l);
        verify(subscribeRepository , times(1)).deleteById(anyLong());

    }

    @Test
    void findSubscribeByEmail() {
        when(subscribeRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(subscribe));
        SubscribeDTO returnedDTO = subscribeService.findSubscribeByEmail("omid@joukar.com");
        assertNotNull(returnedDTO);
        assertEquals(returnedDTO.getEmail() , EMAIL);
        assertEquals(returnedDTO.getSubscribeUrl(),SUBSCRIBE_URL);
    }
    @Test
    void findSubscribeByEmailNotFound() {
        when(subscribeRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class , () ->{
            SubscribeDTO returnedDTO = subscribeService.findSubscribeByEmail("omid@joukar.com");
        });
    }

    @Test
    void isNew() {
        SubscribeDTO sendedDTO = new SubscribeDTO();
        subscribeDTO.setEmail("omid@jj.com");
        when(subscribeRepository.findAll()).thenReturn(Arrays.asList(subscribe));
        boolean result = subscribeService.isNew(sendedDTO);
        assertEquals(true ,result );

    }
    @Test
    void isNewFlase() {
        when(subscribeRepository.findAll()).thenReturn(Arrays.asList(subscribe));
        boolean result = subscribeService.isNew(subscribeDTO);
        assertEquals(false ,result );

    }
}