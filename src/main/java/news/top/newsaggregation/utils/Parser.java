package news.top.newsaggregation.utils;

import news.top.newsaggregation.models.Article;
import news.top.newsaggregation.utils.models.ParserTemplate;

public interface Parser {
    Article parseSite(Article article, ParserTemplate parserTemplate);
}
