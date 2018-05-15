package pkCliente;

import pkAux.Mensagem;

import java.util.ArrayList;

public interface InterfaceGUICliente {
    //Metódos implementados pela GUI que são chamados pelo RodaCliente
    void conectar();
    void desconectar();
    void enviarMensagem(Mensagem msg);
    void exibirMensagem(Mensagem msg);
    void atualizarLista(Mensagem msg);
    void habilitarCampos();
    void desabilitarCampos();
    void alerta(String texto);
    ChatWindow addConversa(String chatWin);
}
