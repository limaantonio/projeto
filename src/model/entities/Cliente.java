package model.entities;

import java.util.Date;

public class Cliente {
	
	private String numeroCliente;
	private String nomeCliente;
	private String dtNascimentoCliente;
	private String cpfCliente;
	private String telefoneCliente;
	
	public Cliente() {
		
	}

	public Cliente(String numeroCliente, String nomeCliente, String dtNascimentoCliente, String cpfCliente,
			String telefoneCliente) {
		this.numeroCliente = numeroCliente;
		this.nomeCliente = nomeCliente;
		this.dtNascimentoCliente = dtNascimentoCliente;
		this.cpfCliente = cpfCliente;
		this.telefoneCliente = telefoneCliente;
	}

	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDtNascimentoCliente() {
		return dtNascimentoCliente;
	}

	public void setDtNascimentoCliente(String dtNascimentoCliente) {
		this.dtNascimentoCliente = dtNascimentoCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	@Override
	public String toString() {
		return "Cliente [numeroCliente=" + numeroCliente + ", nomeCliente=" + nomeCliente + ", dtNascimentoCliente="
				+ dtNascimentoCliente + ", cpfCliente=" + cpfCliente + ", telefoneCliente=" + telefoneCliente + "]";
	}
		
	
}
