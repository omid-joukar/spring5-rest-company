package antigypt.springframework.domain;



import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "birthDate")
    private LocalDate birthDate;
    @Column(name = "hireDate")
    private LocalDate hireDate;
    @Column(name = "email")
    private String email;
    @Column(name = "homePhone")
    private String homePhone;
    @Column(name = "mobilePhone")
    private String mobilePhone;
    @Column(name = "detail")
    private String detail;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "addressLine", column = @Column(name = "addressLine")),
            @AttributeOverride( name = "city", column = @Column(name = "city")),
            @AttributeOverride( name = "region", column = @Column(name = "region")),
            @AttributeOverride( name = "postalCode", column = @Column(name = "postalCode")),
            @AttributeOverride( name = "country", column = @Column(name = "country"))
    })
    private Address address;

    @Enumerated(EnumType.STRING)
    private Title title;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Lob
    @Column(name = "photo")
    private Byte[] photo;

    @OneToMany(mappedBy = "reportsTo", cascade = CascadeType.ALL)
    private List<Employee> employeeList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "reportsTo")
    private Employee reportsTo;
    @ManyToOne
    @JoinColumn(name = "jobTitleId")
    private JobTitle jobTitle;
    @OneToOne
    @JoinColumn(name = "employeeSalaryId")
    private EmployeeSalary employeeSalary;
    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
    @OneToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "deliverEmployee",cascade = CascadeType.ALL)
    List<Orders> ordersList = new ArrayList<>();
   // @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL )
   // private List<EmployeeShift> employeeShiftList = new ArrayList<>();
   // @OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
   // private List<ComplainSuggestion> complainSuggestionList = new ArrayList<>();
   // @OneToMany(mappedBy = "deliverEmployee",cascade = CascadeType.ALL)
   // private List<Order> orderList = new ArrayList<>();

}
