package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Associacao;
import model.entities.Cliente;

public class ClienteFormController {
	
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

	@FXML
	public void onBtSalvar() {
		
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
