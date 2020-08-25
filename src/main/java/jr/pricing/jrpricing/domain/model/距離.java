package jr.pricing.jrpricing.domain.model;

import lombok.Getter;

@Getter
public class 距離 {
    private final int キロメートル;

    public 距離(int キロメートル) {
        if (キロメートル < 0) {
            throw new RuntimeException("距離は0以上の整数です。");
        }
        this.キロメートル = キロメートル;
    }
}
