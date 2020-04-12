package news.top.newsaggregation.utils;

import kong.unirest.Unirest;
import news.top.newsaggregation.models.Article;
import news.top.newsaggregation.utils.models.ParserTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParserImpl implements Parser {
    @Override
    public Article parseSite(Article article, ParserTemplate parserTemplate) {
        Document document = Jsoup.parse(Unirest.get(article.getLink()).asString().getBody());
        //extract text and clean from HTML elements
        Elements textOfDocument = document.select(parserTemplate.getTextXpath());
        // Extract title
        String title = document.select(parserTemplate.getTitleXpath()).first().text();
        // Extract Images LINKS by correct Query
        Elements images = document.select(parserTemplate.getImagesXpath());
        // Extract Video in order of ordinary extraction
        Elements linksToVideo = document.select(parserTemplate.getVideoXpath());
        return article;
    }
}
