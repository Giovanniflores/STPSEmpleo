<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Kioscos de consulta</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute> 
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		¡Consulta las ofertas del Portal del Empleo en los kioscos de información!
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp"/>">Programas y servicios de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/vinculacionLaboral.jsp"/>">Vinculación laboral</a></li>
          <li class="active">Kioscos de consulta del Portal del Empleo</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp"/>
      
      <div class="col-sm-8 col-sm-pull-4">
      	 
      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
        <div class="clearfix"></div>
		
		 <div class="panel">
          <div class="panel-body">
			<h2 class="titulosh2">Kioscos de consulta del Portal del Empleo</h2>
		
			<p><strong>¡Consulta las ofertas del Portal del Empleo en los kioscos de información!</strong></p>
	        <p><strong>¿Qué son los kioscos de información sobre ofertas de empleo?</strong></p>
	        <p>Son módulos informáticos de autoservicio con acceso a Internet que facilitan la consulta inmediata de las vacantes que se publican en el Portal de Empleo. Estos kioscos son de fácil operación, ya que están equipados con un monitor que permite consultar la información por medio de una pantalla táctil.</p>
<p>A través de estos módulos las personas tienen acceso en forma gratuita a los siguientes servicios:</p>
<ul class="list-group-contenidos">
    <li>Búsqueda, consulta e impresión de las ofertas de trabajo que se publican en el Portal del Empleo</li>
    <li>Inscripción a listas de correo electrónico para recibir notificaciones sobre ofertas de empleo.</li>
</ul>

<p>Para tener acceso a los kioscos de consulta del Portal del Empleo se requiere que la persona interesada acuda personalmente a donde se dispone de este servicio. Los servicios son enteramente gratuitos.</p>
<p>Se cuenta con 16 kioscos ubicados en los siguientes domicilios:</p>
				
				<div class="page-header">
		        <h4><small></small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading">Aguascalientes</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Av. Aguascalientes No.3214, <abbr title="Colonia">Col. </abbr>Prado Sur, <abbr title="C&oacute;digo Postal">C.P.</abbr> 20280, Aguascalientes, Ags.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 15:30 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
						
			<div class="panel panel-default">
				<div class="panel-heading">Baja California Sur</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Delegaci&oacute;n Cabo San Lucas (Delegaci&oacute;n Municipal), Carretera a Todos Santos Km. 2, <abbr title="Colonia">Col. </abbr>Ejidal, Cabo San Lucas, B.C.S.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading">Chiapas</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Presidencia Municipal de Tapachula, Quinta Poniente entre Sexta y Octava Norte, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 30700, Tapachula, Chis.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Palacio de Gobierno<br />
        Av. Central y 2 Oriente s/n <abbr title="Colonia">Col. </abbr>Centro,   <abbr title="C&oacute;digo Postal">C.P. </abbr>29000,<br />
        Tuxtla Guti&eacute;rrez, Chis.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading">Chihuahua</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Edificio H&eacute;roes de la Revoluci&oacute;n. Av. Venustiano Carranza 803, P.B., <abbr title="Colonia">Col. </abbr>Obrera, <abbr title="C&oacute;digo Postal">C.P.</abbr> 31350, Chihuahua, Chih.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading">Jalisco</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Instituto Jalisciense de las Mujeres <br />
        Miguel Blanco No. 883, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 44100, Guadalajara, Jal.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 16:30 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading">Michoac&aacute;n</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Madero No. 2, Jacona int. 3-B, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 59800, Jacona, Mich.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 18:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Presidencia Municipal<br />
        Mariano Jim&eacute;nez s/n , <abbr title="Colonia">Col. </abbr>Centro,    <abbr title="C&oacute;digo Postal">C.P.</abbr> 59300, La Piedad de Cavadas, Mich.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 18:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Ciudad Universitaria (CU) Edif. B Av. Francisco J. M&uacute;jica s/n,<br />
							    <abbr title="Colonia">Col. </abbr>Felicitas del R&iacute;o, Morelia, Mich.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 18:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Presidencia Municipal<br />
        Av. Chiapas No. 514, <abbr title="Colonia">Col. </abbr>Ram&oacute;n Far&iacute;as, <abbr title="C&oacute;digo Postal">C.P.</abbr> 61900, Uruapan, Mich.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 18:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading">Nayarit</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Calzada del Ej&eacute;rcito No. 311, P.B. <abbr title="C&oacute;digo Postal">C.P.</abbr> 63135, Tepic, Nay.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 Servicio las 24 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading">Puebla</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Calle 7 Norte No. 9, <abbr title="Colonia">Col. </abbr>Centro, Puebla, Pue.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08.00 a 16.00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Plaza comercial Tehuac&aacute;n<br />
        Calzada Adolfo L&oacute;pez Mateos No. 3210 local 5, Tehuac&aacute;n, Pue.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading">Quintana Roo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Av. Xcaret Supermanzana 33, Manzana 2, lote 2, Centro Comercial Las Palmas, Planta Alta, local 58a y 59a, Canc&uacute;n, Q.R.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 17:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading">San Luis Potos&iacute;</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Av. Manuel J. Clouthier Local Z-05, 2&ordm; piso de la STPS. S.L.P., S.L.P.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 19:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="panel panel-default">
				<div class="panel-heading">Tabasco</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-8">
							<p><strong>Domicilio</strong><br />
							    Av. Méndez No. 720, <abbr title="Colonia">Col. </abbr>Centro, Villahermosa, Tab.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			
			
<p>Nota: Las oficinas del Servicio Nacional de Empleo son las encargadas de la operaci&oacute;n de los programas en cada entidad federativa y dependen de los gobiernos de los estados. En la Ciudad de México se denominan Unidades Delegacionales del Servicio de Empleo (UDSE) y dependen del Gobierno de la Ciudad de México.</p>
<p><strong>&iexcl;Nuestros servicios son gratuitos!</strong></p>
    <p><strong>&iexcl;Estamos para servirte!</strong></p>
<div class="skype_c2c_menu_container notranslate" id="skype_c2c_menu_container" style="display: none;" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" data-fp="{7133A57D-150E-46A4-928E-F3D8C74E670F}" data-murl="https://pipe.skype.com/Client/2.0/" data-p2murl="https://c2c-p2m-secure.skype.com/p2m/v1/push" data-uiid="1" data-uilang="en">
<div class="skype_c2c_menu_click2call"><a class="skype_c2c_menu_click2call_action" id="skype_c2c_menu_click2call_action" target="_self">Call</a></div>
<div class="skype_c2c_menu_click2sms"><a class="skype_c2c_menu_click2sms_action" id="skype_c2c_menu_click2sms_action" target="_self">Send SMS</a></div>
<div class="skype_c2c_menu_push_to_mobile"><a class="skype_c2c_menu_push_to_mobile_action" id="skype_c2c_menu_push_to_mobile_action" target="_blank">Call from mobile</a></div>
<div class="skype_c2c_menu_add2skype"><a class="skype_c2c_menu_add2skype_text" id="skype_c2c_menu_add2skype_text" target="_self">Add to Skype</a></div>
<div class="skype_c2c_menu_toll_info"><span class="skype_c2c_menu_toll_callcredit">You'll need Skype Credit</span><span class="skype_c2c_menu_toll_free">Free via Skype</span></div>
</div>

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
