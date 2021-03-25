package antigypt.springframework.api.v1.mapper;


import antigypt.springframework.api.v1.model.OrderDTO;

import antigypt.springframework.domain.Order;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderDTOToOrder(OrderDTO orderDTO);
    OrderDTO orderToOrderDTO(Order order);


}
