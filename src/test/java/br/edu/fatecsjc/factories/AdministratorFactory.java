package br.edu.fatecsjc.factories;

import br.edu.fatecsjc.models.Administrator;
import br.edu.fatecsjc.models.enums.AuthorityName;

public class AdministratorFactory {

    public static Administrator validAdministrator() {

        Administrator validAdministrator = new Administrator();

        AccountFactory.validAccount(validAdministrator);
        validAdministrator.getAuthorities().add(AuthorityName.ADMINISTRATOR.getCode());


        return validAdministrator;
    }
}
