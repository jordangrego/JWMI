package br.com.montreal.wmiController;

import java.io.IOException;

import br.com.montreal.entidades.AppConfig;
import br.com.montreal.util.UtilBaseDados;
import br.com.montreal.wmiController.enums.EnumRetornoModal;
import br.com.montreal.wmiController.pattern.AbstractFxModalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigController extends AbstractFxModalController {

	@FXML
	private TextField txtIP;
	
	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField ptxtSenha;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private void btnSalvarClick(ActionEvent event) {
		try {
			AppConfig appConfig = UtilBaseDados.recuperaBaseDados();
			
			appConfig.setIp(this.txtIP.getText());
			appConfig.setUsuario(this.txtUsuario.getText());
			appConfig.setSenha(this.ptxtSenha.getText());
			
			UtilBaseDados.gravaBaseDados(appConfig);
			
			this.caller.executeModalReturn(EnumRetornoModal.OK, this.getNomeModal());

			this.alertaSucesso("", "", "Gravado com suacesso");
			
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected String getNomeModal() {
		return "ConfigController";
	}

}
