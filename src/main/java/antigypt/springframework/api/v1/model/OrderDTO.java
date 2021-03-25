package antigypt.springframework.api.v1.model;

import antigypt.springframework.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String orderDate;
    private String orderTime;
    private String requiredDate;
    private String shippedDate;
    private String shippedTime;
    private String comments;
    private OrderStatus orderStatus;
    private DepartmentDTO department;
    private EmployeeDTO deliverEmployee;
    private CustomerDTO customer;
    private String OrderUrl;
}
