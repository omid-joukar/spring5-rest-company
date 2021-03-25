package antigypt.springframework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Past
    private String birthDate;
    private String creationDate;
    @Email
    private String Email;
    @Min(8)
    @Max(14)
    private String homePhone;
    @Min(8)
    @Max(14)
    private String mobilePhone;
    @NotBlank
    private Long customerNumber;

    @NotBlank
    AddressDTO address;
    private BuyTrolleyDTO buyTrolley;
    private String customerUrl;

}
