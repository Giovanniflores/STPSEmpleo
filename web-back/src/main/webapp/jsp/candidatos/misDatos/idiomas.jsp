<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.core.candidate.vo.IdiomaVO, mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%
	String context = request.getContextPath() +"/";
	int NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS = 2;
%>
<script type="text/javascript">
	function confirmAlert(mensaje){
			dojo.byId('mensaje').innerHTML = mensaje;
			dijit.byId('MensajeAlert').show();		
	}
	
	function closeDialog(){
	        dijit.byId('MensajeAlert').hide();
	        setTimeout("window.location.reload();",10);
	}
	
	function actualizaValorAd(campo,valor) {
		if(valor!=''){
			document.getElementById(campo).value=valor;
		}			
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<%
	int index = 1;
	String idIdiomaAdd = "idIdiomaAdd";
	String idIdiomaSelectAdd = "idIdiomaSelectAdd";
	String idCertificacionSelectAdd = "idCertificacionSelectAdd";
	String idCertificacionAdd = "idCertificacionAdd";
	String idDominioSelectAdd = "idDominioSelectAdd";
	String idDominioAdd = "idDominioAdd";
	String idCandidatoIdioma = "idCandidatoIdioma";

	List<IdiomaVO> languages = (List<IdiomaVO>) request.getSession().getAttribute("IDIOMAS_LIST");
	if (null == languages && !languages.isEmpty()) {
		languages = new ArrayList<IdiomaVO>();	
	}
	Iterator<IdiomaVO> itLanguages = languages.iterator();
	while (itLanguages.hasNext()) {
		IdiomaVO oe = itLanguages.next();
		if (oe.getPrincipal() == 2) {
			String selectedLan = (String)session.getAttribute("IDIOMAS_" + oe.getIdCandidatoIdioma());
			String selectedLanCert = (String)session.getAttribute("CERTIFICACIONES_" + oe.getIdCandidatoIdioma());
			String selectedLanDom = (String)session.getAttribute("DOMINIOS_" + oe.getIdCandidatoIdioma());
%>    	
    	<div class="idiomas bloque">
            <p class="un_cuarto fl"><span class="c_naranja">*</span><strong><label>Idioma</label>:</strong><br />
            	<input type="hidden" name="<% out.print(idCandidatoIdioma + index); %>" id="<% out.print(idCandidatoIdioma + index); %>" value="<%=oe.getIdCandidatoIdioma() %>" />
            	<input type="hidden" name="<% out.print(idIdiomaAdd + index); %>" id="<% out.print(idIdiomaAdd + index); %>" value="<%=oe.getIdIdioma() %>" />
	        	<select dojotype="dijit.form.FilteringSelect" required="true" autocomplete="true" missingMessage="Es necesario indicar el idioma adicional" onchange="javascript:actulizaCertificacionesAdd('<%=oe.getIdCandidatoIdioma() %>',this.value);actualizaValorAd('<% out.print(idIdiomaAdd + index); %>',this.value);">
		        	<%=selectedLan %>
	        	</select>
            </p>
            <p class="un_cuarto fl"><strong><label>Dominio del idioma</label>:</strong><br />
            	<input type="hidden" name="<% out.print(idDominioAdd + index); %>" id="<% out.print(idDominioAdd + index); %>"  value='<% out.print(oe.getIdDominio()); %>' />
	        	<select dojotype="dijit.form.FilteringSelect" style="width:8em;" required="true" autocomplete="true" onchange="javascript:actualizaValorAd('<% out.print(idDominioAdd + index); %>',this.value);">
	        		<%=selectedLanDom %>
	        	</select>
            </p>
            <input type="hidden" name="<% out.print(idCertificacionAdd + index); %>" id="<% out.print(idCertificacionAdd + index); %>"  value='<% out.print(oe.getIdCertificacion()); %>' />
            <p class="un_cuarto fl"><strong>&iquest;<label>Cuentas con certificaci&oacute;n</label>?</strong><br />
	        	<select dojotype="dijit.form.FilteringSelect" required="true" autocomplete="true" onchange="javascript:actualizaValorAd('<% out.print(idCertificacionAdd + index); %>',this.value);actualizaDominioAdd('<%=oe.getIdCandidatoIdioma() %>',this.value);">
	        		<%=selectedLanCert %>
	        	</select>
            </p>
             
            <p class="un_cuarto fl"><input type="button" class="eliminar" onclick="javascript:deleteLang(<%=oe.getIdCandidatoIdioma()%>);" value="Eliminar" /></p>
            <br clear="all" />
    	</div>
 <% 
 			index++;
 		}
	}
	if (languages.size() < NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS) {
%>
	    <p class="add_ph"><input type="button" class="agregar" onclick="javascript:togglelan();" value="Agregar idioma" /></p>
<%  
	}else {
%>
		<div class="entero">
	    						
		</div>
<%  
	}
%>