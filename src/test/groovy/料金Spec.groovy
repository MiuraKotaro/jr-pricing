import jr.pricing.jrpricing.domain.model.fare.割引率
import jr.pricing.jrpricing.domain.model.fare.料金
import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class 料金Spec extends Specification {
    /**
     * 正常系
     */

    def "料金コンストラクタ正常"(int a) {
        setup:
        def fare = new 料金(a)

        expect:
        fare != null

        where:
        a   | _
        0   | _
        10  | _
        20  | _
        200 | _
    }

    def "料金.足す正常spec"(int a, int b, int c) {
        expect:
        new 料金(a).足す(new 料金(b)).get円() == c

        where:
        a  | b  || c
        10 | 20 || 30
        90 | 70 || 160
    }

    def "料金.割引正常"(int a, int yen) {
        setup:
        def fare = new 料金(10000)
        def par = new 割引率(a)

        expect:
        fare.割引(par).get円() == yen

        where:
        a   || yen
        0   || 10000
        1   || 9900
        99  || 100
        100 || 0
    }

    /**
     * 異常系
     */

    def "料金を少数でコンストラクト"(double a) {
        when:
        new 料金(a)

        then:
        thrown(RuntimeException)

        where:
        a    | _
        10.0 | _
        30.3 | _
    }

    def "料金コンストラクタで例外が出る場合"(int a) {
        when:
        new 料金(a)

        then:
        thrown(RuntimeException)

        where:
        a   | _
        9   | _
        11  | _
        99  | _
        -1  | _
        -90 | _
        -98 | _
    }

}