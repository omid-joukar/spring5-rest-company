package antigypt.springframework.api.v1.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentDTO {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String title;
    private String gender;
    @Past
    private String birthDate;
    private String applicationDate;
    @Email
    private String email;
    @NotBlank
    AddressDTO address;
    @Min(6)
    @Max(8)
    private String homePhone;
    @Min(8)
    @Max(14)
    private String mobilePhone;
    private Double desiredSalary;
    private Byte[] photo;
    private String detail;
    private Byte[] cv;
    @JsonProperty("recruitment_url")
    private String recruitmentUrl;

}
