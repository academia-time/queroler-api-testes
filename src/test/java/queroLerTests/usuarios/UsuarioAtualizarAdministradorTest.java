package queroLerTests.usuarios;

import baseTest.BaseTest;
import clients.UsuarioClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import factories.UsuarioAtualizarAdministradorFactory;
import io.restassured.response.Response;
import models.UsuarioAtualizarAdministradorModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import report.Setup;
import utils.DataFakerUtils;
import utils.UsuarioHelper;

import java.io.File;
import java.io.IOException;

@ExtendWith(Setup.class)
public class UsuarioAtualizarAdministradorTest extends BaseTest {

    @Test
    public void deveAtualizarDadosDoAdministrador() throws JsonProcessingException {
        String token = UsuarioHelper.loginAdministrador();

        UsuarioAtualizarAdministradorModel usuarioModel = UsuarioAtualizarAdministradorFactory.atualizarAdministradorSemfoto();

        Response responseUsuarioAtualizar = UsuarioClient.usuarioAtualizarAdministrador(token, usuarioModel);

        responseUsuarioAtualizar
                .then()
                .log().body()
                .statusCode(204);

    }

    @Test
    public void deveAtualizarDadosDoAdministradorComFoto() throws IOException {
        String token = UsuarioHelper.loginAdministrador();

        UsuarioAtualizarAdministradorModel usuarioModel = UsuarioAtualizarAdministradorFactory.atualizarAdministradorComfoto();
        File imagem = DataFakerUtils.fotoPerfil();
        Response responseUsuarioAtualizar = UsuarioClient.usuarioAtualizarAdministradorComFoto(token, usuarioModel, imagem);

        responseUsuarioAtualizar
                .then()
                .log().body()
                .statusCode(204);

    }

}
