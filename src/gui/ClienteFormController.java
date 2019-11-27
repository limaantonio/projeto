package gui;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import application.Main;
import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Exception.ValidationException;
import model.entities.Associacao;
import model.entities.Cliente;
import model.services.AssociacaoService;
import model.services.ClienteService;

public class ClienteFormController implements Initializable{

	private Cliente entity = new Cliente();
	
	private ClienteService service = new ClienteService();
	
	private AssociacaoService associacaoService = new AssociacaoService();
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	
	
	@FXML
	private ComboBox<Associacao> comboBoxAssociacao;

	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private DatePicker txtDataNascimento;
	
	@FXML
	private TextField txtCPF;
	
	@FXML
	private Label labelErrorName;
	
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	@FXML
	private Button btSair;
	
	private ObservableList<Associacao> obsList;
	
	public void setClienteService(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	public void setCliente(Cliente entity) {
		this.entity = entity;
	}
	
	public void setClienteService(ClienteService service) {
		this.service = service;
	}
	
	public void setServices(ClienteService service, AssociacaoService associacaoService) {
		this.service = service;
		this.associacaoService = associacaoService;
	}
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		
		if (entity == null) {
			throw new IllegalStateException("Enitity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(getFormData());
			notifyDataChangeListener();
			
			Alerts.showAlert("Salvar", null, "Responsável salvo com sucesso!", AlertType.INFORMATION);
			
			clear();
			
		}
		catch(ValidationException e) {
			setErrorMensage(e.getErros());
		}
		catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	private void notifyDataChangeListener() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Cliente getFormData() {
		Cliente obj = new Cliente();
		
		ValidationException exception = new ValidationException("Validation exception");
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		if(txtName.getText() == null || txtName.getText().trim().equals("")) {
			exception.addErrors("name", "Fiel can't be empty");
		}
		obj.setNome(txtName.getText());
		Instant instant = Instant.from(txtDataNascimento.getValue().atStartOfDay(ZoneId.systemDefault()));
		obj.setDataNascimento(Date.from(instant));
		obj.setCpf(txtCPF.getText());
		obj.setAssociacao(comboBoxAssociacao.getValue());
		
	
		
		if(exception.getErros().size() > 0) {
			throw exception;
		}
		
		return obj;
	}

	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
		
		List<Associacao> list = associacaoService.findAll();
		obsList = FXCollections.observableArrayList(list);
		comboBoxAssociacao.setItems(obsList);
		initializeComboBoxAssociacao(); 
	}
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getNome());
		//txtDataNascimento(LocalDate.ofInstant(entity.getDataNascimento().toInstant(), ZoneId.systemDefault()));
		txtCPF.setText(entity.getCpf());
		
			
		if(entity.getAssociacao() == null) {
			comboBoxAssociacao.getSelectionModel().selectFirst();
		}else {
			comboBoxAssociacao.setValue(entity.getAssociacao());
		}
		
		
	}
	
	private void setErrorMensage(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("name")) {
			labelErrorName.setText(errors.get("name"));
		}
	}
	
	@FXML
	public void onBtCanelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@FXML
	public void onBtSair() {
		Main.chageScreen("main");
	}
	
	public void loadAssociatedObjects() {
		if (associacaoService == null) {
			throw new IllegalStateException("AssociacaoService was null");
		}
		List<Associacao> list = associacaoService.findAll();
		obsList = FXCollections.observableArrayList(list);
		comboBoxAssociacao.setItems(obsList);
	}
	
	private void initializeComboBoxAssociacao() {
		Callback<ListView<Associacao>, ListCell<Associacao>> factory = lv -> new ListCell<Associacao>() {
			@Override
			protected void updateItem(Associacao item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		comboBoxAssociacao.setCellFactory(factory);
		comboBoxAssociacao.setButtonCell(factory.call(null));
	}
	public void clear() {
		txtId.setText("");
		txtName.setText("");
		//txtDataNascimento(Date.from());
		txtCPF.setText("");
		
	}
}
