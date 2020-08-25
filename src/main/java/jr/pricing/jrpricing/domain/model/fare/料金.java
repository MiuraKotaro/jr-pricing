package jr.pricing.jrpricing.domain.model.fare;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class 料金 {
    private final int 円;
    
    public 料金 足す(料金 足す数) {
        return new 料金(円 + 足す数.get円());
    }
}
