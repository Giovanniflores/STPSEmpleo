package mx.gob.stps.portal.movil.web.seguridad.vo;

import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;

public final class UsuarioFirmadoVO {

	private long idUsuario;
	private String usuario;
	private String correoElectronico;
	
	private long idPerfil;
	private long idPropietario;
	
	private UsuarioFirmadoVO(long idUsuario, String usuario, String correoElectronico, long idPerfil, long idPropietario){
		this.idUsuario = idUsuario;
		this.correoElectronico = correoElectronico;
		this.idPerfil = idPerfil;
		this.idPropietario = idPropietario;
	}
	
	public static final UsuarioFirmadoVO getInstance(long idUsuario, String usuario, String correoElectronico, long idPerfil, long idPropietario){
		return new UsuarioFirmadoVO(idUsuario, usuario, correoElectronico, idPerfil, idPropietario);
	}

	public long getIdUsuario() {
		return idUsuario;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public long getIdPerfil() {
		return idPerfil;
	}
	
	public long getIdPropietario() {
		return idPropietario;
	}

	public boolean isCandidato(){
		boolean is = idPerfil==PERFIL.CANDIDATO.getIdOpcion();
		return is;
	}

	public boolean isEmpresa(){
		boolean is = idPerfil==PERFIL.EMPRESA.getIdOpcion();
		return is;
	}

}