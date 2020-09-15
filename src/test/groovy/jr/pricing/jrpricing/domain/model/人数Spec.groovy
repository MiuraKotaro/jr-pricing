package jr.pricing.jrpricing.domain.model

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class 人数Spec extends Specification {
    /**
     * 正常系
     */
    def "正常：new 人数(#a)"(int a) {
        setup:
        new 人数(a)

        where:
        a << [0, 1, 10, 100, 1 << 30, (1 << 31) - 1, Integer.MAX_VALUE, 1L << 32]
    }

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

    def "異常：new 人数(#a)"(int a) {
        when:
        new 人数(a)

        then:
        thrown(RuntimeException)

        where:
        a << [-1, Integer.MAX_VALUE + 1, Integer.MAX_VALUE * 2]
    }

    def "異常系：new 人数(#a).足す(new 人数(#b))"() {
        when:
        new 人数(a as int).足す(new 人数(b as int))

        then:
        thrown(RuntimeException)

        where:
        a           | b
        -2          | -3
        20000000000 | 20000000000
    }
}