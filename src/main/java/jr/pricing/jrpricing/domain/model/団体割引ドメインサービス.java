package jr.pricing.jrpricing.domain.model;

import jr.pricing.jrpricing.domain.model.fare.割引率;
import jr.pricing.jrpricing.domain.model.fare.料金;

public class 団体割引ドメインサービス {
    public static 料金 団体時期割引計算(料金 一人分片道運賃, 利用日 _利用日, 人数 大人人数, 人数 子供人数) {
        final 人数 団体割引最少人数 = new 人数(8);
        final 割引率 団体年末年始割引率 = new 割引率(10);
        final 割引率 団体割引率 = new 割引率(15);
        final 人数 全体人数 = 大人人数.足す(子供人数);

        if (団体割引最少人数.大なり(全体人数)) {
            return 一人分片道運賃;
        }
        if (_利用日.isAfter(12, 20) || _利用日.isBefore(1, 11)) {
            return 一人分片道運賃.割引(団体年末年始割引率);
        } else {
            return 一人分片道運賃.割引(団体割引率);
        }
    }
}
