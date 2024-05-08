package exercise.controller;

import static exercise.util.Util.isNullOrBlank;
import static exercise.util.ValidatorUtil.collectValidatorErrors;
import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import org.eclipse.jetty.util.security.SecurityUtils;

public class SessionsController {

    public static final String SESSION_STORE_LOGIN_KEY = "current_user";
    public static final String VALIDATOR_WRONG_USERNAME_OR_PASSWORD = "Wrong username or password";

    public static void build(Context context) {
        var loginPage = new LoginPage();
        context.render("build.jte", model("page", loginPage));
    }

    public static void create(Context context) {
        var loginValidator = context.formParamAsClass("name", String.class)
                .check(it -> !isNullOrBlank(it), "Имя пользователя не должно быть пустым")
                .check(UsersRepository::existsByName, VALIDATOR_WRONG_USERNAME_OR_PASSWORD);

        var passwordValidator = context.formParamAsClass("password", String.class)
                .check(it -> !isNullOrBlank(it), "Пароль не должен быть пустым")
                .check(password -> {
                    return UsersRepository.findByName(loginValidator.get())
                            .map(user -> user.getPassword().equals(Security.encrypt(password)))
                            .orElse(false);
                }, VALIDATOR_WRONG_USERNAME_OR_PASSWORD);

        try {
            collectValidatorErrors(loginValidator, passwordValidator);

            var login = loginValidator.get();

            context.sessionAttribute(SESSION_STORE_LOGIN_KEY, login);

            context.redirect(NamedRoutes.rootPath());
        } catch (ValidationException exception) {
            var login = context.formParam("login");

            var loginPage = new LoginPage(login, exception.getErrors());
            context.render("build.jte", model("page", loginPage));
        }
    }

    public static void destroy(Context context) {
        context.sessionAttribute(SESSION_STORE_LOGIN_KEY, null);
        context.redirect(NamedRoutes.rootPath());
    }
}