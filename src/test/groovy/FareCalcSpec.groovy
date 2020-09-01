import jr.pricing.jrpricing.domain.model.人数
import jr.pricing.jrpricing.domain.model.利用日
import jr.pricing.jrpricing.domain.model.片道往復
import jr.pricing.jrpricing.domain.model.特急利用
import jr.pricing.jrpricing.domain.model.駅
import jr.pricing.jrpricing.application.service.料金計算サービス
import spock.lang.Specification

import java.time.LocalDate

class FareCalcSpec extends Specification {
    def "fare calculation specification"(駅 降車駅, LocalDate date, 片道往復 _片道往復, int adult, int child, 特急利用 _特急利用, int yen) {
        expect:
        料金計算サービス.料金計算(降車駅, new 利用日(date), _片道往復, new 人数(adult), new 人数(child), _特急利用).get円() == yen

        where:
        降車駅  | date            | _片道往復   | adult | child | _特急利用      || yen
        駅.姫路 | LocalDate.now() | 片道往復.片道 | 1     | 0     | 特急利用.利用しない || 10010
    }
}