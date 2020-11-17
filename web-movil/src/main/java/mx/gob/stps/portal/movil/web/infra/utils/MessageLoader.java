package mx.gob.stps.portal.movil.web.infra.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Carga los mensajes en el archivo MessageResource.properties para accederlos dentro de una clase
 * cuando no se puede obtener el componente que lo hace de Struts
 * 
 * @author oscar.manzo
 */
public final class MessageLoader {

	private static MessageLoader instance = new MessageLoader();

	private static Properties properties = null;

	private MessageLoader() {}

	public static MessageLoader getInstance() {
		return instance;
	}

	public String getMessage(String key) {
		String value = null;
		try {
			if (properties == null) {
				properties = initProperties();
			}
			value = properties.getProperty(key.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	private Properties initProperties() throws Exception {
		Properties properties = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(ConstantesMovil.APPLICATION_MESSAGES);
		properties.load(in);
		return properties;
	}
}