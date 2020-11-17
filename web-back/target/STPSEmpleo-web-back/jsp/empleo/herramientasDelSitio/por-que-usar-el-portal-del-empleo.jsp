<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<t:publicTemplate>
    <jsp:attribute name="titulo">&iquest;Por qu&eacute; usar el Portal del Empleo?</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		&iquest;Por qu&eacute; usar el Portal del Empleo?.
	</jsp:attribute>
    <style type="text/css">
		.panel-body > img {
		    float: left;
		    padding: 0 15px 0 0;
		}
    </style>

	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	           <li> <a href="<c:url value="/inicio.do"/>">Inicio</a></li>
<!-- 	          <li><a href="#">Inicio</a></li> -->
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
	          <li class="active">¿Por qué usar el Portal del Empleo?</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
	    <div class="row">
		  <!-- div menu -->
		  <jsp:include page="menu.jsp"/>
		  
		  <!-- div contenido -->
	      <div class="col-sm-8 col-sm-pull-4">
	      
	      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
	
	        <div class="panel">
	          <div class="panel-heading">
	            <h2 class="titulosh2">¿Por qué usar el Portal del Empleo?</h2>
	          </div>
	          <div class="panel-body">
	            <p>Los servicios que ofrece el Portal del Empleo han transformado la manera en que los buscadores de empleo 
	            y empresas se vinculan, gracias a que el Portal del Empleo tiene ventajas únicas en cuanto a seguridad de 
	            datos, disponibilidad y a que es gratuito. En promedio, al día, se ofertan doscientas mil vacantes de 
	            empleo.</p>
	            
	            <p>Mantenemos relación con <a href="<c:url value="/jsp/empleo/candidatos/bolsasEmpleoAsociadas.jsp"/>">otras bolsas de trabajo</a> por internet, como una forma de aumentar las posibilidades 
	            de colocación de los buscadores de empleo, y difundimos información sobre programas laborales, opciones de 
	            capacitación, artículos de interés, entre otros temas.</p>
	            
	            <p>Gracias a nuestro diseño fácil de usar y a que somos una de las cinco bolsas más conocidas, utilizadas y 
	            recomendadas en México, según el estudio “
	            <a title="Este documento se abrirá en una nueva ventana" target="_blank" href="https://www.amipci.org.mx/images/Busqueda_Empleo_Internet_AMIPCI_2015.pdf ">Búsqueda de Empleo por 
	            Internet 2015</a>” de la <a title="Este enlace se abrirá en una nueva ventana" target="_blank" href="https://www.amipci.org.mx/es/ ">Asociación Mexicana de Internet (AMIPCI)</a>, 
	            contamos con una amplia cobertura y calidad en los servicios que ofrecemos, por lo que 
	            miles de buscadores de empleo han encontrado ofertas de trabajo acordes a sus necesidades.</p>
	            
	            <h2 class="titulosh2" style="padding-top: 50px;">¡Aprovecha los beneficios del Portal del Empleo!</h2>
	            <hr>
	            <h3 class="titulosh2">¿Eres candidato?</h3>
	            <p><strong>Tenemos para ti: </strong></p>
	            
	            <div class="panelArticulos">
		            <div class="panel">
						<div class="panel-heading">
							<h4 class="panel-title">Diferentes opciones de búsqueda de ofertas de empleo</h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/icon-buscar.png">
							<p>Contamos con opciones de búsqueda de ofertas de empleo abiertas, <a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar"/>">búsqueda específica</a> y 
							búsqueda por perfil; utiliza cualquiera de ellas para encontrar el trabajo que buscas. Si estás 
							en sesión, se mostrará el grado de compatibilidad de tu perfil con los requisitos de la oferta; 
							postúlate en donde la compatibilidad sea mayor</p>
						</div>
					</div>
					<div class="panel panel-articulos">
						<div class="panel-heading">
							<h4 class="panel-title">Espacio virtual</h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/icon-virtual.png">
							<p>Al momento de concluir tu registro en el Portal del Empleo, contarás con un espacio virtual 
							para administrar tu información, actualizar tu perfil laboral, subir tu video-currículum, 
							generar tu curriculum vitae, almacenar las ofertas de tu interés, entre otras cosas.</p>
						</div>
					</div>
		            <div class="panel">
						<div class="panel-heading">
							<h4 class="panel-title">Mejor proyección de tu perfil laboral </h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/ico-proyeccion-laboral.png">
							<p>Desde tu espacio, podrás subir tu video-currículum para proyectar una mejor imagen ante el 
							reclutador. Además, podrás personalizar tu <em>curriculum vitae</em> con las diferentes plantillas 
							disponibles.</p>
						</div>
					</div>
					<div class="panel panel-articulos">
						<div class="panel-heading">
							<h4 class="panel-title">Cursos de capacitación en línea</h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/ico-capacitacion.png">
							<p>Ponemos a tu disposición <a href="<c:url value="/jsp/empleo/candidatos/capacitacionBecas.jsp"/>">cursos en línea</a> que la Secretaría del Trabajo y Previsión Social 
							(STPS) ofrece a través de su plataforma PROCADIST, o cursos que ofrece la Fundación Manpower; 
							totalmente gratuitos, diseñados para mejorar tu desempeño laboral.</p>
						</div>
					</div>
		            <div class="panel">
						<div class="panel-heading">
							<h4 class="panel-title">Consejos de empleo</h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/ico-consejos.png">
							<p>A menudo piensas: "si busco trabajo, ¿por qué no lo encuentro?”, o ¿estás iniciando tu vida 
							laboral?, el Portal del Empleo te ayuda en ambos casos. A través del taller <a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Habilidades para la 
							Búsqueda de Empleo (HPBE)</a>, aprenderás cómo elaborar un curriculum vitae de alto impacto, cómo 
							conducirte en una entrevista de empleo, la forma correcta para llenar una solicitud de empleo, 
							dónde buscar trabajo, entre otros temas.</p>
						</div>
					</div>
					<hr>
	            <h3 class="titulosh2">¿Eres empresa?</h3>
	            <p><strong>Tenemos para ti: </strong></p>
	            	<div class="panel">
						<div class="panel-heading">
							<h4 class="panel-title">Publicación de ofertas de empleo </h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/icon-oferta.png">
							<p>Publicar tu vacante de empleo en el Portal del Empleo, te da la ventaja de que será 
							promocionada por todos los servicios de vinculación laboral con que cuenta el Servicio Nacional 
							de Empleo (SNE). Por lo que la verás publicada en nuestro periódico de ofertas de empleo, te 
							enviaremos candidatos de nuestra bolsa de trabajo, la cual atiende de manera presencial a los 
							buscadores de empleo, podrás participar en las Ferias de Empleo y más.</p>
						</div>
					</div>
					<div class="panel panel-articulos">
						<div class="panel-heading">
							<h4 class="panel-title">Búsqueda de candidatos</h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/icon-buscar-candidato.png">
							<p>Contacta al candidato de tu interés para cubrir el puesto vacante en tu empresa. Para lo 
							cual, ponemos a tu disposición diversas opciones de búsqueda de candidatos, abierta, específica 
							y por oferta de empleo.</p>
						</div>
					</div>
	            	<div class="panel">
						<div class="panel-heading">
							<h4 class="panel-title">Asistencia a Ferias de Empleo</h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/icon-asistencia-ferias.png">
							<p>Con tu registro en el Portal del Empleo, podrás inscribirte a los diferentes eventos de 
							<a href="http://ferias.empleo.gob.mx/content/common/home.jsf ">Ferias de Empleo</a> que el SNE 
							organiza, en donde llevarás a cabo reclutamiento de personal directo.</p>
						</div>
					</div>
					<div class="panel panel-articulos">
						<div class="panel-heading">
							<h4 class="panel-title">Herramienta de vinculación match</h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/icon-vinculacion.png">
							<p>Conoce la compatibilidad entre el perfil del candidato y los requisitos de tu oferta de 
							empleo a través de nuestra herramienta match. Conócela y ahorra tiempo para cubrir tus vacantes 
							de empleo.</p>
						</div>
					</div>
	            	<div class="panel">
						<div class="panel-heading">
							<h4 class="panel-title">Consejos para tu empresa</h4>
						</div>
						<div class="panel-body">
							<img src="${context}/css/images/contenido/icon-mejora.png">
							<p>Consulta nuestro apartado dedicado al reclutamiento y a 
							<a href="<c:url value="/jsp/empleo/empresas/mejoraDesempenoEmpresa.jsp"/>">mejorar el 
							desempeño de tu empresa</a>.  Encontrarás temas como el análisis de puestos, la selección 
							del personal, herramientas para evaluar a tus candidatos, entre otros.</p>
						</div>
					</div>
				</div>
				<p>Si requieres asesoría, te ofrecemos atención personalizada y gratuita a través de nuestro 
				<a href="<c:url value="/miespacionav.do?method=agendaCita"/>">sistema de citas</a> vía telefónica, 
				01 800 841 20 20, llámanos de 8 am a 10 pm  y los 365 días del año.</p>
				<p><strong>Regístrate como <a href="<c:url value="/registro_candidato.do"/>">candidato</a> o 
				<a href="<c:url value="/registro_empresa.do"/>">empresa</a> y disfruta de todos los beneficios que el 
				Portal del Empleo tiene para ti.</strong></p>
				<p><strong> No olvides que, con tu registro, tendrás acceso gratis a todos los servicios que ofrece el 
				Servicio Nacional de Empleo.</strong></p>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
