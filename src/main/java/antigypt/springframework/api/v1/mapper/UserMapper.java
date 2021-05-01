package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.UserDTO;
import antigypt.springframework.domain.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDTOToUser(UserDTO userDTO);
    UserDTO userToUserDTO(User user);
}
