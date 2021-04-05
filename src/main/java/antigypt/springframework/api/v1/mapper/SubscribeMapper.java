package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.SubscribeDTO;
import antigypt.springframework.domain.Subscribe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscribeMapper {
    SubscribeMapper INSTANCE = Mappers.getMapper(SubscribeMapper.class);
    Subscribe subscribeDTOToSubscribe(SubscribeDTO subscribeDTO);
    SubscribeDTO subscribeToSubscribeDTO(Subscribe subscribe);
}
