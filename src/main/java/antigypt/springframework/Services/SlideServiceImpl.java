package antigypt.springframework.Services;



import antigypt.springframework.api.v1.mapper.SlideMapper;
import antigypt.springframework.api.v1.model.SlideDTO;
import antigypt.springframework.repositories.SlideRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlideServiceImpl implements SlideService{

    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;

    public SlideServiceImpl(SlideRepository slideRepository, SlideMapper slideMapper) {
        this.slideRepository = slideRepository;
        this.slideMapper = slideMapper;
    }

    @Override
    public List<SlideDTO> getAllSlides() {
       return  slideRepository.findAll()
                .stream().map(slide -> {
                    SlideDTO slideDTO = slideMapper.slideToSlideDTO(slide);
                    slideDTO.setSlideUrl("/api/v1/slides/"+slide.getSlideId());
                    return slideDTO;
                }).collect(Collectors.toList());
    }
}
