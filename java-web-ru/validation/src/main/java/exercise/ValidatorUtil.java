package exercise;

import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;
import io.javalin.validation.Validator;

import java.util.HashMap;
import java.util.List;

public class ValidatorUtil {

    public static void collectValidatorErrors(Validator... validators) throws ValidationException {
        var resultMap = new HashMap<String, List<ValidationError<Object>>>();
        for (var validator : validators) {
            resultMap.putAll(validator.errors());
        }

        if (!resultMap.isEmpty()) {
            throw new ValidationException(resultMap);
        }
    }
}
