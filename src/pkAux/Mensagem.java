package pkAux;

import pkServidor.*;

import java.awt.*;
import java.io.Serializable;

public class Mensagem implements Serializable {
    private String destino;
    private String tipoMensagem;
    private Object conteudoMensagem;

    public String getTipoMsg() {
        return this.tipoMensagem;
    }
    public Object getMensagem() {
        return this.conteudoMensagem;
    }
    public String getDestino() {
        return this.destino;
    }
    public Mensagem(Object conteudoMensagem) {
        this.conteudoMensagem = conteudoMensagem;
    }
    public Mensagem(String destino, String texto) {
        this.destino = destino;
        this.tipoMensagem = "texto";
        this.conteudoMensagem = texto;
    }
    public Mensagem(String destino, Image img) {
        this.destino = destino;
        this.tipoMensagem = "img";
        this.conteudoMensagem = img;
    }
}
