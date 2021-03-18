package antigypt.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="Order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(name = "orderDate")
    private LocalDate orderDate;
    @Column(name = "orderTime")
    private LocalTime orderTime;

    @Column(name = "requiredDate")
    private LocalDate requiredDate;
    @Column(name = "shippedDate")
    private LocalDate shippedDate;
    @Column(name = "shippedTime")
    private LocalTime shippedTime;
    @Column(name = "comments")
    private String comments;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;



    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "deliverEmployeeId")
    private Employee deliverEmployee;
    @ManyToOne
    @JoinColumn(name ="customerId")
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

   // @OneToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name = "groceriesReport")
   // private BuyTrolley groceriesReport;
   // @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
   // private ComplainSuggestion complainSuggestion;
   // @ManyToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name = "alertId")
   // private Alert alert;

}
