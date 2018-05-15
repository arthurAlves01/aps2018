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
        //Cria o timestamp para todos os objetos criados
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
        //Construtor utilizado para enviar a lista de clientes => LISTA_CLIENTES
        this.origem = clienteRemovido;
        this.conteudoMensagem = clienteAtivos;
        this.tipoMensagem = TipoMensagem.LISTA_CLIENTES;
    }
    public Mensagem(String nomeUsuario, TipoMensagem tpMsg) {
        //Utilizado para solicitar validação do usuário pelo servidor => REQ_CONN
        this.origem = nomeUsuario;
        this.tipoMensagem = tpMsg;
    }
    public Mensagem(String origem, String destino, String texto) {
        //Utilizado para enviar mensagens de texto => TEXTO
        this.origem = origem;
        this.destino = destino;
        this.tipoMensagem = TipoMensagem.TEXTO;
        this.conteudoMensagem = texto;
    }
    public Mensagem(String origem, String destino, Image img) {
        //Utilizado para enviar mensagens de texto => IMG
        this.origem = origem;
        this.destino = destino;
        this.tipoMensagem = TipoMensagem.IMG;
        this.conteudoMensagem = img;
    }
    public Mensagem(TipoMensagem tpMsg) {
        //Utilizado para enviar solicitação de desconexão para o servidor ou cliente => DC
        //ou informar solicitação ok para o cliente => CONN_OK
        this.tipoMensagem = tpMsg;
    }
    public String getOrigem() {
        return origem;
    }
}