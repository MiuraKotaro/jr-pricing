package jr.pricing.jrpricing.domain.model.fare

import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class 割引率Spec extends Specification {
    /**
     * 正常系
     */

    def "正常：new 割引率(#a)"(int a) {
        setup:
        new 割引率(a)

        where:
        a << [0, 1, 2, 9, 10, 50, 90, 95, 99, 100]
    }

    def "正常：割引率(#a).get百分率 == #b"(int a, double b) {
        expect:
        new 割引率(a).get百分率() == b

        where:
        a   | b
        0   | 0
        1   | 0.01
        10  | 0.1
        23  | 0.23
        99  | 0.99
        100 | 1
    }

    /**
     * 異常系
     */

    def "異常：new 割引率(#a)"(int a) {
        when:
        new 割引率(a)

        then:
        thrown(RuntimeException)

        where:
        a << [-1, 101]
    }
}