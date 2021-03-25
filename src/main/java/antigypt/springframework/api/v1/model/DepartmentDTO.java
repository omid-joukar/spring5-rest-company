package antigypt.springframework.api.v1.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    @Min(6)
    private String phoneNumber;
    @Email
    private String email;
    private String detail;
    @NotBlank
    AddressDTO address;
    @JsonProperty("depratment_url")
    private String DepartmetnUrl;

   // private ProductListDTO productList;

}
