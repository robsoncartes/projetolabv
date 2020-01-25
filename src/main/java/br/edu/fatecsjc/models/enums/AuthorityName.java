package br.edu.fatecsjc.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthorityName {

    ADMINISTRATOR(1, "ROLE_ADMINISTRATOR"),
    USER(2, "ROLE_USER");

    private int code;
    private String description;

    public static AuthorityName toEnum(Integer code) {

        if (code == null)
            return null;

        for (AuthorityName authorityName : AuthorityName.values()) {
            if (code.equals(authorityName.getCode()))
                return authorityName;
        }

        throw new IllegalArgumentException("Invalid id: " + code);
    }
}
