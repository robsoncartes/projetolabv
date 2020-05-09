package br.edu.fatecsjc.unit.models;

import br.edu.fatecsjc.models.Activity;
import br.edu.fatecsjc.utils.TestUtil;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class ActivityTest extends TestUtil {

    @Test
    public void activityTestPojoConstructors() {

        final Class<?> classUnderTest = Activity.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR).areWellImplemented();
    }

    @Test
    public void activityTestPojoGetters() {

        final Class<?> classUnderTest = Activity.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER).areWellImplemented();
    }

    @Test
    public void activityTestPojoSetters() {

        final Class<?> classUnderTest = Activity.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.SETTER).areWellImplemented();
    }

    @Test
    public void activityTestPojoHashCode() {

        final Class<?> classUnderTest = Activity.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.HASH_CODE).areWellImplemented();
    }

    @Test
    public void activityTestPojoEquals() {

        final Class<?> classUnderTest = Activity.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.EQUALS).areWellImplemented();
    }

    @Test
    public void activityTestPojoToString() {

        final Class<?> classUnderTest = Activity.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.TO_STRING).areWellImplemented();
    }
}
