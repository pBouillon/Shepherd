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
 * Unit test suite for {@link UpdateMediaCommand}
 */
public class UpdateMediaCommandTests extends ConstraintValidatorTests {

    @Test
    public void givenABlankName_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setName("     ");

        Set<ConstraintViolation<UpdateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must not be blank", constraintViolationsMessages.get(0));
    }

    @Test
    public void givenAName_WhenCreatingTheCommand_ThenTheValidationShouldPass() {
        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setName("A completely valid name");

        Set<ConstraintViolation<UpdateMediaCommand>> constraintViolations = validator.validate(command);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenAnEmptyName_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        UpdateMediaCommand command = new UpdateMediaCommand();
        command.setName("");

        Set<ConstraintViolation<UpdateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(2, constraintViolations.size());

        assertTrue(constraintViolationsMessages.contains("must not be blank"));

        assertTrue(constraintViolationsMessages.contains(
                "size must be between " + Media.NAME_MIN_LENGTH + " and " + Media.NAME_MAX_LENGTH));
    }

}
