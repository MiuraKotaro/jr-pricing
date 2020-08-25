package jr.pricing.jrpricing.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class 申し込みフォーム {
    private final 駅 乗車駅;
    private final 駅 降車駅;
    private final 利用日 _利用日;
    private final 片道往復 _片道往復;
    private final 人数 十八才以上;
    private final 人数 十八才未満;
    private final 特急利用 _特急利用;
}
