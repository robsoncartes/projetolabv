package br.edu.fatecsjc.unit.models;

import br.edu.fatecsjc.factories.ExamFactory;
import br.edu.fatecsjc.models.Exam;
import br.edu.fatecsjc.utils.TestUtil;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.Assert.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class ExamTest extends TestUtil {

    @Test
    public void examTestPojoConstructors() {

        final Class<?> classUnderTest = Exam.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR).areWellImplemented();
    }

    @Test
    public void examTestPojoGetters() {

        final Class<?> classUnderTest = Exam.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER).areWellImplemented();
    }

    @Test
    public void examTestPojoSetters() {

        final Class<?> classUnderTest = Exam.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.SETTER).areWellImplemented();
    }

    @Test
    public void examTestPojoHashCode() {

        final Class<?> classUnderTest = Exam.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.HASH_CODE).areWellImplemented();
    }

    @Test
    public void examTestPojoEquals() {

        final Class<?> classUnderTest = Exam.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.EQUALS).areWellImplemented();
    }

    @Test
    public void examTestPojoToString() {

        final Class<?> classUnderTest = Exam.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.TO_STRING).areWellImplemented();
    }

    @Test
    public void examShouldBeValid() {

        Exam validExam = ExamFactory.validExam(new Exam());
        assertEquals(0, getErrorSize(validExam));
    }
}
