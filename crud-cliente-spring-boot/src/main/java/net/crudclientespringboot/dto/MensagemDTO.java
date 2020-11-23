package net.crudclientespringboot.dto;

import net.crudclientespringboot.enums.TipoMensagem;

import java.io.Serializable;

public class MensagemDTO implements Serializable {

    private String mensagem;
    private TipoMensagem tipoMensagem;

    public MensagemDTO(String mensagem, TipoMensagem tipoMensagem){
        this.mensagem = mensagem;
        this.tipoMensagem = tipoMensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public TipoMensagem getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(TipoMensagem tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }
}
