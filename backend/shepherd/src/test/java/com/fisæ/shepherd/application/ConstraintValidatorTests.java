package com.fisæ.shepherd.application;

import org.junit.jupiter.api.BeforeEach;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Base class for any test class that aims to test Hibernate validators
 */
public abstract class ConstraintValidatorTests {

    /**
     * Instance of the validator to verify the constraints of the command
     */
    protected Validator validator;

    /**
     * Setup method, executed before each test
     */
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Convert a set of {@link ConstraintViolation} to a list of the associated messages
     *
     * @param constraintViolations Set of constraint violations to convert
     * @param <T> Type of the original set of constraint violations
     *
     * @return The list of the messages associated to each constraint violation
     */
    protected static <T> List<String> getConstraintViolationMessages(
            Set<ConstraintViolation<T>> constraintViolations) {
        return constraintViolations
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

}
