package queroLerTests.leituras;

import baseTest.BaseTest;
import clients.DiarioClient;
import clients.LeituraClient;
import factories.DiarioFactory;
import factories.LeituraComentarioFactory;
import factories.LeituraStatusFactory;
import factories.LivroFactory;
import io.restassured.response.Response;
import models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import report.Setup;
import utils.LivroHelper;
import utils.UsuarioHelper;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(Setup.class)
public class LeituraCriarComentarioTest extends BaseTest {

    @Test
    public void criarComentarioDiarioDoLivroComSucesso() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();

        leituraComentarioModel.setPaginaInicial(diarioCriado.paginaInicial());
        leituraComentarioModel.setPaginaFinal(diarioCriado.paginaFinal());

        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void paginaInicialMaiorQuePaginaFinal() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();

        leituraComentarioModel.setPaginaInicial(diarioCriado.paginaFinal());
        leituraComentarioModel.setPaginaFinal(diarioCriado.paginaInicial());

        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(400)
                .body(equalTo("A página inicial deve ser menor que a página final."));
    }

    @Test
    public void paginaInicialEPaginaFinalEComentarioNulos() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.leituraTodosNulos();
        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(400)
                .body("comentario", equalTo("comentario é obrigatório."));
    }

    @Test
    public void criarComentarioComComentarioNulo() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();
        leituraComentarioModel.setComentario(null);
        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(400)
                .body("comentario", equalTo("comentario é obrigatório."));
    }

    @Test
    public void paginaFinalEComentarioNulos() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();
        leituraComentarioModel.setPaginaFinal(null);
        leituraComentarioModel.setComentario(null);
        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(400)
                .body("comentario", equalTo("comentario é obrigatório."));
    }

    @Test
    public void paginaInicialEComentarioNulo() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();
        leituraComentarioModel.setPaginaInicial(null);
        leituraComentarioModel.setComentario(null);
        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(400)
                .body("comentario", equalTo("comentario é obrigatório."));
    }

    @Test
    public void paginaInicialEPaginaFinalNulos() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();
        leituraComentarioModel.setPaginaInicial(null);
        leituraComentarioModel.setPaginaFinal(null);
        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(500);
    }

    @Test
    public void paginaInicialValorNegativo() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();
        leituraComentarioModel.setPaginaInicial(-1);
        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(400)
                .body("paginaInicial", equalTo("paginaInicial deve ser um valor positivo."));
    }

    @Test
    public void paginaFinalValorNegativo() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();
        leituraComentarioModel.setPaginaFinal(-1);
        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(400)
                .body("paginaFinal", equalTo("paginaFinal deve ser um valor positivo."));
    }

    @Test
    public void paginaInicialEPaginaFinalZero() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();
        leituraComentarioModel.setPaginaInicial(0);
        leituraComentarioModel.setPaginaFinal(0);

        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(400)
                .body("paginaFinal", equalTo("paginaFinal deve ser um valor positivo."),
                        "paginaInicial", equalTo("paginaInicial deve ser um valor positivo."));
    }

    @Test
    public void paginaFinalMaiorQueTotalPaginasLivro() throws IOException {
        String token = UsuarioHelper.loginLeitor();

        DiarioCriado diarioCriado = criarDiario(token);

        LeituraComentarioModel leituraComentarioModel = LeituraComentarioFactory.criarLeituraComentario();
        leituraComentarioModel.setPaginaInicial(diarioCriado.paginaInicial());
        leituraComentarioModel.setPaginaFinal(diarioCriado.paginaFinal()+1);

        Response responseLeituraComentario = LeituraClient.criarLeituraComentario(token, diarioCriado.diarioId(), leituraComentarioModel);
        responseLeituraComentario
                .then()
                .log().body()
                .statusCode(400);
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