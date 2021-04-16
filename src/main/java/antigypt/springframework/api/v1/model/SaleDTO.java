package antigypt.springframework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private String cover;
    private String topic;
    private String content;
    private String percent;
    private String saleUrl;
}
