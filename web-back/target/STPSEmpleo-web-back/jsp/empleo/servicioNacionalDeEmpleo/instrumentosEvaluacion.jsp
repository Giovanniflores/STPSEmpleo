<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Instrumentos de evaluaci&oacute;n</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Conoce qu&eacute; eval&uacute;an los empleadores en los candidatos
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
          <li class="active">Instrumentos de evaluación</li>
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
			<h2 class="titulosh2">Instrumentos de evaluación</h2>
		
	        <p>Conoce qu&eacute; eval&uacute;an los empleadores en los candidatos</p>
<p>Los instrumentos de selecci&oacute;n son recursos que aportan informaci&oacute;n valiosa a las empresas para conocer a los buscadores de empleo y determinar su aptitud para el trabajo.</p>
<p>Entre m&aacute;s informaci&oacute;n de calidad aporte el solicitante a trav&eacute;s de esos instrumentos, y entre mejor los conozca, podr&aacute; utilizarlos a su favor para llegar a estar entre los aspirantes con mayores posibilidades de contrataci&oacute;n.</p>
<p><strong>4. Instrumentos de evaluaci&oacute;n</strong></p>
<p>Para allegarse el personal que necesitan, las empresas regularmente siguen un proceso, el cual, aunque puede variar de un establecimiento a otro, cubre las etapas necesarias para dar con el mejor candidato.</p>
<p>La obtenci&oacute;n y an&aacute;lisis de informaci&oacute;n sobre los solicitantes con el auxilio de instrumentos de acopio y evaluaci&oacute;n tales como la solicitud de empleo y la entrevista, entre otros, son elementos fundamentales de ese proceso.</p>
<p>En este m&oacute;dulo conocer&aacute;s algunos detalles de la forma como los establecimientos eval&uacute;an antes de decidir sobre la contrataci&oacute;n de una persona, para que con esos elementos puedas obtener mejores posibilidades de obtener un empleo.</p>
<p><strong>4.1 Solicitud de empleo</strong></p>
<p>La solicitud de empleo es un formato impreso que permite registrar los datos generales del buscador de trabajo y de sus familiares m&aacute;s cercanos. En &eacute;l tambi&eacute;n se anota informaci&oacute;n sobre los empleos anteriores, los antecedentes educativos, las actividades y conocimientos que se poseen y otros datos relativos al estado de salud y socioecon&oacute;mico del solicitante.</p>
<p>La solicitud de empleo habla de ti. Procura:</p>
<ul class="list-group-contenidos">
    <li>Hacer una lista de la informaci&oacute;n y documentos que necesitas para llenar la solicitud.</li>
    <li>Hacer un borrador de la solicitud, el cual puedas corregir antes de entregar al empleador.</li>
    <li>Incluir en la solicitud todos los datos requeridos.</li>
    <li>Llenar la solicitud con letra de molde cuando se trate de un formulario impreso.</li>
    <li class="no_line">Revisar la ortograf&iacute;a. Puedes recurrir a los correctores ortogr&aacute;ficos que incluyen algunos programas de c&oacute;mputo como los procesadores de palabras.</li>
</ul>
<p><strong>4.2 Curriculum vitae</strong></p>
<p>El curr&iacute;culum vitae es un documento en el que se registra con cierto detalle el historial laboral de las personas. Es &uacute;til para dar a conocer informaci&oacute;n m&aacute;s amplia de lo que permite la solicitud de empleo. El curr&iacute;culum debe preferentemente incluir:</p>
<ul class="list-group-contenidos">
    <li>El objetivo profesional del aspirante.</li>
    <li>Datos generales del aspirante.</li>
    <li>Datos sobre los tres &uacute;ltimos empleos.</li>
    <li>Logros</li>
    <li>Principales t&iacute;tulos y estudios.</li>
    <li class="no_line">Participaci&oacute;n en eventos relevantes.</li>
</ul>
<p><strong>4.3 Entrevista</strong></p>
<p>&iquest;Qu&eacute; hacer durante la entrevista?</p>
<ul class="list-group-contenidos">
    <li>Muestra seguridad en ti mismo.</li>
    <li>Acude aseado.</li>
    <li>Habla en forma pausada.</li>
    <li>S&oacute;lo contesta lo que se te pregunte.</li>
    <li>Habla sobre lo que realmente conozcas.</li>
    <li>No ofrezcas nada que no puedas dar.</li>
    <li class="no_line">S&eacute; t&uacute; mismo.</li>
</ul>
<p>Se recomienda:</p>
<p>Centrar la conversaci&oacute;n en tus experiencias y no en las de otras personas. Dar un tono positivo a tu pl&aacute;tica. Procura no hablar mal de otras personas. Ten clara qu&eacute; informaci&oacute;n personal no est&aacute;s dispuesto a proporcionar. Preguntar por el salario, el horario, etc. cuando el empleador conozca m&aacute;s de ti.</p>
<p><strong>4.3.1 Tipos de entrevista</strong></p>
<p>Entrevista inicial</p>
<p>&iquest;Cu&aacute;l es su objetivo?</p>
<p>Identificar en el m&iacute;nimo tiempo posible las principales caracter&iacute;sticas del candidato con el prop&oacute;sito de seleccionar s&oacute;lo a aquellos aspirantes que cumplen con los requerimientos del puesto solicitado.</p>
<p>El candidato obtiene informaci&oacute;n general sobre las particularidades del trabajo a realizar y puede expresar o no su inter&eacute;s en la vacante una vez que conoce los siguientes aspectos:</p>
<ul class="list-group-contenidos">
    <li>El trabajo a realizar</li>
    <li>El horario de labores</li>
    <li>La remuneraci&oacute;n</li>
    <li>Las prestaciones</li>
    <li class="no_line">El tipo de contrataci&oacute;n</li>
</ul>
<p>Entrevista t&eacute;cnica</p>
<p>&iquest;Cu&aacute;l es su objetivo?</p>
<p>Identificar si el aspirante cubre la experiencia y conocimientos requeridos para desempe&ntilde;ar las funciones del puesto vacante. Regularmente, el titular del &aacute;rea en que se ubica la vacante es quien realiza la entrevista.</p>
<p>El aspirante habla sobre sus conocimientos y lo que sabe hacer:</p>
<ul class="list-group-contenidos">
    <li>M&eacute;todos y t&eacute;cnicas</li>
    <li>Esquemas de organizaci&oacute;n</li>
    <li>Sistemas de control de calidad</li>
    <li>Sistemas de inventarios</li>
    <li class="no_line">Costo de producci&oacute;n, distribuci&oacute;n, etc.</li>
</ul>
<p><strong>4.3.2 Fases de la entrevista</strong></p>
<p>La entrevista de empleo es una herramienta de comunicaci&oacute;n interpersonal destinada a obtener informaci&oacute;n sobre la trayectoria laboral y caracter&iacute;sticas personales del aspirante a una vacante.</p>
<p>Raport</p>
<p>El empleador busca &quot;romper el hielo&quot; para generar confianza en el candidato y, con ello, asegurar su colaboraci&oacute;n. Usualmente le formula preguntas que no tienen relaci&oacute;n con el empleo que se busca: &iquest;c&oacute;mo est&aacute; el clima?, &iquest;hay mucho tr&aacute;fico?, &iquest;tuvo alg&uacute;n problema para llegar?</p>
<p>Cima</p>
<p>El empleador busca obtener la informaci&oacute;n mediante preguntas que requieren de una respuesta amplia. El candidato debe de prestar particular atenci&oacute;n a las preguntas que indagan sobre el dominio y la experiencia que tiene en el empleo, para dar las mejores respuestas.</p>
<p>Cierre</p>
<p>El empleador busca finalizar la entrevista de manera que todos los elementos informativos sean abordados en el tiempo previsto. En esta fase, el solicitante de trabajo debe estar atento para ser breve y preciso en sus respuestas.</p>
<p><strong>4.4 Pruebas psicol&oacute;gicas</strong></p>
<p>Los test o pruebas son instrumentos que exploran a los individuos para conocer aspectos relevantes de su personalidad y de los conocimientos y capacidades que saben aplicar. Existen test de personalidad y de inteligencia como son los de: razonamiento l&oacute;gico, matem&aacute;tico y verbal; as&iacute; como los test t&eacute;cnicos, administrativos y de observaci&oacute;n, entre otros.</p>
<p><strong>4.5 An&aacute;lisis socioecon&oacute;mico</strong></p>
<p>Los establecimientos est&aacute;n interesados en conocer los estilos de vida y otros aspectos de tu vida personal, dado que se cree que esas pautas de comportamiento pueden reproducirse en el centro de trabajo. Con ese fin, efect&uacute;an visitas al domicilio del solicitante para observar de manera directa su entorno familiar y social.</p>
<p>Las empresas tambi&eacute;n investigan la relaci&oacute;n que mantiene el solicitante con algunos establecimientos y organizaciones, tales como bancos y casas comerciales. Se acostumbra verificar las referencias de empleos anteriores, antecedentes de cr&eacute;dito, referencias bancarias, etc.</p>
<p><strong>4.6 Examen m&eacute;dico</strong></p>
<p>Debes saber que:</p>
<p>El estado de salud de los aspirantes es importante porque puede influir significativamente en la productividad.</p>
<p>Algunos establecimientos aplican ex&aacute;menes m&eacute;dicos a efecto de determinar la aptitud f&iacute;sica del solicitante para el trabajo a realizar. Esta valoraci&oacute;n puede ser importante para las personas con discapacidad, siempre y cuando no sea un mecanismo de discriminaci&oacute;n.</p>
<p><strong>4.7 Personas con capacidades diferentes</strong></p>
<p>Hay que tener presente que:</p>
<p>Un buen proceso de selecci&oacute;n da oportunidades por igual a las personas con o sin capacidades diferentes.</p>
<p>A pesar de que la discapacidad no es un impedimento para la contrataci&oacute;n y de que existen leyes y normas que buscan lograr la equiparaci&oacute;n de oportunidades, es necesario que est&eacute;s atento de que la selecci&oacute;n se efect&uacute;e considerando la disminuci&oacute;n que pudieras tener en tus facultades f&iacute;sicas, intelectuales o sensoriales.</p>
<p>Aqu&iacute; termina el m&oacute;dulo.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
