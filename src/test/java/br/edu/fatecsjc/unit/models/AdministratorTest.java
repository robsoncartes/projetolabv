package br.edu.fatecsjc.unit.models;

import br.edu.fatecsjc.models.Administrator;
import br.edu.fatecsjc.utils.TestUtil;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.FieldPredicate.exclude;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class AdministratorTest extends TestUtil {

    @Test
    public void administratorTestPojoConstructors() {

        final Class<?> classUnderTest = Administrator.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR).areWellImplemented();
    }

    @Test
    public void administratorTestPojoGetters() {

        final Class<?> classUnderTest = Administrator.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER).areWellImplemented();
    }

    @Test
    public void administratorTestPojoSetters() {

        final Class<?> classUnderTest = Administrator.class;

        assertPojoMethodsFor(classUnderTest, exclude("password")).testing(Method.SETTER).areWellImplemented();
    }

    @Test
    public void administratorTestPojoHashCode() {

        final Class<?> classUnderTest = Administrator.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.HASH_CODE).areWellImplemented();
    }

    @Test
    public void administratorTestPojoEquals() {

        final Class<?> classUnderTest = Administrator.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.EQUALS).areWellImplemented();
    }

    @Test
    public void administratorTestPojoToString() {

        final Class<?> classUnderTest = Administrator.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.TO_STRING).areWellImplemented();
    }
}
