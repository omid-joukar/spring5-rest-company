package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.CustomerDTO;
import antigypt.springframework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    @Mappings({@Mapping(
            source = "addressLine",
            target = "address.addressLine"
    ), @Mapping(
            source = "country",
            target = "address.country"
    ), @Mapping(
            source = "city",
            target = "address.city"
    ), @Mapping(
            source = "region",
            target = "address.region"
    ), @Mapping(
            source = "postalCode",
            target = "address.postalCode"
    )})
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
    @Mappings({@Mapping(
            source = "address.addressLine",
            target = "addressLine"
    ), @Mapping(
            source = "address.country",
            target = "country"
    ), @Mapping(
            source = "address.city",
            target = "city"
    ), @Mapping(
            source = "address.region",
            target = "region"
    ), @Mapping(
            source = "address.postalCode",
            target = "postalCode"
    )})
    CustomerDTO customerToCustomerDTO(Customer customer);
}
