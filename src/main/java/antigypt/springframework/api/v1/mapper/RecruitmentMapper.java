package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.RecruitmentDTO;
import antigypt.springframework.domain.Recruitment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;



@Mapper
public interface RecruitmentMapper {
    RecruitmentMapper INSTANCE = Mappers.getMapper(RecruitmentMapper.class);



    RecruitmentDTO recruitmentToRecruitmentDTO(Recruitment recruitment);
    Recruitment recruitmnetDTOToRecruitment(RecruitmentDTO recruitmentDTO);




}
