package jr.pricing.jrpricing.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class 人数 {
    private final int value;

    public 人数(int i) {
        if (i < 0) {
            throw new RuntimeException("0以上の整数を入力してください。");
        }
        value = i;
    }

    public 人数 足す(人数 足す人数) {
        return new 人数(value + 足す人数.value);
    }

    public 人数 引く(人数 引く人数) {
        return new 人数(value - 引く人数.value);
    }

    public boolean 大なりイコール(人数 比較人数) {
        return value >= 比較人数.value;
    }

    public boolean 大なり(人数 比較人数) {
        return value > 比較人数.value;
    }

    public 人数 get無料人数() {
        final 人数 無料化最少人数 = new 人数(31);
        return this.大なりイコール(無料化最少人数) ? new 人数((value + 49) / 50) : new 人数(0);
    }
}
