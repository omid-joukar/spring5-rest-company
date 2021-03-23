package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.BuyTrolleyDTO;
import antigypt.springframework.domain.BuyTrolley;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BuyTrolleyMapper {
    BuyTrolleyMapper INSTANCE = Mappers.getMapper(BuyTrolleyMapper.class);

    @Mappings({@Mapping(source = "customer" , target = "customer")})
    BuyTrolley buyTrolleyDTOToBuyTrolley(BuyTrolleyDTO buyTrolleyDTO);
    @Mappings({@Mapping(source = "customer" , target = "customer")})
    BuyTrolleyDTO buyTrolleyToBuyTrolleyDTO(BuyTrolley buyTrolley);

}
