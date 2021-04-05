package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.SubscribeMapper;
import antigypt.springframework.api.v1.model.SubscribeDTO;
import antigypt.springframework.domain.Subscribe;
import antigypt.springframework.exceptions.ResourceNotFoundException;
import antigypt.springframework.repositories.SubscribeRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@Slf4j
public class SubscribeServiceImpl implements SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final SubscribeMapper subscribeMapper;

    public SubscribeServiceImpl(SubscribeRepository subscribeRepository, SubscribeMapper subscribeMapper) {
        this.subscribeRepository = subscribeRepository;
        this.subscribeMapper = subscribeMapper;
    }

    @Override
    public SubscribeDTO createNewSubscribe(SubscribeDTO subscribeDTO) {
        Subscribe subscribe = subscribeRepository.save(subscribeMapper.subscribeDTOToSubscribe(subscribeDTO));
        SubscribeDTO returnedDTO = subscribeMapper.subscribeToSubscribeDTO(subscribe);
        returnedDTO.setSubscribeUrl("/api/v1/subscribes/"+subscribe.getSubscribeId());
        return returnedDTO;
    }

    @SneakyThrows
    @Override
    public SubscribeDTO findSubscribeById(Long id) {
        Optional<Subscribe> foundedSubscribeOptional = subscribeRepository.findById(id);
        if (!foundedSubscribeOptional.isPresent()){
            throw new ResourceNotFoundException("invalid id : " + id);
        }
        Subscribe foundedSubscribe = foundedSubscribeOptional.get();
        SubscribeDTO returnedDTO = subscribeMapper.subscribeToSubscribeDTO(foundedSubscribe);
        returnedDTO.setSubscribeUrl("/api/v1/subscribes/"+foundedSubscribe.getSubscribeId());
        return returnedDTO;
    }

    @Override
    public List<SubscribeDTO> getAllSubscribes() {
        List<Subscribe> subscribeList = subscribeRepository.findAll();
        List<SubscribeDTO> returnedDTOList = subscribeList
                .stream()
                .map(subscribe -> {
                    SubscribeDTO subscribeDTO = subscribeMapper.subscribeToSubscribeDTO(subscribe);
                    subscribeDTO.setSubscribeUrl("/api/v1/subscribes/"+subscribe.getSubscribeId());
                    return subscribeDTO;
                }).collect(Collectors.toList());
        return returnedDTOList;
    }

    @Override
    public void deleteSubscribeById(Long id) {
        subscribeRepository.deleteById(id);
    }

    @SneakyThrows
    @Override
    public SubscribeDTO findSubscribeByEmail(String email) {
        Optional<Subscribe> foundedSubscribeOptional = subscribeRepository.findByEmail(email);
        if (!foundedSubscribeOptional.isPresent()){
            throw new ResourceNotFoundException();
        }else {
            Subscribe foundedSubscribe = foundedSubscribeOptional.get();
            SubscribeDTO returnedDTO = subscribeMapper.subscribeToSubscribeDTO(foundedSubscribe);
            returnedDTO.setSubscribeUrl("/api/v1/subscribes/" + foundedSubscribe.getSubscribeId());
            return returnedDTO;
        }
    }

    @Override
    public boolean isNew(SubscribeDTO subscribeDTO) {
        boolean isObjectNew = true;
        for(Subscribe subscribe : subscribeRepository.findAll() ){
            if (subscribe.getEmail().trim().equals(subscribeDTO.getEmail())){
                isObjectNew = false;
            }
        }
        return isObjectNew;
    }
}
