package jr.pricing.jrpricing.domain.model.fare

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class 料金Spec extends Specification {
    /**
     * 正常系
     */

    def "正常：new 料金(#a)"(int a) {
        setup:
        new 料金(a)

        where:
        a << [0, 10, 20, 110, 200]
    }

    def "正常：料金(#a).足す(料金(#b)).get円() == #c"(int a, int b, int c) {
        expect:
        new 料金(a).足す(new 料金(b)).get円() == c

        where:
        a  | b
        10 | 20
        90 | 70
        c = a + b
    }

    def "正常：料金(#a).引く(料金(#b)).get円() == #c"(int a, int b, int c) {
        expect:
        new 料金(a).引く(new 料金(b)).get円() == c

        where:
        a   | b
        0   | 0
        30  | 20
        90  | 70
        110 | 110
        c = a - b
    }

    def "正常：料金(11110).割引(#a)"(int a, int yen) {
        setup:
        def fare = new 料金(11110)
        def par = new 割引率(a)

        expect:
        fare.割引(par).get円() == yen

        where:
        a << [100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        yen << [0, 110, 220, 330, 440, 550, 660, 770, 880, 990, 1110, 1220, 1330, 1440, 1550, 1660, 1770, 1880, 1990, 2110, 2220, 2330, 2440, 2550, 2660, 2770, 2880, 2990, 3110, 3220, 3330, 3440, 3550, 3660, 3770, 3880, 3990, 4110, 4220, 4330, 4440, 4550, 4660, 4770, 4880, 4990, 5110, 5220, 5330, 5440, 5550, 5660, 5770, 5880, 5990, 6110, 6220, 6330, 6440, 6550, 6660, 6770, 6880, 6990, 7110, 7220, 7330, 7440, 7550, 7660, 7770, 7880, 7990, 8110, 8220, 8330, 8440, 8550, 8660, 8770, 8880, 8990, 9110, 9220, 9330, 9440, 9550, 9660, 9770, 9880, 9990, 10110, 10220, 10330, 10440, 10550, 10660, 10770, 10880, 10990, 11110]
    }

    /**
     * 異常系
     */

    def "異常：new 料金(double #a)"() {
        when:
        new 料金(a)

        then:
        thrown(RuntimeException)

        where:
        a << [10.0, 30.3]
    }

    def "異常：new 料金(int #a)"() {
        when:
        new 料金(a)

        then:
        thrown(RuntimeException)

        where:
        a << [9, 11, 99, -1, -90, -98]
    }

    def "異常：料金(#a).足す(料金(#b))"(int a, int b) {

        when:
        new 料金(a).足す(new 料金(b))

        then:
        thrown(RuntimeException)

        where:
        a          | b
        Integer.MAX_VALUE / 2 | Integer.MAX_VALUE / 2 + 1
    }

    def "異常：料金(#a).引く(料金(#b)).get円()"(int a, int b) {
        when:
        new 料金(a).引く(new 料金(b))

        then:
        thrown(RuntimeException)

        where:
        a | b
        0 | 20
    }

}