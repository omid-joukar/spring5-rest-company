package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.AddressDTO;
import antigypt.springframework.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    Address addressDTOToAddress(AddressDTO addressDTO);
    AddressDTO adressToAddressDTO(Address address);
}
