package gui;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Cliente;

public class ConsumoFormController {
	
	//Controllers do Componentes do Formulário
	
	@FXML
	private TextField textAssociacao;
	
	@FXML
	private TextField textCliente;
	
	@FXML
	private TextField textEndereco;
	
	@FXML
	private TextField textCPF;
	
	@FXML
	private TextField textConsumoL;
	
	@FXML
	private TextField textDias;
	
	@FXML
	private TextField textValUnit;
	
	@FXML
	private TextField textValTotal;
	
	@FXML
	private Button btCancelar;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btSair;
	
	//Açoes do formulário
	
	

	@FXML
	public void onBtSalvar() {
		
	
		System.out.println("Consumo cadastrado. Deseja Imprimir?");
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
