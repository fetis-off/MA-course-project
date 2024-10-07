package org.project.springweb.validation.book;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {
    private static final String PATTERN_OF_ISBN = "^(?:\\d{9}[\\dXx]|\\d{13})$";

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        return isbn != null && Pattern.compile(PATTERN_OF_ISBN).matcher(isbn).matches();
    }
}
