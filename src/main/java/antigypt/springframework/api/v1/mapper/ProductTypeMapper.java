package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.ProductTypeDTO;
import antigypt.springframework.domain.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductTypeMapper {
    ProductTypeMapper INSTANCE = Mappers.getMapper(ProductTypeMapper.class);

    ProductType ProductTypeDTOToProductType(ProductTypeDTO productTypeDTO);
    ProductTypeDTO ProductTypeToProductTypeDTO(ProductType productType);
}
