package exercise.controller;

import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

import java.util.List;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostsController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping(path = "")
    public List<PostDTO> index() {
        return postRepository.findAll().stream()
                .map(this::mapPostToDTO)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id) {
        return postRepository.findById(id)
                .map(this::mapPostToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    private PostDTO mapPostToDTO(Post post) {
        var commentsDto = mapCommentsToDto(commentRepository.findByPostId(post.getId()));

        var result = new PostDTO();
        result.setId(post.getId());
        result.setTitle(post.getTitle());
        result.setBody(post.getBody());
        result.setComments(commentsDto);
        return result;
    }

    private List<CommentDTO> mapCommentsToDto(List<Comment> comments) {
        return comments.stream()
                .map(this::mapCommentToDto)
                .toList();
    }

    private CommentDTO mapCommentToDto(Comment comment) {
        var result = new CommentDTO();
        result.setId(comment.getId());
        result.setBody(comment.getBody());
        return result;
    }
}

// END
