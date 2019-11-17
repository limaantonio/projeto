package model.entities;

public class Associacao {
	
	private String nomeAssociacao;
	private String logrado;
	private String distrito;
	private String qtdFamilias;
	private String observacao;
	
	public Associacao(){
		
	}

	public Associacao(String nomeAssociacao, String logrado, String distrito, String qtdFamilias, String observacao) {
		this.nomeAssociacao = nomeAssociacao;
		this.logrado = logrado;
		this.distrito = distrito;
		this.qtdFamilias = qtdFamilias;
		this.observacao = observacao;
	}

	public String getNomeAssociacao() {
		return nomeAssociacao;
	}

	public void setNomeAssociacao(String nomeAssociacao) {
		this.nomeAssociacao = nomeAssociacao;
	}

	public String getLogrado() {
		return logrado;
	}

	public void setLogrado(String logrado) {
		this.logrado = logrado;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getQtdFamilias() {
		return qtdFamilias;
	}

	public void setQtdFamilias(String qtdFamilias) {
		this.qtdFamilias = qtdFamilias;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "Associacao [nomeAssociacao=" + nomeAssociacao + ", logrado=" + logrado + ", distrito=" + distrito
				+ ", qtdFamilias=" + qtdFamilias + ", observacao=" + observacao + "]";
	}
	
	
	
	
	
	
}
