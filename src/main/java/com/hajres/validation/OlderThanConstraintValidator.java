package com.hajres.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.time.LocalDate;

public class OlderThanConstraintValidator implements ConstraintValidator<OlderThan, Date> {
    private int ageLimit;
    @Override
    public void initialize(OlderThan olderThan) {
        ageLimit = olderThan.value();
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if (date == null) return false;
        LocalDate localDate = date.toLocalDate();

        // if localDate comes after current date - age limit, it's younger than the age limit
        // and returns false. Otherwise it returns true.
        result = !localDate.isAfter(LocalDate.now().minusYears(ageLimit));
        return result;
    }
}
