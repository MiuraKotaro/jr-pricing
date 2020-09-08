import jr.pricing.jrpricing.domain.model.人数
import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class 人数Spec extends Specification {
    /**
     * 正常系
     */
    def "#a .compareTo( #b ) == #c "() {
        setup:
        def A = new 人数(a)
        def B = new 人数(b)

        expect:
        A <=> B == c

        where:
        a | b  || c
        3 | 2  || 1
        3 | 3  || 0
        5 | 5  || 0
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