package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.OrderDTO;
import antigypt.springframework.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    @Mappings({
            @Mapping(source = "department" , target = "department"),
            @Mapping(source = "department.productList.products", target = "department.productList"),
            @Mapping(source = "deliverEmployee" , target = "deliverEmployee"),
            @Mapping(source = "deliverEmployee.department.productList.products" , target = "deliverEmployee.department.productList"),
            @Mapping(source = "customer" , target = "customer"),
    })
    Order orderDTOToOrder(OrderDTO orderDTO);
    @Mappings({
            @Mapping(source = "department" , target = "department"),
            @Mapping(source = "department.productList", target = "department.productList.products"),
            @Mapping(source = "deliverEmployee" , target = "deliverEmployee"),
            @Mapping(source = "deliverEmployee.department.productList" , target = "deliverEmployee.department.productList.products"),
            @Mapping(source = "customer" , target = "customer"),
    })
    OrderDTO orderToOrderDTO(Order order);
}
