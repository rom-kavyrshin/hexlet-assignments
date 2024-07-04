package exercise.dto;

import jakarta.validation.constraints.PositiveOrZero;
import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
public class CarUpdateDTO {

    private JsonNullable<String> model;

    @NotNull
    private JsonNullable<String> manufacturer;

    @NotNull
    @PositiveOrZero
    private JsonNullable<Integer> enginePower;
}
// END
