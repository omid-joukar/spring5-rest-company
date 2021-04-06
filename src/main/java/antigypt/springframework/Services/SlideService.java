package antigypt.springframework.Services;

import antigypt.springframework.api.v1.model.SlideDTO;

import java.util.List;

public interface SlideService {
    List<SlideDTO> getAllSlides();
}
