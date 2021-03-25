package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.PaymentDTO;
import antigypt.springframework.domain.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);



    Payment paymentDTOToPayment(PaymentDTO paymentDTO);
    PaymentDTO paymentToPaymentDTO(Payment payment);


}
