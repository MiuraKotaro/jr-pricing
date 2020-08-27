package jr.pricing.jrpricing.domain.model;

import jr.pricing.jrpricing.domain.model.fare.割引率;
import jr.pricing.jrpricing.domain.model.fare.料金;

public enum 片道往復 {
    片道 {
        @Override
        public 料金 往復割引計算(料金 一人分片道運賃, 距離 _距離) {
            return 一人分片道運賃;
        }
    },
    往復 {
        private final 割引率 運賃割引率 = new 割引率(10);
        private final 距離 往復割引最短距離 = new 距離(600);

        @Override
        public 料金 往復割引計算(料金 一人分片道運賃, 距離 _距離) {
            return _距離.大なり(往復割引最短距離) ? 一人分片道運賃.割引(運賃割引率) : 一人分片道運賃;
        }
    };

    public abstract 料金 往復割引計算(料金 一人分片道運賃, 距離 _距離);
}
