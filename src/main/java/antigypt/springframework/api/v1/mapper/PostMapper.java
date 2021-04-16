package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.PostDTO;
import antigypt.springframework.domain.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    Post postDTOToPost(PostDTO postDTO);
    PostDTO postToPostDTO(Post post);

}
