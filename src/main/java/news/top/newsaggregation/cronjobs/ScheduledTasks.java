package news.top.newsaggregation.cronjobs;

import lombok.AllArgsConstructor;
import news.top.newsaggregation.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScheduledTasks {
    private Map<String, List<String>> rssFeeds = new HashMap<>();
    @Autowired
    private ArticleRepository repository;

    @Scheduled(cron = "*/30 * * * *")
    public void doEvery30Minute() {



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
