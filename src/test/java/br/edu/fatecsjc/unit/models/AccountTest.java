package br.edu.fatecsjc.unit.models;

import br.edu.fatecsjc.factories.AccountFactory;
import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.utils.TestUtil;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.Assert.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import br.edu.fatecsjc.utils.TestUtil.*;

public class AccountTest extends TestUtil {

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

    @Test
    public void accountShouldBeValid() {

        Account validAccount = AccountFactory.validAccount(new Account());
        assertEquals(0, getErrorSize(validAccount));
    }
}
