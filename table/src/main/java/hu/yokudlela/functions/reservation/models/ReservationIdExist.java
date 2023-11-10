package hu.yokudlela.functions.reservation.models;


import hu.yokudlela.functions.reservation.ReservationRepository;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ReservationIdExistValidator.class)
public @interface ReservationIdExist {

    String message();// default "{EarlierDate.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

@Slf4j
class ReservationIdExistValidator implements ConstraintValidator<ReservationIdExist, String> {
    String message;

    @Autowired
    private ReservationRepository repoReservation;

    @Override
    public void initialize(ReservationIdExist constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(this.repoReservation.existsById(value)){
            return true;
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}

