package jr.pricing.jrpricing.domain.model.fare;

import jr.pricing.jrpricing.domain.model.人数;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class 料金 {
    private final int 円;
    
    public 料金 足す(料金 足す数) {
        return new 料金(円 + 足す数.get円());
    }
    public 料金 かける(人数 かける数) {
        return new 料金(円 * かける数.get_人数());
    }
    public 料金 割引(割引率 _割引率) {
        // １の位切り捨て
        return new 料金((int)(円 * (1 - _割引率.get百分率()) / 10) * 10);
    }
}
