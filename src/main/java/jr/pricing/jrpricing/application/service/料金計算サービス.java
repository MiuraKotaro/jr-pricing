package jr.pricing.jrpricing.application.service;

import jr.pricing.jrpricing.domain.model.*;
import jr.pricing.jrpricing.domain.model.fare.割引率;
import jr.pricing.jrpricing.domain.model.fare.料金;

public class 料金計算サービス {
    public 料金 料金計算(駅 降車駅, 利用日 _利用日, 片道往復 _片道往復, 人数 大人人数, 人数 子供人数, 特急利用 _特急利用) {

        人数 無料人数 = 無料人数計算(大人人数.足す(子供人数));
        if (大人人数.大なりイコール(無料人数)) {
            大人人数 = 大人人数.引く(無料人数);
        } else {
            無料人数 = 無料人数.引く(大人人数);
            大人人数 = new 人数(0);
            子供人数 = 子供人数.引く(無料人数);
        }

        料金 片道合計金額 = new 料金(0);
        if (大人人数.大なりイコール(new 人数(1))) {
            料金 大人一人分片道料金 = 一人分片道計算(降車駅, _特急利用, _片道往復, _利用日, 大人人数, 子供人数, 年齢区分.おとな);
            片道合計金額 = 大人一人分片道料金.かける(大人人数);
        }
        if (子供人数.大なりイコール(new 人数(1))) {
            料金 子供一人分片道料金 = 一人分片道計算(降車駅, _特急利用, _片道往復, _利用日, 大人人数, 子供人数, 年齢区分.こども);
            片道合計金額 = 片道合計金額.足す(子供一人分片道料金.かける(子供人数));
        }

        return _片道往復 == 片道往復.片道 ? 片道合計金額 : 片道合計金額.足す(片道合計金額);
    }

    private 料金 一人分片道計算(駅 降車駅, 特急利用 _特急利用, 片道往復 _片道往復, 利用日 _利用日, 人数 大人人数, 人数 子供人数, 年齢区分 _年齢区分) {
        final 割引率 年齢区分割引率 = _年齢区分.get運賃割引率();

        料金 一人分運賃 = 降車駅.get運賃().割引(年齢区分割引率);
        一人分運賃 = 運賃割引計算(一人分運賃, 降車駅.get_距離(), _片道往復, _利用日, 大人人数, 子供人数);
        料金 一人分特急料金 = _特急利用.get特急料金(降車駅);
        料金 一人分 = 一人分運賃.足す(一人分特急料金);
        return 一人分.足す(_特急利用.get特急料金(降車駅).割引(年齢区分割引率));
    }

    private 料金 運賃割引計算(料金 一人分片道運賃, 距離 _距離, 片道往復 _片道往復, 利用日 _利用日, 人数 大人人数, 人数 子供人数) {
        一人分片道運賃 = _片道往復.往復割引計算(一人分片道運賃, _距離);
        一人分片道運賃 = 団体時期割引計算(一人分片道運賃, _利用日, 大人人数, 子供人数);
        return 一人分片道運賃;
    }

    private 料金 団体時期割引計算(料金 一人分片道運賃, 利用日 _利用日, 人数 大人人数, 人数 子供人数) {
        final 人数 団体割引最少人数 = new 人数(8);
        final 割引率 団体年末年始割引率 = new 割引率(10);
        final 割引率 団体割引率 = new 割引率(15);
        final 人数 全体人数 = 大人人数.足す(子供人数);

        if (全体人数.大なりイコール(団体割引最少人数)) {
            if (_利用日.isAfter(12, 20) && _利用日.isBefore(1, 11)) {
                return 一人分片道運賃.割引(団体年末年始割引率);
            } else {
                return 一人分片道運賃.割引(団体割引率);
            }
        }

        return 一人分片道運賃;
    }

    private 人数 無料人数計算(人数 全体人数) {
        final 人数 無料化最少人数 = new 人数(31);
        return 全体人数.大なりイコール(無料化最少人数) ? 全体人数.plus49div50() : new 人数(0);

    }
}
