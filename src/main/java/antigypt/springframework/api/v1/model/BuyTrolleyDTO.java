package antigypt.springframework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyTrolleyDTO {
    private String buyTrolleyUrl;
    private Long productCount;
    //private CustomerDTO customer;

}
