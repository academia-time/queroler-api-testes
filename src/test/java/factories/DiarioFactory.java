package factories;

import models.DiarioModel;
import utils.DataFakerUtils;

public class DiarioFactory {

    public static DiarioModel criarDiarioLido(int livroId) {

        DiarioModel diario = new DiarioModel();

        diario.setLivroId(livroId);
        diario.setInicioDaLeitura(DataFakerUtils.dataInicio());
        diario.setTerminoDaLeitura(DataFakerUtils.dataAtual());
        diario.setPaginasLidas(DataFakerUtils.paginasLidas());
        diario.setNota(DataFakerUtils.nota());
        diario.setTituloDaResenha(DataFakerUtils.tituloResenha());
        diario.setResenha(DataFakerUtils.resenha());
        diario.setSpoiler(DataFakerUtils.spoiler());
        return diario;
    }

    public static DiarioModel criarDiarioLido(Integer livroId, Integer numeroDePaginas) {

        DiarioModel diario = new DiarioModel();

        diario.setLivroId(livroId);
        diario.setInicioDaLeitura(DataFakerUtils.dataInicio());
        diario.setTerminoDaLeitura(DataFakerUtils.dataAtual());
        diario.setPaginasLidas(numeroDePaginas-1);
        diario.setNota(DataFakerUtils.nota());
        diario.setTituloDaResenha(DataFakerUtils.tituloResenha());
        diario.setResenha(DataFakerUtils.resenha());
        diario.setSpoiler(DataFakerUtils.spoiler());
        return diario;
    }

    public static DiarioModel terminoLeituraAnteriorAoInicio(Integer livroId, Integer numeroDePaginas) {

        DiarioModel diario = new DiarioModel();

        diario.setLivroId(livroId);
        diario.setInicioDaLeitura(DataFakerUtils.dataAtual());
        diario.setTerminoDaLeitura(DataFakerUtils.dataInicio());
        diario.setPaginasLidas(numeroDePaginas);
        diario.setNota(DataFakerUtils.nota());
        diario.setTituloDaResenha(DataFakerUtils.tituloResenha());
        diario.setResenha(DataFakerUtils.resenha());
        diario.setSpoiler(DataFakerUtils.spoiler());
        return diario;

    }

    public static DiarioModel criarDiarioLendo(Integer livroId, Integer numeroDePaginas) {

        DiarioModel diario = new DiarioModel();

        diario.setLivroId(livroId);
        diario.setInicioDaLeitura(DataFakerUtils.dataInicio());
        diario.setTerminoDaLeitura(null);
        diario.setPaginasLidas(numeroDePaginas);
        diario.setNota(DataFakerUtils.nota());
        diario.setTituloDaResenha(DataFakerUtils.tituloResenha());
        diario.setResenha(DataFakerUtils.resenha());
        diario.setSpoiler(true);
        return diario;

    }

}
