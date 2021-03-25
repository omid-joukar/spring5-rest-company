package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.JobTitleDTO;
import antigypt.springframework.domain.JobTitle;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;


@Mapper
public interface JobTitleMapper {
    JobTitleMapper INSTANCE = (JobTitleMapper) Mappers.getMapper(JobTitleMapper.class);


    JobTitleDTO jobTitleToJobTitleDTO(JobTitle jobTitle);
    JobTitle jobTitleDTOToJobTitle(JobTitleDTO jobTitleDTO);

    //List<EmployeeDTO> listEmployeeToListEmployeeDTO(List<Employee> employees);

    //List<Employee> listEmployeeDTOToListEmployee(List<EmployeeDTO> employees);
}
