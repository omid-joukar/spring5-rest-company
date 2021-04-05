package antigypt.springframework.Services;



import antigypt.springframework.api.v1.model.SubscribeDTO;

import java.util.List;

public interface SubscribeService {
    SubscribeDTO createNewSubscribe(SubscribeDTO subscribeDTO);
    SubscribeDTO findSubscribeById(Long id);
    List<SubscribeDTO> getAllSubscribes();
    void deleteSubscribeById(Long id);
    SubscribeDTO findSubscribeByEmail(String email);
    boolean isNew(SubscribeDTO subscribeDTO);
}
