package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.SlideMapper;
import antigypt.springframework.api.v1.model.SlideDTO;
import antigypt.springframework.domain.Slide;
import antigypt.springframework.repositories.SlideRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SlideServiceImplTest {
    private static final Long SLIDE_ID = 1L;
    private static final String COVER = "1.jpg";
    private static final String TITLE = "seyed";
    private static final String TOPIC = "hoda";
    private static final String SLIDE_URL = "/api/v1/slides/1";


    @Mock
    SlideRepository slideRepository;
    SlideMapper slideMapper;
    SlideServiceImpl slideService;

    Slide slide;
    SlideDTO slideDTO;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        slideMapper = SlideMapper.INSTANCE;
        slideService  = new SlideServiceImpl(slideRepository , slideMapper);


        slide = new Slide();
        slide.setSlideId(SLIDE_ID);
        slide.setCover(COVER);
        slide.setTitle(TITLE);
        slide.setTopic(TOPIC);


        slideDTO = new SlideDTO();
        slideDTO.setTitle(TITLE);
        slideDTO.setTopic(TOPIC);
        slideDTO.setSlideUrl(SLIDE_URL);



    }

    @Test
    void getAllSlides() {

        when(slideRepository.findAll()).thenReturn(Arrays.asList(slide));
        List<SlideDTO> slideDTOList = slideService.getAllSlides();
        assertNotNull(slideDTOList);
        assertEquals(1,slideDTOList.size());


    }
}