package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Responsavel;


public class ResponsavelFormController {
	
	//Controllers do Componentes do Formulário
	
		@FXML
		private TextField textNomeResponsavel;
		
		@FXML
		private TextField textDataNascimento;
		
		@FXML
		private TextField textEndereco;
		
		@FXML
		private TextField textCPF;
		
		@FXML
		private TextField textObservacao;
		
		@FXML
		private Button btCancelar;
		
		@FXML
		private Button btSalvar;
		
		@FXML
		private Button btSair;
		
		//Açoes do formulário
		
		@FXML
		public void onBtSalvar() {
			Responsavel responsavel = new Responsavel(this.textNomeResponsavel.getText(),
										  this.textDataNascimento.getText(), 
										  this.textEndereco.getText(), 
										  this.textCPF.getText(),
										  this.textObservacao.getText());
			
			System.out.println("Responsavel Salvo");
			System.out.println(responsavel);
		}
		
		@FXML
		public void onBtCancelar() {
			System.out.println("Operação cancelada");
		}
		
		@FXML
		public void onBtSair() {
			//Main.chageScreen("main");
		}
}
