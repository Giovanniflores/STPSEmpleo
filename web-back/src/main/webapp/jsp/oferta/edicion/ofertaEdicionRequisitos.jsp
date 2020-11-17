<%@page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import="mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO"%>
<%@page import="mx.gob.stps.portal.core.oferta.registro.vo.RequisitoVO"%>
<%@page import="mx.gob.stps.portal.core.oferta.registro.vo.OfertaIdiomaVO"%>
<%@page import="mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO"%>
<%@page import="org.apache.struts.util.MessageResources"%>
<%@page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link   href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	#dialogDetalle_underlay { background-color:gray; }
</style>

<% 
List<CatalogoOpcionVO> listaGradoEstudios= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_GRADO_ESTUDIOS");	
List<CatalogoOpcionVO> listaCarreraEspecialidad= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_GRADO_ESTUDIOS");	
List<CatalogoOpcionVO> listaSituacionAcademica= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_SITUACION_ACADEMICA");	
List<CatalogoOpcionVO> listaIdioma= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_IDIOMA");	
List<CatalogoOpcionVO> listaDominio= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_DOMINIO");	
List<CatalogoOpcionVO> listaExperiencia= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_EXPERIENCIA");	

String context = request.getContextPath() +"/";

List<RequisitoVO> listaRequisitosOferta= (List<RequisitoVO>) request.getSession().getAttribute("listaRequisitosOferta");
List<OfertaCarreraEspecialidadVO> listaCarrerasEspecialidadesOferta= (List<OfertaCarreraEspecialidadVO>) request.getSession().getAttribute("listaCarrerasEspecialidadesOferta");
OfertaCarreraEspecialidadVO carrera1=new OfertaCarreraEspecialidadVO();
if(listaCarrerasEspecialidadesOferta!=null)
	if(listaCarrerasEspecialidadesOferta.size()>0){
 carrera1=listaCarrerasEspecialidadesOferta.get(0);
listaCarrerasEspecialidadesOferta.remove(0);
	}

request.getSession().setAttribute("carrera1",carrera1);

RequisitoVO conocimientoP=(RequisitoVO)request.getSession().getAttribute("conocimientoPrincipal");
if(conocimientoP==null){
	conocimientoP=new RequisitoVO();
	conocimientoP.setDescripcion("");
}
RequisitoVO habilidadP=(RequisitoVO)request.getSession().getAttribute("habilidadPrincipal");
if(habilidadP==null){ 
	habilidadP=new RequisitoVO();
	habilidadP.setDescripcion("");
	}
RequisitoVO competenciaP=(RequisitoVO)request.getSession().getAttribute("competenciaPrincipal");
if(competenciaP==null) {
	competenciaP=new RequisitoVO();
	competenciaP.setDescripcion("");
	}

ArrayList<OfertaIdiomaVO> idiomas=(ArrayList<OfertaIdiomaVO>)request.getSession().getAttribute("listaIdiomasOferta");
OfertaIdiomaVO idioma1=new OfertaIdiomaVO();
for(int i=0;i<idiomas.size();i++){
	OfertaIdiomaVO idioma=idiomas.get(i);
	if(idioma.getPrincipal()==1){
		idioma1=idioma;
		idiomas.remove(i);
		}
	}	
request.getSession().setAttribute("idioma1",idioma1);
String vProxy = context + "/SpellCheck.do?method=gogiespell&lang=";
%>

<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");    
    dojo.require("dijit._Calendar");
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.DateTextBox");    
    dojo.require("dijit.form.NumberTextBox");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore"); 
	dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.form.CheckBox");   
    dojo.require("dijit.form.FilteringSelect");
    
    
    function caracteresValidos(e){
    	var tecla_codigo = (document.all) ? e.keyCode : e.which;
    	if(tecla_codigo==8 || tecla_codigo==0) return true;
    	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) return true; //vocales minusculas con acento
    	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) return true; //vocales mayuzculas con acento
    	if (tecla_codigo==209 || tecla_codigo==241 ) return true; //� y �
   		
    	var patron =/[A-Za-z\s\-.������������0-9.,;:]/;
    	tecla_valor = String.fromCharCode(tecla_codigo);
    	return patron.test(tecla_valor);

    }
    
    
    function ligaCarrea()
    {
    	window.open("${pageContext.request.contextPath}/ofertaEdicionCarreras.do?method=carrerasSimilaresInit&cid="+document.registroRequisitosFormBean.carrera.value, 'popupwindow', 'width=600,height=650,scrollbars,resizable'); 
    }
    
    function ligaIdioma()
    {
    	
    	window.open("${pageContext.request.contextPath}/ofertaEdicionExperiencia.do?method=idiomaInit&iid="+document.registroRequisitosFormBean.idioma1.value, 'popupwindow', 'width=600,height=350,scrollbars,resizable'); 
    }

    function changeTypeRequired(){
    	
    	var widget1 = dijit.byId('edadDe');
    	var widget2 = dijit.byId('edadA');
    	
    	if(document.registroRequisitosFormBean.edadRequerida.checked == true){
    		widget1.setDisabled(true);
    		widget1.attr('value', '');
    		widget2.setDisabled(true);
    		widget2.attr('value', '');
    		
    	} else {
    		widget1.setDisabled(false);
    		widget2.setDisabled(false);
    		
    	}
    }
   
    function doSubmit(method) {
		document.registroRequisitosFormBean.method.value = method;
		document.registroRequisitosFormBean.Guardar.disabled=true;
		if(validaDatos()){
			document.registroRequisitosFormBean.submit();
    		return true;
    	   }
    	else
    		{
    		document.registroRequisitosFormBean.Guardar.disabled=false;
    		return false;
    		}
  		
	}

	 function validaDatos(){       
	        
	    	if(!dijit.byId('estudiosSelect').isValid()){
	    		dijit.byId('estudiosSelect').focus();
	    		mensaje("El siguiente campo muestra muestra datos invalidos: �ltimo grado de estudios. ");								
	    	return false;
	    	}	

	    	 if(dijit.byId('carreraSelect').get('value')>0)
	    		 if(document.registroRequisitosFormBean.situacionAcademica.value=="-1")
	    			   {
	    				 document.registroRequisitosFormBean.situacionAcademica.focus();
	    				 mensaje("El siguiente campo muestra muestra datos invalidos: Situacion Acad�mica. ");								
	    		        	return false;
	    			 
	    			   }
	    	
	    	 if(document.registroRequisitosFormBean.conocimientosGenerales.value == "" ){
		    		document.registroRequisitosFormBean.conocimientosGenerales.focus();
		    		mensaje("El siguiente campo muestra muestra datos invalidos: Conocimientos y habilidades generales. ");			
					
				return false;
		    	}
	    	 
	    	
	    	if(document.registroRequisitosFormBean.aniosExperiencia.value == "-1" ){
	    		document.registroRequisitosFormBean.aniosExperiencia.focus();
				mensaje("El siguiente campo muestra muestra datos invalidos: A�os de experiencia.");
				
				
			return false;
	    	}
	    	
	    	if(document.registroRequisitosFormBean.conocimiento1.value != "" ){
	    		if(document.registroRequisitosFormBean.aniosExperienciaConoc.value == "-1"){
	    			document.registroRequisitosFormBean.aniosExperienciaConoc.focus();
	    			mensaje("El siguiente campo muestra muestra datos invalidos: Conocimiento, a�os de experiencia");
	    			return false;
					}
	    		
	    		if(document.registroRequisitosFormBean.dominioConoc.value == "-1"){
	    			document.registroRequisitosFormBean.dominioConoc.focus();
	    			mensaje("El siguiente campo muestra muestra datos invalidos: Conocimiento, dominio");
	    			return false;
					}
			
	    	}
	    	
	    	
	    	if(document.registroRequisitosFormBean.habilidad1.value != "" ){
	    		if(document.registroRequisitosFormBean.aniosExperienciaHabilidad.value == "-1"){
	    			document.registroRequisitosFormBean.aniosExperienciaHabilidad.focus();
	    			mensaje("El siguiente campo muestra muestra datos invalidos: Habilidad, a�os de experiencia");
	    			return false;
					}
	    		
	    		if(document.registroRequisitosFormBean.dominioHabilidad.value == "-1"){
	    			document.registroRequisitosFormBean.dominioHabilidad.focus();
	    			mensaje("El siguiente campo muestra muestra datos invalidos:Habilidad, dominio");
	    			return false;
					}
			
	    	}
	    	
	    	
	    	if(document.registroRequisitosFormBean.competencia1.value != "" ){
	    		if(document.registroRequisitosFormBean.aniosExperienciaComp.value == "-1"){
	    			document.registroRequisitosFormBean.aniosExperienciaComp.focus();
	    			mensaje("El siguiente campo muestra muestra datos invalidos: Competencia, a�os de experiencia");
	    			return false;
					}
	    		
	    		if(document.registroRequisitosFormBean.dominioComp.value == "-1"){
	    			document.registroRequisitosFormBean.dominioComp.focus();
	    			mensaje("El siguiente campo muestra muestra datos invalidos: Competencia, dominio");
	    			return false;
					}
			}
	    	
	    	
	    	if(dijit.byId('idiomaSelect').get('value')>1 && dijit.byId('idiomaSelect').get('value')<9){
	    		if(dijit.byId('certificacionSelect').get('value')<=0){
	    			dijit.byId('certificacionSelect').focus();
	    			mensaje("El siguiente campo muestra muestra datos invalidos: Certificaci�n ");
	    			return false;
	    		}
	    		
	    		if(document.registroRequisitosFormBean.dominioIdioma.value=="-1"){
	    			document.registroRequisitosFormBean.dominioIdioma.focus();
	    			mensaje("El siguiente campo muestra muestra datos invalidos: Idioma, dominio");
	    			return false;
	    		}
	    		
	    	} 
	    	
	    	

	    	if(document.registroRequisitosFormBean.disponibilidadViajar[0].checked==false && document.registroRequisitosFormBean.disponibilidadViajar[1].checked==false ) {
	    		document.registroRequisitosFormBean.disponibilidadViajar[0].focus();
	    		mensaje("El siguiente campo muestra muestra datos invalidos: Disponibilidad para viajar.");
	    		return false;
	    	}
	    	

	    	if(document.registroRequisitosFormBean.disponibilidadRadicar[0].checked==false && document.registroRequisitosFormBean.disponibilidadRadicar[1].checked==false) {
	    		document.registroRequisitosFormBean.disponibilidadRadicar[0].focus();
	    		mensaje("El siguiente campo muestra muestra datos invalidos: Disponibilidad para radicar en otra ciudad.");
	    		return false;
	    	}
	    	
	    	
	    	
	    	if(document.registroRequisitosFormBean.edadRequerida.checked == false){
	    		
	    		if(!dijit.byId('edadDe').isValid() || !dijit.byId('edadDe').get('value')){
	    			dijit.byId('edadDe').focus();
		    		mensaje("El siguiente campo muestra muestra datos invalidos: Edad de. ");								
		    	return false;
		    	}	
	    		
	    		if(!dijit.byId('edadA').isValid() || !dijit.byId('edadA').get('value')){
	    			dijit.byId('edadA').focus();
		    		mensaje("El siguiente campo muestra muestra datos invalidos: Edad a. ");								
		    	return false;
		    	}	
	    		
	    	}
	    	
	    	if(document.registroRequisitosFormBean.genero[0].checked==false && document.registroRequisitosFormBean.genero[1].checked==false && document.registroRequisitosFormBean.genero[2].checked==false ) {
	    		document.registroRequisitosFormBean.genero[0].focus();
	    		mensaje("El siguiente campo muestra muestra datos invalidos: G�nero.");
	    		return false;
	    	}
	    	
	    	
	    return true;	
	 }
	
	 function cancelaRegistro(usuario) {

	    	if(confirm("Los datos no guardados se perder�n �Continuar?")) {
	    		
	    		if(usuario=='200')
	        		document.registroRequisitosFormBean.action='<c:url value="/OfertaNavegacion.do?method=init"/>';
	        	if(usuario=='100')
	            	document.registroRequisitosFormBean.action="autorization.do?method=init";
	            		
	    		document.registroRequisitosFormBean.submit();
	    	} 
	    } 
	
	 function mensaje(mensaje){
			dojo.byId('mensaje').innerHTML = mensaje;
			dijit.byId('MensajeAlert').show();		
		} 
    
	 function habilitaCombosConocimiento(){
		 if(document.registroRequisitosFormBean.conocimiento1.value=="")
		 {
		 	document.registroRequisitosFormBean.aniosExperienciaConoc.disabled=true;
		 	document.registroRequisitosFormBean.dominioConoc.disabled=true;
		 }
		 else{
			 document.registroRequisitosFormBean.aniosExperienciaConoc.disabled=false;
			 document.registroRequisitosFormBean.dominioConoc.disabled=false;
			 
			 }
	 }
    
	 function habilitaCombosCompetencia(){
		 if(document.registroRequisitosFormBean.competencia1.value=="")
		 {
		 	document.registroRequisitosFormBean.aniosExperienciaComp.disabled=true;
		 	document.registroRequisitosFormBean.dominioComp.disabled=true;
		 }
		 else{
			 document.registroRequisitosFormBean.aniosExperienciaComp.disabled=false;
			 document.registroRequisitosFormBean.dominioComp.disabled=false;
			 
			 }
	 }
	 function habilitaCombosHabilidad(){
		 if(document.registroRequisitosFormBean.habilidad1.value=="")
		 {
		 	document.registroRequisitosFormBean.aniosExperienciaHabilidad.disabled=true;
		 	document.registroRequisitosFormBean.dominioHabilidad.disabled=true;
		 }
		 else{
			 document.registroRequisitosFormBean.aniosExperienciaHabilidad.disabled=false;
			 document.registroRequisitosFormBean.dominioHabilidad.disabled=false;
			 
			 }
	 }
	 
	 var dialogDetalleEmpresa;

	 function showDetalleEmpresa(idEmpresa){

	 	dialogDetalleEmpresa = new dijit.Dialog({
	         title: 'Detalle de registro',
	         href: '${context}autorization.do?method=detalleEmpresa&idEmpresa='+ idEmpresa,
	         style: 'width:800px;height:600px;',
	         showTitle: false, draggable : false, closable : false
	     });

	 	dojo.style(dialogDetalleEmpresa.closeButtonNode,"display","none");	
	 	dialogDetalleEmpresa.show();
	 }

	 function closeDetalleEmpresa(){
	 	dialogDetalleEmpresa.hide();
	 }
</script>

<jsp:useBean id="ofertaVO" scope="session" class="mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO" />

<form name="registroRequisitosFormBean" action="ofertaEdicionRequisitos.do" method="post">

<input type="hidden" name="flagEstudios" value="0"/>
<input type="hidden" name="flagIdioma" value="0"/>
<input type="hidden" name="idOferta" value='<%=request.getAttribute("idOferta")%>'/>

	<input type="hidden" name="method" value="init" />
 <div class="div_tab">
			
			<input type="button" class="boton_divisor" value=" " />
			<input type="button" class="boton_tab${thisTabId == 1? '_sel':'' }" value="${thisTabId == 1? thisTabName:'1' }"  />
			<input type="button" class="boton_separador" value="  " />
			<input type="button" class="boton_tab${thisTabId == 2? '_sel':'' }" value="${thisTabId == 2? thisTabName:'2. Requisitos de la oferta de empleo' }"  />
			<input type="button" class="boton_separador" value="  " />
			<input type="button" class="boton_tab${thisTabId == 3? '_sel':'' }" value="${thisTabId == 3? thisTabName:'3' }" />
			<input type="button" class="boton_separador" value="  " />
			<input type="button" class="boton_divisor_fin" value="" />
			
	</div>

    <c:if test="${not empty USUARIO_APP and (USUARIO_APP.publicador or USUARIO_APP.administrador)}">
    <div class="entero" style="text-align: left;">
		<input type="button" value="Detalle Empresa" class="boton" onClick="showDetalleEmpresa(${ofertaVO.idEmpresa});"/>
    </div>    
    </c:if>

 	<div class="entero">
 		<span class="un_medio">
		<strong>�ltimo grado de estudios*</strong>
				<input type="hidden" name="estudios" id="estudios" value="" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="estudiosStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="estudiosStore" id="estudiosSelect" required="true" disabled="true" autoComplete="false" style="width:100%"></select>
      </span>
	</div>

 	<div class="entero">
       <span class="un_medio">
        <strong>Carrera o especialidad*</strong>
				<input type="hidden" name="carrera" id="carrera" value="" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="carreraStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="carreraStore" id="carreraSelect"   style="width:100%"></select>
				<br>
				 <a	href="javascript:ligaCarrea()">  Agregar similares</a>
       </span>       
	</div>

 	<div class="entero">
        <span class="un_tercio">
        <strong>Situaci�n acad�mica*</strong>
        <select name="situacionAcademica" disabled>
		<option value="-1">Seleccione</option>
		<%for (CatalogoOpcionVO catalogoOpcionVO : listaSituacionAcademica){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"
			<%=ofertaVO.getIdSituacionAcademica()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>
		</select>
        </span>
	</div>

<br>
<%--
<div class="entero">
<span><strong>Conocimientos y habilidades generales*</strong></span><br>
<span>
			        	
 
				
				 <textarea name="conocimientosGenerales" id="conocimientosGenerales" maxlength="2000" 
	        	onkeypress="return caracteresValidos(event);"
	        	regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$" 
	        	style="width:550px;min-height:120px;_height:200px;" 
	        	require="false"><%=ofertaVO.getHabilidadGeneral() %></textarea><!-- OnChange="Upper(this.form.funcion)" -->
	        	<script type="text/javascript">
	         		var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
	         		vSpellCon.setLanguages({'es': 'Espa�ol'});
	         		vSpellCon.hideLangWindow();
	         		vSpellCon.decorateTextarea("conocimientosGenerales");
				</script>
    
						        	
</span>
</div>
 --%>
	<br>


<div class="entero" >
<span>
<strong>
A�os de experiencia*</strong><select
		name="aniosExperiencia">
		<option value="-1">Seleccione</option>
		<%for (CatalogoOpcionVO catalogoOpcionVO : listaExperiencia){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"
			<%=ofertaVO.getExperienciaAnios()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>
	</select> 
</span>
</div>
	
	
	

	<div class="entero">
	<span ><strong>
	Conocimientos de acuerdo al area laboral</strong></span>
	</div>
    <br>
    
	<div class="entero">
	<span class="un_tercio">
	<strong>Conocimiento</strong><br>
	<input type="text" name="conocimiento1" value='<%=conocimientoP.getDescripcion() %>' style="width: 50%" maxlength="50" /><br>
	<a href="${pageContext.request.contextPath}/ofertaEdicionExperiencia.do?method=conocimientoInit"
		onclick="window.open(this.href, 'popupwindow', 'width=600,height=350,scrollbars,resizable'); return false;">Agregar
	conocimiento</a>
	</span> 
	
	<span class="un_tercio" >
	<strong>Experiencia</strong><br>
	<select
		name="aniosExperienciaConoc">
		<option value="-1">Seleccione</option>
		<%for (CatalogoOpcionVO catalogoOpcionVO : listaExperiencia){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=conocimientoP.getExperiencia()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>
	</select>
	</span>

	<span class="un_tercio">
	<strong>Dominio</strong><br>
	<select name="dominioConoc">
		<option value="-1">Seleccione</option>
		<%for (CatalogoOpcionVO catalogoOpcionVO : listaDominio){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=conocimientoP.getDominio()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %> ><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>
	</select>
	</span>
	
	</div>

<br><br>


<div class="entero">
	<span class="un_tercio">
	<strong>Habilidad</strong><br>
	<input type="text" name="habilidad1" value='<%=habilidadP.getDescripcion() %>' style="width: 50%" maxlength="50" /><br>
	<a href="${pageContext.request.contextPath}/ofertaEdicionExperiencia.do?method=habilidadInit"
		onclick="window.open(this.href, 'popupwindow', 'width=600,height=350,scrollbars,resizable'); return false;">Agregar
	habilidad</a>
	</span> 
	
	<span class="un_tercio">
	<strong>Experiencia</strong><br>
	<select name="aniosExperienciaHabilidad">
		<option value="-1">Seleccione</option>
		<%for (CatalogoOpcionVO catalogoOpcionVO : listaExperiencia){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"
			<%=habilidadP.getExperiencia()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>
	</select>
	</span>

	<span class="un_tercio">
	<strong>Dominio</strong><br>
	<select name="dominioHabilidad">
		<option value="-1">Seleccione</option>
		<%for (CatalogoOpcionVO catalogoOpcionVO : listaDominio){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=habilidadP.getDominio()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>
	</select>
	</span>
	
	</div>

<br><br>
<div class="entero">
	<span ><strong>
	Competencias Gerenciales</strong></span>
	</div>
    <br>

<div class="entero">
	<span class="un_tercio">
	<strong>Competencia </strong><br>
	<input type="text" name="competencia1" value='<%=competenciaP.getDescripcion() %>' style="width:50%" maxlength="50" /><br>
	<a href="${pageContext.request.contextPath}/ofertaEdicionExperiencia.do?method=competenciaInit"
		onclick="window.open(this.href, 'popupwindow', 'width=600,height=350,scrollbars,resizable'); return false;">Agregar
	competencia</a>
	</span> 
	
	<span class="un_tercio">
	<strong>Experiencia</strong><br>
	<select name="aniosExperienciaComp">
		<option value="-1">Seleccione</option>
		<%for (CatalogoOpcionVO catalogoOpcionVO : listaExperiencia){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>" <%=competenciaP.getExperiencia()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>
	</select> 
	</span>

	<span class="un_tercio">
	<strong>Dominio</strong><br>
	<select name="dominioComp">
		<option value="-1">Seleccione</option>
		<%for (CatalogoOpcionVO catalogoOpcionVO : listaDominio){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>" <%=competenciaP.getDominio()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>
	</select>
	</span>
	
	</div>

<br>


<div class="entero">
	<span class="un_tercio">
	<strong>Idioma* </strong><br>
	<input type="hidden" name="idioma1" id="idioma1" value="-1" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStore" urlPreventCache="true" clearOnClose="true"></div>
				<select dojotype="dijit.form.FilteringSelect" store="idiomaStore" id="idiomaSelect"  disabled="true" autoComplete="false" ></select><br>
	<a href="javascript:ligaIdioma()" style ='display:none' id="linkAgregarIdioma" >Agregar idiomas</a>
	</span> 
	
	<span class="un_tercio">
	<strong id="lblCertificacionIdioma">Certificaci�n*</strong><br />
	<input type="hidden" name="certificacionIdioma" id="certificacionIdioma" value="-1" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="certificacionStore" urlPreventCache="true" clearOnClose="true"></div>
				<select dojotype="dijit.form.FilteringSelect" store="certificacionStore" id="certificacionSelect"  disabled="true" autoComplete="false"></select>
	</span>

	<span class="un_tercio">
	<strong id="lblDominioIdioma">Dominio*</strong><br />
	<select name="dominioIdioma">
		<option value="-1">Seleccione</option>
		<%for (CatalogoOpcionVO catalogoOpcionVO : listaDominio){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>" <%=idioma1.getIdDominio()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>
	</select>
	</span>
	
	</div>

	
<br><br>



	<div class="entero">
	<span class="entero">Disponibilidad para viajar*
	<input type=radio name="disponibilidadViajar" value=2 <%=ofertaVO.getDisponibilidadViajar()==2?"checked":"" %> />Si
	<input type=radio name="disponibilidadViajar" value=1 <%=ofertaVO.getDisponibilidadViajar()==1?"checked":"" %> />No
	</span> 
	</div>
	<div class="entero">
   <span class="entero"> Disponibilidad	para radicar en otra ciudad*
   <input type=radio name="disponibilidadRadicar" value=2 <%=ofertaVO.getDisponibilidadRadicar()==2?"checked":"" %> />Si
	<input type=radio name="disponibilidadRadicar" value=1 <%=ofertaVO.getDisponibilidadRadicar()==1?"checked":"" %> />No
     </span> 
	</div>

<br>

	<div class="entero">
	<span class="un_medio">
    <input type=checkbox name="edadRequerida" <%=ofertaVO.getEdadRequisito()==1?"checked":"" %> onchange="changeTypeRequired()"  ></checkbox>
		Rango de edad (No es requisito)
	</span>
	
	<span class="un_medio" >
	De: 
		<input id="edadDe" name="edadDe" maxlength="5" size="5"  value='<%=ofertaVO.getEdadMinima() >1?ofertaVO.getEdadMinima():"" %>'     	
			        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,2}$"
			        	invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" style="width: 50px"/>
		
	&nbsp;&nbsp;&nbsp;&nbsp;
	A: 
		<input id="edadA" name="edadA" maxlength="5" size="5"  value='<%=ofertaVO.getEdadMaxima() >1?ofertaVO.getEdadMaxima():"" %>'     	
			        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,2}$"
			        	invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" style="width: 50px"/>
	</span>	
	</div>

	<div class="entero"><span><strong>Genero* </strong></span> <span> Masculino<input
		type="radio" name="genero" value="1"
		<%=ofertaVO.getGenero()==1?"checked":"" %> /></span> <span>Femenino<input
		type="radio" name="genero" value="2"
		<%=ofertaVO.getGenero()==2?"checked":""%> /></span> <span>No es requisito<input
		type="radio" name="genero" value="3"
		<%=ofertaVO.getGenero()==3?"checked":""%> /></span>
	</div>

<br/>	

<div class="entero">
<span><strong>	Observaciones</strong><br>
	 

 	

 <textarea name="observaciones" id="observaciones" maxlength="2000" 
	        	onkeypress="return caracteresValidos(event);"
	        	regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$" 
	        	style="width:550px;min-height:120px;_height:200px;" 
	        	require="false"><%=ofertaVO.getObservaciones() %></textarea><!-- OnChange="Upper(this.form.funcion)" -->
	        	<script type="text/javascript">
	         		var vSpellFuncion1 = new GoogieSpell("googiespell/", '<%=vProxy%>');
	         		vSpellFuncion1.setLanguages({'es': 'Espa�ol'});
   					vSpellFuncion1.hideLangWindow();
  					vSpellFuncion1.decorateTextarea("observaciones");
				</script>
    


	</span>
</div>




<br>

	<div class="entero">
	<span class="un_tercio"></span>
	<span class="un_tercio"><input type="button" value="Continuar" class="boton"	name="Guardar" onclick="doSubmit('guardarRequisitos');" />
	<input type="button" value="Cancelar" class="boton" onClick="cancelaRegistro(${usuario});"/></span>
	<span class="un_tercio"></span>
	  </div>


 
    <div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none">    
               <table width="500px" height="100px" >
                  <tr align="center">
                  <td><div id ="mensaje" style="width:500px;height:100px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>                   
                  </tr>
                </table>                                
    </div>


</form>


<script type="text/javascript">
function idiomaIninguno(){			
		var certificacionSelect = dijit.byId('certificacionSelect');        	
		var dominioIdioma = document.registroRequisitosFormBean.dominioIdioma;
		
		dominioIdioma.disabled=true;        		
		setEnableWg('certificacionSelect', false);
		certificacionSelect.attr('required', false);
		   
		document.getElementById('lblCertificacionIdioma').innerHTML = 'Certificaci�n';
		document.getElementById('lblDominioIdioma').innerHTML = 'Dominio';
		document.getElementById('linkAgregarIdioma').style.display='none';
		
		certificacionSelect.reset();
}

function setEnableWg(id, habilita) {
	if (habilita) { //Habilita Widget
		dijit.byId(id).attr('disabled', '');
		dojo.removeAttr(id, 'disabled');
	} else { //Deshabilita Widget
		dijit.byId(id).attr('disabled', 'disabled');
	}
}


var indice = false;	

dojo.addOnLoad(function() {
	changeTypeRequired();
	var idiomaVar = ${idioma1.idIdioma};
	
	dijit.byId('idiomaSelect').disabled=false;
	idiomaStore.url = "${context}ofertaEdicionExperiencia.do?method=obtenerIdioma";
	idiomaStore.close();      
	
	dijit.byId('certificacionSelect').disabled=false;
	certificacionStore.url = "${context}ofertaEdicionExperiencia.do?method=obtenerCertificacion" + "&" + "idioma="+ idiomaVar;
	certificacionStore.close();
	
	if(idiomaVar == 1 )
		idiomaIninguno();
	else
	   document.getElementById('linkAgregarIdioma').style.display='block';

	dojo.connect(dijit.byId("idiomaSelect"), "onChange", function() {		
	
		if(!indice){
			if(document.registroRequisitosFormBean.flagIdioma.value=="1"){
				dijit.byId('certificacionSelect').reset();
				document.registroRequisitosFormBean.certificacionIdioma.value="-1";
			} else{
				document.registroRequisitosFormBean.flagIdioma.value="1";
	
				var vIdioma = dijit.byId('idiomaSelect').get('value');
					
	        	dijit.byId('certificacionSelect').disabled=false;
	        		
	        	certificacionStore.url = "${context}ofertaEdicionExperiencia.do?method=obtenerCertificacion" + "&" + "idioma="+ vIdioma;
	        	certificacionStore.close();
	        	  document.registroRequisitosFormBean.idioma1.value=dijit.byId('idiomaSelect').get('value');
	              
			}
			indice = true;
		}else {
			
			var vIdioma = dijit.byId('idiomaSelect').get('value');			
	    	var certificacionSelect = dijit.byId('certificacionSelect');        	
	    	var dominioIdioma = document.registroRequisitosFormBean.dominioIdioma;
	    	
	    	dominioIdioma.value = -1;
	    	
	    	if (vIdioma == 1){  
	    		idiomaIninguno();
	    		certificacionSelect.reset();		    	
	    	} else {
	    		dominioIdioma.disabled=false;        		
				setEnableWg('certificacionSelect', true);
	    		certificacionSelect.attr('required', true);
	    		document.getElementById('lblCertificacionIdioma').innerHTML = 'Certificaci�n*';
	    		document.getElementById('lblDominioIdioma').innerHTML = 'Dominio*';
	    		document.getElementById('linkAgregarIdioma').style.display='block';
	    		certificacionStore.url = "${context}ofertaEdicionExperiencia.do?method=obtenerCertificacion" + "&" + "idioma="+ vIdioma;
	        	certificacionStore.close();    	     
	        	document.registroRequisitosFormBean.idioma1.value=dijit.byId('idiomaSelect').get('value');
	    	}	
		}  
    });

	 dojo.connect(dijit.byId("certificacionSelect"), "onChange", function() {	
         document.registroRequisitosFormBean.certificacionIdioma.value=dijit.byId('certificacionSelect').get('value');
     });
	 
	 
	 
	 idiomaStore.fetch({
	      	onComplete: function(items, request) {       
	      		var entidadV = ${idioma1.idIdioma};
	      		if (items.length == 0) return;                    	
	      		dijit.byId('idiomaSelect').attr('value', entidadV);
	      		 document.registroRequisitosFormBean.idioma1.value=dijit.byId('idiomaSelect').get('value');
	      		
	      		
	      	}
		});
	
	 certificacionStore.fetch({
	      	onComplete: function(items, request) {
	      		var minicipioV = ${idioma1.idCertificacion};
	      		if (items.length == 0) return;                    	
	      		dijit.byId('certificacionSelect').attr('value', minicipioV);
	      		document.registroRequisitosFormBean.certificacionIdioma.value=dijit.byId('certificacionSelect').get('value');
	      	}
		});
	 
	 
	 //***CARRERAS
	 
	  if(dijit.byId('carreraSelect').get('value')>0)
	         document.registroRequisitosFormBean.situacionAcademica.disabled=false;
	 
	 var estudiosVar = ${ofertaVO.idNivelEstudio};
		
		dijit.byId('estudiosSelect').disabled=false;
		estudiosStore.url = "${context}ofertaEdicionRequisitos.do?method=obtenerEstudios";
		estudiosStore.close();      
		
		dijit.byId('carreraSelect').disabled=false;
		carreraStore.url = "${context}ofertaEdicionRequisitos.do?method=obtenerCarreras" + "&" + "estudios="+ estudiosVar;
		carreraStore.close();
		

		dojo.connect(dijit.byId("estudiosSelect"), "onChange", function() {

				var vEstudios = dijit.byId('estudiosSelect').get('value');
					
				
				if(document.registroRequisitosFormBean.flagEstudios.value=="1"){
					dijit.byId('carreraSelect').reset();
					document.registroRequisitosFormBean.carrera.value="-1";
					}
					else
					{
					document.registroRequisitosFormBean.flagEstudios.value="1";
					}

				
	        	dijit.byId('carreraSelect').disabled=false;
	        	
	        	carreraStore.url = "${context}ofertaEdicionRequisitos.do?method=obtenerCarreras" + "&" + "estudios="+ vEstudios;
	        	carreraStore.close();
		     
	        document.registroRequisitosFormBean.estudios.value=dijit.byId('estudiosSelect').get('value');
	       
	    });

		 dojo.connect(dijit.byId("carreraSelect"), "onChange", function() {
	         document.registroRequisitosFormBean.carrera.value=dijit.byId('carreraSelect').get('value');
	         if(dijit.byId('carreraSelect').get('value')>0)
	         document.registroRequisitosFormBean.situacionAcademica.disabled=false;
	     });
		 
		 
		 estudiosStore.fetch({
		      	onComplete: function(items, request) {       
		      		var entidadV = ${ofertaVO.idNivelEstudio};
		      		if (items.length == 0) return;                    	
		      		dijit.byId('estudiosSelect').attr('value', entidadV);
		      		 document.registroRequisitosFormBean.estudios.value=dijit.byId('estudiosSelect').get('value');
		      		
		      		
		      	}
			});
		
		 estudiosStore.fetch({
		      	onComplete: function(items, request) {       
		      		var entidadV = ${carrera1.id};
		      		if (items.length == 0) return;                    	
		      		dijit.byId('carreraSelect').attr('value', entidadV);
		      		 document.registroRequisitosFormBean.carrera.value=dijit.byId('carreraSelect').get('value');
		      		
		      		
		      	}
			});
		
	
	});     

</script>
