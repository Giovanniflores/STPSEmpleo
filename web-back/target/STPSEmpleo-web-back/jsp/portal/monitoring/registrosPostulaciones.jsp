<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>

<fmt:setBundle basename="MessageResources" var="portalbundle" />
<fmt:setBundle basename="portal-web" var="messages" />



<%
	String context = request.getContextPath() + "/";
%>




<script type="text/javascript">

	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");
	
	function displayErrorMsg(textBox, errmsg){
		var originalValidator = textBox.validator;
		textBox.validator = function() {return false;};
		textBox.validate();
		textBox.validator = originalValidator;
		
		dijit.showTooltip(
			    errmsg,
			    textBox.domNode, 
			    textBox.get("tooltipPosition"),
			    !textBox.isLeftToRight()
		);
	}			
	
	
	
	
	function orderBy(orderType, columnNumber){
			dojo.xhrPost({url: 'seguimientoPostulacionExterna.do?method=orderByColumn&orderType=' + orderType + '&columnNumber=' + columnNumber, timeout:180000, 
			  load: function(availableEventsData){
			      dojo.byId('tabla').innerHTML = availableEventsData;
			      // TODO: Change to Dojo methods
			      if(columnNumber == '1' && orderType == 'asc'){
						document.getElementById('postNameAsc').style.display = 'none';
				  		document.getElementById('postNameDesc').style.display = 'inline';
				  }else if(columnNumber == '1' && orderType == 'desc'){
				  		document.getElementById('postNameDesc').style.display = 'none';
				  		document.getElementById('postNameAsc').style.display = 'inline';
				  }else if(columnNumber == '2' && orderType == 'asc'){
				  		document.getElementById('empresaNameAsc').style.display = 'none';
				  		document.getElementById('empresaNameDesc').style.display = 'inline';
			  	  }else if(columnNumber == '2' && orderType == 'desc'){
				  		document.getElementById('empresaNameDesc').style.display = 'none';
				  		document.getElementById('empresaNameAsc').style.display = 'inline';
				  }
			  }});
		 }

	function alertMsg(msg){
		alert(msg);
	}
   
    function toggleDetail(idDetalle){
    	 var lTable = document.getElementById(idDetalle);
   		 lTable.style.display = (lTable.style.display == "table-row") ? "none" : "table-row";
   		//alert('el id es '+idDetalle);
    }
    
    function enviarAMiEspacioCandidato(){
    	dijit.byId('MensajeAlerConfirt').hide();
    	window.location.href = '<c:url value="/miEspacioCandidato.do"/>'
    
    }
    
    function cancelar(){
		dojo.byId('mensajeConfir').innerHTML = 'Los datos no guardados se perderán ¿Continuar?';
		dijit.byId('MensajeAlerConfirt').show();		
    }
    
    function closeDialog(){
        dijit.byId('MensajeAlerConfirt').hide();
        setTimeout("window.location.reload();",10);
	}    
	
	  function doSubmitPage(pagina, dif) {
    		dojo.xhrPost({url: 'seguimientoPostulacionExterna.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
				load: function(dataCand){
    	 			dojo.byId('tabla').innerHTML=dataCand;
			}});
    	 }
</script>

<bean:define id="estatusCont" value="false"></bean:define>
<table style="width:100%">
<caption>Postulaciones externas</caption>
<tr>
<th style="width: 400px">
	<div class="fl">Puesto</div>
	<div class="order-fix">
		<a id="postNameAsc"    title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','1')"></a> 
		<a id="postNameDesc" 	  title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','1')"></a>
	</div>
</th>
<th style="width: 345px">
	<div class="fl">Empresa</div>
	<div class="order-fix">
		<a id="empresaNameAsc"    title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','2')"></a> 
		<a id="empresaNameDesc" 	  title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','2')"></a>
	</div>
</th>
<c:choose>
	<c:when test="${showStatCol == true}">
		<th>
			<div class="fl">Estatus</div>
			<div class="order-fix"></div>			
		</th>
	</c:when>
	<c:otherwise>
		<th>
			<div class="fl">Dar seguimiento</div>
			<div class="order-fix"></div>
		</th>
	</c:otherwise>
</c:choose>
</tr>
<logic:iterate id="postulaciones" indexId="postulacionesIndexId" collection='<%= request.getSession(false).getAttribute("PAGE_LIST") %>'>
<bean:define id="indexMod3" value='<%= String.format("%d",postulacionesIndexId % 2) %>'/>
	<logic:equal name="indexMod3" value="0">
		<bean:define id="trClase" value=""/>
	</logic:equal>
	<logic:notEqual name="indexMod3" value="0">
		<bean:define id="trClase" value="odd"/>
	</logic:notEqual>
	<tr class="${trClase}">
		<td style="width: 400px">
			
           <a id="ligaDet<bean:write name="postulaciones" property="idPostulacionExterna"/>" style="color: gray; font-weight: bold;" href="#" onclick="javascript:toggleDetail(<bean:write name="postulaciones" property="idPostulacionExterna"/>)"><bean:write name="postulaciones" property="tituloOferta"/></a> 
		</td>
		<td style="width: 345px">
			<bean:write name="postulaciones" property="nombreEmpresa"/>
		</td>
		<td>
		<c:choose>
			<c:when test="${postulaciones.estatus == 2}">
				<c:out value="CONTRATADO"></c:out>
			</c:when>
		
		<c:otherwise>
			<a href="<%=request.getContextPath()%>/seguimientoPostulacionExterna.do?method=cargaDetallePostulacion&idPostulacionExterna=${postulaciones.idPostulacionExterna}">Dar seguimiento a esta postulación</a>
		</c:otherwise>
		</c:choose>
			</td>
	</tr>
		<tr
			id="<bean:write name="postulaciones" property="idPostulacionExterna"/>"
			style="display: table-row;">
			<td colspan="3">
			<p style="font-weight: bold;">Resumen:</p>
			Persona de contacto: <bean:write
					name="postulaciones" property="contactoEmpresa" />, Teléfono de
				contacto: <bean:write name="postulaciones" property="acceso" /> <bean:write
					name="postulaciones" property="clave" /> <bean:write
					name="postulaciones" property="telefono" />, <c:if
					test="${postulaciones.extension != null}">
			Extensión: <bean:write name="postulaciones" property="extension" />,
		</c:if> <c:if test="${postulaciones.contactoCorreoElectronico != null}">
		Correo electrónico de contacto: <bean:write name="postulaciones"
						property="contactoCorreoElectronico" />,<br />
				</c:if> 
				<fmt:setLocale value="es_MX"/>
				<fmt:formatNumber type="currency" value="${postulaciones.salario}"
					var="sueldo"></fmt:formatNumber> 
					Salario: <c:out value="${sueldo}"></c:out>,
				<fmt:formatDate value="${postulaciones.fechaContacto}"
					pattern="dd-MM-yyyy" var="fechaCont" /> Fecha de contacto: <c:out
					value="${fechaCont}"></c:out>, <fmt:formatDate
					value="${postulaciones.fechaEntrevista}" pattern="dd-MM-yyyy"
					var="fechaEnt" /> Fecha de entrevista: <c:out value="${fechaEnt}"></c:out>

			</td>
		</tr>
	</logic:iterate>
</table>
<bean:define id="sufijo" value="" />
<jsp:include page="../layout/pager.jsp" flush="true">
	<jsp:param name="SUFIJO" value="${sufijo}" />
	<jsp:param name="tipoRegistros" value="postulaciones" />
</jsp:include>





