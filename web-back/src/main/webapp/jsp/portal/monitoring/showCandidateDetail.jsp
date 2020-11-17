<%@page import="mx.gob.stps.portal.core.candidate.vo.*"%>
<%@page import="mx.gob.stps.portal.core.infra.vo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	long idEstatus = 0;
	OfertaCandidatoResumenVo ofertaCandidatoVo = new OfertaCandidatoResumenVo();
	InformacionGeneralVO detalle = (InformacionGeneralVO) request.getSession().getAttribute("detalle");
	String nombreCompleto = detalle.getNombre() + " " + detalle.getApellido1() + (null != detalle.getApellido2() ? " " + detalle.getApellido2() : "");
	EntrevistaVO entrevista = (EntrevistaVO) request.getSession().getAttribute("interview");
	long idCandidato = (Long) request.getSession().getAttribute("idCandidato");
	int fulfillment = (Integer) request.getSession().getAttribute("fulfillment");
	long idOferta = (Long) request.getSession().getAttribute("idOferta");
	String context = request.getContextPath() + "/";
	String invoke = (null != request.getSession().getAttribute("_urlbackto") ? (String) request.getSession().getAttribute("_urlbackto"): "detalleOfertaCreada.do?method=init&ido=" + idOferta);
	Integer estatusOfertaC = (null != request.getSession().getAttribute("estatusOfertaCandidato") ? (Integer)request.getSession().getAttribute("estatusOfertaCandidato") : 0);
	String estatusOfertaCandidato = estatusOfertaC.toString();
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.DateTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit._Calendar");
	dojo.require("dijit.form.SimpleTextarea");
	dojo.require("dijit.form.NumberTextBox");
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script>
	function validarFechaEntrevista() {

		if(!validarFormaEntrevista()){
			return false;
		}
		
		var dateFin = dijit.byId('fechaEntrevista').get('value');
		var dateHoy = new Date();
		var hora = new Array();
		//hora = dijit.byId('idHoraSelect').get('displayedValue').split(':');
		hora = dijit.byId('hora').get('value').split(':');
		dateFin.setHours(hora[0], hora[1]);
		var dif = dojo.date.difference(dateHoy, dateFin, 'minute');
		if ((dif/60) < 72) {
			message('La fecha de la entrevista debe ser mayor a 72 horas naturales con respecto a la fecha actual');
			dateFin.focus();
			return false;
		}
		//dijit.byId('valorHora').set('value', dijit.byId('idHoraSelect').get('displayedValue'));
		dijit.byId('valorHora').set('value', dijit.byId('hora').get('value'));
		
		return true;
	}
	
	
	function validarFormaEntrevista() {
		if (!dijit.byId('fechaEntrevista').isValid()) {
			message("Favor de proporcionar la fecha de manera correcta.");
			return false;
		}
		if (dijit.byId('hora').get('value') == '' || !dijit.byId('hora').isValid()) {
			message("Favor de ingresar la hora en formato 24Hr.");
			displayErrorMsg(dijit.byId('hora'), dijit.byId('hora').get("invalidMessage"));
			return false;
		}
		return true;
	}

	function doSubmit(urlAction,form,ventana) {
		var vEntrevistaVal = validarFechaEntrevista();
		if (vEntrevistaVal) {		
			//alert('vEntrevistaVal:true');
			//closeDialog(ventana);
			ocultarDatosEntrevista();
			dojo.byId('btnProgramar').disabled=true;	
			dojo.xhrPost(
				{
					url: urlAction,
					form: form,
					timeout:180000, // Tiempo de espera 3 min
					load: function(data) {
						var res = dojo.fromJson(data); // Datos del servidor
						//dojo.byId('msginter').innerHTML=res.message;
						message(res.message);
					}
				}
			);	
		} //else {
			//ocultarDatosEntrevista();
		//} 		
	}

	function closeDialog(ventana) {
        dijit.byId(ventana).hide();
	}

	function mensaje(){
		dijit.byId('confirmacion').show();	
	}

	function asociarCandidatoOferta(idOfertaEmpleo,idCandidato, ventana) {
		dojo.byId('idCandidato').value = idCandidato;
		dojo.byId('idOfertaEmpleo').value = idOfertaEmpleo;
		//dijit.byId(ventana).show();
		setDiv('divEntrevista', true);
	}

	function setDiv(id, visible) {
		var vStyle = visible ? 'visible':'hidden';
		var vBlock = visible ? 'block':'none';
		document.getElementById(id).style.visibility = vStyle;
		document.getElementById(id).style.display = vBlock;
	}
	
	function ocultarDatosEntrevista() {
		setDiv('divEntrevista', false);
	}
	
	function ligarCandidato(urlAction,form) {
		dojo.byId('btnCarpeta').disabled=true;
		dojo.xhrPost(
			{
				url: urlAction,
				form: form,
				timeout:180000, // Tiempo de espera 3 min
				load: function(data) {
					var res = dojo.fromJson(data); // Datos del servidor
					//dojo.byId('msginter').innerHTML=res.message;
					messageFunction(res.message);
				}
			}
		);
	}
	
	function messageFunction(msg){
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p class="form_nav">'
		+ '<input id="btnCerrar" class="boton_naranja" type="button" name="btnCerrar" onclick="window.history.go(-1)" value="Cerrar"/>';
	    + '</p>'
	    +'</div>';
		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false
	        
	    });		
		dialogMsg.show();
	}
	
	function submitWindow() {
		document.viewCandidateInfo.action = '<%=invoke%>';
		document.viewCandidateInfo.submit();
	}

	function getFechaEntrevista() {
		var Hr72 = 3;
		fechaActual = new Date();
		fechaActual.setDate(fechaActual.getDate() + Hr72);
		fechaEntevista = new Date(fechaActual.getFullYear(), fechaActual
				.getMonth(), fechaActual.getDate());

		if (dijit.byId('fechaEntrevista').constraints)
		dijit.byId('fechaEntrevista').constraints.min = fechaEntevista;
	}

	dojo.addOnLoad(function() {
                 
		getFechaEntrevista();
    });  
</script>

<div class="detalle_oferta">
<div class="det_index">


	<h2 class="titulo_profesion">Detalle del candidato</h2>


	<div class="perfil_candidato">
    	<div class="user_pic">
    		<img src="<%=context%>imageAction.do?method=getImagen&ID_CANDIDATO=<%=idCandidato%>" alt="Foto Candidato" height="194" width="194"/>		
    	</div>
    	
		<div class="perfil_c emp">
		<div class="perfil_title">
			<h3>${detalle.nombre} ${detalle.apellido1} ${detalle.apellido2}</h3>
        	<p>Puesto solicitado<br/><strong>${detalle.puestoSolicitado}</strong></p>
        	
        	<c:if test="${detalle.descripcionDiscapacidad!='Ninguna'}">
        		<p>Tipo de discapacidad<br/><strong> ${detalle.descripcionDiscapacidad}</strong></p> 
        	</c:if>	
        </div>	            
            <%if (detalle.getContactoTelefono() < 2) {%>
            <p>Edad<br/><strong>${detalle.edadActual}</strong></p>
            <p>Tel&eacute;fono<br/>
            	<strong> ${detalle.acceso} - ${detalle.clave} - ${detalle.telefono} 
            	<c:if test="${not empty detalle.extension}"> ext. ${detalle.extension}</c:if>
            	</strong>
            </p>
            <%}%>            
            <%if (detalle.getContactoCorreo() < 2) {%>
            <p>Correo electr&oacute;nico<br/><strong>${detalle.correoElectronico}</strong></p>
            <%}%>
            <%if (detalle.getConfidencialidadCandidato() < 2) {%>
            <p>Direcci&oacute;n<br/>
            	<strong>${detalle.nombreEntidad} ${detalle.nombreMunicipio} ${detalle.nombreColonia} 
            	<c:if test="${not empty detalle.codigoPostal}"> c.p. ${detalle.codigoPostal}</c:if>
            	</strong><br/>
            </p>
			<%}%>
			<p>Estatus<br/><strong>${detalle.descEstatus}</strong><br/></p>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="offer_index">


<div id="infoProf" class="offer_section">
	<div id="formacion" class="ficha_relacionada">
		<h3>Formaci&oacute;n profesional</h3>
		<ul>
			<li class="odd">
				<strong>Estudios</strong>
				<br>
				<span>${detalle.carreraEspecialidad}, ${detalle.gradoEstudios}</span>
			</li>
			<c:if test="${not empty detalle.conocimientoComputacionVO}">
				<li class="odd">
					<strong>Conocimientos en computaci&oacute;n:</strong><br>
					<c:if test="${detalle.conocimientoComputacionVO.procesadorTxt==1}">Procesador de textos&nbsp;</c:if>
					<c:if test="${detalle.conocimientoComputacionVO.hojaCalculo==1}">Hojas de c&aacute;lculo&nbsp;</c:if>
					<c:if test="${detalle.conocimientoComputacionVO.internet==1}">Internet y correo electr&oacute;nico&nbsp;</c:if>
					<c:if test="${detalle.conocimientoComputacionVO.redesSociales==1}">Redes sociales&nbsp;</c:if>
					<c:if test="${not empty detalle.conocimientoComputacionVO.otros}">${detalle.conocimientoComputacionVO.otros}</c:if>
				</li>
			</c:if>


			<li>
				<strong>Conocimientos:</strong><br>
				<c:if test="${not empty detalle.conocimientos}">
					<c:forEach var="rowKnowList" items="${detalle.conocimientos}">
						<br><c:out value="${rowKnowList.conocimientoHabilidad}"/>			
					</c:forEach>
				</c:if>
			</li>
		</ul>
	</div>
	<div id="experiencia" class="offer_section">
		<h3>Experiencia relevante</h3>
		<ul>
			<li>
				<strong>A&ntilde;os de experiencia:</strong>
				<br>
				${detalle.aniosExperencia}
			</li>
			<li>
				<strong>Experiencia:</strong>
				<br>
				${detalle.experiencia}
				<c:if test="${not empty listaEstandares}">
					<c:forEach var="rowListaEstandares" items="${listaEstandares}">
						<p><c:out value="${rowListaEstandares.titulo}"/></p>				
					</c:forEach>
				</c:if>	
			</li>
		</ul>
	</div>
	<div id="expectativas" class="offer_section">
		<h3>Expectativas laborales</h3>
		<ul>
			<li>
				<strong>Salario mensual pretendido:</strong>
				<br>${detalle.salarioPretendido}
			</li>
			<li>
				<strong>Disponibilidad para viajar:</strong>
				<br>
				<c:if test="${detalle.disponibilidadViajar==1}">No</c:if>
				<c:if test="${detalle.disponibilidadViajar!=1}">S&iacute;</c:if>
			</li>
			<li>
				<strong>Disponibilidad para radicar fuera:</strong>
				<br>
				<c:if test="${detalle.disponibilidadRadicar==1}">No</c:if>
				<c:if test="${detalle.disponibilidadRadicar!=1}">S&iacute;</c:if>
			</li>
		</ul>
	</div>
	<div class="pdf_dwn">
		<a class="consultar_mas_vacantes" href="<c:url value="/miscandidatos.do?method=genera&idCandidato=<%=idCandidato%>"/>">Descargar CV completo en PDF</a>
	</div>   
	<div>
		<c:if test="${detalle.empresaConfidencial==true}">
			<div>
				<p>
					<a href="OfertaNavegacion.do?method=init&amp;idCandidato=<%=idCandidato%>" class="consultar_mas_vacantes">Agregar a mis candidatos seleccionados</a>
				</p>
			</div>
		</c:if>
		<div>
			<%
				String urlRegresar = "#";
				if (null != request.getSession().getAttribute("_urlpageinvoke")) {
					urlRegresar = (String)request.getSession().getAttribute("_urlpageinvoke");
				}
			%>
			<p>
				<a href="javascript:window.history.go(-1)" class="">Regresar</a>
			</p>
		</div>
	</div>
</div>
	</div>



	<!-- MONITORING  --> 
	<div class="ficha_oferta">

	<div id="botones" class="entero">
		<p>&nbsp;</p>
		<form id="viewCandidateInfo" name="viewCandidateInfo" method="post" dojoType="dijit.form.Form" >
			<input type="hidden" name="method" value="asociarCandidatoOferta" />
			<input type="hidden" id="idCandidato" name="idCandidato" value="<%=idCandidato%>" />
			<input type="hidden" id="idOfertaEmpleo" name="idOfertaEmpleo" value="<%=idOferta%>" />
			
			<%-- <input id="btnRegresar" type="button" class="boton" value="Regresar a los resultados" onclick="javascript:submitWindow()" /> --%>
			<%
				if (estatusOfertaCandidato.equals("0")) {
			%>
			 		<input id="btnCarpeta" type="button" class="boton" value="Agregar a mi carpeta empresarial" onclick="javascript:ligarCandidato('/viewCandidateDetail.do','viewCandidateInfo');" />
			<%
				}
				if (!estatusOfertaCandidato.equals("20")) {
			%>
				<input id="btnProgramar" type="button" class="boton" value="Programar entrevista en l&iacute;nea" onclick="javascript:asociarCandidatoOferta('<%=idOferta%>','<%=idCandidato%>', 'ventanaProgramar')" />				
			<%		
				}
			%>				
		</form>
	</div>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<!-- INICIO DE PROGRAMAR ENTREVISTA -->
	<div class="entero" id="divEntrevista" style="display:none; visibility:hidden; position:relative; left:0px;">
		<form id="programarEntrevistaForm" name="programarEntrevistaForm" method="post" dojoType="dijit.form.Form">
			<input type="hidden" name="method" value="programarEntrevista" />
			<input type="hidden" id="idCandidato" name="idCandidato" value="<%=idCandidato%>" />
			<input type="hidden" id="idOfertaEmpleo" name="idOfertaEmpleo" value="<%=idOferta%>" />
			<input type="hidden" id="empresa" name="empresa" value="${AdmonCandidatosForm.ofertaDetalle.empresa}" />
			<input type="hidden" id="tituloOferta" name="tituloOferta" value="${AdmonCandidatosForm.ofertaDetalle.tituloOferta}" />
			<input type="hidden" id="nombre" name="nombre" value="<%=nombreCompleto%>" />
			<input type="hidden" id="email" name="email" value="${detalle.correoElectronico}" />
			<table>
				<tr>
					<td>
						<h3>
							<%=detalle.getNombre()%>
							<%=detalle.getApellido1()%>
							<%=detalle.getApellido2()%>
						</h3>
					</td>
				</tr>
				
				<%if (null != entrevista.getFechaString() && !entrevista.getFechaString().equalsIgnoreCase("")) {%>
					<tr>
						<td colspan="2">Ya ha sido programada una entrevista con el
							candidato para el d&iacute;a <%=entrevista.getFechaString()%> y
							hora <%=entrevista.getHora()%>.</td>
					</tr>
				<%}else {%>
					<tr>
						<td>
							<strong>Fecha:</strong><br/>
							<input type="text" name="fechaEntrevista" id="fechaEntrevista" dojoType="dijit.form.DateTextBox" required="true" size="10"
						   		constraints="{datePattern:'dd/MM/yyyy'}" style="position:relative; left:0px;"/><br/>
							<strong>Hora: <i>formato de 24 hrs, ejemplo (13:30)</i></strong><br>
							<input type="hidden" name="valorHora" id="valorHora" dojoType="dijit.form.TextBox"/>
							<input id="hora" name="hora" dojoType="dijit.form.ValidationTextBox" required="false" size="5" regExp="^(0[1-9]|1\d|2[0-3]):([0-5]\d)$"
						   		invalidMessage="Hora inv&aacute;lida, debe indicarse en formato de 24hrs, ejemplos 09:00 o 15:30"
						   		uppercase="true" size="5" maxlength="5" value="" style="position:relative; left:0px;" trim="true"/><br><br>
							<input type="button" value="Enviar invitaci&oacute;n" class="boton" id="botonEnviar"
						   		onclick="javascript:doSubmit('/viewCandidateDetail.do','programarEntrevistaForm','ventanaProgramar');">
							<input type="button" value="Cancelar" class="boton" onclick="ocultarDatosEntrevista()">
						</td>
					</tr>
				<%}%>
				<tr>
					<td colspan="2" align="right">
						<img src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	</div>
</div>
<div class="porcentaje_c">
	<% if (idOferta > 0) {%>
    	<h3><span>${detalle.nombre} ${detalle.apellido1} ${detalle.apellido2}</span></h3>
    	<div class="porcentaje_c1">
    	<em>El porcentaje de compatibilidad<br> con esta oferta de empleo es del</em>
        <strong><%=fulfillment%> %</strong>
        
	<%}%>
		</div>
		<div class="porcent_prompt">
			<a href="detalleoferta.do?method=init&id_oferta_empleo=<%=idOferta%>">${AdmonCandidatosForm.ofertaDetalle.tituloOferta}</a>
		</div>
		
	<div class="videoCV_c">
		<h3>Video Curr&iacute;culum</h3>
		<c:choose>
			<c:when test="${detalle.urlVideoEstatus == 1}">
				<a href="${detalle.urlVideoCV}" target="_blank">${detalle.urlVideoCV}</a><br>
			</c:when>
			<c:otherwise>
				<div style="text-align: center; margin-top: 50px">No disponible </div>
			</c:otherwise>
		</c:choose>
		<c:if test="${detalle.urlVideoEstatus == 1}">
			<iframe width="280" height="200" src="${decoratedVideo}" frameborder="0" allowfullscreen></iframe>
		</c:if>
	</div>
		
		
	</div>
	
<div class="clearfix"></div>
</div>