package gui;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Cliente;
import model.services.ClienteService;

public class TabelaResponsavelController implements Initializable, DataChangeListener {

	private ClienteService service = new ClienteService();
	
	private ObservableList<Cliente> obsList;
	
	@FXML
	private TableView<Cliente> tableViewResponsaveis;
	
	@FXML
	private TableColumn<Cliente, Cliente> tableColumnREMOVE = new TableColumn<Cliente, Cliente>();

	@FXML
	private TableColumn<Cliente, Integer> tableColumnId;

	@FXML
	private TableColumn<Cliente, String> tableColumnName;
	
	@FXML
	private TableColumn<Cliente, Date> tableColumnDateNascimento;
	
	
	
	@FXML
	private TableColumn<Cliente, String> tableColumnCpf;

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

	public void setClienteService(ClienteService service) {
		this.service = service;
	}

	private void initializeNodes() {

		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnDateNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
		Utils.formatTableColumnDate(tableColumnDateNascimento, "dd/MM/yyyy");
		tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
	
		List<Cliente> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewResponsaveis.setItems(obsList);
		

	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("service was null");
		}
		List<Cliente> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewResponsaveis.setItems(obsList);
		
	}
	@Override
	public void onDataChanged() {
		updateTableView();

	}
	
	
	
	private void removeEntity(Cliente obj) {
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
			Cliente selectedItem = tableViewResponsaveis.getSelectionModel().getSelectedItem();
			tableViewResponsaveis.getItems().remove(selectedItem);
			removeEntity(selectedItem);
		});
	}
	
	public void onBtIncluir() {
		Main.chageScreen("cliente");
	}
	
	public void onBtSair() {
		Main.chageScreen("main");
	}
	
	public void onBtAtualizar() {
		updateTableView();
	}
}
