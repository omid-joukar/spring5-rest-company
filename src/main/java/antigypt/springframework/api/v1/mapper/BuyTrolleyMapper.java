package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.BuyTrolleyDTO;
import antigypt.springframework.domain.BuyTrolley;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public interface BuyTrolleyMapper {
    BuyTrolleyMapper INSTANCE = Mappers.getMapper(BuyTrolleyMapper.class);

    BuyTrolley buyTrolleyDTOToBuyTrolley(BuyTrolleyDTO buyTrolleyDTO);
    BuyTrolleyDTO buyTrolleyToBuyTrolleyDTO(BuyTrolley buyTrolley);

}
