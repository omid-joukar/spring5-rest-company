package antigypt.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by omid on 11/18/2020.
 */
@Entity
@Table(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "paymentDate")
    private LocalDate paymentDate;
    @Column(name = "paymentTime")
    private LocalTime paymentTime;
    @Column(name = "rebate")
    private Double rebate;

    @Enumerated
    private PaymentType paymentType;

    @OneToOne(mappedBy = "payment",cascade = CascadeType.ALL)
    private Orders orders;


}
