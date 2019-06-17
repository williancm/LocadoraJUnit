package local.service;

import static local.util.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import local.model.Filme;
import local.model.Locacao;
import local.model.Cliente;
import local.exception.FilmeSemEstoqueException;
import local.exception.LocadoraException;

public class LocacaoService {
    public Locacao alugarFilme(Cliente cliente, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
        if (cliente == null) {
            throw new LocadoraException("Impossivel locar sem um usuário");
        }

        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Nenhum filme foi selecionado");
        }


        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);

        if(filmes.size() >= 3){
            filmes.get(2).setPrecoLocacao(filmes.get(2).getPrecoLocacao() * 0.75);
        }
        if(filmes.size() >= 4){
            filmes.get(3).setPrecoLocacao(filmes.get(3).getPrecoLocacao() * 0.5);
        }
        int count = 0;
        for(Filme filme: filmes) {
            if (filme.getEstoque() == 0) {
                throw new FilmeSemEstoqueException("Filme sem estoque");
            }

            locacao.addFilme(filme);
            locacao.setDataLocacao(new Date());
            locacao.setValor(locacao.getValor() + filme.getPrecoLocacao());
            if(filmes.size() == 5 && count  == 4){
                locacao.setValor(locacao.getValor() - filme.getPrecoLocacao());
            }
            count++;
            //Entrega no dia seguinte
            Date dataEntrega = new Date();
            dataEntrega = adicionarDias(dataEntrega, 1);
            locacao.setDataRetorno(dataEntrega);
        }
        //Salvando a locacao...	
        //TODO adicionar método para salvar
        return locacao;
    }
}
