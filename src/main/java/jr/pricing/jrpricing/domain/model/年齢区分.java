package jr.pricing.jrpricing.domain.model;

import jr.pricing.jrpricing.domain.model.fare.割引率;
import jr.pricing.jrpricing.domain.model.fare.料金;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum 年齢区分 {
    おとな(new 割引率(0)),
    こども(new 割引率(50));

    private final 割引率 _割引率;

    public 料金 割引計算(料金 割引前) {
        return 割引前.割引(_割引率);
    }
}
