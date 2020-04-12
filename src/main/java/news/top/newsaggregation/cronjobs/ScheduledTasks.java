package news.top.newsaggregation.cronjobs;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import news.top.newsaggregation.repositories.ArticleRepository;
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
import java.util.stream.Stream;

@Component
public class ScheduledTasks {
    private Map<String, List<String>> rssFeeds = new HashMap<>();
    @Autowired
    private ArticleRepository repository;

    @Scheduled(cron = "*/30 * * * *")
    public void doEvery30Minute() {



        try {
            for (String newsProvider :
                    rssFeeds.keySet()) {
                for (String rssFeedLink: rssFeeds.get(newsProvider)
                     ) {
                    RssReader reader = new RssReader();
                    Stream<Item> rssFeed = reader.read(rssFeedLink);
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
