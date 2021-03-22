package antigypt.springframework.api.v1.model;

import antigypt.springframework.domain.Order;
import antigypt.springframework.domain.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {


    private Double amount;
    private LocalDate paymentDate;
    private LocalTime paymentTime;
    private Double rebate;
    private PaymentType paymentType;
    private OrderDTO order;
    private String paymentUrl;
}
