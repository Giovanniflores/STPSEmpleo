package mx.gob.stps.portal.web.infra.utils;

import java.text.Collator;
import java.util.List;

public abstract class TableSortAbstract<T> {
	
	private Class<T> vo;
	
	private List<T> lista;
	
	protected Collator comparador;
	
	public static final String ORDENAMIENTO_ASCENDENTE = "asc";
	
	public static final String ORDENAMIENTO_DESCENDENTE = "desc";
	
	protected Integer numeroColumna;
	
	protected String tipoOrdenamiento;
	
	public TableSortAbstract(Class<T> vo){
		this.vo = vo;
		comparador = Collator.getInstance();
		comparador.setStrength(Collator.PRIMARY);
	}
	
	protected abstract List getListaOrdenada();

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public Class<T> getVo() {
		return vo;
	}
	
	

}
