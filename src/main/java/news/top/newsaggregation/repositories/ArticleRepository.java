package news.top.newsaggregation.repositories;

import news.top.newsaggregation.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource
public interface ArticleRepository  extends JpaRepository<Article, Long> {
}
