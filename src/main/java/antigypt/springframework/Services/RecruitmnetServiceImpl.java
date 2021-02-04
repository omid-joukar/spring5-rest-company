package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.RecruitmentMapper;
import antigypt.springframework.api.v1.model.RecruitmentDTO;
import antigypt.springframework.controllers.api.v1.RecruitmentController;
import antigypt.springframework.domain.Recruitment;
import antigypt.springframework.repositories.RecruitmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RecruitmnetServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentMapper recruitmentMapper;

    public RecruitmnetServiceImpl(RecruitmentRepository recruitmentRepository, RecruitmentMapper recruitmentMapper) {
        this.recruitmentRepository = recruitmentRepository;
        this.recruitmentMapper = recruitmentMapper;
    }
    @Override
    public RecruitmentDTO findRecruitmentById(Long id) {
        Optional<Recruitment> recruitmentOptional = recruitmentRepository.findById(id);
        if (!recruitmentOptional.isPresent()){

        }
        Recruitment recruitment = recruitmentOptional.get();
        RecruitmentDTO returnedDTO = recruitmentMapper.recruitmentToRecruitmentDTO(recruitment);
        returnedDTO.setRecruitmentUrl(RecruitmentController.BASE_URL+"/"+recruitment.getRecruitmentId());
        return returnedDTO;
    }

    @Override
    public RecruitmentDTO createNewRecruitmnet(RecruitmentDTO recruitmentDTO) {

        Recruitment toBeSaved = recruitmentMapper.recruitmnetDTOToRecruitment(recruitmentDTO);
        Recruitment savedRecruitment = recruitmentRepository.save(toBeSaved);
        RecruitmentDTO returnedDTO = recruitmentMapper.recruitmentToRecruitmentDTO(savedRecruitment);
        recruitmentDTO.setRecruitmentUrl(RecruitmentController.BASE_URL+"/" +savedRecruitment.getRecruitmentId());
        return recruitmentDTO ;
    }


}
