package mx.gob.stps.portal.web.crm.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by benjamin.vander on 17/11/2015.
 */
public class BuscadorForm extends BaseMenuForm{

    private String idConsejo;

    private String temasEmpleo;

    private Long idTema;

    private List<CreateArticuloForm> articulosFormList;


    private EtiquetasForm etiquetasForms;

    private List<EtiquetaForm> listaEtiquetas;

    private int totalRegistros;
    private int totalPaginas;


    public List<CreateArticuloForm> getArticulosFormList() {
        return articulosFormList;
    }

    public void setArticulosFormList(List<CreateArticuloForm> articulosFormList) {
        this.articulosFormList = articulosFormList;
    }

    public EtiquetasForm getEtiquetasForms() {
        return etiquetasForms;
    }

    public void setEtiquetasForms(EtiquetasForm etiquetasForms) {
        this.etiquetasForms = etiquetasForms;
    }


    public String getIdConsejo() {
        return idConsejo;
    }

    public void setIdConsejo(String idConsejo) {
        this.idConsejo = idConsejo;
    }


    public List<EtiquetaForm> getListaEtiquetas() {
        return listaEtiquetas;
    }

    public void setListaEtiquetas(List<EtiquetaForm> listaEtiquetas) {
        this.listaEtiquetas = listaEtiquetas;
    }


    public String getTemasEmpleo() {
        return temasEmpleo;
    }

    public void setTemasEmpleo(String temasEmpleo) {
        this.temasEmpleo = temasEmpleo;
    }


    public int getTotalRegistros() {
        return this.articulosFormList.size();
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public int getTotalPaginas() {
        return this.totalRegistros /10;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
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

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }
}
