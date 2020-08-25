package jr.pricing.jrpricing.domain.model.fare;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class 運賃 {
    private final 料金 _料金;

    public int get円() {
        return _料金.get円();
    }
}
