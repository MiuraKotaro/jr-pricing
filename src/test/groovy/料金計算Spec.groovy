import jr.pricing.jrpricing.application.service.料金計算サービス
import jr.pricing.jrpricing.domain.model.*
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class 料金計算Spec extends Specification {
    /**
     * 正常系
     */

    @Unroll
    def "fare calculation specification"(駅 降車駅, LocalDate date, 片道往復 _片道往復, int adult, int child, 特急利用 _特急利用, int yen) {
        expect:
        料金計算サービス.料金計算(降車駅, new 利用日(date), _片道往復, new 人数(adult), new 人数(child), _特急利用).get円() == yen

        where:
        降車駅   | date                       | _片道往復   | adult | child | _特急利用       || yen
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 0     | 0     | 特急利用.利用しない  || 0
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 1     | 0     | 特急利用.利用しない  || 10010
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 1     | 0     | 特急利用.ひかり指定席 || 15930
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 1     | 0     | 特急利用.のぞみ指定席 || 16460
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 1     | 0     | 特急利用.自由席    || 15400
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 30    | 0     | 特急利用.利用しない  || 255000
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 31    | 0     | 特急利用.利用しない  || 255000
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 0     | 1     | 特急利用.利用しない  || 5000
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 7     | 0     | 特急利用.利用しない  || 70070
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 7     | 0     | 特急利用.利用しない  || 駅.姫路.get運賃().かける(new 人数(7)).get円()
        駅.姫路  | LocalDate.of(2020, 12, 21) | 片道往復.片道 | 7     | 0     | 特急利用.利用しない  || 70070
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 8     | 0     | 特急利用.利用しない  || 68000
        駅.姫路  | LocalDate.of(2020, 12, 20) | 片道往復.片道 | 8     | 0     | 特急利用.利用しない  || 68000
        駅.姫路  | LocalDate.of(2020, 12, 21) | 片道往復.片道 | 8     | 0     | 特急利用.利用しない  || 72000
        駅.姫路  | LocalDate.of(2020, 1, 10)  | 片道往復.片道 | 8     | 0     | 特急利用.利用しない  || 72000
        駅.姫路  | LocalDate.of(2020, 1, 11)  | 片道往復.片道 | 8     | 0     | 特急利用.利用しない  || 68000
        駅.姫路  | LocalDate.of(2020, 9, 3)   | 片道往復.往復 | 1     | 0     | 特急利用.利用しない  || 18000
        駅.新大阪 | LocalDate.of(2020, 9, 3)   | 片道往復.往復 | 1     | 0     | 特急利用.利用しない  || 8910 * 2
        駅.新大阪 | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 0     | 1     | 特急利用.ひかり指定席 || 7190
        駅.新大阪 | LocalDate.of(2020, 9, 3)   | 片道往復.片道 | 1     | 1     | 特急利用.ひかり指定席 || 21590
    }

    /**
     * 異常系
     */
}