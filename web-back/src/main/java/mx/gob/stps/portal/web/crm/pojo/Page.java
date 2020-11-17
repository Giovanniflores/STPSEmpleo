package mx.gob.stps.portal.web.crm.pojo;

import org.apache.struts.action.ActionForm;

/**
 * Created by benjamin.vander on 17/11/2015.
 */
public class Page extends ActionForm {

    private int pagenumer;


    public int getPagenumer() {
        return pagenumer;
    }

    public void setPagenumer(int pagenumer) {
        this.pagenumer = pagenumer;
    }
}
