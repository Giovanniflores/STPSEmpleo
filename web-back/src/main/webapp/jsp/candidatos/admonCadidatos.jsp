<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="mx.gob.stps.portal.web.autorization.vo.OfertaDetalleVO"%>
<%
  String context = request.getContextPath() +"/";
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<style type="text/css">
	#dialogDetalle_underlay { background-color:gray; }
</style>
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.TextBox");
	
	//Evalua que pager debe ser llamado segun el diferenciador (dif)
	function doSubmitPagina(pagina, dif) {
	 	var divTabla = "";
	 	if (dif=="_CANDIDATOS") {
	 		divTabla =  "divCandidatos";
	 	}else if (dif=="_POSTULADOS") {
	 		divTabla =  "divPostulados";
	 	}else {
	 		divTabla =  "divNominales";
	 	}
	 	dojo.byId('method').value = "goToPage";
		dojo.byId('tablaPager').value = dif;
		dojo.byId('goToPageNumber').value = pagina;
	   	dojo.xhrPost({url: 'admonCandidatos.do', form:'AdmonCandidatosForm', timeout:180000, 
					  load: function(dataCand){
					      dojo.byId(divTabla).innerHTML=dataCand;
					  }});
	}
	
	//Evalua que pager debe ser llamado segun el diferenciador (dif)
	function doSubmitPager(method, dif){
	 	if (dif=="_CANDIDATOS") {
	 		doSubmitPagerCand(method);
	 	}else if (dif=="_POSTULADOS") {
	 		doSubmitPagerPost(method);
	 	}else {
	 		doSubmitPagerPost(method);
	 		doSubmitPagerNoml(method);
	 	}
	}
	//funcionamiento del pager para candidatos	
  	function doSubmitPagerCand(method){
		 dojo.byId('method').value = method;
		 dojo.byId('tablaPager').value = "_CANDIDATOS";		 
		 dojo.xhrPost({url: 'admonCandidatos.do', form:'AdmonCandidatosForm', sync: true, preventCache:true, timeout:180000, 
					  load: function(dataCand){
					      dojo.byId('divCandidatos').innerHTML=dataCand;
					  }});
	}
	
	//funcionamiento del pager para postulados
 	function doSubmitPagerPost(method) {
	 	dojo.byId('method').value = method;
		dojo.byId('tablaPager').value = "_POSTULADOS";
	  	dojo.xhrPost({url: 'admonCandidatos.do', form:'AdmonCandidatosForm', sync: true, preventCache:true, timeout:180000, 
				  load: function(dataPost){
				      dojo.byId('divPostulados').innerHTML=dataPost;
				  }});
	}
	
	//funcionamiento del pager para nominales
 	function doSubmitPagerNoml(method) {
	 	dojo.byId('method').value = method;
		dojo.byId('tablaPager').value = "_NOMINALES";
	  	dojo.xhrPost({url: 'admonCandidatos.do', form:'AdmonCandidatosForm', sync: true, preventCache:true, timeout:180000, 
				  load: function(dataPost) {
				      dojo.byId('divNominales').innerHTML=dataPost;
				  }});
	}
	
	//inicia los pagers de las tablas usadas y dibuja las tablas (una a la vez)
	function doSubmitInitPagers() {
	 	doSubmitPagerPost('pageTable');
	 	doSubmitPagerCand('pageTable');
	 	doSubmitPagerNoml('pageTable');	 	
	}
	
	//muestra u oculta el resumen del registro seleccionado
 	function mostrarResumen(idResumen) {
		var thisRow = dojo.style(idResumen, "display");
		
		if(thisRow == 'block'){
			dojo.style(idResumen, "display", "none");
			dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "none");
			dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "block");
		}
			
		if(thisRow == 'none'){
			dojo.style(idResumen, "display", "block");
			dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "block");
			dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "none");
		}
	}
	
	//Muestra u oculta todos los resumenes de la lista
 	function mostrarTodas(tipo, totReg, prefix) {
		//Esconder
		if(tipo == 1){
	 		for(var i = 1; i <= totReg; i++){
    	
	    	    dojo.style("hideAll" + prefix, "display", "block");
	    	    dojo.style("showAll" + prefix, "display", "none");
				
				dojo.style(eval("\"" + prefix + i + "\""), "display", "block");
				//dojo.style(eval("\"" + "hide" + prefix + i + "\""), "display", "block");
				//dojo.style(eval("\"" + "show" + prefix + i + "\""), "display", "none");
			}
		}
		//Mostrar
		if(tipo == 2){
			for(var i = 1; i <= totReg; i++){

	    	    dojo.style("hideAll" + prefix, "display", "none");
	    	    dojo.style("showAll" + prefix, "display", "block");
	
				dojo.style(eval("\"" + prefix + i + "\""), "display", "none");
				//dojo.style(eval("\"" + "hide" + prefix + i + "\""), "display", "none");
				//dojo.style(eval("\"" + "show" + prefix + i + "\""), "display", "block");
			}
		}
	}
	
	//Evalua si se chequean todos los check box (o deseleccionan si estan seleccionados)
	function callable(form,chk, field) {
		if(chk.checked==true) {
			selectCheck(form, true, field);
		}else {
			selectCheck(form, false, field);
		}
    }
    //Checkea todos los check boxes
	function selectCheck(form, obj, field) {
		var i=form.elements.length;
		for(var k=0;k<i;k++) {
			if(form.elements[k].name==field) {
				if(!form.elements[k].disabled) 
					form.elements[k].checked=obj;
			}
		}
	}
	var tblID;
	var lista = "";
	//eliminar filas, crea una cadena con los id de las ofertas de candidato a eliminar
    function eliminarCandidatos(form, tableID, ckeckName) {
    	tblID = tableID;
    	try {
    		//dojo.byId('btnEliminar').disabled="disabled";
    		//dojo.byId('btnEliminarPost').disabled="disabled";
   			var i=form.elements.length;
   			for(var k=0;k<i;k++) {
				if(form.elements[k] !=null && form.elements[k].name !=null){
					if(form.elements[k].name==ckeckName) {
						if (form.elements[k].checked && !form.elements[k].disabled){
							rowID = form.elements[k].id;
							lista += form.elements[k].value +"|";
        				}
        			} 
        		}
       		}
       		if (lista == ""){
       			message("Debe de seleccionar al menos un candidato para eliminarlo de la lista");
       			//dojo.byId('btnEliminar').disabled="";
    			//dojo.byId('btnEliminarPost').disabled="";
   				return;
       		}
       		var input = '<input id="btnConf" class="boton_naranja" type="button" name="btnConf" onClick="doSubmitDeleteCandidatos();" value="Aceptar"/>';
        	messageInput('¿Está seguro que desea eliminar del listado al [a los] candidato[s] seleccionado[s]?',input);
        	//if (confirm("¿Está seguro que desea eliminar del listado al [a los] candidato[s] seleccionado[s]?")) {
        		//doSubmitDeleteCandidatos(lista, tableID);
         	//}
         } catch(e) {
        	alert('Excepcion: ' + e);
        }
    }
    function messageInput(msg,input) {
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
				'<p style="text-align: center;">'+ msg +'</p><br>'+
				'<p class="form_nav">' + input + cerrarMsg() + 
			'</div>';

		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	        hide: function() {
	        	clearWidgetsHtml('messageDialgop2');
	        	dialogMsg.destroy();
	        }
	    });			
		dialogMsg.show();
	}
    //elimina todos los candidatos de la tabla seleccionada (el boton indica la tabla (tableID))
    function doSubmitDeleteCandidatos() {
    	tableID = tblID;
    	listaIdOfertasCandidatos = lista;
	   	try {
	   		dijit.byId('listaIdOfertasCandidatos').set('value', listaIdOfertasCandidatos);
	    	dijit.byId('method').set('value','borrarOfertaCandidatos');
	    	if (tableID == "tblCandidatos") {
	    		dojo.byId('tablaPager').value = "_CANDIDATOS";
	 		}else if (tableID == "tblPostulados") {
	 			dojo.byId('tablaPager').value = "_POSTULADOS";
	 		}else {
	 			dojo.byId('tablaPager').value = "_NOMINALES";
	 		}
	 		dialogMsg.hide();
	    	dojo.xhrPost( {					 					 
				url: 'admonCandidatos.do',
			  	form:'AdmonCandidatosForm',
			  	timeout:180000,
			  	load: function(data){
			  		var res = dojo.fromJson(data);
					if (res.type == 'ext') {
						message(res.message);
						doSubmitPager('pageTable', dojo.byId('tablaPager').value);
						lista = "";
						//}
					} else {
						message(res.message);
					}
			    }
			 });
		}catch (e){
			message(e);
		}
		//dojo.byId('btnEliminar').disabled=""; 
  		//dojo.byId('btnEliminarPost').disabled=""; 
    }
</script>


<div class="tab_block">
	<div align="left" id="returnME" style="display:block;">
		<a href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');" class="expand">
		<strong>Ir al inicio de Mi espacio</strong></a>
	</div>
	<div class="Tab_espacio">
		<h3>Ofertas de empleo</h3>
			<div class="subTab">
				<ul>
					<li><a href="javascript:doSubmit('<c:url value="/dondePublicar.do?method=init"/>');">Crear oferta de empleo</a></li>
					<li class="curSubTab"><span>Ver mis ofertas de empleos</span></li>
				</ul>
                <div class="clearfix"></div>
            </div>
	</div>	      
</div>


<form name="AdmonCandidatosForm" id="AdmonCandidatosForm" action="admonCandidatos.do" method="post" dojoType="dijit.form.Form">		
	<input type="hidden" name="method" id="method" value="init"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="tablaPager" id="tablaPager"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idCandidatoEliminar" id="idCandidatoEliminar"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="listaIdOfertasCandidatos" id="listaIdOfertasCandidatos"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" dojoType="dijit.form.TextBox"/>

	<table style="width: 100%" class="misOfertas" cellspacing="0" cellpadding="0" border="0">
		<caption>Mi oferta de empleo</caption>
		<tbody>
			<tr class="temas">
		    	<th style="width: 160px">T&iacute;tulo de la oferta</th>
		        <th>Área de negocio</th>
		        <th>Nivel de estudios</th>
		        <th>Carrera</th>
		        <th class="fin">Ubicaci&oacute;n</th>
			</tr>
			<tr class="odd">
				<td class="checar">
					<a class="iconosTablas" href="<c:url value="detalleoferta.do?method=init&id_oferta_empleo=${AdmonCandidatosForm.ofertaDetalle.idOfertaEmpleo}&_urlpageinvoke=admonCandidatos"/>" title="Ver detalle"><strong>${AdmonCandidatosForm.ofertaDetalle.tituloOferta}</strong></a>
	                <p class="Npostulaciones">		
					<c:out value="${AdmonCandidatosForm.totalPostulados}"/>&nbsp;Postulaciones	
					</p>
	
				</td>
				<td>${AdmonCandidatosForm.ofertaDetalle.ocupacion}</td>
				<td>${AdmonCandidatosForm.ofertaDetalle.gradoEstudio}</td>	
				<td>${AdmonCandidatosForm.ofertaDetalle.carrera}</td>	
				<td>${AdmonCandidatosForm.ofertaDetalle.ubicacion}</td>
			</tr>
		</tbody>	
	</table>
	<div id="divPostulados"></div>
	<div id="divCandidatos"></div>	
	<div id="divNominales"></div>
	
	<div class="borde_inferior"></div>
	
	<p class="entero" style="text-align:right;">
			<!-- <input type="button" class="boton" name="btnEliminarPost"  id="btnEliminarPost" value="Eliminar" onclick="eliminarCandidatos(this.form, 'tblPostulados', 'chkPostulado');" >-->
	</p>
 	 
	<script>
		doSubmitInitPagers();
	</script>

	
</form>
