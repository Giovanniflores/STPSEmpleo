<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="mx.gob.stps.portal.web.autorization.vo.OfertaDetalleVO"%>
<%
  String context = request.getContextPath() +"/";
%>

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
	function doSubmitPagina(pagina, dif){
	 	var divTabla = "";
	 	if (dif=="_CANDIDATOS"){
	 		divTabla =  "divCandidatos";
	 	}else {
	 		divTabla =  "divPostulados";
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
	 	if (dif=="_CANDIDATOS"){
	 		doSubmitPagerCand(method);
	 	}else {
	 		doSubmitPagerPost(method);
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
 	function doSubmitPagerPost(method){
	 	dojo.byId('method').value = method;
		dojo.byId('tablaPager').value = "_POSTULADOS";
	  	dojo.xhrPost({url: 'admonCandidatos.do', form:'AdmonCandidatosForm', sync: true, preventCache:true, timeout:180000, 
				  load: function(dataPost){
				      dojo.byId('divPostulados').innerHTML=dataPost;
				  }});
	}
	
	//inicia los pagers de las tablas usadas y dibuja las tablas (una a la vez)
	function doSubmitInitPagers(){
	
		/*dojo.byId('method').value = 'pageTable';
	 	dojo.byId('tablaPager').value = "_CANDIDATOS";
	 	dojo.xhrPost({url: 'admonCandidatos.do', form:'AdmonCandidatosForm', timeout:180000, 
		load: function(dataCand){
			dojo.byId('divCandidatos').innerHTML=dataCand;
		}});**/
	 	doSubmitPagerCand('pageTable');
	 	doSubmitPagerPost('pageTable');	 	
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
	
	//eliminar filas, crea una cadena con los id de las ofertas de candidato a eliminar
    function eliminarCandidatos(form, tableID, ckeckName) {
    	var lista = "";
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
       			alert("Debe de seleccionar al menos un candidato para eliminarlo de la lista");
       			//dojo.byId('btnEliminar').disabled="";
    			//dojo.byId('btnEliminarPost').disabled="";
   				return;
       		}
       		
        	if (confirm("¿Está seguro que desea eliminar del listado al [a los] candidato[s] seleccionado[s]?")) {
        		doSubmitDeleteCandidatos(lista, tableID);
         	}
         } catch(e) {
        	alert('Excepcion: '+e);
        }
    }
    
    //elimina todos los candidatos de la tabla seleccionada (el boton indica la tabla (tableID))
    function doSubmitDeleteCandidatos(listaIdOfertasCandidatos, tableID) {
    var msg = "";
	   	try {
	   		dijit.byId('listaIdOfertasCandidatos').set('value', listaIdOfertasCandidatos);
	    	dijit.byId('method').set('value','borrarOfertaCandidatos');
	    	if (tableID == "tblCandidatos"){
	    		dojo.byId('tablaPager').value = "_CANDIDATOS";
	 		}else{
	 			dojo.byId('tablaPager').value = "_POSTULADOS";
	 		}
	    	dojo.xhrPost( {					 					 
				url: 'admonCandidatos.do',
			  	form:'AdmonCandidatosForm',
			  	timeout:180000,
			  	
			  	load: function(data){
			  		var res = dojo.fromJson(data);
					if (res.type == 'ext') {
						if (confirm(res.message)) {
							doSubmitPager('pageTable', dojo.byId('tablaPager').value);
						}
					} else {
						alert(res.message);
					}
			    }
			 });
		}catch (e){
			alert(e);
		}
		//dojo.byId('btnEliminar').disabled=""; 
  		//dojo.byId('btnEliminarPost').disabled=""; 
    }
</script>

<div class="tab_block">
	<div align="left" style="display:block;" id="returnME">
		<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');">
			<strong>Ir al inicio de Mi espacio</strong>
		</a>
	</div>
	<div class="Tab_espacio">
		<h3>Postulantes a mis ofertas de empleo</h3>
		<div class="subTab">
			<ul>
				<li class="curSubTab">
					<span>Mis postulantes</span>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<form name="AdmonCandidatosForm" id="AdmonCandidatosForm" action="admonCandidatos.do" method="post" dojoType="dijit.form.Form">		
	<input type="hidden" name="method" id="method" value="postulates"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="tablaPager" id="tablaPager"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idCandidatoEliminar" id="idCandidatoEliminar"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="listaIdOfertasCandidatos" id="listaIdOfertasCandidatos"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" dojoType="dijit.form.TextBox"/>
	
	<div id="divPostulados"></div>	
	<div class="borde_inferior"></div>
 	 
	<script>
		doSubmitInitPagers();
	</script>
</form>