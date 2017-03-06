package br.com.montreal.util;

import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import br.com.montreal.entidades.AppConfig;

public class UtilBaseDados {
	public static AppConfig recuperaBaseDados() throws IOException {
		// String pathBaseDados = UtilComum.recuperarPropriedadeEstudoApp("pathBaseDados");
		String pathBaseDados = System.getProperty("user.dir") + "\\base.xml";
		String xmlArquivo = UtilComum.recuperarTextoArquivo(pathBaseDados);
		XStream xstream = new XStream();
		AppConfig baseDados = (AppConfig)xstream.fromXML(xmlArquivo); 
		return baseDados;
	}
	
	public static void gravaBaseDados(AppConfig baseDados) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("br.com.montreal.entidades.AppConfig", AppConfig.class); 
		String xml = xstream.toXML(baseDados); 
		// String pathBaseDados = UtilComum.recuperarPropriedadeEstudoApp("pathBaseDados");
		String pathBaseDados = System.getProperty("user.dir") + "\\base.xml";
		UtilComum.gravarArquivo(xml.getBytes(), pathBaseDados);
	}
	
}
