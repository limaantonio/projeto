package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainViewController implements Initializable{
	
	@FXML
	private MenuItem menuItemCliente;
	
	@FXML
	private MenuItem menuItemAssociacao;
	
	@FXML
	private Menu menuHome;
	
	@FXML
	public void onMenuItemClienteSelect() {
		Main.chageScreen("cliente");
	}
	
	@FXML
	public void onMenuItemAssociacaoSelect() {
		Main.chageScreen("associacao");
	}
	
	@FXML
	public void onMenuHomeSelect() {
		Main.chageScreen("main");
	}

	



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
