package antigypt.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by omid on 11/20/2020.
 */

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "productName")
    private String productName;
    @Column(name = "expirationDate")
    private LocalDate expirationDate;
    @Column(name = "produceDate")
    private LocalDate produceDate;
    @Column(name = "price")
    private Double price;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "productCount")
    private Long productCount;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "detail")
    private String detail;
    @Lob
    @Column(name = "serialNumber")
    private Byte[] serialNumber;
    @Lob
    @Column(name = "image")
    private Byte[] image;

    @ManyToOne
    @JoinColumn(name = "productTypeId")
    private ProductType productType;

    @ManyToMany
    @JoinTable(name = "department_product" ,
            joinColumns = @JoinColumn(name = "product_Id"),
            inverseJoinColumns = @JoinColumn(name = "department_Id"))
    List<Department> departmentList = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ProductBuyTrolley",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "buyTrolley_id"))
    private List<BuyTrolley> buyTroleyList = new ArrayList<>();

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "producersId")
    //private Producers producers;

}
