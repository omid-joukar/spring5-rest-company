package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.PostMapper;
import antigypt.springframework.api.v1.model.PostDTO;
import antigypt.springframework.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream().map(post -> {
                    PostDTO postDTO = postMapper.postToPostDTO(post);
                    postDTO.setPostUrl("/api/v1/posts/"+ post.getPostId());
                    return postDTO;
                }).collect(Collectors.toList());
    }
}
