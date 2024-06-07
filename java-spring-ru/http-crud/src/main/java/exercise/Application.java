package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        var skip = (page - 1) * limit;
        return posts.stream().skip(skip).limit(limit).toList();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> show(@PathVariable String id) {
        return posts.stream().filter(it -> it.getId().equals(id)).findFirst();
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post newPost) {
        posts.add(newPost);
        return newPost;
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post patchPost) {
        var oldPostOptional = posts.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();

        oldPostOptional.ifPresent(it -> {
            it.setTitle(patchPost.getTitle());
            it.setBody(patchPost.getBody());
        });
        return patchPost;
    }

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(it -> it.getId().equals(id));
    }
    // END
}
