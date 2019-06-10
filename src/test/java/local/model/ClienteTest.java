package local.model;

import local.exception.ClienteException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteTest {

    @Test
    public void nomeNaoDeveSerNulo() {
        Cliente cliente = new Cliente();
        Exception ex = assertThrows(ClienteException.class, () ->cliente.setNome(null));
        assertThat(ex.getMessage(), Is.is(CoreMatchers.equalTo("Nome é um campo obrigatório")));
    }

    @Test
    public void nomeCliente3caracteres(){
        Cliente cliente = new Cliente();
        Exception ex = assertThrows(ClienteException.class, () ->cliente.setNome("R m"));
        assertThat(ex.getMessage(), is(equalTo("O nome do cliente deve possuir entre 4 e 55 caracteres")));
    }

    @Test
    public void nomeCliente4caracteres(){
        Cliente cliente = new Cliente();
        cliente.setNome("IG R");
        assertThat(cliente.getNome(), is(equalTo("IG R")));
    }

    @Test
    public void nomeCliente55caracteres(){
        Cliente cliente = new Cliente();
        cliente.setNome("R amboooooooooooooooooooooooooooooooooooooooooooooooooo");
        assertThat(cliente.getNome(), is(equalTo("R amboooooooooooooooooooooooooooooooooooooooooooooooooo")));
    }

    @Test
    public void nomeCliente56Caracteres() {
        Cliente cliente = new Cliente();
        Exception ex = assertThrows(ClienteException.class, () ->cliente.setNome("Ramboooooooooooooooooooooooooooooooooooooooooooooooooooo"));
        assertThat(ex.getMessage(), is(equalTo("O nome do cliente deve possuir entre 4 e 55 caracteres")));
    }

    @Test
    public void nomeClienteComSobrenome(){
        Cliente cliente = new Cliente();
        cliente.setNome("Willian Matheus");
        assertThat(cliente.getNome(), is(equalTo("Willian Matheus")));
    }

    @Test
    public void nomeClienteSemSobrenome() {
        Cliente cliente = new Cliente();
        Exception ex = assertThrows(ClienteException.class, () ->cliente.setNome("Willian"));
        assertThat(ex.getMessage(), is(equalTo("necessário adicionar um sobrenome (ex.: Angelo Luz)")));
    }

    @Test
    public void nomeClienteSemSimbolos(){
        Cliente cliente = new Cliente();
        cliente.setNome("Willian Matheus");
        assertThat(cliente.getNome(), is(equalTo("Willian Matheus")));
    }

    @Test
    public void nomeClienteComSimbolos() {
        Cliente cliente = new Cliente();
        Exception ex = assertThrows(ClienteException.class, () ->cliente.setNome("Willian@2"));
        assertThat(ex.getMessage(), is(equalTo("Números e símbolos não são permitidos")));
    }
}
