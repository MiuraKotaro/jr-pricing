import jr.pricing.jrpricing.domain.model.人数
import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class 人数Spec extends Specification {
    /**
     * 正常系
     */
    def "get無料人数正常"(int a, int b) {
        setup:
        def ninn = new 人数(a)

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

    def "#a .compareTo( #b ) == #c "() {
        setup:
        def A = new 人数(a)
        def B = new 人数(b)

        expect:
        A <=> B == c

        where:
        a | b || c
        3 | 2 || 1
        3 | 3 || 0
        5 | 5 || 0
        4 | 10 || -1
    }

    /**
     * 異常系
     */

    def "コンストラクタ異常"(int a) {
        when:
        new 人数(a)

        then:
        thrown(RuntimeException)

        where:
        a  | _
        -1 | _
    }
}