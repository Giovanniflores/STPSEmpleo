<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ page import="mx.gob.stps.portal.core.empresa.vo.EmpresaVO"%>
<%@ page import="mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate"%>
<%@ page import="mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl"%>
<%@ page import="mx.gob.stps.portal.web.security.vo.UsuarioWebVO"%>

<%String context = request.getContextPath() +"/";%> 

<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");
dojo.require("dijit.Dialog");

var dialogTestimonio;

function showWindow(){
	if (!dialogTestimonio){
		dialogTestimonio = new dijit.Dialog({
	        title: 'Comparte tu testimonio',
	        href: '${context}testimonio.do?method=initWindow',
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

			<div class="tab_block">
				<div align="left" style="display:block;" id="returnME">
					<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');">
						<strong>Ir al inicio de Mi espacio</strong>
					</a>
				</div>
				<div class="Tab_espacio">
					<h3>Entrevistas en línea</h3>
					<div class="subTab">			
						<ul>
							<li class="curSubTab"><span>Entrevistas en línea</span></li>
							<li><a href="<c:url value="/acercaAbriendoEspacios.do?method=init"/>">Acerca del portal Abriendo Espacios</a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
            <div class="mientrevista">
            	<fieldset>
            		<legend>Entrevista en línea</legend>
            	<div id="user_interview">
                  
                  <jsp:include page="/entrevistaProgramada.do" flush="true" >
						<jsp:param name="method" value="entrevistaProgramadaEmpresaEnLinea" />						
					</jsp:include>                 
                 </div> 
 
                  <div id="mis_herramientas" class="gris">
	                  <h3>Mis herramientas</h3>	   
					  <ul>
					  	<li><a id="entrevista" href="<c:url value="/entrevistaProgramada.do?method=entrevistaProgramadaEnlineaEmpresa"/>">Entrevista en línea</a></li>					  
					  	<!-- li><a id="testimonio" href="javascript:showWindow()">Compartir mi testimonio</a></li  -->
					  </ul>             
	                  
	                  
	                 
                  </div>
                  </fieldset>
                   <div>
	                 <br>       
		                 <jsp:include page="/entrevistaProgramada.do" flush="true" > 
								<jsp:param name="method" value="entrevistaProgramadaEmpresa" />							
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
				<input type="button" class="boton" value="Aceptar" onclick="javascript:location.href ='<c:url value="/miEspacioEmpresas.do"/>';"/>
		</div>
	</div>
</div>