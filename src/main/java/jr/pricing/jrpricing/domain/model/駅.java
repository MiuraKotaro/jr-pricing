package jr.pricing.jrpricing.domain.model;

import jr.pricing.jrpricing.domain.model.fare.料金;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum 駅 {
    東京(new 料金(0), new 料金(0), new 料金(0), new 距離(0)),
    新大阪(new 料金(8_910), new 料金(5_490), new 料金(320), new 距離(553)),
    姫路(new 料金(10_010), new 料金(5_920), new 料金(530), new 距離(644));

    private final 料金 運賃;
    private final 料金 指定席ひかり料金;
    private final 料金 のぞみ割り増し料金;
    private final 距離 dist;
}
