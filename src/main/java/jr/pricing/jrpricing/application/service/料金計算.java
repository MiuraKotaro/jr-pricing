package jr.pricing.jrpricing.application.service;

import jr.pricing.jrpricing.domain.model.fare.料金;
import jr.pricing.jrpricing.domain.model.申し込みフォーム;

public class 料金計算 {
    public 料金 料金計算(申し込みフォーム _申し込みフォーム) {
        料金 合計金額 = new 料金(_申し込みフォーム.get降車駅().get_運賃().get円());
    }
}
