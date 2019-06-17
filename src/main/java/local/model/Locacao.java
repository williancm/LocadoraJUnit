package local.model;

import local.exception.LocadoraException;
import local.util.DataUtils;
import org.thymeleaf.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Locacao {

	private Cliente cliente;
	private List<Filme> filmes;
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;

	public Locacao(){
		filmes = new ArrayList<>();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) throws LocadoraException {
		if(DataUtils.isDomingo(dataRetorno, 1)){
			throw new LocadoraException("Não é possível devolver um filme no domingo");
		}
		this.dataRetorno = dataRetorno;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public List<Filme> getFilmes() {
		return filmes;
	}
	public void addFilme(Filme filme){
		filmes.add(filme);
	}
}