package queroLerTests.usuarios;

import baseTest.BaseTest;
import clients.DiarioClient;
import clients.LeituraClient;
import clients.UsuarioClient;
import factories.DiarioFactory;
import factories.LeituraStatusFactory;
import factories.LivroFactory;
import factories.UsuarioFactory;
import io.restassured.response.Response;
import models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import queroLerTests.livros.LivroCadastrarTest;
import report.Setup;
import utils.LivroHelper;
import utils.UsuarioHelper;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static utils.UsuarioHelper.logResposta;

@ExtendWith(Setup.class)
public class UsuarioComentariosTest extends BaseTest {

    @Test
    public void UsuarioIdComentario() {
        String token = UsuarioHelper.loginLeitor();

        Response responseUsuario = UsuarioClient.buscarUsuario(token);

        int usuarioId = responseUsuario.jsonPath().getInt("id");

        responseUsuario
                .then()
                .log().body()
                .statusCode(200);

        Response response = UsuarioClient.usuarioIdComentario(token, usuarioId);
        response
                .then()
                .log().body()
                .statusCode(200)
        ;

        logResposta("GET/usuarios/"+usuarioId+"/comentarios", response);

    }

    @Test
    public void UsuarioIdComentarioInexistente() {
        String token = UsuarioHelper.loginLeitor();

        Response responseUsuario = UsuarioClient.buscarUsuario(token);

        int usuarioId = -1;

        responseUsuario
                .then()
                .log().body()
                .statusCode(200);

        Response response = UsuarioClient.usuarioIdComentario(token, usuarioId);
        response
                .then()
                .log().body()
                .statusCode(200)
                .body(equalTo("[]"))
        ;

        logResposta("GET/usuarios/"+usuarioId+"/comentarios", response);

    }

    private DiarioCriado criarDiario(String token) throws IOException {
        LivroModel livroModel = LivroFactory.criarLivroIsbn13();
        Response responseLivro = LivroHelper.criarLivroCadastrar(token, livroModel);
        responseLivro
                .then()
                .log().body()
                .statusCode(201);
        int livroId = responseLivro.jsonPath().getInt("id");

        LeituraStatusModel leituraStatusModel = LeituraStatusFactory.criarLeituraLivroStatusQueroLer(livroId);
        Response responseLeitura = LeituraClient.criarLeituraStatus(token,leituraStatusModel);
        responseLeitura
                .then()
                .statusCode(201);

        DiarioModel diarioModel = DiarioFactory.criarDiarioLido(livroId);
        diarioModel.setPaginasLidas(livroModel.getNumeroDePaginas());
        Response responseDiario = DiarioClient.criarDiario(token, diarioModel);

        responseDiario
                .then()
                .statusCode(201);

        int diarioId = responseDiario.jsonPath().getInt("id");

        int paginaInicial = 1;
        int paginaFinal = livroModel.getNumeroDePaginas();

        return new DiarioCriado(diarioId, paginaInicial, paginaFinal);
    }

}
