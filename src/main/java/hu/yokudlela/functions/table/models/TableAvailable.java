package hu.yokudlela.functions.table.models;


import hu.yokudlela.functions.table.TableRepository;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;
import java.util.Optional;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TableAvailableContainer.class)
@Constraint(validatedBy = TableAvailableValidator.class)
public @interface TableAvailable {

    String message();
    String value();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@interface TableAvailableContainer {

    TableAvailable[] value();
}

@Slf4j
class TableAvailableValidator implements ConstraintValidator<TableAvailable, Long> {
    String message;

    String value;
    Class<?>[] groups;

    @Autowired
    private TableRepository repoTable;

    @Override
    public void initialize(TableAvailable constraintAnnotation) {
        value = constraintAnnotation.value();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<TableEntity> tbl = this.repoTable.findById(value);
        if(this.value.isEmpty() ||
           (tbl.isPresent()  && tbl.get().isAvailable()==Boolean.parseBoolean(this.value))){
            return true;
        }
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}
