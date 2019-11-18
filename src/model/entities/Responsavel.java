package model.entities;

public class Responsavel {
	
	private String nomeResponsavel;
	private String dataNascimento;
	private String endereco;
	private String cpf;
	private String observacao;
	
	public Responsavel(){
		
	}

	public Responsavel(String nomeResponsavel, String dataNascimento, String endereco, String cpf, String observacao) {
		this.nomeResponsavel = nomeResponsavel;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.cpf = cpf;
		this.observacao = observacao;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "Responsavel [nomeResponsavel=" + nomeResponsavel + ", dataNascimento=" + dataNascimento + ", endereco="
				+ endereco + ", cpf=" + cpf + ", observacao=" + observacao + "]";
	}
	
	

	
	
	
	
	
	
}
