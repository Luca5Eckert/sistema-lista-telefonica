package com.sistema.lista.telefonica.model.valueobjects;

import com.sistema.lista.telefonica.exception.contato.TelefoneBlankException;
import org.apache.commons.lang3.math.NumberUtils;

public class Telefone {

    private final String value;

    public Telefone(String value) {
        if(value.isBlank()){
            throw new TelefoneBlankException("Telefone não pode ser branco");
        }
        if(!NumberUtils.isCreatable(value)){
            throw new TelefoneBlankException("Telefone precisa ser apenas digitos");
        }
        if(value.length() != 9){
            throw new TelefoneBlankException("Telefone precisa ter 9 digitos exatos");
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
