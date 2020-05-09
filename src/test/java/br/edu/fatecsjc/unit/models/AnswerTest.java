package br.edu.fatecsjc.unit.models;

import br.edu.fatecsjc.factories.AnswerFactory;
import br.edu.fatecsjc.models.Answer;
import br.edu.fatecsjc.utils.TestUtil;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.Assert.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class AnswerTest extends TestUtil {

    @Test
    public void answerTestPojoConstructors() {

        final Class<?> classUnderTest = Answer.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR).areWellImplemented();
    }

    @Test
    public void answerTestPojoGetters() {

        final Class<?> classUnderTest = Answer.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER).areWellImplemented();
    }

    @Test
    public void answerTestPojoSetters() {

        final Class<?> classUnderTest = Answer.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.SETTER).areWellImplemented();
    }

    @Test
    public void answerTestPojoHashCode() {

        final Class<?> classUnderTest = Answer.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.HASH_CODE).areWellImplemented();
    }

    @Test
    public void answerTestPojoEquals() {

        final Class<?> classUnderTest = Answer.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.EQUALS).areWellImplemented();
    }

    @Test
    public void answerTestPojoToString() {

        final Class<?> classUnderTest = Answer.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.TO_STRING).areWellImplemented();
    }

    @Test
    public void answerShouldBeValid() {

        Answer validAnswer = AnswerFactory.validAnswer(new Answer());
        assertEquals(0, getErrorSize(validAnswer));
    }

    @Test
    public void answerShouldNotBeValidWithAnswerEqualsNull() {

        Answer answer = AnswerFactory.validAnswer(new Answer());

        answer.setAnswer(null);
        assertEquals(1, getErrorSize(answer));
        assertEquals("must not be null", getErrorMessage(answer));
    }

    @Test
    public void answerShouldNotBeValidWithCorrectEqualsNull() {

        Answer answer = AnswerFactory.validAnswer(new Answer());

        answer.setCorrect(null);
        assertEquals(1, getErrorSize(answer));
        assertEquals("must not be null", getErrorMessage(answer));
    }

    @Test
    public void answerShouldNotBeValidWithQuestiontEqualsNull() {

        Answer answer = AnswerFactory.validAnswer(new Answer());

        answer.setQuestion(null);
        assertEquals(1, getErrorSize(answer));
        assertEquals("must not be null", getErrorMessage(answer));
    }
}
