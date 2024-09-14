package jp.co.falsystack.umicroservice.runner.impl;

import jakarta.annotation.PreDestroy;
import jp.co.falsystack.umicroservice.config.TwitterToKafkaServiceConfigData;
import jp.co.falsystack.umicroservice.listener.TwitterKafkaStatusListener;
import jp.co.falsystack.umicroservice.runner.StreamRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.v1.FilterQuery;
import twitter4j.v1.TwitterStream;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterKafkaStreamRunner implements StreamRunner {

    private final TwitterKafkaStatusListener twitterKafkaStatusListener;
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private TwitterStream twitterStream;

    @Override
    public void start() throws TwitterException {
        twitterStream = Twitter.newBuilder().listener(twitterKafkaStatusListener).build().v1().stream();
        var keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        twitterStream.filter(FilterQuery.ofTrack(keywords));
        log.info("Started filtering twitter stream for keywords {}", Arrays.toString(keywords));
    }

    @PreDestroy
    public void shutdown() {
        if (twitterStream != null) {
            log.info("Closing twitter stream!");
            twitterStream.shutdown();
        }
    }
}
