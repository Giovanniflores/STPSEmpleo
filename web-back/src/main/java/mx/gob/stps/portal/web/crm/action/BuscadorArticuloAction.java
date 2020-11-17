package mx.gob.stps.portal.web.crm.action;


import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.BuscadorForm;
import mx.gob.stps.portal.web.crm.form.CreateArticuloForm;
import mx.gob.stps.portal.web.crm.form.EtiquetaForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by benjamin.vander on 19/11/2015.
 */
public class BuscadorArticuloAction extends PagerAction {

    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();

    @Override
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


    private enum ActionForwardMapping {
        // The forward names must match in struts-config.xml
        SUCCESS("SUCCESS"),LOGIN("login");


        private final String forwardName;

        private ActionForwardMapping(String forwardName) {
            this.forwardName = forwardName;
        }
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        BuscadorForm buscadorForm = (BuscadorForm) form;
        String idEtiquetaStr = request.getParameter("idEtiqueta");
        buscadorForm.setListaEtiquetas(servicio.getEtiquetasAlfabetico());
        Long idEtiqueta = buscadorForm.getIdTema();
        if(buscadorForm.getTemasEmpleo() != null){
            Long idTempra = servicio.getIdEtiqueta(buscadorForm.getTemasEmpleo());
            if(idTempra != null){
                idEtiqueta = idTempra;
                buscadorForm.setTemasEmpleo(null);
            }
        }
        if(idEtiqueta == null){
            buscadorForm.setTemasEmpleo(null);
            buscadorForm.setArticulosFormList(servicio.getTodoArticulos().getArticulosFormList());

        }
        else
        {
            //buscar Articulos con el numero de etiqueta
            EtiquetaForm etiquetaForm = new EtiquetaForm(idEtiqueta);
            servicio.getEtiqueta(etiquetaForm);
            buscadorForm.setTemasEmpleo(etiquetaForm.getEtiqueta());

            List<CreateArticuloForm> articulos = servicio.getEtiquetasArticulosDesdeEtiqueta(idEtiqueta);

            buscadorForm.setArticulosFormList(articulos);


        }
        recortarLinea(buscadorForm.getArticulosFormList());
       /* request.getSession().setAttribute(BODY_JSP, mapping.findForward(ActionForwardMapping.SUCCESS.toString()).getPath());
        return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);*/
        return mapping.findForward(ActionForwardMapping.SUCCESS.toString());
    }

    private void recortarLinea(List<CreateArticuloForm> articulosFormList) {
        for(CreateArticuloForm articulo : articulosFormList){
           // articulo.setArticulo(recortarAUnaLinae(articulo.getArticulo(),articulo.getId()));
            articulo.setArticulo(addRef(articulo.getDescripcion(),articulo.getId(),articulo.getTitulo()));
        }
    }

    private String recortarAUnaLinae(String articulo,Long id) {

        if(articulo.indexOf(".") > 0){
            articulo = articulo.substring(0,articulo.indexOf("."));
        }
        articulo = articulo.replace("</p>","");
       return  articulo + " <a href=\"/articulo/"+id+"/"+limpiarURL(articulo)+"\">Leer m�s</a></p>";
    }

    private String addRef(String contenido, Long id, String titulo){
        return contenido + " <a href=\"/articulo/"+id+"/"+limpiarURL(titulo)+"\">Leer m�s</a></p>";
    }
    
	private String limpiarURL(String cadena){
		cadena = cadena.toLowerCase();
		cadena = cadena.trim();
		cadena = cadena.replaceAll(" +", "-");
		cadena = cadena.replace("�", "a");
		cadena = cadena.replace("�", "e");
		cadena = cadena.replace("�", "i");
		cadena = cadena.replace("�", "o");
		cadena = cadena.replace("�", "u");
		cadena = cadena.replace("�", "n");		
		cadena = cadena.replace("�", "a");
		cadena = cadena.replace("�", "e");
		cadena = cadena.replace("�", "i");
		cadena = cadena.replace("�", "o");
		cadena = cadena.replace("�", "u");
		cadena = cadena.replace("�", "a");
		cadena = cadena.replace("�", "e");
		cadena = cadena.replace("�", "i");
		cadena = cadena.replace("�", "o");
		cadena = cadena.replace("�", "u");	
		cadena = cadena.replace("�", "a");
		cadena = cadena.replace("�", "e");
		cadena = cadena.replace("�", "i");
		cadena = cadena.replace("�", "o");
		cadena = cadena.replace("�", "u");
		cadena = cadena.replace("�", "a");
		cadena = cadena.replace("�", "e");
		cadena = cadena.replace("�", "i");
		cadena = cadena.replace("�", "o");
		cadena = cadena.replace("�", "u");	
		cadena = cadena.replace("�", "a");
		cadena = cadena.replace("�", "e");
		cadena = cadena.replace("�", "i");
		cadena = cadena.replace("�", "o");
		cadena = cadena.replace("�", "u");
		cadena = cadena.replace("`", "");
		cadena = cadena.replace("'", "");
		cadena = cadena.replace(".", "");
		cadena = cadena.replace(",", "");
		cadena = cadena.replace(";", "");
		cadena = cadena.replace(":", "");
		cadena = cadena.replace("*", "");
		cadena = cadena.replace("<", "");
		cadena = cadena.replace(">", "");
		cadena = cadena.replace("|", "");
		cadena = cadena.replace("+", "");
		cadena = cadena.replace("*", "");
		cadena = cadena.replace("�", "n");	
		cadena = cadena.replace(" ", "-");
		cadena = cadena.replace("%", "");
		cadena = cadena.replace("_", "-");
		cadena = cadena.replace("$", "");
		cadena = cadena.replace("!", "");
		cadena = cadena.replace("(", "");
		cadena = cadena.replace(")", "");
		cadena = cadena.replace("\"","");
		cadena = cadena.replace("�", "");
		cadena = cadena.replace("�", "");
		cadena = cadena.replace("{", "");
		cadena = cadena.replace("}", "");
		cadena = cadena.replace("[", "");
		cadena = cadena.replace("]", "");
		cadena = cadena.replace("#", "");
		cadena = cadena.replace("&", "");
		cadena = cadena.replace("/", "");
		cadena = cadena.replace("=", "");
		cadena = cadena.replace("?", "");
		cadena = cadena.replace("�", "");
		cadena = cadena.replace("�", "");
		cadena = cadena.replace("!", "");
		cadena = cadena.replace("�", "");
		cadena = cadena.replace("@", "");
		return cadena;
	}
}
