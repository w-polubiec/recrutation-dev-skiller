package pl.wpolubiec

import spock.lang.Specification

import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime

class DeveloperReputationCalculatorSpec extends Specification {

    final DeveloperReputationCalculator calculator = new DeveloperReputationCalculator()

    def "should calculate properly when not October date"() {

        given:
        List<Developer> list = Mock(ArrayList.class)

        when:
        list.size() >> size
        Developer dev = new Developer("John", list)

        then:
        calculator.calculate(dev) == result

        where:
        size | result
        0    | 0
        1    | 2
        2    | 4
        3    | 6
        4    | 8
        5    | 10
        6    | 12
    }

    def "should calculate properly when October "() {

        given:
        ZonedDateTime dateTime = ZonedDateTime.parse("2024-10-01T10:15:30+01:00[Europe/Paris]")
        List<Developer> list = Mock(ArrayList.class)

        when:
        list.size() >> size
        Developer dev = new Developer("John", list)

        then:
        calculator.calculate(dev, dateTime) == result

        where:
        size | result
        0    | 10
        1    | 12
        2    | 14
        3    | 16
        4    | 18
        5    | 20
        6    | 22
    }

    def "should calculate same size changing months"() {

        given:
        List<Developer> list = Mock(ArrayList.class)

        when:
        list.size() >> size
        ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.of(2024, month, 1, 13, 00), ZoneId.of("UTC"))
        Developer dev = new Developer("John", list)

        then:
        calculator.calculate(dev, dateTime) == result

        where:
        month           | size | result
        Month.JUNE      | 2    | 4
        Month.JULY      | 2    | 4
        Month.AUGUST    | 2    | 4
        Month.SEPTEMBER | 2    | 4
        Month.OCTOBER   | 2    | 14
        Month.NOVEMBER  | 2    | 4
        Month.DECEMBER  | 2    | 4
    }

}