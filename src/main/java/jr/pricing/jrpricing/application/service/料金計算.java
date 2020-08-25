package jr.pricing.jrpricing.application.service;

import jr.pricing.jrpricing.domain.model.*;
import jr.pricing.jrpricing.domain.model.fare.割引率;
import jr.pricing.jrpricing.domain.model.fare.料金;

import java.time.LocalDate;
import java.time.Month;

import static jr.pricing.jrpricing.domain.model.特急利用.*;

public class 料金計算 {
    public 料金 料金計算(駅 降車駅, 利用日 _利用日, 片道往復 _片道往復, 人数 十八才以上, 人数 十八才未満, 特急利用 _特急利用) {
        料金 大人一人分 = 十八才以上.get_人数() > 0 ? 大人一人分片道計算(降車駅, _特急利用) : new 料金(0);
        料金 子供一人分 = 十八才未満.get_人数() > 0 ? 子供一人分片道計算(降車駅, _特急利用) : new 料金(0);

        大人一人分 = 割引計算(大人一人分, 降車駅, _利用日, 十八才以上, 十八才未満);
        子供一人分 = 割引計算(子供一人分, 降車駅, _利用日, 十八才以上, 十八才未満);
        // 合計
        料金 合計金額 = (大人一人分.かける(十八才以上)).足す(子供一人分.かける(十八才未満));

        final 人数 無料人数 = 無料人数計算(十八才以上, 十八才未満);
        合計金額 = 合計金額.引く(大人一人分.かける(無料人数));

        return _片道往復.is片道() ? 合計金額 : 合計金額.かける(new 人数(2));
    }

    private 料金 大人一人分片道計算(駅 降車駅, 特急利用 _特急利用) {
        料金 大人一人分 = 降車駅.get_運賃().get_料金();
        final 料金 自由席減額 = new 料金(530);

        料金 指定席料金 = new 料金(0);
        switch (_特急利用) {
            case ひかり指定席:
                指定席料金 = 降車駅.get_指定席ひかり料金().get_料金();
                break;
            case のぞみ指定席:
                指定席料金 = 降車駅.get_指定席ひかり料金().get_料金().足す(降車駅.get_のぞみ割り増し料金().get_料金());
                break;
            case 自由席:
                指定席料金 = 降車駅.get_指定席ひかり料金().get_料金().引く(自由席減額);
                break;
        }

        return 大人一人分.足す(指定席料金);
    }

    private 料金 子供一人分片道計算(駅 降車駅, 特急利用 _特急利用) {
        final 割引率 子供割引率 = new 割引率(50);
        料金 子供一人分 = 降車駅.get_運賃().get_料金().割引(子供割引率);
        final 料金 自由席減額 = new 料金(530);

        料金 指定席料金 = new 料金(0);
        switch (_特急利用) {
            case ひかり指定席:
                指定席料金 = 降車駅.get_指定席ひかり料金().get_料金();
                break;
            case のぞみ指定席:
                指定席料金 = 降車駅.get_指定席ひかり料金().get_料金().足す(降車駅.get_のぞみ割り増し料金().get_料金());
                break;
            case 自由席:
                指定席料金 = 降車駅.get_指定席ひかり料金().get_料金().引く(自由席減額);
                break;
        }

        return 子供一人分.足す(指定席料金.割引(子供割引率));
    }

    private 料金 割引計算(料金 一人分片道料金, 駅 降車駅, 利用日 _利用日, 人数 大人人数, 人数 子供人数) {
        final int 往復割引最短距離 = 600;
        final 割引率 往復割引率 = new 割引率(10);
        if (降車駅.get_距離().getキロメートル() > 往復割引最短距離) {
            一人分片道料金 = 一人分片道料金.割引(往復割引率);
        }

        final int 団体割引最少人数 = 8;
        final 割引率 団体冬割引率 = new 割引率(10);
        final 割引率 団体割引率 = new 割引率(15);
        if (大人人数.get_人数() + 子供人数.get_人数() >= 団体割引最少人数) {
            if  (_利用日.get_利用日().isAfter(LocalDate.of(_利用日.get_利用日().getYear(), 12, 20)) &&
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
