package antigypt.springframework.controllers.api.v1;

import antigypt.springframework.Services.PostService;
import antigypt.springframework.api.v1.model.PostListDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin(origins = "*")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public PostListDTO getAllPosts(){
        return new PostListDTO(postService.getAllPosts());
    }


}
