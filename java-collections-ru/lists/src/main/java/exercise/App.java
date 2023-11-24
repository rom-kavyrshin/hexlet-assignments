package exercise;

import java.util.*;

// BEGIN
public class App {



    public static void queueTest() {
        Deque<String> stack = new LinkedList<>();
        stack.offerFirst("First");
        stack.offerFirst("Second");
        stack.offerFirst("Third");

        for (String s : stack) {
            System.out.println(s);
        }

        System.out.println();

        System.out.println(stack.peekFirst());
        System.out.println(stack.poll());
        System.out.println(stack.pollFirst());
        System.out.println(stack.pollFirst());
        System.out.println(stack.pollFirst());
    }

    public static void queueTest2() {
        Deque<String> stack = new LinkedList<>();
        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        for (String s : stack) {
            System.out.println(s);
        }

        System.out.println();

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
//END
