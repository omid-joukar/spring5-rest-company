package antigypt.springframework.controllers.api.v1.security;




import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class UserLoginController {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void produceTOken()
    {

    }
}
