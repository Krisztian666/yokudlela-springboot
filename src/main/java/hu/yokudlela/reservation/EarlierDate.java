package hu.yokudlela.reservation;


import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.annotation.*;
import java.time.LocalDateTime;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BeginForEndBeforeValidator.class)
public @interface EarlierDate {
    //    String[] value();
    String earlier();
    String later();

    String message();// default "{EarlierDate.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class BeginForEndBeforeValidator implements ConstraintValidator<EarlierDate, Object> {
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    String earlier;
    String later;
    String message;

    @Override
    public void initialize(EarlierDate constraintAnnotation) {
        earlier = constraintAnnotation.earlier();
        later = constraintAnnotation.later();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(earlier!=null && later!=null){
            var begin = (LocalDateTime)PARSER.parseExpression(earlier).getValue(value);
            var end = (LocalDateTime)PARSER.parseExpression(later).getValue(value);
            if(begin!=null && end!=null && begin.isBefore(end)){
                return true;
            }
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}

