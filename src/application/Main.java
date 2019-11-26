package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	

	private static Stage stage;
	private static Scene mainScene;
	private static Scene clientScene;
	private static Scene associacoScene;
	private static Scene responsavelScene;
	private static Scene consumoScene;
	private static Scene tabelaScene;
	private static Scene tabelaResponsavelScene;
	
	@Override
	public void start(Stage primarystage) {
		stage = primarystage;
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/Main.fxml"));
			mainScene = new Scene(parent);
			
			Parent parentCliente = FXMLLoader.load(getClass().getResource("/gui/Cliente.fxml"));
			clientScene = new Scene(parentCliente);
			
			Parent parentAssociacao = FXMLLoader.load(getClass().getResource("/gui/Associacao.fxml"));
			associacoScene = new Scene(parentAssociacao);
			
			Parent parentResponsavel = FXMLLoader.load(getClass().getResource("/gui/Responsavel.fxml"));
			responsavelScene = new Scene(parentResponsavel);
			
			Parent parentConsumo = FXMLLoader.load(getClass().getResource("/gui/Consumo.fxml"));
			consumoScene = new Scene(parentConsumo);
			
			Parent parentTabelaAssociacoes = FXMLLoader.load(getClass().getResource("/gui/TabelaAssociacoes.fxml"));
			tabelaScene = new Scene(parentTabelaAssociacoes);
			
			Parent parentTabelaResponsaveis = FXMLLoader.load(getClass().getResource("/gui/TabelaResponsaveis.fxml"));
			tabelaResponsavelScene = new Scene(parentTabelaResponsaveis);
			
			
			
			primarystage.setScene(mainScene);
			primarystage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void chageScreen(String scr) {
		switch (scr) {
		case "main":
			stage.setScene(mainScene);
			break;

		case "cliente":
			stage.setScene(clientScene);
			break;
		
		case "associacao":
			stage.setScene(associacoScene);
			break;
		
		case "responsavel":
			stage.setScene(responsavelScene);
			break;
			
		case "consumo":
			stage.setScene(consumoScene);
			break;
			
		case "tabela":
			stage.setScene(tabelaScene);
			break;
			
		case "tabelaResponsaveis":
			stage.setScene(tabelaResponsavelScene);
			break;
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
}
