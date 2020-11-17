<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="no-more-tables">
	<table id="resultados" class="table table-striped table-condensed offer" summary="Los resultados de la busqueda"  cellspacing="0" cellpadding="0" border="0" width="100%">
		<thead>
			<tr class="temas">
				<th>
					<span>Título de la oferta</span>	
					<div class="pull-right">
						<a id="jobOfferNameAsc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','1')"></a> 
						<a id="jobOfferNameDesc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','1')"></a>
					</div>
				</th>
				<th>
					<span>Ubicación</span>
					<div class="pull-right">
						<a id="locationAsc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','2')"></a> 
						<a id="locationDesc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','2')"></a>
					</div>
				</th>
				<th style="width: 25%;">
					<span>Empresa</span>
					<div class="pull-right">
						<a id="enterpriseNameAsc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','3')"></a> 
						<a id="enterpriseNameDesc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','3')"></a>
					</div>
				</th>
				<th>
					<span>Salario neto ofrecido (mensual)</span>
					<div class="pull-right">
						<a id="netSalaryAsc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','4')"></a> 
						<a id="netSalryDesc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','4')"></a>
					</div>
				</th>
				<th>
					<span>Fecha de publicación</span>
					<div class="pull-right">
						<a id="publicationDateAsc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','5')"></a> 
						<a id="publicationDateDesc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','5')"></a>
					</div>
				</th>
				<logic:equal name="BusquedaEspecificaOfertasForm" property="candidateLogged" value="true">
					<th>
						<span>Compatibilidad</span>
						<div class="pull-right">
							<a class="ascendente"></a> 
							<a class="descendente"></a>
						</div>
					</th>
				</logic:equal>
			</tr>
		</thead>
		<tbody>
			
								
			<logic:iterate collection='<%= request.getSession(false).getAttribute("PAGE_LIST") %>' id="jobOffer" indexId="jobOfferIndex">
			
				<bean:define id="indexMod2" value='<%= String.format("%d", jobOfferIndex % 2) %>'/>
				<logic:equal name="indexMod2" value="0">
				
					<bean:define id="trClass" value="odd"/>
					
				</logic:equal>
				<logic:notEqual name="indexMod2" value="0">
					<bean:define id="trClass" value="null"/>
					
				</logic:notEqual>	
				
				
		    	<tr class="${trClass}" >
		    	
					<td data-title="Título de la oferta">
	<%-- 					<a id="job${jobOfferIndex}" class="titulo" href="javascript:void(0)" onclick="gotoOffer()"><bean:write name="jobOffer" property="jobOfferName"/></a> --%>
						<a class="titulo" href="<c:url value="/detalleoferta.do?method=init&id_oferta_empleo=${jobOffer.jobId}"/>"><bean:write name="jobOffer" property="jobOfferName"/></a>
						<input type="hidden" name="estado2" value="<bean:write name="jobOffer" property="calle" />" id="estado2" >
					</td>
					<td data-title="Ubicación">
				
						<bean:write name="jobOffer" property="locationEntity"/>
						
				
						<br/>
				
				 		
					</td>
					<td data-title="Empresa">
						<bean:write name="jobOffer" property="enterpriseName"/>
					</td>
					<td data-title="Salario neto (mensual)" class="text-center">
						<fmt:setLocale value="en_US"/>
						<fmt:formatNumber value="${jobOffer.netSalary}" type="currency"/>
					</td>
					<td data-title="Fecha de publicación">
						<fmt:setLocale value="es_mx" />
						<fmt:formatDate value="${jobOffer.publicationDate}" pattern="dd"/>
						&nbsp;de&nbsp;
						<fmt:formatDate value="${jobOffer.publicationDate}" pattern="MMMM"/>
						&nbsp;de&nbsp;
						<fmt:formatDate value="${jobOffer.publicationDate}" pattern="yyyy"/>
					</td>
					<logic:equal name="BusquedaEspecificaOfertasForm" property="candidateLogged" value="true">
						<td>
							<bean:write name="jobOffer" property="compatibility"/>%
						</td>
					</logic:equal>
				
				</tr>
			
				
				<tr class="${trClass} detalles">
			
					<logic:equal name="BusquedaEspecificaOfertasForm" property="candidateLogged" value="true">
						<bean:define id="colSpan" value="6"/>
					</logic:equal>
					<logic:notEqual name="BusquedaEspecificaOfertasForm" property="candidateLogged" value="true">
						<bean:define id="colSpan" value="5"/>
					</logic:notEqual>
					<td colspan="${colSpan}"> 
						<div>
						<strong>Funciones</strong>: <bean:write name="jobOffer" property="jobOfferDescFunctions"/>
						<!-- strong>Experiencia</strong>: <bean:write name="jobOffer" property="jobOfferDescYearsExp"/> a&ntilde;o<logic:greaterThan name="jobOffer" property="jobOfferDescYearsExp" value="1">s</logic:greaterThan>.-->
						<strong>Idiomas</strong>: <bean:write name="jobOffer" property="jobOfferDescLangs"/>.
						<!-- strong>Rango de edad</strong>: 
						<logic:equal name="jobOffer" property="jobOfferDescMinAge" value="0">
							<logic:equal name="jobOffer" property="jobOfferDescMaxAge" value="0">
								No es requisito
							</logic:equal>
						</logic:equal>
						<logic:notEqual name="jobOffer" property="jobOfferDescMinAge" value="0">
							<bean:write name="jobOffer" property="jobOfferDescMinAge"/> -&nbsp;	
						</logic:notEqual>
						<logic:notEqual name="jobOffer" property="jobOfferDescMinAge" value="0">
							<bean:write name="jobOffer" property="jobOfferDescMaxAge"/>	
						</logic:notEqual>
						a&ntilde;os. -->
						</div>
					</td>  
				</tr>
		    </logic:iterate>
		</tbody>
	</table>
</div>

<%-- <a href="<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=buscar" class="boton_naranja nueva-busqueda" id="nueva-busqueda">Nueva&nbsp;b&uacute;squeda</a> --%>
	
<bean:define id="sufijo" value=""/>
<jsp:include page="../layout/pager.jsp" flush="true">
        <jsp:param name="SUFIJO" value="${sufijo}"/>
        <jsp:param name="tipoRegistros" value="ofertas"/>		 
</jsp:include>

	