<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
<!--
var carreraclean = true;
var entidadclean = true;

function clean(field, numfield) {

	if (numfield==1){
		if (carreraclean){
			field.value='';
			carreraclean = false;
		}		
	}

	if (numfield==2){
		if (entidadclean){
			field.value='';
			entidadclean = false;
		}		
	}
	
	 

	field.style.color='#454545';
}

function employ(form) {
    form.searchQ.value = form.searchCarrera.value;
    form.submit();
 }
 


//-->
</script>

<form id="ocupateForm" name="ocupateForm" action="ocupate.m" method="post">

<div dojoType="dojox.mobile.RoundRect" align="center">
	<input type="hidden" name="method" id="method" value="init"/>
	<input type="hidden" name="searchQ" id="searchQ" value=""/>
	
	<h3>¿Qué empleo buscas?</h3><br/>
	<input type="text" name="searchCarrera" id="searchCarrera" style="width: 200px;"
	       value=""
	       onfocus="javascript:clean(this, 1); clean(searchEntidad, 2);" />
	<br/>
	<h3>¿Dónde?</h3><br/>
	<select id="idEntidad" name="idEntidad">
	<c:forEach var="entidad" items="${entidades}">
		<option value="${entidad.idCatalogoOpcion}">${entidad.opcion}</option>
	</c:forEach>
	</select>
	<br/>
	<button type="button" onclick="javascript:employ(this.form)" dojoType="dojox.mobile.Button" class="baseBtn roundBtn navyBtn buscarBtn" style="width:90px;">Buscar</button><br/>
</div>

</form>