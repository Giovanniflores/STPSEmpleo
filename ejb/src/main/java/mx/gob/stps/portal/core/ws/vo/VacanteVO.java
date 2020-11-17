package mx.gob.stps.portal.core.ws.vo;

import java.io.Serializable;

import mx.gob.stps.portal.core.infra.utils.Utils;

public class VacanteVO implements Serializable {

	private static final long serialVersionUID = 3202974688390111427L;
	
	// OFERTA_EMPLEO
	protected long idEmpresaSisne;
	protected String tituloOferta;
	protected int idOcupacion;
	protected String funciones;
	protected int idTipoEmpleo;
	protected String diasLaborales;
	protected String horaEntrada;
	protected String horaSalida;
	protected int rolarTurno;
	protected String empresaOfrece;
	protected double salario;
	protected int idTipoContrato;
	protected int idJerarquia;
	protected long idEntidad;
	protected String municipio;
	protected String habilidadGeneral;	

	public long getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	protected int numeroPlazas;
	protected int limitePostulantes;
	protected long idDiscapacidad;	
	protected long idCausaVacante;
	protected String fechaInicio;
	protected int disponibilidadViajar;
	protected int disponibilidadRadicar;
	protected long idNivelEstudio;
	protected long idSituacionAcademica;
	protected int experienciaAnios;
	protected int edadMinima;
	protected int edadMaxima;
	protected int genero;	
	protected String observaciones;
	protected String fechaAlta;		
	protected int idVigenciaOferta;
	protected int idMedioContactoPreferido;
	//Se agregarón estos campos 30/01/13
	protected String nombreEmpresa;
	protected String nombreContacto;
	protected String correoElectronicoContacto;
	protected int idActividadEconomica;
	protected String cargoContacto;
//	protected int contactoDomicilio;
	
	// OFERTA_IDIOMA
	protected int idIdioma1;
	protected int idDominio1;	
	protected int idIdioma2;
	protected int idDominio2;
	
	//OFERTA_CARRERA_ESPEC
	protected int idCarreraEspecialidad;
	
	//TELEFONO
	protected String acceso;
	protected String clave;
	protected String telefono;
	protected String extension;

	public long getIdEmpresaSisne() {
		return idEmpresaSisne;
	}
	public void setIdEmpresaSisne(long idEmpresaSisne) {
		this.idEmpresaSisne = idEmpresaSisne;
	}
	public String getTituloOferta() {
		return tituloOferta;
	}
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}
	public int getIdOcupacion() {
		return idOcupacion;
	}
	public void setIdOcupacion(int idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public String getFunciones() {
		return funciones;
	}
	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}
	public int getIdTipoEmpleo() {
		return idTipoEmpleo;
	}
	public void setIdTipoEmpleo(int idTipoEmpleo) {
		this.idTipoEmpleo = idTipoEmpleo;
	}
	public String getDiasLaborales() {
		return diasLaborales;
	}
	public void setDiasLaborales(String diasLaborales) {
		this.diasLaborales = diasLaborales;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	public int getRolarTurno() {
		return rolarTurno;
	}
	public void setRolarTurno(int rolarTurno) {
		this.rolarTurno = rolarTurno;
	}
	public String getEmpresaOfrece() {
		return empresaOfrece;
	}
	public void setEmpresaOfrece(String empresaOfrece) {
		this.empresaOfrece = empresaOfrece;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getIdTipoContrato() {
		return idTipoContrato;
	}
	public void setIdTipoContrato(int idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}
	public int getIdJerarquia() {
		return idJerarquia;
	}
	public void setIdJerarquia(int idJerarquia) {
		this.idJerarquia = idJerarquia;
	}
	public int getNumeroPlazas() {
		return numeroPlazas;
	}
	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}
	public int getLimitePostulantes() {
		return limitePostulantes;
	}
	public void setLimitePostulantes(int limitePostulantes) {
		this.limitePostulantes = limitePostulantes;
	}
	public long getIdDiscapacidad() {
		return idDiscapacidad;
	}
	public void setIdDiscapacidad(long idDiscapacidad) {
		this.idDiscapacidad = idDiscapacidad;
	}
	public long getIdCausaVacante() {
		return idCausaVacante;
	}
	public void setIdCausaVacante(long idCausaVacante) {
		this.idCausaVacante = idCausaVacante;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public int getDisponibilidadViajar() {
		return disponibilidadViajar;
	}
	public void setDisponibilidadViajar(int disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}
	public int getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}
	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}
	public long getIdNivelEstudio() {
		return idNivelEstudio;
	}
	public void setIdNivelEstudio(long idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}
	public long getIdSituacionAcademica() {
		return idSituacionAcademica;
	}
	public void setIdSituacionAcademica(long idSituacionAcademica) {
		this.idSituacionAcademica = idSituacionAcademica;
	}
	public int getExperienciaAnios() {
		return experienciaAnios;
	}
	public void setExperienciaAnios(int experienciaAnios) {
		this.experienciaAnios = experienciaAnios;
	}
	public int getEdadMinima() {
		return edadMinima;
	}
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}
	public int getEdadMaxima() {
		return edadMaxima;
	}
	public void setEdadMaxima(int edadMaxima) {
		this.edadMaxima = edadMaxima;
	}
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public int getIdVigenciaOferta() {
		return idVigenciaOferta;
	}
	public void setIdVigenciaOferta(int idVigenciaOferta) {
		this.idVigenciaOferta = idVigenciaOferta;
	}
	public String getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public String getCorreoElectronicoContacto() {
		return correoElectronicoContacto;
	}
	public void setCorreoElectronicoContacto(String correoElectronicoContacto) {
		this.correoElectronicoContacto = correoElectronicoContacto;
	}
	public String getCargoContacto() {
		return cargoContacto;
	}
	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}
	public int getIdIdioma1() {
		return idIdioma1;
	}
	public void setIdIdioma1(int idIdioma) {
		this.idIdioma1 = idIdioma;
	}
	public int getIdDominio1() {
		return idDominio1;
	}
	public void setIdDominio1(int idDominio) {
		this.idDominio1 = idDominio;
	}
	public int getIdIdioma2() {
		return idIdioma2;
	}
	public void setIdIdioma2(int idIdioma) {
		this.idIdioma2 = idIdioma;
	}
	public int getIdDominio2() {
		return idDominio2;
	}
	public void setIdDominio2(int idDominio) {
		this.idDominio2 = idDominio;
	}		
	public int getIdCarreraEspecialidad() {
		return idCarreraEspecialidad;
	}
	public void setIdCarreraEspecialidad(int idCarreraEspecialidad) {
		this.idCarreraEspecialidad = idCarreraEspecialidad;
	}
	public String getAcceso() {
		return acceso;
	}
	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}
	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = habilidadGeneral;
	}

	public int getIdMedioContactoPreferido() {
		return idMedioContactoPreferido;
	}
	public void setIdMedioContactoPreferido(int idMedioContactoPreferido) {
		this.idMedioContactoPreferido = idMedioContactoPreferido;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public int getIdActividadEconomica() {
		return idActividadEconomica;
	}
	public void setIdActividadEconomica(int idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}
//	public int getContactoDomicilio() {
//		return contactoDomicilio;
//	}
//	public void setContactoDomicilio(int contactoDomicilio) {
//		this.contactoDomicilio = contactoDomicilio;
//	}
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		
		if (this.tituloOferta != null)
			buffer.append("tituloOferta="+this.tituloOferta+", ");
		else
			buffer.append("tituloOferta=, ");
		
		buffer.append("idOcupacion="+Utils.toString(this.idOcupacion)+", ");
		
		if (this.funciones != null)		
			buffer.append("funciones="+this.funciones+", ");
		else
			buffer.append("funciones=, ");
		
		buffer.append("idTipoEmpleo="+Utils.toString(this.idTipoEmpleo)+", ");
		
		if (this.diasLaborales != null)		
			buffer.append("diasLaborales="+this.diasLaborales+", ");
		else
			buffer.append("diasLaborales=, ");
		
		if (this.horaEntrada != null)		
			buffer.append("horaEntrada="+this.horaEntrada+", ");
		else
			buffer.append("horaEntrada=, ");		

		if (this.horaSalida != null)				
			buffer.append("horaSalida="+this.horaSalida+", ");
		else
			buffer.append("horaSalida=, ");	
		
		buffer.append("rolarTurno="+Utils.toString(this.rolarTurno)+", ");
		
		if (this.empresaOfrece != null)		
			buffer.append("empresaOfrece="+this.empresaOfrece+", ");
		else
			buffer.append("empresaOfrece=, ");
		
		buffer.append("salario="+Utils.toString(this.salario)+", ");

		buffer.append("idTipoContrato="+Utils.toString(this.idTipoContrato)+", ");
		buffer.append("idJerarquia="+Utils.toString(this.idJerarquia)+", ");		
		buffer.append("numeroPlazas="+Utils.toString(this.numeroPlazas)+", ");		
		buffer.append("limitePostulantes="+Utils.toString(this.limitePostulantes)+", ");
		buffer.append("idDiscapacidad="+Utils.toString(this.idDiscapacidad)+", ");

		buffer.append("idCausaVacante="+Utils.toString(this.idCausaVacante)+", ");
		
		if (this.fechaInicio != null)
			buffer.append("fechaInicio="+this.fechaInicio.toString()+", ");
		else
			buffer.append("fechaInicio=, ");
		
		buffer.append("disponibilidadViajar="+Utils.toString(this.disponibilidadViajar)+", ");		
		buffer.append("disponibilidadRadicar="+Utils.toString(this.disponibilidadRadicar)+", ");		
		buffer.append("idNivelEstudio="+Utils.toString(this.idNivelEstudio)+", ");
		buffer.append("idSituacionAcademica="+Utils.toString(this.idSituacionAcademica)+", ");
		buffer.append("experienciaAnios="+Utils.toString(this.experienciaAnios)+", ");		
		buffer.append("edadMinima="+Utils.toString(this.edadMinima)+", ");
		buffer.append("edadMaxima="+Utils.toString(this.edadMaxima)+", ");
		buffer.append("genero="+Utils.toString(this.genero)+", ");
		
		if (this.observaciones != null)
			buffer.append("observaciones="+this.observaciones+", ");
		else
			buffer.append("observaciones=, ");
		
		if (this.fechaAlta != null)
			buffer.append("fechaAlta="+this.fechaAlta.toString()+", ");
		else
			buffer.append("fechaAlta=, ");
		
		buffer.append("idVigenciaOferta="+Utils.toString(this.idVigenciaOferta)+", ");
		
		if (this.nombreContacto != null)		
			buffer.append("nombreContacto="+this.nombreContacto+", ");
		else
			buffer.append("nombreContacto=, ");	
		
		if (this.correoElectronicoContacto != null)
			buffer.append("correoElectronicoContacto="+this.correoElectronicoContacto+", ");
		else
			buffer.append("correoElectronicoContacto=, ");	
		
		if (this.cargoContacto != null)
			buffer.append("cargoContacto="+this.cargoContacto+", ");
		else
			buffer.append("cargoContacto=, ");
		
		buffer.append("idIdioma1="+Utils.toString(this.idIdioma1)+", ");
		buffer.append("idDominio1="+Utils.toString(this.idDominio1)+", ");
		buffer.append("idIdioma2="+Utils.toString(this.idIdioma2)+", ");
		buffer.append("idDominio2="+Utils.toString(this.idDominio2)+", ");
		buffer.append("idCarreraEspecialidad="+Utils.toString(this.idCarreraEspecialidad)+", ");	
		buffer.append("idEntidad="+Utils.toString(this.idEntidad)+", ");
		buffer.append("municipio="+Utils.toString(this.municipio)+", ");		
		
		if (this.acceso != null)
			buffer.append("acceso="+this.acceso+", ");		
		else
			buffer.append("acceso=, ");
		
		if (this.clave != null)
			buffer.append("clave="+this.clave+", ");
		else
			buffer.append("clave=, ");
		
		if (this.telefono != null)
			buffer.append("telefono="+this.telefono+", ");
		else
			buffer.append("telefono=, ");
		
		if (this.extension != null)
			buffer.append("extension="+this.extension+", ");
		else
			buffer.append("extension=, ");

		if (this.habilidadGeneral != null)
			buffer.append("habilidadGeneral="+this.habilidadGeneral+", ");
		else
			buffer.append("extension=, ");
		
		buffer.append("idMedioContactoPreferido="+Utils.toString(this.idMedioContactoPreferido)+", ");			
		
		return buffer.toString();
	}
	
}
