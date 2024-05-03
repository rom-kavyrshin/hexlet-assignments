package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;


// BEGIN
@AllArgsConstructor
@Getter
public class PostsPage {
    private long currentPage;
    private boolean hasNextPage;
    private List<Post> postList;
}
// END


