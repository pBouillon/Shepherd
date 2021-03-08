package com.fisæ.shepherd.application.media.query;

import com.fisæ.shepherd.application.media.ConstraintValidatorTests;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for {@link GetMediaQuery}
 */
public class GetMediaQueryTests extends ConstraintValidatorTests {

    @Test
    public void givenANegativeId_WhenCreatingTheQuery_ThenTheValidationShouldFail() {
        GetMediaQuery query = new GetMediaQuery();
        query.setId(-1L);

        Set<ConstraintViolation<GetMediaQuery>> constraintViolations = validator.validate(query);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must be greater than or equal to 1", constraintViolationsMessages.get(0));
    }

    @Test
    public void givenAPositiveId_WhenCreatingTheQuery_ThenTheValidationShouldPass() {
        GetMediaQuery query = new GetMediaQuery();
        query.setId(1L);

        Set<ConstraintViolation<GetMediaQuery>> constraintViolations = validator.validate(query);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenTheDefaultQuery_WhenValidatingIt_ThenTheValidationShouldFail() {
        GetMediaQuery query = new GetMediaQuery();

        Set<ConstraintViolation<GetMediaQuery>> constraintViolations = validator.validate(query);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must be greater than or equal to 1", constraintViolationsMessages.get(0));
    }

}
