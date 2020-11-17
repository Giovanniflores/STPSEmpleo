<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<table id="resultados" summary="Los resultados de la busqueda" >
	<tbody>
	<tr class="temas">
		    <th><a id="titulo_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','1')"></a> <a id="titulo_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','1')"></a> Nombre(s), primer apellido y segundo apellido</th>
		    <th><a id="ubicacion_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','2')"></a> <a id="ubicacion_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','2')"></a> Grado de estudios - Carrera o Especialidad</th>    	 
		    <th width="60px;"><a id="empresa_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','3')"></a> <a id="empresa_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','3')"></a> Edad</th>
		    <th><a id="salario_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','4')"></a> <a id="salario_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','4')"></a> Entidad federativa - Municipio/Delegaci&#243;n</th>
    </tr>
    
	<c:set var="sufijo" value="" />
	<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	
	<c:forEach var="candidato" items="${sessionScope[pageList]}" varStatus="rowCounter">
		
		<!-- registros sobre los que se pagina-->		
		<tr>
		    <td><div><strong><a class="titulo" href="${context}buscarcandidatos.do?method=detail&idc=${candidato.idCandidato}">${candidato.nombreCompleto}</a></strong></div></td>
		   	<td><c:out value="${candidato.estudios}"/></td>
		   	<td><c:out value="${candidato.edad}"/></td>
		   	<td><c:out value="${candidato.entidadFederativa}"/></td>
		</tr>
		<tr class="odd detalles">
			<td colspan="4">
				<strong>Expectativas laborales:&nbsp;</strong>
				<strong>Subárea laboral deseada:&nbsp;</strong>${busquedaCandidatosForm.subAreaLaboral}.&nbsp;
				<strong>Salario pretendido:&nbsp;</strong>${candidato.salarioPretendido}.&nbsp; 
				<!-- strong>Tipo de contrato:&nbsp;</strong>${candidato.tipoContrato}.&nbsp;
				<strong>Tipo de empleo:&nbsp;</strong>${candidato.tipoEmpleo}.&nbsp; 
				<strong>Disponibilidad para viajar:&nbsp;</strong>${candidato.viajar}.&nbsp;
				<strong>Disponibilidad para radicar fuera:&nbsp;</strong>${candidato.radicar} --> 
			</td>
		</tr>
		
	</c:forEach>		
	</tbody> 
</table>

<!--  <a href="javascript:doSubmit('init');" class="boton_naranja nueva-busqueda" id="nueva-busqueda">Nueva b&#250;squeda</a> -->
</br>

<jsp:include page="../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="candidatos"/>		 
</jsp:include>