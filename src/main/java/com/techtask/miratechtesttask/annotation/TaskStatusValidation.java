package com.techtask.miratechtesttask.annotation;

import com.techtask.miratechtesttask.validation.TaskStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, TYPE, PARAMETER})
@Retention(RUNTIME)
@Documented
@NotNull(message = "Value cannot be null")
@Constraint(validatedBy = TaskStatusValidator.class)
public @interface TaskStatusValidation {
    Class<? extends Enum<?>> enumClazz();
    String message() default "Value is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
