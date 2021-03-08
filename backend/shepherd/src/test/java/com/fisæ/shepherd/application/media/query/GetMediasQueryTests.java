package com.fisæ.shepherd.application.media.query;

import com.fisæ.shepherd.application.media.ConstraintValidatorTests;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for {@link GetMediasQuery}
 */
public class GetMediasQueryTests extends ConstraintValidatorTests {

    @Test
    public void givenANegativePageOffset_WhenCreatingTheQuery_ThenTheValidationShouldFail() {
        GetMediasQuery query = new GetMediasQuery();
        query.setPageId(-1);

        Set<ConstraintViolation<GetMediasQuery>> constraintViolations = validator.validate(query);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must be between 0 and 50", constraintViolationsMessages.get(0));
    }

    @Test
    public void givenANegativeNumberOfItemsPerPage_WhenCreatingTheQuery_ThenTheValidationShouldFail() {
        GetMediasQuery query = new GetMediasQuery();
        query.setItemsPerPages(-1);

        Set<ConstraintViolation<GetMediasQuery>> constraintViolations = validator.validate(query);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must be between 1 and 50", constraintViolationsMessages.get(0));
    }

    @Test
    public void givenANumberOfItemsPerPageNull_WhenCreatingTheQuery_ThenTheValidationShouldPass() {
        GetMediasQuery query = new GetMediasQuery();
        query.setItemsPerPages(0);

        Set<ConstraintViolation<GetMediasQuery>> constraintViolations = validator.validate(query);
        List<String> constraintViolationsMessages = getConstraintViolationMessages(constraintViolations);

        assertEquals(1, constraintViolations.size());
        assertEquals("must be between 1 and 50", constraintViolationsMessages.get(0));
    }

    @Test
    public void givenANumberOfPageOffsetNull_WhenCreatingTheQuery_ThenTheValidationShouldFail() {
        GetMediasQuery query = new GetMediasQuery();
        query.setPageId(0);

        Set<ConstraintViolation<GetMediasQuery>> constraintViolations = validator.validate(query);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void givenTheDefaultQuery_WhenValidatingIt_ThenTheValidationShouldPass() {
        GetMediasQuery query = new GetMediasQuery();

        Set<ConstraintViolation<GetMediasQuery>> constraintViolations = validator.validate(query);

        assertEquals(0, constraintViolations.size());
    }

}
