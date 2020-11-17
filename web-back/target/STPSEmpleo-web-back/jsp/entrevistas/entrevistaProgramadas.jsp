<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="mx.gob.stps.portal.core.infra.vo.EntrevistaVO"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper"%>
<%@ page import="java.util.List"%>

<%
		List<CatalogoOpcionVO> listaHorarioContacto	 = (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_HORARIO_CONTACTO");	
		String context = request.getContextPath() +"/";	
		List<EntrevistaVO> listaEntrevista = (List<EntrevistaVO>)request.getAttribute("ENTREVISTA_PROGRAMADA");		
		String tituloEtiqueta 	= (String)request.getAttribute("TITULO_ETIQUETA");
		String tituloTipo 		= (String)request.getAttribute("TITULO_TIPO");
		String inicioPage =  context + "inicio.do";
		Integer idContador = 0;
		String 	estiloTr = "";
%>

<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	 
	  dojo.require("dijit.form.Form");
	  dojo.require("dijit.form.ValidationTextBox");
	  dojo.require("dijit.form.SimpleTextarea");
	  dojo.require("dijit.form.NumberTextBox");
	  dojo.require("dijit.form.Button");
	  dojo.require("dojox.image.Lightbox");
	  dojo.require("dojox.widget.FisheyeLite");
	  dojo.require("dojo.parser");
      dojo.require("dijit.form.Button");
      dojo.require("dijit.Dialog");
      dojo.require("dijit.form.TextBox");	  
	  dojo.require("dijit.dijit"); // loads the optimized dijit layer
      dojo.require("dijit._Calendar");
      dojo.require("dijit.form.DateTextBox");
      
      function showDialogEntrevistaEnLinea(idEstatus,idEntrevista,tipo,id, 
      		nombre,fechaFinEntrevista,correoCandidato,correoEmpresa,razonSocial,candidato,
      		fecha,hora,empleo) {
	   	 	if (dojo.byId('estatusIcono_' + idEstatus).title == id 
	   	 		&& id != 'Reprogramación'){
	   	 		mensaje("Esta Entrevista ya tiene el Estatus Solicitado.");
	   	 		return;
	   	 	} 
	   	 	if (dojo.byId('estatusIcono_' + idEstatus).title == 'Rechazar'	   	 	
	   	 			&& tipo == 'Empresa' ){
	   	 		mensaje("La empresa no puede reprogramar las entrevistas que han sido rechazadas.");
	   	 		return;
	   	 	} 
  			dojo.byId('fechaEntrevista').value = "";
  			dojo.byId('hora').value = "";  			
			dojo.byId('correoCandidato').value 	= correoCandidato;
			dojo.byId('correoEmpresa').value 	= correoEmpresa;
			dojo.byId('nombreContactoEmpresa').value 	= nombre;
			dojo.byId('nombreContactoCandidato').value 	= nombre;
			dojo.byId('nombreEmpresa').value 	= razonSocial;
			dojo.byId('tituloOferta').value 	= empleo;
			dojo.byId('nombreCandidato').value 	= candidato;			 
	        if ('<%=EntrevistaHelper.EMPRESA%>' == tipo && '<%=EntrevistaHelper.REPROGRAMAR%>' == id) { 
	        	//showDialogVenta('ventanaReprogramar');
		      	mostrarDatosEntrevista(idEntrevista);
		      	setFechaEntrevista(fechaFinEntrevista);
	          	dojo.byId('nombre').innerHTML = nombre;
	          	document.reprogramarEntrevistaNameForm.idEntrevista.value 	= idEntrevista;
       	 	  	document.reprogramarEntrevistaNameForm.idOfertaEmpleo.value 	= empleo;
       	 	  	document.reprogramarEntrevistaNameForm.nombreContactoEmpresa.value = nombre;
       	 	  	document.reprogramarEntrevistaNameForm.nombreCandidato.value = candidato;
       	 	}
       	 	if ('<%=EntrevistaHelper.CANDIDATO%>' == tipo && '<%=EntrevistaHelper.REPROGRAMAR%>' == id){
			      //showDialogVenta('ventanaReprogramar');
			      mostrarDatosEntrevista(idEntrevista);
			      setFechaEntrevista(fechaFinEntrevista);
		          dojo.byId('nombre').innerHTML = empleo;
		          document.reprogramarEntrevistaNameForm.idEntrevista.value 	= idEntrevista;
	       	 	  document.reprogramarEntrevistaNameForm.idOfertaEmpleo.value 	= empleo;
	       	 	  document.reprogramarEntrevistaNameForm.nombreContactoEmpresa.value = nombre;
	       	 	  document.reprogramarEntrevistaNameForm.nombreCandidato.value = candidato;
        	}
       	 	if ('<%=EntrevistaHelper.CANDIDATO%>' == tipo && '<%=EntrevistaHelper.RECHAZAR%>' == id){
			      showDialogVenta('ventanaRechazar');
			      document.rechazarEntrevistaNameForm.idEntrevista.value 	= idEntrevista;
	       	 	  document.rechazarEntrevistaNameForm.idOfertaEmpleo.value 	= empleo;
	       	 	  document.rechazarEntrevistaNameForm.fecha.value = fecha; 
	       	 	  document.rechazarEntrevistaNameForm.hora.value = hora;   
				  document.rechazarEntrevistaNameForm.correoEmpresa.value = correoEmpresa;
				  document.rechazarEntrevistaNameForm.correoCandidato.value = correoCandidato;
				  document.rechazarEntrevistaNameForm.tituloOferta.value = empleo;
				  document.rechazarEntrevistaNameForm.nombreContactoEmpresa.value = nombre;  
				  document.rechazarEntrevistaNameForm.nombreCandidato.value = candidato;     	 	  	          
       		}
       		
       	 if('<%=EntrevistaHelper.EMPRESA%>' == tipo && '<%=EntrevistaHelper.CANCELADA%>' == id){
		      showDialogVenta('ventanaCancelar');
		      document.cancelarEntrevistaNameForm.idEntrevista.value 	= idEntrevista;
       	 	  document.cancelarEntrevistaNameForm.idOfertaEmpleo.value 	= empleo; 
       	 	  document.cancelarEntrevistaNameForm.fecha.value = fecha; 
       	 	  document.cancelarEntrevistaNameForm.hora.value = hora; 
       	 	  	

				document.cancelarEntrevistaNameForm.correoEmpresa.value = correoEmpresa;
				document.cancelarEntrevistaNameForm.correoCandidato.value = correoCandidato;
				document.cancelarEntrevistaNameForm.tituloOferta.value = empleo;
				document.cancelarEntrevistaNameForm.nombreEmpresa.value = razonSocial;    
       		}
       		
       	 if('<%=EntrevistaHelper.ACEPTAR%>' == id){
       	 
       	 	if('<%=EntrevistaHelper.EMPRESA%>' == tipo && 
       	 			dojo.byId('estatusIcono_' + idEstatus).title == 'Nueva'){
       	 				mensaje("Usted no puede cambiar la entrevista a Aceptada");
       	 		return;	   	 	
       	 	}
       	        	       	      
       	 	  showDialogVenta('ventanaAceptar');
       	 	  document.aceptarEntrevistaNameForm.idEntrevista.value 	= idEntrevista;
       	 	  document.aceptarEntrevistaNameForm.idOfertaEmpleo.value 	= empleo;       	 	  

       	 	  document.aceptarEntrevistaNameForm.fecha.value = fecha; 
       	 	  document.aceptarEntrevistaNameForm.hora.value = hora;        	 	  	
					
							
				document.aceptarEntrevistaNameForm.correoEmpresa.value = correoEmpresa;
				document.aceptarEntrevistaNameForm.correoCandidato.value = correoCandidato;
				document.aceptarEntrevistaNameForm.tituloOferta.value = empleo;
				document.aceptarEntrevistaNameForm.nombreEmpresa.value = razonSocial;				
				document.aceptarEntrevistaNameForm.nombreCandidato.value = candidato;
       	 	}
        }

	    function validarForm(id){
		    var bandera = false; 
			    if(dijit.byId(id).isValid()){
			    	bandera = true;
			    } else {
			     mensaje("Por favor proporcione todos los datos solicitados");
			    }
	    return bandera;
	    } 			
	    function closeDialog(id) {	 
		    dijit.byId(id).hide();
		 	//if ('ventanaReprogramar' == id)
		    //setTimeout("refresh()",0);
		}
		function setDiv(id, visible) {
			var vStyle = visible ? 'visible':'hidden';
			var vBlock = visible ? 'block':'none';
			document.getElementById(id).style.visibility = vStyle;
			document.getElementById(id).style.display = vBlock;
		}
		function mostrarDatosEntrevista(idEntrevista) {
			setDiv('ventanaReprogramar', true);
			dijit.byId('fechaEntrevista').attr('required', true);
			dijit.byId('hora').attr('required', true);
			document.getElementById('idEntrevista').value=idEntrevista;
		}
		function ocultarDatosEntrevista() {
			setDiv('ventanaReprogramar', false);
			dijit.byId('fechaEntrevista').attr('required', false);
			dijit.byId('hora').attr('required', false);
		}
	    function showDialogVenta(id) {
	    	dijit.byId(id).show();
	        dijit.byId(id).closeButtonNode.style.display = 'none';  
	  	}
	   
	   function redireccionarLogin(){
			window.location='<%=inicioPage%>';
	   }
	   
	   function refresh(){
           window.location.reload();
   		}
  
	   function mensaje(mensaje) {
	   		message(mensaje);
			//dojo.byId('mensaje').innerHTML = mensaje;
			//dijit.byId('MensajeAlert').show();		
	   }
		

		
	function doSubmit(urlAction,form,ventana) {	
		//var closeVentana = "closeDialog('" + ventana + "')";
      	if (validarForm(form)) {
	      	if (ventana == 'ventanaReprogramar' && !validar72hr()) {
	      		return ;
	      	}
	      	if (ventana == 'ventanaReprogramar') {
	      		ocultarDatosEntrevista();
	      	}else {
	      		closeDialog(ventana);
	      	}
			 dojo.xhrPost(
						 { 
						  url: urlAction,
						  form: form,
						  timeout:180000, // Tiempo de espera 3 min
						  load: function(data) {
						 	var res = dojo.fromJson(data); // Datos del servidor
						 		if(res.message != null){	
									 mensaje(res.message);	
									 setTimeout("refresh()",10000);								
								}
								//if(type = 'ext') {
									//setTimeout("refresh()",10000);
								//else {
									//setTimeout(closeVentana,10000);
								//}	 		 				
						   	 }
						 } 
					 );
		 		}	
	 	}
    
    function setFechaEntrevista(fecha){
    
    var elem = fecha.split('/');
	var	dia = elem[0];
	var	mes = elem[1];
	var	ano = elem[2];
	
    fechaFin = new Date(); 
    fechaFin.setFullYear(ano,mes-1,dia);   
    dijit.byId('fechaEntrevista').constraints.max = fechaFin;
       
    }
    
    
    
    var formatDate = function (formatDate, formatString) {
	if(formatDate instanceof Date) {
		var months = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
		var yyyy = formatDate.getFullYear();
		var yy = yyyy.toString().substring(2);
		var m = formatDate.getMonth() + 1;
		var mm = m < 10 ? "0" + m : m;
		var mmm = months[m];
		var d = formatDate.getDate();
		var dd = d < 10 ? "0" + d : d;
		
		var h = formatDate.getHours();
		var hh = h < 10 ? "0" + h : h;
		var n = formatDate.getMinutes();
		var nn = n < 10 ? "0" + n : n;
		var s = formatDate.getSeconds();
		var ss = s < 10 ? "0" + s : s;

		formatString = formatString.replace(/yyyy/i, yyyy);
		formatString = formatString.replace(/yy/i, yy);
		formatString = formatString.replace(/mmm/i, mmm);
		formatString = formatString.replace(/mm/i, mm);
		formatString = formatString.replace(/m/i, m);
		formatString = formatString.replace(/dd/i, dd);
		formatString = formatString.replace(/d/i, d);
		formatString = formatString.replace(/hh/i, hh);
		formatString = formatString.replace(/h/i, h);
		formatString = formatString.replace(/nn/i, nn);
		formatString = formatString.replace(/n/i, n);
		formatString = formatString.replace(/ss/i, ss);
		formatString = formatString.replace(/s/i, s);

		return formatString;
		} else {
			return "";
		}
    };

    function validar72hr(){    
	    var Hr72 = 3;
		var fecha72 = new Date();
			fecha72.setDate(fecha72.getDate() + Hr72 );
		var resultado72	= formatDate(fecha72,'dd/mm/yyyy');
		var horaArreglo = dojo.byId('hora').value.split(':');
		var horaBox 	= horaArreglo[0]+horaArreglo[1];
		var horaActual 	= formatDate(fecha72,'hhnn');
	    if (resultado72 == dojo.byId('fechaEntrevista').value) {    		
			if(horaBox > horaActual) {		    	      	  
		    	   return true;   	 
		    }else {
		    	mensaje('La fecha de entrevista debe ser mayor a 72 horas naturales con respecto a la fecha actual.');	
		    	return false;
		    }
    	}else{
    		return true;
    	}
	}
   
	 function getFechaEntrevista(){
	  var Hr72 = 3;
	   fechaActual = new Date(); 
       fechaActual.setDate(fechaActual.getDate() + Hr72 );
       fechaEntevista = new Date(fechaActual.getFullYear(),fechaActual.getMonth(),fechaActual.getDate());     
        dijit.byId('fechaEntrevista').constraints.min = fechaEntevista;  
      }     
    
      dojo.addOnLoad(function() {                   
            getFechaEntrevista();
       });   
   
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>


<table cellspacing="0" border="0" class="entrevista margin_top_40" style="margin-bottom: 50px;">
<caption>Administrar entrevistas</caption>
 <tbody>
		<tr class="temas">
			<td align="center" width="150px;" >Oferta de empleo</td>
			<td align="center" width="250px;" ><%=tituloEtiqueta %></td>
			<td align="center" width="250px;" >Nombre de la persona de contacto</td>
			<td align="center" width="100px;" >Fecha</td>
			<td align="center" width="20px;" >Hora</td>
			<td align="center" width="150px;" >Estatus</td>
			<td align="center" width="100px;" >Opciones</td>		
		</tr>	
  <% for (EntrevistaVO entrevistaVo : listaEntrevista) { %>
			<tr  <%=EntrevistaHelper.getEstiloTr(idContador)%>>
			<td align="center" ><%= entrevistaVo.getTituloOferta()%></td>
			<td align="center"><%= EntrevistaHelper.getTipoDato(tituloTipo,
												entrevistaVo.getNombre(),
												entrevistaVo.getRazonSocial())%></td>
			<td align="center"><%= entrevistaVo.getContactoEmpresa()%></td>
			<td align="center"><%= entrevistaVo.getFechaString()%></td>
			<td align="center"><%= entrevistaVo.getHora()%></td>
			<td align="center"><%= EntrevistaHelper.getIcono(tituloTipo,entrevistaVo.getEstatus(),idContador)%></td>
						<td align="center"><%= EntrevistaHelper.getIconoOpciones(
						entrevistaVo.getEstatus(),
						idContador,
						entrevistaVo.getIdEntrevista(),
						tituloTipo,
						entrevistaVo.getContactoEmpresa(),
						entrevistaVo.getTituloOferta(),
						entrevistaVo.getFechaFin(),
						entrevistaVo.getCorreoCandidato(),
						entrevistaVo.getCorreoEmpresa(),
						entrevistaVo.getRazonSocial(),
						entrevistaVo.getFechaString(),
						entrevistaVo.getHora(),
						entrevistaVo.getNombre()
						)%>
			</td>
		</tr>
		<%idContador++;}%>
  
  <%if(listaEntrevista != null && listaEntrevista.size()!=0 ){%> 
	   <tr>   	
	   		<td colspan="7" align="right">&nbsp;</td>
	   </tr>
	   <tr>   	
	   		<td align="right" colspan="7">
	   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img title="Nueva" src="images/bt_nueva.gif"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nueva
	   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img title="Aceptada" src="images/bt_aceptada.gif"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Aceptada
	   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img title="Reprogramación" src="images/bt_reprogramar.gif"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Reprogramación
	   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img title="Rechazar" src="images/bt_rechazada.gif"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rechazar
	   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img title="Cancelada" src="images/bt_cancelar.gif"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cancelada
	   		</td>
	   </tr>
   <%}else {%>
   <tr>
   		<td colspan="7" align="center" class="Font60">
   			<div class="gris" style="font-weight: bold">No existen entrevistas por administrar.</div>
   		</td>
   </tr>
   <%}%>
   </tbody>
</table>
<!-- Reprogramar Entrevista -->
	<!-- div dojoType="dijit.Dialog" id="ventanaReprogramar" title="Reprogramación de entrevista" style="display:none" draggable ="false" -->
	<div class="entero" id="ventanaReprogramar" style="display:none; visibility:hidden; position:relative; left:0px;">
		<form id="reprogramarEntrevistaNameForm" name="reprogramarEntrevistaNameForm"  method="post"  dojoType="dijit.form.Form">
		    <input type="hidden" name="method" value="reprogramar"/>
		    <input type="hidden" name="tipo"   value="<%=tituloTipo%>"/>
		    <input type="hidden" id="idEntrevista" name="idEntrevista" value=""/>
		    <input type="hidden" id="idOfertaEmpleo" name="ofertaEmpleo" value=""/>
		    <input type="hidden" id="correoCandidato" name="correoCandidato" value=""/>
		    <input type="hidden" id="correoEmpresa" name="correoEmpresa" value=""/>
		    <input type="hidden" id="nombreContactoEmpresa" name="nombreContactoEmpresa" value=""/>
		    <input type="hidden" id="nombreContactoCandidato" name="nombreContactoCandidato" value=""/>
		    <input type="hidden" id="nombreCandidato" name="nombreCandidato" value=""/>
		    <input type="hidden" id="nombreEmpresa" name="nombreEmpresa" value=""/>
		    <input type="hidden" id="tituloOferta" name="tituloOferta" value=""/>		   
            <div class="entero" id="divEntrevista">
				<strong>Fecha:</strong><br>
				<input type="text" name="fechaEntrevista" id="fechaEntrevista"
					dojoType="dijit.form.DateTextBox" required="false" size="10"
					style="position:relative; left:0px;"/><br><br>
				<strong>Hora: <i>formato de 24 hrs, ejemplo (13:30)</i></strong><br>
				<input id="hora" name="hora" dojoType="dijit.form.ValidationTextBox"
					required="false"
					size="5"
					regExp="^(0[1-9]|1\d|2[0-3]):([0-5]\d)$"
					invalidMessage="Hora inv&aacute;lida, debe indicarse en formato de 24hrs, ejemplos 09:00 o 15:30"
					uppercase="true" size="5" maxlength="5" value=""
					style="position:relative; left:0px;" trim="true"/><br><br>
					<input type="button" class="boton" name="botonEnviar" id="botonEnviar"
							value="Enviar Invitaci&oacute;n" onclick="javascript:doSubmit('/entrevistaProgramada.do','reprogramarEntrevistaNameForm','ventanaReprogramar');"/>
					<input type="button" class="boton" name="btnCancelar" id="btnCancelar"
						   value="Cancelar"
						   onclick="ocultarDatosEntrevista()"/>
			</div>                    
          </form>                   
	</div>
			

			
<!-- Rechazar Entrevista -->	

	<div dojoType="dijit.Dialog" id="ventanaRechazar" title="Rechazar entrevista" style="display:none" draggable ="false">
		<form id="rechazarEntrevistaNameForm" name="rechazarEntrevistaNameForm"  
		    method="post"  dojoType="dijit.form.Form">
		    
		    <input type="hidden" name="method" value="rechazar"/>
		    <input type="hidden" name="tipo"   value="<%=tituloTipo%>"/>
		    <input type="hidden" id="idEntrevista" name="idEntrevista" value=""/>
		    <input type="hidden" id="idOfertaEmpleo" name="ofertaEmpleo" value=""/>
		    <input type="hidden" id="correoCandidato" name="correoCandidato" value=""/>
		    <input type="hidden" id="correoEmpresa" name="correoEmpresa" value=""/>		    
		    <input type="hidden" id="nombreContactoEmpresa" name="nombreContactoEmpresa" value=""/>
		    <input type="hidden" id="nombreContactoCandidato" name="nombreContactoCandidato" value=""/>
		    <input type="hidden" id="nombreEmpresa" name="nombreEmpresa" value=""/>
		    <input type="hidden" id="tituloOferta" name="tituloOferta" value=""/>
		    <input type="hidden" id="nombreCandidato" name="nombreCandidato" value=""/>
		    <input type="hidden" id="fecha" name="fecha" value=""/>
		    <input type="hidden" id="hora" name="hora" value=""/>
		    
         <table class="alertDialog"  border="0" width="100%" heigth="50%">
				<tr>
					<td colspan="4">
						¿Estas seguro que deseas rechazar la entrevista programada?
					</td>
				</tr>
				<tr>
					<td colspan="2" height="30px;">&nbsp;&nbsp;&nbsp;</td>
				</tr>						
				<tr>
				<td align="center">
					   <input type="button" value="Aceptar" class="boton" onclick="javascript:doSubmit('/entrevistaProgramada.do','rechazarEntrevistaNameForm','ventanaRechazar');">
			    </td>			    
				<td align="center">
						<input type="button" value="Cancelar" class="boton" onclick="closeDialog('ventanaRechazar')">   
					</td>
				</tr>								
		</table>                   
        </form>                   
	</div>	
<!-- Cancelar Entrevista -->	

	<div dojoType="dijit.Dialog" id="ventanaCancelar" title="Cancelar entrevista" style="display:none" draggable ="false"> 
		<form id="cancelarEntrevistaNameForm" name="cancelarEntrevistaNameForm"  
		    method="post"  dojoType="dijit.form.Form">
		    
		    <input type="hidden" name="method" value="cancelar"/>
		    <input type="hidden" name="tipo"   value="<%=tituloTipo%>"/>
		    <input type="hidden" id="idEntrevista" name="idEntrevista" value=""/>
		    <input type="hidden" id="idOfertaEmpleo" name="ofertaEmpleo" value=""/>
		    <input type="hidden" id="correoCandidato" name="correoCandidato" value=""/>
		    <input type="hidden" id="correoEmpresa" name="correoEmpresa" value=""/>		    
		    <input type="hidden" id="nombreContactoEmpresa" name="nombreContactoEmpresa" value=""/>
		    <input type="hidden" id="nombreContactoCandidato" name="nombreContactoCandidato" value=""/>
		    <input type="hidden" id="nombreEmpresa" name="nombreEmpresa" value=""/>
		    <input type="hidden" id="tituloOferta" name="tituloOferta" value=""/>
		    <input type="hidden" id="nombreCandidato" name="nombreCandidato" value=""/>
		    <input type="hidden" id="fecha" name="fecha" value=""/>
		    <input type="hidden" id="hora" name="hora" value=""/>
		    
            <table class="alertDialog"  border="0" width="100%">
				<tr>
					<td colspan="2" >
						¿Estas seguro que ya no deseas rechazar la entrevista programada?
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;&nbsp;&nbsp;</td>
				</tr>			
				<tr>
					<td align="center" >
					    <input type="button" value="Aceptar" class="boton" onclick="javascript:doSubmit('/entrevistaProgramada.do','cancelarEntrevistaNameForm','ventanaCancelar');">
					</td>
					<td align="center">
						<input type="button" value="Cancelar" class="boton" onclick="closeDialog('ventanaCancelar')">   
					</td>
				</tr>								
		</table>
		 
    </form>                   
</div>			
			
<!-- Aceptar Entrevista -->	

	<div dojoType="dijit.Dialog" id="ventanaAceptar" title="Aceptar entrevista" style="display:none" draggable ="false">
		<form id="aceptarEntrevistaNameForm" name="aceptarEntrevistaNameForm"  
		    method="post"  dojoType="dijit.form.Form">
		    
		    <input type="hidden" name="method" value="aceptar"/>
		    <input type="hidden" name="tipo"   value="<%=tituloTipo%>"/>
		    <input type="hidden" id="idEntrevista" name="idEntrevista" value=""/>
		    <input type="hidden" id="idOfertaEmpleo" name="ofertaEmpleo" value=""/>
		    <input type="hidden" id="correoCandidato" name="correoCandidato" value=""/>
		    <input type="hidden" id="correoEmpresa" name="correoEmpresa" value=""/>		    
		    <input type="hidden" id="nombreEmpresa" name="nombreEmpresa" value=""/>
		    <input type="hidden" id="nombreCandidato" name="nombreCandidato" value=""/>
		    <input type="hidden" id="tituloOferta" name="tituloOferta" value=""/>		    
		    <input type="hidden" id="fecha" name="fecha" value=""/>
		    <input type="hidden" id="hora" name="hora" value=""/>
		    
		  <table class="alertDialog"  border="0" width="100%">
				<tr>
					<td colspan="2" >
						¿Estas seguro que deseas aceptar la entrevista programada?
					</td>
				</tr>
				<tr>
					<td colspan="2" height="30px;">&nbsp;&nbsp;&nbsp;</td>
				</tr>			
				<tr>
					<td align="center">
					    <input type="button" value="Aceptar" class="boton" onclick="javascript:doSubmit('/entrevistaProgramada.do','aceptarEntrevistaNameForm','ventanaAceptar');">
					</td>
					<td align="center">
						<input type="button" value="Cancelar" class="boton" onclick="closeDialog('ventanaAceptar')">   
					</td>
				</tr>					
		</table>		 
     </form>                   
</div>			
