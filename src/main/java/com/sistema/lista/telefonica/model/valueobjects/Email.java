package com.sistema.lista.telefonica.model.valueobjects;

import com.sistema.lista.telefonica.exception.contato.EmailBlankException;
import com.sistema.lista.telefonica.exception.contato.TelefoneBlankException;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final String value;


    public Email(String value) {
        if(value.isBlank()){
            throw new EmailBlankException("Email não pode ser branco");
        }
        if(!validEmail(value)){
            throw new EmailBlankException("Email precisa estar na sintaxe de: nome_usuario@gmail.com");
        }
        this.value = value;
    }

    private boolean validEmail(String value) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
        return matcher.matches();
    }

    public String getValue() {
        return value;
    }
}
