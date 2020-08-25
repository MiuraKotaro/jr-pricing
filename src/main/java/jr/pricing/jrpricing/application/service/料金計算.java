package jr.pricing.jrpricing.application.service;

import jr.pricing.jrpricing.domain.model.*;
import jr.pricing.jrpricing.domain.model.fare.料金;

public class 料金計算 {
    public 料金 料金計算(駅 降車駅, 利用日 _利用日, 片道往復 _片道往復, 人数 十八才以上, 人数 十八才未満, 特急利用 _特急利用) {
        //　大人一人分計算
        料金 大人一人分;
        //　子供一人分計算
        料金 子供一人分;
        // 合計
        料金 合計金額 = (大人一人分.かける(十八才以上)).足す(子供一人分.かける(十八才未満));

        return 合計金額;
    }
}
