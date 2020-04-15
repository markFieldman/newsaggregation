package news.top.newsaggregation.models;

import com.apptastic.rssreader.Item;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
public class Article{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private List<Media> mediaList;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    private String categoryString;
    private String title;
    private String description;
    private String link;
    private String author;
    private String guid;
    private String isPermaLink;
    private LocalDate pubDate;

    public void addToMediaList(List<Media> mediaList){
        if(this.mediaList!=null) {
            this.mediaList.addAll(mediaList);
        } else {
            this.mediaList = mediaList;
        }
    }

    public static Article fromRSSItem(Item item){
        Article article = new Article();
        article.setAuthor(item.getAuthor().orElse(""));
        article.setCategoryString(item.getCategory().orElse(""));
        article.setLink(item.getLink().orElse(""));
        article.setDescription(item.getDescription().orElse(""));
        article.setTitle(item.getTitle().orElse(""));
        article.setPubDate(LocalDate.parse(item.getPubDate().orElse("")));
        return article;
    }


}
