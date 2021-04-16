package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.SaleDTO;
import antigypt.springframework.domain.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SaleMapper {

        SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);
        Sale postDTOToPost(SaleDTO saleDTO);
        SaleDTO saleToSaleDTO(Sale sale);

}
