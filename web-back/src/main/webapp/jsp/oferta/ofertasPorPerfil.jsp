<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script><script type="text/javascript">
	
function doSubmit(method){
	document.OfertasPorPerfilForm.method.value=method;
	document.OfertasPorPerfilForm.submit();		
}
              
</script>

<form name="OfertasPorPerfilForm" id="OfertasPorPerfilForm" method="post" action="ofertasPerfil.do">

	<input type="hidden" name="method" id="method" value="init"/>

                  
	<div id="ofertas">                  

	<p class="un_medio">ID CANDIDATO: <input type="text" name="idCandidato" id="idCandidato" value="${OfertasPorPerfilForm.idCandidato}" /> </p>
	<p class="un_medio">ID EMPRESA:   <input type="text" name="idEmpresa" id="idEmpresa" value="${OfertasPorPerfilForm.idEmpresa}" /> </p>

	</br>
    <p class="un_tercio"><input type="button" value="Mis ofertas de empleo" onclick="javascript:doSubmit('init');" name="" class="boton"> </p>	
	<p class="un_tercio"><input type="button" value="Perfil-Empresa" onclick="javascript:doSubmit('ofertasPerfilEmpresa');" name="" class="boton"></p>
	<p class="un_tercio"><input type="button" value="Ofertas empresa" onclick="javascript:doSubmit('ofertasEmpresa');" name="" class="boton"></p>
	</br>



	
	<h3>Mis ofertas de empleo Por Perfil (Test se borrara posteriormente no es version final)</h3>
                  
	<table cellspacing="0" cellpadding="0" border="0" width="100%">
		<tbody>

		<tr class="temas">
    		<th>Título de la oferta</th>
        	<th>Ubicación</th>
        	<th>Empresa</th>
        	<th>Salario</th>
        	<th class="fin"></th>
    	</tr>

		<c:forEach var="oferta" items="${OfertasPorPerfilForm.ofertas}" varStatus="rowCounter">
			<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
    			<td>${oferta.tituloOferta}</td>
        		<td>${oferta.ubicacion}</td>
        		<td>${oferta.empresa}</td>
	        	<td>
				<c:if test="${not empty oferta.salario}">
					<fmt:setLocale value="en_US"/>
					<fmt:formatNumber type="CURRENCY" value="${oferta.salario}" />
		        </c:if>				        	
	        	</td>        		
        		<td><a class="btn_portal" href="#">Ver detalle...</a></td>
    		</tr>
    	</c:forEach>

		</tbody>
	</table>

	</div>

</form>
