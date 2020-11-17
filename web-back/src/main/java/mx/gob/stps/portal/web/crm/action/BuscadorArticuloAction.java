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
       return  articulo + " <a href=\"/articulo/"+id+"/"+limpiarURL(articulo)+"\">Leer más</a></p>";
    }

    private String addRef(String contenido, Long id, String titulo){
        return contenido + " <a href=\"/articulo/"+id+"/"+limpiarURL(titulo)+"\">Leer más</a></p>";
    }
    
	private String limpiarURL(String cadena){
		cadena = cadena.toLowerCase();
		cadena = cadena.trim();
		cadena = cadena.replaceAll(" +", "-");
		cadena = cadena.replace("á", "a");
		cadena = cadena.replace("é", "e");
		cadena = cadena.replace("í", "i");
		cadena = cadena.replace("ó", "o");
		cadena = cadena.replace("ú", "u");
		cadena = cadena.replace("ñ", "n");		
		cadena = cadena.replace("Á", "a");
		cadena = cadena.replace("É", "e");
		cadena = cadena.replace("Í", "i");
		cadena = cadena.replace("Ó", "o");
		cadena = cadena.replace("Ú", "u");
		cadena = cadena.replace("â", "a");
		cadena = cadena.replace("ê", "e");
		cadena = cadena.replace("î", "i");
		cadena = cadena.replace("ô", "o");
		cadena = cadena.replace("û", "u");	
		cadena = cadena.replace("Â", "a");
		cadena = cadena.replace("Ê", "e");
		cadena = cadena.replace("Î", "i");
		cadena = cadena.replace("Ô", "o");
		cadena = cadena.replace("Û", "u");
		cadena = cadena.replace("ä", "a");
		cadena = cadena.replace("ë", "e");
		cadena = cadena.replace("ï", "i");
		cadena = cadena.replace("ö", "o");
		cadena = cadena.replace("ü", "u");	
		cadena = cadena.replace("Ä", "a");
		cadena = cadena.replace("Ë", "e");
		cadena = cadena.replace("Ï", "i");
		cadena = cadena.replace("Ö", "o");
		cadena = cadena.replace("Ü", "u");
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
		cadena = cadena.replace("Ñ", "n");	
		cadena = cadena.replace(" ", "-");
		cadena = cadena.replace("%", "");
		cadena = cadena.replace("_", "-");
		cadena = cadena.replace("$", "");
		cadena = cadena.replace("!", "");
		cadena = cadena.replace("(", "");
		cadena = cadena.replace(")", "");
		cadena = cadena.replace("\"","");
		cadena = cadena.replace("´", "");
		cadena = cadena.replace("¨", "");
		cadena = cadena.replace("{", "");
		cadena = cadena.replace("}", "");
		cadena = cadena.replace("[", "");
		cadena = cadena.replace("]", "");
		cadena = cadena.replace("#", "");
		cadena = cadena.replace("&", "");
		cadena = cadena.replace("/", "");
		cadena = cadena.replace("=", "");
		cadena = cadena.replace("?", "");
		cadena = cadena.replace("¿", "");
		cadena = cadena.replace("°", "");
		cadena = cadena.replace("!", "");
		cadena = cadena.replace("¡", "");
		cadena = cadena.replace("@", "");
		return cadena;
	}
}
