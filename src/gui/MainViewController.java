package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class MainViewController implements Initializable{
	
	@FXML
	private MenuItem menuConsumo;
	
	@FXML
	private Menu menuHome;
	
	@FXML
	private MenuItem menuItemCliente;
	
	@FXML
	private MenuItem menuItemAssociacao;
	
	@FXML
	private MenuItem menuItemResponsavel;
	
	@FXML
	private MenuItem menuItemTabelaAssociacao;
	
	@FXML
	private MenuItem menuItemTabelaResponsaveis;
	
	@FXML
	public void onMenuHomeSelect() {
		Main.chageScreen("main");
	}
	
	@FXML
	public void onMenuItemConsumoSelect() {
		Main.chageScreen("consumo");
	}
	
	@FXML
	public void onMenuItemClienteSelect() {
		Main.chageScreen("cliente");
	}
	
	@FXML
	public void onMenuItemAssociacaoSelect() {
		Main.chageScreen("associacao");
	}

	@FXML
	public void onMenuItemResponsavelSelect() {
		Main.chageScreen("responsavel");
	}
	
	@FXML
	public void onMenuItemTabelaAssociacaoSelect() {
		Main.chageScreen("tabela");
		
	}
	
	@FXML
	public void onMenuItemTabelaResponsaveisSelect() {
		Main.chageScreen("tabelaResponsaveis");
		
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}
	
}
