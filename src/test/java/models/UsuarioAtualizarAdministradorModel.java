package models;

import lombok.Data;

@Data
public class UsuarioAtualizarAdministradorModel {

    private String dataDeNascimento;
    private String cidade;
    private String estado;
    private String pais;
    private byte[] fotoUrl;

}
