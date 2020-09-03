package jr.pricing.jrpricing.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class 利用日 {
    private final LocalDate value;

    public boolean isAfter(int month, int day) {
        return value.isAfter(LocalDate.of(value.getYear(), month, day));
    }

    public boolean isBefore(int month, int day) {
        return value.isBefore(LocalDate.of(value.getYear(), month, day));
    }

}
