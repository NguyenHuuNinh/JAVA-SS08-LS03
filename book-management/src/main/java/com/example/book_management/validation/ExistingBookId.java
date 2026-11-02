package com.example.book_management.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookIdValidator.class)
@Documented
public @interface ExistingBookId {
    String message() default "Sách không tồn tại trong hệ thống";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}