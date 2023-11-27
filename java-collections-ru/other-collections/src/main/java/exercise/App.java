package exercise;

import java.util.*;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static void main(String[] args) {
        Map<String, Object> data1 = new HashMap<>(
                Map.of("one", "eon", "two", "two", "four", true, "abs", 'h')
        );
        Map<String, Object> data2 = new HashMap<>(
                Map.of("two", "own", "zero", 4, "four", true)
        );
        System.out.println(data1);
        System.out.println(data2);

        var result = genDiff(data1, data2);

        System.out.println(result);
    }

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> first, Map<String, Object> second) {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        Set<String> added = new HashSet<>(second.keySet());
        added.removeAll(first.keySet());

        result.putAll(added.stream().collect(Collectors.toMap(it -> it, it -> "added")));
        System.out.println("added " + added);

        Set<String> deleted = new HashSet<>(first.keySet());
        deleted.removeAll(second.keySet());

        result.putAll(deleted.stream().collect(Collectors.toMap(it -> it, it -> "deleted")));
        System.out.println("deleted " + deleted);

        Set<String> intersection = new HashSet<>(first.keySet());
        intersection.retainAll(second.keySet());

        for (String key : intersection) {
            if (first.get(key).equals(second.get(key))) {
                result.put(key, "unchanged");
            } else {
                result.put(key, "changed");
            }
        }
        System.out.println("intersection " + intersection);

        return result;
    }
}
//END
