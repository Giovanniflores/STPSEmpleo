package mx.gob.stps.portal.movil.web.infra.utils;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.APPLICATION_PROPERTIES;

import java.io.InputStream;
import java.util.Properties;

import mx.gob.stps.portal.movil.web.infra.utils.Utils;

public final class PropertiesLoaderWeb {

	private static PropertiesLoaderWeb instance = new PropertiesLoaderWeb();

	private static Properties properties = null;

	private PropertiesLoaderWeb() {}

	public static PropertiesLoaderWeb getInstance() {
		return instance;
	}

	/**
	 * Method <b>getProperty</b>
	 * 
	 * @param key
	 * @return String
	 */
	public String getProperty(String key) {
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
	
	public int getPropertyInt(String key) {
		int intValor = 0;
		String valor = getProperty(key);

		if (Utils.esEntero(valor)) {
			try {
				intValor = Integer.parseInt(valor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return intValor;
	}

	/**
	 * Method <b>initProperties</b>
	 * 
	 * @return Properties
	 */
	private Properties initProperties() throws Exception {
		Properties properties = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES);
		properties.load(in);
		return properties;
	}

}
