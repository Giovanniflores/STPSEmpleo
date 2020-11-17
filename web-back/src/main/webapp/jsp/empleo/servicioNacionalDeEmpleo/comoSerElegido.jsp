<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">&iquest;C&oacute;mo ser elegido?</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Logra una buena imagen en tus empleadores. Las empresas u organizaciones realizan, en la mayor&iacute;a de los casos, un proceso sistem&aacute;tico para encontrar a los mejores candidatos a ocupar sus vacantes de empleo.
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
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/talleresBuscadoresEmpleo.jsp"/>">Talleres para buscadores de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/talleresLinea.jsp"/>">Talleres en línea</a></li>
          <li class="active">¿Cómo ser elegido?</li>
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
			<h2 class="titulosh2">¿Cómo ser elegido?</h2>
		
	        <p>Logra una buena imagen en tus empleadores</p>
<p>Las empresas u organizaciones realizan, en la mayor&iacute;a de los casos, un proceso sistem&aacute;tico para encontrar a los mejores candidatos a ocupar sus vacantes de empleo.</p>
<p>Quienes carecen de trabajo tambi&eacute;n deben actuar de manera similar, planeando, programando y cuidando los detalles de su b&uacute;squeda de empleo para aspirar a ocupar las mejores ofertas de trabajo en el menor tiempo posible.</p>
<p><strong>3. &iquest;C&oacute;mo ser elegido?</strong></p>
<p>Las empresas ponen manos a la obra cuando requieren personal para desempe&ntilde;ar tareas que no pueden cubrir con recursos humanos internos. A este proceso se le llama selecci&oacute;n.</p>
<p>Quienes buscan trabajo tambi&eacute;n tienen una labor a realizar cuando necesitan encontrar ofertas acordes a su perfil e intereses.</p>
<p>Conocer la forma general en que ambos proceden para lograr su objetivo es parte de lo que podemos aprender para mejorar la estrategia de b&uacute;squeda de empleo.</p>
<p>En el presente m&oacute;dulo conocer&aacute;s el proceso general que siguen las empresas para seleccionar al personal y algunas recomendaciones para incrementar tus oportunidades de ser elegido.</p>
<p><strong>3.1 Diagrama de flujo del proceso</strong></p>
<p>Se recomienda:</p>
<ul class="list-group-contenidos">
    <li>Verificar que se cumple con los requisitos de la vacante.</li>
    <li>Llamar a la empresa u organizaci&oacute;n para concertar una entrevista.</li>
    <li>Recabar los datos necesarios para localizar al establecimiento antes de trasladarse.</li>
    <li>Recabar informaci&oacute;n de la empresa en Internet, si est&aacute; disponible.</li>
</ul>
<p>Ten en cuenta que:</p>
<ul class="list-group-contenidos">
    <li>Los reclutadores esperan recibir a m&aacute;s de un solicitante para poder elegir al candidato ideal.</li>
    <li>La empresa comienza a evaluar tu desempe&ntilde;o desde la llamada telef&oacute;nica o primer contacto.</li>
    <li>Al acudir a tu cita &ndash;a&uacute;n con los inconvenientes del caso&ndash; est&aacute;s demostrando ser responsable y con capacidad para resolver problemas.</li>
</ul>
<p>Debes saber que:</p>
<ul class="list-group-contenidos">
    <li>Adem&aacute;s de pedir la solicitud de empleo o curr&iacute;culum, las empresas realizan entrevistas para conocer el inter&eacute;s y caracter&iacute;sticas del candidato.</li>
    <li>En algunos casos, los establecimientos aplican ex&aacute;menes pr&aacute;cticos y de conocimientos, as&iacute; como pruebas psicol&oacute;gicas.</li>
    <li>Algunas empresas investigan para obtener informaci&oacute;n socioecon&oacute;mica del candidato y solicitan o aplican un examen m&eacute;dico.</li>
</ul>
<p>Para realizar la evaluaci&oacute;n:</p>
<ul class="list-group-contenidos">
    <li>El responsable de recursos humanos recaba informaci&oacute;n sobre tus caracter&iacute;sticas personales y laborales. La solicitud de empleo, el curr&iacute;culum y las entrevistas sirven a ese prop&oacute;sito.</li>
    <li>El responsable de la selecci&oacute;n de personal analiza tus caracter&iacute;sticas a la luz de sus perfiles de puesto para determinar qu&eacute; tan cercano es tu perfil al trabajo requerido.</li>
    <li>Los perfiles de puesto son descripciones sobre el trabajo que se necesita realizar.</li>
</ul>
<p>Considera que:</p>
<ul class="list-group-contenidos">
    <li>Algunas empresas u organizaciones cuentan con departamentos de reclutamiento y selecci&oacute;n de personal o buscan ser asesoradas para localizar al mejor candidato.</li>
    <li>La selecci&oacute;n de personal es un proceso costoso. Por tanto, muchas empresas prefieren promover a personal interno y no contratar a candidatos externos.</li>
    <li>Las empresas cuentan con personal calificado para la selecci&oacute;n.</li>
</ul>
<p><strong>3.2 &iquest;C&oacute;mo cubrir una vacante?</strong></p>
<p>Se sugiere:</p>
<ul class="list-group-contenidos">
    <li>Recurrir a diversas fuentes de informaci&oacute;n sobre vacantes.</li>
    <li>Recabar informaci&oacute;n de vacantes que proporcionen datos suficientes como para darte una idea de la seriedad de quienes las ofrecen.</li>
    <li>Ser constante. Las vacantes de tu inter&eacute;s pueden ofrecerse cuando menos lo esperas.</li>
</ul>
<p>Si ya tienes cita:</p>
<ul class="list-group-contenidos">
    <li>Procura ir descansado. Tu capacidad de respuesta ser&aacute; mejor y te ver&aacute;s f&iacute;sicamente bien.</li>
    <li>Acude puntualmente a ella. Prev&eacute; posibles contratiempos que puedan demorarte para llegar a tiempo.</li>
    <li>No destines la jornada a otra actividad. Despu&eacute;s de la entrevista, pueden aplicarte las pruebas psicol&oacute;gicas y pr&aacute;cticas, y ese tiempo lo debes de tener disponible.</li>
</ul>
<p>Es conveniente que:</p>
<ul class="list-group-contenidos">
    <li>Incluyas en la solicitud de empleo o en tu curr&iacute;culum los datos necesarios y cuides la presentaci&oacute;n.</li>
    <li>Cuentes con original y copia de los principales documentos de identificaci&oacute;n y otros que regularmente las empresas solicitan.</li>
    <li>Hables con las personas que conocen tu trabajo para asegurar que te extender&aacute;n las cartas de recomendaci&oacute;n que necesites.</li>
</ul>
<p>Es muy importante que:</p>
<ul class="list-group-contenidos">
    <li>No suspendas tu b&uacute;squeda de empleo a&uacute;n cuando alguna empresa ofrezca llamarte. &iexcl;Siempre ten m&aacute;s de una opci&oacute;n!</li>
    <li>Selecciones las vacantes que te den m&aacute;s oportunidades y centra tu b&uacute;squeda en ellas.</li>
    <li>Si tienes empleo y est&aacute;s buscando otro, procura no abandonar el primero hasta asegurar tu contrataci&oacute;n en el segundo.</li>
</ul>
<p>Al iniciar tu b&uacute;squeda, trata de:</p>
<ul class="list-group-contenidos">
    <li>Pensar positivamente. Es decir, que te va a ir bien porque cumples con los requisitos, tienes inter&eacute;s y eres una persona capaz.</li>
    <li>Confiar en tus capacidades y perfeccionarlas si fuera necesario.</li>
    <li>Fortalecer tu car&aacute;cter para no desanimarte ante los rechazos.</li>
</ul>
<p>Aqu&iacute; termina el m&oacute;dulo.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
