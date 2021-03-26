package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.OrderDTO;

import antigypt.springframework.domain.Orders;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Orders orderDTOToOrder(OrderDTO orderDTO);
    OrderDTO orderToOrderDTO(Orders orders);


}
