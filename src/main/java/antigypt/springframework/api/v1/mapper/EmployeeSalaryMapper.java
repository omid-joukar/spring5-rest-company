package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.EmployeeSalaryDTO;
import antigypt.springframework.domain.EmployeeSalary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeSalaryMapper {
    EmployeeSalaryMapper INSTANCE = Mappers.getMapper(EmployeeSalaryMapper.class);

    EmployeeSalary employeeSalaryDTOToEmployeeSalary(EmployeeSalaryDTO employeeSalaryDTO);
    EmployeeSalaryDTO employeeSalaryToEmployeeSalaryDTO(EmployeeSalary employeeSalary);
}