package mx.gob.stps.portal.movil.app.glasses.cookies;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.movil.app.glasses.exception.CookieException;
import mx.gob.stps.portal.movil.app.glasses.utilities.Codec;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

public class CookieSession {

	public static final String NAME = "STPS-MOVIL_SESSION";
	// public static final char RECORD_SEPARATOR = 0x1e;
	// public static final char END_OF_TEXT = 0x03;
	// public static final int MAX_AGE = 15 * 60; // Default value
	// TODO cambiar @ que no se occupe en un login
	public static final char DELIMITER_ONE = '%';
	public static final char DELIMITER_TWO = '¬';

	public static String read(final HttpServletRequest request) throws CookieException {
		String encodedValue = CookieUtils.readCookie(request, NAME);
		//removing firstpart
		String[] splitingEncoded = encodedValue.split("&");
		if(splitingEncoded.length > 1){
			encodedValue = splitingEncoded[1];
		}
		
		return (encodedValue == null) ? null : decryptDecode(encodedValue);
	}

	private static String decryptDecode(String value) throws CookieException {
		// TODO: DECRYPT VALUE
		// 1. Decode value
		String clearValue;
		String encodedValue;
		try {
			clearValue = value.substring(0, value.indexOf(DELIMITER_ONE));
			encodedValue = value.substring(value.indexOf(DELIMITER_ONE) + 1);
		} catch (IndexOutOfBoundsException e) {
			// Invalidate cookie
			throw new CookieException("Invalid Cookie Session");
		}
		try {
			if (Codec.encodeBASE64(clearValue).equals(encodedValue)) {
				return clearValue;
			}
		} catch (Exception e) {
			// Do Nothing...
		}

		return null;
	}

	public static MovilSessionVO decodeValue(String cookie) {
		MovilSessionVO vo = new MovilSessionVO();
		String decodedValue = "";
		try {
			String[] splitted = cookie.split("&");
			if(splitted.length>1){
				cookie = splitted[1];
			}
			decodedValue = Codec.decodeBASE64Str(cookie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String split = String.valueOf(DELIMITER_TWO);
		String[] ret = decodedValue.split(split);
		vo.setIdUsuario(Long.valueOf(ret[0]));
		vo.setIdDevice(ret[1]);
		vo.setToken(ret[2]);
		vo.setPerfil(Integer.valueOf(ret[3]));
		vo.setUsername(ret[4]);

		if (vo.getPerfil() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario())
			vo.setIdEmpresa(Long.valueOf(ret[5]));
		else
			vo.setIdCandidato(Long.valueOf(ret[5]));
		// verificar los datos del cookie que si existe en la base de datos
		
		return vo;

	}

	public static String createCookie(String idUsuario, String token, String idDevice, String idPerfil,
			String username, String idTipoUsuario) {
		String clearValue = String.format("%s%s%s%s%s%s%s%s%s%s%s", idUsuario, DELIMITER_TWO, idDevice, DELIMITER_TWO,
				token, DELIMITER_TWO, idPerfil, DELIMITER_TWO, username, DELIMITER_TWO, idTipoUsuario);
		String encodedValue = null;

		// int newMaxAge = ((5/3) * maxAge);

		try {
			encodedValue = Codec.encodeBASE64(clearValue);
		} catch (Exception e) {
			// DO Nothing...
		}
		return encodedValue;
	}

	// public static void write(final HttpServletRequest request, final
	// HttpServletResponse response, String username, String role, int maxAge) {
	public static String write(final HttpServletRequest request, final HttpServletResponse response, String idUsuario,
			String token, String idDevice, String idPerfil, String username, String idTipoUsuario,String tipoUsuario) {

		String encodedValue = createCookie(idUsuario, token, idDevice, idPerfil, username, idTipoUsuario);
		if(tipoUsuario.equals(TIPO_USUARIO.EMPRESA.getTipoUsuario())){
			token = TIPO_USUARIO.EMPRESA.getTipoUsuario() +"&" +encodedValue;
		}
		else
			if(tipoUsuario.equals(TIPO_USUARIO.CANDIDATO.getTipoUsuario())){
				token = TIPO_USUARIO.CANDIDATO.getTipoUsuario() + "&" + encodedValue;
			}
		int untilBrowserIsAlive = -1;
		CookieUtils.writeCookie(request, response, NAME, token, untilBrowserIsAlive);
		return encodedValue;
	}

}
