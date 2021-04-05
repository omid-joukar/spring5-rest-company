package antigypt.springframework.controllers.api.v1;

import antigypt.springframework.Services.SubscribeService;
import antigypt.springframework.api.v1.model.SubscribeDTO;
import antigypt.springframework.api.v1.model.SubscribeListDTO;
import antigypt.springframework.exceptions.DuplicateException;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscribes")
@CrossOrigin(origins = "*")
public class SubscribeController {
    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @SneakyThrows
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public SubscribeDTO createNewSubscribe(@RequestBody SubscribeDTO subscribeDTO, BindingResult result){
        if (!subscribeService.isNew(subscribeDTO)){
            throw new DuplicateException();
        }
        return subscribeService.createNewSubscribe(subscribeDTO);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SubscribeListDTO getAllSubscribes(){
        return new SubscribeListDTO(subscribeService.getAllSubscribes());
    }



    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public SubscribeDTO getSubscribeByEmail(@PathVariable String email){
        return subscribeService.findSubscribeByEmail(email);
    }


}
