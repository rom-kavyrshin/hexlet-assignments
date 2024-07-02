package exercise.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HasLettersValidator implements ConstraintValidator<HasLetters, String> {

    HasLetters hasLetters;

    @Override
    public void initialize(HasLetters constraintAnnotation) {
        this.hasLetters = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.contains("ab");
    }
}
