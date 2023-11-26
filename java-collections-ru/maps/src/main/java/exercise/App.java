package exercise;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static void main(String[] args) {
        toString(getWordCount("hi all hi some hi"));
    }

    public static Map<String, Integer> getWordCount(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .filter(it -> !it.isEmpty())
                .collect(Collectors.groupingBy(x -> x, Collectors.summingInt(it -> 1)));
    }

    public static String toString(Map<String, Integer> source) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        if (!source.isEmpty()) {
            stringBuilder.append(System.lineSeparator());

            for (Map.Entry<String, Integer> entry : source.entrySet()) {
                stringBuilder.append("  ").append(entry.getKey()).append(":").append(" ").append(entry.getValue());
                stringBuilder.append(System.lineSeparator());
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
//END
