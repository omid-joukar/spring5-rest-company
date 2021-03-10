package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.DepartmentCorruptProductDTO;
import antigypt.springframework.domain.DepartmentCorruptProduct;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentCorruptProductMapper{
    DepartmentCorruptProductMapper INSTANCE = Mappers.getMapper(DepartmentCorruptProductMapper.class);
    DepartmentCorruptProduct departmentCorruptProductDTOToDepartmentCorruptProduct(DepartmentCorruptProductDTO departmentCorruptProductDTO);
    DepartmentCorruptProductDTO departmentCorruptProductToDepartmentCorruptProductDTO(DepartmentCorruptProduct departmentCorruptProduct);
}
