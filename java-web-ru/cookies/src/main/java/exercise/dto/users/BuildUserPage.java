package exercise.dto.users;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildUserPage {
    private String firstName;
    private String lastName;
    private String email;
    private Map<String, List<ValidationError<Object>>> validationErrors;
}