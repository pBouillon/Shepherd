package com.fisæ.shepherd.application.media.command;

import com.fisæ.shepherd.application.ConstraintValidatorTests;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for {@link DeleteMediaCommand}
 */
@SpringBootTest
public class DeleteMediaCommandTests extends ConstraintValidatorTests {

    @Test
    public void givenANegativeId_WhenCreatingTheCommand_ThenTheValidationShouldFail() {
        DeleteMediaCommand command = new DeleteMediaCommand();
        command.setId(-1L);

        Set<ConstraintViolation<DeleteMediaCommand>> constraintViolations = validator.validate(command);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must be greater than or equal to 1", constraintViolationsMessages.get(0));
    }

    @Test
    public void givenAPositiveId_WhenCreatingTheCommand_ThenTheValidationShouldPass() {
        DeleteMediaCommand command = new DeleteMediaCommand();
        command.setId(1L);

        Set<ConstraintViolation<DeleteMediaCommand>> constraintViolations = validator.validate(command);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenTheDefaultCommand_WhenValidatingIt_ThenTheValidationShouldPass() {
        DeleteMediaCommand command = new DeleteMediaCommand();

        Set<ConstraintViolation<DeleteMediaCommand>> constraintViolations = validator.validate(command);

        assertEquals(0, constraintViolations.size());
    }

}
