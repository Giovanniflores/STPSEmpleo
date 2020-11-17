<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <% String context = request.getContextPath()+"/"; 
       String rutaPdf = "https://www.empleo.gob.mx/admon/pdf/cursos/";
    %>
<c:set var="url" value="${pageContext.request.requestURL}" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cursos de Formaci&oacute;n | Portal del Empleo</title>
<meta property="og:title" content="Portal del Empleo: Cursos de Formación">
<meta name="twitter:title" content="Portal del Empleo: Cursos de Formación">
<meta property="og:description" content="Portal del Empleo: Cursos de Formación">
<meta name="twitter:description" content="Portal del Empleo: Cursos de Formación">
<meta name="description" content="Cursos de Formación, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.">
<meta property="og:url" content="${url}">
<meta property="og:site_name" content="Portal del Empleo">
<meta property="og:image" content="http://qa.empleo.gob.mx/css/images/contenido-compartir.jpg">
<meta property="og:image:type" content="image/jpeg">
<meta property="og:image:width" content="1200">
<meta property="og:image:height" content="627">
<meta property="fb:app_id" content="308925772806125" />
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@empleogob_mx">
<meta name="twitter:creator" content="@InfotecMexico">
<meta name="twitter:image:src" content="http://qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
<meta name="author" content="infotec">

<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />
<style type="text/css">

	.textmin {
			FONT-WEIGHT: normal;
			FONT-SIZE: 8pt;
			COLOR: #595959;
			FONT-STYLE: normal;
			FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;
			text-align: justify;
	}

</style>

<script type="text/javascript">

function doSubmitPager(opcion){
if(opcion=='prev'){
		
		document.Busqueda.paginaAnterior.value=true;
		
	}else{
		
		document.Busqueda.paginaSiguiente.value=true;
		
	}
	
	
	document.Busqueda.method.value="cambiarPagina";
	
	document.Busqueda.submit();


}

function doSubmitBusqueda(){

document.Busqueda.method.value="busquedaCurso";
document.Busqueda.submit();


}




</script>
</head>
<body >
<div id="resultadosBusqueda" align="center" style="width: 655px; margin: auto;"> 
 <table style="max-width: 655px" border="0" cellpadding="0" cellspacing="0" align="center">
       
        <tr>
            <td><div align="left"><span class="textmin"><br/>En esta secci&oacute;n podr&aacute;s encontrar cursos de capacitaci&oacute;n para el trabajo que ofrece la Direcci&oacute;n General de Centros de Formación para el Trabajo (DGCFT) de la Secretar&iacute;a de Educación Pública. Estos cursos de capacitación están vinculados con las ramas productivas y de servicio del pa&iacute;s, para que sus egresados certifiquen sus capacidades laborales.</span></div></td>
        </tr>
        <tr>
                            <td>
                                <form name="Busqueda" method="post" action="${context}cursoFormacion.do">
                                <input type="hidden" id="method" name="method" value="">
                                <input type="hidden" id="paginaActual" name="paginaActual" value="${cursoFormacionForm.paginaActual}">
                                <input type="hidden" id="paginaSiguiente" name="paginaSiguiente" value="false">
                                <input type="hidden" id="paginaAnterior" name="paginaAnterior" value="false">
                                    <table style="max-width: 655px" border="0" cellspacing="2" cellpadding="2">
                                        <tr><td align="center" colspan=4 class="textmayusB"><br>LE SUGERIMOS UTILIZAR LOS SIGUIENTES FILTROS PARA OPTIMIZAR SU BUSQUEDA</td></tr>
                                        <tr>
                                            <td align="center" style="max-width: 218px" class="textmayusB"><strong><label for="curso">CURSO</label>:&nbsp;</strong><br>
                                                <input type="text" class="textmayusB" name="curso" id="curso" size="20" maxlength="20" value="" /></td>
                                            <td align="center" style="max-width: 218px" class="textmayusB"><strong><label for="plantel">PLANTEL</label>:&nbsp;</strong><br>
                                                <input type="text" class="textmayusB" name="plantel" id="plantel" size="20" maxlength="20" value="" />
                                            </td>
                                            <td align="center" style="max-width: 218px" class="textmayusB"><strong><label for="idEntidad">ESTADO</label>:&nbsp;</strong><br>
                                                <select class="textmayusB" name="idEntidad" id="idEntidad">
                                                    <option  title="SELECCIONE UNA OPCIÓN" value="0">Seleccione una opción</option>
                                                    <c:forEach var="entidad" items="${cursoFormacionForm.listaEntidades}">
                                                    <option title="${entidad.opcion}" value="${entidad.idCatalogoOpcion}">${entidad.opcion}</option>
                                                    </c:forEach>
                                                        
                                                    
                                                </select>
                                            </td>
                                        </tr>
                                        <tr valign="top">
                                            <td align="center" height="40" colspan="3">
                                                <input type="button" class="boton" value="Buscar" name="Busqueda" onclick="javascript:doSubmitBusqueda();" />
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </td>
                        </tr>
        <tr>
        <tr><td height="18" align="left"><Strong>CURSOS DEL ${cursoFormacionForm.cursoDesde} A ${cursoFormacionForm.cursoHasta} DE <%=(Integer)request.getSession().getAttribute("TOTAL_CURSOS")%></Strong></td></tr>
        <tr>
            <td width="573" valign="top">
                <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0" class="offer">
		<tr class="temas">
			<th>Curso</th>
			<th>Plantel</th>
			<th>Estado</th>
			<th>Domicilio</th>
			<th class="fin">Descripcion</th>
			
		</tr>
	<%
		int rowCounter = 1;	
		
	%>
			<c:forEach var="cursos" items="${cursoFormacionForm.listaCursos}">
			<tr <% out.print(rowCounter % 2 == 0 ? "" : "class='odd'"); %> align="center">
				<td><div class="profesion"><strong>${cursos.curso}</strong></div></td>
				<td>${cursos.plantel}</td>
				<td>${cursos.entidad}</td>
				<td>${cursos.domicilio}</td>
				<td>
				<c:if test="${!empty cursos.linkPdf}">
				<a href="<%=rutaPdf %>${cursos.linkPdf}" target="_blank">Ver</a>
				</c:if>
				<c:if test="${empty cursos.linkPdf}">&nbsp;</c:if>
				</td>
				
				

				
			</tr>
			<%
			rowCounter++;
		
	%>
			</c:forEach>
	
	</table>
</td>
</tr>
</table>
<br clear="all"/>

<p align="center">
			<c:if test="${cursoFormacionForm.paginaAnterior }">
			<a href="javascript:doSubmitPager('prev')" style="color:#4f6710;">&lt;&lt;&lt;&nbsp;</a></c:if>
			<c:if test="${!cursoFormacionForm.paginaAnterior }">
			<a href="#" style="color:#4f6710;">&lt;&lt;&lt;&nbsp;</a></c:if><span class="textmin">
		    &nbsp;..&nbsp;${cursoFormacionForm.paginaActual}&nbsp;..&nbsp;</span>
		    <c:if test="${cursoFormacionForm.paginaSiguiente}"><a href="javascript:doSubmitPager('next')" style="color:#4f6710;">&nbsp;&gt;&gt;&gt;</a></c:if>
		    <c:if test="${!cursoFormacionForm.paginaSiguiente }"><a href="#" style="color:#4f6710;">&nbsp;&gt;&gt;&gt;</a></c:if><span class="textmin">
		    &nbsp;&nbsp;de <%=(Integer)request.getSession().getAttribute("ULTIMA_PAGINA")%> P&aacute;ginas</span>
		</p>
		<p align="center" ><span class="textmin">Mostrando ${cursoFormacionForm.cursoHasta} de <%=(Integer)request.getSession().getAttribute("TOTAL_CURSOS")%> Cursos</span></p>

</div>
</body>
</html>