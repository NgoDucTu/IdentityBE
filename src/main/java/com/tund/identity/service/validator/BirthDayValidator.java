package com.tund.identity.service.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BirthDayValidator implements ConstraintValidator<BirthDayAnnotation, LocalDate> {
    private int min;

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(localDate)) return true;
        long old = ChronoUnit.YEARS.between(localDate, LocalDate.now());
        return old >= min;
    }

    @Override
    public void initialize(BirthDayAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }
}
