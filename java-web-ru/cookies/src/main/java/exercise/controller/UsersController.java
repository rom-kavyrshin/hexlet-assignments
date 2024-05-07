package exercise.controller;

import exercise.dto.users.BuildUserPage;
import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;

import static exercise.util.Util.isNullOrBlank;
import static exercise.util.ValidatorUtil.collectValidatorErrors;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;

import java.util.Locale;
import java.util.Objects;


public class UsersController {

    private static final String TOKEN_COOKIE_KEY = "token";

    public static void build(Context context) throws Exception {
        var buildUserPage = new BuildUserPage();
        context.render("users/build.jte", model("page", buildUserPage));
    }

    // BEGIN
    public static void create(Context context) {
        var firstNameValidator = context.formParamAsClass("firstName", String.class)
                .check(it -> !isNullOrBlank(it), "Имя не должно быть пустым");

        var lastNameValidator = context.formParamAsClass("lastName", String.class)
                .check(it -> !isNullOrBlank(it), "Фамилия не должна быть пустой");

        var emailValidator = context.formParamAsClass("email", String.class)
                .check(it -> !isNullOrBlank(it), "Email не должен быть пустым");

        var passwordValidator = context.formParamAsClass("password", String.class)
                .check(Objects::nonNull, "Пароль не должен быть пустым");

        try {
            collectValidatorErrors(firstNameValidator, lastNameValidator, emailValidator, passwordValidator);

            var firstName = firstNameValidator.get().trim();
            var lastName = lastNameValidator.get().trim();
            var email = emailValidator.get().trim().toLowerCase(Locale.ROOT);
            var password = passwordValidator.get();

            var secureToken = Security.generateToken();
            var user = new User(firstName, lastName, email, Security.encrypt(password), secureToken);
            UserRepository.save(user);

            context.cookie(TOKEN_COOKIE_KEY, secureToken);

            context.redirect(NamedRoutes.userPath(user.getId()));
        } catch (ValidationException exception) {
            var firstName = context.formParam("firstName");
            var lastName = context.formParam("lastName");
            var email = context.formParam("email");

            var buildUserPage = new BuildUserPage(firstName, lastName, email, exception.getErrors());
            context.render("users/build.jte", model("page", buildUserPage));
        }
    }

    public static void show(Context context) {
        long userId = context.pathParamAsClass("id", Long.class).getOrDefault(-1L);
        var secureToken = context.cookie(TOKEN_COOKIE_KEY);

        UserRepository.find(userId).ifPresentOrElse((it) -> {
            if (it.getToken().equals(secureToken)) {
                var page = new UserPage(it);
                context.render("users/show.jte", model("page", page));
            } else {
                context.redirect(NamedRoutes.buildUserPath());
            }
        }, () -> {
            throw new NotFoundResponse("User not found");
        });
    }
    // END
}
