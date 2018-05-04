package pkAux;

import java.io.Serializable;

public enum TipoMensagem implements Serializable{
    TEXTO,
    IMG,
    REQ_CONN,
    DC,
    CONN_OK,
    LISTA_CLIENTES
}