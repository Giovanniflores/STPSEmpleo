<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="mx.gob.stps.portal.web.autorization.vo.OfertaDetalleVO, mx.gob.stps.portal.core.candidate.vo.CandidatoVo"%>
<%
  
   int NUMERO_MAX_REGISTROS = 5;
   int NUMERO_PAGUINAS	= 0;

 
  int numcand = 0;
  String context = request.getContextPath() +"/";
  List<CandidatoVo> candidatosVO = (List<CandidatoVo>)session.getAttribute("FULL_LIST_CANDIDATOS");
  if (null != candidatosVO) {
	  numcand = candidatosVO.size();
	 	if( numcand % NUMERO_MAX_REGISTROS == 0) {
	 	 NUMERO_PAGUINAS =  numcand / NUMERO_MAX_REGISTROS;
	 	}else{
	 	 NUMERO_PAGUINAS =  numcand / NUMERO_MAX_REGISTROS;
	 	 NUMERO_PAGUINAS++;
	 	}
	 	
  }
%>

<style type="text/css">
	#dialogDetalle_underlay { background-color:gray; }
</style>

<script>
	function callable(form, chk, field) {
		if(chk.checked==true) {
			selectCheck(form, true, field);
		}else {
			selectCheck(form, false, field);
		}
    }
	function selectCheck(form, obj, field) {
		var i=form.elements.length;
		for(var k=0;k<i;k++) {
			if(form.elements[k].name==field) {
				if(!form.elements[k].disabled) 
					form.elements[k].checked=obj;
			}
		}
	}
</script>
<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.TextBox");
	
	dojo.addOnLoad(function() {
		limpiarValores();
	});
	
	 function marcadores(metodo){
		  var totalRegistros = <%=numcand%>;
		  
		  var actual =  document.getElementById('numeroPage').value;
		  var vistas =  document.getElementById('numeroOfertaVistas').value;		  
		 
		  if('next' == metodo && actual < <%=NUMERO_PAGUINAS%> ){
			  actual++;	 vistas =   parseInt(vistas) + parseInt(<%=NUMERO_MAX_REGISTROS%>);
		  }else if('prev' == metodo  && actual != 1 ){
			  actual--;  vistas = 	parseInt(vistas) - parseInt(<%=NUMERO_MAX_REGISTROS%>);
		  }
		  
		  var aux  = actual * parseInt(<%=NUMERO_MAX_REGISTROS%>);
		  
		  if(aux > totalRegistros){
			  vistas = totalRegistros;
		  }
		  
		  document.getElementById('numeroPage').value =  actual;
		  document.getElementById('numeroOfertaVistas').value =  vistas;
		
	  }
	 
	 
	 function limpiarValores(){		 
		 document.getElementById('numeroPage').value =  1;
		 document.getElementById('numeroOfertaVistas').value =  <%=NUMERO_MAX_REGISTROS%>;
	 }
	 
	
  function doSubmitPagerCand(method){
	 
	  marcadores(method);
	  
	 dojo.byId('method').value = method;
	 dojo.byId('tablaPager').value = "_CANDIDATOS";
	 dojo.xhrPost({url: 'detalleOfertaCreada.do', form:'AdmonCandidatosForm', sync: true, preventCache:true, timeout:180000, 
				  load: function(dataCand){
				      dojo.byId('divCandidatos').innerHTML=dataCand;
				  }});
	}
 	function doSubmitPagerPost(method){
	 	dojo.byId('method').value = method;
		dojo.byId('tablaPager').value = "_POSTULADOS";
	  	dojo.xhrPost({url: 'detalleOfertaCreada.do', form:'AdmonCandidatosForm', sync: true, preventCache:true, timeout:180000, 
				  load: function(dataPost){
				      dojo.byId('divPostulados').innerHTML=dataPost;
				  }});
	}
	function doSubmitInitPagers(){
		dojo.byId('method').value = 'pageTable';
	 	dojo.byId('tablaPager').value = "_CANDIDATOS";
	 	dojo.xhrPost({url: 'detalleOfertaCreada.do', form:'AdmonCandidatosForm', timeout:180000, 
		load: function(dataCand){
			dojo.byId('divCandidatos').innerHTML=dataCand;
			doSubmitPagerPost('pageTable');
		}});
	}
	

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

 function mostrarTodas(tipo, totReg, prefix) {

	if(tipo == 1){
	
	    for(var i = 1; i <= totReg; i++){
    	
    	    dojo.style("hideAll" + prefix, "display", "block");
    	    dojo.style("showAll" + prefix, "display", "none");
			
			dojo.style(eval("\"" + prefix + i + "\""), "display", "block");
			dojo.style(eval("\"" + "hide" + prefix + i + "\""), "display", "block");
			dojo.style(eval("\"" + "show" + prefix + i + "\""), "display", "none");
		}
	}

	if(tipo == 2){
	
    	for(var i = 1; i <= totReg; i++){

    	    dojo.style("hideAll" + prefix, "display", "none");
    	    dojo.style("showAll" + prefix, "display", "block");

			dojo.style(eval("\"" + prefix + i + "\""), "display", "none");
			dojo.style(eval("\"" + "hide" + prefix + i + "\""), "display", "none");
			dojo.style(eval("\"" + "show" + prefix + i + "\""), "display", "block");
		}
	}

}
	
	//eliminar filas
    function eliminarCandidatos(form, tableID, ckeckName) {
    	var lista = "";
    	try {
    		if (confirm("¿Está seguro que desea eliminar del listado al [a los] candidato[s] seleccionado[s]?")) {
    			dojo.byId('btnEliminar').disabled="";
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
         	}
         	if (tableID == "tblCandidatos"){
         		doSubmitDeleteCandidatos(lista);
         	}else{
         		doSubmitDeletePostulados(lista);
         	}
        } catch(e) {
        	alert('Excepcion: '+e);
        }
    }
    
    function doSubmitDeleteCandidatos(listaIdOfertasCandidatos) {
    var msg = "";
	   	try {
	   		dijit.byId('listaIdOfertasCandidatos').set('value', listaIdOfertasCandidatos);
	    	dijit.byId('method').set('value','borrarOfertaCandidatos');
	    	dojo.xhrPost( {					 					 
				url: 'admonCandidatos.do',
			  	form:'AdmonCandidatosForm',
			  	timeout:180000,
			  	load: function(data){
			  		 // alert(data);
					  dojo.byId('divCandidatos').innerHTML=data; 
					  dojo.byId('btnEliminar').disabled=""; 
			    }
			 });
		}catch (e){
			alert(e);
		}
    }
	
	    function doSubmitDeletePostulados(listaIdOfertasCandidatos) {
    	var msg = "";
	   	try {
	   		dijit.byId('listaIdOfertasCandidatos').set('value', listaIdOfertasCandidatos);
	    	dijit.byId('method').set('value','borrarOfertaPostulados');
	    	dojo.xhrPost( {					 					 
				url: 'admonCandidatos.do',
			  	form:'AdmonCandidatosForm',
			  	timeout:180000,
			  	load: function(data){
					  dojo.byId('divPostulados').innerHTML=data; 
					  dojo.byId('btnEliminarPost').disabled=""; 
			    }
			 });
		}catch (e){
			alert(e);
		}
    }
	
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<form name="AdmonCandidatosForm" id="AdmonCandidatosForm" action="/detalleOfertaCreada.do" method="post" dojoType="dijit.form.Form">		
	<input type="hidden" name="method" id="method" value="init"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="tablaPager" id="tablaPager"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idCandidatoEliminar" id="idCandidatoEliminar"  dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="listaIdOfertasCandidatos" id="listaIdOfertasCandidatos"  dojoType="dijit.form.TextBox"/>
	<div class="tab_block">
		<div align="left" style="display:block;" id="returnME">
			<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');">
			<strong>Ir al inicio de Mi espacio</strong></a>
		</div>	     
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="offer">
		<caption>Oferta</caption>
		<tr class="temas">
			<th style="width: 150px; !important">Título de la oferta</th>
			<th>Área de negocio</th>
			<th>Último grado de estudios</th>
			<th>Carrera o especialidad</th>
			<th class="fin">Ubicación</th>
		</tr>
		<tr>
			<td>
				<div class="profesion">
					<strong><a href="<%=context%>detalleoferta.do?method=init&id_oferta_empleo=${AdmonCandidatosForm.ofertaDetalle.idOfertaEmpleo}">${AdmonCandidatosForm.ofertaDetalle.tituloOferta}</a></strong>
				</div>
			</td>
			<td>${AdmonCandidatosForm.ofertaDetalle.subAreaLaboralDescripcion}</td>
			<td>${AdmonCandidatosForm.ofertaDetalle.gradoEstudio}</td>	
			<td>${AdmonCandidatosForm.ofertaDetalle.carrera}</td>	
			<td>${AdmonCandidatosForm.ofertaDetalle.ubicacion}</td>
		</tr>
	</table>
	<div id="divCandidatos" name="divCandidatos"></div>	
	<input type="hidden" name="PageActual" value="">
	<div class="borde_inferior"></div>

	<% if (numcand > NUMERO_MAX_REGISTROS) { %>
	
			<p align="center" >
					<a href="javascript:doSubmitPagerCand('prev')">&lt;&lt;&lt;&nbsp;</a>
					&nbsp;&nbsp;
						..
						<input type="text" id="numeroPage" value="1" size="2" maxlength="2"  style="border: none; width:15px;"  />
						.. 
					&nbsp;&nbsp;
					<a href="javascript:doSubmitPagerCand('next')">&nbsp;&gt;&gt;&gt;</a>
					 de <%=NUMERO_PAGUINAS %> Páginas 
			</p>
			<p align="center">Mostrando 
				<input type="text" id="numeroOfertaVistas" value="<%=NUMERO_MAX_REGISTROS %>" size="2" maxlength="2" style="border: none; width:25px;" /> de <%=numcand %> Candidatos</p>
		
	<% } %>
	<!--<p class="entero" style="text-align:right;">
			<input type="button" class="boton" name="btnEliminar"  id="btnEliminar" value="Eliminar" onclick="eliminarCandidatos(this.form, 'tblCandidatos', 'chkCandidato');" ></p><p>&nbsp;</p>
	</br>
	<div id="divPostulados" name="divPostulados"></div>

	<div class="borde_inferior"></div>
	<c:if test="${not empty PAGE_LIST_POSTULADOS}">
		<p align="center" >
				<a href="javascript:doSubmitPagerPost('prev')">&lt;&lt;&nbsp;Anterior</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:doSubmitPagerPost('next')">Siguiente&nbsp;&gt;&gt;</a>
		</p>
	</c:if>
	<p class="entero" style="text-align:right;">
			<input type="button" class="boton" name="btnEliminarPost"  id="btnEliminarPost" value="Eliminar" onclick="eliminarCandidatos(this.form, 'tblPostulados', 'chkPostulado');" ></p><p>&nbsp;</p>-->

<script>
	doSubmitInitPagers();
</script>
</form>