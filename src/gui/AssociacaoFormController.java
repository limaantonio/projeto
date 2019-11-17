package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Associacao;
import model.entities.Cliente;

public class AssociacaoFormController {
	
	//Controllers do Componentes do Formulário
	
		@FXML
		private TextField textNomeAssociacao;
		
		@FXML
		private TextField textLogradoro;
		
		@FXML
		private TextField textDistrito;
		
		@FXML
		private TextField textQtdFamilias;
		
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
			
			
		
			Associacao associacao = new Associacao(this.textNomeAssociacao.getText(),
										  this.textLogradoro.getText(), 
										  this.textDistrito.getText(), 
										  this.textQtdFamilias.getText(),
										  this.textObservacao.getText());
			
			System.out.println("Associacao Salvo");
			System.out.println(associacao);
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
