<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<style type="text/css">
	.filtro{

		display: none;

	}


</style>
<t:publicTemplate>
	<jsp:attribute name="titulo">Directorio de oficinas del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Selecciona un estado de la lista del catálogo para mostrar los datos de las oficinas del SNE.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/acercaSne.jsp"/>">Acerca del SNE </a></li>
          <li class="active">Ubicación</li>
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
			<h2 class="titulosh2">Ubicación</h2>
			
			<div class="page-header">
		        <h3>Directorio de Oficinas del Servicio Nacional de Empleo</h3>
			</div>
			 <!-- agregado por Benjamin para funcionalidad  08/02/2016 -->
			  <div class="page-header">
				<p>
				  Selecciona un estado de la lista del catálogo para mostrar los datos de las oficinas del <strong>SNE</strong>
		 		</p>
				  <select>
					  <option>Seleccione una entidad</option>
					  <option value="aguascalientes">Aguascalientes</option>
					  <option value="bajacalifornia">Baja California</option>
					  <option value="bajacaliforniasur">Baja California Sur</option>
					  <option value="campeche">Campeche</option>
					  <option value="coahuila">Coahuila</option>
					  <option value="colima">Colima</option>
					  <option value="chiapas">Chiapas</option>
					  <option value="chihuahua">Chihuahua</option>
					  <option value="ciudaddemexico">Ciudad de México</option>
					  <option value="durango">Durango</option>
					  <option value="guanajuato">Guanajuato</option>
					  <option value="guerrero">Guerrero</option>
					  <option value="hidalgo">Hidalgo</option>
					  <option value="jalisco">Jalisco</option>
					  <option value="estadodemexico">Estado de México</option>
					  <option value="michoacan">Michoacán</option>
					  <option value="morelos">Morelos</option>
					  <option value="nayarit">Nayarit</option>
					  <option value="nuevoleon">Nuevo León</option>
					  <option value="oaxaca">Oaxaca</option>
					  <option value="puebla">Puebla</option>
					  <option value="queretaro">Querétaro</option>
					  <option value="quintanaroo">Quintana Roo</option>
					  <option value="sanluispotosi">San Luis Potosí</option>
					  <option value="sinaloa">Sinaloa</option>
					  <option value="sonora">Sonora</option>
					  <option value="tabasco">Tabasco</option>
					  <option value="tamaulipas">Tamaulipas</option>
					  <option value="tlaxcala">Tlaxcala</option>
					  <option value="veracruz">Veracruz</option>
					  <option value="yucatan">Yucatán</option>
					  <option value="zacatecas">Zacatecas</option>
				  </select>
			  </div>
			  <p class="imgHome-Ubic" id="map" style="display : block"><img height="480" width="635" alt="" src="../../../images/bg_mapa-ubicaciones.png" /></p>
			 <!-- fin agregado funcionalidad-->
			<div class="page-header filtro aguascalientes">
		        <h4>Entidad - <small>Aguascalientes</small></h4>
			</div>
			
			<div class="panel panel-default filtro aguascalientes">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Av. Aguascalientes Sur 3214, Fracc. Prados del Sur,<br />
							    <abbr title="C&oacute;digo Postal">C.P.</abbr> 20280.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Aguascalientes, Aguascalientes.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. María del Carmen Villa Zamarripa</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						           (449) 971-94-01 al 04 <br />
						           Ext. 115</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <!-- <a href="mailto:sneags_director@stps.gob.mx">sneags_director@stps.gob.mx</a><br />-->
						    <!-- <a href="mailto:oziel.guerrero@aguascalientes.gob.mx">oziel.guerrero@aguascalientes.gob.mx</a></p>-->
							<a href="mailto:carmen.villa@aguascalientes.gob.mx">carmen.villa@aguascalientes.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->

			<div class="page-header filtro aguascalientes">
		        <h4><small>Calvillo</small></h4>
			</div>
			
			<div class="panel panel-default filtro aguascalientes">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Blvrd. Rodolfo Landeros s/n, <abbr title="Colonia">Col. </abbr>Calvillo Centro,<br />
							<abbr title="C&oacute;digo Postal">C.P.</abbr> 20800.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
   								Calvillo, Aguascalientes.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
    							L a V de 8 a 15:30 hrs.</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Responsable</strong><br />
    						Lic. Pedro López Eudave</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							<p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    						<span class="skype_c2c_print_container notranslate">(495) 956-17-88 </span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524959561788" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container">
    						<span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
   							 <a href="CE1Calvillo@stps.gob.mx">CE1Calvillo@stps.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<!--<div class="page-header filtro aguascalientes">
		        <h4><small>Jesús María</small></h4>
			</div> -->
			
			<!-- <div class="panel panel-default filtro aguascalientes">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Mar&iacute;a del Carmen Brise&ntilde;o No. 302, Esquina Paseo de los Chicahuales, Cabecera Municipal de Jes&uacute;s Mar&iacute;a. <abbr title="C&oacute;digo Postal">C.P.</abbr> 20925</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
   								Jesús María, Aguascalientes.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
    							L a V de 8 a 15:30 hrs.</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Responsable</strong><br />
    						Miguel Rivera Sánchez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							<p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    						         <span>01 (449) 963-60-28</span><br />
                                                         <span>01 (449) 963-96-65 ext. 1</span><br />
                                                        </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
   							 <a href="mailto:sne.urjesusmaria@hotmail.com">sne.urjesusmaria@hotmail.com</a><br />
    						 <a href="mailto:miguelrs33@hotmail.com">miguelrs33@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div> --><!-- end panel -->

			<div class="page-header filtro bajacalifornia">
		        <h4>Entidad - <small>Baja California</small></h4>
		        <h4><small>Mexicali</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacalifornia">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Calle Calafia N&deg;. 1100 y Av. Pioneros Centro C&iacute;vico C.P. 21000. Mexicali, Baja California&nbsp;</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Mexicali, Baja California.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Lic. Mirna Cecilia Rincón Vargas</br>
							    Directora del Servicio Nacional de Empleo</p>
							    <p>Atención a solicitantes:</p>
							     <!-- <p>Lic. María Elena Valdez</p> -->
								 <p>Lic. Juan Manuel Lizárraga Luque <br />Coordinador de Vinculación Laboral</p>
						      
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     (686) 555-49-90</p>
                                                   <p><span>(686) 555-49-91</span><br />
                                                    Ext. 5417 o 5418</p>
						</div>
						<div class="col-sm-6">
							<!-- <p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:seebc@stps.gob.mx">seebc@stps.gob.mx</a></p> -->
        <p><span><a href="">N/A</a></span></p>
     <!--   <p><span><a href="mailto:seebc_mexicali@stps.gob.mx">seebc_mexicali@stps.gob.mx</a></span></p> -->
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro bajacalifornia">
		        <h4><small>Ensenada</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacalifornia">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Del Puerto # 351 y 355, Fracc. Playas de Ensenada, Ensenada B.C.  </p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
   								Ensenada, Baja California.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
    							L a V de 8 a 16 hrs.</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Responsable</strong><br />
    						Esperanza Jiménez Iñiguez</br>
    						Coordinadora de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							<p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    						<span class="skype_c2c_print_container notranslate">(646) 178-27-23</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526461740297" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
    <p class="linea-div"><span>    <span class="skype_c2c_print_container notranslate">(646) 178-34-44</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526461757116" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></span></p>
    						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
   							 <a href="mailto:empleoensenada@hotmail.com">empleoensenada@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro bajacalifornia">
		        <h4><small>Tijuana</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacalifornia">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Blvd. Federico Benítez López, Plaza Ejecutiva Carrousel, No. 14785 Segundo Nivel local 36, Col. Gas y Anexas, C.P. 22115.
							</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
   								Tijuana, Baja California.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
    							L a V de 8 a 16 hrs.</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Responsable</strong><br />
    						<!-- Nayla Carmelina Rangel Franco</br> -->
    						<!-- Coordinadora de la Unidad Operativa</p> -->
							Diego Alfonso Ramírez Robles</br>
    						Coordinador de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							<p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    						<span class="skype_c2c_print_container notranslate">(664) 6816907</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526646816907" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
    <p><span><span class="skype_c2c_print_container notranslate">(664) 6819149</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526646819149" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
   							 <a href="mailto:lic_ramirezrobles@hotmail.com">lic_ramirezrobles@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<!-- <div class="page-header filtro bajacalifornia">
		        <h4><small>San Quintín</small></h4>
			</div> -->
			
			<!-- <div class="panel panel-default filtro bajacalifornia">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. &quot;A&quot; entre 9 y 10, Fracc. Ciudad San Quintin (Centro de Gobierno), C.P. 22935, Ensenada B.C.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
   								San Quintín, Baja California.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
    							L a V de 8 a 15 hrs.</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Responsable</strong><br />
    						Alma Angélica Sandoval</br>
    						Coordinadora de Módulo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							<p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    						<span class="skype_c2c_print_container notranslate">01 (616) 165 2464</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526161652464" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
    Ext. 3609    </span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
   							 <a href="mailto:aasandoval@baja.gob.mx">aasandoval@baja.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div> --><!-- end panel -->
			
			<div class="page-header filtro bajacaliforniasur">
		        <h4>Entidad - <small>Baja California Sur</small></h4>
		        <h4><small>La Paz</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacaliforniasur">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Allende N°. 1486 esq. Melit&oacute;n Alba&ntilde;ez y Ensenada, Fracc. Perla, </abbr><span><abbr title="C&oacute;digo Postal">C.P.</abbr> 23040.</abbr></span></p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    La Paz, Baja California Sur.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Raúl Ceseña Ceseña<br />
							    Director General del Servicio Nacional de Empleo</p>
							    						      
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">(612) 1229700</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526121229700" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
    Ext. 1023<br/>
    <!--<span class="skype_c2c_print_container notranslate">(612) 122 42 48 ext 209 y 210</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526121229700" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br /> -->    
    <span class="skype_c2c_print_container notranslate">(612) 122 01 77 ext. 1023</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526121229700" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
     </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:director@snebcs.gob.mx">director@snebcs.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro bajacaliforniasur">
		        <h4><small>Comond&uacute; (Ciudad Constituci&oacute;n)</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacaliforniasur">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Carretera Transpeninsular Km. 212.5 <abbr title="Colonia">Col. Vargas</abbr>, Edificio de Gobierno (antes BANRURAL)</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Comond&uacute;, Baja California Sur.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Coordinadora</strong><br />
							    Lic. Bertha Janett Cortés Ávila</p>
							    						      
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">(613) 132 45 72</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526121324572" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:berthajanett81@hotmail.com">berthajanett81@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro bajacaliforniasur">
		        <h4><small>La Paz</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacaliforniasur">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Allende N°. 1486 esq. Melit&oacute;n Alba&ntilde;ez y Ensenada, Fracc. Perla, </abbr><span><abbr title="C&oacute;digo Postal">C.P.</abbr> 23040.</abbr></span></p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    La Paz, Baja California Sur.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Lic. Reyna Luz García Huerta</br>
								Coordinadora Unidad Regional
							    </p>
							    						      
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                             <span>(612) 1229700 Ext. 1012</span><br />
                                                             <!--<span>(612) 122 42 48 ext 209 y 210</span><br /> -->
                                                             <span>(612) 122 01 77 ext. 1012 </span><br />
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:urlapaz@outlook.com">urlapaz@outlook.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro bajacaliforniasur">
		        <h4><small>San Jose del Cabo</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacaliforniasur">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Blvd. Mauricio Castro y 5 de Mayo Edif. Pedrin,<abbr>1er. Piso, Local 3, Col. 8 de Octubre </abbr>,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 23406.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Los Cabos, Baja California Sur.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    T.S. Magdalena Verdugo Ibarra<br />
							    Coordinadora</p>
							    						      
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                             <span>(624) 105 24 05</span><br />
                                                             <span>(624) 105 24 06</span><br />
                                                             <span>Ext. 115 </span><br />
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:riolita55@hotmail.com">riolita55@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro bajacaliforniasur">
		        <h4><small>Cabo San Lucas</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacaliforniasur">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Calle Aguajitos s/n, Plaza Tres V&iacute;rgenes, Local 202, <abbr title="Colonia">Col. Arcos del Sol</abbr>, <abbr title="C&oacute;digo Postal">C.P.</abbr> 23474.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cabo San Lucas, Baja California Sur.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   C.P. Luz María Poixtan Fernández</br>
							    Responsable de Módulo</p>
							    						      
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">624 1437849</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526241437849" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 624 1437849</span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:cabosanlucas@snebcs.gob.mx">cabosanlucas@snebcs.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<!--<div class="page-header filtro bajacaliforniasur">
		        <h4><small>Guerrero Negro</small></h4>
			</div> -->
			
			<!--<div class="panel panel-default filtro bajacaliforniasur">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Calle Manuel F. Montoya E/Calle Mario C&aacute;rdenas Ibarra,<abbr title="Colonia">Col. </abbr>Solidaridad.<br />&nbsp;</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Mulegé, Baja California Sur.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Laura Patricia Castro López</p>
							    						      
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">044 (615) 15 70381</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526151570381" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span> </br> Directo</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:laurakcastro25@live.com.mx">laurakcastro25@live.com.mx</a></p>
						</div>
					</div>
				</div>
			</div> --><!-- end panel -->
			
			<div class="page-header filtro bajacaliforniasur">
		        <h4><small>Loreto</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacaliforniasur">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Calle Pueblito E/Independencia y Zapata, Col. Centro.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Loreto, Baja California Sur.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Dora Elda Oropeza Villalejo
							    </p>
							    						      
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                          (613) 1351144 Directo   
						        </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:doritavillalejo@hotmail.com">doritavillalejo@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro bajacaliforniasur">
		        <h4><small>Santa Rosalía</small></h4>
			</div>
			
			<div class="panel panel-default filtro bajacaliforniasur">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Av. Alvaro Obreg&oacute;n esq. Calle Noria s/n, <abbr title="Colonia">Col. </abbr>Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 23920.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Mulegé, Baja California Sur.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. José Alfredo Palafox Romo</p>
							    						      
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (612) 1523185</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526121523185" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (612) 1523185</span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:josealfredo_pr@hotmail.com">josealfredo_pr@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro campeche">
		        <h4>Entidad - <small>Campeche</small></h4>
			</div>
			
			<div class="panel panel-default filtro campeche">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Calle 53 No. 2, <abbr title="Colonia">Col. </abbr>San Francisco de Campeche Centro,
    <abbr title="C&oacute;digo Postal">C.P. </abbr>24000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Campeche, Campeche.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Mtro. Luis Antonio Chin Gutiérrez
							    Director del Servicio Nacional de Empleo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     <p>
						         <span>(981) 811 20 35</span><br />
                                                         <span>(981) 811 57 64</span><br />
                                                          <span>(981) 816 34 50 ext. 101</span>
                                                    </p> 
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:seecamp_director@stps.gob.mx">seecamp_director@stps.gob.mx</a><br />
                                                    <a href="mailto:seecamp@stps.gob.mx">seecamp@stps.gob.mx</a><br />
                                                        </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro campeche">
		        <h4><small>Campeche</small></h4>
			</div>
			
			<div class="panel panel-default filtro campeche">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Calle 53 No. 2, <abbr title="Colonia">Col. </abbr>San Francisco de Campeche Centro,
    <abbr title="C&oacute;digo Postal">C.P. </abbr>24000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Campeche, Campeche.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. José Alfredo Loria Uc</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                 <p>
						         <span>(981) 811 57 63</span><br />
                                 <span>(981) 816 34 50</span><br />
                                                 </p>
                                                </div>
                                                					    
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:bolsa_snecampeche1@hotmail.com">bolsa_snecampeche1@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro campeche filtro campeche">
		        <h4><small>Cd. del Carmen</small></h4>
			</div>
			
			<div class="panel panel-default filtro campeche">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Calle 30 No.120 entre calle 31 (Av. Aviación) y 33, <abbr title="Colonia">Col. </abbr>Centro, Ciudad del Carmen, <br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 24100.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Carmen, Campeche.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Zaira Gómez Borbolla</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     <p>
						         <span>(938) 382 39 37</span><br />
                                                     </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:empleo-carmen@hotmail.com">empleo-carmen@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
	<!--		<div class="page-header filtro campeche">
		        <h4><small>Calakmul</small></h4>
			</div>
			
			<div class="panel panel-default filtro campeche">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Chicana No. 45, <abbr title="Colonia">Col. </abbr>Bellavista, entre Av. Calakmul y Calle Silvituk,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 24640.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Xpujil, Calakmul, Campeche.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Marbella Jiménez López</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <p>
						         <span>01 (983) 176 17 84</span><br />
                                                         <span>01 (983) 371 62 13</span><br />
                                                    </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:mar_il@outlook.com">mar_il@outlook.com</a><br /></p>
						</div>
					</div>
				</div>
			</div>--><!-- end panel -->
			
			<div class="page-header filtro campeche">
		        <h4><small>Calkiní</small></h4>
			</div>
			
			<div class="panel panel-default filtro campeche">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 26 s/n, entre Av. Eduardo Baeza (calle15) y calle 19 <abbr title="Colonia">Col. </abbr>San Luís Obispo,<br />
    <abbr title="C&oacute;digo Postal">C.P. 24904.</abbr> </p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Calkiní, Campeche.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Profa. Bertha de la Cruz Vázquez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <p>
						         <span>(996) 961 14 55</span><br />
                                                    </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:lileniga21@hotmail.com">lileniga21@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro campeche">
		        <h4><small>Campeche</small></h4>
			</div>
			
			<div class="panel panel-default filtro campeche">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 63 No. 18, <abbr title="Colonia">Col. </abbr>Centro, San Francisco de Campeche, <abbr title="C&oacute;digo Postal">C.P.</abbr> 24000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Campeche, Campeche.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Dionisia Sarmiento Buendia</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     <p>
						        <!-- <span>01 (981) 816 34 50</span><br />-->
						         <span>(981) 811 39 89</span>
                                                    </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:pymes.desarrolloeconomico@gmail.com">pymes.desarrolloeconomico@gmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro campeche">
		        <h4><small>Champotón</small></h4>
			</div>
			
			<div class="panel panel-default filtro campeche">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 25 s/n entre calles 32 y 34, <abbr title="Colonia">Col. </abbr>Centro,<abbr title="C&oacute;digo Postal">C.P.</abbr> 24400.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Champotón, Campeche.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Rosario Lizbeth Cocon Álvarez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     <p>
						         <!-- -<span>01 (982) 828 00 47</span><br /> -->
                                                <span>(982) 828 00 47 ext. 160 y 170</span>     </p>        
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:bolsadetrabajo_h.ayuntachampo@hotmail.com">bolsadetrabajo_h.ayuntachampo@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro campeche">
		        <h4><small>Escárcega</small></h4>
			</div>
			
			<div class="panel panel-default filtro campeche">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Solidaridad s/n, entre 67 y 69,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 24350.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Escárcega, Campeche.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Jesús Antonio Sabido Caamal</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     <p>
						         <span>(982) 824 18 01</span><br />
                                                         <span>(982) 824 01 12</span><br />
                                                     </p>  
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:sabido-1976@hotmail.com">sabido-1976@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro coahuila">
		        <h4>Entidad - <small>Coahuila</small></h4>
		        <h4><small>Saltillo</small></h4>
			</div>
			
			<div class="panel panel-default filtro coahuila">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Carlos Abedrop Davila No. 2610, <abbr title="Colonia">Col. </abbr>Centro Metropolitano, <abbr title="C&oacute;digo Postal">C.P.</abbr> 25022.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Saltillo, Coahuila.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Nazira Zogbi Castro</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (844) 412-56-76, 410-52-49<br />
                                                          410-33-62, 412-03-25 <br />
                                                          414-21-00 y 412-66-02<br />
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:seecoah_director@stps.gob.mx">seecoah_director@stps.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro coahuila">
		        <h4><small>Acuña</small></h4>
			</div>
			
			<div class="panel panel-default filtro coahuila">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Lerdo Sur N° 800,<!--<abbr title="Colonia">-->  <!--</abbr>-->Planta Alta,Zona Centro,<abbr title="C&oacute;digo Postal">C.P.</abbr> 26200.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Acuña, Coahuila.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 14:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Alfredo Cisneros Villarreal</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    (877) 773-11-70</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:institutoee@hotmail.com">sneacu_vinculacion@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro coahuila">
		        <h4><small>Monclova</small></h4>
			</div>
			
			<div class="panel panel-default filtro coahuila">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Blvd. Benito Juárez No. 719 y 717, esquina Vía Apia, <abbr title="Colonia">Col. </abbr>Roma, Frontera, <br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 25710.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Monclova, Coahuila.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Edna Miroslava Heredia Alarcón</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01(866) 633-88-18 <br /> 
                                                          633-39-39 y 632-32-55  
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:seemonclova_infor@stps.gob.mx">seemonclova_infor@stps.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro coahuila">
		        <h4><small>Piedras Negras</small></h4>
			</div>
			
			<div class="panel panel-default filtro coahuila">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Avenida 16 de Septiembre # 512, <abbr title="C&oacute;digo Postal">C.P.</abbr> 26014.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Piedras Negras, Coahuila.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Prof. Juan Alfredo Botello Najera</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (878) 782 38 58</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:seecoah_admvo@stps.gob.mx">seecoah_admvo@stps.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro coahuila">
		        <h4><small>Sabinas</small></h4>
			</div>
			
			<div class="panel panel-default filtro coahuila">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Lamadrid No. 289, <abbr title="Colonia">Col. </abbr>Ciudad Sabinas Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 26700.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Sabinas, Coahuila.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Diana Guadalupe Haro Martínez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (861) 612 06 29</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:ieesabinas@hotmail.com">ieesabinas@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->			
			
			<div class="page-header filtro coahuila">
		        <h4><small>Saltillo</small></h4>
			</div>
			
			<div class="panel panel-default filtro coahuila">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Guadalupe Victoria No. 608, Planta Baja, <abbr title="Colonia">Col. </abbr>Saltillo Zona Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 25000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Saltillo, Coahuila.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lorena Fernández Salas</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (844) 410-34-22, 410-34-50 <br />
                                                          412-71-88, 412-89-85 y 412-96-56
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:uccoah_contraloria1@stps.gob.mx">uccoah_contraloria1@stps.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro coahuila">
		        <h4><small>Torreón</small></h4>
			</div>
			
			<div class="panel panel-default filtro coahuila">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Zaragoza No. 148 Bis Sur,  <abbr title="Colonia">Col. </abbr>Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 27000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Torreón, Coahuila.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Raul Alejandro Garza del Valle</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (871) 712-27-60<br />
                                                          712-78-65 Y 711-15-81
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:uotorreon_director@stps.gob.mx">uotorreon_director@stps.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro colima">
		        <h4>Entidad - <small>Colima</small></h4>
		        </div>
			
			<div class="panel panel-default filtro colima">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							3er. Anillo Perif&eacute;rico esq. Av. Ej&eacute;rcito Mexicano, Complejo Administrativo del Gobierno del Estado, edif. A, 2 piso, <abbr title="Colonia">Col. </abbr>El Diezmo, <abbr title="C&oacute;digo Postal"> C.P.</abbr> 28029.<br />
    &nbsp;</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Colima, Colima.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							Lic. Hugo Alejandro Luna Gutiérrez   </br>
                                                         Coordinador de la Unidad Regional Colima</p>
                                                </div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    (312) 316 20 00 </br>
						    Ext. 2277</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:snecolima@gobiernocolima.gob.mx">snecolima@gobiernocolima.gob.mx</a><br />
						    <a href="mailto:snecolima@gmail.com">snecolima@gmail.com</a></p>
						    
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro colima">
		        <h4><small>Manzanillo</small></h4>
		        </div>
			
			<div class="panel panel-default filtro colima">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Paseo de las Gaviotas #93, Barrio 6, <abbr title="Colonia">Col.</abbr>Valle de las Garzas,  <abbr title="C&oacute;digo Postal"> C.P.</abbr> 28219, Manzanillo Centro.<br />
                           </p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Manzanillo, Colima.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Ihojannatan Carlos González Cervantes</br>
							 Coordinador Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
					           <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                      <p>
						         <span>(314) 334 26 99</span><br />
                                                         <span>(314) 332 78 00</span><br />
                                                     </p>             
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:ihojann@gmail.com">ihojann@gmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro colima">
		        <h4><small>Tecomán</small></h4>
		        </div>
			
			<div class="panel panel-default filtro colima">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Hidalgo s/n, <abbr title="Colonia">Col. </abbr>Centro, Tecomán.<br />
    &nbsp;</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tecomán, Colima.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Janell Alejandra Córdoba Urtiz</br>
							 Analista B, Consejero del Empleo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						         <span>(313) 324 38 68</span><br />
                                                         <span>(313) 326 71 43</span><br />
                                                         </p> 
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:jaculr3@hotmail.com">jaculr3@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro chiapas">
		        <h4>Entidad - <small>Chiapas</small></h4>
		        <h4><small>Tuxtla Gutiérrez</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chiapas">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 2&deg; Av. Sur y Calle Central s/n, 1er Piso, <abbr title="Colonia">Col.</abbr> Centro, <abbr title="C&oacute;digo Postal"> C.P.</abbr> 29000.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tuxtla Guti&eacute;rrez, Chiapas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Ana Keyla &Aacute;lvarez Arzate<br/>
							 Cargo<br/>
							Subsecretaria del Servicio Nacional de Empleo Chiapas</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (961)&nbsp; 6189440</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529616189440" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
    Ext. 63026</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:keyla.alvarez@empleos.chiapas.gob.mx">keyla.alvarez@empleos.chiapas.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro chiapas">
		        <h4><small>Comit&aacute;n</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chiapas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Central Poniente Lic. Benito Ju&aacute;rez No. 22, <abbr title="Colonia">Col. </abbr><span>Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 30000.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Comit&aacute;n de Dom&iacute;nguez, Chiapas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							Lic. Mario Caralampio Hernández López<br/>
							Cargo<br/>
							Delegado de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (963) 6320082</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529636320082" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:comitan@empleos.chiapas.gob.mx">comitan@empleos.chiapas.gob.mx</a><br/>
						    <a href="mailto:mchernandez@empleos.chiapas.gob.mx">mchernandez@empleos.chiapas.gob.mx</a></p>
   
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro chiapas">
		        <h4><small>Palenque</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chiapas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Reforma No. 25, entre Av. Independencia y Av. Abasolo, <abbr title="Colonia">Col.</abbr> Centro, <abbr title="C&oacute;digo Postal"> C.P.</abbr> 29960.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Palenque, Chiapas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Ramiro Alberto Villegas López</br>
							 Delegado de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (916) 3451618</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529163451618" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:rvillegas@empleos.chiapas.gob.mx">rvillegas@empleos.chiapas.gob.mx</a></p>
    <p><span><a href="mailto:palenque@empleos.chiapas.gob.mx">palenque@empleos.chiapas.gob.mx</a></span></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro chiapas">
		        <h4><small>Pichucalco</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chiapas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Prolongaci&oacute;n de Morelos No. 20 esq. Vicente Guerrero 1er Piso, <abbr title="Colonia">Col. </abbr>Pichucalco Chiapas <br /><abbr title="C&oacute;digo Postal">C.P.</abbr> 29520.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Pichucalco, Chiapas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							Lic. Dulce María Juárez Hernández</br>
							 Delegada de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (932) 3230661</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529323230661" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						        <p><span><a href="mailto:pichucalco@empleos.chiapas.gob.mx">pichucalco@empleos.chiapas.gob.mx</a></span></p>
						        <p><span><a href="mailto:mjuarez@empleos.chiapas.gob.mx">mjuarez@empleos.chiapas.gob.mx</a></span></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro chiapas">
		        <h4><small>San Cristóbal de las Casas</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chiapas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Ignacio Allende No. 11, <abbr title="Colonia">Barrio </abbr>La Merced, <br />C.P.</abbr> 29240.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Crist&oacute;bal de las Casas, Chiapas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Profr. Nicolás Lorenzo Álvarez Martínez</br>
							 Delegado de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (967) 6780682</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529676780682" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
				              <p><span><a href="mailto:sancristobal@empleos.chiapas.gob.mx">sancristobal@empleos.chiapas.gob.mx</a></span></p>
				               <p><span><a href="mailto:nalvarez@empleos.chiapas.gob.mx">nalvarez@empleos.chiapas.gob.mx</a></span></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro chiapas">
		        <h4><small>Tuxtla</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chiapas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							3era. Calle Poniente Sur No. 170, Col. <abbr title="Colonia">Centro </abbr>Tuxtla Gutiérrez Chiapas, <br />C.P.</abbr> 29000.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tuxtla Gutiérrez, Chiapas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Alejandro Gustavo Navarro Samayoa</br>
							 Delegado de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (961) 611 43 76</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529676780682" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:anavarro@empleos.chiapas.gob.mx">anavarro@empleos.chiapas.gob.mx</a></p>
    <p><span><a href="mailto:tuxtla@empleos.chiapas.gob.mx">tuxtla@empleos.chiapas.gob.mx</a></span></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->

			<div class="page-header filtro chiapas">
		        <h4><small>Tapachula</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chiapas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							1era Av. Sur Prolongación Unidad Administrativa, Edif. B, Planta Baja, Col. <abbr title="Colonia">Las Palmas </abbr>, <br />C.P.</abbr> 30709.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tapachula de Córdoba y Ordóñez, Chiapas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 LIc. Luis Hipólito Cortes Magariño</br>
							 Delegado de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (962) 626 05 76 y 01 (962) 628 71 47</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529676780682" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:lcortes@empleos.chiapas.gob.mx">lcortes@empleos.chiapas.gob.mx</a></p>
    						<p><span><a href="mailto:tapachula@empleos.chiapas.gob.mx">tapachula@empleos.chiapas.gob.mx</a></span></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->								
			
			<div class="page-header filtro chihuahua">
		        <h4>Entidad - <small>Chihuahua</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chihuahua">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Allende No. 901 Primer Piso,  <abbr title="Colonia">Col. </abbr>Centro.<br />
    <abbr title="C&oacute;digo Postal"> </abbr> </p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Chihuahua, Chihuahua.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Ana Roció Escobar Rivero</br>
							 Directora del Servicio Nacional de Empleo Chihuahua.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">(614) 429 33 00 </span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529616189440" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
    Ext. 24702</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:ana.escobar@chihuahua.gob.mx">ana.escobar@chihuahua.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro chihuahua">
		        <h4><small>Cd. Juárez</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chihuahua">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Edificio Administrativo, Planta Baja, Av. Eje Vial Juan Gabriel y Calle Aserraderos s/n,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 32250.<br />
    </abbr></p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Juárez, Chihuahua.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Zazai Rubio Hernández</br>
							 Coordinador de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">(656) 629 33 00 </span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526566293300" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
    Ext. 55409, 55542, 55555, 55556, 55580, 55558</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:zazai.rubio@chihuahua.gob.mx">zazai.rubio@chihuahua.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro chihuahua">
		        <h4><small>Chihuahua</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chihuahua">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Allende No. 901 Primer Piso, <abbr title="Colonia">Col. </abbr>Centro.<br />
    <abbr title="C&oacute;digo Postal"> </abbr> <br /></p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Chihuahua, Chihuahua.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Norma Carolina Reyes Rubio</br>
							 Coordinadora de la Unidad Regional Chihuahua</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">(614) 429 33 00</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526144293300" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
    Ext. 24737</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:norma.reyes@chihuahua.gob.mx">norma.reyes@chihuahua.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro chihuahua">
		        <h4><small>Hidalgo del Parral</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chihuahua">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 20 de Noviembre No. 2, <abbr title="Colonia">Col.</abbr> Centro. <br />
    <abbr title="C&oacute;digo Postal"> </abbr> </p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Hidalgo del Parral, Chihuahua.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Carlos Iván Valdez Valenzuela</br>
							 Responsable de la Unidad Regional</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     <p>
						         <span>(627) 523 93 00 </span><br />
                                                         Ext. 77364 y 77365
                                                    </p> 
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:carlos.valdez@chihuahua.gob.mx">carlos.valdez@chihuahua.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
		<!--	<div class="page-header filtro chihuahua">
		        <h4><small>Camargo</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chihuahua">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Auditorio Cultural Camargo, 2&deg; Piso, Av. Juárez y Calle Francisco Sarabia s/n, <abbr title="Colonia">Col.</abbr> Centro,&nbsp; <span><abbr title="C&oacute;digo Postal">C.P.</abbr> 33700. </span></p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Camargo, Chihuahua.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Karen Nohemi Moreno Carrete</br>
							 Responsable del Módulo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (648) 4620720</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526484620720" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (648) 4620720</span></span></span></span></p>
						<span class="skype_c2c_print_container notranslate">01 (648) 4626902</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526484620720" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (648) 4626902</span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:comercioyturismofe@hotmail.com">comercioyturismofe@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div>--><!-- end panel -->
			
	<!--	<div class="page-header filtro chihuahua">
		        <h4><small>Cd. de Nuevo Casas Grandes</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chihuahua">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Benito Ju&aacute;rez No. 815, <abbr title="Colonia">Col.</abbr> Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 31700.<br />
    <br />
    &nbsp;</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Nuevo Casas Grandes, Chihuahua.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Jose Alfredo Armend'ariz Ceballes</br>
							 Responsable del Módulo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (636) 6941912</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526366941912" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (636) 6941912</span></span></span></span> y 6941360</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:serviciodeempleoncg@hotmail.com">serviciodeempleoncg@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div>--><!-- end panel -->
			
	<!--	<div class="page-header filtro chihuahua">
		        <h4><small>Cuauhtémoc</small></h4>
		        </div>
			
			<div class="panel panel-default filtro chihuahua">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ignacio Allende s/n, <abbr title="Colonia">Col. </abbr>Ciudad Cuauht&eacute;moc Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 31500.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cuauhtémoc, Chihuahua.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 C. Sara Isela Delgado Silveyra</br>
							 Responsable del Módulo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (625) 581 92<br />
						    Ext. 75013</p>
						   </div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:saradelgadosilveyra@hotmail.com">saradelgadosilveyra@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div>--><!-- end panel -->
			
			<!--<div class="page-header filtro chihuahua">
		        <h4><small>Delicias</small></h4>
		        </div> -->
			
			<!--<div class="panel panel-default filtro chihuahua">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Edif. Lerdo de Tejada 1er Piso, Av. 6a Oriente y Calle 1a Norte, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 33000.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Delicias, Chihuahua.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 C. Magdalena Matar Conde</br>
							 Responsable del Módulo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (639) 479 93 00<br />
						    Ext. 70004, 70005  y 70053</p>
						   </div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:snedelicias@hotmail.com">snedelicias@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div> --><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4>Entidad - <small>Ciudad de México</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							<!-- Xocongo No. 58, Esquina con Fernando de Alva Ixtlilxóchitl Piso 6, <abbr title="Colonia">Col. </abbr>Tr&aacute;nsito,<br />
<abbr title="C&oacute;digo Postal">C.P.</abbr> 06820<abbr title="Ciudad de M&eacute;xico"></abbr></p>
							-->
							 Calz. San Antonio Abad No. 32, <abbr title="Colonia">Col. </abbr>Tr&aacute;nsito,<br /> 
  Deleg, Cuauht&eacute;moc, <abbr title="C&oacute;digo Postal">C.P.</abbr> 06820</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cuauhtémoc, Ciudad de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Maestra Adela Ruíz Belenguer</br>
							 Subdirectora de Normatividad y Coordinación Operativa</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						         555709 3233<br /> 
							Ext. 3041 
							    <br />
							</p>
                                                </div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:atencion.ciudadana.uc@gmail.com">atencion.ciudadana.uc@gmail.com</a>
						<br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Álvaro Obregón</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 10 y Prolongaci&oacute;n Canarios, <abbr title="Colonia">Col. </abbr>Toltecas, <abbr title="C&oacute;digo Postal">C.P.</abbr> 01160,Ciudad de M&eacute;xico<br />
    &nbsp;</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Álvaro Obregón, Ciudad de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Rubén Hernández Estrada<br />
							 Coordinador Regional del Servicio de Empleo en Álvaro Obregón</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    555276 6885
							5552766886
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:kimbapancracia@gmail.com">kimbapancracia@gmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Azcapotzalco</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calz. Camarones No. 494, esq. Norte 87B, <abbr title="Colonia">Col. </abbr>El Recreo, <span><abbr title="C&oacute;digo Postal">C.P.</abbr> 2700, Ciudad de M&eacute;xico</span></p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Azcapotzalco, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Ana María Mejia Cordero<br />
							 Coordinadora Regional del Servicio de Empleo en Azcapotzalco</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    555341 0953<br />
						 
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						   
						    <a href="mailto:sneazcapotzalco@gmail.com">sneazcapotzalco@gmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Benito Juárez</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Prolongación Uxmal casi esq. Mpio. Libre. Sta. Cruz Atoyac, <span><abbr title="C&oacute;digo Postal">C.P.</abbr> 3310, Ciudad de M&eacute;xico</span></abbr></p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Benito Juárez, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Christian Vallarta González<br />
							 Coordionador Regional del Servicio de Empleo en Benito Juárez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    555604 5737 <br />
							555605 7111<br />
							</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />						    
						    <a href="mailto:sejuarez@stps.gob.mx">sejuarez@stps.gob.mx</a><br />
							<a href="mailto:uosebju.cr@gmail.com">uosebju.cr@gmail.com</a>							
							</p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Coyoacán</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Prolongación Sta. Úrsula (entre Sta. Isauro y Sn. Ricardo), <span><abbr title="C&oacute;digo Postal">C.P.</abbr> 4600, Ciudad de M&eacute;xico</span></abbr></p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Coyoacán, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 <!-- María Dolores Gutiérrez Chávez<br /> -->
							Lic. Juan Manuel de Jesús Muñetón Vargas<br />
							Coordionador Regional del Servicio de Empleo en Coyoacán</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    555619 2852<br />
							555604 5737<br /></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:e.udse.coyoacan@gmail.com">e.udse.coyoacan@gmail.com</a><br />
						    <a href="mailto:vinculacion.coyoacan@hotmail.com">vinculacion.coyoacan@hotmail.com</a><br />
							<a href="mailto:juanmmuneton@hotmail.com">juanmmuneton@hotmail.com</a>
							</p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Cuajimalpa</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Veracruz No. 121, <abbr title="Colonia">Col. </abbr>Cuajimalpa,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 5000, Ciudad de M&eacute;xico</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cuajimalpa de Morelos, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Gabino Millán Mercado<br />
							 Coordinador Regional del Servicio de Empleo en Cuajimalpa</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    553000 2100 Ext. 1750 y 1741<br />
							555812 2688<br /></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:secuaji@stps.gob.mx">secuaji@stps.gob.mx</a><br />
							<a href="mailto:secuaji.coord@gmail.com">secuaji.coord@gmail.com</a>
						     </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Cuauhtémoc</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							<!-- Xocongo No. 58 esq. con Fernando de Alva Ixtlilx&oacute;chitl 2&deg; piso, <abbr title="Colonia">Col. </abbr>Tr&aacute;nsito,<br /> <abbr title="C&oacute;digo Postal"> C.P.</abbr> 06820, Ciudad de M&eacute;xico -->
    <!-- </span>&nbsp;</p> -->
	Calz. San Antonio Abad No.32, (Planta Baja), <abbr title="Colonia">Col. </abbr>Tr&aacute;nsito, <abbr title="C&oacute;digo Postal"> C.P.</abbr> 06820.
    </span></p>

    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    <!-- Cuauhtémoc, Ciudad de México</p> -->
								Cuauhtémoc, Ciudad de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 <!-- Lic. Miguel Ángel Bermudez Olguin<br /> -->
							 Beatriz Guadalupe Picazo Peche<br /> 
					Coordinadora Regional del Servicio de Empleo en Cuauhtémoc</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    555038 0200 Ext. 3045
						    </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
							<a href="mailto:uosecuauhtemoc@gmail.com">uosecuauhtemoc@gmail.com</a><br/>
						    <a href="mailto:c.udse.cuauh@gmail.com">c.udse.cuauh@gmail.com</a><br/>
						     <a href="mailto:setemoc@stps.gob.mx">setemoc@stps.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Gustavo A. Madero</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							5 de Febrero y Vicente Villada <abbr title="Colonia">Col.</abbr> La Villa, <br /><span><abbr title="C&oacute;digo Postal">C.P.</abbr> 7050,</span> Ciudad de M&eacute;xico.</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Gustavo A. Madero, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Eliseo Lorenzo Rodríguez<br />
							 Coordinador Regional del Servicio de Empleo en Gustavo A. Madero</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							5555 9164 9670  <br />
						    55767 114 0364</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						   		 <a href="mailto:semadero@stps.gob.mx">semadero@stps.gob.mx</a>
								 <a href="mailto:uose.gamcor@gmail.com">uose.gamcor@gmail.com</a>
						    </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Iztacalco</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							A&ntilde;il y Río Churubusco, <abbr title="Colonia">Col. </abbr>Granjas M&eacute;xico,<br /><abbr title="C&oacute;digo Postal"> C.P.</abbr> 8400, Ciudad de M&eacute;xico</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Iztacalco, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Martha Patricia Elvira Mora<br />
							 Coordinadora Regional del Servicio de Empleo en Iztacalco</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    555654 7055<br />
						    553182 2512
						    </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:patyelvira@hotmail.com">patyelvira@hotmail.com</a>
							<a href="mailto:uose.iztacalco@hotmail.com">uose.iztacalco@hotmail.com</a>							
							</p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Iztapalapa</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ayuntamiento y Aldama, Barrio San Lucas, <abbr title="C&oacute;digo Postal"><br /> C.P.</abbr> 09000, Ciudad de M&eacute;xico
    &nbsp;</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Iztapalapa, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Hugo Rodríguez de la Rosa<br />
							 Coordinador Regional del Servicio de Empleo en Iztapalapa</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
								555595 8208
						    </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:u.o.empleoiztapalapa@gmail.com">u.o.empleoiztapalapa@gmail.com</a><br />
						    <a href="mailto:seiztapalapa@stps.gob.mx">seiztapalapa@stps.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Magdalena Contreras</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Luis Cabrera No. 1, Casa Popular, <abbr title="Colonia"> Col. </abbr>San Jer&oacute;nimo L&iacute;dice,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 10200, Ciudad de México</p>
    						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Magdalena Contreras, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
						<!---	 Lic. María de los Remedios Morales Sánchez<br />
							 Jefe de Unidad Delegacional de Servicio del Empleo</p> --->
							Lic. Ariadna Tatiana Serrano Díaz<br />
							Coordinadora Regional del Servicio de Empleo en Magdalena Contreras</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    551520 0795<br />
						    553574 4247</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						   <a href="secretariamco@hotmail.com">secretariamco@hotmail.com</a><br />
						   <a href="mailto:uose.magdalenacontreras@gmail.com">uose.magdalenacontreras@gmail.com</a></p>
						 </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Miguel Hidalgo</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							<!-- <strong>PROVISIONALMENTE ESTÁ UBICADA EN:</strong><br /> -->
							<!-- Xocongo No. 58 esq. con Fernando de Alva Ixtlilx&oacute;chitl 2&deg; piso, <abbr title="Colonia">Col. </abbr>Tr&aacute;nsito<abbr title="C&oacute;digo Postal">,<br /> C.P.</abbr> 06820, Ciudad de M&eacute;xico -->
							Gobernador Rafael Rebolla No. 129, <abbr title="Colonia">Col. </abbr>San Miguel Chapultepec 1 Secc, <br /> <abbr title="C&oacute;digo Postal"> C.P.</abbr> 11850, Ciudad de M&eacute;xico
    </span></span></p>	</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    <!-- Cuauhtémoc, Ciudad de México</p> -->
								Miguel Hidalgo, Ciudad de México</p> 
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 <br />
							 Coordinador Regional del Servicio de Empleo en Miguel Hidalgo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    555271 4713<br />
						    555386 8476</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <!--<a href="mailto:semiguel@stps.gob.mx">semiguel@stps.gob.mx</a><br /> -->
							<a href="mailto:uosemh@gmail.com">uosemh@gmail.com</a><br />
						    <!--<a href="mailto:miguelclaro1@hotmail.com">miguelclaro1@hotmail.com</a> -->
						    </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Dirección General de Empleo</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calz. San Antonio Abad No. 32, <abbr title="Colonia">Col. </abbr>Tr&aacute;nsito, Deleg. Cuauhtémoc,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 06820.
    </span></span></p>	</div>
					<!---<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cuauhtémoc, Ciudad de México</p> 
							   
							     
						</div>--->
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							Lic. Oscar Hugo Ortíz Milán<br />
							 Director General de Empleo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						       555709 3233  <br />
                                Ext. 3001
                            </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:hortizm@cdmx.gob.mx">hortizm@cdmx.gob.mx</a>
							<a href="mailto:dge.styfe@gmail.com">dge.styfe@gmail.com</a>
							</p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Milpa Alta</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Jalisco esq. Yucat&aacute;n s/n, <abbr title="Colonia">Col. </abbr>Villa Milpa Alta,<br /><abbr title="C&oacute;digo Postal"> C.P.</abbr> 12000, Ciudad de M&eacute;xico</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Milpa Alta, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Luis Pérez<br />
							 Coordinador Regional del Servicio de Empleo en Milpa Alta</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    558891 2525<br />
						    555862 3150 Ext. 1009</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:uosemal.coord@gmail.com">uosemal.coord@gmail.com</a><br />
						    <a href="mailto:semilpa@stps.gob.mx">semilpa@stps.gob.mx</a>
							<a href="mailto:09perezlu@gmail.com">09perezlu@gmail.com</a>
							</p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Tláhuac</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Nicol&aacute;s Bravo y Cuitl&aacute;huac, Barrio La Asunci&oacute;n <span><br /><abbr title="C&oacute;digo Postal">C.P.</abbr> 13000, Ciudad de México</span>
    &nbsp;</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tláhuac, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 <!-- Lic. Irma Aguayo Rendón<br /> -->
							 Sandra Bocardo Esparza<br /> 
							 Coordinadora Regional del Servicio de Empleo en Tláhuac</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    553000 2100 Ext. 1810, 1811<br />
						    555842 6123<br />
						    555842 1731</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />						   
						    <a href="mailto:uose.tlh@gmail.com">uose.tlh@gmail.com</a><br />
						    <a href="mailto:boesbuc785@gmail.com">boesbuc785@gmail.com</a>
						    </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Tlalpan</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Periférico <span>Sur e </span>Insurgentes, Villa Ol&iacute;mpica, <abbr title="C&oacute;digo Postal">C.P.</abbr> 14020, Ciudad de México</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tlalpan, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Juan Luis Cortés López<br />
							 Coordinador Regional del Servicio de Empleo en Tlalpan</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    553000 2100 Ext. 1820, 1821<br />
						    555528 5502<br />
						    555665 5712</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:udse.tlalpan@gmail.com">udse.tlalpan@gmail.com</a><br />
						    <a href="mailto:setlalpan@stps.gob.mx">setlalpan@stps.gob.mx</a>
							<a href="mailto:uose.tlanpan@gmail.com">uose.tlanpan@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Venustiano Carranza</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Francisco del Paso y Troncoso No. 219, <abbr title="Colonia">Col. </abbr>Jard&iacute;n Balbuena,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 15900, Ciudad de M&eacute;xico</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Venustiano Carranza, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Lic. Laura María Evangelista Cortés<br />
							 Coordinador Regional del Servicio de Empleo en Venustiano Carranza</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">55 57686450</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+525557686450" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span"></span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:sevenus@stps.gob.mx">sevenus@stps.gob.mx</a><br />
						    <a href="mailto:uosevca.coord@gmail.com">uosevca.coord@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro ciudaddemexico">
		        <h4><small>Xochimilco</small></h4>
		        </div>
			
			<div class="panel panel-default filtro ciudaddemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Durazno y Ejido, <abbr title="Colonia">Col.</abbr> San Jos&eacute; de las Peritas,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 16010, Ciudad de México</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Xochimilco, Ciudad de México</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							 Fátima Berenice Amezcua Martínez<br />
							 Coordinadora Regional del Servicio de Empleo en Xochimilco</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    553000 2100 Ext. 1840, 1841<br />
						    555676 0465<br />
						    555641 7065</p>
						    555641 7065</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />						    
						    <a href="mailto:semilco@stps.gob.mx">semilco@stps.gob.mx</a><br />
						    <a href="mailto:unidad.xoch@gmail.com">unidad.xoch@gmail.com</a>
						    </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro durango">
		        <h4>Entidad - <small>Durango</small></h4>
			</div>
			
			<div class="panel panel-default filtro durango">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Lic. Alberto Terrones B. No. 107, <abbr title="Colonia">Col. </abbr>Victoria de Durango Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 34000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Durango, Durango.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Ing. Gerardo Alvarado Solórsano</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (618) 81 02 623</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526188102623" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
							<a href="mailto:gerardo.alvarados@durango.gob.mx">gerardo.alvarados@durango.gob.mx</a>
						  <br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro durango">
		        <h4><small>Gómez Palacio</small></h4>
			</div>
			
			<div class="panel panel-default filtro durango">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
				<!-- 		Ju&aacute;rez No. 334, <abbr title="Colonia">Col. </abbr>G&oacute;mez Palacio Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 35000. -->	
    									
    						Santiago Lavín # 273, <abbr title="Colonia">Col. </abbr>Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 35000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Gómez Palacio, Durango.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Alejandra Vázquez Hidalgo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (871) 71 52 123</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528717152123" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:ale_vazhi@hotmail.com">ale_vazhi@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro durango">
		        <h4><small>Lerdo</small></h4>
			</div>
			
			<div class="panel panel-default filtro durango">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Chihuahua No. 14 OTE, frente al Parque Victoria, <abbr title="Colonia">Col. </abbr>Ciudad Lerdo Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 35150.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Lerdo, Durango.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Elizabeth Martínez Sánchez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (871) 74 42 968</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528717259646" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:bolsatrabajo_lerdo@hotmail.com">bolsatrabajo_lerdo@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guanajuato">
		        <h4>Entidad - <small>Guanajuato</small></h4>
			</div>
			
			<div class="panel panel-default filtro guanajuato">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Galarza No. 88, <abbr title="Colonia">Col. </abbr>Guanajuato Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 36000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Guanajuato, Guanajuato.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Ing. Marco Antonio Morales García</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (473) 73 2 49 43
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:mamoralesg@guanajuato.gob.mx">mamoralesg@guanajuato.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guanajuato">
		        <h4><small>Celaya</small></h4>
			</div>
			
			<div class="panel panel-default filtro guanajuato">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Pipila esq. Ignacio Camargo 1er. piso, Edificio de Gobierno s/n, <abbr title="Colonia">Col. </abbr>Celaya Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 38000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Celaya, Guanajuato.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Abraham Francisco Cruz Guerrero</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (461) 61 2 65 95
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:acruzg@guanajuato.gob.mx">acruzg@guanajuato.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guanajuato">
		        <h4><small>Guanajuato</small></h4>
			</div>
			
			<div class="panel panel-default filtro guanajuato">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Galarza No. 88, <abbr title="Colonia">Col. </abbr>Guanajuato Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 36000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Guanajuato, Guanajuato.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Guillermo Escalera García</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (473) 73 4 08 21
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:gescalerag@guanajuato.gob.mx">gescalerag@guanajuato.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guanajuato">
		        <h4><small>Irapuato</small></h4>
			</div>
			
			<div class="panel panel-default filtro guanajuato">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />  
							Centro de Gobierno  Vialidad interior sobre Av. Siglo XXI # 412, Predios Los Sauces,<br />
							<!--Paseo de las Fresas No. 74, <abbr title="Colonia">Col. </abbr>Irapuato,--->
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 36547.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Irapuato, Guanajuato.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Javier Banda Samaguey</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						  01 (462) 607 45 45<br />
						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:jbandas@guanajuato.gob.mx">jbandas@guanajuato.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guanajuato">
		        <h4><small>León</small></h4>
			</div>
			
			<div class="panel panel-default filtro guanajuato">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Boulevard Delta No. 201, <abbr title="Colonia">Col. </abbr>San José de Santa Julia,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 37545.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    León, Guanajuato.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    <!-- Martha Verónica Velázquez Castro</p> -->
								Lic. Rogelio Villa Ortiz</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (477) 148 12 72
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <!-- <a href="mailto:mvelazquezc@guanajuato.gob.mx">mvelazquezc@guanajuato.gob.mx</a><br /></p> -->
							<a href="mailto:rvilla@guanajuato.gob.mx">rvilla@guanajuato.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guanajuato">
		        <h4><small>Salamanca</small></h4>
			</div>
			
			<div class="panel panel-default filtro guanajuato">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Morelos No. 402, <abbr title="Colonia">Col. </abbr>Salamanca,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 36760.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Salamanca, Guanajuato.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    José Esteban Pérez Rodríguez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (464) 64 7 76 75
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:jperez@guanajuato.gob.mx">jperez@guanajuato.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->

			<div class="page-header filtro guanajuato">
		        <h4><small>San José Iturbide</small></h4>
			</div>
			
			<div class="panel panel-default filtro guanajuato">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />  
							Morelos No. 15-A, <abbr title="Colonia">Colonia </abbr>Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 37980.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San José Iturbide, Guanajuato.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. David Audeves Gamba</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (014) 19 8 05 38
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:dgamba@guanajuato.gob.mx">dgamba@guanajuato.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guerrero">
		        <h4>Entidad - <small>Guerrero</small></h4>
		        <h4><small>Chilpancingo</small></h4>
			</div>
			
			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Dr. Galo Sober&oacute;n y Parra No. 1, <abbr title="Colonia">Col. </abbr>Chilpancingo de los Bravo Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 39000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Chilpancingo de los Bravo, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 							L a V de 8:30 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Jesús Orbe Torres</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (014) 47 14 040
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:seegro_director@stps.gob.mx">seegro_director@stps.gob.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guerrero">
		        <h4><small>Acapulco</small></h4>
			</div>
			
			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Laurel No. 3, <abbr title="Colonia">Col. </abbr>El Roble, <abbr title="C&oacute;digo Postal">C.P.</abbr> 39640.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Acapulco de Juárez, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 							L a V de 8:30 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Adrián Deloya Álvarez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (744) 487 32 26
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:bolsadetrabajoacapulco@yahoo.com.mx">bolsadetrabajoacapulco@yahoo.com.mx</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guerrero">
		        <h4><small>Chilpancingo de los Bravo</small></h4>
			</div>
			
			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Dr. Galo Sober&oacute;n y Parra No. 1, <abbr title="Colonia">Col. </abbr>Chilpancingo de los Bravo Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 39000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Chilpancingo de los Bravo, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 							L a V de 8:30 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lourdes Tapia Noyola</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (747) 47 223 46
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:urcentro@hotmail.com">urcentro@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guerrero">
		        <h4><small>Iguala</small></h4>
			</div>
			
			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Zapata No. 16 2do piso, <abbr title="Colonia">Col. </abbr>Iguala de la Independencia Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 40000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Iguala de la Independencia, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 8:30 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Edgar Eduardo Peralta Jiménez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (733) 332 04 32
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:uri_sne@hotmail.com">uri_sne@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guerrero">
		        <h4><small>Zihuatanejo</small></h4>
			</div>
			
			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Morelos s/n,(A un costado del Centro Social Municipal), <abbr title="Colonia">Col. </abbr>Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 40890.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Zihuatanejo de Azueta, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 <!-- L a V de 9 a 16:30 hrs.</p>	 -->
								 L a V de 8:30 a 16:00 hrs</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    <!-- Octavio Álvarez Campos</p> -->
								Lic. Gerardo Ramírez Suazo<br />
							 Coordinador de módulo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (755) 544 89 02
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:urzihuatanejo@hotmail.com">urzihuatanejo@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guerrero">
		        <h4><small>Técpan de Galeana</small></h4>
			</div>
			
			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Demetrio Ramos No. 6, (Frente al Zócalo a espaldas de la Catedral), <abbr title="Colonia">Col. </abbr>Centro, T&eacute;cpan de Galeana,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 40900.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Técpan de Galeana, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:00 hrs</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Juan Maya Obe</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (742) 100 25 71</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527421090250" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:juanm560625@live.com">juanm560625@live.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->

			<div class="page-header filtro guerrero">
		        <h4><small>Ciudad Altamirano</small></h4>
			</div>

			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							L&aacute;zaro C&aacute;rdenas No. 1012, Altos 34,  <abbr title="Colonia">Col. </abbr>Centro Poniente,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 40660.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Pungarabato, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Angel Alfredo Santamaria Orrostieta</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (767) 672 00 45</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:angalfsantamaria@hotmail.com">angalfsantamaria@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guerrero">
		        <h4><small>Ometepec</small></h4>
			</div>
			
			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Cuauht&eacute;moc No. 59 Depto 2, <abbr title="Colonia">Col. </abbr>Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 41700.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Ometepec, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Magdalis Jiménez Añorve</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (741) 412 05 35</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:urometepec@hotmail.com">urometepec@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guerrero">
		        <h4><small>Taxco</small></h4>
			</div>
			
			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Plateros No. 295, 3er. piso, <abbr title="Colonia">Col. </abbr>Barrio Pedro Mart&iacute;n,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 40290.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Taxco de Alarcón, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 8:30 a 16:00 hrs</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Antonio Navarro García</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (762) 62 237 14</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:sne_taxco@hotmail.com">sne_taxco@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro guerrero">
		        <h4><small>Tlapa</small></h4>
			</div>
			
			<div class="panel panel-default filtro guerrero">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Comonfort No. 68, <abbr title="Colonia">Col. </abbr>Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 41304.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tlapa de Comonfort, Guerrero.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Anahí Reyes Tapia</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (757) 476 09 58</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:urtlapa@hotmail.com">urtlapa@hotmail.com</a><br /></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro hidalgo">
		        <h4>Entidad - <small>Hidalgo</small></h4>
		        <h4><small>Pachuca</small></h4>
			</div>
			
			<div class="panel panel-default filtro hidalgo">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Luis Donaldo Colosio No. 216, <abbr title="Colonia">Col. </abbr>Arboledas <br />de San Javier,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 42084.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Pachuca de Soto, Hidalgo.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Guadalupe Sarai Marín Hernández</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						   01 (771) 71 78 000 <br />Ext. 2791</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						       <a href="mailto:sarai.marin@hidalgo.gob.mx">sarai.marin@hidalgo.gob.mx</a>
                                                        </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro hidalgo">
		        <h4><small>Tula de Allende</small></h4>
			</div>
			
			<div class="panel panel-default filtro hidalgo">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Plaza del Nacionalismo S/N (Parque Infantil la Tortuga), <abbr title="Colonia">Col. </abbr> Centro, <span><abbr title="C&oacute;digo Postal">C.P.</abbr> 42800</span></p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tula de Allende, Hidalgo.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Norma Olguín Moreno<br />
							    Coordinadora</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						               <span>01 (773) 7320496</span><br />
                                                               <span>01 (773) 1003399</span><br /> 
                                                          </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:snehuregionaltula@hidalgo.gob.mx">snehuregionaltula@hidalgo.gob.mx</a><br />
						    <a href="mailto:normaom@hotmail.es">normaom@hotmail.es</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro jalisco">
			<h4>Entidad - <small>Jalisco</small></h4>
		        <h4><small>Guadalajara</small></h4>
			</div>
			
			<div class="panel panel-default filtro jalisco">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Paseo Degollado N&deg;. 54 Plaza Tapatia <abbr title="Colonia">Col. </abbr> Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 44100.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Guadalajara, Jalisco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Gilberto Ortega Valdés<br />
							    Director General del SNE Jalisco</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                           01(33) 36 68 18 00<br/>
						           Ext. 31348
						         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:gilberto.ortega@jalisco.gob.mx">gilberto.ortega@jalisco.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro jalisco">
		        <h4><small>Guadalajara</small></h4>
			</div>
			
			<div class="panel panel-default filtro jalisco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Paseo Degollado N&deg;. 54 Plaza Tapatia <abbr title="Colonia">Col. </abbr> Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 44100.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Guadalajara, Jalisco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Patricia Mestas Torres<br />
							    Directora de Vinculación Laboral</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01(33) 36681681<br/>
						    36681800 Ext. 31306</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:patricia.mestas@jalisco.gob.mx">patricia.mestas@jalisco.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro jalisco">
		        <h4><small>Arandas</small></h4>
			</div>
			
			<div class="panel panel-default filtro jalisco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Francisco Medina Ascencio No. 469, <abbr title="Colonia">Col. </abbr>Santa B&aacute;rbara,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 47185.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Arandas, Jalisco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Karely Lara Armenta</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (348)783 10 40<br/>
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:snejuarandas@gmail.com">snejuarandas@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro jalisco">
		        <h4><small>Autlán</small></h4>
			</div>
			
			<div class="panel panel-default filtro jalisco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Pedro Moreno No. 168, <abbr title="Colonia">Col. </abbr>Centro Autl&aacute;n de Navarro.<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 48900.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Autlán de Navarro, Jalisco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Jorge Luis Villaseñor Rojas</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (317) 3811127 Ext. 104</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:sneautlan@gmail.com">sneautlan@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro jalisco">
		        <h4><small>Ciudad Guzm&aacute;n</small></h4>
			</div>
			
			<div class="panel panel-default filtro jalisco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br /> 
							Hermenegildo Galena No. 424, <abbr title="Colonia">Col. </abbr> Constituyentes, Cd. Guzm&aacute;n,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 49088.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Zapotlán el Grande, Jalisco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30  hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Juan Carlos Ramírez Villa</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (341) 412 85 75</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:snej.urciudadguzman@gmail.com">snej.urciudadguzman@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro jalisco">
		        <h4><small>Lagos de Moreno</small></h4>
			</div>
			
			<div class="panel panel-default filtro jalisco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Lic. Primo de Verdada y Ramos No. 174, <abbr title="Colonia">Col. </abbr> Centro
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 47400.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Lagos de Moreno, Jalisco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 8:30 a 16:30 hrs</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Erika López X.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (474) 742 61 06</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:erilopez624@gmail.com">erilopez624@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro jalisco">
		        <h4><small>Jamay</small></h4>
			</div>
			
			<div class="panel panel-default filtro jalisco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Guerrero No. 64, <abbr title="Colonia">Col. </abbr>Jamay Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 47900.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Jamay, Jalisco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Alejandro Mendoza Jiménez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (392) 9242060 ext. 111</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:snejocotlan@gmail.com">snejocotlan@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro jalisco">
		        <h4><small>Tlaquepaque</small></h4>
			</div>
			
			<div class="panel panel-default filtro jalisco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Marcelino Garc&iacute;a Barrag&aacute;n No. 285, <abbr title="Colonia">Col. </abbr>Tlaquepaque Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 45500.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Pedro Tlaquepaque, Jalisco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Maria Teresa Chávez Sánchez</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                         01 33 36 35 26 28
						         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:empleotlaquepaque@gmail.com">empleotlaquepaque@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro jalisco">
		        <h4><small>Puerto Vallarta</small></h4>
			</div>
			
			<div class="panel panel-default filtro jalisco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Francisco Medina Ascencio Km. 8.5 s/n, <abbr title="Colonia">Col. </abbr>Puerto Vallarta Centro,
                                                       <abbr title="C&oacute;digo Postal">C.P.</abbr> 48300.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Puerto Vallarta, Jalisco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:30 a 16:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Carlos Eduardo Caire Herrera</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (332) 221 22 00<br/>
                                                          01 (322) 221 21 61</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:ccaireherrera@hotmail.com">ccaireherrera@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
					
			<div class="page-header filtro estadodemexico">
		        <h4>Entidad - <small>Estado de México</small></h4>
		        <h4><small>Toluca</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Rafael M. Hidalgo No. 301, piso 4, <abbr title="Colonia">Col. </abbr>Cuauht&eacute;moc,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 50130.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Toluca, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. José Antonio Corona Yurrieta</p>
							    <p>Director General de Empleo y Productividad</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    	01 (722) 276-09-00 Ext. 74774
						    </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:josea.corona@edomex.gob.mx">josea.corona@edomex.gob.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro estadodemexico">
		        <h4><small>Atlacomulco</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ingeniero Luis Galindo Ruiz No. 312, Centro de Servicios Administrativos(CROSA), Edif &quot;F&quot; <br />
    Planta Baja, <abbr title="Colonia">Col. </abbr>Isidro Fabela,    <abbr title="C&oacute;digo Postal">C.P.</abbr> 50450.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Atlacomulco, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Dionisio Moreno Gil</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						   01 (712) 122-86-04, 124-83-93 y 120-11-85</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:ore_atlacomulco180507@hotmail.com">ore_atlacomulco180507@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro estadodemexico">
		        <h4><small>Ecatepec</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Nicol&aacute;s Bravo s/n, <abbr title="Colonia">Col. </abbr>La Mora,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 55030.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Ecatepec de Morelos, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Gregorio Gerardo Osorio Gómez</p>
							    <p>Jefe de la Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 
						    <p>01 (55) 57-70-96-14 y 51-16-79-07</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:regecate2@hotmail.com">regecate2@hotmail.com</a><br />
						   
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro estadodemexico">
		        <h4><small>Ixtapaluca</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Municipio Libre s/n, <abbr title="Colonia">Col. </abbr>Ixtapaluca Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 56150</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Ixtapaluca, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Lic. Verónica Wendy Aguilar Galicia</p>
							   <p>Jefa de la Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p>01 (55) 26-06-73-61 y 59-72-35-88</p> 
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:oreixtapaluca@hotmail.com">oreixtapaluca@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro estadodemexico">
		        <h4><small>Naucalpan</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Ju&aacute;rez No. 39, <abbr title="Colonia">Col. </abbr>El Mirador, Palacio Municipal edificio &quot;B&quot;
    						1er. Piso,    <abbr title="C&oacute;digo Postal">C.P.</abbr> 53050.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Naucalpan de Juárez, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    N/D</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (55) 53-71-84-00 Ext. 2320 y 2322.</span>
						</div>
							<p><strong>Correo electr&oacute;nico</strong><br />
						<div class="col-sm-6">
						    <a href="mailto:osnenaucalpan@hotmail.com">osnenaucalpan@hotmail.com</a><br />
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro estadodemexico">
		        <h4><small>Nezahualc&oacute;yotl</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Moneda No. 5, <abbr title="Colonia">Col. </abbr>Metropolitana Primera Secci&oacute;n,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 57730.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Nezahualcóyotl, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    José Robledo Carreón</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						   01(55) 51-12-80-54 y 51-12-80-56</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:oficinaregionalneza@yahoo.com.mx">oficinaregionalneza@yahoo.com.mx</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro estadodemexico">
		        <h4><small>Tejupilco</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Santa Cecilia No. 40, Oficina No. 6, Ex Bodega IMPECSA, <abbr title="Colonia">Col. </abbr>México 68,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 51400.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tejupilco, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Jorge Jerónimo Avilés</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01(724) 267-54-31</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:oretejupilco@hotmail.com">oretejupilco@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro estadodemexico">
		        <h4><small>Tlalnepantla</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Hidalgo No. 132;  <abbr title="Colonia">Col. </abbr>La Romana,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 54030.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tlalnepantla de Baz, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Cecilia Carolina Landeta Adrián</p>
							    <p>Jefa de la Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p>01(55) 53-19-08-63 y 53-83-31-35.</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:osnetlalnepantlastps@hotmail.com">osnetlalnepantlastps@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro estadodemexico">
		        <h4><small>Toluca</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ramón Corona No. 201, esq. Ignacio López Rayón, <abbr title="Colonia">Col. </abbr>Francisco Murguía<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 50130.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Toluca, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    María de Jesús Gayosso Macias</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						   01(722) 213-33-30 y 213-11-57.<br /></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:dgps@sneedomex.gob.mx">oretoluca@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro estadodemexico">
		        <h4><small>Valle de Bravo</small></h4>
			</div>
			
			<div class="panel panel-default filtro estadodemexico">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Rinconada San Vicente s/n, <abbr title="Colonia">Col. </abbr>Santa Mar&iacute;a Ahuacatlan,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 51200.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Valle de Bravo, Estado de México.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Sonia López Cabrales</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 017 2626211</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:sonialopezcabrales@hotmail.com">sonialopezcabrales@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro michoacan">
		        <h4>Entidad - <small>Michoacán</small></h4>
		        <h4><small>Morelia</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. L&aacute;zaro C&aacute;rdenas No. 866, Col. Ventura Puente, <span><abbr title="C&oacute;digo Postal">C.P.</abbr> 58020.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Morelia, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Pedro Gustavo Valenzuela Cantellano<br />
							    Director del Servicio Nacional de Empleo Michoacán</p> 
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                           <p>
                                                             <span>(443) 113-93-00</span><br />
															 <span>(443) 113 45 00</span><br />
                                                           </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
                                                         <a href="mailto:dirsnemichoacan@gmail.com">dirsnemichoacan@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro michoacan">
		        <h4><small>Morelia</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading">Vinculación Laboral</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. L&aacute;zaro C&aacute;rdenas No. 866, Col. Ventura Puente, <span><abbr title="C&oacute;digo Postal">C.P.</abbr> 58020.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Morelia, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Psic. Yeny San Pablo Ramírez<br />
							    Responsable de Vinculación Laboral </p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          <p>
                                   <span>(443) 113-93-00</span><br />
								   <span>(443) 113 45 00</span><br />
                                  </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:yensanpablo@gmail.com">yensanpablo@gmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
						
			<div class="page-header filtro michoacan">
		        <h4><small>Oficina Abriendo Espacios</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Perif&eacute;rico Paseo de la Rep&uacute;blica N&deg;. 2888-A Fracc. La Huerta, <abbr title="C&oacute;digo Postal">C.P.</abbr> 58080.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Morelia, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Psic. Enrique Urbina Ortega<br />
							    Responsable de la Oficina de Abriendo Espacios</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">(443) 113-93-00</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524433532600" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">(443) 113-45-00</span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:enrique-urbina@hotmail.com">enrique-urbina@hotmail.com</a></p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<!-- div class="page-header filtro michoacan">
		        <h4><small>Cd. Hidalgo</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Altos Del Mercado Municipal s/n, <abbr title="Colonia">Col. </abbr>Ciudad Hidalgo Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 61100.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Hidalgo, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    ADRIÁN HUERTA VALDESPINO<br />
							    Responsable del Módulo del SNE Cd. Hidalgo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <p>
                                                             <span>01 (786) 154 21 11</span><br />
                                                             <span>01 (786) 154 08 35</span><br />
                                                    </p>
						    
						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:empleo_cdhidalgo@sedeco.michoacan.gob.mx">empleo_cdhidalgo@sedeco.michoacan.gob.mx</a>
                                                    <a href="mailto:snelapiedad@hotmail.com">snelapiedad@hotmail.com</a>
                                                        </p>    
						</div>
					</div>
				</div>
			</div-->
			
			<div class="page-header filtro michoacan">
		        <h4><small>La Piedad</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Tulipanes No. 87,<abbr title="Colonia">Col. </abbr> Obrera,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 59370.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    La Piedad, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Lic. Jessica Alejandra Ramírez Acevedo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						         <span>(352) 145 69 32</span><br />
                                                         <span>(352) 154 60 11</span><br />
                                                         </p>
						    
						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:empleo_lapiedad@sedeco.michoacan.gob.mx">empleo_lapiedad@sedeco.michoacan.gob.mx</a>
                                                    <a href="mailto:snelapiedad@hotmail.com">snelapiedad@hotmail.com</a><br />
                                                   <!--<a href= "sara91.jrra@gmail.com">sara91.jrra@gmail.com</a> -->
                                                        </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro michoacan">
		        <h4><small>L&aacute;zaro C&aacute;rdenas</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Circuito Tepalcatepec # 74, <abbr title="Colonia">Col. </abbr>Primer Sector de Fideicomiso,<abbr title="C&oacute;digo Postal">C.P.</abbr> 60953.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Lázaro Cárdenas, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Marco Antonio González García</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						         <span>(753) 131 42 15</span><br />
                                                         <!-- <span>01 (753) 537 43 08</span><br /> -->
                                                         </p>
						    
						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:gmarcoantonio2015@gmail.com">gmarcoantonio2015@gmail.com</a>
                                                    <a href="mailto:lic_glez@hotmail.com">lic_glez@hotmail.com</a>
                                                        </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro michoacan">
		        <h4><small>P&aacute;tzcuaro</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ahumada No. 13 int.1,<abbr title="Colonia">Col.</abbr> Centro,C.P. 61600</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Pátzcuaro, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Eduardo Sánchez Arreola<br />
							    Responsable de la Unidad Regional</p>    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          <span>(434) 132 45 24</span><br />
						          <span>(434) 207 32 18</span> 
                                                         </p>
						    					    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:ofi.snepatzcuaro@gmail.com">ofi.snepatzcuaro@gmail.com</a>
                                                    <!-- <a href="mailto:alfredovalle.sne@gmail.com">alfredovalle.sne@gmail.com</a> -->
													<!-- <a href="mailto:temoc@gmail.com">temoc@gmail.com</a> -->
                                                        </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro michoacan">
		        <h4><small>Uruapan</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							<!-- Av. Juarez No. 71, <abbr title="Colonia">Col. </abbr>Uruapan Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 6000.</p> -->
							Av. Juarez No. 71, <abbr title="Colonia">Col. </abbr>Uruapan Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 6000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Uruapan, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Lic. Itzel López Estrada<br />
							    Responsable de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                             <span>(452) 150 45 41</span><br />
                                                             <!--<span>01 (452) 528 59 50</span><br /> -->
						         </p>   
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:empleo_uruapan@michoacan.gob.mx">empleo_uruapan@michoacan.gob.mx</a>
                                                    <a href="mailto:sneuruapan@gmail.com">sneuruapan@gmail.com</a>
                                                        </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro michoacan">
		        <h4><small>Zamora</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Padre Cabadas Sur esquina Justo Sierra Oriente,<abbr title="Colonia">Col. </abbr>Jardines de Catedral,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 59600.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Zamora, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    <!-- LIC. ARTURO ESQUIVEL NUÑEZ<br /> -->
								Lic. Rosalba Diego Huipe<br />
							    Responsable de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						             <span>(351) 273 54 68</span><br />
                                                             <!--<span>01 (351) 515 73 29</span><br /> -->
    						         </p>
						    
						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:snezamora2020@gmail.com">snezamora2020@gmail.com</a>
                                                    <!-- <a href="mailto:arturoesquiveln5@gmail.com">arturoesquiveln5@gmail.com</a> -->
													<!-- <a href="mailto:jaescoo11@gmail.com">jaescoo11@gmail.com</a> -->
                                                        </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro michoacan">
		        <h4><small>Zit&aacute;cuaro</small></h4>
			</div>
			
			<div class="panel panel-default filtro michoacan">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Cuauht&eacute;moc Oriente No. 19, <abbr title="Colonia">Col. </abbr>Zit&aacute;cuaro Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 61500.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Zitácuaro, Michoacán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    <!-- ABEL CÁRDENAS<br /> -->
								Abel Cárdenas<br />
							    Responsable de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						            <span>(715) 112 12 76</span><br />
						            <span>(715) 126 06 66</span>                                            
                                                         </p>
						   
                                                                                                         
						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						    <a href="mailto:snezitacuaro@gmail.com ">snezitacuaro@gmail.com </a>
                                                    <a href="mailto:Cardenas840@gmail.com">Cardenas840@gmail.com</a>
                                                        </p>
						</div>
					</div>
				</div>
			</div><!-- end panel -->
				
			<div class="page-header filtro morelos">
		        <h4>Entidad - <small>Morelos</small></h4>
		        <h4><small>Cuernavaca</small></h4>
			</div>
			
			<div class="panel panel-default filtro morelos">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Dr. Gustavo G&oacute;mez Azcarate s/n, esq. Av. Domingo Diez <abbr title="Colonia">Col. </abbr>Lomas de la Selva,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 62270.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cuernavaca, Morelos.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    <!-- Emmanuel Alexis Ayala Gutiérrez<br> -->
							    <!-- Director General del SNE</p> -->
								 Lic. Marcela Hernández Salgado<br>
							    Encargada de Despacho</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 77 73110701</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527773110701" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br>
						    01 77 73111273</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <!-- <a href="mailto:snemor_direccion@stps.gob.mx">snemor_direccion@stps.gob.mx</a></p>   -->
						  <a href="mailto:marcela.hernandez@morelos.gob.mx">marcela.hernandez@morelos.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro morelos">
		        <h4><small>Cuautla</small></h4>
			</div>
			
			<div class="panel panel-default filtro morelos">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							TEMPORALMENTE EN ICATMOR<br />
							Plantel Anenecuilco, Carretera Cuautla-Ayala, km. 3, Anenecuilco, Ayala, Morelos</p>
							<!-- <abbr title="Colonia">Col. </abbr>Centro,<br /><abbr title="C&oacute;digo Postal"> C.P.</abbr> 62740, Cuautla, Mor.</p> -->
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cuautla, Morelos.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Adriana Noem&iacute; Col&iacute;n Lima<br>
							    Coordinadora</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (735) 3545980</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527353545980" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>					    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:snemor_cuautla@stps.gob.mx">snemor_cuautla@stps.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro morelos">
		        <h4><small>Jojutla</small></h4>
			</div>
			
			<div class="panel panel-default filtro morelos">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							TEMPORALMENTE<br />
							<!-- Boulevard L&aacute;zaro C&aacute;rdenas No. 405, <abbr title="Colonia"> Col.</abbr>Cuauht&eacute;moc<br /><abbr title="C&oacute;digo Postal"> C.P.</abbr> 62901.</p> -->
							Calle Riva Palacio # 124,<abbr title="Colonia"> Col.</abbr>Centro Jojutla<br />, Morelos</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Jojutla, Morelos.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. José Ma. Altamirano Román</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />N/D
							 </p>
						    <!-- <span class="skype_c2c_print_container notranslate">01 (734) 3440722</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527343440722" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>-->
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <!-- <a href="mailto:urjojutla_coordreg@stps.gob.mx">urjojutla_coordreg@stps.gob.mx</a></p>   -->
						  <a href="mailto:jose.altamirano@morelos.gob.mx">jose.altamirano@morelos.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro nayarit">
			<h4>Entidad - <small>Nayarit</small></h4>
		        <h4><small>Tepic</small></h4>
			</div>
			
			<div class="panel panel-default filtro nayarit">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Quer&eacute;taro Esquina Con Morelos No. 35, <abbr title="Colonia">Col. </abbr>Tepic Centro,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 63000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tepic, Nayarit.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 							 L a V de 8:00 a 16:00 hrs.</p>		
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							  Lic. Abril Fernanda Dufor López</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    (311) 2-11-84-11 (directo)<br /><br />
						    </p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:abrildufour@nayarit.gob.mx">abrildufour@nayarit.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro nayarit">
		        <h4><small>Mezcales, Bahía de Banderas </small></h4>
			</div>
			
			<div class="panel panel-default filtro nayarit">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Carretera Federal 200 Tepic-Puerto Vallarta No. 256 Norte,<abbr title="Colonia">Col. </abbr> Las Parotas<abbr title="C&oacute;digo Postal">C.P.</abbr> 63735.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Mezcales, Bahía de Banderas, Nayarit.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 							 L a V de 8:00 a 16:00 hrs.</p>		
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							  L.A.E. Leonardo Cadena Sánchez</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    (329) 6-88-59-66 (directo)<br /><br />
						    </p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  		<a href="mailto:leonardocadena.sne@gmail.com">leonardocadena.sne@gmail.com</a>
						  		<a href="mailto:snebadeba@gmail.com">snebadeba@gmail.com</a>
						  	</p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			
									
			<div class="page-header filtro nuevoleon">
		        <h4>Entidad - <small>Nuevo León</small></h4>
		        <h4><small>Monterey</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Torre Administrativa, Washington No. 2000, <abbr title="Colonia"> Col.</abbr> Obrera.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Monterrey, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Gerardo Lozano Osuna<br />
							    Director</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (81) 20332567</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528120332567" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (81) 20332567</span></span></span></span></p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:gerardo.lozano@nuevoleon.gob.mx">gerardo.lozano@nuevoleon.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro nuevoleon">
		        <h4><small>Monterrey</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Pabell&oacute;n Ciudadano 3er. Piso, Washington No. 2000, <abbr title="Colonia"> Col.</abbr> Obrera, <abbr title="C&oacute;digo Postal">C.P.</abbr> 64010.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Monterrey, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 17 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Diana Laura González<br />
							    Coordinadora</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <!--<span class="skype_c2c_print_container notranslate">01 (81) 20332405</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528120332405" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (81) 20332405</span></span></span></span><br>-->
    						<!--<span class="skype_c2c_print_container notranslate">01 (81) 22220214</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528122220214" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (81) 22220214</span></span></span></span> <br>-->
    						<!--<span><span class="skype_c2c_print_container notranslate">01 (81) 22220215</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528122220215" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (81) 22220215</span></span></span></span></span></p>-->    						
    						<span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="#" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">(81) 20330016 </span></span></span></span></span>
    						<span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="#" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">(81) 20330017 </span></span></span></span></span>
    						<span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="#" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">(81) 20330018</span></span></span></span></span>
    						<span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="#" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">(81) 20330019</span></span></span></span></span>
    						<span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="#" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">(81) 20330020</span></span></span></span></span>
    						<span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="#" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">(81) 20330021</span></span></span></span></span>
    						<span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="#" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">(81) 20330022</span></span></span></span></span>
    						<span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="#" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">(81) 20330023</span></span></span></span></span>
    						</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:dianalaura.gonzalez@nuevoleon.gob.mx">dianalaura.gonzalez@nuevoleon.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro nuevoleon">
		        <h4><small>Apodaca</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Zaragoza entre Hidalgo y Ju&aacute;rez, <abbr title="Colonia">Col. </abbr>Centro,<br /><abbr title="C&oacute;digo Postal"> C.P.</abbr> 66600.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Apodaca, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 17 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Maira Estrada <br />
							    Jefa de Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (81) 20 20 29 11<br />
						    01 (81) 20 20 29 12</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:concertador.empresas@nuevoleon.gob.mx">concertador.empresas@nuevoleon.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro nuevoleon">
		        <h4><small>Escobedo</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Madero No. 201<abbr>Nte. 3er Piso </abbr><abbr title="Colonia">Col. </abbr>Centro, <span><br /><abbr title="C&oacute;digo Postal">C.P.</abbr> 66050.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    General Escobedo, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 17 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Lic. Juan Antonio Flores<br />
							    Jefe de Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (81) 20 20 29 02 <br />
						    01 (81) 20 20 29 03</p>
						<!--	 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (81) 20202902</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528120202902" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (81) 20202902</span></span></span></span> <br>
						    20202903</p>  -->						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:concertador.empresar@nuevoleon.gob.mx">concertador.empresar@nuevoleon.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro nuevoleon">
		        <h4><small>Guadalupe</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Pablo Livas # 2408, <abbr title="Colonia">Col. </abbr>Villas 2do. Piso, <abbr title="C&oacute;digo Postal"> C.P.</abbr> 67175.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Guadalupe, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 17 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Isidro Ramírez Llamas<br />
							    Jefe de Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 022 20202906<br>
						    01 022 20202907</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:isidro_ri@yahoo.com.mx">isidro_ri@yahoo.com.mx</a><br>
						  <a href="mailto:oficinaguadalupe_sne@yahoo.com.mx">oficinaguadalupe_sne@yahoo.com.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<!-- div class="page-header filtro nuevoleon">
		        <h4><small>Linares</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Carretera a Galeana, Parque el Nogalar Km. 2.5 <abbr title="C&oacute;digo Postal"> C.P.</abbr> 6770.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Linares, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Sharon Charles Martínez<br />
							    Jefe de Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (821) 2127750</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528212127750" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (821) 2127750</span></span></span></span></p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:uolinares_admin@stps.gob.mx">uolinares_admin@stps.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div>
			
			<div class="page-header filtro nuevoleon">
		        <h4><small>San Bernabé</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Aztl&aacute;n esq Apolo <abbr title="Colonia">Col. </abbr> San Bernab&eacute; (dentro de las instalaciones del Centro Comunitario de Desarrollo Social), <abbr title="C&oacute;digo Postal"> C.P.</abbr> 64100.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Bernabé, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    C. Valdemar Cantu Medina<br />
							    Consejero de Empleo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    N/D</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:valdemar.catunm@gmail.com">valdemar.catunm@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div-->
			
			<div class="page-header filtro nuevoleon">
		        <h4><small>San Nicolás de los Garza</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Sendero Divisorio con,<abbr>Del Mirador S/N, Colonia: El Mirador San Nicolás de los Garza, N.L.</abbr><abbr title="C&oacute;digo Postal">C.P.</abbr>66422.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Nicolás de los Garza, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 17 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Johanna Itzel Soto<br />
							    Jefe de Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (81) 20202904</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528120202904" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (81) 20202904</span></span></span></span><br />
						     20202905</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:itzel.soto@outlook.com">itzel.soto@outlook.com</a><br>
						  <!--<a href="mailto:arturogarcia70@hotmail.com">arturogarcia70@hotmail.com</a></p> -->  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<!-- div class="page-header filtro nuevoleon">
		        <h4><small>San Pedro</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Corregidora s/n, esq. Independencia, <abbr title="Colonia">Col.</abbr> Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 66230.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Pedro Garza García, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Issac Reyes Salas<br />
							    Jefe de Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (81)20202909</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528120202909" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (81)20202909</span></span></span></span><br />
						    20202910</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:empleosanpedro@hotmal.com">empleosanpedro@hotmal.com</a></p>  
						</div>
					</div>
				</div>
			</div>
			
			<div class="page-header filtro nuevoleon">
		        <h4><small>Sta. Catarina</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Blvd. D&iacute;az Ordaz esq. San Francisco, <abbr title="Colonia">Col.</abbr> La Fama, <abbr title="C&oacute;digo Postal">C.P.</abbr> 66370.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Santa Catarina, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Erika Moreno<br />
							    Consejero de Empleo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    N/D</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:empleo_santacatarina@hotmail.com">empleo_santacatarina@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div>
			
			<div class="page-header filtro nuevoleon">
		        <h4><small>Sabinas Hidalgo</small></h4>
			</div>
			
			<div class="panel panel-default filtro nuevoleon">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Camino Real s/n esq. Luis T. Mireles, <br><abbr title="Colonia">Col.</abbr> Fovisste, <abbr title="C&oacute;digo Postal">C.P.</abbr> 65200.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Sabinas Hidalgo, Nuevo León.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Francisco Javier González González<br />
							    Jefe de Oficina</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01(824) 2421163</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528242421163" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01(824) 2421163</span></span></span></span></p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:programasabinas@hotmail.com">programasabinas@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div-->
			
			<div class="page-header filtro oaxaca">
			<h4>Entidad - <small>Oaxaca</small></h4>
		        <h4><small>Oaxaca</small></h4>
			</div>
			
			<div class="panel panel-default filtro oaxaca">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							<!--Ciudad Judicial, Edificio  &quot;G&quot; “Mar&iacute;a Sabina”, Nivel 4, Av. Gerardo Pandal Graff No. 1, <abbr title="Colonia"> Col. </abbr> Reyes Mantec&oacute;n,<abbr title="C&oacute;digo Postal"> C.P. </abbr>71257.</p> --->
						    Ciudad Administrativa “Benemérito de las Américas” Edificio 6, “Macedonio Alcalá” nivel 3, Carretera Internacional Oaxaca-Istmo Km. 11.5, Tlalixtac de Cabrera, Oaxaca, <abbr title="C&oacute;digo Postal"> C.P. </abbr>68270.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tlalixtac de Cabrera, Oaxaca</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a v de 9 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Grisel Valencia Sánchez<br />
							    Coordinadora del Servicio Nacional de Empleo Oaxaca</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						<!---    <span class="skype_c2c_print_container notranslate">01 (951) 501 69 00 </span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529515016900" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span> Ext. 26629 </p> ---->				    
						 	    <span class="skype_c2c_print_container notranslate">01 (951) 501 50 00 </span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+019515015000" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span> Ext. 12628 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
                                              <!--    <a href="mailto:coord.sneoax@gmail.com">coord.sneoax@gmail.com</a>   -->
                                              <a href="mailto:coord.sne@gmail.com">coord.sne@gmail.com</a>
                                                 
						</p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro oaxaca">
		        <h4><small>Oaxaca Mixteca</small></h4>
			</div>
			
			<div class="panel panel-default filtro oaxaca">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Centro Regional de Servicios Administrativos, Libramiento Norte s/n, Agencia Agua Dulce, <abbr title="C&oacute;digo Postal"> C.P. </abbr> 69005.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Heroica Ciudad de Huajuapan de León, Oaxaca.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Ing. María de la Luz Guerrero Espinosa<br />
							    Jefa de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <p><span class="skype_c2c_print_container notranslate"></span>01 (953) 688-32-60 ext. 1100 <span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529535323841" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></strong></p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:mariluguerrerosneo.mixteca@gmail.com">mariluguerrerosneo.mixteca@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro oaxaca">
		        <h4><small>Puerto Escondido (Costa)</small></h4>
			</div>
			
			<div class="panel panel-default filtro oaxaca">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							1a Sur N&deg;. 305, sector Hidalgo, <abbr title="C&oacute;digo Postal"> C.P. </abbr> 71980.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Pedro Mixtepec - Dto. 22, Oaxaca.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Daniela Kenia Porras Vazquez<br />
							    Jefa de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (954) 688 06 15</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529545822871" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:keniaporras.osne.costa@gmail.com">keniaporras.osne.costa@gmail.com</a></p> 
						  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro oaxaca">
		        <h4><small>Salina Cruz (Istmo)</small></h4>
			</div>
			
			<div class="panel panel-default filtro oaxaca">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							<!-- Av. Manuel Ávila Camacho N&deg; 200, Int. Palacio Federal, <abbr title="C&oacute;digo Postal"> C.P. </abbr> 70600.</p> -->
							Av. Ferrocarril No. 204,<abbr title="Colonia">Col. </abbr>Cuaútemoc,<abbr title="C&oacute;digo Postal"> C.P. </abbr> 70660.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Salina Cruz, Oaxaca.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. July Vanessa Ramírez Ramírez<br />
							    Jefa de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							 <!-- 01 (971) 7143272 -->
						    <span class="skype_c2c_print_container notranslate">01 (971) 720 26 04</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529711331544" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:sneo.istmo@gmail.com">sneo.istmo@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro oaxaca">
		        <h4><small>Tuxtepec (Papaloapan)</small></h4>
			</div>
			
			<div class="panel panel-default filtro oaxaca">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Vicente Guerrero No. 346, <abbr title="Colonia">Col. </abbr>Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 68300.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Juan Bautista Tuxtepec, Oaxaca.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Luz María Gutiérrez José<br />
							    Jefa de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (287) 106 34 76</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+522878752127" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:luz_gutierrez.snepapaloapan@hotmail.com">luz_gutierrez.snepapaloapan@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro oaxaca">
		        <h4><small>Valles Centrales</small></h4>
			</div>

			<div class="panel panel-default filtro oaxaca">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />

							<!-- Centro Administrativo del Poder Ejecutivo y Judicial General Porfirio D&iacute;az. &ldquo;Soldado de la Patria&rdquo;, Edificio G-2 Mar&iacute;a Sabina, Nivel 1.<abbr> </abbr>Agencia Municipal Reyes Mantec&oacute;n. <abbr title="C&oacute;digo Postal"> C.P.</abbr> 71257.</p> -->
							Ciudad Administrativa “Benemérito de las Américas” Edificio 6, “Macedonio Alcalá” nivel 3, Carretera Internacional Oaxaca-Istmo Km. 11.5, Tlalixtac de Cabrera, Oaxaca, <abbr title="C&oacute;digo Postal"> C.P.</abbr> 68270.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />

							    Tlalixtac de Cabrera, Oaxaca</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 17 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />

							    Erika Citlali Mac.Laury López<br />
							    Jefa de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 (951) 501 50 00 Ext. 12513</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+522878752127" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:erimac.sneo@gmail.com">erimac.sneo@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro puebla">
			<h4>Entidad - <small>Puebla</small></h4>
		        <h4><small>Puebla</small></h4>
			</div>
			
			<div class="panel panel-default filtro puebla">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Callej&oacute;n De La 10 Norte No. 806,<abbr title="Colonia">Col. </abbr>San Francisco,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 72000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Puebla, Puebla.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 18:00 Hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Raquel Rosalía Razo Valdés</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (222) 246 57 76<br>
							Exts. 101 y 103</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:rqlrazo@hotmail.com">rqlrazo@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
                        
                        	<div class="panel panel-default filtro puebla">
				<div class="panel-heading">Vinculación Laboral</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Centro Integral de Servicios, Edificio Norte Planta Baja. Blvd. Atlixcayotl No. 1101<abbr title="C&oacute;digo Postal">C.P.</abbr> 72000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Puebla, Puebla.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 14:00 Hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Raquel Rosalía Razo Valdés</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						           01 (222) 3-03-46-00 <br>
							   Ext. 2140</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:rqlrazo@hotmail.com">rqlrazo@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro puebla">
		        <h4><small>Tehuac&aacute;n</small></h4>
			</div>
			
			<div class="panel panel-default filtro puebla">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Carretera Federal Puebla-Tehuac&aacute;n Km. 115 No. 1629-B, <abbr title="Colonia">Col. </abbr>San Lorenzo Teotipilco,
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 75855.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tehuacán, Puebla.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Christian Mart&iacute;nez Aguilar</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (238) 380 39 30 Ext. 233</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:chrissan1785@gmail.com">chrissan1785@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro puebla">
		        <h4><small>Teziutl&aacute;n</small></h4>
			</div>
			
			<div class="panel panel-default filtro puebla">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Carretera Federal Teziutlán-Puebla s/n, Entronque con Autopista Teziutlán-Puebla, frente al Hospital General de Teziutlán (CIS)
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 73800.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Teuzitl&aacute;nn, Puebla.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Michel Cruz Aros</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    045 (231) 1752895</p>						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:pueblavinculacion3@gmail.com">pueblavinculacion3@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
						
			<div class="page-header filtro queretaro">
			<h4>Entidad - <small>Querétaro</small></h4>
		        <h4><small>Querétaro</small></h4>
			</div>
			
			<div class="panel panel-default filtro queretaro">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ezequiel Montes N&deg;. 23 Norte 1&deg; piso, Centro Hist&oacute;rico <abbr title="C&oacute;digo Postal">C.P.</abbr> 76000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Querétaro, Querétaro.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Lic. Andrea Fernanda De Alba González<br />
							   Coordinadora de Vinculación Laboral</p>  
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						      <p>
						        <!--  <span>01(442) 23-56-613</span><br /> -->
                                                         <span>(442) 23-56-667</span><br />
                                                         <span>(442) 23-56-601</span><br />
                                                      </p> 						    
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						      <a href="mailto:sneqro.vinculacion@gmail.com">sneqro.vinculacion@gmail.com</a><br />
                                                      <!-- <a href="mailto:elugoc@queretaro.gob.mx">elugoc@queretaro.gob.mx</a>--></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro queretaro">
		        <h4><small>San Juan del R&iacute;o</small></h4>
			</div>
			
			<div class="panel panel-default filtro queretaro">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle José María Pino Suárez No. 67, esquina con Calle Valentín Gómez Farías, int. 88-92 y 115, Plaza Comercial Agua Rica, <abbr title="Colonia">Col. </abbr>Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 76800.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Juan del Río, Querétaro.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    C. María Dolores Martínez González<br />
							    Coordinadora de la Unidad Operativa de SJR</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						      <p>
						         <span>(427) 272 32 29</span><br/>
                                                         <span>(427) 274 69 99</span><br/>
                                                         <span>(427) 272 35 54</span><br/>
                                                      </p> 
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:uosjrio_director@stps.gob.mx">uosjrio_director@stps.gob.mx</a><br />
						  <a href="mailto:sne.direccionsjr@gmail.com">sne.direccionsjr@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			
			
			
			
			<div class="page-header filtro queretaro">
		        <h4><small>Jalpan de Serra</small></h4>
			</div>
			
			<div class="panel panel-default filtro queretaro">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Vicente Guerrero No. 4<abbr> </abbr><abbr title="Colonia">Col. </abbr>Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 76300.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Jalpan de Sierra, Querétaro.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:30 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Maribel Cuellar Guillén<br />
							    Encargada de Módulo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">(441) 296 15 20</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524412961520" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span"></span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:snemodulojalpan@gmail.com">snemodulojalpan@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro quintanaroo">
			<h4>Entidad - <small>Quintana Roo</small></h4>
		        <h4><small>Chetumal</small></h4>
			</div>
			
			<div class="panel panel-default filtro quintanaroo">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Cristóbal Colón No. 191 Planta Alta, entre Av. Héroes y Av. 16 de Septiembre,<abbr title="Colonia">Col. </abbr>Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 77000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Othón P. Blanco, Chetumal.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Lic. David Ernesto Xec Beltrán <br />
							   Coordinador General del Servicio Estatal del Empleo y Capacitación para el Trabajo de Quintana Roo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">(983) 832-87-96</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529838324567" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
						    (983) 832-45-67<br />
						    (983) 833-06-62<br />
						   </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:seeqroo_director@stps.gob.mx">seeqroo_director@stps.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro quintanaroo">
		        <h4><small>Cancún</small></h4>
			</div>
			
			<div class="panel panel-default filtro quintanaroo">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Xcaret, SM 36 Mz. 2 Lt 2 Plaza Las Palmas Locales A-34b, planta alta. <span><abbr title="C&oacute;digo Postal">C.P.</abbr> 77505.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Benito Juárez, Cancún.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 14:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Pendiente <br />
							 </p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    (998) 8922094<br />
						    <span class="skype_c2c_print_container notranslate">(988) 8980732</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529888980732" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br /></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:cancuncoord@stps.gob.mx">cancuncoord@stps.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro quintanaroo">
		        <h4><small>Chetumal</small></h4>
			</div>
			
			<div class="panel panel-default filtro quintanaroo">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br /> 
							Calle Cristóbal Colón No. 191 Planta Baja entre Av. Heroes y Av. 16 de Septiembre,<abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 77000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Othón P. Blanco, Chetumal.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 14:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							Ing. Pedro Joaquín Moen Cano<br />
							 Coordinador de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						         <span>(983) 832 45 67</span><br />
                                                         <span>(983) 832 87 96</span><br />
                                                         <span>(983) 833 06 62</span><br />
                                                          </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:urchetumal_coordinacion@stps.gob.mx">urchetumal_coordinacion@stps.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro quintanaroo">
		        <h4><small>Playa del Carmen</small></h4>
			</div>
			
			<div class="panel panel-default filtro quintanaroo">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. 30 Norte, Mz. 96 Lt 10, Torre Atmosphera<br /> <abbr title="Colonia">Col. </abbr>Zazil-Ha,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 77720.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Solidaridad, Playa del Carmen.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 14:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Jorge Adrián Barrientos Escobedo<br />
							    Coordinador de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     (984) 8732138<br />
						    <span class="skype_c2c_print_container notranslate">(984) 8030060</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529848030060" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
   							</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						 <!---	<a href="mailto:urpcarmen_coord@stps.gob.mx">urpcarmen_coord@stps.gob.mx</a> <br /
						 <a href="mailto:jorge.a.b.e@hotmail.com">jorge.a.b.e@hotmail.com</a></p>  ---> 
						   <a href="mailto:urpcarmen_coord@stps.gob.mx">urpcarmen_coord@stps.gob.mx</a></p>
    						<p><span><a href="mailto:jorge.a.b.e@hotmail.com">jorge.a.b.e@hotmail.com</a></span></p>
						
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sanluispotosi">
		        <h4>Entidad - <small>San Luis Potos&iacute;</small></h4>
		        <h4><small>San Luis Potos&iacute;</small></h4>
			</div>
			
			<div class="panel panel-default filtro sanluispotosi">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Manuel J. Clouthier, No. 263A, local z05, Plaza Tangamanga, <abbr title="Colonia">Col.</abbr> Tangamanga. <abbr title="C&oacute;digo Postal"> C.P.</abbr> 78290.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Luis Potosí, San Luis Potosí.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Luis Alberto González Olvera<br />
							    Director del SNE en S.L.P.</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 444 8264600</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524448264600" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 444 8264600</span></span></span></span><br />
    Ext. 113</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:seeslp_director@stps.gob.mx">seeslp_director@stps.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sanluispotosi">
		        <h4><small>Cd. Valles</small></h4>
			</div>
			
			<div class="panel panel-default filtro sanluispotosi">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Pasaje María Luisa, Calle Abasolo No. 518 Altos, Locales 48, 49, 50, 51, 52 y 53, Ciudad Valles, Zona Centro,<abbr title="C&oacute;digo Postal"> C.P.</abbr> 79000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Ciudad Valles, San Luis Potosí.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Dr. Lorenzo Estrada Torres<br />
							    Responsable de la Unidad</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (481) 382 24 126<br />
    					    <span class="skype_c2c_print_container notranslate">01 (481) 3813454</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524813813454" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:drlet1964@hotmail.com">drlet1964@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sanluispotosi">
		        <h4><small>Matehuala</small></h4>
			</div>
			
			<div class="panel panel-default filtro sanluispotosi">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Palma China No.123, esq. Calle Paseo Angel Veral, <abbr title="Colonia">Col. </abbr> Santa Martha, <abbr title="C&oacute;digo Postal">C.P.</abbr> 78760.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Matehuala, San Luis Potosí.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Eduardo Lomelí de la Rosa<br />
							    Responsable de la Unidad</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 48 88877202</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524888877202" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
    <span class="skype_c2c_print_container notranslate">01 48 88877203</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524888877203" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:eduardo1945@hotmail.com">eduardo1945@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sanluispotosi">
		        <h4><small>Río Verde</small></h4>
			</div>
			
			<div class="panel panel-default filtro sanluispotosi">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 5 De Mayo, No. 107 Locales 16 y 18 (Altos), Zona Centro <span><abbr title="C&oacute;digo Postal">C.P.</abbr> 79610.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Río Verde, San Luis Potosí.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Ana Lilia Zuniga Sánchez<br />
							    Responsable de la Unidad</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 48 78720765</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524878720765" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:azusa_2422@hotmail.com">azusa_2422@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sanluispotosi">
		        <h4><small>Tamazunchale</small></h4>
			</div>
			
			<div class="panel panel-default filtro sanluispotosi">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Boulevard 20 de Noviembre No. 15, Barrio del Carmen.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tamazunchale, San Luis Potosí.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Fabio Hazel Monsiváis Medina<br />
							    Responsable de la Unidad</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 (483) 3621746</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524833621746" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:tamazunchalebt_sne@hotmail.com">tamazunchalebt_sne@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sanluispotosi">
		        <h4><small>Zona Industrial</small></h4>
			</div>
			
			<div class="panel panel-default filtro sanluispotosi">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Salvador Nava Martínez No. 50, <abbr title="Colonia">Col.</abbr> El Paseo</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Luis Potosí, San Luis Potosí.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    C. María de Lourdes Rosales Nieto<br />
							    Responsable de la Unidad</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					                01 (444) 814- 1012 Ext. 123</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:lur1773@hotmail.com">lur1773@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sinaloa">
		        <h4>Entidad - <small>Sinaloa</small></h4>
		        <h4><small>Culiacán</small></h4>
			</div>
			
			<div class="panel panel-default filtro sinaloa">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Insurgentes s/n,<abbr title="Colonia"> Col. </abbr>Palacio de Gobierno Del Estado de Sinaloa, <abbr title="C&oacute;digo Postal">C.P.</abbr> 80129.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Culiacán, Sinaloa.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 <!-- L a V de 9 a 13 hrs.</p>	 -->
								 L a V de 8 a 15 hrs.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    <!-- Luis Alfonso Martínez Moraila</p> -->
								Arturo Torres Sato</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					                 <span>01 667 7123418</span><br />
                                                         <span>01 667 7144186</span><br />
                                                         <span>01 667 7587193</span><br />
                                                          Red: 825 400</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <!-- <a href="mailto:luis.martinez@sinaloa.gob.mx">luis.martinez@sinaloa.gob.mx</a></p>   -->
						  <a href="mailto:arturo.torresato@sinaloa.gob.mx">arturo.torresato@sinaloa.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sinaloa">
		        <h4><small>Centro de Intermediaci&oacute;n Laboral (Culiac&aacute;n)</small></h4>
			</div>
			
			<div class="panel panel-default filtro sinaloa">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br /> 
							Blvd. Miguel Tamayo Espinoza de los Monteros y Blvd. Pedro Infante s/n, Planta Baja, Unidad de Servicios Estatales, Ventanilla 16 y 17, <abbr title="Colonia">Col. </abbr>Desarrollo Urbano Tres Ríos, <abbr title="C&oacute;digo Postal">C.P.</abbr> 80109.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Culiacán, Sinaloa.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 <!-- L a V de 9 a 13 hrs.</p>	 -->
								 L a V de 8 a 15 hrs.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Manuel Humberto Inzunza Inzunza</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 (667) 75 87 000</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526677587000" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
    						Ext. 2790 y 2791</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
							<!-- <a href="mailto:cil@sinaloa.gob.mx">cil@sinaloa.gob.mx</a></p>   -->
						  <a href="mailto:sne.cilsinaloa@gmail.com">sne.cilsinaloa@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sinaloa">
		        <h4><small>Guasave</small></h4>
			</div>
			
			<div class="panel panel-default filtro sinaloa">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Leyva No. 85,<abbr title="Colonia"> Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 81000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Guasave, Sinaloa.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 <!-- L a V de 9 a 13 hrs.</p>	 -->
								 L a V de 8 a 15 hrs.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Herman Rolando Montoya Beltrán</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 (687) 87 14 516</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526878714516" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <!-- <a href="mailto:empleo_guasave@prodigy.net.mx">empleo_guasave@prodigy.net.mx</a></p>   -->
						  <a href="mailto:empleo.guasave@gmail.com">empleo.guasave@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sinaloa">
		        <h4><small>Mazatl&aacute;n</small></h4>
			</div>
			
			<div class="panel panel-default filtro sinaloa">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Río Culiacán s/n,<abbr title="Colonia">Col. </abbr>Telleria, <abbr title="C&oacute;digo Postal">C.P.</abbr> 82017.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Mazatlán, Sinaloa.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 <!-- L a V de 9 a 13 hrs.</p> -->
L a v de 8 a 15 hrs.</p>								 
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Ana Marcela Félton Gorostiza</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br /> 
    					    <span class="skype_c2c_print_container notranslate">01 (668) 81 70 847 </span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526691184144" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <!-- <a href="mailto:empleo@prodigy.net.mx">empleo@prodigy.net.mx</a></p>   -->
						  <a href="mailto:marcela.felton@sinaloa.gob.mx">marcela.felton@sinaloa.gob.mx</a></p>  
						  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sinaloa">
		        <h4><small>Mochis</small></h4>
			</div>
			
			<div class="panel panel-default filtro sinaloa">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Guillermo Prieto No. 105 Sur, Edif. Paulina, Local 1,<abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 81500.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Ahome, Sinaloa.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 <!-- L a V de 9 a 13 hrs.</p>	 -->
								 L a V de 8 a 15 hrs.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    <!-- Carlos Gerardo López Castro</p> -->
							    Leticia Tamayo Ramos</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 (668) 81 70 847</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526688170847" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
    						Ext. 113</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <!-- <a href="mailto:empleo_mochis@prodigy.net.mx">empleo_mochis@prodigy.net.mx</a></p>   -->
						  <a href="mailto:leticia.tamayo@sinaloa.gob.mx">leticia.tamayo@sinaloa.gob.mx</a></p>  						  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sinaloa">
		        <h4><small>Salvador Alvarado</small></h4>
			</div>
			
			<div class="panel panel-default filtro sinaloa">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Antonio Rosales y Aquiles Serdán s/n, <abbr title="Colonia">Col. </abbr>Morelos, <abbr title="C&oacute;digo Postal">C.P.</abbr> 81460.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Salvador Alvarado, Sinaloa.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    <!-- Edith González Sánchez</p> -->
							    Yesenia López Arreola</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					                   <span>01 673 7327621</span><br />
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:empleo.guamuchil@gmail.com">empleo.guamuchil@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sonora">
			<h4>Entidad - <small>Sonora</small></h4>
		        <h4><small>Hermosillo</small></h4>
			</div>
			
			<div class="panel panel-default filtro sonora">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Centro de Gobierno, Edificio Sonora Sur, 3er Piso. Comonfort y Paseo Río Sonora.<abbr title="Colonia">Col. </abbr> <abbr title="C&oacute;digo Postal">C.P.</abbr> 83280.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Hermosillo, Sonora.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Alejandro Aurelio Elizalde Lizárraga<br />
							     Cargo<br/>
							     Subsecretario de Promoción del Empleo y Productividad y Titular del Servicio Nacional del Empleo</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					                  01 000 2120008
							</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:alejandro.elizalde@sonora.gob.mx">alejandro.elizalde@sonora.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sonora">
		        <h4><small>Cajeme</small></h4>
			</div>
			
			<div class="panel panel-default filtro sonora">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Edificio Gobierno del Estado, Primer Piso. 5 de Febrero y Prof. Montero Morales Cd. Obregón, Sonora.  <abbr title="Colonia"> </abbr><abbr title="C&oacute;digo Postal">C.P.</abbr> 85000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cajeme, Sonora.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. José Alberto Santacruz Gastelo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                          01 (664) 415 34 80   
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:jasantacruz@snesonora.gob.mx">jasantacruz@snesonora.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sonora">
		        <h4><small>Hermosillo</small></h4>
			</div>
			
			<div class="panel panel-default filtro sonora">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Centro de Gobierno Calle Ignacio Comonfort entre Av. Cultura y Paseo Río Sonora. Hermosillo, Sonora.<abbr title="Colonia"></abbr><abbr title="C&oacute;digo Postal">C.P.</abbr> 83280.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Hermosillo, Sonora.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							     Lic. María Elena Ríos Cabral <br />
                                                             Cargo<br/>
							     Coordinadora</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                             <span>01 (662) 217 31 85</span><br />
                                                             <span>01 (662) 259 61 74</span><br />
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:maria.rios@snesonora.gob.mx">maria.rios@snesonora.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sonora">
		        <h4><small>Navojoa</small></h4>
			</div>
			
			<div class="panel panel-default filtro sonora">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Avenida Hidalgo # 206-B entre Otero y Pesqueira. <abbr title="Colonia">Col. </abbr>Centro Navojoa, <br />
							<abbr title="C&oacute;digo Postal">C.P.</abbr> 85800.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Navojoa, Sonora.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Jesús Ángel Mendivil Nieblas<br />
							    Cargo<br/>
							    Coordinador</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                            <span>01 (642) 422 61 87</span><br /> 
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:amendivil@snesonora.gob.mx">amendivil@snesonora.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sonora">
		        <h4><small>Nogales</small></h4>
			</div>
			
			<div class="panel panel-default filtro sonora">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Avenida Plutarco Elías Calles 898, Local Nogales, Sonora. México. <abbr title="Colonia"></abbr> <abbr title="C&oacute;digo Postal">C.P.</abbr> 84000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Nogales, Sonora.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Gabriela Ileana León Félix <br/>
                                                            Cargo<br/>
							    Coordinadora</p>   
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                           <span>01 (631) 314 30 24</span><br /> 
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:gleon@snesonora.gob.mx">gleon@snesonora.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sonora">
		        <h4><small>San Luis R&iacute;o Colorado</small></h4>
			</div>
			
			<div class="panel panel-default filtro sonora">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							F&eacute;lix Contreras entre Calle Primera y Calle Segunda s/n, <abbr title="Colonia">Col. </abbr>Cuauht&eacute;moc, <abbr title="C&oacute;digo Postal">C.P.</abbr> 83400.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    San Luis Río Colorado, Sonora.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    María de los Angeles Moroyoqui Murrieta</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                             <span>01 (653) 535 80 70</span><br /> 
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:angelesmoroyoqui@snesonora.gob.mx">angelesmoroyoqui@snesonora.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro sonora">
		        <h4><small>Hermosillo</small></h4>
			</div>
			
			<div class="panel panel-default filtro sonora">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Centro de Gobierno, Calle I. Comonfort No. 8, <br /><abbr title="Colonia">Col. </abbr>Villa Hermosa, <abbr title="C&oacute;digo Postal">C.P.</abbr> 83280.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Hermosillo, Sonora.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Anai Amavizca Montoya</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 662 2170055</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526622170055" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 662 2170055</span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:anai-amavizcam@gmail.com">anai-amavizcam@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
		<!--	<div class="page-header filtro sonora">
		        <h4><small>Puerto Pe&ntilde;asco</small></h4>
			</div>
			
			<div class="panel panel-default filtro sonora">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Boulevard Benito Ju&aacute;rez s/n, <abbr title="Colonia">Col. </abbr> Benito Juárez. <abbr title="C&oacute;digo Postal">C.P.</abbr> 83400</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Puerto Peñasco, Sonora.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 15 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    C. Rosa Amelia Ortega<br />
							    Encargada de Módulo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 (638) 1082200</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526381082200" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (638) 1082200</span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:ramelia114@hotmail.com">ramelia114@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div>--><!-- end panel -->
			
			<div class="page-header filtro tabasco">
			<h4>Entidad - <small>Tabasco</small></h4>		       
			</div>
			
			
			<div class="page-header filtro tabasco">
		        <h4><small>Centro (Villahermosa)</small></h4>
			</div>
			
			<div class="panel panel-default filtro tabasco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Narciso Saenz No. 215, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 86000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Centro, Tabasco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Lorenzo Guzmán Vázquez</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					                  01 (993) 312 3009 <br />
							  Ext. 119<br />
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:lorenguzman39@gmail.com">lorenguzman39@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tabasco">
		        <h4><small>CHONTALPA (C&aacute;rdenas)</small></h4>
			</div>
			
			<div class="panel panel-default filtro tabasco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Lázaro Cárdenas del Río S/N entre calle Manuel Leyva y Joaquín Cuevas, <abbr title="C&oacute;digo Postal">C.P.</abbr> 86500.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cárdenas, Tabasco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Gloria Maria Montejo Cano</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    01 (937) 372 6995</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:montejocano@hotmail.com">montejocano@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			

			
			<div class="page-header filtro tabasco">
		        <h4><small>TEAPA</small></h4>
			</div>
			
			<div class="panel panel-default filtro tabasco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Juan N. Fernández No. 134, <abbr title="Colonia">Col. </abbr>Centro, <br /><abbr title="C&oacute;digo Postal">C.P.</abbr> 86800</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Teapa, Tabasco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Roberto Castillo Díaz</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    01 (932) 322 2489</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:castillo.jr4@gmail.com">castillo.jr4@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			

			<div class="page-header filtro tabasco">
		        <h4><small>TENOSIQUE</small></h4>
			</div>
			
			<div class="panel panel-default filtro tabasco">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Camino a San Juan No. 2, <abbr title="Colonia">Col. </abbr>Estaci&oacute;n Nueva, <br /><abbr title="C&oacute;digo Postal">C.P.</abbr> 86900</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tenosique, Tabasco.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Lic. Alfonso Manuel Pérez López</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    01 (934) 342 1776</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:ampl1975@hotmail.com">ampl1975@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->			
			
			<div class="page-header filtro tamaulipas">
			<h4>Entidad - <small>Tamaulipas</small></h4>
		        <h4><small>Ciudad Victoria</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Libramiento Naciones Unidas con Blvd. Praxedis Balboa, Centro de Oficinas Gubernamentales, Torre Bicentenario, Piso 17, Cd. Victoria, Tam., <abbr title="Colonia">Col. </abbr>Parque Bicentenario, <br /><abbr title="C&oacute;digo Postal">C.P.</abbr> 87083.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Victoria, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br /> 
							  Lic. Alexandro Perales Huerta<br> 
							  Director del Servicio Estatal de Empleo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                             <span>01 (834) 318-80-00</span><br />
                                                             Ext. 43858
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:planeacion@tamaulipas.gob.mx">planeacion@tamaulipas.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tamaulipas">
		        <h4><small>Altamira</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Hidalgo esq. con Quintero No. 408, Zona Centro,<abbr title="Colonia">Col. </abbr>Altamira Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 89600.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Altamira, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Ing. Félix Joaquín Montes Galarza</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					                     <span>01 (833) 162-09-17</span><br />
                                                             <span>01 (833) 162-09-31</span><br />
                                                             <span>01 (833) 162-09-61</span><br />
                                                             </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:joaquin.montes@tam.gob.mx">joaquin.montes@tam.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tamaulipas">
		        <h4><small>Cd. Victoria</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Libramiento Naciones Unidas con Blvd. Praxedis Balboa, Centro de Oficinas Gubernamentales, Torre Bicentenario, Planta Baja, <abbr title="Colonia">Col. </abbr>Parque Bicentenario, <abbr title="C&oacute;digo Postal">C.P.</abbr> 87083.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Cd. Victoria, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Jesús Alberto Izaguirre Ramos</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					                     <span>01 (834) 107-80-00 Ext. 43189 y 43199</span><br />
                                                             </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:osne.victoria@tam.gob.mx">osne.victoria@tam.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tamaulipas">
		        <h4><small>Mante</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Pedro José Méndez No. 208 Ote. entre Manuel González y Ocampo, <abbr title="Colonia">Col. </abbr>Mante, <abbr title="C&oacute;digo Postal">C.P.</abbr> 89800.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    El Mante, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. José Roberto Ugalde Gallegos</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                             <span>01 (831) 232-89-17</span><br />
                                                             <span>01 (831) 232-36-91</span><br />
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:osne.mante@tam.gob.mx">osne.mante@tam.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tamaulipas">
		        <h4><small>Matamoros</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ave. Lauro Villar No. 1101 esq. Francisco Villa, Plaza Río, Local 6, <abbr title="Colonia">Col. </abbr>Fracc. Las Palmas, H., <abbr title="C&oacute;digo Postal">C.P.</abbr> 87420.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Matamoros, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. José de Jesús Quintana Alba</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                             <span>01 (868) 812-02-01</span><br />
                                                             <span>01 (868) 812-24-40</span><br />
                                                             <span>01 (868) 813-63-67</span><br />
                                                             <span>01 (868) 813-63-88</span><br />
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:jesus.quintana@tam.gob.mx">jesus.quintana@tam.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tamaulipas">
		        <h4><small>Nuevo Laredo</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Héroes de Nácataz esq. con Reynosa, anexo al Centro Cívico, Zona Centro, <abbr title="Colonia">Col. </abbr>Nuevo Laredo, <abbr title="C&oacute;digo Postal">C.P.</abbr> 88000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Nuevo Laredo, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Georgina Gordillo Torrijos</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                           <span>01 (867) 715-64-24</span><br />
                                                           <span>01 (867) 715-75-07</span><br />
                                                           <span>01 (867) 715-75-08</span><br />  
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:ginagordillot@hotmail.com.mx">ginagordillot@hotmail.com.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tamaulipas">
		        <h4><small>Tampico</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Faja de Oro No. 101, entre Veracruz y Ejercito Mexicano, <abbr title="Colonia">Col. </abbr>Minerva, <abbr title="C&oacute;digo Postal">C.P.</abbr> 89120.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tampico, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    C. Lidia Isela Núñez López</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                           <span>01 (833) 217-61-94</span><br />
                                                           <span>01 (833) 217-61-95</span><br />
                                                           <span>01 (833) 217-61-98</span><br />  
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:lidianunez2803@gmail.com">lidianunez2803@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tamaulipas">
		        <h4><small>Reynosa</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Blvd. Hidalgo No. 310 esq. Rayón,<abbr title="Colonia">Col. </abbr>Bella Vista,  <abbr title="C&oacute;digo Postal">C.P.</abbr> 88600.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Reynosa, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    C. Alfonso González Gutiérrez</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					                   <span>01 (899) 925-88-60</span><br />
                                                           <span>01 (899) 925-88-65</span><br />
                                                           <span>01 (899) 925-88-86</span><br /> 
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:alfonso.gonzalez@tam.gob.mx">alfonso.gonzalez@tam.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tamaulipas">
		        <h4><small>Madero</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Linares No. 101-B, entre Ave. 1° de Mayo y Niños Héroes, Zona Centro,  <abbr title="Colonia">Col. </abbr>Madero, <abbr title="C&oacute;digo Postal">C.P.</abbr> 89490.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Ciudad Madero, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							     N D </p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 (833) 305-24-43</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528333052443" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 (833) 305-24-44</span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:empleo_madero@hotmail.com">empleo_madero@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
		<!-- <div class="page-header filtro tamaulipas">
		        <h4><small>Rio Bravo</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Plaza Hidalgo 2 s/n, Edificio de la Presidencia Municipal, Zona Centro,  <abbr title="Colonia">Col. </abbr>Río Bravo, <abbr title="C&oacute;digo Postal">C.P.</abbr> 88900.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Rio Bravo, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    C.P. Rodolfo Enrique González López</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					                   <span>01 (899) 934-00-11</span><br />
                                                           <span>01 (899) 934-00-43</span><br />
                                                           <span>01 (899) 934-74-88 Ext. 145</span><br /> 
                                                         </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:empleoreynosa@tamaulipas.gob.mx">empleoreynosa@tamaulipas.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div>--><!-- end panel -->
			
			<div class="page-header filtro tamaulipas">
		        <h4><small>Tampico Norte</small></h4>
			</div>
			
			<div class="panel panel-default filtro tamaulipas">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Josefa Ortiz de Domínguez, entre 1ª. y 2ª. Avenida, (Instalaciones de la Delegación Municipal, Zona Norte de Tampico), <abbr title="Colonia">Col. </abbr>Laguna de la Puerta, <abbr title="C&oacute;digo Postal">C.P.</abbr> 89310.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tampico, Tamaulipas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 13 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Jessica Jazmin Vazquez Lara</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
                                                             <span>01 (833) 132-07-84</span><br /> 
    					                 </p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:sne_norte@hotmail.com">sne_norte@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tlaxcala">
			<h4>Entidad - <small>Tlaxcala</small></h4>
		        <h4><small>Tlaxcala</small></h4>
			</div>
			
			<div class="panel panel-default filtro tlaxcala">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ex Rancho La Aguanaja s/n <abbr title="Colonia">Col. </abbr> Centro. <abbr title="C&oacute;digo Postal">C.P.</abbr> 90600.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Apetatitlán de Antonio Carvajal, Tlaxcala.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 14:00 y de 15:00 a 17:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Yaneli Pérez Vázquez<br />
							    Directora</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
    					    <span class="skype_c2c_print_container notranslate">01 (246) 464 17 15</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+522464641715" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:seetlax_director@stps.gob.mx">seetlax_director@stps.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tlaxcala">
		        <h4><small>Apizaco</small></h4>
			</div>
			
			<div class="panel panel-default filtro tlaxcala">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Fco. I. Madero <span>No. 1107</span> <span>altos, Edif. ESMON, 1er piso, <abbr title="Colonia">Col. </abbr> Centro. <abbr title="C&oacute;digo Postal">C.P.</abbr> 90300.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Apizaco, Tlaxcala.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 14:00 y de 15:00 a 17:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. María José Reygadas Nava<br />
							    Coordinadora</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">01 241 41 8 01 77</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+522414180177" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
							<span class="skype_c2c_print_container notranslate">01 241 41 8 01 78</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+522414180178" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:mariajose_2322@hotmail.com">mariajose_2322@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tlaxcala">
		        <h4><small>Calpulalpan</small></h4>
			</div>
			
			<div class="panel panel-default filtro tlaxcala">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 5 de Mayo No. 27 Altos. <abbr title="Colonia">Col. </abbr> Centro. <abbr title="C&oacute;digo Postal">C.P.</abbr> 90200</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Calpulalpan, Tlaxcala.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 14:00 y de 15:00 a 17:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Denise Monserrat Montano Juárez<br />
							    Coordinadora de Módulo</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">01 (749) 91 8 01 05</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527499180105" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:moncemjuarez@hotmail.com">moncemjuarez@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tlaxcala">
		        <h4><small>Tlaxcala</small></h4>
			</div>
			
			<div class="panel panel-default filtro tlaxcala">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Leonarda G&oacute;mez No. 60, Planta Baja, <abbr title="Colonia"> Col. </abbr>Acxotla del R&iacute;o, <abbr title="C&oacute;digo Postal">C.P.</abbr> 90160.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Totolac, Tlaxcala.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 14:00 y de 15:00 a 17:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Roberto Luna Cesma<br />
							    Coordinador</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">01 246 4 662316</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+522464662316 " onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span><br />
							<span class="skype_c2c_print_container notranslate">01 246 4 662634</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+522464662634" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:sfa2005.2008@gmail.com">sfa2005.2008@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro tlaxcala">
		        <h4><small>Zacatelco</small></h4>
			</div>
			
			<div class="panel panel-default filtro tlaxcala">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Independencia <span>No. 1, Planta Baja del Palacio Municipal,</span> Secci&oacute;n Primera, <abbr title="Colonia"> Col. </abbr> Centro. <abbr title="C&oacute;digo Postal">C.P.</abbr> 90740.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Zacatelco, Tlaxcala.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 14:00 y de 15:00 a 17:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Jorge Ramos Pinillo<br />
							    Coordinador de la Unidad Regional</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">01 (246) 49 7 48 62</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+522464974862 " onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:ramospi_1005@live.com.mx">ramospi_1005@live.com.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro veracruz">
		        <h4>Entidad - <small>Veracruz</small></h4>
		        <h4><small>Xalapa</small></h4>
			</div>
			
			<div class="panel panel-default filtro veracruz">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Manuel Ávila Camacho No. 191, <abbr title="Colonia">Col. </abbr>Guardia, <abbr title="C&oacute;digo Postal">C.P.</abbr> 91020.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Xalapa, Veracruz.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 9:00 a 15:00 y de 16:00 a 18:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Esmeralda Solano Landa</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							(228) 842 19 00<br />
    						        Ext. 3152</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:direccion.snexalapa@gmail.com">direccion.snexalapa@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro veracruz">
		        <h4><small>Coatzacoalcos</small></h4>
			</div>
			
			<div class="panel panel-default filtro veracruz">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Guti&eacute;rrez Zamora No. 405 altos, <abbr title="Colonia">Col. </abbr>Centro Coatzacoalcos, <abbr title="C&oacute;digo Postal">C.P.</abbr> 96400.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Coatzacoalcos, Veracruz.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 9:00 a 15:00 y de 16:00 a 18:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Beatriz Rosas Martinez</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">(921) 21 21 942</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+529212121942" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:beatriz.rosas.martinez@outlook.com">beatriz.rosas.martinez@outlook.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro veracruz">
		        <h4><small>C&oacute;rdoba</small></h4>
			</div>
			
			<div class="panel panel-default filtro veracruz">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. 3 No. 104, Int. 310, Edificio Plaza Jardín, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 94500.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    C&oacute;rdoba, Veracruz.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 15:00 y de 16:00 a 18:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Vicente Romero Ortíz</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">271 7145727</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+522717145727" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:coordinacioncordoba04@gmail.com">coordinacioncordoba04@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro veracruz">
		        <h4><small>P&aacute;nuco</small></h4>
			</div>
			
			<div class="panel panel-default filtro veracruz">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Melchor Ocampo No. 103, <abbr title="Colonia">Col. </abbr>Centro, P&aacute;nuco, <abbr title="C&oacute;digo Postal">C.P.</abbr> 93990.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    P&aacute;nuco, Veracruz.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 9:00 a 15:00 y de 16:00 a 18:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Francisco Guerrero Ostos</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">(846) 266 23 44</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528462662344" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:paco_simple@hotmail.com">paco_simple@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro veracruz">
		        <h4><small>Poza Rica</small></h4>
			</div>
			
			<div class="panel panel-default filtro veracruz">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Josefa Ortiz de Domínguez s/n, <abbr title="Colonia">Col. </abbr>Obras Sociales, <abbr title="C&oacute;digo Postal">C.P.</abbr> 93240.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Poza Rica, Veracruz.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 9:00 a 15:00 y de 16:00 a 18:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Arturo Juaréz Suaréz</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">(782) 82 33 245</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527828233245" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:art-juarez@hotmail.com">art-juarez@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro veracruz">
		        <h4><small>Xalapa</small></h4>
			</div>
			
			<div class="panel panel-default filtro veracruz">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Manuel Ávila Camacho No. 191, <abbr title="Colonia">Col. </abbr>Francisco Ferrer Guardia, <abbr title="C&oacute;digo Postal">C.P.</abbr> 91020.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Xalapa, Veracruz.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 9:00 a 15:00 hrs y de 16:00 a 18:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    C.P. Rafael Ramos Sánchez</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							 (228) 842 19 00 <br />
    						        Ext. 3133</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:coordinacionsnexalapa@gmail.com">coordinacionsnexalapa@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro veracruz">
		        <h4><small>Boca del Río</small></h4>
			</div>
			
			<div class="panel panel-default filtro veracruz">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Tiburón No. 76 entre Ballena y Salmón, Fracc. Costa de Oro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 94299.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Boca del Río, Veracruz.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								L a V de 9:00 a 15:00 y de 16:00 a 18:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Carmen Yepez Ramirez</p>    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							(229) 93 11 951 <br />
    						</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:snever.bocario@gmail.com">snever.bocario@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
		<!--	<div class="page-header filtro veracruz">
		        <h4><small>Papantla</small></h4>
			</div>
			
			<div class="panel panel-default filtro veracruz">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Reforma 100 dentro del Ayuntamiento de Papantla s/n, <abbr title="Colonia">Col. </abbr>Papantla Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 93400.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Papantla, Veracruz.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Griselda Vega Fernandez</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">01 784 8423837</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527848423837" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 784 8423837</span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:papantla_snever@hotmail.com">papantla_snever@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div>--><!-- end panel -->
			
		<!--	<div class="page-header filtro veracruz">
		        <h4><small>Tuxpan</small></h4>
			</div>
			
			<div class="panel panel-default filtro veracruz">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Ju&aacute;rez No. 20, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 92800.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tuxpan, Veracruz.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 14 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Patricia Natalia Benitez Mar</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">01 783 8351336</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527838351336" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 783 8351336</span></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:seetuxpam@hotmail.com">seetuxpam@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div> --><!-- end panel -->
			
			<div class="page-header filtro yucatan">
			<h4>Entidad - <small>Yucatán</small></h4>
		        <h4><small>Mérida</small></h4>
			</div>
			
			<div class="panel panel-default filtro yucatan">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 66 No. 438 entre 49 y 53, <abbr title="Colonia">Col. </abbr>M&eacute;rida Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 97000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Mérida, Yucatán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Irais Barón Zermeño</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
									01 (999) 611 87 60<br />
							Ext. 43537</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:irais.baron@yucatan.gob.mx">irais.baron@yucatan.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro yucatan">
		        <h4><small>Mérida</small></h4>
			</div>
			
			<div class="panel panel-default filtro yucatan">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 66 No. 438 entre 49 y 53, <abbr title="Colonia">Col. </abbr>M&eacute;rida Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 97000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Mérida, Yucatán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Francisco Javier Sabido Góngora</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							01 (999) 611 87 60<br />
							Ext. 43519</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:francisco.sabido@yucatan.gob.mx">francisco.sabido@yucatan.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro yucatan">
		        <h4><small>Tic&uacute;l</small></h4>
			</div>
			
			<div class="panel panel-default filtro yucatan">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 25 No. 204 entre 28 y 30, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 97860.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Ticúl, Yucatán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    José Samuel Chuc López</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							01 (997) 972 20 98 y 01 (997) 972 19 51</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:jose_samuelchuc@hotmail.com">jose_samuelchuc@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro yucatan">
		        <h4><small>Valladolid</small></h4>
			</div>
			
			<div class="panel panel-default filtro yucatan">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 40 No. 179 F entre 31 y 33, <abbr title="Colonia">Col. </abbr>Valladolid Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 97780.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Valladolid, Yucatán.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 8:00 a 15:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Teresita de Jesús Domínguez Mendoza</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							01 (985) 856 21 14 y 01 (985) 856 07 92</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:teresita.dominguez@yucatan.gob.mx">teresita.dominguez@yucatan.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro zacatecas">
			<h4>Entidad - <small>Zacatecas</small></h4>
		        <h4><small>Zacatecas</small></h4>
			</div>
			
			<div class="panel panel-default filtro zacatecas">
				<div class="panel-heading">Unidad Central</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Circuito Cerro del Gato s/n Edificio H, <abbr title="Colonia">Col. </abbr>Ciudad Gobierno, <abbr title="C&oacute;digo Postal">C.P.</abbr> 98160.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Zacatecas, Zacatecas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9 a 16 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							   Cliserio del Real Hernández</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							(492) 491 50 90 al 92<br />
							Ext. 17100</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:seezac_director@stps.gob.mx">seezac_director@stps.gob.mx</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro zacatecas">
		        <h4><small>Fresnillo</small></h4>
			</div>
			
			<div class="panel panel-default filtro zacatecas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Cuauht&eacute;moc No. 7, <abbr title="Colonia">Col. </abbr>Fresnillo Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 99000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Fresnillo, Zacatecas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Nubia Lizette Alvarado Ríos</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">(493) 983 74 50</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524939329177" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span>
							<br />
							(493) 983 74 53<br />
							</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:concertacion.sne.fllo@hotmail.com">concertacion.sne.fllo@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro zacatecas">
		        <h4><small>R&iacute;o Grande</small></h4>
			</div>
			
			<div class="panel panel-default filtro zacatecas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Hidalgo No. 37, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 98420.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    R&iacute;o Grande, Zacatecas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Mtra. Elizabeth Hernández Calderón</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">(493) 982 78 43</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524989827843" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:betty_hdezcalderon@hotmail.com">betty_hdezcalderon@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro zacatecas">
		        <h4><small>Sombrerete</small></h4>
			</div>
			
			<div class="panel panel-default filtro zacatecas">
				<div class="panel-heading">Unidad Regional</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Francisco Javier Mina No. 32-B, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 99100.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Sombrerete, Zacatecas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Lic. Bianca Alejandra Ramos Amador</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">(433) 935 55 20</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524339355520" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:filly-ramos06@hotmail.com">filly-ramos06@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<!-- <div class="page-header filtro zacatecas">
		        <h4><small>Jalpa</small></h4>
			</div> -->
			
			<!-- <div class="panel panel-default filtro zacatecas">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Plaza Ar&eacute;chiga, Edificio Pal y Pal 2do Piso s/n, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 99600.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Jalpa, Zacatecas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    N/D</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">01 463 9555557</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524639555557" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:snejalpa@gmail.com">snejalpa@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div> --><!-- end panel -->
			
			<div class="page-header filtro zacatecas">
		        <h4><small>Ojocaliente</small></h4>
			</div>
			
			<div class="panel panel-default filtro zacatecas">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Plaza Principal No. 4, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 98710.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Ojocaliente, Zacatecas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    David Octavio Tiscareño Alonso</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">01 458 9440087</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524589440087" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:sneregion10@gmail.com">sneregion10@gmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header filtro zacatecas">
		        <h4><small>Pinos</small></h4>
			</div>
			
			<div class="panel panel-default filtro zacatecas">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Plaza Principal Lado Poniente s/n, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 98920.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Pinos, Zacatecas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Javier Sánchez Aguilar</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">(496) 864 51 17</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524968645117" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:sanchez_javier.a@hotmail.com">sanchez_javier.a@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->

			<div class="page-header filtro zacatecas">
		        <h4><small>Tabasco</small></h4>
			</div>

			<div class="panel panel-default filtro zacatecas">
				<div class="panel-heading">Módulo</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Portal Hidalgo s/n, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 99630.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Municipio o Delegaci&oacute;n</strong><br />
							    Tabasco, Zacatecas.</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V de 9:00 a 16:00 hrs.</p>	
						</div>
						<div class="col-sm-6">
							 <p><strong>Responsable</strong><br />
							    Luis Rogelio Gómez Martínez</p>
							    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
							<span class="skype_c2c_print_container notranslate">(463) 957 00 74</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+524639570074" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /></span></span></span></p>
						</div>
						<div class="col-sm-6">
							<p><strong>Correo electr&oacute;nico</strong><br />
						  <a href="mailto:rogeliogomez_74@hotmail.com">rogeliogomez_74@hotmail.com</a></p>  
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			</div>
</div>
<p>&nbsp;</p>
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

		<!-- agregado por Benjamin para funcionalidad  08/02/2016 -->
		<script type="text/javascript">
			function showHide(valor){

				$(".filtro").not("."+valor).hide();
				$("."+valor).show();
			};
			$(document).ready(function() {
				$("select").change(function () {

					$(this).find("option:selected").each(function () {
						valor = $(this).attr("value");
						showHide(valor);
						$("#map").hide();

					}).change();
				});
			});
		</script>
		<!-- fin script -->
	</jsp:body>
</t:publicTemplate>
