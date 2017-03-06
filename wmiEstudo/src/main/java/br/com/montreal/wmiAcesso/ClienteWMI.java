package br.com.montreal.wmiAcesso;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.Dispatch;
import com.jacob.com.EnumVariant;
import com.jacob.com.Variant;

import br.com.montreal.datarow.ServicoWMIRow;

public class ClienteWMI {
	public List<ServicoWMIRow> RecuperaServicos() {
		
		List<ServicoWMIRow> lista = new ArrayList<ServicoWMIRow>();
		
		ActiveXComponent wmi = new ActiveXComponent("WbemScripting.SWbemLocator");

		Variant variantParameters[] = new Variant[4];
		variantParameters[0] = new Variant("10.55.1.64");
		variantParameters[1] = new Variant("root\\cimv2");
		variantParameters[2] = new Variant("jordan.silva");
		variantParameters[3] = new Variant("@a123456");
		ActiveXComponent axWMI;

		try {
			Variant conRet = wmi.invoke("ConnectServer", variantParameters);
			axWMI = new ActiveXComponent(conRet.toDispatch());
		} catch (ComFailException e) {
			axWMI = null;
			e.printStackTrace();
		}

		if (axWMI == null) {
			System.out.println("NULO");
		} else {
			System.out.println("");

			String servico = "postgresql-x64-9.5";// JOptionPane.showInputDialog("Nome do Serviço", "postgresql-x64-9.5");

			String query;

			if (!servico.equals("")) {
				query = "SELECT * FROM Win32_Service where Name = '" + servico + "'";
			} else {
				query = "SELECT * FROM Win32_Service";
			}

			Variant v = axWMI.invoke("ExecQuery", new Variant(query));

			EnumVariant enumVariant = new EnumVariant(v.toDispatch());
			Dispatch item = null;
			while (enumVariant.hasMoreElements()) {
				
				item = enumVariant.nextElement().toDispatch();
				ServicoWMIRow row = new ServicoWMIRow();
				row.setName(Dispatch.call(item, "Name").toString());
				row.setPathName(Dispatch.call(item, "PathName").toString());
				row.setStarted(Dispatch.call(item, "Started").toString());
				row.setStartName(Dispatch.call(item, "StartName").toString());
				row.setState(Dispatch.call(item, "Status").toString());
				row.setStatus(Dispatch.call(item, "State").toString());
				
				lista.add(row);
			}
		}
		
		return lista;
	}
}
