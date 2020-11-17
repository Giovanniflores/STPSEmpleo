<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Textarea");

	function cancelarValidacion(idRegValidar){
		  dojo.byId('method').value='init';
		  dojo.byId('autorizationForm').submit();	  
	}
	
	function showWindowMotivoRechazo(idRegValidar){

		try{
			if (dialogRechazo){
				  dijit.byId('idMotivoRechazoSelect').attr('value', null);
				  dojo.style(dialogRechazo.closeButtonNode,"display","none");
				  dialogRechazo._relativePosition = { x: 338, y:200 };				  
				  dialogRechazo.show();
			}
		} catch(err){
			  dialogRechazo = new dijit.Dialog({
			        title: 'Motivo de Rechazo',
			        href: '${context}autorization.do?method=motivoRechazoDetalle',
			        style: "width:280px; height:330px;",
			        showTitle: false, draggable : true, closable : false
			  });
			  
			  dojo.style(dialogRechazo.closeButtonNode,"display","none");
			  dialogRechazo._relativePosition = { x: 338, y:200 };			  
			  dialogRechazo.show();
		}
	
	}

	function closeWindowRechazo(){
		  dialogRechazo.hide();
	}
	
	function autorizarRegistro(idRegValidar){
		  dojo.byId('idRegValidar').value=idRegValidar;	  
		  dojo.byId('method').value='autorizarRegistro';
		  dojo.byId('autorizationForm').submit();	  
	}	

	  function rechazarRegistro(idRegValidar){

		if(!dijit.byId('idMotivoRechazoSelect').isValid()){
			dijit.byId('idMotivoRechazoSelect').focus();
			alert("El siguiente campo muestra datos inválidos: Motivo. ");								
			return false;
		}			
		  
		if (dijit.byId('idMotivoRechazoSelect').get('item') && dijit.byId('idMotivoRechazoSelect').get('item').label){
			dojo.byId('idMotivoRechazo').value = dijit.byId('idMotivoRechazoSelect').get('item').label[0];
		}

		if(dijit.byId('motivoRechazoText').value.length==0){		
			alert("El siguiente campo muestra datos inválidos: Detalle. ");								
			return false;
		}		

		document.autorizationForm.idMotivoRechazo.value = dojo.byId('idMotivoRechazo').value;
		document.autorizationForm.motivoRechazo.value   = dojo.byId('motivoRechazoText').value;
		
		closeWindowRechazo();

        dojo.byId('idRegValidar').value=idRegValidar;	  
		dojo.byId('method').value='rechazarRegistro';
		dojo.byId('autorizationForm').submit();			
	  }

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div>
		<table>
			<tr>
				<td colspan="4" style="font-size: 13px; font-weight:bold;">Empresa</td>
			</tr>
			<tr>
				<td>ID del Portal del Empleo</td>
				<td colspan="3" ><strong>${autorizationForm.empresa.idPortalEmpleo}</strong></td>
			</tr>
			<tr>
				<td>RFC</td>
				<td><strong>${autorizationForm.empresa.rfc}</strong></td>
				<td>Tipo de persona</td>
				<td><strong>${autorizationForm.empresa.tipoPersona}</strong></td>
			</tr>
			<tr>
				<td>Nombre(s)</td>
				<td><strong>${autorizationForm.empresa.nombre}</strong></td>
				<td>Primer apellido</td>
				<td><strong>${autorizationForm.empresa.apellido1}</strong></td>
			</tr>
			<tr>
				<td>Segundo apellido</td>
				<td><strong>${autorizationForm.empresa.apellido2}</strong></td>
				<td>Fecha de nacimiento</td>
				<td><strong>${autorizationForm.empFechaNacimiento}</strong></td>
			</tr>
			<tr>
				<td>Razón social</td>
				<td><strong>${autorizationForm.empresa.razonSocial}</strong></td>
				<td>Fecha de acta constitutiva</td>
				<td><strong>${autorizationForm.empFechaAlta}</strong></td>
			</tr>
			<tr>
				<td>Nombre Comercial</td>
				<td colspan="3" ><strong>${autorizationForm.empresa.nombreComercial}</strong></td>				
			</tr>
			<tr>
				<td>Descripción de la empresa</td>
				<td><strong>${autorizationForm.empresa.descripcion}</strong></td>
				<td>Nombre de la persona de contacto con la empresa</td>
				<td><strong>${autorizationForm.empresa.contactoEmpresa}</strong></td>
			</tr>
			<tr>
				<td>Tipo de empresa</td>
				<td><strong>${autorizationForm.empresa.tipoEmpresa}</strong></td>
				<td>Actividad económica principal</td>
				<td><strong>${autorizationForm.empresa.actividadEconomica}</strong></td>
			</tr>
			<tr>
				<td>Número de empleados</td>
				<td colspan="3"><strong>${autorizationForm.empresa.numeroEmpleados}</strong></td>
			</tr>
			<tr><td colspan="4">&nbsp;</td></tr>
			<tr>
				<td colspan="4" style="font-size: 13px; font-weight:bold;">Domicilio</td>
			</tr>
			<tr>
				<td colspan="4" >
					<table style="width: 100%">
						<tr>
							<td>Código postal</td>
							<td><strong>${autorizationForm.empresa.domicilio.codigoPostal}</strong></td>
							<td>País</td>
							<td><strong>México</strong></td>
							<td>Entidad federativa</td>
							<td><strong>${autorizationForm.empresa.domicilio.entidad}</strong></td>
						</tr>
						<tr>
							<td>Municipio</td>
							<td><strong>${autorizationForm.empresa.domicilio.municipio}</strong></td>
							<td>Colonia</td>
							<td><strong>${autorizationForm.empresa.domicilio.colonia}</strong></td>
							<td>Calle</td>
							<td><strong>${autorizationForm.empresa.domicilio.calle}</strong></td>
						</tr>
						<tr>
							<td>Número exterior</td>
							<td><strong>${autorizationForm.empresa.domicilio.numeroExterior}</strong></td>
							<td>Número interior</td>
							<td><strong>${autorizationForm.empresa.domicilio.numeroInterior}</strong></td>
							<td>Entre calles</td>
							<td><strong>${autorizationForm.empresa.domicilio.entreCalle}</strong></td>
						</tr>
						<tr>
							<td>Y</td>
							<td colspan="5" ><strong>${autorizationForm.empresa.domicilio.yCalle}</strong></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td colspan="4">&nbsp;</td></tr>
			<tr>
				<td colspan="4" style="font-size: 13px; font-weight:bold;">Datos de identificación de la cuenta</td>
			</tr>
			<tr>
				<td colspan="4">
					<table style="width: 100%">
						<tr>
							<td>Correo electrónico</td>
							<td colspan="5"><strong>${autorizationForm.empresa.correoElectronico}</strong></td>
						</tr>
						<tr>
							<td>Tipo de teléfono</td>
							<td><strong>${autorizationForm.telefonoVO.tipoTelefono}</strong></td>
							<td>Acceso</td>
							<td><strong>${autorizationForm.telefonoVO.acceso}</strong></td>
							<td>Clave Lada</td>
							<td><strong>${autorizationForm.telefonoVO.clave}</strong></td>
						</tr>
						<tr>
							<td>Teléfono</td>
							<td><strong>${autorizationForm.telefonoVO.telefono}</strong></td>
							<td>Extensión</td>
							<td colspan="3"><strong>${autorizationForm.telefonoVO.extension}</strong></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

        <c:if test="${not empty autorizationForm.empresasFraudulentas}">
		<table>
		    <c:forEach var="empfrau" items="${autorizationForm.empresasFraudulentas}" varStatus="rowCounter">
			<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
		        <td>
					La empresa <c:out value="${empfrau.empresa}"/> identificada como fraudulenta 
					coincide en el "<c:out value="${empfrau.coincidencias}"/>" con la empresa que estás validando.
		        	<a class="btn_portal" href="javascript:showWindowEmpFraudulenta(${empfrau.idEmpresa})">
		        		Ver detalle de la empresa
		        	</a>
		        </td>
		    </tr>
		    </c:forEach>
	    </table>
	    </c:if>

		<c:if test="${empty OFERTA_DETALLE_EMP}">
		<p style="text-align:center">
        	<input type="button" value="Modificar empresa" class="boton" onclick="javascript:editarEmpresa(${autorizationForm.idRegValidar}, ${autorizationForm.empresa.idEmpresa});"/>
        	<input type="button" value="Autorizar registro" class="boton" onclick="javascript:autorizarRegistro(${autorizationForm.idRegValidar});"/>
        	<input type="button" value="Eliminar registro" class="boton" onclick="javascript:showWindowMotivoRechazo(${autorizationForm.idRegValidar});"/>
        	<input type="button" value="Cancelar validación" class="boton" onclick="javascript:cancelarValidacion(${autorizationForm.idRegValidar});"/>
        </p>
		</c:if>
		<c:if test="${not empty OFERTA_DETALLE_EMP}">
		<p style="text-align:center">
        	<input type="button" value="Cerrar" class="boton" onclick="javascript:closeDetalleEmpresa();"/>
        </p>
		</c:if>
		
<form name="autorizationForm" id="autorizationForm" method="post" action="autorization.do">
	<input type="hidden" name="method" id="method" value="init"/>
	<input type="hidden" name="idRegValidar" id="idRegValidar" value="0"/>
	<input type="hidden" name="idEmpresa" id="idEmpresa" value="0"/>
	<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="0"/>
	<input type="hidden" name="idMotivoRechazo" id="idMotivoRechazo" value="0"/>
	<input type="hidden" name="motivoRechazo" id="motivoRechazo" value=""/>	
</form>		

</div>