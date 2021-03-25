package antigypt.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "birthDate")
    private LocalDate birthDate;
    @Column(name = "creationDate")
    private LocalDate creationDate;
    @Column(name = "Email")
    private String Email;
    @Column(name = "homePhone")
    private String homePhone;
    @Column(name = "mobilePhone")
    private String mobilePhone;
    @Column(name = "customerNumber")
    private Long customerNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "addressLine", column = @Column(name = "addressLine")),
            @AttributeOverride( name = "city", column = @Column(name = "city")),
            @AttributeOverride( name = "region", column = @Column(name = "region")),
            @AttributeOverride( name = "postalCode", column = @Column(name = "postalCode")),
            @AttributeOverride( name = "country", column = @Column(name = "country"))
    })
    private Address address;



    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "buyTrolleyId")
    private BuyTrolley buyTrolley;






   //@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
   //private List<ComplainSuggestion> complainSuggestionList = new ArrayList<>();



}
