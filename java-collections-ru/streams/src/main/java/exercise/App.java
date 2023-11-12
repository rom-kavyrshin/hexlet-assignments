package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static int getCountOfFreeEmails(List<String> emailList) {
        return (int) emailList.stream().filter(App::isFreeEmail).count();
    }

    public static boolean isFreeEmail(String email) {
        return email.endsWith("yandex.ru") || email.endsWith("gmail.com") || email.endsWith("hotmail.com");
    }

}
// END
