package antigypt.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BuyTrolley")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyTrolley {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyTrolleyId;

    @Column(name = "productCount")
    private Long productCount;


    @OneToOne(mappedBy = "buyTrolley",cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToMany
    @JoinTable(name = "ProductBuyTrolley",
    joinColumns = @JoinColumn(name = "buyTrolley_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList = new ArrayList<>();

}
