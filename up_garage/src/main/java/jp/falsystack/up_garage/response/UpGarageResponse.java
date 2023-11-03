package jp.falsystack.up_garage.response;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * {
 *    "id":"xxxxxxxxxx",
 *    "actives_at":1579938264219,
 *    "called_at":1579938263720,
 *    "total_diff":0
 *     "result": {
 *      "attempts": 呼び出し回数,
 *      "url": 応募キーワードを含むページのURL (失敗時はnull)
 *    }
 * }
 */
@Getter
@ToString
@NoArgsConstructor
public class UpGarageResponse {

  private String id;
  private Long actives_at;
  private Long called_at;
  private Integer total_diff;
  private Map<String, String> result = new HashMap<>();

  @Builder
  public UpGarageResponse(String id, Long activesAt, Long calledAt, Integer totalDiff, Map<String, String> result) {
    this.id = id;
    this.actives_at = activesAt;
    this.called_at = calledAt;
    this.total_diff = totalDiff;
    this.result = result;
  }
}
