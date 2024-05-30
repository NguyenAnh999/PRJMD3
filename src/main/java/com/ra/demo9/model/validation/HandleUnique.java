package com.ra.demo9.model.validation;

import com.ra.demo9.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class HandleUnique implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserDao userDao;

    //existByEmail  -> true else false
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userDao.existByEmail(s);
    }
}
