package br.com.montreal.wmiController.pattern;

public abstract class AbstractFxModalController extends AbstractFxController {
protected IModalCaller caller;
	
	public void setCaller(IModalCaller caller) {
		this.caller = caller;
	}
	
	protected abstract String getNomeModal();
}
