package com.ra.demo9.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = HandleUniqueUsername.class)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "user already exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
