package net.crudclientespringboot.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoTelefone{
    RESIDENCIAL(0,"Residencial"),
    COMERCIAL(1,"Comercial"),
    CELULAR(2,"Celular");


    private final int valor;
    private final String descricao;

    TipoTelefone(int valor, String descricao){
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getDescricoes(){
        return new ArrayList<>(Arrays.asList(RESIDENCIAL.getDescricao(),
                                                   COMERCIAL.getDescricao(),
                                                    CELULAR.getDescricao()));
    }

}
