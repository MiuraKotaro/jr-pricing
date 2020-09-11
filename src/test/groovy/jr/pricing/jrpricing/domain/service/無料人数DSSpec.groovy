package jr.pricing.jrpricing.domain.service

import jr.pricing.jrpricing.domain.model.人数
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class 無料人数DSSpec extends Specification {
    def "get無料人数正常"(int a, int b) {
        setup:
        def ninn = new 人数(a)
        def muryo = new 無料人数ドメインサービス(new 人数(1), new 人数(1));

        expect:
        muryo.get無料人数(ninn).getValue() == b

        where:
        a   || b
        0   || 0
        1   || 0
        2   || 0
        9   || 0
        10  || 0
        30  || 0
        31  || 1
        50  || 1
        51  || 2
        100 || 2
        101 || 3
    }

}