package jr.pricing.jrpricing.domain.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class 距離 implements Comparable<距離> {
    private final int キロメートル;

    public 距離(int キロメートル) {
        if (キロメートル < 0) {
            throw new RuntimeException("距離は0以上の整数です。");
        }
        this.キロメートル = キロメートル;
    }

    @Override
    public int compareTo(@NonNull 距離 o) {
        return this.キロメートル - o.キロメートル;
    }
}
