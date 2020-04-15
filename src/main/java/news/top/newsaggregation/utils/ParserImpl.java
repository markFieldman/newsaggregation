package news.top.newsaggregation.utils;

import kong.unirest.Unirest;
import news.top.newsaggregation.models.Article;
import news.top.newsaggregation.models.Media;
import news.top.newsaggregation.utils.models.ParserTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ParserImpl implements Parser {
    @Override
    public  Article parseSite(Article article, ParserTemplate parserTemplate) {
        Document document = Jsoup.parse(Unirest.get(article.getLink()).asString().getBody());
        //extract text and clean from HTML elements
        Elements textOfDocument = document.select(parserTemplate.getTextXpath());
        article.setDescription(textOfDocument.text());
        // Extract title
        // Extract Images LINKS by correct Query
        Elements images = document.select(parserTemplate.getImagesXpath());
        article.setMediaList(images.stream().map(element -> {
            Media media = new Media();
            media.setLink(element.attr("href"));
            media.setArticle(article);
            media.setMediaType(MediaType.IMAGE_JPEG);
            return media;
        }).collect(Collectors.toList()));
        // Extract Video in order of ordinary extraction
        Elements linksToVideo = document.select(parserTemplate.getVideoXpath());
        article.addToMediaList(images.stream().map(element -> {
            Media media = new Media();
            media.setLink(element.attr("href"));
            media.setArticle(article);
            media.setMediaType(MediaType.IMAGE_JPEG);
            return media;
        }).collect(Collectors.toList()));
        return article;
    }
}
