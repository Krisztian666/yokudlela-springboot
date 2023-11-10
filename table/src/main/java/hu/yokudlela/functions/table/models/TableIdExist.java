package hu.yokudlela.functions.table.models;

import hu.yokudlela.functions.table.TableRepository;
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
@Constraint(validatedBy = TableIdExistValidator.class)
public @interface TableIdExist {

    String message();// default "{EarlierDate.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

@Slf4j
class TableIdExistValidator implements ConstraintValidator<TableIdExist, Long> {
    String message;

    @Autowired
    private TableRepository repoTable;

    @Override
    public void initialize(TableIdExist constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(this.repoTable.existsById(value)){
            return true;
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}

