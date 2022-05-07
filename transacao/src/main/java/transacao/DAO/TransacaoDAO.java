package transacao.DAO;

import java.util.Date;

public class TransacaoDAO {

	private String bancoOrigem;
	private Integer agenciaOrigem;
	private String contaOrigem;
	private String bancoDestino;
	private Integer agenciaDestino;
	private String contaDestino;
	private Double valor;
	private Date data;
	
	public TransacaoDAO() {}

	public TransacaoDAO(String bancoOrigem, Integer agenciaOrigem, String contaOrigem, String bancoDestino,
			Integer agenciaDestino, String contaDestino, Double valor, Date data) {
		this.bancoOrigem = bancoOrigem;
		this.agenciaOrigem = agenciaOrigem;
		this.contaOrigem = contaOrigem;
		this.bancoDestino = bancoDestino;
		this.agenciaDestino = agenciaDestino;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.data = data;
	}

	public String getBancoOrigem() {
		return bancoOrigem;
	}

	public void setBancoOrigem(String bancoOrigem) {
		this.bancoOrigem = bancoOrigem;
	}

	public Integer getAgenciaOrigem() {
		return agenciaOrigem;
	}

	public void setAgenciaOrigem(Integer agenciaOrigem) {
		this.agenciaOrigem = agenciaOrigem;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getBancoDestino() {
		return bancoDestino;
	}

	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}

	public Integer getAgenciaDestino() {
		return agenciaDestino;
	}

	public void setAgenciaDestino(Integer agenciaDestino) {
		this.agenciaDestino = agenciaDestino;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
