<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ page trimDirectiveWhitespaces="true" %>
<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="js/idle/idle.js"></script>
<%
    String context = request.getContextPath() + "/";
%>
<c:set var="regexmat">^[a-zA-Z0-9]{12}</c:set>
<script type="text/javascript" lang="es">


    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require("dijit.form.Form");
    dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.ComboBox");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.TextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.Select");
    dojo.require("dijit.form.MultiSelect");
    dojo.require("dijit.form.CheckBox");
    dojo.require("dojo.data.ItemFileReadStore");
    dojo.require("dijit.form.FilteringSelect");

    var subAreaNegocioInitialLoad = true;   // To handle onchange event on dojo-select-widget
    var entidadInitialLoad = true;   // To handle onchange event on dojo-select-widget

    dojo.ready(function () {
        <%--var estaRegistrado = '${registrado}';--%>
        <%--if (estaRegistrado == 'true') {--%>
        <%--dojo.style("btnSubmit", "display", "none");--%>
        <%--alert('Ya has registrado tus datos en Egresados CONALEP!');--%>
        <%--window.history.back();--%>
        <%--}--%>

        // NOTE: Setting a value programmatically on a dojo-select-widget fires the "onchange" event, in order to prevent this issue,
        // a global variable (E.g., subAreaNegocioInitialLoad) is used in the function which handles the onchange event (E.g., areaNegocioSelectOnChangeHandler)

        dijit.byId('areaNegocioSelect').set('value', ${conalepCandidatoForm.idAreaNegocio});
        <%--dijit.byId('subAreaNegocioSelect').set('value', ${conalepCandidatoForm.idSubAreaNegocio});--%>
        dijit.byId('entidadSelect').set('value', ${conalepCandidatoForm.idEntidad});
        <%--dijit.byId('plantelSelect').set('value', ${conalepCandidatoForm.idPlantel});--%>
        dijit.byId('planEstudiosSelect').set('value', ${conalepCandidatoForm.idPlanEstudios});
        dijit.byId('generacionSelect').set('value', ${conalepCandidatoForm.generacion});
    });

    function areaNegocioSelectOnChangeHandler() {
        //  Load another catalog via ajax
        var idAreaNegocio = dijit.byId('areaNegocioSelect').get('value');
        subAreaNegocioStore.url = "${context}conalep.do?method=catalogoSubAreaNegocio&idAreaNegocio=" + idAreaNegocio;
        subAreaNegocioStore.close();
        subAreaNegocioStore.fetch({
            onComplete: function (items, request) {
                if (items.length == 0) {
                    return;
                }
                if (subAreaNegocioInitialLoad) {
                    dijit.byId('subAreaNegocioSelect').set('value', ${conalepCandidatoForm.idSubAreaNegocio});
                    subAreaNegocioInitialLoad = false;
                } else {
                    dijit.byId('subAreaNegocioSelect').set('value', '');
                }
            }
        });
    }

    function entidadSelectOnChangeHandler() {
        //  Load another catalog via ajax
        var idEntidad = dijit.byId('entidadSelect').get('value');
        plantelStore.url = "${context}conalep.do?method=catalogoPlantel&idEntidad=" + idEntidad;
        plantelStore.close();
        plantelStore.fetch({
            onComplete: function (items, request) {
                if (items.length == 0) {
                    return;
                }
                if (entidadInitialLoad) {
                    dijit.byId('plantelSelect').set('value', ${conalepCandidatoForm.idPlantel});
                    entidadInitialLoad = false;
                } else {
                    dijit.byId('plantelSelect').set('value', '');
                }
            }
        });
    }

    function save() {

        if (valida() == false) {
            return;
        }

        <%-- Copiar valores 'select' a campos ocultos --%>
        dojo.byId("idAreaNegocio").value = dijit.byId('areaNegocioSelect').get('value');
        dojo.byId("idSubAreaNegocio").value = dijit.byId('subAreaNegocioSelect').get('value');
        dojo.byId("idPlantel").value = dijit.byId('plantelSelect').get('value');
        dojo.byId("idPlanEstudios").value = dijit.byId('planEstudiosSelect').get('value');
        dojo.byId("generacion").value = dijit.byId('generacionSelect').get('value');

        dojo.xhrPost({
            url: '${context}conalep.do?method=save', form: 'conalepForm', sync: true,
            load: function (data) {
                if (data = 'success') {
                    dojo.style("btnSubmit", "display", "none");
                    messageConfirm('Los datos se guardaron correctamente');
                } else {
                    messageConfirm('Hubo un error al guardar los datos!');
                }
            }
        });
    }

    function valida() {
        var valorAreaNegocio = dijit.byId('areaNegocioSelect').get('value');
        var valorSubAreaNegocio = dijit.byId('subAreaNegocioSelect').get('value');
        var valorEntidad = dijit.byId('entidadSelect').get('value');
        var valorPlantel = dijit.byId('plantelSelect').get('value');
        var valorPlanEstudios = dijit.byId('planEstudiosSelect').get('value');
        var valorGeneracion = dijit.byId('generacionSelect').get('value');
        var valorMatricula = dijit.byId('matricula').get('value');
        if (valorAreaNegocio == '') {
            messageConfirm('Debe seleccionar una Área de Negocio');
            return false;
        }
        if (valorSubAreaNegocio == '') {
            messageConfirm('Debe seleccionar una Sub-Área de Negocio');
            return false;
        }
        if (valorEntidad == '') {
            messageConfirm('Debe seleccionar una Entidad');
            return false;
        }
        if (valorPlantel == '') {
            messageConfirm('Debe seleccionar un Plantel');
            return false;
        }
        if (valorPlanEstudios == '') {
            messageConfirm('Debe seleccionar un Plan de Estudios');
            return false;
        }
        if (valorGeneracion == '') {
            messageConfirm('Debe seleccionar una Generación');
            return false;
        }
        if (valorMatricula == '') {
            messageConfirm('Debe de proporcionar una Matrícula');
            return false;
        }

        return true;
    }


    function messageConfirm(msg) {
        var html =
                '<div id="messageDialgopC" name="messageDialgopC">' +
                '<p style="text-align: center;">' + msg + '</p>' +
                '<p class="form_nav">' +
                //'<input id="btnRenv" class="boton_naranja" type="button" name="btnRenv" onClick="doSubmmitDataComp()" value="Aceptar"/>' +
                '<input id="btnCanc" class="boton_naranja" type="button" name="btnCanc" onClick="dialogMsgConfirm.hide();" value="Aceptar"/>' +
                '</p>' +
                '</div>';
        dialogMsgConfirm = new dijit.Dialog({
            title: 'Mensaje',
            content: html,
            style: "width:300px;",
            showTitle: false, draggable: false, closable: false,
        });
        dialogMsgConfirm.show();
    }

</script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div dojoType="dojo.data.ItemFileReadStore" jsId="areaNegocioStore" urlPreventCache="true" clearOnClose="true" url="${context}conalep.do?method=catalogoAreaNegocio"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="subAreaNegocioStore" urlPreventCache="true" clearOnClose="true" url="${context}conalep.do?method=catalogoSubAreaNegocio&idAreaNegocio=${conalepCandidatoForm.idAreaNegocio}"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadStore" urlPreventCache="true" clearOnClose="true" url="${context}conalep.do?method=catalogoEntidad"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="plantelStore" urlPreventCache="true" clearOnClose="true" url="${context}conalep.do?method=catalogoPlantel&idEntidad=${conalepCandidatoForm.idEntidad}"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="planEstudiosStore" urlPreventCache="true" clearOnClose="true" url="${context}conalep.do?method=catalogoPlanEstudios"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="generacionStore" urlPreventCache="true" clearOnClose="true" url="${context}conalep.do?method=catalogoGeneracion"></div>

<div class="formApp miEspacio">
    <form name="conalepForm" id="conalepForm" method="post" action="conalep.do" dojoType="dijit.form.Form">

        <input type="hidden" id="idConalepCandidato" name="idConalepCandidato" value="${conalepCandidatoForm.idConalepCandidato}">
        <input type="hidden" id="idCandidato" name="idCandidato" value="${conalepCandidatoForm.idCandidato}">
        <input type="hidden" id="idAreaNegocio" name="idAreaNegocio" value="${conalepCandidatoForm.idAreaNegocio}">
        <input type="hidden" id="idSubAreaNegocio" name="idSubAreaNegocio" value="${conalepCandidatoForm.idSubAreaNegocio}">
        <input type="hidden" id="idPlantel" name="idPlantel" value="${conalepCandidatoForm.idPlantel}">
        <input type="hidden" id="idPlanEstudios" name="idPlanEstudios" value="${conalepCandidatoForm.idPlanEstudios}">
        <input type="hidden" id="generacion" name="generacion" value="${conalepCandidatoForm.generacion}">

        <div class="tab_block">
            <div class="sublevelTitle">
                CONALEP
                <br/><span class="subsublevel">Datos del Candidato</span>
            </div>
            <p class="descProgram">
                Descripci&oacute;n del programa
            </p>
            <br/>
            <fieldset class="generalesOferta">
                <legend>Mis datos</legend>
                <div class="grid1_3 margin_top_10">
                    <label for="areaNegocioSelect">&Aacute;rea Negocio</label>
                    <select id="areaNegocioSelect" name="areaNegocioSelect"
                            dojotype="dijit.form.FilteringSelect" store="areaNegocioStore"
                            required="true" missingMessage="Área Negocio."
                            onchange="areaNegocioSelectOnChangeHandler()">
                    </select>
                </div>
                <div class="grid1_3 margin_top_10">
                    <label for="subAreaNegocioSelect">Sub-&Aacute;rea Negocio</label>
                    <select id="subAreaNegocioSelect" name="subAreaNegocioSelect"
                            dojotype="dijit.form.FilteringSelect" store="subAreaNegocioStore"
                            required="true" missingMessage="Sub-Área Negocio.">
                    </select>
                </div>
                <div class="grid1_3 margin_top_10">
                    <label for="entidadSelect">Entidad del Plantel</label>
                    <select id="entidadSelect" name="entidadSelect"
                            dojotype="dijit.form.FilteringSelect" store="entidadStore"
                            required="true" missingMessage="Entidad del Plantel."
                            onchange="entidadSelectOnChangeHandler()">
                    </select>
                </div>
                <div class="grid1_3 margin_top_10">
                    <label for="plantelSelect">Plantel</label>
                    <select id="plantelSelect" name="plantelSelect"
                            dojotype="dijit.form.FilteringSelect" store="plantelStore"
                            required="true" missingMessage="Plantel.">
                    </select>
                </div>
                <div class="grid1_3 margin_top_10">
                    <label for="planEstudiosSelect">Plan de Estudios</label>
                    <select id="planEstudiosSelect" name="planEstudiosSelect"
                            dojotype="dijit.form.FilteringSelect" store="planEstudiosStore"
                            required="true" missingMessage="Plan Estudios.">
                    </select>
                </div>
                <div class="grid1_3 margin_top_10">
                    <label for="generacionSelect">Generaci&oacute;n</label>
                    <select id="generacionSelect" name="generacionSelect"
                            dojotype="dijit.form.FilteringSelect" store="generacionStore"
                            required="true" missingMessage="Generación." scrollOnFocus="true">
                    </select>
                </div>
                <div class="grid1_3 margin_top_10">
                    <label for="matricula">Matr&iacute;cula</label>
                    <input type="text" id="matricula" name="matricula" maxlength="11" size="30" trim="true"
                           value="${conalepCandidatoForm.matricula ne '' ? conalepCandidatoForm.matricula : ''}" dojoType="dijit.form.ValidationTextBox"
                           required="true" missingMessage="Matrícula."
                           <%--regExp="${regexmat}" invalidMessage="Matrícula no cumple con el formato."--%>
                    />
                </div>
                <br/>
                <input id="btnSubmit" type="button" value="Guardar"
                       style="font-size: 12px; margin: 0px;" class="boton_naranja"
                       onclick="save();"/>
            </fieldset>
        </div>

        <c:if test="${conalepCandidatoForm.direccionGeoReferenciada eq false}">
        <div>
            <p>Para completar tu registro, debes registrar la localización de tu domicilio en tu <a href="<c:url value="/perfil.do?method=init"/>">perfil</a></p>
        </div>
        </c:if>
    </form>
</div>		
