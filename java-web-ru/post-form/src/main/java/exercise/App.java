package exercise;

import exercise.util.Security;
import io.javalin.Javalin;
import java.util.List;
import java.util.Locale;

import static exercise.util.Util.isNullOrBlank;
import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.http.BadRequestResponse;
import io.javalin.rendering.template.JavalinJte;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/users/build", context -> {
            context.render("users/build.jte");
        });

        app.post("/users", context -> {
            var name = context.formParam("name");
            var lastname = context.formParam("lastname");
            var email = context.formParam("email");
            var password = context.formParam("password");

            if (isNullOrBlank(name)
                    || isNullOrBlank(lastname)
                    || isNullOrBlank(email)
                    || isNullOrBlank(password)
            ) {
                throw new BadRequestResponse();
            } else {
                name = StringUtils.capitalize(name.trim());
                lastname = StringUtils.capitalize(lastname.trim());
                email = email.trim().toLowerCase(Locale.ROOT);
                password = Security.encrypt(password);

                var user = new User(name, lastname, email, password);
                UserRepository.save(user);

                context.redirect("/users");
            }
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
