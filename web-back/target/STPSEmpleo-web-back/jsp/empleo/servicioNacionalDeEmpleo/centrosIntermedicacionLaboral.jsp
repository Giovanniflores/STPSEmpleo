<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Centros de Intermediaci&oacute;n Laboral del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Los Centros de Intermediaci&oacute;n Laboral (CIL) son un programa del Servicio Nacional del Empleo (SNE) que tiene como objetivo proporcionar herramientas de apoyo a las personas en su b&uacute;squeda y contacto con las oportunidades de empleo.
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
          <li class="active">Centros de Intermediación Laboral</li>
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
			<h2 class="titulosh2">Centros de Intermediación Laboral</h2>
		
	        <p>Los Centros de Intermediaci&oacute;n Laboral (CIL) son un programa del Servicio Nacional del Empleo (SNE) que tiene como objetivo proporcionar herramientas de apoyo a las personas en su b&uacute;squeda y contacto con las oportunidades de empleo.</p>
<p>Los CIL son m&oacute;dulos de servicio ubicados en las principales oficinas del SNE equipados con computadoras con acceso a Internet y correo electr&oacute;nico, impresoras, fotocopiadoras, fax y tel&eacute;fono para el apoyo integral de los procesos de vinculaci&oacute;n laboral. Funcionan bajo un enfoque de autoayuda, donde los mismos interesados realizan directamente sus procesos de b&uacute;squeda y contacto con las ofertas de empleo, empleando los recursos inform&aacute;ticos disponibles.</p>
<p>Los CIL te ofrecen los siguientes servicios gratuitos:</p>
<ul class="list-group-contenidos">
    <li>Acceso al Portal del Empleo <a href="https://www.empleo.gob.mx">www.empleo.gob.mx</a></li>
    <li>Consulta de otras bolsas electr&oacute;nicas de trabajo locales</li>
    <li>Correo electr&oacute;nico</li>
    <li>Comunicaci&oacute;n telef&oacute;nica y fax</li>
    <li>Servicios de impresi&oacute;n y fotocopiado</li>
    <li>Asesor&iacute;a t&eacute;cnica</li>
</ul>
<p>Para tener acceso a los servicios que ofrecen los CIL se requiere que la persona interesada acuda personalmente a las oficinas del SNE donde se dispone de este servicio. Actualmente el SNE cuenta con 38 CIL ubicados en los siguientes puntos:</p>

				
	                <div class="page-header">
		        <h4>Entidad - <small>Aguascalientes</small></h4>
		        <h4><small>Aguascalientes</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Av. Aguascalientes Sur No. 3214, Fraccionamiento Prados del Sur. <abbr title="Colonia"></abbr><br />
                                                         <abbr title="C&oacute;digo Postal"></abbr></p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						             01 (449) 971-94-01 al 04 <br/>
						             Ext. 103
                                                          </p> 
                                                </div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 8:00 a 15:00 hrs.</p>	
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Baja California</small></h4>
		        <h4><small>Mexicali</small></h4>
			</div>
			
                        <div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							  Calle Calafia esquina con Pioneros No. 1100, Centro Cívico.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						            01 (686) 555 4990  <br/>
						            01 (612) 555 4991
                                                          </p> 
                                                </div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								  L a V 8:00 a 15:00 hrs.</p>	
						</div>
					</div>
				</div>
			</div><!-- end panel -->
                        
                        
                        <div class="page-header">
		        <h4>Entidad - <small>Baja California Sur</small></h4>
		        <h4><small>La Paz</small></h4>
			</div>
			
                        <div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							   Ignacio Allende # 1486, E/Melitón Albañez y Ensenada, Fraccionamiento Perla,<abbr title="C&oacute;digo Postal">C.P.</abbr>23000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						            01 (612) 12-227-13 <br/>
						            01 (612) 12-242-48 ext. 212
                                                          </p> 
                                                </div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								  L a V 8:00 a 15:00 hrs.</p>	
						</div>
					</div>
				</div>
			</div><!-- end panel -->
                        
			<div class="page-header">
		        <h4>Entidad - <small>Campeche</small></h4>
		        <h4><small>Campeche</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Calle 53 por Calle 8 No.2, <abbr title="Colonia">Col. </abbr>Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 24000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (981) 816 46 92 <br/>
	                                                 </p>
                                            	</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Coahuila</small></h4>
		        <h4><small>Saltillo</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Victoria 608 Poniente Planta Baja, <abbr title="Colonia">Col. </abbr>Centro,<br />
    <abbr title="C&oacute;digo Postal">C.P.</abbr> 25000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     01 (844) 412 89 85<br />
							01 (844) 410 34 50<br /></p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 14:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Chiapas</small></h4>
		        <h4><small>Tuxtla Guti&eacute;rrez</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    3ª. Poniente Sur No. 170, entre Avenida Central y 1ª. Sur, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 29000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (961) 12 14 692<br />
                                                         </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4><small>Comit&aacute;n</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Calle Central Poniente, Lic. Benito Ju&aacute;rez No. 22, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 30000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (963) 632 00 82</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4><small>San Crist&oacute;bal</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							    Ignacio Allende No. 11, Planta Baja <abbr title="Colonia">Col. </abbr>Barrio La Merced, <abbr title="C&oacute;digo Postal">C.P.</abbr> 29240.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (967) 678 06 82</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Chihuahua</small></h4>
		        <h4><small>Chihuahua</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Ignacio Allende No. 901, Centro de Justicia Laboral, Planta Alta, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal"></abbr></p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (614) 429 33 00</br>
						             Ext. 24754
                                                         </p> 
                                                        
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 14:00 hrs.</p>	
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Ciudad de México</small></h4>
		        <h4><small>Cuauht&eacute;moc</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Serapio Rend&oacute;n No. 76, <abbr title="Colonia">Col. </abbr>San Rafael, <abbr title="C&oacute;digo Postal">C.P.</abbr> 06470.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 55 15001300</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+525515001300" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 55 15001300</span></span></span></span><br />
    						Ext.1407</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V 09:00 a 13:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4><small>Iztapalapa</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ayuntamiento y Aldama s/n, <abbr title="Colonia">Col. </abbr>Barrio San Lucas, <abbr title="C&oacute;digo Postal">C.P.</abbr> 09000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						   <!-- <span class="skype_c2c_print_container notranslate">01 55 26360011</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+525526360011" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 55 26360011</span></span></span></span><br />
    						Ext.1407- -->
    						01 (55) 12 72 18 94
    						</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Durango</small></h4>
		        <h4><small>Durango</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Alberto Terrones No. 107,  <abbr title="Colonia">Colonia. </abbr>F&aacute;tima, <abbr title="C&oacute;digo Postal">C.P.</abbr> 34060.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (618) 8 10 24 96<br />
                            </p>
						    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V 8:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			
			<div class="page-header">
		        <h4>Entidad - <small>Guanajuato</small></h4>
		        <h4><small>León</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Boulevard Delta No. 201 <abbr title="Colonia">Col. </abbr>San Jos&eacute; de Santa Julia, <abbr title="C&oacute;digo Postal">C.P.</abbr> 37545.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 477 148 1272<br />
                            </p>
						    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:30 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Guerrero</small></h4>
		        <h4><small>Acapulco</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Laurel No. 3, <abbr title="Colonia">Col. </abbr>El Roble, <abbr title="C&oacute;digo Postal">C.P.</abbr> 39690.</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (744) 488 33 86<br />
                            </p> 
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
						<!--	 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 16:00 hrs.</p>	-->
 								 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:30 a 16:00 hrs.</p>
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4><small>Chilpancingo</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Galo Soberon y Parra No. 1, <abbr title="Colonia">Col. </abbr>. Centro, <abbr title="C&oacute;digo Postal"> C.P.</abbr> 39000 </p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						     01 (747) 472 69 16<br />
                             01 (747) 472 90 35<br />

						    </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:30 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Hidalgo</small></h4>
		        <h4><small>Pachuca</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Boulevard Luis Donaldo Colosio No. 216, <abbr title="Colonia">Col. </abbr>Arboledas de San Javier,</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 771 7178000</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527717178000" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 771 7178000</span></span></span></span><br />
    Ext. 2734</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4><small>Tula de Allende</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Plaza Constituci&oacute;n No. 17, <abbr title="Colonia">Col. </abbr>Centro,</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 773 7320496</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527737320496" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 773 7320496</span></span></span></span></p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Jalisco</small></h4>
		        <h4><small>Guadalajara</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Paseo Degollado No. 54, en Plaza Tapat&iacute;a, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 44100.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						   <!-- <span class="skype_c2c_print_container notranslate">01 33 36681681</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+523336681681" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 33 36681681</span></span></span></span></p>-->
						 	   01 (33) 36 68 16 81 ext. 31329<br /></p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:30 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Estado de M&eacute;xico</small></h4>
		        <h4><small>Tlalnepantla</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Hidalgo # 132, <abbr title="Colonia">Col. </abbr>. La Romana, <abbr title="C&oacute;digo Postal">C.P.</abbr> 54090.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <!-- <span class="skype_c2c_print_container notranslate">01 55 53188900</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+525553188900" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 55 53188900</span></span></span></span><br />
    						Ext. 4879-->
    						01 (55) 53 83 31 35
    						</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 18:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4><small>Toluca</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Ram&oacute;n Corona Oriente No. 201 esq. Ignacio L&oacute;pez Ray&oacute;n, <abbr title="Colonia">Col. </abbr>Francisco Murgu&iacute;a.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						  <!-- <span class="skype_c2c_print_container notranslate">01 722 2133 330</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+527222133330" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 722 2133 330</span></span></span></span>-->
						  	01 (722) 213 14 09
						  </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 18:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Michoac&aacute;n</small></h4>
		        <h4><small>Morelia</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Lázaro Cárdenas No. 866,<abbr title="Colonia">Col. </abbr>Ventura Puente, <abbr title="C&oacute;digo Postal">C.P.</abbr> 58020.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						         01 (443) 353 30 81<br />
                                                         </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 17:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Morelos</small></h4>
		        <h4><small>Cuernavaca</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Dr. G&oacute;mez Azcarate s/n esq. con Domingo Diez, <abbr title="Colonia">Col. </abbr>Lomas de la Selva, <abbr title="C&oacute;digo Postal">C.P.</abbr> 62270.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 777 102 04 42<br />
                                                          01 777 311 12 73<br />
                                                         </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 14:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Nayarit</small></h4>
		        <h4><small>Tepic</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Querétaro # 35 esquina con Morelos,<abbr title="Colonia">Col. </abbr>Centro Tepic Nayarit.</p>
						</div>
						<div class="col-sm-6">
							<p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (311) 211 84 00<br />
						         	ext. 107
                            </p> 
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4>Entidad - <small>Nuevo Le&oacute;n</small></h4>
		        <h4><small>General Escobedo</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Francisco Javier Mina No. 100, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 66050.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 81 20202903</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528120202903" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 81 20202903</span></span></span></span></p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 14:00 y 16:00 a 18:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4><small>Guadalupe</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. L&aacute;zaro C&aacute;rdenas No. 500 Int. Sal&oacute;n Polivalente, <abbr title="Colonia">Col. </abbr>Vivienda Popular, <abbr title="C&oacute;digo Postal">C.P.</abbr> 67170.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 81 20202907</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528120202907" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 81 20202907</span></span></span></span></p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 15:00 y 16:00 a 18:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		        <h4><small>Monterrey</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Pino Su&aacute;rez No. 507 Norte, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 64000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <span class="skype_c2c_print_container notranslate">01 81 20202309</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+528120202309" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 81 20202309</span></span></span></span></p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 14:00 y 16:00 a 18:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Oaxaca</small></h4>
		    <h4><small>Oaxaca</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
						<!-- 	Fuerza Aérea núm. 602, <abbr title="Colonia">Col. </abbr>Reforma, <abbr title="C&oacute;digo Postal">C.P.</abbr> 68050.</p>  -->
								Fuerza Aérea núm. 602, <abbr title="Colonia">Col. </abbr>Reforma, Oaxaca de Juárez. <abbr title="C&oacute;digo Postal">C.P.</abbr>68050.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						         01 (951) 513 12 50  <br />
						    </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 16:00 hrs.</p>	
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Puebla</small></h4>
		    <h4><small>Puebla</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Centro Integral de Servicios Edificio Norte Planta Baja Blvd. Atlixc&aacute;yotl No. 1101, <abbr title="Colonia">Reserva Territorial Atlixc&aacute;yotl</abbr>, Puebla, Pue. <abbr title="C&oacute;digo Postal">C.P.</abbr> 72000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						            01 222 3034600<br />
						            Ext. 2140
                                                         </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 14:00 hrs.</p>	
						</div>
					</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Quintana Roo</small></h4>
		    <h4><small>Benito Ju&aacute;rez (Canc&uacute;n)</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Xcaret SM. 36 Mz. 2, Lt. 2, Plaza Las Palmas, Planta Alta, Locales A34-B, <abbr title="C&oacute;digo Postal">C.P.</abbr> 77500, Cancun, Municipio de Benito Juárez.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						         01 (998) 898 07 32<br />
                                                         01 (998) 892 20 94
                                                         </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
                        
                        	<div class="page-header">
		                    <h4><small>CHETUMAL</small></h4>
			       </div>
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
						<!--	<p><strong>Domicilio</strong><br />
							 Av. Carmen Ochoa de Merino No. 171, Planta Baja entre 5 de Mayo y 16 de Septiembre,<abbr title="Colonia">Col. </abbr> Centro en Chetumal, Municipio de Othón P. Blanco.</p>-->
							 <p><strong>Domicilio</strong><br />
							 Calle Cristóbal Colón No. 191, entre Av. Héroes y Av. 16 de Septiembre,<abbr title="Colonia">Col. </abbr> Centro, Cd. Chetumal, Othón P. Blanco, <abbr title="C&oacute;digo Postal">C.P.</abbr> 77000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (983) 832 45 67<br />
                                                          01 (983) 832 87 96
                                                         </p> 						    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
                        
                        <div class="page-header">
		                    <h4><small>PLAYA DEL CARMEN</small></h4>
			       </div>
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							 Av. 30 Norte. Mz. 96, Lt. 10, Edificio Atmosfera, <abbr title="Colonia">Col. </abbr>Zazil-Ha, en Playa del Carmen, Municipio de Solidaridad.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (984) 803 00 60<br />
                                                          01 (984) 873 21 28
                                                         </p> 						    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>San Luis Potos&iacute;</small></h4>
		    <h4><small>San Luis Potos&iacute;</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Manuel J. Clouthier, No. 263A, local z05, Plaza Tangamanga, <abbr title="Colonia">Col. </abbr>Tangamanga, <abbr title="C&oacute;digo Postal">C.P.</abbr> 78290.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						            01 (444) 826 46 00  <br />
						            Ext. 160
                                                         </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 14:30 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Sinaloa</small></h4>
		    <h4><small>Culiacán</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Boulevard Miguel tamayo Espinoza de los Monteros y Blvd. Pedro Infante s/n, Planta Baja Unidad de Servicios Estatales, Ventanilla 16 y 17,&nbsp; <abbr title="Colonia">Col. </abbr>Desarrollo Urbano Tres R&iacute;os, <abbr title="C&oacute;digo Postal">Culiac&aacute;n, Sinaloa, C.P.</abbr> 80109.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    <!--<span class="skype_c2c_print_container notranslate">01 667 7587000</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526677587000" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 667 7587000</span></span></span></span><br /> -->
    						01 (667) 758 70 00<br />
    						Ext. 2790 y 2656
    						</p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Sonora</small></h4>
		    <h4><small>Hermosillo</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Centro de Gobierno, Calle I. Comonfort local No. 8, entre Av. Cultura y Paseo R&iacute;o Sonora. <abbr title="C&oacute;digo Postal">C.P.</abbr> 83000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						<!-- <span class="skype_c2c_print_container notranslate">01 662 259 61 73</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526622596173" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 662 259 61 73</span></span></span></span><br />-->
						 <!--   <span class="skype_c2c_print_container notranslate">01 662 259 61 74</span><span data-ismobile="false" data-isrtl="false" data-isfreecall="false" data-numbertype="paid" data-numbertocall="+526622596174" onclick="SkypeClick2Call.MenuInjectionHandler.makeCall(this, event)" onmouseout="SkypeClick2Call.MenuInjectionHandler.hideMenu(this, event)" onmouseover="SkypeClick2Call.MenuInjectionHandler.showMenu(this, event)" tabindex="-1" dir="ltr" class="skype_c2c_container notranslate" id="skype_c2c_container"><span skypeaction="skype_dropdown" dir="ltr" class="skype_c2c_highlighting_inactive_common"><span id="non_free_num_ui" class="skype_c2c_textarea_span"><img width="0" height="0" src="resource://skype_ff_extension-at-jetpack/skype_ff_extension/data/call_skype_logo.png" class="skype_c2c_logo_img" alt="" /><span class="skype_c2c_text_span">01 662 259 61 74</span></span></span></span></p>-->
							   01 (662) 259-61-73<br />
							 01 (662) 259-61-74 <br /></p>
                                     
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Tabasco</small></h4>
		    <h4><small>Villahermosa</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle Narciso Saenz No. 215,  <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 86000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						         01 (993) 314 00 32<br />
							 01 (993) 312 30 09 Ext. 119<br /></p>
                                                         </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 14:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Tamaulipas</small></h4>
		    <h4><small>Matamoros</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Lauro Villar No. 1101 esq. con Francisco Villa, Plaza Río, Local – 6, Fracc. Las Palmas, <abbr title="C&oacute;digo Postal">C.P.</abbr> 87420.
							</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						        01 (868) 812-02-01<br />
							01 (868) 812-24-40<br />
							01 (868) 813-63-67<br />
							01 (868) 813-63-88 <br/>
							
							</p>
                                                </div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 L a V 9:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
			<h4>Entidad - <small>Tlaxcala</small></h4>
		    <h4><small>Tlaxcala</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Guillermo Valle No. 7, <abbr title="Colonia">Col. </abbr> Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 90000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						        01 (246) 466 23 16<br />
							</p>
                                                </div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 14:00 y de 15:00 a 17:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		    <h4><small>Zacatelco</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Independencia No. 1, <abbr title="Colonia">Col. </abbr> Centro, Zacatelco, Tlaxcala.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (246) 497 48 62<br />
                            </p> 						    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 14:00 y de 15:00 a 17:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		    <h4>Entidad - <small>Veracruz</small></h4>
		    <h4><small>Coatzacoalcos</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Zamora No. 405, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 96400.</p>
						</div>
						<div class="col-sm-6">
						<p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						        01 (921) 212 76 58<br />
							01 (921) 212 19 42<br /></p>	 
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		    <h4><small>Xalapa</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. Manuel &Aacute;vila Camacho No. 191, <abbr title="Colonia">Col. </abbr>Francisco Ferrer Guardia, <abbr title="C&oacute;digo Postal">C.P.</abbr> 91020.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						            01 (228) 842 19 00 <br />
						            Ext. 3133
                             </p>						    
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 09:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		    <h4>Entidad - <small>Yucat&aacute;n</small></h4>
		    <h4><small>Mérida uno</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Calle 60 No. 526 entre 65 y 67, <abbr title="Colonia">Col. </abbr>Centro(INTERIOR JAPAY), <abbr title="C&oacute;digo Postal">C.P.</abbr> 97000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (999) 923 66 49<br />
                                                         </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		    <h4>Entidad - <small>Yucat&aacute;n</small></h4>
		    <h4><small>Mérida dos</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Av. 86 entre 171 y 171-A, <abbr title="Colonia">Col. </abbr>Colonia Emiliano Zapata Sur lll, interior Plaza Santos Sur, Mérida, Yucatan.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						          01 (999) 429 79 39<br />
                                                         </p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 15:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->
			
			<div class="page-header">
		    <h4>Entidad - <small>Zacatecas</small></h4>
		    <h4><small>Zacatecas</small></h4>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<p><strong>Domicilio</strong><br />
							Plaza Bicentenario<br />
    Calle Ventura Salazar s/n, <abbr title="Colonia">Col. </abbr>Centro, <abbr title="C&oacute;digo Postal">C.P.</abbr> 98000.</p>
						</div>
						<div class="col-sm-6">
							 <p><strong>Tel&eacute;fono(s) y extensi&oacute;n </strong><br />
						    01 (492) 922-79-78<br /></p>
						</div>
						<div class="clearfix"></div>
						<div class="col-sm-6">
							 <p><strong>Horarios de atenci&oacute;n</strong><br />
 								 08:00 a 16:00 hrs.</p>	
						</div>
						</div>
				</div>
			</div><!-- end panel -->


<p>Nota: Las oficinas del Servicio Nacional de Empleo son las encargadas de la operaci&oacute;n de los programas en cada entidad federativa y dependen de los gobiernos de los estados. En la Ciudad de México se denominan Unidades Delegacionales del Servicio de Empleo (UDSE) y son dependientes del Gobierno de la Ciudad de México.</p>
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
