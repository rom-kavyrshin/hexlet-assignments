package exercise.controller;

import exercise.dto.MainPage;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {

    public static void index(Context context) {
        String currentUser = context.sessionAttribute(SessionsController.SESSION_STORE_LOGIN_KEY);
        var rootPage = new MainPage(currentUser);
        context.render("index.jte", model("page", rootPage));
    }
}
