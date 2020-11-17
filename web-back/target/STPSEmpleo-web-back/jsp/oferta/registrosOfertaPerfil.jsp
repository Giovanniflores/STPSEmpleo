<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page trimDirectiveWhitespaces="true" %>

<%	
	String context = request.getContextPath() +"/";
    String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
%>
<%-- <div id="hideAll" style="display:none;"  align="right"><a href="javascript:mostrarTodas(2, ${NUM_REGISTROS})" class="expand">[Ocultar resumen de todas las ofertas]</a></div> --%>
<%-- <div id="showAll" style="display:block;" align="right"><a href="javascript:mostrarTodas(1, ${NUM_REGISTROS})" class="expand">[Ver resumen de todas las ofertas]</a></div> --%>
<div class="miEspacio">
<form name="myopportunities" id="myopportunities" method="post">
<h2>Mi espacio</h2>
<div class="tab_block">
        <div align="left" id="returnME" style="display:block;">
            <a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
<strong>Ir al inicio de Mi espacio</strong></a>
        </div>
        <div class="Tab_espacio">
        <h3>Encontrar ofertas de empleo</h3>
            <div class="subTab">
                <ul>
                    <li class="curSubTab"><span>Ofertas de acuerdo a mi perfil</span></li>                    
                    <li><a href="<c:url value="/ofertasPorParametros.do?method=init"/>">Búsqueda parametrizable</a></li>                    
                    <li><a href="<c:url value="/bolsasTrabajo.do?method=init"/>">Buscar en otras bolsas de trabajo</a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="sublevelTitle">
            Ofertas de acuerdo a mi perfil
        </div>
		<c:if test="${OfertasPorPerfilForm.inscritoPPC}">
		<c:choose>
      		<c:when test="${not empty OfertasPorPerfilForm.ofertas}">        
        		<p>Se encontraron las siguientes ofertas de empleo de acuerdo a tu perfil. Para que continúes con el Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD), debes postularte al menos a una oferta.</p>
 		</c:when>
     		 <c:otherwise>
       		  <p>No se encontraron ofertas compatibles a tu perfil, te recomendamos contactar a un asesor en línea para ayudarte en la búsqueda de ofertas de acuerdo a tu perfil
         		 o si lo deseas realiza otra búsqueda.</p>
	      </c:otherwise>
		</c:choose>
		</c:if>	        

		<c:if test="${not OfertasPorPerfilForm.inscritoPPC}">
		<c:choose>
		      <c:when test="${not empty OfertasPorPerfilForm.ofertas}">
		      	<p>Se encontraron las siguientes ofertas de acuerdo a tu perfil.<p>
		      </c:when>      	
			  <c:otherwise>
				<p>No se encontraron ofertas compatibles a tu perfil.</p>			  
		      </c:otherwise>    
		</c:choose>        	
		</c:if>		
</div>
<div class="publicados">
<table class="seleccionados" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr class="temas">
		<th><div class="fl">Puesto</div>
			<div class="order-fix">
			 <a id="puesto_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','puesto')"></a>
         	 <a id="puesto_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','puesto')"></a>
         	 </div>
        </th>
		<th><div class="fl">Empresa</div>
			<div class="order-fix">
 			 <a id="empresa_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','empresa')"></a>
             <a id="empresa_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','empresa')"></a>
             </div>
        </th>		
		<th><div class="fl">Ubicación</div>
			<div class="order-fix">
		     <a id="ubicacion_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','ubicacion')"></a>
             <a id="ubicacion_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','ubicacion')"></a>
             </div>		
		</th>
		<th><div class="fl">Salario mensual</div>
			<div class="order-fix">
   			 <a id="salario_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','salario')"></a>
             <a id="salario_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','salario')"></a>
             </div>		
		</th>
		<th style="text-align: center">
			<div class="fl">Compatibilidad</div>
			<div class="order-fix"></div>
		</th>
	</tr>
	
	<c:set var="sufijo" value="" />
	<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	
	<c:forEach var="ofertasPerfil" items="${sessionScope[pageList]}"
		varStatus="rowCounter">		
		<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
			<td><div class="profesion"><strong><a
				href="${pageContext.request.contextPath}/detalleoferta.do?method=init&id_oferta_empleo=${ofertasPerfil.idOfertaEmpleo}">${ofertasPerfil.tituloOferta}</a></strong></div></td>
			<td>${ofertasPerfil.empresa}</td>
			<td>${ofertasPerfil.ubicacion}</td>
        	<td>
			<c:if test="${not empty ofertasPerfil.salario}">
				<fmt:setLocale value="en_US"/>
				<fmt:formatNumber type="CURRENCY" value="${ofertasPerfil.salario}" />
	        </c:if>				        	
        	</td>
			<td style="text-align: center;">${ofertasPerfil.compatibilidad} %</td>
		</tr>

		<tr class="detalles">
			<td colspan="5">					   
				<h3><Strong>Resumen:</Strong></h3>
				<div  id="${rowCounter.count}">
					${ofertasPerfil.tituloOferta} con ${ofertasPerfil.gradoEstudio}
					<c:if test="${ofertasPerfil.carrera != null}"> en ${ofertasPerfil.carrera}</c:if>
					<c:if test="${ofertasPerfil.funciones != '' and ofertasPerfil.funciones != 'Ninguna'}">, ${ofertasPerfil.funciones}</c:if>
					<c:if test="${ofertasPerfil.idiomasCert != null}">
						<c:if test="${ofertasPerfil.idiomasCert == 'No es requisito'}">, Idioma no es requerido</c:if>		
						<c:if test="${ofertasPerfil.idiomasCert != 'No es requisito'}">, ${fn:trim(ofertasPerfil.idiomasCert)}</c:if>			
					</c:if>
					<c:if test="${ofertasPerfil.numeroPlazas != null}">, ${ofertasPerfil.numeroPlazas} plaza(s)</c:if>
					<c:if test="${ofertasPerfil.contactoTel == 2}">, ${ofertasPerfil.telefonoOferta}</c:if>
					<c:if test="${ofertasPerfil.contactoCorreo == 2}">, ${ofertasPerfil.correoElectronicoContacto}</c:if>
					<c:if test="${ofertasPerfil.contactoDomicilio == 2}">, ${ofertasPerfil.domicilio}</c:if>					
					<c:if test="${empty ofertasPerfil.habilidades && not ofertasPerfil.habilidadGeneral == null}">, ${ofertasPerfil.habilidadGeneral}</c:if>		
					<c:if test="${not empty ofertasPerfil.habilidades}">, ${ofertasPerfil.habilidades}</c:if>	
				</div>
			</td>
		</tr>
</c:forEach>

</table>

</div>
</form>
</div>
<c:if test="${not empty OfertasPorPerfilForm.ofertas}"> 
	<jsp:include page="../layout/pager.jsp" flush="true">
	         <jsp:param name="SUFIJO" value="${sufijo}"/>
	         <jsp:param name="tipoRegistros" value="ofertas"/>		 
	</jsp:include>
</c:if>	        



<c:if test="${OfertasPorPerfilForm.inscritoPPC}">
<c:choose>
      <c:when test="${empty OfertasPorPerfilForm.ofertas}">
      <div class="alt_c">
      <h3>No se encontraron ofertas compatibles con tu perfil</h3>
      <div style="margin: auto; padding: 30px; width: 562px;" class="gris">
      	
     	 <p>Como <strong>alternativa de ayuda</strong> te presentamos el siguiente listado de opciones las cuales podrás encontrar desde tu espacio o en la parte superior
     	 del portal.</p>
     	 <ul>
	     	 <li><a href="<%=contextSWB%>/swb/empleo/candidatos#Buscador_empleo">Buscador de Empleo</a></li>	     	 
	     	 <li><a href="<%=contextSWB%>/swb/empleo/candidatos#Buscador_especifico">Buscador específico</a></li>
	     	 <li><a href="<%=contextSWB%>/swb/empleo/candidatos#Buscador_otras">Buscador en otras bolsas de trabajo</a></li>
     	 </ul><br>
		 <p>Además, te brindamos <strong>opciones de apoyo</strong> para mejorar tu vinculación laboral con las empresas.</p><br>  
		 <ul>
			 <li><a href="<%=contextSWB%>/swb/empleo/candidatos#Registro_ferias">Registrarse a evento de Ferias de Empleo</a></li>
			 <li><a href="<%=contextSWB%>/swb/empleo/candidatos#Solicitar_cita">Solicitar una cita</a></li>
			 <li>¿Necesitas ayuda?<a href="<%=contextSWB%>/swb/empleo/candidatos#Asesoria"> Inicia una Asesoría</a></li>
			 <li><a href="<%=contextSWB%>/swb/empleo/candidatos">Llama al 01 800 841 2020</a></li>		 
		 </ul><br>
		 </div>	
	  </div>	   	 
      </c:when>
      <c:otherwise>
      </c:otherwise>
</c:choose>
</c:if>	



