package jr.pricing.jrpricing.domain.model.fare;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class 割引率 {
    private final int パーセント;

    public 割引率(int パーセント) {
        if (パーセント > 100 || パーセント < 0) {
            throw new RuntimeException("0以上100以下の整数を入力してください。");
        }
        this.パーセント = パーセント;
    }

    double get百分率() {
        return パーセント / 100.0;
    }
}
