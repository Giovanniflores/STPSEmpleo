package mx.gob.stps.portal.core.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultInfoBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private List<String> carreras;
    private String ocupacion;
    private List<String> conocimientos;
    private String titulo;
    private Date fecha;

    public ResultInfoBO() {
        this.id = 0L;
        this.carreras = new ArrayList<String>();
        this.ocupacion = "";
        this.conocimientos = new ArrayList<String>();
        this.titulo = "";
        this.fecha = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public List<String> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<String> value) {
        this.carreras = value;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String value) {
        this.ocupacion = value;
    }

    public List<String> getConocimientos() {
        return conocimientos;
    }

    public void setConocimientos(List<String> value) {
        this.conocimientos = value;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String value) {
        this.titulo = value;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date value) {
        this.fecha = value;
    }

    @Override
    public String toString() {
    	return "{id : " + id + ", carreras : " + carreras + ", ocupacion : " + ocupacion
    		+ ", conocimientos : " + conocimientos + ", titulo : \"" + titulo + "\", fecha : " + fecha.toString() + "}";
    }

}
