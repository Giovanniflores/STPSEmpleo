package mx.gob.stps.portal.web.oferta.form;

import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadOfertaVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoBecateVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.oferta.vo.OfertaEmpleoVO;

import org.apache.struts.action.ActionForm;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public class OfertaEmpleoForm extends ActionForm{
	private static final long serialVersionUID = -643651113083605965L;

	// Identificador de Oferta de Empleo que se utiliza como plantilla
	private long idOfertaEmpleoOriginal;

	private int idOfertaEmpleo;

	//Inicia Datos de registro	
	private String tituloOferta;
	private int idOcupacion;
	private String ocupacion;
	private int numeroPlazas;
	private String funciones;
	private int idTipoEmpleo;
	private int idTipoContrato;
	private String fechaVigencia;
	private String horaEntrada;
	private String horaSalida;
	private int rolarTurnos;
	private String diasLaborales;
	private int aceptaDiscapacitados;
	private int idTipoDiscapacidad;
	private int idCausaOferta;
	private int idEntidad;
	private int idMunicipio;
	private String urlUbicacion;
	private int diaVigencia;
	private int mesvigencia;
	private int anioVigencia;
	private int idVigenciaOferta;
	
	private int idNivelEstudio;
	private int idOfertaCarrera;
	private int idCarreraEspecialidad;
	private int idSituacionAcademica;
	private String equipoTecnologia;
	private int aniosExperiencia;
	private int edadRequisito;
	private int edadMinima;
	private int edadMaxima;
	private int genero;
	private int idOfertaRequisitoConocimiento;
	private String conocimiento;
	private int idExperienciaConocimiento;
	private int idOfertaRequisitoHabilidad;
	private String habilidad;
	private int idExperienciaHabilidad;
	private int idOfertaIdioma;
	private int idIdioma;
	private int idCertificacionIdioma;
	private int idDominioIdioma;
	private int conocimientoCompNinguno;
	private int conocimientoCompProcesadorTxt;
	private int conocimientoCompHojaCal;
	private int conocimientoCompInternet;
	private int conocimientoCompRedes;
	private String conocimientoCompOtros;
	private int viajar;
	private int radicar;
	private String observaciones;
	private double salario;
	private long[] idPrestacion;
	private String empresaOfrece;
	private ConocimientoComputacionVO conocimientoComputacion;
	
	private int idEmpresa;
	private String nombreEmpresa;
	private String nombreContacto;
	private int idActividadEconomica;
	private String actividadEconomica;	
	private String correoElectronicoContacto;
	private String cargoContacto;
	private String calle;
	private String entreCalle;
	private String yCalle;
	private String numeroExterior;
	private String numeroInterior;
	
	private int idEntidadDomicilio;
	private int idMunicipioDomicilio;
	private int idColoniaDomicilio;
	private String codigoPostal;
	private int idContacto;
	private int contactoTelefono;
	private int contactoCorreo;
	private int contactoDomicilio;
	private String diasentrevista;
	private int idHorarioDe;
	private int idHorarioA;
	private int idDuracionAproximada;
	
	//Becate
	private OfertaEmpleoBecateVO ofertaEmpleoBecate;
	private ModalidadOfertaVO modalidadOferta;
	private String claveOferta;
	private int idCurso;
	private int idTipoRecurso;
	private int numeroPlazasBecate;
	private int idHorarioImparticion;
	private int idHorarioEntrada;
	private int idHorarioSalida;
	private Date fechaInicioBecate;
	private Date fechaFinBecate;
	private int idDuracion;
	private int idSalario;
	private int idModalidad;
	private double monto;	
	private boolean ofertaBecate;
	private int idModalidadOferta;
	private int discapacidad;
	
	//Termina Datos de registro
	
	private OfertaEmpleoVO ofertaEmpleoVo;
	
	private String[] gradosDependientes;
	private String[] idiomasDependientes;
	
	private List<CatalogoOpcionVO> prestaciones;
	private List<CatalogoOpcionVO> carrerasMultiRegistro;
	private List<CatalogoOpcionVO> idiomasMultiRegistro1;
	private List<CatalogoOpcionVO> idiomasMultiRegistro2;
	private List<CatalogoOpcionVO> idiomasMultiRegistro3;
	private List<CatalogoOpcionVO> catalogoIdiomas;
	private List<CatalogoOpcionVO> catalogoDominios;	
	
	private String accesoCelular;
	private String accesoFijo;
	
	private int idTipoTelefonoFijo;
	private int idTipoTelefonoCelular;
	
	private int idTelefono;
	private int tipoTelefono;
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;
	
	private List<OfertaCarreraEspecialidadVO> carrerasOferta;
	private List<OfertaRequisitoVO> conocimientos;
	private List<OfertaRequisitoVO> habilidades;
	private List<OfertaIdiomaVO> idiomas;
	private List<TelefonoVO> telefonos;
	private List<OfertaCarreraEspecialidadVO> carrerasAdicionales;
	private List<OfertaRequisitoVO> conocimientosAdicionales;
	private List<OfertaRequisitoVO> habilidadesAdicionales;
	private List<OfertaIdiomaVO> idiomasAdicionales;
	private List<TelefonoVO> telefonosAdicionales;
	
	private DomicilioVO domicilio;
	private int idDomicilio;
	
	private int totalCarrerasAdicionales;
	private int totalConocimientosAdicionales;
	private int totalHabilidadesAdicionales;
	private int totalIdiomasAdicionales;
	private int totalTelefonosAdicionales;
	
	private int domingo;
	private int lunes;
	private int martes;
	private int miercoles;
	private int jueves;
	private int viernes;
	private int sabado;
	private int fuente;
	
	private int discapacidadAuditiva;
	private int discapacidadIntelectual;
	private int discapacidadMental;
	private int discapacidadMotriz;
	private int discapacidadVisual;
	private int discapacidadNinguna;
	private String discapacidades;
	
	private Date fechaInicio;
	
	private boolean datosValidosEdicion;
	
	private long[] idHabilidad;
	private long codigo_universal_de_puesto_sfp;
	private boolean empresaSFP;

	private double salarioMinimoMensual;
	
	private long idperfilTipo;
	
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
	public int getNumeroPlazas() {
		return numeroPlazas;
	}
	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
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
	public int getIdTipoContrato() {
		return idTipoContrato;
	}
	public void setIdTipoContrato(int idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}
	public String getFechaVigencia() {
		return fechaVigencia;
	}
	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
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
	public int getRolarTurnos() {
		return rolarTurnos;
	}
	public void setRolarTurnos(int rolarTurnos) {
		this.rolarTurnos = rolarTurnos;
	}
	public String getDiasLaborales() {
		return diasLaborales;
	}
	public void setDiasLaborales(String diasLaborales) {
		this.diasLaborales = diasLaborales;
	}
	public int getAceptaDiscapacitados() {
		return aceptaDiscapacitados;
	}
	public void setAceptaDiscapacitados(int aceptaDiscapacitados) {
		this.aceptaDiscapacitados = aceptaDiscapacitados;
	}
	public int getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}
	public void setIdTipoDiscapacidad(int idDiscapacidad) {
		this.idTipoDiscapacidad = idDiscapacidad;
	}
	public int getIdCausaOferta() {
		return idCausaOferta;
	}
	public void setIdCausaOferta(int idCausaOferta) {
		this.idCausaOferta = idCausaOferta;
	}
	public int getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}
	public int getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getUrlUbicacion() {
		return urlUbicacion;
	}
	public void setUrlUbicacion(String urlUbicacion) {
		this.urlUbicacion = urlUbicacion;
	}
	public int getIdNivelEstudio() {
		return idNivelEstudio;
	}
	public void setIdNivelEstudio(int idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}
	public int getIdCarreraEspecialidad() {
		return idCarreraEspecialidad;
	}
	public void setIdCarreraEspecialidad(int idCarreraEspecialidad) {
		this.idCarreraEspecialidad = idCarreraEspecialidad;
	}
	public int getIdSituacionAcademica() {
		return idSituacionAcademica;
	}
	public void setIdSituacionAcademica(int idSituacionAcademica) {
		this.idSituacionAcademica = idSituacionAcademica;
	}
//	public String getHabilidadGeneral() {
//		return habilidadGeneral;
//	}
//	public void setHabilidadGeneral(String habilidadGeneral) {
//		this.habilidadGeneral = habilidadGeneral;
//	}
	public int getAniosExperiencia() {
		return aniosExperiencia;
	}
	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}
	public int getEdadRequisito() {
		return edadRequisito;
	}
	public void setEdadRequisito(int edadRequisito) {
		this.edadRequisito = edadRequisito;
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
	public String getConocimiento() {
		return conocimiento;
	}
	public void setConocimiento(String conocimiento) {
		this.conocimiento = conocimiento;
	}
	public int getIdExperienciaConocimiento() {
		return idExperienciaConocimiento;
	}
	public void setIdExperienciaConocimiento(int idExperienciaConocimiento) {
		this.idExperienciaConocimiento = idExperienciaConocimiento;
	}
	public String getHabilidad() {
		return habilidad;
	}
	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}
	public int getIdExperienciaHabilidad() {
		return idExperienciaHabilidad;
	}
	public void setIdExperienciaHabilidad(int idExperienciaHabilidad) {
		this.idExperienciaHabilidad = idExperienciaHabilidad;
	}
	public int getIdIdioma() {
		return idIdioma;
	}
	public void setIdIdioma(int idIdioma) {
		this.idIdioma = idIdioma;
	}
	public int getIdCertificacionIdioma() {
		return idCertificacionIdioma;
	}
	public void setIdCertificacionIdioma(int idCertificacionIdioma) {
		this.idCertificacionIdioma = idCertificacionIdioma;
	}
	public int getIdDominioIdioma() {
		return idDominioIdioma;
	}
	public void setIdDominioIdioma(int idDominioIdioma) {
		this.idDominioIdioma = idDominioIdioma;
	}
	public int getConocimientoCompNinguno() {
		return conocimientoCompNinguno;
	}
	public void setConocimientoCompNinguno(int conocimientoCompNinguno) {
		this.conocimientoCompNinguno = conocimientoCompNinguno;
	}
	public int getConocimientoCompProcesadorTxt() {
		return conocimientoCompProcesadorTxt;
	}
	public void setConocimientoCompProcesadorTxt(int conocimientoCompProcesadorTxt) {
		this.conocimientoCompProcesadorTxt = conocimientoCompProcesadorTxt;
	}
	public int getConocimientoCompHojaCal() {
		return conocimientoCompHojaCal;
	}
	public void setConocimientoCompHojaCal(int conocimientoCompHojaCal) {
		this.conocimientoCompHojaCal = conocimientoCompHojaCal;
	}
	public int getConocimientoCompInternet() {
		return conocimientoCompInternet;
	}
	public void setConocimientoCompInternet(int conocimientoCompInternet) {
		this.conocimientoCompInternet = conocimientoCompInternet;
	}
	public int getConocimientoCompRedes() {
		return conocimientoCompRedes;
	}
	public void setConocimientoCompRedes(int conocimientoCompRedes) {
		this.conocimientoCompRedes = conocimientoCompRedes;
	}
	public int getViajar() {
		return viajar;
	}
	public void setViajar(int viajar) {
		this.viajar = viajar;
	}
	public int getRadicar() {
		return radicar;
	}
	public void setRadicar(int radicar) {
		this.radicar = radicar;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public long[] getIdPrestacion() {
		return idPrestacion;
	}
	public void setIdPrestacion(long[] idPrestacion) {
		this.idPrestacion = idPrestacion;
	}
	public String getEmpresaOfrece() {
		return empresaOfrece;
	}
	public void setEmpresaOfrece(String empresaOfrece) {
		this.empresaOfrece = empresaOfrece;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getIdContacto() {
		return idContacto;
	}
	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}
	public int getContactoTelefono() {
		return contactoTelefono;
	}
	public void setContactoTelefono(int contactoTelefono) {
		this.contactoTelefono = contactoTelefono;
	}
	public int getContactoCorreo() {
		return contactoCorreo;
	}
	public void setContactoCorreo(int contactoCorreo) {
		this.contactoCorreo = contactoCorreo;
	}
	public int getContactoDomicilio() {
		return contactoDomicilio;
	}
	public void setContactoDomicilio(int contactoDomicilio) {
		this.contactoDomicilio = contactoDomicilio;
	}
	public String getDiasentrevista() {
		return diasentrevista;
	}
	public void setDiasentrevista(String diasentrevista) {
		this.diasentrevista = diasentrevista;
	}
	public int getIdHorarioDe() {
		return idHorarioDe;
	}
	public void setIdHorarioDe(int idHorarioDe) {
		this.idHorarioDe = idHorarioDe;
	}
	public int getIdHorarioA() {
		return idHorarioA;
	}
	public void setIdHorarioA(int idHorarioA) {
		this.idHorarioA = idHorarioA;
	}
	public int getIdDuracionAproximada() {
		return idDuracionAproximada;
	}
	public void setIdDuracionAproximada(int idDuracionAproximada) {
		this.idDuracionAproximada = idDuracionAproximada;
	}
	public void setIdOfertaEmpleo(int idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}
	public int getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}
	public void setOfertaEmpleoVo(OfertaEmpleoVO ofertaEmpleoVo) {
		this.ofertaEmpleoVo = ofertaEmpleoVo;
	}
	public OfertaEmpleoVO getOfertaEmpleoVo() {
		return ofertaEmpleoVo;
	}
	public String[] getGradosDependientes() {
		return gradosDependientes;
	}
	public void setGradosDependientes(String[] gradosDependientes) {
		this.gradosDependientes = gradosDependientes;
	}
	public String[] getIdiomasDependientes() {
		return idiomasDependientes;
	}
	public void setIdiomasDependientes(String[] idiomasDependientes) {
		this.idiomasDependientes = idiomasDependientes;
	}
	public String getConocimientoCompOtros() {
		return conocimientoCompOtros;
	}
	public void setConocimientoCompOtros(String conocimientoCompOtros) {
		this.conocimientoCompOtros = conocimientoCompOtros;
	}
	public void setPrestaciones(List<CatalogoOpcionVO> prestaciones) {
		this.prestaciones = prestaciones;
	}
	public List<CatalogoOpcionVO> getPrestaciones() {
		return prestaciones;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setIdActividadEconomica(int idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}
	public int getIdActividadEconomica() {
		return idActividadEconomica;
	}
	public String getAccesoCelular() {
		return accesoCelular;
	}
	public void setAccesoCelular(String accesoCelular) {
		this.accesoCelular = accesoCelular;
	}
	public String getAccesoFijo() {
		return accesoFijo;
	}
	public void setAccesoFijo(String accesoFijo) {
		this.accesoFijo = accesoFijo;
	}
	public int getIdTipoTelefonoFijo() {
		return idTipoTelefonoFijo;
	}
	public void setIdTipoTelefonoFijo(int idTipoTelefonoFijo) {
		this.idTipoTelefonoFijo = idTipoTelefonoFijo;
	}
	public int getIdTipoTelefonoCelular() {
		return idTipoTelefonoCelular;
	}
	public void setIdTipoTelefonoCelular(int idTipoTelefonoCelular) {
		this.idTipoTelefonoCelular = idTipoTelefonoCelular;
	}
	public int getTipoTelefono() {
		return tipoTelefono;
	}
	public void setTipoTelefono(int tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
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
	

	
	public String getCorreoElectronicoContacto() {
		return correoElectronicoContacto;
	}
	public void setCorreoElectronicoContacto(String correoElectronicoContacto) {
		this.correoElectronicoContacto = correoElectronicoContacto;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getEntreCalle() {
		return entreCalle;
	}
	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}
	public String getyCalle() {
		return yCalle;
	}
	public void setyCalle(String yCalle) {
		this.yCalle = yCalle;
	}
	public String getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public String getNumeroInterior() {
		return numeroInterior;
	}
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	public int getIdEntidadDomicilio() {
		return idEntidadDomicilio;
	}
	public void setIdEntidadDomicilio(int idEntidadDomicilio) {
		this.idEntidadDomicilio = idEntidadDomicilio;
	}
	public int getIdMunicipioDomicilio() {
		return idMunicipioDomicilio;
	}
	public void setIdMunicipioDomicilio(int idMunicipioDomicilio) {
		this.idMunicipioDomicilio = idMunicipioDomicilio;
	}
	public int getIdColoniaDomicilio() {
		return idColoniaDomicilio;
	}
	public void setIdColoniaDomicilio(int idColoniaDomicilio) {
		this.idColoniaDomicilio = idColoniaDomicilio;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	
	
	public int getDiaVigencia() {
		return diaVigencia;
	}
	public void setDiaVigencia(int diaVigencia) {
		this.diaVigencia = diaVigencia;
	}
	public int getMesvigencia() {
		return mesvigencia;
	}
	public void setMesvigencia(int mesvigencia) {
		this.mesvigencia = mesvigencia;
	}
	public int getAnioVigencia() {
		return anioVigencia;
	}
	public void setAnioVigencia(int anioVigencia) {
		this.anioVigencia = anioVigencia;
	}
	
	public void setCarrerasOferta(List<OfertaCarreraEspecialidadVO> carrerasOferta) {
		this.carrerasOferta = carrerasOferta;
	}
	public List<OfertaCarreraEspecialidadVO> getCarrerasOferta() {
		return carrerasOferta;
	}
	public void reset() {
		//Inicia Datos de registro	
		tituloOferta=null;
		idOcupacion=0;
		numeroPlazas=0;
		funciones=null;
		idTipoEmpleo=0;
		idTipoContrato=0;
		fechaVigencia=null;
		horaEntrada=null;
		horaSalida=null;
		rolarTurnos=0;
		diasLaborales=null;
		aceptaDiscapacitados=0;
		idTipoDiscapacidad=0;
		idCausaOferta=0;
		idEntidad=0;
		idMunicipio=0;
		urlUbicacion=null;
		diaVigencia=0;
		mesvigencia=0;
		anioVigencia=0;
		
		idNivelEstudio=0;
		idCarreraEspecialidad=0;
		idSituacionAcademica=0;
//		habilidadGeneral=null;
		aniosExperiencia=0;
		edadRequisito=0;
		edadMinima=0;
		edadMaxima=0;
		genero=0;
		conocimiento=null;
		idExperienciaConocimiento=0;
		habilidad=null;
		idExperienciaHabilidad=0;
		idIdioma=0;
		idCertificacionIdioma=0;
		idDominioIdioma=0;
		conocimientoCompNinguno=0;
		conocimientoCompProcesadorTxt=0;
		conocimientoCompHojaCal=0;
		conocimientoCompInternet=0;
		conocimientoCompRedes=0;
		conocimientoCompOtros=null;
		viajar=0;
		radicar=0;
		observaciones=null;
		salario=0;
		idPrestacion=null;
		empresaOfrece=null;
		carrerasOferta=null;
		idOfertaRequisitoConocimiento=0;
		idOfertaRequisitoHabilidad=0;
		habilidades=null;
		conocimientos=null;
		idOfertaIdioma=0;
		idiomas=null;
		
		idEmpresa=0;
		nombreEmpresa=null;
		idActividadEconomica=0;
		actividadEconomica=null;
		correoElectronicoContacto=null;
		calle=null;
		entreCalle=null;
		yCalle=null;
		numeroExterior=null;
		numeroInterior=null;
		
		idEntidadDomicilio=0;
		idMunicipioDomicilio=0;
		idColoniaDomicilio=0;
		codigoPostal=null;
		idContacto=0;
		contactoTelefono=0;
		contactoCorreo=0;
		contactoDomicilio=0;
		diasentrevista=null;
		idHorarioDe=0;
		idHorarioA=0;
		idDuracionAproximada=0;
		claveOferta=null;
		idCurso=0;
		idTipoRecurso=0;
		numeroPlazasBecate=0;
		idHorarioImparticion=0;
		idHorarioEntrada=0;
		idHorarioSalida=0;
		fechaInicioBecate=null;
		fechaFinBecate=null;
		idDuracion=0;
		idSalario=0;
		idModalidad=0;
		monto=0;
		ofertaBecate=false;
		//Termina Datos de reguistro
		
		ofertaEmpleoVo=null;
		ofertaEmpleoBecate = null;
		modalidadOferta = null;
		
		gradosDependientes=null;
		idiomasDependientes=null;
		
		prestaciones= null;
		
		
		accesoCelular=null;
		accesoFijo=null;
		
		idTipoTelefonoFijo=0;
		idTipoTelefonoCelular=0;
		
		tipoTelefono=0;
		acceso=null;
		clave=null;
		telefono=null;
		extension=null;
		idOfertaCarrera=0;
		telefonos=null;
		idTelefono=0;
		
		nombreContacto=null;
		domicilio=null;
		idDomicilio=0;
		carrerasAdicionales=null;
		totalCarrerasAdicionales=0;
		conocimientosAdicionales=null;
		totalConocimientosAdicionales=0;
		habilidadesAdicionales=null;
		totalHabilidadesAdicionales=0;
		idiomasAdicionales=null;
		totalIdiomasAdicionales=0;
		telefonosAdicionales=null;
		totalTelefonosAdicionales=0;
		cargoContacto=null;
		
		
		domingo=0;
		lunes=0;
		martes=0;
		miercoles=0;
		jueves=0;
		viernes=0;
		sabado=0;
		
		discapacidadAuditiva=0;
		discapacidadIntelectual=0;
		discapacidadMental=0;
		discapacidadMotriz=0;
		discapacidadVisual=0;
		discapacidadNinguna=0;		
		
		carrerasMultiRegistro=null;
		idiomasMultiRegistro1=null;
		idiomasMultiRegistro3=null;
		idiomasMultiRegistro2=null;
		
		ocupacion =null;
		conocimientoComputacion=null;
		
		idOfertaEmpleo=0;
		
		datosValidosEdicion = true;
		
		idVigenciaOferta=0;
		
		idHabilidad = null;
	
		idOfertaEmpleoOriginal = 0;

		salarioMinimoMensual = 0D;
	}
	public void setIdOfertaCarrera(int idOfertaCarrera) {
		this.idOfertaCarrera = idOfertaCarrera;
	}
	public int getIdOfertaCarrera() {
		return idOfertaCarrera;
	}
	public void setIdOfertaRequisitoConocimiento(
			int idOfertaRequisitoConocimiento) {
		this.idOfertaRequisitoConocimiento = idOfertaRequisitoConocimiento;
	}
	public int getIdOfertaRequisitoConocimiento() {
		return idOfertaRequisitoConocimiento;
	}
	public void setIdOfertaRequisitoHabilidad(int idOfertaRequisitoHabilidad) {
		this.idOfertaRequisitoHabilidad = idOfertaRequisitoHabilidad;
	}
	public int getIdOfertaRequisitoHabilidad() {
		return idOfertaRequisitoHabilidad;
	}
	public void setConocimientos(List<OfertaRequisitoVO> conocimientos) {
		this.conocimientos = conocimientos;
	}
	public List<OfertaRequisitoVO> getConocimientos() {
		return conocimientos;
	}
	public void setHabilidades(List<OfertaRequisitoVO> habilidades) {
		this.habilidades = habilidades;
	}
	public List<OfertaRequisitoVO> getHabilidades() {
		return habilidades;
	}
	public int getIdOfertaIdioma() {
		return idOfertaIdioma;
	}
	public void setIdOfertaIdioma(int idOfertaIdioma) {
		this.idOfertaIdioma = idOfertaIdioma;
	}
	public List<OfertaIdiomaVO> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(List<OfertaIdiomaVO> idiomas) {
		this.idiomas = idiomas;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public String getNombreContacto() {
		return nombreContacto;
	}
	public int getIdTelefono() {
		return idTelefono;
	}
	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}
	public List<TelefonoVO> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<TelefonoVO> telefonos) {
		this.telefonos = telefonos;
	}
	public DomicilioVO getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
	}
	public int getIdDomicilio() {
		return idDomicilio;
	}
	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	public List<OfertaCarreraEspecialidadVO> getCarrerasAdicionales() {
		return carrerasAdicionales;
	}
	public void setCarrerasAdicionales(
			List<OfertaCarreraEspecialidadVO> carrerasAdicionales) {
		this.carrerasAdicionales = carrerasAdicionales;
	}
	public void setTotalCarrerasAdicionales(int totalCarrerasAdicionales) {
		this.totalCarrerasAdicionales = totalCarrerasAdicionales;
	}
	public int getTotalCarrerasAdicionales() {
		return totalCarrerasAdicionales;
	}
	public void setConocimientosAdicionales(List<OfertaRequisitoVO> conocimientosAdicionales) {
		this.conocimientosAdicionales = conocimientosAdicionales;
	}
	public List<OfertaRequisitoVO> getConocimientosAdicionales() {
		return conocimientosAdicionales;
	}
	public void setTotalConocimientosAdicionales(
			int totalConocimientosAdicionales) {
		this.totalConocimientosAdicionales = totalConocimientosAdicionales;
	}
	public int getTotalConocimientosAdicionales() {
		return totalConocimientosAdicionales;
	}
	public void setHabilidadesAdicionales(List<OfertaRequisitoVO> habilidadesAdicionales) {
		this.habilidadesAdicionales = habilidadesAdicionales;
	}
	public List<OfertaRequisitoVO> getHabilidadesAdicionales() {
		return habilidadesAdicionales;
	}
	public void setTotalHabilidadesAdicionales(int totalHabilidadesAdicionales) {
		this.totalHabilidadesAdicionales = totalHabilidadesAdicionales;
	}
	public int getTotalHabilidadesAdicionales() {
		return totalHabilidadesAdicionales;
	}
	public void setIdiomasAdicionales(List<OfertaIdiomaVO> idiomasAdicionales) {
		this.idiomasAdicionales = idiomasAdicionales;
	}
	public List<OfertaIdiomaVO> getIdiomasAdicionales() {
		return idiomasAdicionales;
	}
	public void setTotalIdiomasAdicionales(int totalIdiomasAdicionales) {
		this.totalIdiomasAdicionales = totalIdiomasAdicionales;
	}
	public int getTotalIdiomasAdicionales() {
		return totalIdiomasAdicionales;
	}
	public List<TelefonoVO> getTelefonosAdicionales() {
		return telefonosAdicionales;
	}
	public void setTelefonosAdicionales(List<TelefonoVO> telefonosAdicionales) {
		this.telefonosAdicionales = telefonosAdicionales;
	}
	public int getTotalTelefonosAdicionales() {
		return totalTelefonosAdicionales;
	}
	public void setTotalTelefonosAdicionales(int totalTelefonosAdicionales) {
		this.totalTelefonosAdicionales = totalTelefonosAdicionales;
	}
	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}
	public String getCargoContacto() {
		return cargoContacto;
	}
	public int getDomingo() {
		return domingo;
	}
	public void setDomingo(int domingo) {
		this.domingo = domingo;
	}
	public int getLunes() {
		return lunes;
	}
	public void setLunes(int lunes) {
		this.lunes = lunes;
	}
	public int getMartes() {
		return martes;
	}
	public void setMartes(int martes) {
		this.martes = martes;
	}
	public int getMiercoles() {
		return miercoles;
	}
	public void setMiercoles(int miercoles) {
		this.miercoles = miercoles;
	}
	public int getJueves() {
		return jueves;
	}
	public void setJueves(int jueves) {
		this.jueves = jueves;
	}
	public int getViernes() {
		return viernes;
	}
	public void setViernes(int viernes) {
		this.viernes = viernes;
	}
	public int getSabado() {
		return sabado;
	}
	public void setSabado(int sabado) {
		this.sabado = sabado;
	}
	
	public int getDiscapacidadAuditiva() {
		return discapacidadAuditiva;
	}
	public void setDiscapacidadAuditiva(int discapacidadAuditiva) {
		this.discapacidadAuditiva = discapacidadAuditiva;
	}
	public int getDiscapacidadIntelectual() {
		return discapacidadIntelectual;
	}
	public void setDiscapacidadIntelectual(int discapacidadIntelectual) {
		this.discapacidadIntelectual = discapacidadIntelectual;
	}
	public int getDiscapacidadMental() {
		return discapacidadMental;
	}
	public void setDiscapacidadMental(int discapacidadMental) {
		this.discapacidadMental = discapacidadMental;
	}
	public int getDiscapacidadMotriz() {
		return discapacidadMotriz;
	}
	public void setDiscapacidadMotriz(int discapacidadMotriz) {
		this.discapacidadMotriz = discapacidadMotriz;
	}
	public int getDiscapacidadVisual() {
		return discapacidadVisual;
	}
	public void setDiscapacidadVisual(int discapacidadVisual) {
		this.discapacidadVisual = discapacidadVisual;
	}
	public int getDiscapacidadNinguna() {
		return discapacidadNinguna;
	}
	public void setDiscapacidadNinguna(int discapacidadNinguna) {
		this.discapacidadNinguna = discapacidadNinguna;
	}	
	public String getDiscapacidades() {
		return discapacidades;
	}
	public void setDiscapacidades(String discapacidades) {
		this.discapacidades = discapacidades;
	}
	public void setCarrerasMultiRegistro(List<CatalogoOpcionVO> carrerasMultiRegistro) {
		this.carrerasMultiRegistro = carrerasMultiRegistro;
	}
	public List<CatalogoOpcionVO> getCarrerasMultiRegistro() {
		return carrerasMultiRegistro;
	}
	public List<CatalogoOpcionVO> getIdiomasMultiRegistro1() {
		return idiomasMultiRegistro1;
	}
	public void setIdiomasMultiRegistro1(
			List<CatalogoOpcionVO> idiomasMultiRegistro1) {
		this.idiomasMultiRegistro1 = idiomasMultiRegistro1;
	}
	public List<CatalogoOpcionVO> getIdiomasMultiRegistro2() {
		return idiomasMultiRegistro2;
	}
	public void setIdiomasMultiRegistro2(
			List<CatalogoOpcionVO> idiomasMultiRegistro2) {
		this.idiomasMultiRegistro2 = idiomasMultiRegistro2;
	}
	public List<CatalogoOpcionVO> getIdiomasMultiRegistro3() {
		return idiomasMultiRegistro3;
	}
	public void setIdiomasMultiRegistro3(
			List<CatalogoOpcionVO> idiomasMultiRegistro3) {
		this.idiomasMultiRegistro3 = idiomasMultiRegistro3;
	}
	public List<CatalogoOpcionVO> getCatalogoIdiomas() {
		return catalogoIdiomas;
	}
	public void setCatalogoIdiomas(List<CatalogoOpcionVO> catalogoIdiomas) {
		this.catalogoIdiomas = catalogoIdiomas;
	}
	public List<CatalogoOpcionVO> getCatalogoDominios() {
		return catalogoDominios;
	}
	public void setCatalogoDominios(List<CatalogoOpcionVO> catalogoDominios) {
		this.catalogoDominios = catalogoDominios;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFuente(int fuente) {
		this.fuente = fuente;
	}
	public int getFuente() {
		return fuente;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setConocimientoComputacion(ConocimientoComputacionVO conocimientoComputacion) {
		this.conocimientoComputacion = conocimientoComputacion;
	}
	public ConocimientoComputacionVO getConocimientoComputacion() {
		return conocimientoComputacion;
	}
	public boolean isDatosValidosEdicion() {
		return datosValidosEdicion;
	}
	public void setDatosValidosEdicion(boolean datosValidosEdicion) {
		this.datosValidosEdicion = datosValidosEdicion;
	}
	public void setIdVigenciaOferta(int idVigenciaOferta) {
		this.idVigenciaOferta = idVigenciaOferta;
	}
	public int getIdVigenciaOferta() {
		return idVigenciaOferta;
	}
	public long[] getIdHabilidad() {
		return idHabilidad;
	}
	public void setIdHabilidad(long[] idHabilidad) {
		this.idHabilidad = idHabilidad;
	}
	public long getIdOfertaEmpleoOriginal() {
		return idOfertaEmpleoOriginal;
	}
	public void setIdOfertaEmpleoOriginal(long idOfertaEmpleoOriginal) {
		this.idOfertaEmpleoOriginal = idOfertaEmpleoOriginal;
	}
	public long getCodigo_universal_de_puesto_sfp() {
		return codigo_universal_de_puesto_sfp;
	}
	public void setCodigo_universal_de_puesto_sfp(long codigo_universal_de_puesto_sfp) {
		this.codigo_universal_de_puesto_sfp = codigo_universal_de_puesto_sfp;
	}
	public boolean getEmpresaSFP() {
		return empresaSFP;
	}
	public void setEmpresaSFP(boolean empresaSFP) {
		this.empresaSFP = empresaSFP;
	}
	public String getActividadEconomica() {
		return actividadEconomica;
	}
	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}
	
	public OfertaEmpleoBecateVO getOfertaEmpleoBecate() {
		return ofertaEmpleoBecate;
	}
	public void setOfertaEmpleoBecate(OfertaEmpleoBecateVO ofertaEmpleoBecate) {
		this.ofertaEmpleoBecate = ofertaEmpleoBecate;
	}
	
	public boolean isOfertaBecate() {
		return ofertaBecate;
	}
	public void setOfertaBecate(boolean ofertaBecate) {
		this.ofertaBecate = ofertaBecate;
	}
	public String getClaveOferta() {
		return claveOferta;
	}
	public void setClaveOferta(String claveOferta) {
		this.claveOferta = claveOferta;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public int getIdTipoRecurso() {
		return idTipoRecurso;
	}
	public void setIdTipoRecurso(int idTipoRecurso) {
		this.idTipoRecurso = idTipoRecurso;
	}
	public int getNumeroPlazasBecate() {
		return numeroPlazasBecate;
	}
	public void setNumeroPlazasBecate(int numeroPlazasBecate) {
		this.numeroPlazasBecate = numeroPlazasBecate;
	}
	public int getIdHorarioImparticion() {
		return idHorarioImparticion;
	}
	public void setIdHorarioImparticion(int idHorarioImparticion) {
		this.idHorarioImparticion = idHorarioImparticion;
	}
	public int getIdHorarioEntrada() {
		return idHorarioEntrada;
	}
	public void setIdHorarioEntrada(int idHorarioEntrada) {
		this.idHorarioEntrada = idHorarioEntrada;
	}
	public int getIdHorarioSalida() {
		return idHorarioSalida;
	}
	public void setIdHorarioSalida(int idHorarioSalida) {
		this.idHorarioSalida = idHorarioSalida;
	}
	public Date getFechaInicioBecate() {
		return fechaInicioBecate;
	}
	public void setFechaInicioBecate(Date fechaInicioBecate) {
		this.fechaInicioBecate = fechaInicioBecate;
	}
	public Date getFechaFinBecate() {
		return fechaFinBecate;
	}
	public void setFechaFinBecate(Date fechaFinBecate) {
		this.fechaFinBecate = fechaFinBecate;
	}
	public int getIdDuracion() {
		return idDuracion;
	}
	public void setIdDuracion(int idDuracion) {
		this.idDuracion = idDuracion;
	}
	public int getIdSalario() {
		return idSalario;
	}
	public void setIdSalario(int idSalario) {
		this.idSalario = idSalario;
	}
	public int getIdModalidad() {
		return idModalidad;
	}
	public void setIdModalidad(int idModalidad) {
		this.idModalidad = idModalidad;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public int getIdModalidadOferta() {
		return idModalidadOferta;
	}
	public void setIdModalidadOferta(int idModalidadOferta) {
		this.idModalidadOferta = idModalidadOferta;
	}
	public ModalidadOfertaVO getModalidadOferta() {
		return modalidadOferta;
	}
	public void setModalidadOferta(ModalidadOfertaVO modalidadOferta) {
		this.modalidadOferta = modalidadOferta;
	}
	public int getDiscapacidad() {
		return discapacidad;
	}
	public void setDiscapacidad(int discapacidad) {
		this.discapacidad = discapacidad;
	}

	public double getSalarioMinimoMensual() {
		return salarioMinimoMensual;
	}

	public void setSalarioMinimoMensual(double salarioMinimoMensual) {
		this.salarioMinimoMensual = salarioMinimoMensual;
	}
	public long getIdperfilTipo() {
		return idperfilTipo;
	}
	public void setIdperfilTipo(long idperfilTipo) {
		this.idperfilTipo = idperfilTipo;
	}
	public String getEquipoTecnologia() {
		return equipoTecnologia;
	}
	public void setEquipoTecnologia(String equipoTecnologia) {
		this.equipoTecnologia = equipoTecnologia;
	}
}