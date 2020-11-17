<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.TextBox");

var dialogTestimonio;

function showWindow(){
	if (!dialogTestimonio){
		dialogTestimonio = new dijit.Dialog({
	        title: 'Comparte tu testimonio',
	        href: '<%=context%>testimonio.do?method=initWindow',
	        style: "width:400px; height:350px;",
	        showTitle: false,
	        draggable : false
	    });
	}

	dialogTestimonio.show();
}

function closeWindow(){
	dialogTestimonio.hide();
}

function doSubmitTestimonio(method){
	if(document.testimonioForm.testimonio.value==''){
		alert('Debe escribir su testimonio para poder guardarlo');
	}else {
		document.testimonioForm.method.value=method;
		dojo.xhrPost(
				 {url: 'testimonio.do?method=registrar', form:'testimonioForm', timeout:180000, // Tiempo de espera 3 min
				  load: function(data){
					  dialogTestimonio.hide();
					  dojo.byId('mensaje').innerHTML = 'Tu testimonio se guardo correctamente';
					  dijit.byId('MensajeAlert').show();	 	
				  }
				 } );
	}
}

function maxlength(field, size) {
    if (field.value.length > size) {
        field.value = field.value.substring(0, size);
    }
}
</script>


<div class="formApp miEspacio">
<h2>Mi espacio</h2>
<div class="tab_block">
	<div align="left" id="returnME" style="display:block;">
		<a href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');" class="expand">
		<strong>Ir al inicio de Mi espacio</strong></a>
	</div>
	<div class="Tab_espacio">
		<h3>Programas y servicios</h3>
		<div class="subTab">
			<ul>
				<li class="curSubTab">
					<span>Consultar mis entrevistas en línea</span>
				</li>
				<li>
					<a href="<c:url value="/miespacionav.do?method=agendaCita"/>">Solicitar una cita</a>
				</li>
				<li>
					<a href="<c:url value="/registrarCandidatoEvento.do?method=init"/>">Registrarme a un evento de Ferias de Empleo</a>
				</li>
				<!--INI_JGLC_PPC-->
<%-- 				 <c:if test="${candidatoheader.isActiveToPpc() || candidatoheader.isInactiveToPpc()}"> --%>
<!-- 					<li class="width_f"> -->
<!-- 						<a target="_blank" href="/miespacionav.do?method=terminosCondicionesPpcEnPdf" id="imprimir_terminos_condiciones_ppc_sd">Imprimir los términos y condiciones del Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD)</a> -->
<!-- 					</li> -->
<%-- 				</c:if> --%>
				<!--FIN_JGLC_PPC-->
			</ul>
			<div class="clearfix"></div>
		</div>
	</div>	      
</div>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
              <fieldset class="mientrevista" >
              	<legend>Entrevistas en línea</legend>
           		<div id="user_interview">
                  <jsp:include page="/entrevistaProgramada.do" flush="true" >
						<jsp:param name="method" value="entrevistaProgramadaCandidatoEnLinea" />						
					</jsp:include>                 
                 </div> 
                <div id="mis_herramientas" class="gris">
                  <h3>Mis herramientas</h3>
                  <ul>
                  	<li><a id="entrevista" href="<c:url value="/entrevistaProgramada.do?method=entrevistaProgramadaEnlineaCandidato"/>">Entrevista en línea</a></li>
                  	<!-- li><a href="javascript:showWindow()" id="testimonio">Compartir mi testimonio</a></li -->
                  </ul>
                 </div> 
              </fieldset>           
                <div style="background: none">                
	                 <jsp:include page="/entrevistaProgramada.do" flush="true" > 
							<jsp:param name="method" value="entrevistaProgramadaCandidato" />							
					</jsp:include> 
				</div>
				

</div>
<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false" >
<div class="campoTexto margin_top_30">
		  <table class="alertDialog" >
			 <tr align="center">
				 <td><div id ="mensaje"></div>&nbsp;</td>				 
			 </tr>
		 </table>	
		 <div class="form_nav">
				<input type="button" class="boton" value="Aceptar" onclick="javascript:location.href ='<c:url value="/miEspacioCandidato.do"/>';"/>
		</div>
	</div>
</div>
