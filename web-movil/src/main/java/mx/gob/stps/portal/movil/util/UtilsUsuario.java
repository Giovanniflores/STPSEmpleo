package mx.gob.stps.portal.movil.util;

import mx.gob.stps.portal.core.infra.utils.Password;

public final class UtilsUsuario {

	/**
	 * Valida con correspondencia de la contraseña
	 * 
	 * @param storedPassword
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static boolean validatePassword(String storedPassword,
			String password) throws Exception {

		if (storedPassword == null || storedPassword.isEmpty())
			return false;
		if (password == null || password.isEmpty())
			return false;

		String codificadoPassword = Password.codificaPassword(password);
		if (!storedPassword.equals(codificadoPassword))
			return false;

		return true;
	}

}
