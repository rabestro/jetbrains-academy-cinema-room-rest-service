package cinema.validation;

import cinema.model.Cinema;
import cinema.model.Seat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public record FreeSeatValidator(Cinema cinema) implements ConstraintValidator<FreeSeat, Seat> {
    @Override
    public boolean isValid(Seat value, ConstraintValidatorContext context) {
        return cinema.isFree(value);
    }
}
