package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {

    public static final String[][] firstImage = {
            {"*", "*", "*", "*"},
            {"*", " ", " ", "*"},
            {"*", " ", " ", "*"},
            {"*", "*", "*", "*"},
    };

    public static final String[][] secondImage = {
            {"*", "*"},
            {" ", " "},
            {" ", " "},
            {"*", "*"},
    };

    public static final String[][] thirdImage = {};

    @Test
    void enlargeArrayImage_ПроверитьНормальныйСлучайСКвадратнойМатрицей() {
        String[][] expected = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };

        assertThat(App.enlargeArrayImage(firstImage))
                .isDeepEqualTo(expected);
    }

    @Test
    void enlargeArrayImage_ПроверитьНормальныйСлучайСНеКвадратнойМатрицей() {
        String[][] expected = {
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
                {" ", " ", " ", " "},
                {" ", " ", " ", " "},
                {" ", " ", " ", " "},
                {" ", " ", " ", " "},
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
        };

        assertThat(App.enlargeArrayImage(secondImage))
                .isDeepEqualTo(expected);
    }

    @Test
    void enlargeArrayImage_ПроверитьСлучайСПустойМатрицей() {
        String[][] expected = new String[][]{};

        assertThat(App.enlargeArrayImage(thirdImage))
                .isDeepEqualTo(expected);
    }

}
// END