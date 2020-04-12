package news.top.newsaggregation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.MediaType;

import javax.persistence.*;

@Data
@Entity
public class Media {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Article article;
    private MediaType mediaType;
    private String link;
}
