package antigypt.springframework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideDTO {

    private String cover;
    private String topic;
    private String title;
    private String slideUrl;

}
