package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void take_ИзСпискаИзШестиЭлементовВзятьПервыеТриЭлемента() {
        var source = List.of(1, 2, 3, 4, 5, 6);
        var expected = List.of(1, 2, 3);

        Assertions.assertEquals(expected, App.take(source, 3));
    }

    @Test
    void take_ИзСпискаИзТрехЭлементовВзятьПервыеШестьЭлементов() {
        var source = List.of(1, 2, 3);
        var expected = List.of(1, 2, 3);

        Assertions.assertEquals(expected, App.take(source, 6));
    }

    @Test
    void take_ИзПустогоСпискаВзятьПервыеТриЭлемента() {
        var source = new ArrayList<Integer>();
        var expected = new ArrayList<Integer>();

        Assertions.assertEquals(expected, App.take(source, 3));
    }
}
