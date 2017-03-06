package br.com.montreal.datarow;

import javafx.beans.property.SimpleStringProperty;

public class ServicoWMIRow {
	
	private SimpleStringProperty name;
	private SimpleStringProperty pathName;
	private SimpleStringProperty started;
	private SimpleStringProperty startName;
	private SimpleStringProperty status;
	private SimpleStringProperty state;
	
	public ServicoWMIRow() {
		name = new SimpleStringProperty();
		pathName = new SimpleStringProperty();
		started = new SimpleStringProperty();
		startName = new SimpleStringProperty();
		status = new SimpleStringProperty();
		state = new SimpleStringProperty();
	}
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public String getPathName() {
		return pathName.get();
	}
	public void setPathName(String pathName) {
		this.pathName.set(pathName);
	}
	public String getStarted() {
		return started.get();
	}
	public void setStarted(String started) {
		this.started.set(started);
	}
	public String getStartName() {
		return startName.get();
	}
	public void setStartName(String startName) {
		this.startName.set(startName);
	}
	public String getStatus() {
		return status.get();
	}
	public void setStatus(String status) {
		this.status.set(status);
	}
	public String getState() {
		return state.get();
	}
	public void setState(String state) {
		this.state.set(state);
	}
}
