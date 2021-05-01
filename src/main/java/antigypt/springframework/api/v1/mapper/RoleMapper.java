package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.RoleDTO;
import antigypt.springframework.domain.security.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role roleDTOToRole(RoleDTO roleDTO);
    RoleDTO roleToRoleDTO(Role role);
}
