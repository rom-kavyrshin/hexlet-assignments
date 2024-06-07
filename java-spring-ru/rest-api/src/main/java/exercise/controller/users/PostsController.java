package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    private final List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> index(@PathVariable int id) {
        var result = posts.stream()
                .filter(it -> it.getUserId() == id)
                .toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create(@PathVariable int id, @RequestBody Post newPost) {
        newPost.setUserId(id);
        posts.add(newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }
}
// END
