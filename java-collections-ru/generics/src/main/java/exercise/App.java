package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        var result = new ArrayList<Map<String, String>>();
        var bookSatisfy = false;

        for (Map<String, String> book : books) {
            bookSatisfy = true;

            for (Map.Entry<String, String> whereEntry : where.entrySet()) {
                if (!book.get(whereEntry.getKey()).equals(whereEntry.getValue())) {
                    bookSatisfy = false;
                }
            }

            if (bookSatisfy) {
                result.add(book);
            }
        }

        return result;
    }
}
//END
