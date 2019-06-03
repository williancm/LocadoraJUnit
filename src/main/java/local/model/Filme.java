package local.model;

import local.exception.FilmeException;
import local.exception.FilmeSemEstoqueException;

public class Filme {


    private String nome;
    private Integer estoque;
    private Double precoLocacao;

    public Filme() {
    }

    public Filme(String nome, Integer estoque, Double precoLocacao) {
        this.nome = nome;
        this.estoque = estoque;
        this.precoLocacao = precoLocacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() < 2 || nome.length() > 99){
            throw new FilmeException("O nome do cliente deve possuir entre 2 e 99 caracteres");
        }
        if(estoque < 0 || estoque > 99){
            throw new FilmeException("Valor de estoque inválido");
        }
        if(precoLocacao < 1.0 || precoLocacao > 9.99){
            throw new FilmeException("Valor de locação inválido");
        }


        this.nome = nome;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getPrecoLocacao() {
        return precoLocacao;
    }

    public void setPrecoLocacao(Double precoLocacao) {
        this.precoLocacao = precoLocacao;
    }
}
