package exercise;

import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {

    public static final String GENDER_KEY = "gender";
    public static final String GENDER_MALE_VALUE = "male";
    public static final String BIRTHDAY_KEY = "birthday";
    public static final String NAME_KEY = "name";

    public static List<String> takeOldestMans(List<Map<String, String>> personList) {
        return personList.stream()
                .filter(person -> person.get(GENDER_KEY).equals(GENDER_MALE_VALUE))
                .sorted((p1, p2) -> {
                    var first = LocalDate.parse(p1.get(BIRTHDAY_KEY));
                    var second = LocalDate.parse(p2.get(BIRTHDAY_KEY));
                    return first.compareTo(second);
                })
                .map(person -> person.get(NAME_KEY))
                .collect(Collectors.toList());
    }
}
// END
