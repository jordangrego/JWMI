package br.com.montreal.wmiEstudo;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.Dispatch;
import com.jacob.com.EnumVariant;
import com.jacob.com.Variant;

import br.com.montreal.entidades.AppConfig;
import br.com.montreal.util.UtilBaseDados;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Principal extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.verificaBase();
			
			BorderPane root = new BorderPane();
			// tamanho da tela
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			//nao permite mudar tamanho janela
			primaryStage.setResizable(true);

			primaryStage.setTitle("Serviços");
			// primaryStage.getIcons().add(new Image("/icone.png")); //<-- descomentar para ter o icone
			
			primaryStage.setMaximized(true); // <---- seta para maximizado. pode
												// usar setFullScreen para nao
												// mostrar os icones de fechar e
												// afins...
			primaryStage.show();

			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getClassLoader().getResource("principalView.fxml"));
			AnchorPane mainView = (AnchorPane) loader.load();
			mainView.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// centraliza a tela
			root.setCenter(mainView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verificaBase() throws IOException {
		try {
			UtilBaseDados.recuperaBaseDados();
		} catch (IOException e) {
			
			AppConfig base = new AppConfig();
			UtilBaseDados.gravaBaseDados(base);
		}
	}
}
