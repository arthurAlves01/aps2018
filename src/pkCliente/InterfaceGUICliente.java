package pkCliente;

import pkAux.Mensagem;

import java.util.ArrayList;

public interface InterfaceGUICliente {
    void conectar();
    void desconectar();
    void enviarMensagem(Mensagem msg);
    void exibirMensagem(Mensagem msg);
    void atualizarLista(ArrayList<String> clientes);
    void atualizaConversas();
    void habilitarCmapos();
    void desabilitarCampos();
}
