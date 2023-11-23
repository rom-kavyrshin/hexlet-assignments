package exercise;

import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String getForwardedVariables(String content) {
        return content.lines()
                .filter(it -> it.startsWith("environment"))
                .map(it -> it.replaceAll("environment=", ""))
                .map(it -> it.replaceAll("\"", ""))
                .flatMap(it -> Stream.of(it.split(",")))
                .filter(it -> it.startsWith("X_FORWARDED_"))
                .map(it -> it.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
