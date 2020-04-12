package news.top.newsaggregation.models;

import com.rometools.rome.feed.rss.Item;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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
    private String content;
    private Date pubDate;

    public static Article fromRSSItem(Item item){
        Article article = new Article();
        article.setAuthor(Optional.ofNullable(item.getAuthor()).orElse(""));
        article.setCategoryString(Optional.ofNullable(item.getCategories().get(0).getValue()).orElse(""));
        article.setLink(Optional.ofNullable(item.getLink()).orElse(""));
        article.setDescription(Optional.ofNullable(item.getDescription().getValue()).orElse(""));
        article.setTitle(Optional.ofNullable(item.getTitle()).orElse(""));
        article.setContent(Optional.ofNullable(item.getContent().getValue()).orElse(""));
        article.setPubDate(Optional.ofNullable(item.getPubDate()).orElse(new Date()));
        return article;
    }


}
