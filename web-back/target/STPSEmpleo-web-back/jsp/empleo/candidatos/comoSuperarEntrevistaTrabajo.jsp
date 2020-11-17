<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">&iquest;C&oacute;mo superar la entrevista de trabajo?</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Durante el proceso de selecci&oacute;n, la parte m&aacute;s importante es la entrevista, en ella el &aacute;rea de recursos humanos de la empresa, o directamente el empleador, obtienen informaci&oacute;n muy importante sobre quien busca incorporarse a la empresa. La entrevista, si bien es un evento &uacute;nico cada vez que lo enfrentamos, est&aacute; compuesto por cinco etapas:
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>">Habilidades para la Búsqueda de Empleo (HPBE)</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/postula.jsp"/>">Postula</a></li>
          <li class="active">¿Cómo superar la entrevista de trabajo?</li>
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
			<h2 class="titulosh2">¿Cómo superar la entrevista de trabajo?</h2>
		
	        <p>Durante el proceso de selecci&oacute;n, la parte m&aacute;s importante es la entrevista, en ella el &aacute;rea de recursos humanos de la empresa, o directamente el empleador, obtienen informaci&oacute;n muy importante sobre quien busca incorporarse a la empresa. La entrevista, si bien es un evento &uacute;nico cada vez que lo enfrentamos, est&aacute; compuesto por cinco etapas:</p>
<ul class="list-group-contenidos">
    <li><strong>Atenci&oacute;n.</strong> Te sugerimos que: saludes de forma convencional al entrevistador, le mires a los ojos, no extiendas la mano antes que &eacute;l, tomar asiento hasta que te lo indique, recuerda que &eacute;l lleva la batuta, no le tutes, no muestres dudas sobre tu trayectoria ocupacional, y pongas atenci&oacute;n a lo que se te pregunte.</li>
    <li><strong>Inter&eacute;s.</strong> Una vez que has recibido la convocatoria a la entrevista, recuerda que quien la va a conducir es el entrevistador, pero aprovecha el momento en que hable de la plaza y pregunta m&aacute;s sobre &eacute;sta:</li>
    <li><strong>Desarrollo.</strong> Cuida que tus respuestas sean claras, breves y concisas. Cuando te pregunten sobre ti mismo, haz &eacute;nfasis en tus logros, tu experiencia y tu educaci&oacute;n. No uses monos&iacute;labos para contestar. No esperes mucho en dar tus respuestas. No denotes urgencia por el trabajo.</li>
    <li><strong>Decisi&oacute;n.</strong> Despu&eacute;s de las preguntas que te realizar&eacute; el entrevistador, &eacute;ste tendr&aacute; una idea clara de ti y de tus posibilidades dentro de la empresa, as&iacute; como de la contribuci&oacute;n que puedes hacer.</li>
    <li><strong>Conclusi&oacute;n.</strong> A la despedida de la entrevista, agradece al entrevistador la oportunidad, muestra disposici&oacute;n a efectuar otra entrevista si es necesario, e intenta concretar en qu&eacute; fecha recibir&aacute;s noticias.</li>
</ul>
<p><strong>Antes de la entrevista</strong>, debes prepararte para enfrentarla. Considerar los siguientes puntos te ayudar&aacute;:</p>
<ul class="list-group-contenidos">
    <li>Consigue informaci&oacute;n sobre la empresa (&iquest;qu&eacute;  hace?, &iquest;d&oacute;nde est&aacute; ubicada?, &iquest;tiene sucursales?, &iquest;cu&aacute;ntos empleados tiene?, etc.)</li>
    <li>Si ya entregaste tu C.V., es importante que tengas un amplio dominio sobre la informaci&oacute;n que consignaste all&iacute;.</li>
    <li>Estudia y haz algunas pruebas de exposici&oacute;n de tus aptitudes, tu experiencia y tus logros acad&eacute;micos, especialmente de aquellos que tienen relaci&oacute;n con la  vacante a la que est&aacute;s postulando.</li>
    <li>Has una tabla en la que tenga por un lado, tus puntos d&eacute;biles y, por otro, tus puntos fuertes. Busca generar argumentos que te permitan definir ambos, puedes usar el siguiente <a href="${context}/download/candidatos/puntos_fuertes_y_puntos_debiles.docx">formato</a> para hacer tu lista.</li>
    <li>Si recibes una cita por correo electr&oacute;nico, a trav&eacute;s de una carta o una llamada, lo que debes hacer es llamar a la persona que te env&iacute;e el mensaje y que confirmes la fecha, hora y lugar  de la cita.</li>
    <li>Cuida tu presentaci&oacute;n el d&iacute;a de la entrevista.</li>
    <li>Ser puntual el d&iacute;a de la cita.</li>
    <li>No lleves compa&ntilde;&iacute;a, hacerlo denota poca independencia y madurez.</li>
</ul>
<p>S&iacute; quieres ver algunas preguntas que te pueden hacer y c&oacute;mo elaborar las respuestas, sigue el siguiente <a href="${context}/download/candidatos/diez_preguntas_que_debes_considerar_a_la_hora_de_presentarte_a_una_entrevista_de_trabajo.docx" target="_new">v&iacute;nculo</a>. Despu&eacute;s de la entrevista, analiza los resultados obtenidos; establece cu&aacute;les han sido tus puntos fuertes y d&eacute;biles, en el caso de los &uacute;ltimos elabora estrategias que te permitan superarlos.</p>
	        
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
