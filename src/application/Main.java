package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
	
	private static Stage stage;
	
	private static Scene mainScene;
	private static Scene clientScene;
	private static Scene associacoScene;
	
	@Override
	public void start(Stage primarystage) {
		stage = primarystage;
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/main.fxml"));
			mainScene = new Scene(parent);
			
			Parent parentCliente = FXMLLoader.load(getClass().getResource("/gui/cliente.fxml"));
			clientScene = new Scene(parentCliente);
			
			Parent parentAssociacao = FXMLLoader.load(getClass().getResource("/gui/Associacao.fxml"));
			associacoScene = new Scene(parentAssociacao);
			
			
			
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
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
