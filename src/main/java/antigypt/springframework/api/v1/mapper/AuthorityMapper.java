package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.AuthorityDTO;
import antigypt.springframework.domain.security.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorityMapper {

    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);
    AuthorityDTO authorityToAuthorityDTO(Authority authority);
    Authority authorityDTOToAuthority(AuthorityDTO authorityDTO);
}
