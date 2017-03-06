package br.com.montreal.wmiController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.montreal.datarow.ServicoWMIRow;
import br.com.montreal.wmiAcesso.ClienteWMI;
import br.com.montreal.wmiController.enums.EnumRetornoModal;
import br.com.montreal.wmiController.pattern.AbstractFxController;
import br.com.montreal.wmiController.pattern.IModalCaller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class PrincipalController extends AbstractFxController implements IModalCaller, Initializable {

	private ClienteWMI clienteWmi = new ClienteWMI();
	private Timeline oneMinuteWonder;
	private ConfigController configController;
	private boolean isExecute = true;
	
	@FXML
	Button btnConfigurar;
	
	@FXML
	TableView tvServicos;
	
	@FXML
	private void btnConfigurarClick(ActionEvent event) {
		this.configController = (ConfigController) this.showModal("configView.fxml", "Configuração");
		this.configController.setCaller(this);
	}
	
	@Override
	public void executeModalReturn(EnumRetornoModal retornoModal, String nomeModal) {
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.configuraGrid();
		this.oneMinuteWonder = new Timeline(new KeyFrame(Duration.seconds(30), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	if (isExecute) {		    		
		    		System.out.println("Executando WMI Cliente");
		    		executeWmiCliente();
		    	}
		    }
		}));
		
		oneMinuteWonder.setCycleCount(Timeline.INDEFINITE);
		oneMinuteWonder.play();
	}
	
	private void executeWmiCliente() {
		
		List<ServicoWMIRow> dataSource = clienteWmi.RecuperaServicos();
		
		ObservableList<ServicoWMIRow> data = FXCollections.observableArrayList(dataSource);
		tvServicos.setItems(data);
		
		boolean isRunning = false;
		for(ServicoWMIRow row : dataSource) {
			if (row.getStatus().equals("Running")) {
				isRunning = true;
			}
		}
		
		if (isRunning == true) {
			this.isExecute = false;
			this.getStageFromControl(this.btnConfigurar).requestFocus();
			this.alertaAviso("", "", "TEM RUNNING");
			this.isExecute = true;
		}
		
	}
	
	private void configuraGrid() {

		

		TableColumn<ServicoWMIRow, String> colName = new TableColumn<ServicoWMIRow, String>("Name");
		colName.setCellValueFactory(new PropertyValueFactory<ServicoWMIRow, String>("name"));
		colName.setMinWidth(300);

		TableColumn<ServicoWMIRow, String> colState = new TableColumn<ServicoWMIRow, String>("state");
		colState.setCellValueFactory(new PropertyValueFactory<ServicoWMIRow, String>("state"));
		colState.setMinWidth(300);
		
		TableColumn<ServicoWMIRow, String> colStatus = new TableColumn<ServicoWMIRow, String>("status");
		colStatus.setCellValueFactory(new PropertyValueFactory<ServicoWMIRow, String>("status"));
		colStatus.setMinWidth(300);

		

		this.tvServicos.getColumns().add(colName);
		this.tvServicos.getColumns().add(colState);
		this.tvServicos.getColumns().add(colStatus);
	}
}
