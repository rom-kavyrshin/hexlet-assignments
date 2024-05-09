package exercise.controller;

import static exercise.util.Util.isNullOrBlank;
import static exercise.util.ValidatorUtil.collectValidatorErrors;
import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.FlashMessage;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

import java.util.Locale;
import java.util.Objects;

public class PostsController {

    public static final String SESSION_STORE_FLASH_MESSAGE_KEY = "FLASH_MESSAGE";

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN

    public static void index(Context context) {
        var postPage = new PostsPage(PostRepository.getEntities());
        postPage.setFlashMessage(context.consumeSessionAttribute(SESSION_STORE_FLASH_MESSAGE_KEY));
        context.render("posts/index.jte", model("page", postPage));
    }

    public static void create(Context context) {
        var nameValidator = context.formParamAsClass("name", String.class)
                .check(it -> !isNullOrBlank(it), "Имя не должно быть пустым")
                .check(it -> it.trim().length() >= 2, "Длина имени должна быть не короче двух символов");

        var bodyValidator = context.formParamAsClass("body", String.class)
                .check(it -> !isNullOrBlank(it), "Тело не должно быть пустым");

        try {
            collectValidatorErrors(nameValidator, bodyValidator);

            var name = nameValidator.get().trim();
            var body = bodyValidator.get().trim();

            var post = new Post(name, body);
            PostRepository.save(post);

            context.sessionAttribute(SESSION_STORE_FLASH_MESSAGE_KEY, new FlashMessage("Пост был успешно создан!", true));
            context.redirect(NamedRoutes.postsPath());
        } catch (ValidationException exception) {
            var name = context.formParam("name");
            var email = context.formParam("body");

            var buildPostPage = new BuildPostPage(name, email, exception.getErrors());
            context.sessionAttribute(SESSION_STORE_FLASH_MESSAGE_KEY, new FlashMessage("Ошибка создания поста", false));
            buildPostPage.setFlashMessage(context.consumeSessionAttribute(SESSION_STORE_FLASH_MESSAGE_KEY));
            context.render("posts/build.jte", model("page", buildPostPage));
        }
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
