package br.edu.fatecsjc.unit.models;

import br.edu.fatecsjc.factories.QuestionFactory;
import br.edu.fatecsjc.models.Question;
import br.edu.fatecsjc.utils.TestUtil;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.Assert.assertEquals;
import static pl.pojo.tester.api.FieldPredicate.exclude;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class QuestionTest extends TestUtil {

    @Test
    public void questionTestPojoConstructors() {

        final Class<?> classUnderTest = Question.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR).areWellImplemented();
    }

    @Test
    public void questionTestPojoGetters() {

        final Class<?> classUnderTest = Question.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER).areWellImplemented();
    }

    @Test
    public void questionTestPojoSetters() {

        final Class<?> classUnderTest = Question.class;

        assertPojoMethodsFor(classUnderTest, exclude("assertion")).testing(Method.SETTER).areWellImplemented();
    }

    @Test
    public void questionTestPojoHashCode() {

        final Class<?> classUnderTest = Question.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.HASH_CODE).areWellImplemented();
    }

    @Test
    public void questionTestPojoEquals() {

        final Class<?> classUnderTest = Question.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.EQUALS).areWellImplemented();
    }

    @Test
    public void questionTestPojoToString() {

        final Class<?> classUnderTest = Question.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.TO_STRING).areWellImplemented();
    }

    @Test
    public void questionShouldBeValid() {

        Question validQuestion = QuestionFactory.validQuestion(new Question());
        assertEquals(0, getErrorSize(validQuestion));
    }
}
