package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    public static int POSTS_COUNT_ON_PAGE = 5;

    // BEGIN
    public static void index(Context context) {
        var page = context.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var posts = PostRepository.getEntities();
        List<Post> result = List.of();

        var firstItemIndex = (page - 1) * POSTS_COUNT_ON_PAGE;
        var toIndex = firstItemIndex + Math.min(POSTS_COUNT_ON_PAGE, posts.size() - firstItemIndex);
        if (firstItemIndex >= 0 && firstItemIndex < posts.size()) {
            result = posts.subList(firstItemIndex, toIndex);
        }

        PostsPage postsPage = new PostsPage(page, posts.size() > toIndex, result);
        context.render("posts/index.jte", model("page", postsPage));
    }

    public static void show(Context context) {
        long postId = context.pathParamAsClass("id", Long.class).getOrDefault(-1L);

        var courseOptional = PostRepository.find(postId);

        courseOptional.ifPresentOrElse((it) -> {
            var page = new PostPage(it);
            context.render("posts/show.jte", model("page", page));
        }, () -> {
            throw new NotFoundResponse("Page not found");
        });
    }
    // END
}
