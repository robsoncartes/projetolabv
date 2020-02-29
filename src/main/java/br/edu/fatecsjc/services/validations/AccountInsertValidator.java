package br.edu.fatecsjc.services.validations;

import br.edu.fatecsjc.controllers.exceptions.FieldMessage;
import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class AccountInsertValidator implements ConstraintValidator<AccountInsert, Account> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void initialize(AccountInsert ann) {

    }

    @Override
    public boolean isValid(Account account, ConstraintValidatorContext context) {

        List<FieldMessage> messages = new ArrayList<>();

        Account obj = accountRepository.findByEmail(account.getEmail());

        if (obj != null)
            messages.add(new FieldMessage("email", "Email already exists"));

        for (FieldMessage e : messages) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return messages.isEmpty();
    }
}
