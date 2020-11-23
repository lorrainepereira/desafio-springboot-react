package net.crudclientespringboot.enums;

import java.io.Serializable;

public enum Role implements Serializable {

    ADMIN("ADMIN", "Administrador"),
    COMUM("COMUM", "Comum");

    Role(String key, String name) {
        this.key = key;
        this.name = name;
    };

    private String key;
    private String name;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}

