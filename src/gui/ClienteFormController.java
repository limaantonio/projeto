package gui;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Cliente;

public class ClienteFormController {
	
	//Controllers do Componentes do Formulário
	
	@FXML
	private TextField textNumCliente;
	
	@FXML
	private TextField textNomeCliente;
	
	@FXML
	private TextField textDataNascimento;
	
	@FXML
	private TextField textCPF;
	
	@FXML
	private TextField textTelefone;
	
	@FXML
	private Button btCancelar;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btSair;
	
	//Açoes do formulário
	
	

	@FXML
	public void onBtSalvar() {
		
	
		Cliente cliente = new Cliente(this.textNumCliente.getText(),
									  this.textNomeCliente.getText(), 
									  this.textDataNascimento.getText(), 
									  this.textCPF.getText(),
									  this.textTelefone.getText());
		
		
		
		System.out.println("Cliente Salvo");
		System.out.println(cliente);
	}
	
	@FXML
	public void onBtCancelar() {
		System.out.println("Operação cancelada");
	}
	
	@FXML
	public void onBtSair() {
		Main.chageScreen("main");
	}
	
}
