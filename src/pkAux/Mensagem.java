package pkAux;

import pkServidor.*;
import java.util.*;
import java.awt.*;
import java.io.Serializable;

public class Mensagem implements Serializable {
    private String origem;
    private String destino;
    private TipoMensagem tipoMensagem;
    private Object conteudoMensagem;
    private Date timeStamp;

    {
        this.timeStamp = new Date();
    }

    public TipoMensagem getTipoMsg() {
        return this.tipoMensagem;
    }
    public Object getMensagem() {
        return this.conteudoMensagem;
    }
    public String getDestino() {
        return this.destino;
    }
    public Date getTimeStamp() {
        return this.timeStamp;
    }
    public Mensagem(String clienteRemovido, ArrayList<String> clienteAtivos) {
        this.origem = clienteRemovido;
        this.conteudoMensagem = clienteAtivos;
        this.tipoMensagem = TipoMensagem.LISTA_CLIENTES;
    }
    public Mensagem(String nomeUsuario, TipoMensagem tpMsg) {
        this.origem = nomeUsuario;
        this.tipoMensagem = tpMsg;
    }
    public Mensagem(String origem, String destino, String texto) {
        this.origem = origem;
        this.destino = destino;
        this.tipoMensagem = TipoMensagem.TEXTO;
        this.conteudoMensagem = texto;
    }
    public Mensagem(String origem, String destino, Image img) {
        this.origem = origem;
        this.destino = destino;
        this.tipoMensagem = TipoMensagem.IMG;
        this.conteudoMensagem = img;
    }
    public Mensagem(TipoMensagem tpMsg) {
        this.tipoMensagem = tpMsg;
    }
    public String getOrigem() {
        return origem;
    }
}