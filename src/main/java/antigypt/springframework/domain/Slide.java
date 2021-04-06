package antigypt.springframework.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Slide")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slideId;
    @Column(name = "cover")
    private String cover;
    @Column(name = "topic")
    private String topic;
    @Column(name = "title")
    private String title;



}
