package factories;

import models.UsuarioAtualizarAdministradorModel;
import utils.DataFakerUtils;

import java.io.File;

public class UsuarioAtualizarAdministradorFactory {

    public static UsuarioAtualizarAdministradorModel atualizarAdministradorSemfoto() {
        UsuarioAtualizarAdministradorModel usuarioModelAdm = new UsuarioAtualizarAdministradorModel();

        usuarioModelAdm.setDataDeNascimento(DataFakerUtils.dataNascimento());
        usuarioModelAdm.setCidade(DataFakerUtils.cidade());
        usuarioModelAdm.setEstado(DataFakerUtils.estado());
        usuarioModelAdm.setPais(DataFakerUtils.pais());
        usuarioModelAdm.setFotoUrl(null);

        return usuarioModelAdm;
    }

    public static UsuarioAtualizarAdministradorModel atualizarAdministradorComfoto() {
        UsuarioAtualizarAdministradorModel usuarioModelAdm = new UsuarioAtualizarAdministradorModel();

        usuarioModelAdm.setDataDeNascimento(DataFakerUtils.dataNascimento());
        usuarioModelAdm.setCidade(DataFakerUtils.cidade());
        usuarioModelAdm.setEstado(DataFakerUtils.estado());
        usuarioModelAdm.setPais(DataFakerUtils.pais());
        usuarioModelAdm.setFotoUrl(null);

        return usuarioModelAdm;
    }

}
