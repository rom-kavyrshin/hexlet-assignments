package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static final String[][] exampleImage = {
        {"*", "*", "*", "*"},
        {"*", " ", " ", "*"},
        {"*", " ", " ", "*"},
        {"*", "*", "*", "*"},
    };

    public static void main(String[] args) {
        enlargeArrayImage(exampleImage);
    }

    public static String[][] enlargeArrayImage(String[][] source) {
//        print2DArray(source);
        String[][] result = Arrays.stream(source)
                .flatMap(x -> Stream.of(x, x))
                .map(x -> Arrays.stream(x)
                        .flatMap(y -> Stream.of(y, y))
                        .toArray(String[]::new)
                )
                .toArray(String[][]::new);

//        print2DArray(result);

        return result;
    }

    public static void print2DArray(String[][] source) {
        System.out.println("[");
        for (String[] out : source) {
            System.out.print("    ");
            System.out.print("[");
            for (int j = 0; j < out.length; j++) {
                String in = out[j];
                System.out.print(in);
                if (j != out.length - 1) {
                    System.out.print(",");
                    System.out.print(" ");
                }
            }
            System.out.print("]");
            System.out.print(",");
            System.out.println();
        }
        System.out.println("]");
    }
}
// END
