package exercise.dto.posts;

import exercise.dto.BasePage;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostPage extends BasePage {
    private Post post;
}
