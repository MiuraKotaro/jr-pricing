package jr.pricing.jrpricing.domain.service;

import jr.pricing.jrpricing.domain.model.人数;
import lombok.Getter;

@Getter
public class 無料人数ドメインサービス {
    private 人数 大人無料人数, 子供無料人数;

    public 無料人数ドメインサービス(人数 大人, 人数 子供) {

        人数 無料人数 = get無料人数(大人.足す(子供));
        if (大人.compareTo(無料人数) >= 0) {
            大人無料人数 = 無料人数;
            子供無料人数 = new 人数(0);
        } else {
            無料人数 = 無料人数.引く(大人);
            大人無料人数 = 大人;
            子供無料人数 = 無料人数;
        }
    }

    private 人数 get無料人数(人数 全体人数) {
        final 人数 無料化最少人数 = new 人数(31);
        return 全体人数.compareTo(無料化最少人数) >= 0 ? new 人数((全体人数.getValue() + 49) / 50) : new 人数(0);
    }
}
