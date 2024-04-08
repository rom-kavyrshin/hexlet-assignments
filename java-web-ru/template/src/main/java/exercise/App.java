package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users/{id}", context -> {
            int courseId = context.pathParamAsClass("id", Integer.class).getOrDefault(-1);

            var company = USERS.stream()
                    .filter((it) -> it.getId() == courseId)
                    .findFirst();

            company.ifPresentOrElse((it) -> {
                var page = new UserPage(it);
                context.render("users/show.jte", model("userPage", page));
            }, () -> {
                throw new NotFoundResponse("User not found");
            });
        });

        app.get("/users", context -> {
            UsersPage coursesPage = new UsersPage(USERS);
            context.render("users/index.jte", model("usersPage", coursesPage));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
