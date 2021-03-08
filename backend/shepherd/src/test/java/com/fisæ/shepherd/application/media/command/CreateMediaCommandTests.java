package com.fisæ.shepherd.application.media.command;

import com.fisæ.shepherd.application.media.ConstraintValidatorTests;
import com.fisæ.shepherd.domain.entity.Media;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test suite for {@link CreateMediaCommand}
 */
public class CreateMediaCommandTests extends ConstraintValidatorTests {

    @Test
    public void givenABlankName_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        CreateMediaCommand command = new CreateMediaCommand();
        command.setName("     ");

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must not be blank", constraintViolationsMessages.get(0));
    }

    @Test
    public void givenAName_WhenCreatingTheCommand_ThenTheValidationShouldPass() {
        CreateMediaCommand command = new CreateMediaCommand();
        command.setName("A completely valid name");

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenAnEmptyName_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
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
