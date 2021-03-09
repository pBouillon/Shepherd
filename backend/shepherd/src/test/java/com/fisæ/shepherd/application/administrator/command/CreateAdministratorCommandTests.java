package com.fisæ.shepherd.application.administrator.command;

import com.fisæ.shepherd.application.media.ConstraintValidatorTests;
import com.fisæ.shepherd.domain.entity.Administrator;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test suite for {@link CreateAdministratorCommand}
 */
public class CreateAdministratorCommandTests extends ConstraintValidatorTests {

    @Test
    public void givenABlankNickname_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        CreateAdministratorCommand command = new CreateAdministratorCommand();
        command.setNickname("     ");

        Set<ConstraintViolation<CreateAdministratorCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must not be blank", constraintViolationsMessages.get(0));
    }

    @Test
    public void givenANickname_WhenCreatingTheCommand_ThenTheValidationShouldPass() {
        CreateAdministratorCommand command = new CreateAdministratorCommand();
        command.setNickname("A completely valid name");

        Set<ConstraintViolation<CreateAdministratorCommand>> constraintViolations = validator.validate(command);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenAnEmptyNickname_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        CreateAdministratorCommand command = new CreateAdministratorCommand();
        command.setNickname("");

        Set<ConstraintViolation<CreateAdministratorCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(2, constraintViolations.size());

        assertTrue(constraintViolationsMessages.contains("must not be blank"));

        assertTrue(constraintViolationsMessages.contains(
                "size must be between " + Administrator.NICKNAME_MIN_LENGTH
                        + " and " + Administrator.NICKNAME_MAX_LENGTH));
    }

}
