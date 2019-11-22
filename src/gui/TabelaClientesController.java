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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Associacao;
import model.services.AssociacaoService;

public class TabelaClientesController implements Initializable, DataChangeListener {

	private AssociacaoService service;

	
	@FXML
	private TableView<Associacao> tableViewAssociacaos;

	@FXML
	
	private TableColumn<Associacao, Integer> tableColumnId;

	@FXML
	private TableColumn<Associacao, String> tableColumnName;

	
	private ObservableList<Associacao> obsList;

	

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

		//Stage stage = (Stage) Main.getMainScene().getWindow();
		//tableViewAssociacaos.prefHeightProperty().bind(stage.heightProperty());

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

	

	

	
}
