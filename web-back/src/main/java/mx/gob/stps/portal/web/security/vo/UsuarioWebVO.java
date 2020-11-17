package mx.gob.stps.portal.web.security.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;

public class UsuarioWebVO implements Serializable {

	private static final long serialVersionUID = 5903782003239975707L;
	private String usuario;
	private long idUsuario;
	private String correoElectronico;
	private long idTipoUsuario;
	private long idRegistro;
	private int estatus;	
	private long idEntidad;
	private long idPerfil;
	private Date fechaAlta;
	
	private String pwd;

	//  Propiedades para el acceso al OLA
	private String urlCarrera;
	private String urlOcupacion;
	private String tituloCarrera;
	private String tituloOcupacion;
	
	private long idPropietario; // Candidato (idCandidato), Empresa (idEmpresa), Administrador (idUsuario)

	private List<String> acciones;
	
	//RBM1 TK997 y TK998
	private long idRegValidar;
	private UsuarioWebVO(){}

	private UsuarioWebVO(String usuario, long idUsuario, String correoElectronico, long idTipoUsuario, long idRegistro, int estatus, long idEntidad, long idPerfil, Date fechaAlta){
		this.usuario = usuario;
		this.idUsuario = idUsuario;
		this.correoElectronico = correoElectronico;
		this.idTipoUsuario = idTipoUsuario;
		this.idRegistro = idRegistro;
		this.estatus = estatus;
		this.idEntidad = idEntidad;
		this.idPerfil = idPerfil;
		this.fechaAlta = fechaAlta;
	}
	
	private UsuarioWebVO(String usuario, long idUsuario, String correoElectronico, long idTipoUsuario, long idRegistro, int estatus, long idEntidad, long idPerfil, Date fechaAlta, String pwd){
		this.usuario = usuario;
		this.idUsuario = idUsuario;
		this.correoElectronico = correoElectronico;
		this.idTipoUsuario = idTipoUsuario;
		this.idRegistro = idRegistro;
		this.estatus = estatus;
		this.idEntidad = idEntidad;
		this.idPerfil = idPerfil;
		this.fechaAlta = fechaAlta;
		this.pwd = pwd;
	}	
	
	public static UsuarioWebVO getInstance(String usuario, long idUsuario, String correoElectronico, long idTipoUsuario, long idRegistro, int estatus, long idEntidad, long idPerfil, Date fechaAlta){
		return new UsuarioWebVO(usuario, idUsuario, correoElectronico, idTipoUsuario, idRegistro, estatus, idEntidad, idPerfil, fechaAlta);
	}
	
	public static UsuarioWebVO getInstance(String usuario, long idUsuario, String correoElectronico, long idTipoUsuario, long idRegistro, int estatus, long idEntidad, long idPerfil, Date fechaAlta, String pwd){
		return new UsuarioWebVO(usuario, idUsuario, correoElectronico, idTipoUsuario, idRegistro, estatus, idEntidad, idPerfil, fechaAlta, pwd);
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
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public long getIdRegistro() {
		return idRegistro;
	}
	public int getEstatus() {
		return estatus;
	}
	public long getIdEntidad() {
		return idEntidad;
	}
	public long getIdPerfil() {
		return idPerfil;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public long getIdPropietario() {
		return idPropietario;
	}
	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
	}

	public List<String> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<String> acciones) {
		this.acciones = acciones;
	}

	public boolean getAdministrador(){
		return idPerfil == PERFIL.ADMINISTRADOR.getIdOpcion() || idPerfil == PERFIL.SUPERADMINISTRADOR.getIdOpcion();
	}
	
	public boolean getSuperUsuario(){
		return idPerfil == PERFIL.SUPERADMINISTRADOR.getIdOpcion();
	}

	public boolean getPublicador(){
		return idPerfil == PERFIL.PUBLICADOR.getIdOpcion();
	}

	public boolean getEmpresa(){
		return idPerfil == PERFIL.EMPRESA.getIdOpcion();
	}

	public boolean getCandidato(){
		
		boolean candidato= (idPerfil == PERFIL.CANDIDATO.getIdOpcion()) || (idPerfil == PERFIL.CANDIDATO_DISCAPACIDAD.getIdOpcion());
		
		return candidato;		
	}
	

	public boolean getSupervisor(){
		return idPerfil == PERFIL.SUPERVISOR.getIdOpcion();
	}

	public boolean getAdminTipoA(){
		return idPerfil == PERFIL.ADMIN_TIPO_A.getIdOpcion();
	}

	public boolean getSupervisorSnetel(){
		return idPerfil == PERFIL.SUPERVISOR_SNETEL.getIdOpcion();
	}
	
	public boolean getMonitor(){
		return idPerfil == PERFIL.MONITOR_PE.getIdOpcion();
	}
	
	public boolean getAdministradorSnetel(){
		return idPerfil == PERFIL.ADMINISTRADOR_SNETEL.getIdOpcion();
	}		
	
	public String getUrlCarrera() {
		return urlCarrera;
	}

	public void setUrlCarrera(String urlCarrera) {
		this.urlCarrera = urlCarrera;
	}

	public String getUrlOcupacion() {
		return urlOcupacion;
	}

	public void setUrlOcupacion(String urlOcupacion) {
		this.urlOcupacion = urlOcupacion;
	}

	public String getTituloCarrera() {
		return tituloCarrera;
	}

	public void setTituloCarrera(String tituloCarrera) {
		this.tituloCarrera = tituloCarrera;
	}

	public String getTituloOcupacion() {
		return tituloOcupacion;
	}

	public void setTituloOcupacion(String tituloOcupacion) {
		this.tituloOcupacion = tituloOcupacion;
	}

    public boolean isRolEmpresa(){
        return idPerfil == PERFIL.EMPRESA.getIdOpcion();
    }

    public boolean isRolCandidato(){
        return idPerfil == PERFIL.CANDIDATO.getIdOpcion();
    }

    public String getPwd() {
		return pwd;
	}

    public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public long getIdRegValidar() {
		return idRegValidar;
	}

	public void setIdRegValidar(long idRegValidar) {
		this.idRegValidar = idRegValidar;
	}
}
