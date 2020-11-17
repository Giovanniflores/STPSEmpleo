<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Programa de Empleo Temporal (PET)</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Programa de Empleo Temporal (PET) contribuye al bienestar de hombres y mujeres que enfrentan una reducci&oacute;n de sus ingresos, y de la poblaci&oacute;n afectada por emergencias mediante apoyos econ&oacute;micos temporales por su participaci&oacute;n en proyectos de beneficio familiar o comunitario.
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
          <li><a href="${context}/jsp/empleo/servicioNacionalDeEmpleo/mecanismoMovilidadLaboralInternaExterna.jsp">Mecanismo de Movilidad Laboral interna y externa</a></li>
          <li class="active">Programa de Empleo Temporal (PET)</li>
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
			<h2 class="titulosh2">Programa de Empleo Temporal (PET)</h2>
		
	        <p>El Programa de Empleo Temporal (PET) contribuye al bienestar de hombres y mujeres que enfrentan una reducci&oacute;n de sus ingresos, y de la poblaci&oacute;n afectada por emergencias mediante apoyos econ&oacute;micos temporales por su participaci&oacute;n en proyectos de beneficio familiar o comunitario.</p>
<p><strong>Objetivo</strong></p>
<p>Otorgar apoyos econ&oacute;micos a las personas de 16 a&ntilde;os o m&aacute;s que vean disminuidos sus ingresos o su patrimonio a causa de situaciones sociales y econ&oacute;micas adversas, emergencias o desastres, como contraprestaci&oacute;n por su participaci&oacute;n en proyectos de beneficio social, familiar o comunitario.</p>
<p><strong>Cobertura</strong></p>
<p>El Programa opera a nivel nacional y focaliza la entrega de apoyos en Municipios con Alta P&eacute;rdida del Empleo (MAPE) y en los que por presentar &iacute;ndices de Muy Alta, Alta o Media Marginaci&oacute;n (MMAM) hay un elevado nivel de informalidad en sus actividades. Asimismo, el programa atender&aacute; a los municipios que formen parte de las demarcaciones del Programa Nacional para la Prevenci&oacute;n Social de la Violencia y la Delincuencia y municipios comprendidos en la cobertura de la Cruzada Nacional contra el Hambre (CNCH). Los municipios comprendidos en la cobertura del Programa pueden consultarse en la p&aacute;gina electr&oacute;nica del CIPET: <a href="http://www.cipet.gob.mx/" target="_blank">http://www.cipet.gob.mx/</a></p>
<!-- p><strong>Requisitos:</strong></p>
<p>Para participar en un proyecto:</p>
<ol>
    <li>Tener de 16 a&ntilde;os de edad o m&aacute;s.</li>
    <li>Presentar copia y original para cotejo de alguno de los siguientes documentos:<br />
    <ul class="list-group-contenidos">
        <li>Clave &Uacute;nica de Registro de Poblaci&oacute;n (CURP).</li>
        <li>Acta de nacimiento.</li>
        <li>Credencial para votar (vigente).</li>
        <li>Cartilla del Servicio Militar Nacional.</li>
        <li>Pasaporte (vigente).</li>
        <li>Constancia de identidad y edad con fotograf&iacute;a, expedida por la autoridad municipal (solo para localidades con menos de 10 mil habitantes).</li>
        <li>Credencial del Instituto Nacional de las Personas Adultas Mayores (Inapam).</li>
        <li>Formas Migratorias.</li>
        <li>C&eacute;dula de Identidad Ciudadana.</li>
        <li>C&eacute;dula de Identidad Personal.</li>
        <li>C&eacute;dula Profesional.</li>
    </ul>
    </li>
</ol>
<p>Para mayor informaci&oacute;n del programa visite: <a href="http://www.sedesol.gob.mx/es/SEDESOL/Mas_informacion_del_Programa" target="_blank">http://www.sedesol.gob.mx/es/SEDESOL/Mas_informacion_del_Programa</a></p>
<p><strong>Lista de proyectos:</strong></p>

<div class="table-responsive">
  <table class="table table-condensed">
    <tbody>
        <tr>
            <td colspan="2" rowspan="6" bordercolor="#FFFFFF" style="text-align: center;"><img width="159" height="64" alt="" src="${context}/css/images/contenido/logo-sedesol.jpg" />&nbsp;</td>
                    </tr>
        <tr>
            <td colspan="5" bordercolor="#FFFFFF">
            <div align="right"><strong>Subsecretar&iacute;a de Desarrollo Social y Humano </strong></div>
            </td>
        </tr>
        <tr>
            <td colspan="5" bordercolor="#FFFFFF">
            <div align="right"><strong>Direcci&oacute;n General de Atenci&oacute;n a Grupos Prioritarios </strong></div>
            </td>
        </tr>
        <tr>
            <td colspan="5" bordercolor="#FFFFFF">
            <div align="right"><strong>Programa de Empleo Temporal </strong></div>
            </td>
        </tr>
        <tr>
            <td colspan="5" bordercolor="#FFFFFF">
            <div style="text-align: right;"><strong>Obras autorizadas para pr&oacute;xima ejecuci&oacute;n. 24 de septiembre de 2015.</strong></div>
            </td>
        </tr>
        <tr>
            <td colspan="5" bordercolor="#FFFFFF">&nbsp;</td>
        </tr>
        <tr>
            <td width="74" bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;"><strong>Estado</strong></td>
            <td width="72" bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;"><strong>Municipio</strong></td>
            <td width="81" bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;"><strong>Localidad</strong></td>
            <td width="147" bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;"><strong>Descripci&oacute;n de la Obra</strong></td>
            <td width="90" bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;"><strong>Fecha de Inicio</strong></td>
            <td width="87" bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;"><strong>Duraci&oacute;n en Semanas</strong></td>
            <td width="57" bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;"><strong>Empleos</strong></td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">San Blas Atempa</td>
            <td bordercolor="#999999">Puente Madera</td>
            <td bordercolor="#999999">Rehabilitación de Vivienda (cambio de techumbre)</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">3&nbsp;</td>
            <td bordercolor="#999999" style="text-align: center;">100</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">Zimatlán de Álvarez</td>
            <td bordercolor="#999999">San José Guelatová de Diaz</td>
            <td bordercolor="#999999">Rehabilitación de Vivienda (cambio de techumbre)</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;4</td>
            <td bordercolor="#999999" style="text-align: center;">25</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">Santa Lucía Ocotlán</td>
            <td bordercolor="#999999">Santa Lucía Ocotlán</td>
            <td bordercolor="#999999">Rehabilitación de Escuela Preescolar</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;5</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;30</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">Tataltepec de Valdés</td>
            <td bordercolor="#999999">Tataltepec de Valdés</td>
            <td bordercolor="#999999">Rehabilitación de Vivienda (cambio de techumbre)</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;4</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;50</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">San Miguel El Grande</td>
            <td bordercolor="#999999">San Miguel El Grande</td>
            <td bordercolor="#999999">Rehabilitación de Escuela Secundaria</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;5</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;81</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">San Blas Atempa</td>
            <td bordercolor="#999999">Rancho El Llano</td>
            <td bordercolor="#999999">Rehabilitación de Vivienda (cambio de techumbre)</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;3</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;100</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">San Blas Atempa</td>
            <td bordercolor="#999999">Loma Bonita</td>
            <td bordercolor="#999999">Rehabilitación de Vivienda (cambio de techumbre)</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;3</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;100</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">San Juan Bautista Tuxtepec</td>
            <td bordercolor="#999999">Paso Canoa</td>
            <td bordercolor="#999999">Rehabilitación de Escuela Primaria</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;5</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;81</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">San Juan Bautista Tuxtepec</td>
            <td bordercolor="#999999">Piedra Quemada</td>
            <td bordercolor="#999999">Rehabilitación de Escuela Primaria</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;5</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;81</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">San Juan Atepec</td>
            <td bordercolor="#999999">San Juan Atepec</td>
            <td bordercolor="#999999">Rehabilitación de Vivienda (cambio de techumbre)</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;4</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;30</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">San Juan Atepec</td>
            <td bordercolor="#999999">San Juan Diuxi</td>
            <td bordercolor="#999999">Rehabilitación de Vivienda (cambio de techumbre)</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;5</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;35</td>
        </tr>
        <tr>
            <td bordercolor="#999999">Oaxaca</td>
            <td bordercolor="#999999">San Juan Tamazola</td>
            <td bordercolor="#999999">San José Ojo de Agua</td>
            <td bordercolor="#999999">Rehabilitación de Vivienda (cambio de techumbre)</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;28/09/2015</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;5</td>
            <td bordercolor="#999999" style="text-align: center;">&nbsp;30</td>
        </tr>
        <tr>
            <td bgcolor="#CCCCCC" bordercolor="#999999"><strong>Total general</strong></td>
            <td bgcolor="#CCCCCC" bordercolor="#999999"></td>
            <td bgcolor="#CCCCCC" bordercolor="#999999"></td>
            <td bgcolor="#CCCCCC" bordercolor="#999999"></td>
            <td bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;"></td>
            <td bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;">&nbsp;<strong>51</strong></td>
            <td bgcolor="#CCCCCC" bordercolor="#999999" style="text-align: center;">&nbsp;<strong>743</strong></td>
        </tr>
        <tr>
            <td colspan="7">&nbsp;</td>
        </tr>
        
        </tbody>
  </table>
</div>
<p><strong>Nota:</strong> "Las obras reportadas en este archivo, se consideran de carácter indicativo, para tener la certeza de que el proyecto (obra) está a punto de iniciar o está en ejecución, el beneficiario deberá ponerse en contacto con la Delegación Estatal de la SEDESOL para obtener información precisa sobre la ejecución en tiempo real de la obra."</p>
<p><strong>Delegaciones SEDESOL en los Estados</strong></p>
<p><a href="http://www.sedesol.gob.mx/en/SEDESOL/Delegaciones01" target="_blank">http://www.sedesol.gob.mx/en/SEDESOL/Delegaciones01</a></p-->
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
