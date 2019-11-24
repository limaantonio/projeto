package gui;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.IllegalSelectorException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Associacao;
import model.services.AssociacaoService;

public class TabelaAssociacaoController implements Initializable, DataChangeListener {

	private AssociacaoService service = new AssociacaoService();
	
	private ObservableList<Associacao> obsList;
	
	@FXML
	private TableView<Associacao> tableViewAssociacaos;
	
	@FXML
	private TableColumn<Associacao, Associacao> tableColumnREMOVE = new TableColumn<Associacao, Associacao>();

	@FXML
	private TableColumn<Associacao, Integer> tableColumnId;

	@FXML
	private TableColumn<Associacao, String> tableColumnName;
	
	@FXML
	private TableColumn<Associacao, String> tableColumnEndereco;
	
	@FXML
	private TableColumn<Associacao, String> tableColumnDistrito;

	@FXML
	private Button btIncluir;
	
	@FXML
	private Button btEditar;
	
	@FXML
	private Button btAtualizar;
	
	@FXML
	private Button btExcluir;
	
	@FXML
	private Button btSair;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	public void setAssociacaoService(AssociacaoService service) {
		this.service = service;
	}

	private void initializeNodes() {

		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tableColumnDistrito.setCellValueFactory(new PropertyValueFactory<>("distrito"));
		
		
	
		List<Associacao> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewAssociacaos.setItems(obsList);
		

	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("service was null");
		}
		List<Associacao> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewAssociacaos.setItems(obsList);
		
	}
	@Override
	public void onDataChanged() {
		updateTableView();

	}
	
	
	
	private void removeEntity(Associacao obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Você tem certeza que quer exluir?");
		
		if(result.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
				service.remove(obj);
				updateTableView();
			}catch(DbIntegrityException e) {
				Alerts.showAlert("Erro ao exluir objeto", null, e.getMessage(), Alert.AlertType.ERROR);
			}
		}
	}
	
	public void onBtEditar() {
		
	}
	
	public void onExcluir() {
		btExcluir.setOnAction(e -> {
			Associacao selectedItem = tableViewAssociacaos.getSelectionModel().getSelectedItem();
			tableViewAssociacaos.getItems().remove(selectedItem);
			removeEntity(selectedItem);
		});
	}
	
	public void onBtIncluir() {
		Main.chageScreen("associacao");
	}
	
	public void onBtSair() {
		Main.chageScreen("main");
	}
	
	public void onBtAtualizar() {
		updateTableView();
	}
}
