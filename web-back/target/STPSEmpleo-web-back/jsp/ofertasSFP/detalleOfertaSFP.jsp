<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.text.SimpleDateFormat,java.text.ParseException;"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <c:if test="${not empty OFERTA_SFP}">
    
    <jsp:useBean id="OFERTA_SFP" class="mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoSFPVO" scope="request"/>
    	
    <%
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		String fechaAlta = "";
		try{
			if (OFERTA_SFP.getFechaAlta() != null){
				fechaAlta = sdf2.format(sdf.parse(sdf.format(OFERTA_SFP.getFechaAlta())));
			}
		}catch (ParseException e) {
				e.printStackTrace();
			}
		%>
<table class="offer">
	<tr>
		<td colspan="2"><H3>DATOS DE LA OFERTA DE EMPLEO</H3></td>
	</tr>
	<tr>
	
	<tr>
		<td>FECHA:</td>
		<td><%=fechaAlta%></td>		
	</tr>
	<tr>
		<td>AREA LABORAL:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="areaLaboralDescripcion" /></td>
	</tr>
	<tr>
		<td>OCUPACION:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="ocupacionDescripcion" /></td>
	</tr>
	<tr>
		<td>PUESTO:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="tituloOferta" /></td>
	</tr><tr>
		<td>FUNCIONES Y ACTIVIDADES A REALIZAR:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="funciones" /></td>
	</tr>
	<tr>
		<td>TIPO DE EMPLEO:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="tipoEmpleoDescripcion" /></td>
	</tr>
	<tr>
		<td>HORARIO DE EMPLEO:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="horario" /></td>
	</tr>
	<tr>
		<td>NUMERO DE PLAZAS:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="numeroPlazas" /></td>
	</tr>
	<tr>
		<td>SALARIO OFRECIDO MENSUAL(BRUTO):</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="salario" /></td>
	</tr>
	<tr>
		<td>ENTIDAD DE LA OFERTA:</td>
		<td>${OFERTA_SFP.domicilio.entidad}</td>
	</tr>
	<tr>
		<td>MUNICIPIO O DELEGACION:</td>
		<td>${OFERTA_SFP.domicilio.municipio}</td>
	</tr>
	<tr>
		<td colspan="2"><H3>REQUISITOS PARA OCUPAR LA OFERTA DE EMPLEO</H3></td>
	</tr>
	<tr>
		<td>ESCOLARIDAD MINIMA:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="escolaridad" /></td>
	</tr>
	<tr>
		<td>CARRERA O ESPECIALIDAD:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="carreraDescripcion" /></td>
	</tr>
	<tr>
		<td>SITUACION ACADEMICA:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="situacionAcademicaDescrip" /></td>
	</tr>
	<tr>
		<td>EXPERIENCIA:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="habilidadGeneral" /></td>
	</tr>
	<tr>
		<td>AÑOS DE EXPERIENCIA:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="experienciaAniosDescrip" /></td>
	</tr>
	<tr>
		<td>DISPONIBILIDAD VIAJAR:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="dispViajarDescripcion" /></td>
	</tr>
	<tr>
		<td>DISPONIBILIDAD RADICAR FUERA:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="dispRadicarDescripcion" /></td>
	</tr>
	<tr>
		<td>EDAD PREFERENTE:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="edadPreferente" /></td>
	</tr>
	<tr>
		<td colspan="2"><H3>REQUISITOS PARA OCUPAR LA OFERTA DE EMPLEO</H3></td>
	</tr>
	<tr>
		<td>NOMBRE O RAZON SOCIAL:</td>
		<td><jsp:getProperty name ="OFERTA_SFP" property ="empresaDescripcion" /></td>
	</tr>
</table>

</c:if>