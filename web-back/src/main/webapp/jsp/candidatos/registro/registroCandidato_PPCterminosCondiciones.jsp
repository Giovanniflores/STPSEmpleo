<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");

	function registraPortalyPPC(){
		
		if (!opcionSeleccionada()){
			showDatosFaltantes();
			return;
		}
		
		doSubmit("registraPortalyPPC");
	}
	
	function actualizarPortalyPPC(){
		
		if (!opcionSeleccionada()){
			showDatosFaltantes();
			return false;
		}
		else
		{
			//Javascript es asyncrone entonces si no quieres que execute ponle en un if
			doSubmit("actualizarPortalyPPC");
		}
	}
	
	function opcionSeleccionada(){
		
		if (document.getElementById('aceptacionTerminosSi').checked)
			return true;
		
		if (document.getElementById('aceptacionTerminosNo').checked)
			return true;
		
		return false;
	}	

	function showDatosFaltantes(){
		dijit.byId("msgDatosFaltantes").show();		
	}

	function doSubmit(method){
		dojo.byId('form').submit();
	}	
	
</script>

<form class="formApp" name="form" id="form" method="post" action="registro.do" dojoType="dijit.form.Form">
    <div class="flow_1">Inscripci&oacute;n al Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo (PPC-SD)</div>
    <div class="form_wrap terminos_ppc">

        <div class="form_edge">
            <div class="stepApp">
                <p>Los datos marcados con <span>*</span> son obligatorios</p>
            </div>
            <div class="user_dt">
	            <h3>T&eacute;rminos y condiciones al PPC-SD</h3>
            </div>
            <div class="condiciones_1 tip_b"><span class="tip_1"></span>
            <div class="terminos_1 margin_top_10">
				<h4>Proceso de inscripci&oacute;n:</h4>
				<p>A. Aceptar las condiciones de participaci&oacute;n</p>
				<p>Requisitos del PPC son:</p>
				<p> 1. Facilitar al SNE la informaci&oacute;n y documentaci&oacute;n que te sea requerida.</p>
				<p> 2. Hacer uso de la informaci&oacute;n y apoyos que te otorga el SNE para la b&uacute;squeda e identificaci&oacute;n de plazas vacantes a las que podr&iacute;as aplicar.</p>
				<p> 3. Aplicar o postularte a las vacantes que muestren un porcentaje de empat&iacute;a igual o mayor a 70% con tu perfil</p>
				<p> 4. Realizar b&uacute;squeda activa de empleo.</p>
				<p> 5. En los casos en que tambi&eacute;n te postules a otras vacantes no registradas en el SNE directamente (OCC, MANPOWER u otras) o utilices otros medios para la b&uacute;squeda de empleo, deber&aacute;s informarlo, en un plazo no mayor a 24 horas (un d&iacute;a h&aacute;bil), mediante cualquiera de las siguientes opciones:</p>
				<p>    &#10003;Sitio Web en el Portal del Empleo www.empleo.gob.mx</p>
				<p>    &#10003;Consejero Laboral, el que, en su caso, te haya asignado el SNE.</p>
				<p>    &#10003;Tel&eacute;fono (SNEtel al 01-800-841-2020)</p>
				<p>    &#10003;Correo electr&oacute;nico.</p>
				<p>    As&iacute; como el seguimiento y resultado de tu postulaci&oacute;n.</p>
				<p> 6. Asistir puntualmente a tus entrevistas de trabajo concertadas y reportar de los resultados, por los medios referidos en el punto anterior.</p>
				<p> 7. Atender las llamadas, correos y/o requerimientos que el SNE te haga, para valorar tu desempe&ntilde;o en el proceso de b&uacute;squeda de empleo.</p>
				<p> 8. Si no ubicas vacantes o &eacute;stas tienen un nivel de empat&iacute;a con tu perfil inferior al 70%, buscar asistencia de un consejero en l&iacute;nea o en las oficinas del SNE para revisar y, en su caso, adecuar tu perfil.</p>
				<p> 9. De ser el caso, asistir a consejer&iacute;a laboral especializada, a talleres de b&uacute;squeda de empleo o curso de capacitaci&oacute;n que el SNE te ofrezca.</p>
				<p>10. En el momento en que est&eacute; confirmada tu contrataci&oacute;n en un puesto de trabajo, deber&aacute;s reportarlo, se&ntilde;alando en qu&eacute; vacante, ya sea que haya sido concertada con el sistema del SNE o por otros medios; utilizando las opciones referidas en el punto 6 de esta actividad.</p>
				<p></p>
				<p>NOTA: Cualquier omisi&oacute;n a las presentes condiciones se considerara como incumplimiento del Programa y por tanto del requisito para acceder al beneficio del Seguro de Desempleo.</p>
            </div>
            
            <div class="labeled"><span>*</span> Acepto los t&eacute;rminos y condiciones para el PPC-SD</div>
            <div>

		    	<c:choose>
		    		<c:when test="${registroCandidatoForm.edicionPerfilLaboral}">
						<input type="hidden" name="method" id="method" value="actualizarPortalyPPC"/>	
					</c:when>	
					<c:otherwise>
						<input type="hidden" name="method" id="method" value="registrarPortalyPPC"/>
		    		</c:otherwise>
		    	</c:choose>
					                    
                <input type="radio" id="aceptacionTerminosSi" name="aceptacionTerminos" value="${requestScope.aceptaTerminosSi.idOpcion}">
                <label for="aceptacionTerminosSi">
                   	<c:out value="${requestScope.aceptaTerminosSi.opcion}"/>
                </label>

                <br/>
                <input type="radio" id="aceptacionTerminosNo" name="aceptacionTerminos" value="${requestScope.aceptaTerminosNo.idOpcion}">
                <label for="aceptacionTerminosNo">
                   	<c:out value="${requestScope.aceptaTerminosNo.opcion}"/>
                 </label>
                    
            </div>
            
            </div>
        </div> 
    </div>
    <div class="form_nav">						

	    <c:choose>
	    	<c:when test="${registroCandidatoForm.edicionPerfilLaboral}">
	    		<input type="button" value="Finalizar" onclick="actualizarPortalyPPC();"/>		    	
	    	</c:when>
	    	<c:otherwise>
	    		<input type="button" value="Finalizar" onclick="registraPortalyPPC();"/>	
	    	</c:otherwise>
	    	

	    </c:choose>
	    <input type="button" value="Cancelar" onclick="dijit.byId('msgCancelar').show();">	    

    </div>
</form>

<div dojoType="dijit.Dialog" id="msgDatosFaltantes" title="Aviso" draggable ="false" style="display: none;">
	<div class="msg_contain">
		<p>Es necesario seleccionar una respuesta.</p>
	</div>	
	<p class="form_nav">
		<button onclick="dijit.byId('msgDatosFaltantes').hide();">Cerrar</button>
	</p>
</div>

<div dojoType="dijit.Dialog" id="msgCancelar" title="Aviso" style="display:none" draggable="false">
	<div class="msg_contain">
		<p>Los cambios no se guardar&aacute;n</p>		
	</div>
	<p class="form_nav">	
		<button class="button" onclick="window.open('${pageContext.request.contextPath}/logout.do', '_self');">Aceptar</button>
	</p>
</div>
