package mx.gob.stps.portal.web.crm.action;


import mx.gob.stps.portal.web.crm.ValidatorCrm;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.CreateArticuloForm;
import mx.gob.stps.portal.web.crm.helper.FormHelper;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by benjamin.vander on 06/11/2015.
 */
public class CreateArticuloAction extends Action {

    private Long id;
    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();
    private CreateArticuloForm viewForm;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if(!ValidatorCrm.validarSession(request)) {
            return mapping.findForward("login");
        }
        viewForm = (CreateArticuloForm)form;
        String ckeditor = request.getParameter("editor1");
        //prueba crearArticulo



      //  ArticulosForm articulosForm = servicio.getTodoArticulos();
        //servicio.insertArticulo(createArticuloForm);

        //si tiene id es actualizar o buscar articulo
        if (viewForm.getId() != null && viewForm.getId() > 0 && viewForm.getTitulo()==null) {

            //buscar articulo
            id = Long.parseLong(request.getParameter("id"));
            servicio.selectArticulo(id, viewForm);

        } else {

            viewForm.setArticulo(ckeditor);
            if (viewForm.getId() != null && valido()) {
                if (viewForm.getId() != 0) {
                    //actualizar

                    servicio.updateArticulo(viewForm);
                    servicio.deleteEtiquetasArticulo(viewForm.getId());
                    insertarEtiquetas(viewForm, viewForm.getId());

                } else {
                    //Crear articulo
                    if (viewForm.getSelectedEtiquestas() != null && viewForm.getSelectedEtiquestas().length > 0) {

                        Long idArticulo = servicio.insertArticulo(viewForm) + 1L;
                        viewForm.setId(idArticulo);
                        if (idArticulo != 0) {
                            insertarEtiquetas(viewForm, idArticulo);
                        }
                    }

                }
            }
        }

        viewForm.setEtiquetasForm(servicio.getEtiquetasAlfabetico());
        return mapping.findForward("success");



    }

    private void insertarEtiquetas(CreateArticuloForm form, Long idArticulo) throws Exception {
        for (String etiqueta : form.getSelectedEtiquestas()) {
            servicio.createEtiquetaArticulo(idArticulo, Long.parseLong(etiqueta));

        }
    }



    public boolean valido() {
        viewForm.setErrorMessage("");
        int length = FormHelper.getLength(viewForm.getTitulo());
        if(length<3 || length > 120){
            FormHelper.addMessage(viewForm,"El titulo debe ser entre 3 y 120 caracteres.");
        }
        length = FormHelper.getLength(viewForm.getDescripcion());
        if(length >255){
            FormHelper.addMessage(viewForm, "La descripcion solo puede ser de 250 caracteres.");
        }
        if (viewForm.getSelectedEtiquestas() == null){
            FormHelper.addMessage(viewForm, "Se debe selecionar palabras Cláves.");
        }
        if (viewForm.getSelectedEtiquestas() != null && viewForm.getSelectedEtiquestas().length == 0){
            FormHelper.addMessage(viewForm, "Se debe selecionar palabras Cláves.");
        }
        FormHelper.validString(3,100,viewForm.getFuente(),"El fuente ", viewForm);
        FormHelper.validDate(viewForm.getFecha(),viewForm,"dd/mm/yyyy");
        if(viewForm.getArticulo().length()<3){
            FormHelper.addMessage(viewForm, "El articulo debe ser por lo menos tener 3 caracteres.");
        }
        return !viewForm.getHasError();
    }
}
