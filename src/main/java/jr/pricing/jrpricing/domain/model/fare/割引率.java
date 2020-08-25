package jr.pricing.jrpricing.domain.model.fare;

public class 割引率 {
    private final int パーセント;

    public 割引率(int パーセント) {
        if (パーセント > 100 || パーセント < 0) {
            throw new RuntimeException("不正な値です");
        }
        this.パーセント = パーセント;
    }

    public double get百分率() {
        return パーセント / 100.0;
    }
}
