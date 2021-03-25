package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.DepartmentDTO;
import antigypt.springframework.domain.Department;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;



@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDTO departmentToDepartmentDTO(Department department);
    Department departmentDTOToDepartment(DepartmentDTO departmentDTO);

}