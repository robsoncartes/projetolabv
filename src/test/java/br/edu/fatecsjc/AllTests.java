package br.edu.fatecsjc;

import br.edu.fatecsjc.unit.models.AccountTest;
import br.edu.fatecsjc.unit.models.AdministratorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AccountTest.class, AdministratorTest.class})
public class AllTests {


}
