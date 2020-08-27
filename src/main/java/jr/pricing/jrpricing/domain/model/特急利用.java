package jr.pricing.jrpricing.domain.model;

import jr.pricing.jrpricing.domain.model.fare.料金;

public enum 特急利用 {
    利用しない {
        @Override
        public 料金 get特急料金(駅 降車駅) {
            return new 料金(0);
        }
    },
    ひかり指定席 {
        @Override
        public 料金 get特急料金(駅 降車駅) {
            return 降車駅.get指定席ひかり料金();
        }
    },
    のぞみ指定席 {
        @Override
        public 料金 get特急料金(駅 降車駅) {
            return 降車駅.get指定席ひかり料金().足す(降車駅.getのぞみ割り増し料金());
        }
    },
    自由席 {
        private final 料金 自由席減額 = new 料金(530);

        @Override
        public 料金 get特急料金(駅 降車駅) {
            return 降車駅.get指定席ひかり料金().引く(自由席減額);
        }
    };


    public abstract 料金 get特急料金(駅 降車駅);
}
