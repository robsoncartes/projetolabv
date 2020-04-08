package utils;

import org.junit.Before;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;

public class TestUtil {
    @Before
    public void beforeSetLocale() {
        Locale.setDefault(new Locale("en", "EN"));
    }

    protected Set<ConstraintViolation<Object>> getErrorObject(Object object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        return validator.validate(object);
    }

    protected int getErrorSize(Object object) {
        Set<ConstraintViolation<Object>> constraintViolations = getErrorObject(object);
        return constraintViolations.size();
    }

    protected String getErrorMessage(Object object) {
        if (getErrorObject(object).iterator().hasNext()) {
            return getErrorObject(object).iterator().next().getMessage();
        }
        return "is a valid object";
    }
}
