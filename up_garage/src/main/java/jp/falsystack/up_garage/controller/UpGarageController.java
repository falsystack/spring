package jp.falsystack.up_garage.controller;

import jp.falsystack.up_garage.response.UpGarageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

// /answers/16e59cd8-8e424ad5-4fb04243-f16e2a08.html
// /answers/16e59cd8-8e424ad5-4fb04243-f16e2a08.html
// /answers/16e59cd8-8e424ad5-4fb04243-f16e2a08.html

// Algorithm-Rank2-438964702

@Slf4j
@RestController
@RequiredArgsConstructor
public class UpGarageController {

  public static final int AVG_NUMBER = 10;
  public static final int LONG_DELAY_TIME = 200;

  private final WebClient webClient;
  private long requestDelayTime = 0L;
  private long sum = 0L;
  private int count = 0;
  private long avg = 0;

  @GetMapping("/")
  public void requestStart() {

    var startTimeMill = System.currentTimeMillis();
    var firsChallengeResponse = webClient
        .post()
        .uri("?nickname=test1")
        .retrieve()
        .bodyToMono(UpGarageResponse.class)
        .block();

    log.info("first challenge response = {}", firsChallengeResponse);

    // Delay Timeを取る
    var delayTime = firsChallengeResponse.getCalled_at() - startTimeMill;

    // cold start時のdelay timeが長すぎると捨てる
    if (delayTime > LONG_DELAY_TIME) {
      requestStart();
    } else {
      requestDelayTime = delayTime;
    }

    // レスポンスにあるidをヘッダに書き込む
    var mutatedClient = webClient
        .mutate()
        .defaultHeader("X-Challenge-Id", firsChallengeResponse.getId())
        .build();

    // challengeスタート
    fetchChallenge(firsChallengeResponse, mutatedClient, delayTime);
  }

  private void fetchChallenge(UpGarageResponse challengeResponse, WebClient mutatedClient,
      long delayTime) {
    var activesTime = challengeResponse.getActives_at();
    // delay時間分、開始時間を前に引く
    var specificTime = activesTime - delayTime;

    long currentTime;
    while (true) {
      currentTime = System.currentTimeMillis();
      if (specificTime == currentTime) {
        mutatedClient.put()
            .retrieve()
            .bodyToMono(UpGarageResponse.class)
            .subscribe(resp -> {
              log.info("response = {} ", resp);

              // challenge終了
              if (!resp.getResult().isEmpty()) {
                var result = resp.getResult();
                var attempts = result.get("attempts");
                var url = result.get("url");
                log.info("attempts = {}, url = {}", attempts, url);
                return;
              }

              // 処理時刻の差分
              var diffRequestTime = resp.getCalled_at() - activesTime;

              // 補正値として平均を求める
              if (count < AVG_NUMBER) {
                sum += diffRequestTime;
                count++;
              }
              if (count == AVG_NUMBER) {
                avg = sum / AVG_NUMBER;
              }

              var eachDiff = (diffRequestTime) / 2;
              var correctionValue = (eachDiff > 0 && eachDiff < avg ? eachDiff : avg);

              fetchChallenge(resp, mutatedClient, requestDelayTime + correctionValue);
            });
        return;
      }
    }
  }

}
