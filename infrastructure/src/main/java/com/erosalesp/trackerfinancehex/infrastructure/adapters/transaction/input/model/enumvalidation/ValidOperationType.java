package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.enumvalidation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OperationTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOperationType {
    String message() default "Tipo de operación inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
