package com.mall.common.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 校验工具----实现的功能与hibernate-validator一致
 *
 * @author gp6
 * @date 2018-10-16
 */
public class ValidateUtil {

    /**
     * 它是线程安全的
     */
    private static Validator validator;

    static {
        // 通过工厂得到校验器
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        // 得到的校验器validator和直接使用hibernate-validator直接校验一致
        validator = factory.getValidator();
    }

    public static <T> void validate(T t) throws Exception {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (constraintViolations.size() > 0) {
            StringBuilder validateError = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                validateError.append(constraintViolation.getMessage()).append(";");
            }
            throw new Exception(validateError.toString());
        }
    }

} 
