package mx.gob.stps.portal.web.oferta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class OfertaEmpleoVO implements Serializable {
	private static final long serialVersionUID = -189816810865526986L;

	private int idTipoEmpleo;
	
	protected int idOfertaEmpleo;

	protected int idEmpresa; 

	protected String tituloOferta;

	protected int idAreaLaboral;

	protected int idOcupacion;

	protected String funciones;

	protected int idHorarioEmpleo;

	protected String diasLaborales;

	protected String horaEntrada;

	protected String horaSalida;

	protected int rolarTurno;

	protected String empresaOfrece;

	protected float salario;

	protected int idTipoContrato;

	protected int idJerarquia;

	protected int numeroPlazas;

	protected int limitePostulantes;

	protected int idDiscapacidad;

	protected int idCausaVacante;

	protected Date fechaInicio;

	protected Date fechaFin;

	protected int disponibilidadViajar;

	protected int disponibilidadRadicar;

	protected int idNivelEstudio;

	protected int idSituacionAcademica;

	protected String habilidadGeneral;

	protected int experienciaAnios;

	protected int edadRequisito;

	protected int edadMinima;

	protected int edadMaxima;

	protected int genero;

	protected String mapaUbicacion;

	protected String observaciones;

	protected int idTerceraEmpresa;

	protected int idContacto;

	protected int idMedioContacto;

	protected int idHorarioDe;

	protected int idHorarioA;

	protected int idDuracionAproximada;

	protected String diasEntrevista;

	protected int fuente;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaModificacion;

	protected ArrayList<OfertaSectorVO> sectores;

	protected ArrayList<OfertaPrestacionVO> prestaciones;
	
	protected ArrayList<OfertaUbicacionVO> ubicaciones;
	
	protected ArrayList<OfertaCarreraEspecVO> carrerasEspecialidades;
	
	protected ArrayList<OfertaRequisitoVO> conocimientos;
	
	protected ArrayList<OfertaRequisitoVO> habilidades;
	
	protected ArrayList<OfertaRequisitoVO> competencias;
	
	protected ArrayList<OfertaIdiomaVO> idiomas;
	
	private int domingo;
	
	private int lunes;
	
	private int martes;
	
	private int miercoles;
	
	private int jueves;
	
	private int viernes;
	
	private int sabado;
	
	
	
	
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

	public ArrayList<OfertaSectorVO> getSectores()
	{
		return sectores;
	}

	public void setPrestaciones(ArrayList<OfertaPrestacionVO> prestaciones)
	{
		this.prestaciones = prestaciones;
	}
	
	public ArrayList<OfertaPrestacionVO> getPrestaciones()
	{
		return prestaciones;
	}

	public void setSectores(ArrayList<OfertaSectorVO> sectores)
	{
		this.sectores = sectores;
	}
	
	
	public ArrayList<OfertaUbicacionVO> getUbicaciones()
	{
		return ubicaciones;
	}

	public void setUbicaciones(ArrayList<OfertaUbicacionVO> ubicaciones)
	{
		this.ubicaciones = ubicaciones;
	}
	
	
	public ArrayList<OfertaCarreraEspecVO> getCarrerasEspecialidades()
	{
		return carrerasEspecialidades;
	}

	public void setCarrerasEspecialidades(ArrayList<OfertaCarreraEspecVO> carrerasEspecialidades)
	{
		this.carrerasEspecialidades = carrerasEspecialidades;
	}
	
	
	public ArrayList<OfertaRequisitoVO> getConocimientos()
	{
		return conocimientos;
	}

	public void setConocimientos(ArrayList<OfertaRequisitoVO> conocimientos)
	{
		this.conocimientos = conocimientos;
	}
	
	
	public ArrayList<OfertaRequisitoVO> getHabilidades()
	{
		return habilidades;
	}

	public void setHabilidades(ArrayList<OfertaRequisitoVO> habilidades)
	{
		this.habilidades = habilidades;
	}
	
	public ArrayList<OfertaRequisitoVO> getCompetencias()
	{
		return competencias;
	}

	public void setCompetencias(ArrayList<OfertaRequisitoVO> competencias)
	{
		this.competencias = competencias;
	}
	
	public ArrayList<OfertaIdiomaVO> getIdiomas()
	{
		return idiomas;
	}

	public void setIdiomas(ArrayList<OfertaIdiomaVO> idiomas)
	{
		this.idiomas = idiomas;
	}
	
	
	
	public int getIdOfertaEmpleo()
	{
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(int idOfertaEmpleo)
	{
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	
	public int getIdEmpresa()
	{
		return idEmpresa;
	}

	
	public void setIdEmpresa(int idEmpresa)
	{
		this.idEmpresa = idEmpresa;
	}

	
	public String getTituloOferta()
	{
		return tituloOferta;
	}

	
	public void setTituloOferta(String tituloOferta)
	{
		this.tituloOferta = tituloOferta;
	}

	
	public int getIdAreaLaboral()
	{
		return idAreaLaboral;
	}

	
	public void setIdAreaLaboral(int idAreaLaboral)
	{
		this.idAreaLaboral = idAreaLaboral;
	}

	
	public int getIdOcupacion()
	{
		return idOcupacion;
	}

	
	public void setIdOcupacion(int idOcupacion)
	{
		this.idOcupacion = idOcupacion;
	}

	
	
	public String getFunciones()
	{
		return funciones;
	}

	
	public void setFunciones(String funciones)
	{
		this.funciones = funciones;
	}

	
	public int getIdHorarioEmpleo()
	{
		return idHorarioEmpleo;
	}

	
	public void setIdHorarioEmpleo(int idHorarioEmpleo)
	{
		this.idHorarioEmpleo = idHorarioEmpleo;
	}

	
	public String getDiasLaborales()
	{
		return diasLaborales;
	}

	
	public void setDiasLaborales(String diasLaborales)
	{
		this.diasLaborales = diasLaborales;
	}

	
	public String getHoraEntrada()
	{
		return horaEntrada;
	}

	
	public void setHoraEntrada(String horaEntrada)
	{
		this.horaEntrada = horaEntrada;
	}

	
	public String getHoraSalida()
	{
		return horaSalida;
	}

	
	public void setHoraSalida(String horaSalida)
	{
		this.horaSalida = horaSalida;
	}

	
	public int getRolarTurno()
	{
		return rolarTurno;
	}

	
	public void setRolarTurno(int rolarTurno)
	{
		this.rolarTurno = rolarTurno;
	}

	
	public String getEmpresaOfrece()
	{
		return empresaOfrece;
	}

	
	public void setEmpresaOfrece(String empresaOfrece)
	{
		this.empresaOfrece = empresaOfrece;
	}

	
	public float getSalario()
	{
		return salario;
	}

	
	public void setSalario(float salario)
	{
		this.salario = salario;
	}

	
	public int getIdTipoContrato()
	{
		return idTipoContrato;
	}

	
	public void setIdTipoContrato(int idTipoContrato)
	{
		this.idTipoContrato = idTipoContrato;
	}

	
	public int getIdJerarquia()
	{
		return idJerarquia;
	}

	
	public void setIdJerarquia(int idJerarquia)
	{
		this.idJerarquia = idJerarquia;
	}

	
	public int getNumeroPlazas()
	{
		return numeroPlazas;
	}

	
	public void setNumeroPlazas(int numeroPlazas)
	{
		this.numeroPlazas = numeroPlazas;
	}

	
	public int getLimitePostulantes()
	{
		return limitePostulantes;
	}

	
	public void setLimitePostulantes(int limitePostulantes)
	{
		this.limitePostulantes = limitePostulantes;
	}

	
	public int getIdDiscapacidad()
	{
		return idDiscapacidad;
	}

	
	public void setIdDiscapacidad(int idDiscapacidad)
	{
		this.idDiscapacidad = idDiscapacidad;
	}

	
	public int getIdCausaVacante()
	{
		return idCausaVacante;
	}

	
	public void setIdCausaVacante(int idCausaVacante)
	{
		this.idCausaVacante = idCausaVacante;
	}

	
	public Date getFechaInicio()
	{
		return fechaInicio;
	}

	
	public void setFechaInicio(Date fechaInicio)
	{
		this.fechaInicio = fechaInicio;
	}

	
	public Date getFechaFin()
	{
		return fechaFin;
	}

	
	public void setFechaFin(Date fechaFin)
	{
		this.fechaFin = fechaFin;
	}

	
	public int getDisponibilidadViajar()
	{
		return disponibilidadViajar;
	}


	public void setDisponibilidadViajar(int disponibilidadViajar)
	{
		this.disponibilidadViajar = disponibilidadViajar;
	}

	
	public int getDisponibilidadRadicar()
	{
		return disponibilidadRadicar;
	}

	
	public void setDisponibilidadRadicar(int disponibilidadRadicar)
	{
		this.disponibilidadRadicar = disponibilidadRadicar;
	}

	
	public int getIdNivelEstudio()
	{
		return idNivelEstudio;
	}

	
	public void setIdNivelEstudio(int idNivelEstudio)
	{
		this.idNivelEstudio = idNivelEstudio;
	}

	
	public int getIdSituacionAcademica()
	{
		return idSituacionAcademica;
	}

	
	public void setIdSituacionAcademica(int idSituacionAcademica)
	{
		this.idSituacionAcademica = idSituacionAcademica;
	}

	
	public String getHabilidadGeneral()
	{
		return habilidadGeneral;
	}

	
	public void setHabilidadGeneral(String habilidadGeneral)
	{
		this.habilidadGeneral = habilidadGeneral;
	}


	public int getExperienciaAnios()
	{
		return experienciaAnios;
	}

	
	public void setExperienciaAnios(int experienciaAnios)
	{
		this.experienciaAnios = experienciaAnios;
	}

	
	public int getEdadRequisito()
	{
		return edadRequisito;
	}

	
	public void setEdadRequisito(int edadRequisito)
	{
		this.edadRequisito = edadRequisito;
	}

	
	public int getEdadMinima()
	{
		return edadMinima;
	}

	
	public void setEdadMinima(int edadMinima)
	{
		this.edadMinima = edadMinima;
	}

	
	public int getEdadMaxima()
	{
		return edadMaxima;
	}

	
	public void setEdadMaxima(int edadMaxima)
	{
		this.edadMaxima = edadMaxima;
	}

	
	public int getGenero()
	{
		return genero;
	}

	
	public void setGenero(int genero)
	{
		this.genero = genero;
	}

	
	public String getMapaUbicacion()
	{
		return mapaUbicacion;
	}

	
	public void setMapaUbicacion(String mapaUbicacion)
	{
		this.mapaUbicacion = mapaUbicacion;
	}

	

	public String getObservaciones()
	{
		return observaciones;
	}

	
	public void setObservaciones(String observaciones)
	{
		this.observaciones = observaciones;
	}

	
	public int getIdTerceraEmpresa()
	{
		return idTerceraEmpresa;
	}

	
	public void setIdTerceraEmpresa(int idTerceraEmpresa)
	{
		this.idTerceraEmpresa = idTerceraEmpresa;
	}

	
	public int getIdContacto()
	{
		return idContacto;
	}

	
	public void setIdContacto(int idContacto)
	{
		this.idContacto = idContacto;
	}

	
	public int getIdMedioContacto()
	{
		return idMedioContacto;
	}

	
	public void setIdMedioContacto(int idMedioContacto)
	{
		this.idMedioContacto = idMedioContacto;
	}

	
	public int getIdHorarioDe()
	{
		return idHorarioDe;
	}

	
	public void setIdHorarioDe(int idHorarioDe)
	{
		this.idHorarioDe = idHorarioDe;
	}

	
	public int getIdHorarioA()
	{
		return idHorarioA;
	}

	
	public void setIdHorarioA(int idHorarioA)
	{
		this.idHorarioA = idHorarioA;
	}

	
	public int getIdDuracionAproximada()
	{
		return idDuracionAproximada;
	}

	
	public void setIdDuracionAproximada(int idDuracionAproximada)
	{
		this.idDuracionAproximada = idDuracionAproximada;
	}

	
	public String getDiasEntrevista()
	{
		return diasEntrevista;
	}

	
	public void setDiasEntrevista(String diasEntrevista)
	{
		this.diasEntrevista = diasEntrevista;
	}

	
	public int getFuente()
	{
		return fuente;
	}

	
	public void setFuente(int fuente)
	{
		this.fuente = fuente;
	}

	
	public Date getFechaAlta()
	{
		return fechaAlta;
	}

	
	public void setFechaAlta(Date fechaAlta)
	{
		this.fechaAlta = fechaAlta;
	}

	
	public int getEstatus()
	{
		return estatus;
	}

	
	public void setEstatus(int estatus)
	{
		this.estatus = estatus;
	}

	
	public Date getFechaModificacion()
	{
		return fechaModificacion;
	}

	
	public void setFechaModificacion(Date fechaModificacion)
	{
		this.fechaModificacion = fechaModificacion;
	}

	public void setIdTipoEmpleo(int idTipoEmpleo) {
		this.idTipoEmpleo = idTipoEmpleo;
	}

	public int getIdTipoEmpleo() {
		return idTipoEmpleo;
	}

}

