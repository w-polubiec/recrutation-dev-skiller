package pl.wpolubiec;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
class DeveloperReputationCalculatorTest {

    private final DeveloperReputationCalculator calculator = new DeveloperReputationCalculator();

    private Stream<Arguments> checkCurrentMonthAndGetArguments() {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        if (dateTime.getMonth() == Month.OCTOBER) {
            return Stream.of(
                    Arguments.of(1, 12),
                    Arguments.of(2, 14),
                    Arguments.of(3, 16),
                    Arguments.of(4, 18),
                    Arguments.of(5, 20)
            );
        } else {
            return Stream.of(
                    Arguments.of(1, 2),
                    Arguments.of(2, 4),
                    Arguments.of(3, 6),
                    Arguments.of(4, 8),
                    Arguments.of(5, 10)
            );
        }
    }

    @ParameterizedTest
    @MethodSource("pl.wpolubiec.DeveloperReputationCalculatorTest#checkCurrentMonthAndGetArguments")
    void should_calculate_using_current_date_time(int listSize, int result) {
        List<Developer> mockList = Mockito.mock(ArrayList.class);
        when(mockList.size()).thenReturn(listSize);
        Developer dev = new Developer("John", mockList);

        assertEquals(result, calculator.calculate(dev));
    }

    @ParameterizedTest
    @CsvSource({"1,12", "2,14", "3,16", "4,18", "5,20"})
    void should_calculate_using_new_method_when_October(int listSize, int result) {
        ZonedDateTime dateTime = ZonedDateTime.parse("2024-10-01T10:15:30+01:00[Europe/Paris]");
        List<Developer> mockList = Mockito.mock(ArrayList.class);
        when(mockList.size()).thenReturn(listSize);
        Developer dev = new Developer("John", mockList);

        assertEquals(result, calculator.calculate(dev, dateTime));
    }

    @Test
    public void should_use_constant_date_time_with_month_october() {
        ZonedDateTime dateTime = ZonedDateTime.parse("2024-10-01T10:15:30+01:00[Europe/Paris]");
        assertEquals(dateTime.getMonth(), Month.OCTOBER);
    }

}