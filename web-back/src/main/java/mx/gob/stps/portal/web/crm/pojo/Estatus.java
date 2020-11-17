package mx.gob.stps.portal.web.crm.pojo;

/**
 * Created by benjamin.vander on 07/11/2015.
 */
public enum Estatus {
    ACTIVO("activo",1),INDEXABLE("indexable",2),OCCULTO("occulto",3);

    private int num;
    private String estatus;
    Estatus(String estatus, int num){
        this.estatus = estatus;
        this.num = num;

    }

    public int getNum() {
        return num;
    }

    public String getEstatus() {
        return estatus;
    }


}
