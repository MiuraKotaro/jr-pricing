package jr.pricing.jrpricing.domain.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class 人数 implements Comparable<人数> {
    int value;

    public 人数(int i) {
        if (i < 0) {
            throw new RuntimeException("0以上の整数を入力してください。");
        }
        value = i;
    }

    public 人数 足す(@NonNull 人数 足す人数) {
        return new 人数(value + 足す人数.value);
    }

    public 人数 引く(@NonNull 人数 引く人数) {
        return new 人数(value - 引く人数.value);
    }

    @Override
    public int compareTo(@NonNull 人数 o) {
        return Integer.compare(value, o.value);
    }
}
