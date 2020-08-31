package jr.pricing.jrpricing.domain.model;

public class 距離 {
    private final int キロメートル;

    public 距離(int キロメートル) {
        if (キロメートル < 0) {
            throw new RuntimeException("距離は0以上の整数です。");
        }
        this.キロメートル = キロメートル;
    }

    public boolean 大なりイコール(距離 _距離) {
        return キロメートル >= _距離.キロメートル;
    }
}
