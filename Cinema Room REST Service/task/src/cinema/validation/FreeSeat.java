package cinema.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FreeSeatValidator.class})
public @interface FreeSeat {
    String message() default "must be free seat";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
