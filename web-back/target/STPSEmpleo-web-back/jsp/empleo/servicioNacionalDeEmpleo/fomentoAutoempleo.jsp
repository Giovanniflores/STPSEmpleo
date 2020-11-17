<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Fomento al autoempleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Fomento al Autoempleo (FA) es un subprograma que tiene como objetivo apoyar con la entrega de mobiliario, maquinaria, equipo y/o herramienta a los buscadores de empleo que desean desarrollar una actividad por cuenta propia y que aun teniendo experiencia, no logran vincularse a un puesto de trabajo.
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
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasApoyoEmpleo.jsp"/>">Programas de Apoyo al Empleo</a></li>
          <li class="active">Fomento al autoempleo</li>
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
			<h2 class="titulosh2">Fomento al autoempleo</h2>
		
	        <p>El Fomento al Autoempleo (FA) es un subprograma que tiene como objetivo apoyar con la entrega de mobiliario, maquinaria, equipo y/o herramienta a los buscadores de empleo que desean desarrollar una actividad por cuenta propia y que aun teniendo experiencia, no logran vincularse a un puesto de trabajo.</p>
<p><strong>Requisitos:</strong></p>
<ol>
    <li>Realizar, personalmente, el tr&aacute;mite &ldquo;Solicitud de Registro en el Servicio Nacional de Empleo (SNE)&rdquo;.</li>
    <li>Llenar y firmar bajo protesta de decir verdad el formato &ldquo;Registro del Solicitante&rdquo;.</li>
    <li>Ser buscador de empleo.</li>
    <li>Tener 18 a&ntilde;os o m&aacute;s.</li>
    <li>Tener experiencia de, por lo menos, seis meses en las actividades relacionadas con el negocio que se desea emprender, la cual se corroborar&aacute; en la entrevista que se realice al solicitante de empleo.<br />
    En el caso de no contar con experiencia ni conocimientos, dichas personas tendr&aacute;n la opci&oacute;n de participar, previamente, en alg&uacute;n curso de capacitaci&oacute;n del subprograma B&eacute;cate, cuya especialidad deber&aacute; corresponder con el giro del proyecto de negocio.</li>
    <li>Tener un ingreso menor a seis salarios m&iacute;nimos mensuales, en su n&uacute;cleo familiar.</li>
    <li>Aportar recursos propios para la operaci&oacute;n del negocio correspondiente a capital fijo y de trabajo.</li>
    <li>Elaborar y entregar la propuesta del proyecto de negocio.</li>
    <li>Los integrantes del negocio deber&aacute;n firmar la &ldquo;Carta Compromiso&rdquo;.</li>
</ol>
<p>Presentar original (para su cotejo) y entregar copia simple legible de la siguiente documentaci&oacute;n.</p>
<ul class="list-group-contenidos">
    <li>Identificaci&oacute;n oficial (credencial para votar vigente expedida por el Instituto Nacional Electoral [INE], c&eacute;dula profesional, pasaporte vigente o Cartilla del Servicio Militar Nacional).</li>
    <li>Clave &Uacute;nica de Registro de Poblaci&oacute;n (CURP). Si la identificaci&oacute;n oficial contiene impresa la clave CURP, no ser&aacute; necesario presentar este documento.</li>
    <li>Comprobante de domicilio reciente, m&aacute;ximo tres meses de haberse expedido (recibos de luz, tel&eacute;fono fijo, agua o predial) o carta de avecindad con fotograf&iacute;a que demuestre la residencia de, por lo menos, dos a&ntilde;os en la localidad donde habite el solicitante, el cual podr&aacute; ser el lugar donde se instalar&aacute; el negocio.</li>
    <li>Trat&aacute;ndose de personas preliberadas, se aceptar&aacute; la carta de preliberaci&oacute;n que emita el Centro de Readaptaci&oacute;n Social correspondiente.</li>
    <li>Registro Federal de Contribuyentes (RFC), cuando sean negocios que ya est&aacute;n operando. Si el negocio es nuevo, se deber&aacute; tramitar su registro y presentarlo antes de la entrega definitiva de bienes.</li>
</ul>
<p><strong>Apoyos que se otorgan:</strong></p>
<p>En especie (mobiliario, maquinaria, equipo y/o herramienta), cuyo costo puede ser de hasta $25,000.00 por persona; y hasta $125,000.00 cuando el n&uacute;mero de integrantes de la IOCP  sea de cinco o m&aacute;s personas.</p>
<!--  las 24 horas del día -->
<p>Para mayor información, acude a la <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">oficina</a> del Servicio Nacional de Empleo más cercana a tu domicilio. También, puedes llamarnos al 01 800 8 41 2020, sin ningún costo, ll&aacute;manos de 8 am a 10 pm los 365 días del año, y uno de nuestros consejeros con gusto te atenderá.</p>

<p><span style="font-size: x-small;">1&nbsp;</span><span style="font-size: smaller;">Es una propuesta relacionada con una actividad productiva l&iacute;cita, viable y rentable que realiza un emprendedor o grupo de emprendedores, con el prop&oacute;sito de mantener sus propias fuentes de trabajo; el negocio puede ser de nueva creaci&oacute;n o estar en operaci&oacute;n.</span></p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
