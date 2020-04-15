package news.top.newsaggregation.cronjobs;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import news.top.newsaggregation.models.Article;
import news.top.newsaggregation.repositories.ArticleRepository;
import news.top.newsaggregation.utils.ParserImpl;
import news.top.newsaggregation.utils.models.ParserTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ScheduledTasks {
    private Map<String, List<String>> rssFeeds = new HashMap<>();
    private Map<String, ParserTemplate> parserTempates = new HashMap<>();
    @Autowired
    private ArticleRepository repository;
    @Autowired
    private ParserImpl parser;

    @Scheduled(cron = "* */15 * * * *")
    public void doEvery30Minute() {
        try {
            RssReader reader = new RssReader();
            for (String newsProvider :
                    rssFeeds.keySet()) {
                for (String rssFeedLink: rssFeeds.get(newsProvider)
                     ) {
                    Stream<Item> rssFeed = reader.read(rssFeedLink);
                    List<Article> newsFromFeed = rssFeed.map(Article::fromRSSItem).map(article -> {
                        ParserTemplate parserTemplate = parserTempates.get(newsProvider);
                        return parser.parseSite(article, parserTemplate);
                    }).collect(Collectors.toList());
                    repository.saveAll(newsFromFeed);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @PostConstruct
    private void initializeRSSFeeds(){
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("feeds.yaml");
        rssFeeds = yaml.load(inputStream);
    }

}
