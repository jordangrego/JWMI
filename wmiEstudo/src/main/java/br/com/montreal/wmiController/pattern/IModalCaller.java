package br.com.montreal.wmiController.pattern;

import br.com.montreal.wmiController.enums.EnumRetornoModal;

public interface IModalCaller {
	void executeModalReturn(EnumRetornoModal retornoModal, String nomeModal);
}
