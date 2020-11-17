package mx.gob.stps.portal.web.curso.form;

import java.util.List;

import mx.gob.stps.portal.core.curso.vo.CursoVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

import org.apache.struts.action.ActionForm;

public class CursoFormacionForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7062777265196866001L;
	
	private List<CursoVO> listaCursos;
	private List<CatalogoOpcionVO> listaEntidades;
	private String curso;
	private String plantel;
	private int paginaActual;
	private boolean paginaSiguiente;
	private boolean paginaAnterior;
	private int cursoDesde;
	private int cursoHasta;
	private int idEntidad;

	public void setListaCursos(List<CursoVO> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<CursoVO> getListaCursos() {
		return listaCursos;
	}

	public void setListaEntidades(List<CatalogoOpcionVO> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

	public List<CatalogoOpcionVO> getListaEntidades() {
		return listaEntidades;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getPlantel() {
		return plantel;
	}

	public void setPlantel(String plantel) {
		this.plantel = plantel;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public boolean isPaginaSiguiente() {
		return paginaSiguiente;
	}

	public void setPaginaSiguiente(boolean paginaSiguiente) {
		this.paginaSiguiente = paginaSiguiente;
	}

	public boolean isPaginaAnterior() {
		return paginaAnterior;
	}

	public void setPaginaAnterior(boolean paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}

	public int getCursoDesde() {
		return cursoDesde;
	}

	public void setCursoDesde(int cursoDesde) {
		this.cursoDesde = cursoDesde;
	}

	public int getCursoHasta() {
		return cursoHasta;
	}

	public void setCursoHasta(int cursoHasta) {
		this.cursoHasta = cursoHasta;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public int getIdEntidad() {
		return idEntidad;
	}
	
	
	

}
