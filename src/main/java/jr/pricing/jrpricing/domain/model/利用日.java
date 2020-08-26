package jr.pricing.jrpricing.domain.model;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class 利用日 {
    private final LocalDate _利用日;

    public boolean isAfter(int month, int day) {
        return _利用日.isAfter(LocalDate.of(_利用日.getYear(), month, day));
    }

    public boolean isBefore(int month, int day) {
        return _利用日.isBefore(LocalDate.of(_利用日.getYear(), month, day));
    }
}
