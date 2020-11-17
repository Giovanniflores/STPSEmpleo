<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.oferta.search.form.OfertasPorParametrosForm" %>
<%@ page import="mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorParametrosVO" %>
<%@ page import="java.util.List"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String context = request.getContextPath() + "/";
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
  
    int NUMERO_MAX_REGISTROS = 5;
    int NUMERO_PAGUINAS	= 0;
	int numOfertas = 0;

	List<OfertaPorParametrosVO> ofertaPorParametrosVO = (List<OfertaPorParametrosVO>)session.getAttribute("FULL_LIST_OFERTAS");
	
	if (null != ofertaPorParametrosVO) {
		numOfertas = ofertaPorParametrosVO.size();
	  	if( numOfertas % NUMERO_MAX_REGISTROS == 0) {
		 	 NUMERO_PAGUINAS =  numOfertas / NUMERO_MAX_REGISTROS;
	 	}else{
	 	 NUMERO_PAGUINAS =  numOfertas / NUMERO_MAX_REGISTROS;
	 	 NUMERO_PAGUINAS++;
	 	}	 	
	  }
%>
<!--  detectar internet explorer -->
<!--[if lt IE 7 ]> <html class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class=""> <!--<![endif]-->

<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="js/misDatos/messagesValidate.js"></script>
<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link   href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />

<style type="text/css">
#OfertasPorParametrosForm .escolaridad .dijitReset.dijitInputField.dijitButtonText {
	width: 46px;
}
</style>
<script type="text/javascript">
	var depGrado  = new Array(); //Declara arreglo de dependencias de catalogo Grado de Estudios
	//Declara arreglo de dependencias de catalogo Grado de Estudios
	<%String[] depGrado = session.getAttribute("depGrado") != null
					? (String[]) session.getAttribute("depGrado")
					: new String[0];
			for (int i = 0; i < depGrado.length; i++) {%>
			<%="depGrado[" + i + "] = '" + depGrado[i] + "';"%>
		<%}%>
</script>

<script type="text/javascript">
    dojo.require("dijit.dijit");
    dojo.require("dijit.form.Form");
    dojo.require("dijit.form.DateTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit.form.RadioButton");
	dojo.require("dijit.Dialog");
	
	
	dojo.addOnLoad(function() {	
		dojo.connect(dijit.byId('idNivelEstudioSelect'), 'onChange', function() {
			var vGrado = dijit.byId('idNivelEstudioSelect').get('value');
			var vIdCatDep = depGrado[vGrado];
            if(vIdCatDep != 0 || vIdCatDep != undefined) {
            	dijit.byId('idCarreraEspecialidadSelect').reset();
            	carreraStore.url = '${context}grados.do?method=cargarCarrera&idCatDep='+ vIdCatDep;
            	carreraStore.close();
			}
		});
		
		cargaCarreraEspecialidad();
		
		var idIdioma = dijit.byId('idIdiomaSelect').get('value');

    	if(idIdioma  == 1 ){
    		dominioStore.url = '';
    		dominioStore.close();
    		dijit.byId('idDominioIdiomaSelect').set('value','0');
			dijit.byId('idDominioIdiomaSelect').attr('value', 0); 
			desactivaSelectConValor('idDominioIdiomaSelect','');
			setLabelToLabelAndRequerido('idDominioIdiomaSelect','idLabelDominioIdioma','Dominio del idioma',false);
    	}
	});	
	
	
	function cargaCarreraEspecialidad(){
		gradoAcademicoStore.fetch({
 			onComplete: function(items, request) {
		      if (items.length == 0) {
		         return;
		      }
		      var vGrado = '${OfertasPorParametrosForm.idNivelEstudio}';		      
		      dijit.byId('idNivelEstudioSelect').attr('value', vGrado);
		      var vIdCatDep = depGrado[vGrado];
		      if(vIdCatDep != 0 || vIdCatDep != undefined) {
	          	carreraStore.url = '${context}grados.do?method=cargarCarrera' + '&' + 'idCatDep='+ vIdCatDep;
	          	carreraStore.close();
	          	carreraStore.fetch({
 					onComplete: function(items, request) {
						if (items.length == 0) {
				        	return;
				    	}
				    	dijit.byId('idCarreraEspecialidadSelect').attr('value', '${OfertasPorParametrosForm.idCarreraEspecialidad}');
 					}
				});
			  }
 			}
		});
	}
	
	
	function doReset(){
		messageRutaCancel('Se restablecerán los valores por defecto ¿Desea continuar ?','<c:url value="/ofertasPorParametros.do?method=init"/>');
	}
	
	
	function actualizaDominio(){

    	var idIdioma = dijit.byId('idIdiomaSelect').get('value');

    	if(idIdioma  == 1 ){
    		dominioStore.url = '';
    		dominioStore.close();
    		dijit.byId('idDominioIdiomaSelect').set('value','0');
			dijit.byId('idDominioIdiomaSelect').attr('value', 0); 
			desactivaSelectConValor('idDominioIdiomaSelect','');
			setLabelToLabelAndRequerido('idDominioIdiomaSelect','idLabelDominioIdioma','Dominio del idioma',false);
    	}
    	else
    	{
    		dominioStore.url = '';
    		dominioStore.close();
    		dijit.byId('idDominioIdiomaSelect').reset();
		   	dominioStore.url = '${context}idiomas.do?method=cargarDominio';
    		dominioStore.close();
    		enableSelectConValor('idDominioIdiomaSelect', 0);
			dijit.byId('idDominioIdiomaSelect').attr('value', 0); 
    		setLabelToLabelAndRequerido('idDominioIdiomaSelect','idLabelDominioIdioma','Dominio del idioma',true);		
		}
	}
	
	
	function desactivaSelectConValor(selectList, valor) {
		dijit.byId(selectList).attr('value', valor);
		var originalValidator = dijit.byId(selectList).validator;
		dijit.byId(selectList).validator = function() {
			return false;
		};
		
		dijit.byId(selectList).validate();
		dijit.byId(selectList).validator = originalValidator;
		dijit.byId(selectList).reset();
		document.getElementById(selectList).value = valor;
		dijit.byId(selectList).readOnly = true;
		dijit.byId(selectList).disabled = true;
	}
	
	
	function enableSelectConValor(selectList, valor) {
		dijit.byId(selectList).set('value', valor);
		dijit.byId(selectList).readOnly = false;
		dijit.byId(selectList).disabled = false;
		var originalValidator = dijit.byId(selectList).validator;
		dijit.byId(selectList).validator = function() {
			return true;
		};
		dijit.byId(selectList).validate();

		dijit.byId(selectList).validator = originalValidator;
	}
	
	function validaForm() {
		if (validaDatos()) {
			doSubmit();
			/**if (validaHabilidades()) {			
				doSubmit();			
			}**/
		}
	}
	
	
	function validaHabilidades(){
		var ischecked = false;
			var habilidaChecks = document.getElementsByName('idHabilidad');
			var numeroHabilidades = 0;
			
			if (habilidaChecks && habilidaChecks.length){
				for (var i=0;i<habilidaChecks.length;i++) {
					if (habilidaChecks[i].checked) {
						numeroHabilidades++;						
					}
				}			
			}
			
			if(numeroHabilidades > 0)ischecked = true;
			if (!ischecked){
				message('Debe elegir por lo menos una habilidad');
				return false;
			}
			if(numeroHabilidades > 5){
				message('Debe seleccionar máximo 5 habilidades y actitudes que te caracterizan.');
				return false;
			}
	    	
			return true;
		}
	
	
	function validaDatos(){
		if (dijit.byId('idNivelEstudioSelect').get('item')== null || dijit.byId('idNivelEstudioSelect').get('item').value == 0) {
    		message('Debe seleccionar último grado de estudios.');
			return false;
    	}
    	
    	if (dijit.byId('idCarreraEspecialidadSelect').get('item')== null || dijit.byId('idCarreraEspecialidadSelect').get('item').value == 0) {
    		message('Debe seleccionar carrera o especialidad.');
			return false;
    	}
    	
    	if (dijit.byId('idExperienciaSelect').get('item')== null || dijit.byId('idExperienciaSelect').get('item').value == 0) {
    		message('Debe seleccionar años de experiencia.');
			return false;
    	}
    	
    	if (dijit.byId('idIdiomaSelect').get('item')== null || dijit.byId('idIdiomaSelect').get('item').value == 0) {
    		message('Debe seleccionar Idioma adicional al nativo.');
			return false;
    	}
    	
    	if(!validaCampoNoRequerido('idDominioIdiomaSelect')){
    	    message('Debe seleccionar dominio del idioma.');
			return false;
    	}
    	
    	if(!dijit.byId('salarioPretendido').isValid())
    	{
    		message('Debe indicar el salario mensual pretendido.');
    		return false;
    	}
    	
    	return true;
	}
	
	
	function validaCampoNoRequerido(campo) {
		var control = dijit.byId(campo);
		if(isDefinedObject(control)){
			
			var requerido = control.get('required');
			if (!control.validate()) {
				dijit.byId(campo).focus();
				dojo.byId(campo).blur();
				
				return false;
			}
		}
		return true;
	}
	
	
	function doSubmit() {
		dojo.byId('method').value = 'search';
 		dojo.xhrPost({
 			url : 'ofertasPorParametros.do', 
 			form:'OfertasPorParametrosForm',
			load : function(dataOfertas) {
					dojo.byId('divOfertas').innerHTML = dataOfertas;
					}		
		});
	}
	
	
	function marcadores(metodo){
		var totalRegistros = <%=numOfertas%>;
		
		var actual =  document.getElementById('numeroPage').value;
		var vistas =  document.getElementById('numeroOfertaVistas').value;		  
		
		if('next' == metodo && actual < <%=NUMERO_PAGUINAS%> ){
		 actual++;	 vistas =   parseInt(vistas) + parseInt(<%=NUMERO_MAX_REGISTROS%>);
		}else if('prev' == metodo  && actual != 1 ){
		 actual--;  vistas = 	parseInt(vistas) - parseInt(<%=NUMERO_MAX_REGISTROS%>);
		}
		
		var aux  = actual * parseInt(<%=NUMERO_MAX_REGISTROS%>);
		 
		 if(aux > totalRegistros){
		  vistas = totalRegistros;
		 }
		 
		 document.getElementById('numeroPage').value =  actual;
		 document.getElementById('numeroOfertaVistas').value =  vistas;
		
	}	
	
</script>

<form name="OfertasPorParametrosForm" id="OfertasPorParametrosForm" method="post" 
	action="ofertasPorParametros.do" dojoType="dijit.form.Form">	
	<input type="hidden" name="method" id="method" value="search" />
	<input type="hidden" name="tablaPager" id="tablaPager"  dojoType="dijit.form.TextBox"/>
	
	<div class="formApp miEspacio">
		<h2>Mi espacio</h2>
		<div class="tab_block">
			<div align="left" id="returnME" style="display:block;">
		        <a
					href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');"
					class="expand"> <strong>Ir al inicio de Mi espacio</strong></a>
		    </div>
		    <div class="Tab_espacio">
		        <h3>Encontrar ofertas de empleo</h3>
	            <div class="subTab">
	                <ul>
	                	<li><a href="<c:url value="/ofertasPerfiles.do?method=init&tipoConsulta=1"/>">Ofertas de acuerdo a mi perfil</a></li>                    
	                    <li class="curSubTab"><span>Búsqueda parametrizable</span></li>
	                    <li><a href="<c:url value="/bolsasTrabajo.do?method=init"/>">Buscar en otras bolsas de trabajo</a></li>
	                </ul>
	                <div class="clearfix"></div>
	            </div>
		    </div>
		        
	        <div>
           		<p class="labeled">Los datos marcados con <span>*</span> son obligatorios</p>
	        </div>
		 </div>      

		<fieldset>
			<legend>Parámetros</legend>
			<div dojoType="dojo.data.ItemFileReadStore" jsId="carreraStore" 
				urlPreventCache="true" clearOnClose="true"></div>	
			<div dojoType="dojo.data.ItemFileReadStore" jsId="gradoAcademicoStore" 
				urlPreventCache="true" clearOnClose="true" url="${context}grados.do?method=cargarGrados"></div>		
			<div dojoType="dojo.data.ItemFileReadStore" jsId="dominioStore"  
				clearOnClose="true" urlPreventCache="true" url="${context}idiomas.do?method=dominios"></div>
				
			<div class="margin_top_20">
				<label class="labeled"> Edad: ${OfertasPorParametrosForm.getEdad()} años</label>						
			</div>	
																
			<div class="margin_top_20">																						
				<div class="grid1_3 fl">
					<label for="idNivelEstudioSelect"><span>*</span> Último grado de estudios</label> 							
					<select dojotype="dijit.form.FilteringSelect" value='${OfertasPorParametrosForm.idNivelEstudio}'
						id="idNivelEstudioSelect" required="true"  name="idNivelEstudio"
						missingMessage="Debe seleccionar el último grado de estudios" autocomplete="true">
						<c:forEach var="row" items="${opcGrados}">
							<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="grid1_3 fl">					
					<label for="idCarreraEspecialidadSelect"><span>*</span> Carrera o especialidad</label> 					
					<select dojotype="dijit.form.FilteringSelect" store="carreraStore" name="idCarreraEspecialidad"
						scrollOnFocus="true" maxHeight="250" id="idCarreraEspecialidadSelect" required="true"
						missingMessage="Debe seleccionar la carrera o especialidad" autocomplete="true">
					</select>
				</div>
			</div>	

			<div class="clearfix"></div>
			
			<div class="margin_top_20">
				<div class="grid1_3 fl">
					<label for="idExperienciaSelect"><span>*</span> Años de experiencia</label> 
					<select id="idExperienciaSelect" name="idExperiencia" dojotype="dijit.form.FilteringSelect"
						required="true" missingMessage="Debe seleccionar los años que tiene de experiencia en el puesto."
						autocomplete="true" value="${OfertasPorParametrosForm.idExperiencia}">
						<c:forEach var="row" items="${opciExperiencia}">
							<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="clearfix"></div>
			
			<div class="margin_top_20">
				<div class="grid1_3 fl">
					<label for="idIdiomaSelect"><span>*</span> Idioma adicional al nativo</label> 
					<select id="idIdiomaSelect" name="idIdioma" required="true"
						missingMessage="Es necesario indicar el idioma adicional"
						dojotype="dijit.form.FilteringSelect"  readOnly="false"
						value="${OfertasPorParametrosForm.idIdioma}"
						onchange="javascript:actualizaDominio();">
						<c:forEach var="row" items="${opcIdioma}">
							<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
						</c:forEach>
					</select>
				</div>

				<div class="grid1_3 fl">
					<label id="idLabelDominioIdioma" for="idDominioIdiomaSelect"> Dominio del idioma</label> 
					<select id="idDominioIdiomaSelect" name="idDominioIdioma"
						required="false" readOnly="false" dojotype="dijit.form.FilteringSelect"
						missingMessage="Es necesario indicar el dominio requerido del idioma"
						store="dominioStore"							
						value="${OfertasPorParametrosForm.idDominioIdioma}">
						<c:forEach var="row" items="${opcDominio}">
							<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="clearfix"></div>
			
			<div class="margin_top_20">
				<div class="grid1_3 fl">
					<label for="salarioPretendido"><span>*</span> Salario</label>
					<input type="text"
						name="salarioPretendido" id="salarioPretendido"
						value="${OfertasPorParametrosForm.salarioPretendido}" maxlength="9"
						style="width: 10em;" dojoType="dijit.form.ValidationTextBox"
						regExp="^[+]?\d{1,6}(\.\d{1,2})?$" required="true"
						missingMessage="Debe indicar el salario mensual pretendido." />
				</div>
			</div>	
			
			<div class="clearfix"></div>
			
			<!-- div class="margin_top_20 disponib_c">
				<div class="grid1_3 fl">
					<div class="labeled">
						<span>*</span> ¿Puedes viajar?
					</div>
					<div>
						
						<input type="radio" data-dojo-type="dijit.form.RadioButton" class="fl"
							name="disponibilidadViajar" id="disponibilidadViajar1" value="${dispViajarSI}"
							${OfertasPorParametrosForm.disponibilidadViajar eq dispViajarSI ? 'checked="checked"' : '' } />	
						<label for="disponibilidadViajar1">Si</label> 																					
						<input type="radio" data-dojo-type="dijit.form.RadioButton" class="fl"
							name="disponibilidadViajar" id="disponibilidadViajar2" value="${dispViajarNO }"
							${OfertasPorParametrosForm.disponibilidadViajar eq dispViajarNO ? 'checked="checked"' : '' } />	
						<label for="disponibilidadViajar2">No</label>							
					</div>
				</div>
				<div class="grid1_3 fl">
					<div class="labeled">
						<span>*</span> ¿Puedes radicar en otra ciudad?
					</div>
					<div>
						
						<input type="radio" data-dojo-type="dijit.form.RadioButton" class="fl"
							name="disponibilidadRadicar" id="disponibilidadRadicar1" value="${dispRadicarSI}"
							${OfertasPorParametrosForm.disponibilidadRadicar eq dispRadicarSI ? 'checked="checked"' : '' } />
						<label for="disponibilidadRadicar1">Si</label> 
						
						<input type="radio" data-dojo-type="dijit.form.RadioButton" class="fl"
							name="disponibilidadRadicar" id="disponibilidadRadicar2" value="${dispRadicarNO}"
							${OfertasPorParametrosForm.disponibilidadRadicar eq dispRadicarNO ? 'checked="checked"' : '' } />	
						<label for="disponibilidadRadicar2">No</label>							
					</div>
				</div>
			</div -->
			
			<div class="clearfix"></div>
			
			<div class="margin_top_20">
				<div class="grid1_3 fl">
					<label for="idEntidadSelect"><span>*</span> Entidad Federativa donde radicas</label> 							
					<select dojotype="dijit.form.FilteringSelect" required="true" invalidMessage="Dato no válido"
						missingMessage="Es necesario indicar la entidad." autocomplete="true"
						id="idEntidadSelect" name="idEntidad"
						scrollOnFocus="true" maxHeight="250"
						value="${OfertasPorParametrosForm.idEntidad}">
						<c:forEach var="row" items="${opcEntidad}">
							<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
						</c:forEach>
					</select>
				</div>
			</div>					
		</fieldset>	

		<!-- div class="escolaridad">
			<fieldset class="habilidades">
				<legend>Habilidades y actitudes</legend>
				<div class="habilidades bloque" id="listhabilidades">
					<div class="labeled">
						<span>*</span> Selecciona máximo 5 habilidades y actitudes que te
						caracterizan.
					</div>
	
					<fmt:formatNumber var="numbloque" value="${fn:length(opcHabilidades) / 3}" maxFractionDigits="0" />
	
					<c:forEach var="habilidadOpc" items="${opcHabilidades}" varStatus="index">
						<c:set var="open" value="${index.count==1 || index.count==numbloque+1 || index.count==(numbloque*2)+1}" />
						<c:set var="close" value="${index.count==numbloque || index.count==(numbloque*2) || index.count==fn:length(opcHabilidades)}" />		
						<c:set var="checkmedio" value="" />
	
						<c:forEach var="habilidadSaved" items="${OfertasPorParametrosForm.idHabilidades}">
							<c:if test="${habilidadOpc.idCatalogoOpcion == habilidadSaved}">
								<c:set var="checkmedio" value="checked" />
							</c:if>
						</c:forEach>
	
						<c:if test="${open}">
							<ul>
						</c:if>
						
						<li>
							<input type="checkbox" id="idHabilidad${habilidadOpc.idCatalogoOpcion}"
								name="idHabilidad" value="${habilidadOpc.idCatalogoOpcion}"
								${checkmedio} dojoType="dijit.form.CheckBox"/> 
							<label for="idHabilidad${habilidadOpc.idCatalogoOpcion}">${habilidadOpc.opcion}</label>
						</li>
	
						<c:if test="${close}">
							</ul>
						</c:if>
	
					</c:forEach>
				</div>		
			</fieldset>													
		</div -->
			
		<fieldset>
			<div class="form_nav">
				<input type="button" onclick="doReset();" value="Restablecer">
				<input type="button" onclick="validaForm();" value="Buscar">
			</div>
		</fieldset>
         
		<br/><br/>
		
		<div id="divOfertas" name="divOfertas"></div>	
		<input type="hidden" name="PageActual" value="">
		<div class="borde_inferior"></div>
	
		<% if (numOfertas > NUMERO_MAX_REGISTROS) { %>
		
				<p align="center" >
						<a href="javascript:doSubmitPagerCand('prev')">&lt;&lt;&lt;&nbsp;</a>
						&nbsp;&nbsp;
							..
							<input type="text" id="numeroPage" value="1" size="2" maxlength="2"  style="border: none; width:15px;"  />
							.. 
						&nbsp;&nbsp;
						<a href="javascript:doSubmitPagerCand('next')">&nbsp;&gt;&gt;&gt;</a>
						 de <%=NUMERO_PAGUINAS %> Páginas 
				</p>
				<p align="center">Mostrando 
					<input type="text" id="numeroOfertaVistas" value="<%=NUMERO_MAX_REGISTROS %>" size="2" maxlength="2" style="border: none; width:25px;" /> de <%=numOfertas %> Ofertas</p>
			
		<% } %>
	</div>
</form>