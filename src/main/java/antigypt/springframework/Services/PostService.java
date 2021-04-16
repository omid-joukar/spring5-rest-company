package antigypt.springframework.Services;

import antigypt.springframework.api.v1.model.PostDTO;
import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();
}
