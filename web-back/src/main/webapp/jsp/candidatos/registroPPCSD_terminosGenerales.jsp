<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function goToHome(){
		window.open("${DOMAIN_PORTAL}", "_self");
	}

	function registraPPC(){
		dojo.byId('method').value='toRegistroPPC';
		dojo.byId('terminosGeneralesform').action='registro.do';
		dojo.byId('terminosGeneralesform').submit();
	}

</script>

<div class="formApp">
    <div class="flow_1">T&eacute;rminos generales del Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo (PPC-SD)</div>
    <div class="form_wrap">
        <div class="instruc_01"><h2>Requisitos que el desempleado debe cumplir para acceder al beneficio del Programa</h2>
        <p>Para recibir los beneficios del Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo (PPC-SD), los interesados deber&aacute;n cumplir con los siguientes requisitos:</p></div>
        <div class="requisitos_PPC-SD">
            <ol>
                <li>Cotizaciones:
                    <ul>
                        <li>Los trabajadores deber&aacute;n contar con por lo menos <strong>104 cotizaciones semanales en un periodo no mayor a 36 meses o</strong></li>
                        <li>Los trabajadores temporales y/o eventuales deber&aacute;n contar con al menos <strong>26 semanas de cotizaciones en un periodo no mayor a 12 meses;</strong></li>
                    </ul>
                </li>
                <li>Haber permanecido desempleado al menos 45 d&iacute;as naturales consecutivos;</li>
                <li>No percibir otros ingresos econ&oacute;micos por concepto de jubilaci&oacute;n o pensi&oacute;n;</li>
                <li><strong>Acreditar el cumplimiento de los requisitos comprendidos en el Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de desempleo a cargo de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, y</strong></li>
                <li>Declarar, bajo protesta de decir verdad, que no realiza por cuenta propia alguna actividad que le genere ingresos.</li>
            </ol>
            <p>Cuando consideres que ya cumples todos los requisitos, deber&aacute;s acudir a la Afore en la que tengas tu cuenta individual y ah&iacute; solicitar el certificado de cumplimiento y el pago del Seguro de Desempleo.</p>
            <p>Si deseas inscribirte al Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo, debes registrar, v&iacute;a Portal del Empleo o en las oficinas del Servicio Nacional de Empleo:</p>
            <ol>
                <li>Tu Clave &uacute;nica de Registro de Poblaci&oacute;n (CURP),</li>
                <li>Tu N&uacute;mero de Seguridad Social (NSS)</li>
            </ol>
            <p class="ta_center"><strong>Si tus datos son v&aacute;lidos, podr&aacute;s inscribirte.</strong></p>
        </div>
        
		<form name="terminosGeneralesform" id="terminosGeneralesform" method="post" action="registroCandidatoPPCSD.do" dojoType="dijit.form.Form">        
        
	        <div class="form_nav">
	        <c:choose>
				<c:when test="${not empty registroCandidatoPPCSDForm}">
				 	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>
       
		            <input type="submit" value="Inscr&iacute;bete" onclick="window.open('${pageContext.request.contextPath}/registroCandidatoPPCSD.do?method=init', '_self');"/>
		            <input type="button" value="Salir"  onclick="window.open('${pageContext.request.contextPath}/miEspacioCandidato.do?method=init', '_self');"/>
				</c:when>
				<c:when test="${not empty registroCandidatoForm and empty registroCandidatoPPCSDForm and registroCandidatoForm.registroPPC}">
				 	<input type="hidden" name="method" id="method" value="toRegistroPPC" dojoType="dijit.form.TextBox"/>
       
		            <input type="submit" value="Inscr&iacute;bete" onclick="registraPPC();"/>
		            <input type="button" value="Salir"  onclick="window.open('${DOMAIN_PORTAL}', '_self');"/>
				</c:when>
			</c:choose>
	        </div>
	        
        </form>
        
    </div> 
</div>

