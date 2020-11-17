package mx.gob.stps.portal.web.crm.form;


import mx.gob.stps.portal.web.crm.pojo.Page;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin.vander on 06/11/2015.
 */
public class ArticulosForm extends BaseMenuForm {

    private static final long serialVersionUID = -3491637470205228033L;

    private List<CreateArticuloForm> articulosFormList = new ArrayList<CreateArticuloForm>();

    private Boolean borrar;

    private int page;

    private int pages;

    private int totalRegistros;



    private List<Page> numeroPages;

    public Boolean getBorrar() {
        return borrar;
    }

    public void setBorrar(Boolean borrar) {
        this.borrar = borrar;
    }

    public List<CreateArticuloForm> getArticulosFormList() {
        return articulosFormList;
    }

    public void setArticulosFormList(List<CreateArticuloForm> articulosForm) {
        this.articulosFormList = articulosForm;
    }



    public ArticulosForm(){
        super();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public List<Page> getNumeroPages() {
        List<Page> numeroPages = new ArrayList<Page>();
        for(int i=1;i <pages; i++){
            Page page = new Page();
            numeroPages.add(page);
        }
        return numeroPages;
    }

    public void setNumeroPages(List<Page> numeroPages) {
        this.numeroPages = numeroPages;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public ActionErrors validate(ActionMapping mapping, ServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
            return this.validate(mapping, (HttpServletRequest)request);
        } catch (ClassCastException var4) {
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }



}
