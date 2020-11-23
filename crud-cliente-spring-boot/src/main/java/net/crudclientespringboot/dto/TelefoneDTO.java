package net.crudclientespringboot.dto;

import net.crudclientespringboot.enums.TipoTelefone;
import java.io.Serializable;

public class TelefoneDTO implements Serializable {

    private Long idTelefone;
    private TipoTelefone tipo;
    private Integer numero;

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}
