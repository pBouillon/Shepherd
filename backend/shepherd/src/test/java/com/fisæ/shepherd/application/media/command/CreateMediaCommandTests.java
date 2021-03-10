package com.fisæ.shepherd.application.media.command;

import com.fisæ.shepherd.application.ConstraintValidatorTests;
import com.fisæ.shepherd.domain.entity.Media;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test suite for {@link CreateMediaCommand}
 */
public class CreateMediaCommandTests extends ConstraintValidatorTests {

    /**
     * Create a command that is valid and pass the validators
     *
     * @return The command
     */
    private CreateMediaCommand getValidCommand() {
        CreateMediaCommand command = new CreateMediaCommand();

        command.setName("A completely valid name");
        command.setDescription("A valid description too");

        return command;
    }

    @Test
    public void givenABlankDescription_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        CreateMediaCommand command = getValidCommand();
        command.setDescription("     ");

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(2, constraintViolations.size());

        assertTrue(constraintViolationsMessages.contains("must not be blank"));

        assertTrue(constraintViolationsMessages.contains(
                "size must be between " + Media.DESCRIPTION_MIN_LENGTH + " and " + Media.DESCRIPTION_MAX_LENGTH));
    }

    @Test
    public void givenABlankName_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        CreateMediaCommand command = getValidCommand();
        command.setName("     ");

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must not be blank", constraintViolationsMessages.get(0));
    }

    @Test
    public void givenAName_WhenCreatingTheCommand_ThenTheValidationShouldPass() {
        CreateMediaCommand command = getValidCommand();

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenAnEmptyDescription_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        CreateMediaCommand command = getValidCommand();
        command.setDescription("");

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(2, constraintViolations.size());

        assertTrue(constraintViolationsMessages.contains("must not be blank"));

        assertTrue(constraintViolationsMessages.contains(
                "size must be between " + Media.DESCRIPTION_MIN_LENGTH + " and " + Media.DESCRIPTION_MAX_LENGTH));
    }

    @Test
    public void givenAnEmptyName_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        CreateMediaCommand command = getValidCommand();
        command.setName("");

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(2, constraintViolations.size());

        assertTrue(constraintViolationsMessages.contains("must not be blank"));

        assertTrue(constraintViolationsMessages.contains(
                "size must be between " + Media.NAME_MIN_LENGTH + " and " + Media.NAME_MAX_LENGTH));
    }

    @ValueSource(strings = {
            "Not a url",
            "not@a.url"
    })
    @ParameterizedTest
    public void givenAnInvalidUrl_WhenCreatingTheCommand_ThenTheValidationShouldFail(String rawUrl) {
        CreateMediaCommand command = getValidCommand();
        command.setWebsite(Optional.of(rawUrl));

        Set<ConstraintViolation<CreateMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertTrue(constraintViolationsMessages.contains("must be a valid URL"));
    }

}
