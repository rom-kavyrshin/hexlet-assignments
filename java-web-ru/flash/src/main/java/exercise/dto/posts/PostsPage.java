package exercise.dto.posts;

import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import exercise.dto.BasePage;

import java.util.List;

// BEGIN
@Getter
@AllArgsConstructor
public class PostsPage extends BasePage {
    private final List<Post> postsList;

}
// END
