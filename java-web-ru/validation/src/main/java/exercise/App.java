package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;

import static exercise.Util.isNullOrBlank;
import static exercise.ValidatorUtil.collectValidatorErrors;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;
import org.eclipse.jetty.http.HttpStatus;

public final class App {

    public static final String VALIDATOR_TITLE_LENGTH_ERROR = "Название не должно быть короче двух символов";
    public static final String VALIDATOR_TITLE_NOT_UNIQUE_ERROR = "Статья с таким названием уже существует";
    public static final String VALIDATOR_CONTENT_LENGTH_ERROR = "Статья должна быть не короче 10 символов";

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/articles/build", context -> {
            var buildArticlePage = new BuildArticlePage();
            context.render("articles/build.jte", model("page", buildArticlePage));
        });

        app.post("/articles", context -> {
            var titleValidator = context.formParamAsClass("title", String.class)
                    .check(it -> !isNullOrBlank(it) && it.trim().length() >= 2, VALIDATOR_TITLE_LENGTH_ERROR)
                    .check(it -> !ArticleRepository.existsByTitle(it), VALIDATOR_TITLE_NOT_UNIQUE_ERROR);

            var contentValidator = context.formParamAsClass("content", String.class)
                    .check(it -> !isNullOrBlank(it) && it.trim().length() >= 10, VALIDATOR_CONTENT_LENGTH_ERROR);

            try {
                collectValidatorErrors(titleValidator, contentValidator);

                var name = titleValidator.get().trim();
                var description = contentValidator.get().trim();

                var course = new Article(name, description);
                ArticleRepository.save(course);

                context.redirect("/articles");
            } catch (ValidationException exception) {
                var title = context.formParam("title");
                var content = context.formParam("content");

                var buildArticlePage = new BuildArticlePage(title, content, exception.getErrors());
                context.render("articles/build.jte", model("page", buildArticlePage)).status(HttpStatus.UNPROCESSABLE_ENTITY_422);
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
