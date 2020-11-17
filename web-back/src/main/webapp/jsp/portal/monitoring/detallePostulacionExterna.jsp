<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>
<fmt:setBundle basename="portal-web" var="messages"/>



<%
String context = request.getContextPath() +"/";  
%>
     


<script type="text/javascript" >

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
	
 	 function doSubmitPager(method) {
	  	  dojo.xhrPost({url: 'seguimientoPostulacionExterna.do?method='+method, form:'paginationForm', timeout:180000, 
			  load: function(data){
			    dojo.byId('tabla').innerHTML=data;
		  }});
		 
	    }
	    
	      function doSubmitPage(pagina, dif) {
    		dojo.xhrPost({url: 'seguimientoPostulacionExterna.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
				load: function(dataCand){
    	 			dojo.byId('tabla').innerHTML=dataCand;
			}});
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
	
	 function toggleDetail(idDetalle){
	  var lTable = document.getElementById(idDetalle);
   		 lTable.style.display = (lTable.style.display == "table-row") ? "none" : "table-row";
   		//alert('el id es '+idDetalle);
    	
    	
    }  
    
  
</script>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div id="mis_ofertas" class="formApp miEspacio">



        <h2>Mi espacio</h2>

        <div class="tab_block">
            <div align="left" style="display:block;" id="returnME">
                <a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
                    <strong>Ir al inicio de Mi espacio</strong>
                </a>
            </div>
            <div class="Tab_espacio">
                <h3>Mis ofertas de empleo</h3>

                <div class="subTab">
                    <ul>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=misPostulaciones"/>">Mis postulaciones</a>
                        </li>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=misOfertasGuardadas"/>">Mis ofertas guardadas</a>
                        </li>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=empresasQueMeBuscan"/>">Empresas que me buscan</a>
                        </li>
                        <!--INI_JGLC_PPC-->
<!--                         <li > -->
<%--                             <a href="${context}registroPostulacionExterna.do?method=init">Registrar postulaciones externas</a> --%>
<!--                         </li> -->
<!--                         <li class="curSubTab"> -->
<!--                         	<span >Seguimiento a postulaciones externas</span> -->
                           
<!--                         </li> -->
<!--                         <li> -->
<!--                             <a href="/adminNoPostulacionesCandidato.do?method=init" id="registrar_motivo_no_postulacion">Registrar motivo de no postulaci&oacute;n</a> -->
<!--                         </li> -->
                        <!--FIN_JGLC_PPC-->
                    </ul>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>


<div id="cont-resultados">
<div id="tabla">

<bean:define id="sufijo" value=""/>
<jsp:include page="../layout/pager.jsp" flush="true">
        <jsp:param name="SUFIJO" value="${sufijo}"/>
        <jsp:param name="tipoRegistros" value="postulaciones"/>		 
</jsp:include>
</div>	
<form name="paginationForm" id="paginationForm" method="post" action="seguimientoPostulacionExterna.do" dojoType="dijit.form.Form">
				
				<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
	</form>		

	</div>		
    </div>


<script>
	doSubmitPager('page');
</script>


