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

    //TODO: O nome não pode ser nulo.
    // Lança ClienteException -  Nome é um campo obrigatório

    //TODO: nome deve possuir entre 4 e 55 caracteres (inclusive).
    // Lança ClienteException - O nome do cliente deve possuir entre 4 e 55 caracteres

    //TODO: O nome do cadastro deve possuir pelo menos 2 nomes.
    // Lança ClienteException -  necessário adicionar um sobrenome (ex.: Angelo Luz)

    //TODO: Espaços no início e fim de nomes devem ser descartados

    //TODO: Nomes não devem possuir símbolos ou números.
    // Lança ClienteException - Números e símbolos não são permitidos

    @Test
    public void nomeNaoDeveSerNulo() {
        Cliente cliente = new Cliente();
        Exception ex = assertThrows(ClienteException.class, () ->cliente.setNome(null));
        assertThat(ex.getMessage(), Is.is(CoreMatchers.equalTo("Nome é um campo obrigatório")));
    }

}
