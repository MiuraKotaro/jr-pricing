package jr.pricing.jrpricing.application.service;

import jr.pricing.jrpricing.domain.model.*;
import jr.pricing.jrpricing.domain.model.fare.割引率;
import jr.pricing.jrpricing.domain.model.fare.料金;

import java.time.LocalDate;

import static jr.pricing.jrpricing.domain.model.特急利用.*;

public class 料金計算 {
    public 料金 料金計算(駅 降車駅, 利用日 _利用日, 片道往復 _片道往復, 人数 十八才以上, 人数 十八才未満, 特急利用 _特急利用) {
        料金 大人一人分 = 大人一人分片道計算(降車駅, _特急利用);
        料金 子供一人分 = 子供一人分片道計算(降車駅, _特急利用);

        大人一人分 = 割引計算(大人一人分, 降車駅.get_距離(), _利用日, 十八才以上, 十八才未満);
        子供一人分 = 割引計算(子供一人分, 降車駅.get_距離(), _利用日, 十八才以上, 十八才未満);
        // 合計
        料金 片道合計金額 = (大人一人分.かける(十八才以上)).足す(子供一人分.かける(十八才未満));

        final 人数 無料人数 = 無料人数計算(十八才以上, 十八才未満);
        片道合計金額 = 片道合計金額.引く(大人一人分.かける(無料人数));

        return _片道往復.is片道() ? 片道合計金額 : 片道合計金額.かける(new 人数(2));
    }

    private 料金 指定席片道料金計算(駅 降車駅, 特急利用 _特急利用) {
        final 料金 自由席減額 = new 料金(530);

        switch (_特急利用) {
            case ひかり指定席:
                return 降車駅.get指定席ひかり料金();
            case のぞみ指定席:
                return 降車駅.get指定席ひかり料金().足す(降車駅.getのぞみ割り増し料金());
            case 自由席:
                return 降車駅.get指定席ひかり料金().引く(自由席減額);
        }
        return new 料金(0);
    }

    private 料金 大人一人分片道計算(駅 降車駅, 特急利用 _特急利用) {
        料金 大人一人分 = 降車駅.get運賃();

        return 大人一人分.足す(指定席片道料金計算(降車駅, _特急利用));
    }

    private 料金 子供一人分片道計算(駅 降車駅, 特急利用 _特急利用) {
        final 割引率 子供割引率 = new 割引率(50);
        料金 子供一人分 = 降車駅.get運賃().割引(子供割引率);

        return 子供一人分.足す(指定席片道料金計算(降車駅, _特急利用).割引(子供割引率));
    }

    private 料金 割引計算(料金 一人分片道料金, 距離 _距離, 利用日 _利用日, 人数 大人人数, 人数 子供人数) {
        final int 往復割引最短距離 = 600;
        final 割引率 往復割引率 = new 割引率(10);
        if (_距離.getキロメートル() > 往復割引最短距離) {
            一人分片道料金 = 一人分片道料金.割引(往復割引率);
        }

        final int 団体割引最少人数 = 8;
        final 割引率 団体冬割引率 = new 割引率(10);
        final 割引率 団体割引率 = new 割引率(15);
        if (大人人数.get_人数() + 子供人数.get_人数() >= 団体割引最少人数) {
            if (_利用日.get_利用日().isAfter(LocalDate.of(_利用日.get_利用日().getYear(), 12, 20)) &&
                    _利用日.get_利用日().isBefore(LocalDate.of(_利用日.get_利用日().getYear(), 1, 11))) {
                一人分片道料金 = 一人分片道料金.割引(団体冬割引率);
            } else {
                一人分片道料金 = 一人分片道料金.割引(団体割引率);
            }
        }

        return 一人分片道料金;
    }

    private 人数 無料人数計算(人数 大人人数, 人数 子供人数) {
        int 合計人数 = 大人人数.get_人数() + 子供人数.get_人数();
        return 合計人数 > 30 ? new 人数((合計人数 + 49) / 50) : new 人数(0);

    }
}
