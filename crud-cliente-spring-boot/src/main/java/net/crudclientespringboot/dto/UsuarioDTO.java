package net.crudclientespringboot.dto;

import net.crudclientespringboot.enums.Role;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
    private String login;
    private String senha;
    private Role role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
