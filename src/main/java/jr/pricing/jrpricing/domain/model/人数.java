package jr.pricing.jrpricing.domain.model;

import lombok.Getter;

@Getter
public class 人数 {
    private final int _人数;

    public 人数(int i) {
        if (i < 0) {
            throw new RuntimeException("人数は0以上の整数です。");
        }
        _人数 = i;
    }
}
