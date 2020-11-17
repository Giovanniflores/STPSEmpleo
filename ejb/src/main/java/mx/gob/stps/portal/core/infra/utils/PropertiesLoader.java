package mx.gob.stps.portal.core.infra.utils;

import java.util.Properties;


/**
 * Class <b>WebPropertiesLoader</b>
 * 
 * @version 1.0 La clase ConfigAppLoader tiene por objetivo el cargar las
 *          configuraciones de la aplicación en la capa web.
 */
public final class PropertiesLoader {

	// Atributos ------------------------------------------
	private static PropertiesLoader instance = new PropertiesLoader();

	private static Properties properties = null;

	/**
	 * Constructor <b>WebPropertiesLoader</b>
	 */
	private PropertiesLoader() {
	}

	/**
	 * Method <b>getInstance</b>
	 * 
	 * @return WebPropertiesLoader
	 */
	public static PropertiesLoader getInstance() {
		return instance;
	}

	/**
	 * Method <b>getPropertyInt</b>
	 * 
	 * @param key
	 * @return int
	 */
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

	/**
	 * Method <b>initProperties</b>
	 * 
	 * @return Properties
	 */
	private Properties initProperties() throws Exception {
		Properties properties = new Properties();
		properties.load(this.getClass().getClassLoader().getResourceAsStream(Constantes.APPLICATION_PROPERTIES));
		return properties;
	}

}
