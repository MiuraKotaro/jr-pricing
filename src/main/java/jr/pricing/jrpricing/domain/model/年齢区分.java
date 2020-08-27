package jr.pricing.jrpricing.domain.model;

import jr.pricing.jrpricing.domain.model.fare.割引率;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum 年齢区分 {
    おとな(new 割引率(0)),
    こども(new 割引率(50));

    割引率 運賃割引率;
}
