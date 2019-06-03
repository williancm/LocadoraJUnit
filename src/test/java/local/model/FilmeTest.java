package local.model;

import local.exception.FilmeException;
import org.junit.jupiter.api.Test;

import javax.validation.Valid;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmeTest {
    //TODO: Nome deve possuir entre 2 e 99 caracteres (inclusive)
    // Lança FilmeException - O nome do filme deve possuir entre 2 e 99 caracteres

    //TODO: Estoque não pode ser negativo, e o máximo é 99 (inclusive)
    // Lança FilmeException - Valor de estoque inválido

    //TODO: O preço de Locacao deverá ser um número positivo entre R$ 1,00 e R$ 9,99 (inclusive)
    // Lança FilmeException - Valor locação inválido

    @Test
    public void nomeFilme1Caractere() {
        Filme filme = new Filme();
        Exception ex = assertThrows(FilmeException.class, () ->filme.setNome("R"));
        assertThat(ex.getMessage(), is(equalTo("O nome do filme deve possuir entre 2 e 99 caracteres")));
    }

    @Test
    public void nomeFilme2Caracteres() {
        Filme filme = new Filme();
        filme.setNome("Ra");
        assertThat(filme, is(equalTo(filme)));
    }

    @Test
    public void nomeFilme99Caracteres(){
        Filme filme = new Filme();
        filme.setNome("Rambooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        assertThat(filme.getNome(), is(equalTo("Rambooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo")));
    }

    @Test
    public void nomeFilme100Caracteres() {
        Filme filme = new Filme();
        Exception ex = assertThrows(FilmeException.class, () ->filme.setNome("Ramboooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"));
        assertThat(ex.getMessage(), is(equalTo("O nome do filme deve possuir entre 2 e 99 caracteres")));
    }

    @Test
    public void estoqueNegativo() {
        Filme filme = new Filme();
        Exception ex = assertThrows(FilmeException.class, () ->filme.setEstoque(-1));
        assertThat(ex.getMessage(), is(equalTo("Valor de estoque inválido")));
    }

    @Test
    public void estoquePositivo() {
        Filme filme = new Filme();
        filme.setEstoque(0);
        assertThat(filme, is(equalTo(filme)));
    }

    @Test
    public void estoque99() {
        Filme filme = new Filme();
        filme.setEstoque(99);
        assertThat(filme, is(equalTo(filme)));
    }

    @Test
    public void estoque100() {
        Filme filme = new Filme();
        Exception ex = assertThrows(FilmeException.class, () ->filme.setEstoque(100));
        assertThat(ex.getMessage(), is(equalTo("Valor de estoque inválido")));
    }

}
