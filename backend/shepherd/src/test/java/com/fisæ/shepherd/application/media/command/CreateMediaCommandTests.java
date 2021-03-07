package com.fisæ.shepherd.application.media.command;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.fisæ.shepherd.domain.entity.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test suite for {@link CreateMediaCommand}
 */
public class CreateMediaCommandTests {

    /**
     * Instance of the validator to verify the constraints of the command
     */
    private static Validator validator;

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
     *
     * @return The list of the messages associated to each constraint violation
     */
    private static List<String> getConstraintViolationMessages(
            Set<ConstraintViolation<CreateMediaCommand>> constraintViolations) {
        return constraintViolations
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    @Test
    public void givenABlankName_WhenCreatingTheQuery_ThenTheValidationShouldFail() {
        CreateMediaCommand command = new CreateMediaCommand();
        command.setName("     ");

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());

        assertTrue(constraintViolationsMessages.contains("must not be blank"));
    }

    @Test
    public void givenAName_WhenCreatingTheQuery_ThenTheValidationShouldPass() {
        CreateMediaCommand command = new CreateMediaCommand();
        command.setName("A completely valid name");

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenAnEmptyName_WhenCreatingTheQuery_ThenTheValidationShouldFail() {
        CreateMediaCommand command = new CreateMediaCommand();
        command.setName("");

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(2, constraintViolations.size());

        assertTrue(constraintViolationsMessages.contains("must not be blank"));

        assertTrue(constraintViolationsMessages.contains(
                "size must be between " + Media.NAME_MIN_LENGTH + " and " + Media.NAME_MAX_LENGTH));
    }

}
