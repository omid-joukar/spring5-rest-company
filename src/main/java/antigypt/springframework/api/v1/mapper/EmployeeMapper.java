package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.EmployeeDTO;
import antigypt.springframework.domain.Employee;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);


    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);


    List<Employee> listEmployeeDTOToListEmployee(List<EmployeeDTO> employees);
    List<EmployeeDTO> listEmployeeToListEmployeeDTO(List<Employee> employees);
}

