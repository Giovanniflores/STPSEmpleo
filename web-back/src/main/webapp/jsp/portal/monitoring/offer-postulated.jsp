<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer

	function mensaje(mensaje){
		alert(mensaje);		
	}
	
	function redireccionar(home){
			 window.top.location.href=home;
	}

	function cancelaPostulacion(){
		document.opost.method.value='regresaDetalle';
		document.opost.submit();
	}

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<c:if test="${not empty errors}">
<script type="text/javascript">
	dojo.addOnLoad(function(){
		mensaje('${errors}');
	});
</script>

</c:if>

<c:if test="${not empty OfferQuestionForm}">
<c:if test="${not empty OfferQuestionForm.perfilJB}">

<c:set var="perfilJB" value="${OfferQuestionForm.perfilJB}"/>
<c:set var="employ" value="${OfferQuestionForm.ofertaJB}"/>
<c:set var="tel" value="${perfilJB.principal}"/>

<div class="postulado">

	<h3><span>Postularse para </span>${employ.tituloOferta}</h3>
    <div class="ruta_pop">
		<a href="#">${employ.empresaNombre}</a>
		<a href="#">${employ.ubicacion}</a>
		<a href="#">$${employ.salario}</a>
		<span>${employ.fuente}</span>
    </div>

	<div>
		<p>
			Solicitamos ${employ.tituloOferta} ${employ.gradoEstudios} ${employ.especialidades} ${employ.funciones},
			<c:if test="${employ.edadRequisito == 2}">
			Edad ${employ.edadMinima} - ${employ.edadMaxima} años,
			</c:if>
			${employ.idiomas} ${employ.tipoEmpleo}, Número de plazas: ${employ.numeroPlazas}, ${employ.medioContacto}
		</p>
		<div id="datos_capturaNombre">
			<p><strong>Nombre:</strong><span>${perfilJB.nombre}</span></p>
			<p><strong>Primer apellido:</strong><span>${perfilJB.apellido1}</span></p>
			<p><strong>Segundo apellido:</strong><span>${perfilJB.apellido2}</span></p>
		</div>

		<div class="campo_pop derecha3">
			<div class="mail">
            	<p><strong>Correo electrónico:</strong><span>${perfilJB.correoElectronico}</span></p>
			</div>
			
			<div id="tel">
				<c:if test="${not empty tel}">
					<p><strong>Tel&eacute;fonos:</strong></p>
					
					<c:if test="${tel.idTipoTelefono == 5}">
					<p><strong>Fijo:</strong><span>${tel.telefono}</span></p>
					</c:if>
	
					<c:if test="${tel.idTipoTelefono == 1}">
					<p><strong>Celular:</strong><span>${tel.telefono}</span></p>
					</c:if>
					
					<c:if test="${not empty tel.telefono}">
					<p><strong>Hora de llamar:</strong><span>${perfilJB.horarioLlamar}</span></p>
					</c:if>
				</c:if>
			
				<div id="dirPostular">
					<p><strong>Direcci&oacute;n:</strong></p>
					<p><strong>Entidad federativa:</strong><span>${perfilJB.entidad}</span></p>
					<p><strong>Delegaci&oacute;n/ municipio:</strong><span>${perfilJB.municipio}</span></p>
					<p><strong>Calle y n&uacute;mero:</strong><span>${perfilJB.calle} ${perfilJB.numeroExterior} ${perfilJB.numeroInterior}</span></p>
				</div>
				<p>
					<strong>Si deseas modificar alg&uacute;n dato de los presentados en esta pantalla, ingresa a la secci&oacute;n Actualizar mis datos</strong>
				</p>
			</div>
        </div>
                                	
		<form name="opost" id="opost" method="post" action="detalleoferta.do">
			<input type="hidden" name="method" id="method" value="offerPost"/>
			<p class="enviarDatos">
				<input type="submit" value="Enviar mis datos" class="boton"
				       onclick="javascript:if(confirm('¿Está seguro que desea enviar sus datos para postularse a la oferta?')) return true; else return false;"/>
				<input type="button" id="cancelPost" value="Cancelar" class="boton" onclick="javascript:cancelaPostulacion();"/>
			</p>
		</form>

	</div>

</div>

</c:if>
</c:if>