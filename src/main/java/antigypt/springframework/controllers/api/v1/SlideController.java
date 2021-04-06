package antigypt.springframework.controllers.api.v1;


import antigypt.springframework.Services.SlideService;
import antigypt.springframework.api.v1.model.SlideListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/slides")
@CrossOrigin(origins = "*")
public class SlideController {

    private final SlideService slideService;

    public SlideController(SlideService slideService) {
        this.slideService = slideService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SlideListDTO getAllSlides(){
        return new SlideListDTO(slideService.getAllSlides());
    }
}
