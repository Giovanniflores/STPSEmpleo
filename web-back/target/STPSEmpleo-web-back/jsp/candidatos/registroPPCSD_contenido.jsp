<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.Calendar, mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="MessageResources" var="portalbundle" />

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.FilteringSelect");

	function iniciarRegistroCan() {
		//dojo.byId('method').value = 'redirectRegisterCandidatoRU';
		//dojo.byId('registroCandidatoForm').submit();
		window.open('registro-unico.do?method=redirectRegisterCandidatoRU','_blank');
	}

	function salirRegistro() {
		document.location.href = '<%=response.encodeUrl(request.getContextPath()+"/inicio.do")%>';
	}
	
</script>

<form name="registroCandidatoForm" id="registroCandidatoForm"
	method="post" action="registro-unico.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"
		dojoType="dijit.form.TextBox" />
	<div>
		<div class="formApp">
			<div class="flow_1">Registro de Candidatos</div>
			<div class="form_wrap round_corn">
				<div class="instruc_01">
					<strong>Obtén los beneficios de nuestros usuarios
						registrados</strong>
				</div>
				<div class="beneficios">
					<p>
						<strong>Con tu registro, podrás:</strong>
					</p>
					<ul>
						<li class="li_sne"><div class="img_hold"></div>
							<div class="li_text">Acceder al Portal del Empleo y a todos
								los servicios y programas que ofrece el Servicio Nacional del
								Empleo (SNE).</div></li>
						<li class="li_movil"><div class="img_hold"></div>
							<div class="li_text">Recibir, en tu celular y correo
								electrónico, las ofertas de empleo más recientes.</div></li>
						<li class="li_dom"><div class="img_hold"></div>
							<div class="li_text">Agendar una cita para ser atendido,
								personalmente, en la oficina del SNE más cercana a tu domicilio.</div></li>
						<li class="li_espacio"><div class="img_hold"></div>
							<div class="li_text">Acceder a un espacio virtual y
								administrar tu perfil laboral para que las empresas te vean.</div></li>
						<li class="li_buscar"><div class="img_hold"></div>
							<div class="li_text">Buscar ofertas de empleo por palabra
								clave, perfil laboral o de forma específica.</div></li>
						<li class="li_conocer"><div class="img_hold"></div>
							<div class="li_text">Conocer la compatibilidad entre tu
								perfil y las ofertas de empleo de tu interés.</div></li>
						<li class="li_videocur"><div class="img_hold"></div>
							<div class="li_text">Subir tu video currículum y
								personalizar tu CV con las plantillas que tenemos para ti.</div></li>						
					</ul>
					<div class="clearfix"></div>
					<p class="aviso_1">Este programa es público, ajeno a cualquier
						partido político. Queda prohibido el uso para fines distintos al
						desarrollo social.</p>
				</div>
				<div class="form_nav">
					<input type="button" value="Iniciar Registro"
						onclick="javascript:iniciarRegistroCan()" /> <input type="button"
						value="Salir" onclick="javascript:salirRegistro()" />
				</div>
			</div>
		</div>
	</div>
</form>