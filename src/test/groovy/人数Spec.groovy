import jr.pricing.jrpricing.domain.model.人数
import spock.lang.Specification
import spock.lang.Unroll


class 人数Spec extends Specification {
    /**
     * 正常系
     */
    @Unroll
    def "get人数正常"(int a, int b) {
        setup:
        def ninn = new 人数(a);

        expect:
        ninn.get無料人数().getValue() == b

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

    /**
     * 異常系
     */
}