package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.enumvalidation;

import com.erosalesp.trackerfinancehex.domain.transaction.models.enums.OperationType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class OperationTypeValidator implements ConstraintValidator<ValidOperationType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && Arrays.stream(OperationType.values())
              .anyMatch(enumValue -> enumValue.name().equals(value));
    }
}
