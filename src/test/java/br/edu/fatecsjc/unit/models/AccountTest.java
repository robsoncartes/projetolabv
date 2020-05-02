package br.edu.fatecsjc.unit.models;

import br.edu.fatecsjc.models.Account;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class AccountTest {

    @Test
    public void accountTestPojoConstructors() {

        final Class<?> classUnderTest = Account.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR).areWellImplemented();
    }

    @Test
    public void accountTestPojoGetters() {

        final Class<?> classUnderTest = Account.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER).areWellImplemented();
    }

    @Test
    public void accountTestPojoSetters() {

        final Class<?> classUnderTest = Account.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.SETTER).areWellImplemented();
    }
}
