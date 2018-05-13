package pkCliente;

import pkAux.Mensagem;

import java.util.ArrayList;

public interface InterfaceGUICliente {
    void conectar();
    void desconectar();
    void enviarMensagem(Mensagem msg);
    void exibirMensagem(Mensagem msg);
    void atualizarLista(Mensagem msg);
    void atualizaConversas();
    void habilitarCampos();
    void desabilitarCampos();
    void alerta(String texto);
    ChatWindow addConversa(String chatWin);
}
