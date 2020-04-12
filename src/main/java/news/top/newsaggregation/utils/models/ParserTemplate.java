package news.top.newsaggregation.utils.models;

import lombok.Data;

@Data
public class ParserTemplate {
    private String newsProvider;
    private String titleXpath;
    private String textXpath;
    private String imagesXpath;
    private String videoXpath;
}
