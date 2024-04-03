package exercise;

// BEGIN

// END

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        return Javalin.create((config) -> {
            config.bundledPlugins.enableDevLogging();
        });
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.get("/welcome", context -> context.result("Welcome to Hexlet!"));
        app.start(7070);
    }
}
