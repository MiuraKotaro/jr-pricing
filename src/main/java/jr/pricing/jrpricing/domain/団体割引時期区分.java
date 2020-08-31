package jr.pricing.jrpricing.domain;

import jr.pricing.jrpricing.domain.model.fare.割引率;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

/**
 * 団体割引の条件分岐を無くしたいが、できなさそう。
 */
@AllArgsConstructor
public enum 団体割引時期区分 {
    年末年始(LocalDate.of(LocalDate.now().getYear(), 12, 21),
            LocalDate.of(LocalDate.now().getYear(), 1, 10),
            new 割引率(10)),
    その他(LocalDate.of(LocalDate.now().getYear(), 1, 11),
            LocalDate.of(LocalDate.now().getYear(), 12, 20),
            new 割引率(15));

    private final LocalDate start;
    private final LocalDate end;
    private final 割引率 _割引率;
}

