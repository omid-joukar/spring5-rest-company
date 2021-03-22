package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.PaymentDTO;
import antigypt.springframework.domain.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mappings({
            @Mapping(source = "paymentType" , target = "paymentType"),
            @Mapping(source = "order" , target = "order"),
            @Mapping(source = "order.deliverEmployee.department.productList.products" , target = "order.deliverEmployee.department.productList"),
            @Mapping(source = "order.department.productList.products", target = "order.department.productList"),
    })
    Payment paymentDTOToPayment(PaymentDTO paymentDTO);
    @Mappings({
            @Mapping(source = "paymentType" , target = "paymentType"),
            @Mapping(source = "order.department.productList", target = "order.department.productList.products"),
            @Mapping(source = "order.deliverEmployee.department.productList" , target = "order.deliverEmployee.department.productList.products"),
            @Mapping(source = "order" , target = "order"),
    })
    PaymentDTO paymentToPaymentDTO(Payment payment);

}
