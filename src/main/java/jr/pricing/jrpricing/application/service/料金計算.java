package jr.pricing.jrpricing.application.service;

import jr.pricing.jrpricing.domain.model.*;
import jr.pricing.jrpricing.domain.model.fare.料金;

public class 料金計算 {
    public 料金 料金計算(駅 降車駅, 利用日 _利用日, 片道往復 _片道往復, 人数 _人数, 特急利用 _特急利用) {
        料金 合計金額 = 降車駅.get_運賃().get_料金();
    }
}
