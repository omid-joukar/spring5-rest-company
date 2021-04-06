package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.SlideDTO;
import antigypt.springframework.domain.Slide;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SlideMapper {

    SlideMapper INSTANCE = Mappers.getMapper(SlideMapper.class);
    SlideDTO slideToSlideDTO(Slide slide);
    Slide slideDTOToSlide(SlideDTO slideDTO);
}
