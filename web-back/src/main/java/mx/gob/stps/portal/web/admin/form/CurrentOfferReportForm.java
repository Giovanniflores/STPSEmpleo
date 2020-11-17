package mx.gob.stps.portal.web.admin.form;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.oferta.vo.CurrentOfferAreaOcupacionVO;


import org.apache.struts.action.ActionForm;

public class CurrentOfferReportForm extends ActionForm{

	private long totalOfertasPortal;
	private long totalOfertasExternas;
	private long totalOfertas;
	ArrayList ofertasPortalEntidad = new ArrayList();
	List<ParametroVO> ofertasExternasEntidad;
	private long totalOfertasPortalMasculino;
	private long totalOfertasPortalFemenino;
	private long totalOfertasPortalNoRequerido;
	private long totalOfertasIndiferente;
	
	// report de ofertas del portal según entidad y grado de estudios (currentOfferReportEscolaridad.jsp)
	ArrayList ofertasPortalEntidadEscolaridad = new ArrayList();
	private long totalOfertasSinInstruccion;
	private long totalOfertasLeerEscribir;
	private long totalOfertasPrimaria;
	private long totalOfertasSecundaria;
	private long totalOfertasCarreraComercial;
	private long totalOfertasCarreraTecnica;
	private long totalOfertasProfesionalTecnico;
	private long totalOfertasPrepa;
	private long totalOfertasUniversitario;
	private long totalOfertasLicenciatura;
	private long totalOfertasMaestria;
	private long totalOfertasDoctorado;
	private long totalOfertasEntidadEscolaridad;
	
	// report de ofertas del portal según entidad y experiencia (currentOfferReportExperience.jsp)
	ArrayList ofertasPortalEntidadExperiencia = new ArrayList();
	private long totalOfertasSinExperiencia;	
	private long totalOfertasHasta1Anio;
	private long totalOfertasDe1A2Anios;	
	private long totalOfertasDe2A3Anios;	
	private long totalOfertasDe3A4Anios;
	private long totalOfertasDe4A5Anios;
	private long totalOfertasMasDe5Anios;
	private long totalOfertasExpNoRequerida;	
	private long totalOfertasEntidadExperiencia;
	
	// report de ofertas del portal según actividad económica
	ArrayList ofertasPortalActividadEconomica = new ArrayList();
	private long totalOfertasPortalActividadEconomica;
	
	// report de ofertas del portal según área laboral y ocupación
	ArrayList<CurrentOfferAreaOcupacionVO> ofertasAreaOcupacion = new ArrayList<CurrentOfferAreaOcupacionVO>();
	private long totalOfertasAreaOcupacion;

	/**
	 * @return the totalOfertasPortal
	 */
	public long getTotalOfertasPortal() {
		return totalOfertasPortal;
	}
	/**
	 * @param totalOfertasPortal the totalOfertasPortal to set
	 */
	public void setTotalOfertasPortal(long totalOfertasPortal) {
		this.totalOfertasPortal = totalOfertasPortal;
	}
	/**
	 * @return the totalOfertasExternas
	 */
	public long getTotalOfertasExternas() {
		return totalOfertasExternas;
	}
	/**
	 * @param totalOfertasExternas the totalOfertasExternas to set
	 */
	public void setTotalOfertasExternas(long totalOfertasExternas) {
		this.totalOfertasExternas = totalOfertasExternas;
	}
	/**
	 * @return the totalOfertas
	 */
	public long getTotalOfertas() {
		return totalOfertas;
	}
	/**
	 * @param totalOfertas the totalOfertas to set
	 */
	public void setTotalOfertas(long totalOfertas) {
		this.totalOfertas = totalOfertas;
	}
	/**
	 * @return the ofertasPortalEntidad
	 */
	public ArrayList getOfertasPortalEntidad() {
		return ofertasPortalEntidad;
	}
	
	/**
	 * @param ofertasPortalEntidad the ofertasPortalEntidad to set
	 */
	public void setOfertasPortalEntidad(ArrayList ofertasPortalEntidad) {
		this.ofertasPortalEntidad = ofertasPortalEntidad;
	}
	
	
	/**
	 * @return the ofertasExternasEntidad
	 */
	public List<ParametroVO> getOfertasExternasEntidad() {
		return ofertasExternasEntidad;
	}
	/**
	 * @param ofertasExternasEntidad the ofertasExternasEntidad to set
	 */
	public void setOfertasExternasEntidad(List<ParametroVO> ofertasExternasEntidad) {
		this.ofertasExternasEntidad = ofertasExternasEntidad;
	}
	public long getTotalOfertasPortalMasculino() {
		return totalOfertasPortalMasculino;
	}
	public void setTotalOfertasPortalMasculino(long totalOfertasPortalMasculino) {
		this.totalOfertasPortalMasculino = totalOfertasPortalMasculino;
	}
	public long getTotalOfertasPortalFemenino() {
		return totalOfertasPortalFemenino;
	}
	public void setTotalOfertasPortalFemenino(long totalOfertasPortalFemenino) {
		this.totalOfertasPortalFemenino = totalOfertasPortalFemenino;
	}
	public long getTotalOfertasPortalNoRequerido() {
		return totalOfertasPortalNoRequerido;
	}
	public void setTotalOfertasPortalNoRequerido(long totalOfertasPortalNoRequerido) {
		this.totalOfertasPortalNoRequerido = totalOfertasPortalNoRequerido;
	}
	public long getTotalOfertasIndiferente() {
		return totalOfertasIndiferente;
	}
	public void setTotalOfertasIndiferente(long totalOfertasIndiferente) {
		this.totalOfertasIndiferente = totalOfertasIndiferente;
	}
	public ArrayList getOfertasPortalEntidadEscolaridad() {
		return ofertasPortalEntidadEscolaridad;
	}
	public void setOfertasPortalEntidadEscolaridad(
			ArrayList ofertasPortalEntidadEscolaridad) {
		this.ofertasPortalEntidadEscolaridad = ofertasPortalEntidadEscolaridad;
	}
	public long getTotalOfertasSinInstruccion() {
		return totalOfertasSinInstruccion;
	}
	public void setTotalOfertasSinInstruccion(long totalOfertasSinInstruccion) {
		this.totalOfertasSinInstruccion = totalOfertasSinInstruccion;
	}
	public long getTotalOfertasLeerEscribir() {
		return totalOfertasLeerEscribir;
	}
	public void setTotalOfertasLeerEscribir(long totalOfertasLeerEscribir) {
		this.totalOfertasLeerEscribir = totalOfertasLeerEscribir;
	}
	public long getTotalOfertasPrimaria() {
		return totalOfertasPrimaria;
	}
	public void setTotalOfertasPrimaria(long totalOfertasPrimaria) {
		this.totalOfertasPrimaria = totalOfertasPrimaria;
	}
	public long getTotalOfertasSecundaria() {
		return totalOfertasSecundaria;
	}
	public void setTotalOfertasSecundaria(long totalOfertasSecundaria) {
		this.totalOfertasSecundaria = totalOfertasSecundaria;
	}
	public long getTotalOfertasCarreraComercial() {
		return totalOfertasCarreraComercial;
	}
	public void setTotalOfertasCarreraComercial(long totalOfertasCarreraComercial) {
		this.totalOfertasCarreraComercial = totalOfertasCarreraComercial;
	}
	public long getTotalOfertasCarreraTecnica() {
		return totalOfertasCarreraTecnica;
	}
	public void setTotalOfertasCarreraTecnica(long totalOfertasCarreraTecnica) {
		this.totalOfertasCarreraTecnica = totalOfertasCarreraTecnica;
	}
	public long getTotalOfertasProfesionalTecnico() {
		return totalOfertasProfesionalTecnico;
	}
	public void setTotalOfertasProfesionalTecnico(
			long totalOfertasProfesionalTecnico) {
		this.totalOfertasProfesionalTecnico = totalOfertasProfesionalTecnico;
	}
	public long getTotalOfertasPrepa() {
		return totalOfertasPrepa;
	}
	public void setTotalOfertasPrepa(long totalOfertasPrepa) {
		this.totalOfertasPrepa = totalOfertasPrepa;
	}
	public long getTotalOfertasUniversitario() {
		return totalOfertasUniversitario;
	}
	public void setTotalOfertasUniversitario(long totalOfertasUniversitario) {
		this.totalOfertasUniversitario = totalOfertasUniversitario;
	}
	public long getTotalOfertasLicenciatura() {
		return totalOfertasLicenciatura;
	}
	public void setTotalOfertasLicenciatura(long totalOfertasLicenciatura) {
		this.totalOfertasLicenciatura = totalOfertasLicenciatura;
	}
	public long getTotalOfertasMaestria() {
		return totalOfertasMaestria;
	}
	public void setTotalOfertasMaestria(long totalOfertasMaestria) {
		this.totalOfertasMaestria = totalOfertasMaestria;
	}
	public long getTotalOfertasDoctorado() {
		return totalOfertasDoctorado;
	}
	public void setTotalOfertasDoctorado(long totalOfertasDoctorado) {
		this.totalOfertasDoctorado = totalOfertasDoctorado;
	}
	public long getTotalOfertasEntidadEscolaridad() {
		return totalOfertasEntidadEscolaridad;
	}
	public void setTotalOfertasEntidadEscolaridad(
			long totalOfertasEntidadEscolaridad) {
		this.totalOfertasEntidadEscolaridad = totalOfertasEntidadEscolaridad;
	}
	
	public void clearOfertasPortalEntidadEscolaridad(){
		if (this.ofertasPortalEntidadEscolaridad == null)
			
		this.ofertasPortalEntidadEscolaridad.clear();	
	}
	
	public ArrayList getOfertasPortalEntidadExperiencia() {
		return ofertasPortalEntidadExperiencia;
	}
	public void setOfertasPortalEntidadExperiencia(
			ArrayList ofertasPortalEntidadExperiencia) {
		this.ofertasPortalEntidadExperiencia = ofertasPortalEntidadExperiencia;
	}
	public long getTotalOfertasSinExperiencia() {
		return totalOfertasSinExperiencia;
	}
	public void setTotalOfertasSinExperiencia(long totalOfertasSinExperiencia) {
		this.totalOfertasSinExperiencia = totalOfertasSinExperiencia;
	}
	public long getTotalOfertasHasta1Anio() {
		return totalOfertasHasta1Anio;
	}
	public void setTotalOfertasHasta1Anio(long totalOfertasHasta1Anio) {
		this.totalOfertasHasta1Anio = totalOfertasHasta1Anio;
	}
	public long getTotalOfertasDe1A2Anios() {
		return totalOfertasDe1A2Anios;
	}
	public void setTotalOfertasDe1A2Anios(long totalOfertasDe1A2Anios) {
		this.totalOfertasDe1A2Anios = totalOfertasDe1A2Anios;
	}
	public long getTotalOfertasDe2A3Anios() {
		return totalOfertasDe2A3Anios;
	}
	public void setTotalOfertasDe2A3Anios(long totalOfertasDe2A3Anios) {
		this.totalOfertasDe2A3Anios = totalOfertasDe2A3Anios;
	}
	public long getTotalOfertasDe3A4Anios() {
		return totalOfertasDe3A4Anios;
	}
	public void setTotalOfertasDe3A4Anios(long totalOfertasDe3A4Anios) {
		this.totalOfertasDe3A4Anios = totalOfertasDe3A4Anios;
	}
	public long getTotalOfertasDe4A5Anios() {
		return totalOfertasDe4A5Anios;
	}
	public void setTotalOfertasDe4A5Anios(long totalOfertasDe4A5Anios) {
		this.totalOfertasDe4A5Anios = totalOfertasDe4A5Anios;
	}
	public long getTotalOfertasMasDe5Anios() {
		return totalOfertasMasDe5Anios;
	}
	public void setTotalOfertasMasDe5Anios(long totalOfertasMasDe5Anios) {
		this.totalOfertasMasDe5Anios = totalOfertasMasDe5Anios;
	}
	
	public long getTotalOfertasExpNoRequerida() {
		return totalOfertasExpNoRequerida;
	}
	public void setTotalOfertasExpNoRequerida(long totalOfertasExpNoRequerida) {
		this.totalOfertasExpNoRequerida = totalOfertasExpNoRequerida;
	}
	public long getTotalOfertasEntidadExperiencia() {
		return totalOfertasEntidadExperiencia;
	}
	public void setTotalOfertasEntidadExperiencia(
			long totalOfertasEntidadExperiencia) {
		this.totalOfertasEntidadExperiencia = totalOfertasEntidadExperiencia;
	}	
	
	public void clearOfertasPortalEntidadExperiencia(){
		this.ofertasPortalEntidadEscolaridad.clear();		
	}
	
	public void clearOfertasPortalEntidad(){
		this.ofertasPortalEntidad.clear();
	}
	
	public ArrayList getOfertasPortalActividadEconomica() {
		return ofertasPortalActividadEconomica;
	}
	
	public void setOfertasPortalActividadEconomica(
			ArrayList ofertasPortalActividadEconomica) {
		this.ofertasPortalActividadEconomica = ofertasPortalActividadEconomica;
	}
	
	public long getTotalOfertasPortalActividadEconomica() {
		return totalOfertasPortalActividadEconomica;
	}
	
	public void setTotalOfertasPortalActividadEconomica(
			long totalOfertasPortalActividadEconomica) {
		this.totalOfertasPortalActividadEconomica = totalOfertasPortalActividadEconomica;
	}

	public void clearOfertasPortalActividadEconomica(){
		this.ofertasPortalActividadEconomica.clear();
	}
	public ArrayList<CurrentOfferAreaOcupacionVO> getOfertasAreaOcupacion() {
		return ofertasAreaOcupacion;
	}
	
	public void setOfertasAreaOcupacion(
			ArrayList<CurrentOfferAreaOcupacionVO> ofertasAreaOcupacion) {
		this.ofertasAreaOcupacion = ofertasAreaOcupacion;
	}
	
	public long getTotalOfertasAreaOcupacion() {
		return totalOfertasAreaOcupacion;
	}
	public void setTotalOfertasAreaOcupacion(long totalOfertasAreaOcupacion) {
		this.totalOfertasAreaOcupacion = totalOfertasAreaOcupacion;
	}
	public void clearOfertasAreaOcupacion(){	
		this.ofertasAreaOcupacion.clear();
	}
}
