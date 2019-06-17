package local.service;

import local.exception.FilmeSemEstoqueException;
import local.exception.LocadoraException;
import local.model.Filme;
import local.model.Cliente;
import local.model.Locacao;
import local.util.DataUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//TODO atualizar testes para trabalhar com os multiplos filmes
public class LocacaoServiceTest {

    private List<Filme> filmes;
    private Cliente cliente;
    public static Double VALOR_FILME = 4.00;
    private LocacaoService locacaoService;
/*
    @BeforeEach
    public void setUp(){
        cliente = new Cliente("Angelo Gonçalves da Luz");

        filmes = Arrays.asList(
                new Filme("O Cavaleiro das Trevas", 2, VALOR_FILME),
                new Filme("Brilho Eterno de Uma Mente Sem Lembranças", 2, VALOR_FILME),
                new Filme("O Fabuloso Destino de Amelie Poulain", 3, VALOR_FILME),
                new Filme("Avatar", 4, VALOR_FILME),
                new Filme("Gladiador", 2, VALOR_FILME),
                new Filme("Brokeback Mountain", 4, VALOR_FILME),
                new Filme("O Senhor dos Anéis: A Sociedade do Anel", 5, VALOR_FILME),
                new Filme("Quem Quer Ser um Milionário", 2, VALOR_FILME),
                new Filme("A Lista de Schindler", 5, VALOR_FILME),
                new Filme("Guerra nas Estrelas", 9, VALOR_FILME)
        );
    }*/


    @Test
    public void naoDeveLocarFilmeSemUsuario() {
        //Cenário
        Cliente cliente = null;
        LocacaoService ls = new LocacaoService();

        //Processamento e validação
        try {
            //TODO: Corrigir parâmetro para teste
            ls.alugarFilme(cliente, null);
            fail("Locação realizada com usuário null");
        }catch (LocadoraException | FilmeSemEstoqueException ex){
            //assertEquals("Impossível locar sem um usuário",ex.getMessage());
            assertThat(ex.getMessage(),is(equalTo("Impossivel locar sem um usuário")));
        }
    }
    @Test
    public void deveValidarValorLocacao() throws FilmeSemEstoqueException, LocadoraException {
        //TODO: Reescrever teste
        //Cenário
        Cliente cliente = new Cliente("Willian");
        LocacaoService ls = new LocacaoService();
        List<Filme> filmes = Arrays.asList(
                new Filme("Piratas do Vale do Silício", 4, 4.0));

        //Ação
        Locacao locacao = ls.alugarFilme(cliente, filmes);
        //Validação
        assertThat(locacao.getValor(), is(4.0));


    }

    @Test
    public void deveRealizarLocacao() throws FilmeSemEstoqueException, LocadoraException {
        //TODO: Deve realizar uma locação caso os argumentos sejam apropriados
        //Cenário
        Cliente cliente = new Cliente("Willian");
        LocacaoService ls = new LocacaoService();
        List<Filme> filmes = Arrays.asList(
                new Filme("Piratas do Vale do Silício", 4, 4.0));

        //Ação
        Locacao locacao = ls.alugarFilme(cliente, filmes);
        //Validação
        assertThat(locacao.getValor(), is(4.0));
    }

    @Test
    public void verificaDataEntrega() throws LocadoraException {
        //TODO: Deve entregar o filme sempre no dia posterior a retirada
        LocacaoService ls = new LocacaoService();

        Locacao locacao = ls.alugarFilme(cliente,Arrays.asList(filmes.get(0),filmes.get(1)));

        Date data = locacao.getDataRetorno();

        assertTrue(DataUtils.isMesmaData(data,DataUtils.obterDataComDiferencaDias(1)));

    }

    @Test
    public void devePagar75PorcentoNoFilme3() throws
            FilmeSemEstoqueException, LocadoraException{
        //Cenário
        Cliente usuario = new Cliente("Angelo");
        LocacaoService ls = new LocacaoService();
        List<Filme> filmes = Arrays.asList(
                new Filme("Piratas do Vale do Silício", 4, 4.0),
                new Filme("Jobs", 3, 4.0),
                new Filme("Duro de Matar 4", 11, 4.0));
        //Ação
        Locacao locacao = ls.alugarFilme(usuario, filmes);
        //Validação
        assertThat(locacao.getValor(), is(11.0));
    }

    @Test
    public void devePagar50PorcentoNoFilme4() throws
            FilmeSemEstoqueException, LocadoraException{
        //Cenário
        Cliente usuario = new Cliente("Angelo");
        LocacaoService ls = new LocacaoService();
        List<Filme> filmes = Arrays.asList(
                new Filme("Piratas do Vale do Silício", 4, 4.0),
                new Filme("Jobs", 3, 4.0),
                new Filme("Duro de Matar 4", 11, 4.0),
                new Filme("Star Wars", 14, 4.0));
        //Ação
        Locacao locacao = ls.alugarFilme(usuario, filmes);
        //Validação
        assertThat(locacao.getValor(), is(13.0));
    }

    @Test
    public void naoDevePagarOFilme5() throws
            FilmeSemEstoqueException, LocadoraException{
        //Cenário
        Cliente usuario = new Cliente("Angelo");
        LocacaoService ls = new LocacaoService();
        List<Filme> filmes = Arrays.asList(
                new Filme("Piratas do Vale do Silício", 4, 4.0),
                new Filme("Jobs", 3, 4.0),
                new Filme("Duro de Matar 4", 11, 4.0),
                new Filme("Shrek", 11, 4.0),
                new Filme("Star Wars", 14, 4.0));
        //Ação
        Locacao locacao = ls.alugarFilme(usuario, filmes);
        //Validação
        assertThat(locacao.getValor(), is(13.0));
    }

}