<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mx.gob.stps.portal.core.domicilio.vo.MunicipioVO"%>
<%@page import="mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO"%>
<%@page import="mx.gob.stps.portal.core.domicilio.vo.DomicilioVO"%>
<%@page import="mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO"%>
<%@ page import="java.util.List"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link   href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
	.maroonFont{
		color: Maroon;
	}
</style>
<%
  String context = request.getContextPath() +"/";
  DomicilioVO domicilio=(DomicilioVO) request.getSession().getAttribute("domicilio");
  CodigoPostalVO cp=(CodigoPostalVO) request.getSession().getAttribute("cp");
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  DecimalFormat df = new DecimalFormat("#0.00");
 String vProxy = context + "/SpellCheck.do?method=gogiespell&lang=";
%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<jsp:useBean id="ofertaVO" scope="session" class="mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO" /> 
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
    dojo.require("dojo.parser");
    
    function recargaComboEntidad(){
    	
		var vEntidad = dijit.byId('idEntidadSelect').get('value');		
         	dijit.byId('idMunicipioSelect').disabled=false;         		
         	municipioStore.url = "${context}ofertaEdicion.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;           
         	municipioStore.close();
           
	}
    
    
    function ligaSector()
    {
    	
    	window.open("${pageContext.request.contextPath}/ofertaEdicionSectores.do?method=sectoresInit&sid="+document.registroUbicacionFormBean.sector.value, 'popupwindow', 'width=600,height=650,scrollbars,resizable'); 
    }
    
    function ligaUbicaciones()
    {
    	
    	window.open("${pageContext.request.contextPath}/ofertaEdicionEntidades.do?method=entidadesInit&ident="+document.registroUbicacionFormBean.prefiereCandidatosEntidad.value+"&idmun="+document.registroUbicacionFormBean.prefiereCandidatosMunicipio.value, 'popupwindow', 'width=500,height=500,scrollbars,resizable'); 
    	   
    }
    
    function caracteresValidos(e){
    	var tecla_codigo = (document.all) ? e.keyCode : e.which;
    	if(tecla_codigo==8 || tecla_codigo==0) return true;
    	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) return true; //vocales minusculas con acento
    	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) return true; //vocales mayuzculas con acento
    	if (tecla_codigo==209 || tecla_codigo==241 ) return true; //Ñ y ñ
   		
    	var patron =/[A-Za-z\s\-.áéíóúÁÉÍÓÚñÑ0-9.,;:]/;
    	tecla_valor = String.fromCharCode(tecla_codigo);
    	return patron.test(tecla_valor);

    }
    
    function validaDatos(){       
        
    	if(!dijit.byId('tituloOferta').isValid()){
    		dijit.byId('tituloOferta').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Título de la oferta. ");								
    	return false;
    	}			
    	
  	
    	if(document.registroUbicacionFormBean.area.value == "-1" ){
    		dijit.byId('areaSelect').focus();
			mensaje("El siguiente campo muestra datos invalidos: Área laboral.");
		return false;
    	}
    	
    	if(document.registroUbicacionFormBean.ocupacion.value == "-1" ){
    		dijit.byId('ocupacionSelect').focus();
			mensaje("El siguiente campo muestra datos invalidos: Ocupación.");
		return false;
    	}
    	
    	if(dijit.byId('sectorSelect').get("value") == "-1" ){
    		dijit.byId('sectorSelect').focus();
			mensaje("El siguiente campo muestra datos invalidos: Sector de la oferta.");
		return false;
    	}
    	
    
    	
    	if(document.registroUbicacionFormBean.horario.value == "-1" ){
    		document.registroUbicacionFormBean.horario.focus();
			mensaje("El siguiente campo muestra datos invalidos: Tipo de empleo.");
		return false;
    	}
    	
    	if(document.registroUbicacionFormBean.HEntrada.value == "-1" ){
    		document.registroUbicacionFormBean.HEntrada.focus();
			mensaje("El siguiente campo muestra datos invalidos: Hora laboral entrada.");
		return false;
    	}
    	if(document.registroUbicacionFormBean.HSalida.value == "-1" ){
    		document.registroUbicacionFormBean.HSalida.focus();
			mensaje("El siguiente campo muestra datos invalidos: Hora laboral salida.");
		return false;
    	}
    	
    	
    	if(document.registroUbicacionFormBean.domingo.checked==false &&
    		document.registroUbicacionFormBean.lunes.checked==false &&
    		document.registroUbicacionFormBean.martes.checked==false &&
    		document.registroUbicacionFormBean.miercoles.checked==false &&
    		document.registroUbicacionFormBean.jueves.checked==false &&
    		document.registroUbicacionFormBean.viernes.checked==false &&
    		document.registroUbicacionFormBean.sabado.checked==false
    				){
    		document.registroUbicacionFormBean.domingo.focus();
			mensaje("El siguiente campo muestra datos invalidos: Días laborales.");
		return false;
    	}
    	
    	if(document.registroUbicacionFormBean.rolarTurnos[0].checked==false && document.registroUbicacionFormBean.rolarTurnos[1].checked==false) {
    		document.registroUbicacionFormBean.rolarTurnos[0].focus();
    		mensaje("El siguiente campo muestra datos invalidos: Rolar turnos.");
    		return false;
    	}
    	

    	if(document.registroUbicacionFormBean.funciones.value==""){
    		document.registroUbicacionFormBean.funciones.focus();
    		mensaje("El siguiente campo muestra datos invalidos: Funciones y actividades a realizar. ");								
        	return false;
    	}
    	
    	if(document.registroUbicacionFormBean.empresaOfrece.value==""){
    		document.registroUbicacionFormBean.empresaOfrece.focus();
    		mensaje("El siguiente campo muestra datos invalidos: La empresa ofrece. ");										
        	return false;
    	}
    	
    	if(!dijit.byId('salario').isValid()){
    		dijit.byId('salario').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Salario neto mensual ofrecido. ");								
    	return false;
    	}			
    	
    	if(dijit.byId('salario').get('value')=="0"){
    		dijit.byId('salario').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Salario neto mensual ofrecido. ");								
    	return false;
    	}	
    	
    	if(document.registroUbicacionFormBean.tipoContrato.value == "-1" ){
    		document.registroUbicacionFormBean.tipoContrato.focus();
			mensaje("El siguiente campo muestra datos invalidos: Tipo de contrato.");
		return false;
    	}
    	
    	if(document.registroUbicacionFormBean.prestaciones.value == "" ){
    		document.registroUbicacionFormBean.tipoContrato.focus();
			mensaje("El siguiente campo muestra datos invalidos: prestaciones");
		return false;
    	}
    	
    	if(document.registroUbicacionFormBean.jerarquia.value == "-1" ){
    		document.registroUbicacionFormBean.jerarquia.focus();
			mensaje("El siguiente campo muestra datos invalidos: Jerarquía.");
		return false;
    	}
    	
    	if(!dijit.byId('numeroPlazas').isValid()){
    		dijit.byId('numeroPlazas').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Número de plazas. ");								
    	return false;
    	}	
    	
    	/*if(!dijit.byId('limitePostulantes').isValid()){
    		dijit.byId('limitePostulantes').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Límite de postulantes. ");								
    		return false;
    	}*/
    	
    	if(!dijit.byId('vigenciainicio').isValid()){
    		dijit.byId('vigenciainicio').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Vigencia de la oferta, Inicio. ");								
    	return false;
    	}	
    	
    	if(!dijit.byId('vigenciaFin').isValid()){
    		dijit.byId('vigenciaFin').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Vigencia de la oferta, Fin. ");								
    	return false;
    	}	
    	
    	
    	if(document.registroUbicacionFormBean.dicapacidad[0].checked==false && 
    			document.registroUbicacionFormBean.dicapacidad[1].checked==false && 
    			document.registroUbicacionFormBean.dicapacidad[2].checked==false && 
    			document.registroUbicacionFormBean.dicapacidad[3].checked==false && 
    			document.registroUbicacionFormBean.dicapacidad[4].checked==false && 
    			document.registroUbicacionFormBean.dicapacidad[5].checked==false 
    	        			
    	
    	) {
    		document.registroUbicacionFormBean.dicapacidad[0].focus();
    		mensaje("El siguiente campo muestra datos invalidos: discapacidad.");
    		return false;
    	}
    	
    	
    
    	
    	if(document.registroUbicacionFormBean.origenOferta.value == "-1" ){
    		document.registroUbicacionFormBean.origenOferta.focus();
			mensaje("El siguiente campo muestra datos invalidos: Causa que origina la oferta.");
		return false;
    	}
  
    	if(document.registroUbicacionFormBean.checkCtrl.checked==false)
    		{
    		if(document.registroUbicacionFormBean.codigoPostal.value=="")
    			{
    			document.registroUbicacionFormBean.codigoPostal.focus();
    			mensaje("El siguiente campo muestra datos invalidos: Codigo Postal.");
    			return false;
    			
    			}
    		
    		}
    	
    	
    	
    	
    	
    	if(!dijit.byId('codigoPostal').isValid())
		{
    		dijit.byId('codigoPostal').focus();
			mensaje("El siguiente campo muestra datos invalidos: Domicilio, Código postal.");
			return false;
			
		}	
    	
    	if(!dijit.byId('idEntidadSelect').isValid())
		{
    		dijit.byId('idEntidadSelect').focus();
			mensaje("El siguiente campo muestra datos invalidos: Domicilio, Entidad federativa.");
			return false;
			
		}	
    	
    	if(!dijit.byId('idMunicipioSelect').isValid())
		{
    		dijit.byId('idMunicipioSelect').focus();
			mensaje("El siguiente campo muestra datos invalidos: Domicilio, Municipio.");
			return false;
			
		}	
    	
    	if(!dijit.byId('idColoniaSelect').isValid())
		{
    		dijit.byId('idColoniaSelect').focus();
			mensaje("El siguiente campo muestra datos invalidos: Domicilio, Colonia.");
			return false;
			
		}	
    	
    	
    	if(!dijit.byId('calle').isValid()){
    		dijit.byId('calle').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Domicilio, Calle. ");								
    	return false;
    	}	
    	

    	if(!dijit.byId('numeroExterior').isValid()){
    		dijit.byId('numeroExterior').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Domicilio, Número Exterior. ");								
    	return false;
    	}	
    	
    	
    	if(!dijit.byId('yCalle').isValid()){
    		dijit.byId('yCalle').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Domicilio, y Calle. ");								
    	return false;
    	}	
    	
    	
    	
    	if(!dijit.byId('entreCalle').isValid()){
    		dijit.byId('entreCalle').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Domicilio, Entre calle. ");								
    	return false;
    	}	
    	
    	if(!dijit.byId('idEntidadSelect2').isValid()){
    		dijit.byId('idEntidadSelect2').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Prefiere candidatos, Entidad federativa. ");								
    	return false;
    	}
    	
    	if(!dijit.byId('idMunicipioSelect2').isValid()){
    		dijit.byId('idMunicipioSelect2').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Prefiere candidatos, Municipio. ");								
    	return false;
    	}
    	
    	if(document.registroUbicacionFormBean.prefiereCandidatosEntidad.value == "-1" ){
    		document.registroUbicacionFormBean.prefiereCandidatosEntidad.focus();
			mensaje("El siguiente campo muestra datos invalidos: Prefiere candidatos, Entidad Federativa.");
		return false;
    	}
    	
    	if(document.registroUbicacionFormBean.prefiereCandidatosMunicipio.value == "-1" ){
    		document.registroUbicacionFormBean.prefiereCandidatosMunicipio.focus();
			mensaje("El siguiente campo muestra datos invalidos: Prefiere candidatos, Municipio.");
		return false;
    	}
    	
    	if(!dijit.byId('yCalle').isValid()){
    		dijit.byId('yCalle').focus();
    		mensaje("El siguiente campo muestra datos invalidos: Domicilio, y Calle. ");								
    	return false;
    	}	
    	
    
    	
    	return true;		
    }
    
    

	
    
	
	
	
    function doSubmit(method){
    	document.registroUbicacionFormBean.method.value=method;
    	document.registroUbicacionFormBean.Guardar.disabled=true;
    	if(validaDatos()){
    		document.registroUbicacionFormBean.submit();
    		return true;
    	   }
    	else{
    		document.registroUbicacionFormBean.Guardar.disabled=false;
    		return false;
    	}
    }

    function cambiarMayusculas(element) {
    	var myElementValue = document.getElementById(element.id).value;
    	document.getElementById(element.id).value = myElementValue.toUpperCase();
    }

    function cancelaRegistro(usuario) {

    	if(confirm("Los datos no guardados se perderán ¿Continuar?")) {
    		
    		if(usuario=='200')
        		document.registroUbicacionFormBean.action='<c:url value="/OfertaNavegacion.do?method=init"/>';
        	if(usuario=='100')
            	document.registroUbicacionFormBean.action="autorization.do?method=init";
            		
    		document.registroUbicacionFormBean.submit();
    	} 
    } 
    
    function mensaje(mensaje){
		dojo.byId('mensaje').innerHTML = mensaje;
		dijit.byId('MensajeAlert').show();		
	}

    function estableceRangoVigencia(arguments){
    	dijit.byId('vigenciaFin').constraints.min = arguments[0];
    	
    	var findate = new Date(arguments[0]);
    	findate.setDate(arguments[0].getDate()+30);

    	dijit.byId('vigenciaFin').constraints.max = findate;
    }

</script>


<% 
List<CatalogoOpcionVO> listaAreaLaboral= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_AREA_LABORAL");		
//List<CatalogoOpcionVO> listaOcupaciones= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_OCUPACION");		
List<CatalogoOpcionVO> listaSectores= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_SECTOR");		
List<CatalogoOpcionVO> listaHorariosLaborales= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_HORARIO_LABORAL");		
List<CatalogoOpcionVO> listaHorasContacto= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_HORARIO_CONTACTO");		
List<CatalogoOpcionVO> listaTipoContrato= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_TIPO_CONTRATO");		
List<CatalogoOpcionVO> listaPrestaciones= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_PRESTACIONES");		
List<CatalogoOpcionVO> listaJerarquia= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_JERARQUIA_PUESTO");		
List<CatalogoOpcionVO> listaCausaOferta= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_CAUSA_OFERTA");		
List<CatalogoOpcionVO> listaEntidades= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_ENTIDAD_FEDERATIVA");		
List<CatalogoOpcionVO> listaMunicipios= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_MUNICIPIO");		
List<CatalogoOpcionVO> listaDiscapacidades= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_TIPO_DISCAPACIDAD");	
List<CatalogoOpcionVO> listaTipoEmpleo= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_TIPO_EMPLEO");	
ArrayList<String>listaSectoresSeleccionados=(ArrayList<String>)request.getSession().getAttribute("listaSectoresSeleccionados");


long sector1 = 0;
if (listaSectoresSeleccionados!=null && !listaSectoresSeleccionados.isEmpty()){
	sector1 = Utils.parseLong(listaSectoresSeleccionados.get(0));
	listaSectoresSeleccionados.remove(0);
}

ArrayList<String>listaPrestacionesSeleccionadas=(ArrayList<String>)request.getSession().getAttribute("listaPrestacionesSeleccionadas");
ArrayList<RegistroEntidadesVO> lista=(ArrayList<RegistroEntidadesVO>) (request.getSession().getAttribute("listaEntidadesSeleccionadas")!=null?request.getSession().getAttribute("listaEntidadesSeleccionadas"):new ArrayList<RegistroEntidadesVO>());

RegistroEntidadesVO entidad1 = null;

if (lista!=null && !lista.isEmpty()){
	entidad1 = lista.get(0);
	lista.remove(0);
}else{
	entidad1 = new RegistroEntidadesVO();
	entidad1.setEntidad(-1);
	entidad1.setMunicipio(-1);
}

request.getSession().setAttribute("entidad1",entidad1);
//OfertaEmpleoVO oferta =(OfertaEmpleoVO)request.getSession().getAttribute("ofertaVO");



%>
 
<link href='http://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_genericos.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />

<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>


<form name="registroUbicacionFormBean" id="registroUbicacionFormBean" action="ofertaPlantilla.do" method="post">

	<input type="hidden" name="method" id="method" value="init" />
	<input type="hidden" name="Ctrl" id="Ctrl" dojoType="dijit.form.TextBox" value="1"/>	
	<input type="hidden" name="idEmpresa" value="0"/>
    <input type="hidden" name="flagEntidad2" value="0"/>
    <input type="hidden" name="flagArea" value="0"/>
    <input type="hidden" name="cpnuevo" id="cpnuevo" dojoType="dijit.form.TextBox" value="1"/>
    <input type="hidden" name="idOferta" value='<%=request.getAttribute("idOferta")%>' />
   
   
    

   	
    <div class="div_tab">
			
			<input type="button" class="boton_divisor" value=" " />
			<input type="button" class="boton_tab${thisTabId == 1? '_sel':'' }" value="${thisTabId == 1? thisTabName:'1. Información y ubicación de la oferta de empleo' }"  />
			<input type="button" class="boton_separador" value="  " />
			<input type="button" class="boton_tab${thisTabId == 2? '_sel':'' }" value="${thisTabId == 2? thisTabName:'2' }"  />
			<input type="button" class="boton_separador" value="  " />
			<input type="button" class="boton_tab${thisTabId == 3? '_sel':'' }" value="${thisTabId == 3? thisTabName:'3' }" />
			<input type="button" class="boton_separador" value="  " />
			<input type="button" class="boton_divisor_fin" value="" />
			
	</div>
    
    <div>
         <p class="entero">
        <span ><strong>Título de la oferta*</strong><br>
         <input id="tituloOferta" name="tituloOferta" maxlength="150" size="200" value='<jsp:getProperty property="tituloOferta" name="ofertaVO"/>'
        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\w\D]{1,150}$" 
        	invalidMessage="Dato Invalido"  missingMessage="Dato requerido" style="width: 626px"/>        
        </span> 
         
      
         
    </div>
    <div class="entero">
    <span>Área laboral* 
	             <input type="hidden" name="area" id="area" value="-1" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="areaStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="areaStore" id="areaSelect" required="true"  style="width: 626px" >
    			</select> 
	                     </span>  </div>
    
    
    <div class="entero">
    <span>Ocupación*  
    <input type="hidden" name="ocupacion" id="ocupacion" value="-1" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="ocupacionStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="ocupacionStore" id="ocupacionSelect" required="true"  style="width: 626px">
    </select></span> 
    </div>
    
    <div class="entero">  
    <span>Sector de la oferta* <br>
    	<input type="hidden" name="sector" id="sector" value="<%=sector1%>" />
		<select dojotype="dijit.form.FilteringSelect"  id="sectorSelect" name="sectorSelect" required="true"  style="width: 626px">
		<option value="-1" >Seleccione Sector</option>
    	<%for (CatalogoOpcionVO catalogoOpcionVO : listaSectores){%>
		<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=(catalogoOpcionVO.getIdCatalogoOpcion()==sector1)?"selected":"" %> ><%=catalogoOpcionVO.getOpcion() %></option>
		<%}%>   
    </select>
    <br>
    <a href="javascript:ligaSector()">Seleccionar sector</a>
    </span>
    </div>
     
     
    
    <div class="entero">
    
    <p><span>Funciones y actividades a realizar*
  
   <textarea name="funciones" id="funciones" maxlength="2000" 
	        	onkeypress="return caracteresValidos(event);"
	        	regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$" 
	        	style="width:550px;min-height:120px;_height:200px;" 
	        	require="false">${ofertaVO.funciones}</textarea><!-- OnChange="Upper(this.form.funcion)" -->
	        	<script type="text/javascript">
	         		var vSpellFuncion = new GoogieSpell("googiespell/", '<%=vProxy%>');
	         		vSpellFuncion.setLanguages({'es': 'Español'});
   					vSpellFuncion.hideLangWindow();
  					vSpellFuncion.decorateTextarea("funciones");
				</script>
  
    </span></p>
    </div>
    
    <div class="entero">
    <span class="un_tercio">Tipo de empleo*<select name="horario" ><option value="-1">Seleccione</option>
   	<%for (CatalogoOpcionVO catalogoOpcionVO : listaTipoEmpleo){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>" <%=ofertaVO.getIdTipoEmpleo()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
    <%}%>   
    </select></span>
    
    <span class="un_tercio">Hora laboral entrada* 
    <select name="HEntrada" ><option value="-1">Seleccione </option>
    <%for (CatalogoOpcionVO catalogoOpcionVO : listaHorariosLaborales){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=Long.parseLong(ofertaVO.getHoraEntrada())==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>  ><%=catalogoOpcionVO.getOpcion() %></option>
    <%}%>  
    </select></span>
    <span class="un_tercio">Hora laboral salida*
    <select name="HSalida" ><option value="1">Seleccione </option>
    <%for (CatalogoOpcionVO catalogoOpcionVO : listaHorariosLaborales){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>" <%=(Long.parseLong(ofertaVO.getHoraSalida())==catalogoOpcionVO.getIdCatalogoOpcion())?"selected":"" %>><%=catalogoOpcionVO.getOpcion()%> </option>
    <%}%>  
    </select></span>
    </div>
    
    <div class="dos_tercios">
	<span>Días laborales*</span>
	<span>Dom<input type="checkbox" name="domingo" value="1"/ <%=ofertaVO.getDiasLaborales().charAt(0)=='1'?"checked":"" %>></span>
    <span>Lun<input type="checkbox" name="lunes" value="2"/ <%=ofertaVO.getDiasLaborales().charAt(1)=='1'?"checked":"" %>></span>
	<span>Mar<input type="checkbox" name="martes" value="3"/ <%=ofertaVO.getDiasLaborales().charAt(2)=='1'?"checked":"" %>></span>
	<span>Mie<input type="checkbox" name="miercoles" value="4"/ <%=ofertaVO.getDiasLaborales().charAt(3)=='1'?"checked":"" %>></span>
	<span>Jue<input type="checkbox" name="jueves" value="5"/ <%=ofertaVO.getDiasLaborales().charAt(4)=='1'?"checked":"" %>></span>
	<span>Vie<input type="checkbox" name="viernes" value="6"/ <%=ofertaVO.getDiasLaborales().charAt(5)=='1'?"checked":"" %>> </span>
	<span>Sab<input type="checkbox" name="sabado" value="7"/ <%=ofertaVO.getDiasLaborales().charAt(6)=='1'?"checked":"" %>></span> 
	</div>
	
	<div class="un_tercio">
	<span>Rolar Turnos*</span>  <span> Si<input type="radio"  name="rolarTurnos" value="2"/ <%=ofertaVO.getRolarTurno()==2?"checked":"" %>></span> <span>No<input type="radio"  name="rolarTurnos" value="1" <%=ofertaVO.getRolarTurno()==1?"checked":"" %>/></span>	
	</div>
	
	<div class="entero">
    <span>
    La empresa ofrece*
    
    
    <textarea name="empresaOfrece" id="empresaOfrece" maxlength="2000" 
	        	onkeypress="return caracteresValidos(event);"
	        	regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$" 
	        	style="width:550px;min-height:120px;_height:200px;" 
	        	require="false">${ofertaVO.empresaOfrece}</textarea><!-- OnChange="Upper(this.form.funcion)" -->
	        	<script type="text/javascript">
	         		var vSpellFuncion2 = new GoogieSpell("googiespell/", '<%=vProxy%>');
	         		vSpellFuncion2.setLanguages({'es': 'Español'});
   					vSpellFuncion2.hideLangWindow();
  					vSpellFuncion2.decorateTextarea("empresaOfrece");
				</script>
    
    
   
	</span>
	</div>

	<div class="entero"> 
	<span class="un_medio">Salario neto mensual ofrecido*
	<input id="salario" name="salario" maxlength="10" size="13"
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,6}(\.[0-9]{2,2})*$" 
			        	invalidMessage="Dato Invalido"  missingMessage="Dato requerido" trim="true" value= <%=df.format(ofertaVO.getSalario()) %> />  

	</span>
	

	<span class="un_medio">Tipo de contrato*<select name="tipoContrato" ><option value="-1"   >Seleccione una opción</option>
	 <%for (CatalogoOpcionVO catalogoOpcionVO : listaTipoContrato){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=ofertaVO.getIdTipoContrato()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>  ><%=catalogoOpcionVO.getOpcion() %></option>
    <%}%> 
    </select> </span></div>

	<div class="entero"> <span class="un_medio">Prestaciones*<br>
	<span class="maroonFont">(Presionar Ctrl para seleccionar más de una opción)</span>
	<select name="prestaciones" multiple size="8" >
	
	<%
	String sel=null;
	for (CatalogoOpcionVO catalogoOpcionVO : listaPrestaciones){
		sel=null;
	for(String prestacion : listaPrestacionesSeleccionadas){
		if(prestacion.compareTo(catalogoOpcionVO.getIdCatalogoOpcion()+"")==0)
			sel="selected";
	}
	
	%>
	 
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=sel %> ><%=catalogoOpcionVO.getOpcion() %></option>
    <%
    }
    %> 
    </select> <br>
   
    </span>
    <p>
    <span class="un_medio">Jerarquía* <select name="jerarquia" style="width: 150px">
	<option value="-1">Seleccione</option>
	<%for (CatalogoOpcionVO catalogoOpcionVO : listaJerarquia){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>" <%=ofertaVO.getIdJerarquia()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %> ><%=catalogoOpcionVO.getOpcion() %></option>
    <%}%> 
	</select></span></p>
	<br>
	<p>
	 <span >Número de plazas*  
    <input id="numeroPlazas" name="numeroPlazas" maxlength="5" size="5"  value='<jsp:getProperty property="numeroPlazas" name="ofertaVO"/>'       	
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,3}$"
			        	invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" style=" width: 70px;"/>

    </span>
    </p>
    <%--
    <p>
    <span>Límite de postulantes*
    <input id="limitePostulantes" name="limitePostulantes" maxlength="5" size="5"  value='<jsp:getProperty property="limitePostulantes" name="ofertaVO"/>'      	
			        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,3}$"
			        	invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" style=" width: 50px;"/>
    
    </span>
    </p>
    --%>
    </div>
   
   <div class="entero">
   		<span>Vigencia de la oferta - Inicio*
			   <input name="vigenciainicio" id="vigenciainicio"		             		    
       		   dojoType="dijit.form.DateTextBox" 
       		   maxlength="10"  
       		   constraints="{min:'<%=sdf.format(new Date()) %>',datePattern:'dd/MM/yyyy' }";
       		   required="true" missingMessage="Debe indicar la fecha de inicio de vigencia" 
       		   value="<%=sdf.format(ofertaVO.getFechaInicio())%>"
       		   onChange="javascript:estableceRangoVigencia(arguments);"/>
		</span>
	</div>

    <div class="entero">
    	<span>Vigencia de la oferta - fin*
    	<input type="text" name="vigenciaFin" id="vigenciaFin"		             		    
		       dojoType="dijit.form.DateTextBox" 
		       maxlength="10" required="true" missingMessage="Debe indicar la fecha de fin de vigencia"
		       constraints="{min:'<%=sdf.format(new Date()) %>',datePattern:'dd/MM/yyyy'}";
		       value="<%=sdf.format(ofertaVO.getFechaFin())%>"/>
		</span>
	</div>
  
    <div class="entero">
    <p>
	<span>¿Qué tipo de discapacidad acepta?*</span>	<br>
	 <%for (CatalogoOpcionVO catalogoOpcionVO : listaDiscapacidades){%>
		<%=catalogoOpcionVO.getOpcion() %><input type="radio"   name="dicapacidad" value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=ofertaVO.getIdDiscapacidad()==catalogoOpcionVO.getIdCatalogoOpcion()?"checked":"" %>/>
	 <%}%>   
	</p>
	</div>
    <div class="entero"><span>Causa que origina la oferta*
    <select name="origenOferta">
	<option value="-1">Seleccione</option>
	 <%for (CatalogoOpcionVO catalogoOpcionVO : listaCausaOferta){%>
	<option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>" <%=ofertaVO.getIdCausaVacante()==catalogoOpcionVO.getIdCatalogoOpcion()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
    <%}%>
	</select></span></div>

    <div class="entero" >
			<h3>Ubicación de la oferta</h3>	
		</div>
    <div class="entero">
        <span class="un_tercio"><strong>Código postal</strong>
        <input id="codigoPostal" name="codigoPostal" maxlength="5" size="5" value="${domicilio.codigoPostal}"
        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,5}"   
        	invalidMessage="Dato Invalido" trim="true" /> <br><br>
        	
        	 </span>  
        	 <span class="un_tercio" >
        	 <input type="checkbox" id="checkCtrl" name="checkCtrl"  />
        	<!--  <input type="checkbox" id="checkCtrl" name="checkCtrl" onclick="if(this.checked){cambiaBtnCP()}"/ />-->
        	
		<label for="checkCtrl">No conozco mi código postal</label>  
        	    
        </span>
        <span class="un_tercio" >
        <input type="button" name="btnValidar" id="btnValidar" class="boton" value="Validar CP">
		<!--<div id="btnValidar" name ="btnValidar" dojoType="dijit.form.Button">Validar CP</div>-->
		</span>
		
       
</div>

<div class="entero">
		
		 <span class="entero"><strong>País</strong>
        <input name="pais" id="pais" maxlength="10" size="6" value="México" disabled />
        <label for="pais"></label>	
        </span>
        
       </div>
     

<div class="entero">
   				<span class="un_tercio">
	   				<strong>Entidad federativa*</strong><br />
						<input type="hidden" name="idEntidad" id="idEntidad" value="" />
						<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true" ></div>
						<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="idEntidadSelect" required="true" disabled="disabled" autoComplete="false"></select>
   				</span>
   				<span class="un_tercio">
	   				<strong>Municipio*</strong><br />
						<input type="hidden" name="idMunicipio" id="idMunicipio" value="" />
						<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" ></div>
						<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="idMunicipioSelect" required="true" disabled="disabled" autoComplete="false"></select>				        
   				</span>
   				<span class="un_tercio">
	   				<strong>Colonia*</strong><br />
						<input type="hidden" name="idColonia" id="idColonia" value="" />
						<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true" ></div>
						<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="idColoniaSelect" required="true" disabled="disabled" autoComplete="true"></select>
   				</span>   				
   			</div>

      <div class="entero">
      
        <span class="un_tercio">
       <strong>Calle*</strong><br>
        <input id="calle" name="calle" maxlength="150" size="60" value="${domicilio.calle}" onBlur="cambiarMayusculas(this)"
        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\w\D\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$"
        	invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
        </span>
       <span class="un_tercio">
       <strong>Número exterior*</strong><br>
        <input id="numeroExterior" name="numeroExterior" maxlength="50" size="4" value="${domicilio.numeroExterior}"
        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\w\s]{1,50}" 
        	invalidMessage="Dato Invalido" trim="true" /> 
       </span>
        <span class="un_tercio"><strong>Número interior</strong><br>
         <input id="numeroInterior" name="numeroInterior" maxlength="50" size="4" value="${domicilio.numeroInterior}"
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[\w\s]{1,50}" 
        	invalidMessage="Dato Invalido" trim="true" />        
        </span>
   </div>
       
      <div class="entero">
      
        <span class="un_tercio"><strong>Entre calles*</strong><br>
        <input id="entreCalle" name="entreCalle" maxlength="150" size="60" value="${domicilio.entreCalle}" onBlur="cambiarMayusculas(this)"
        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\w\D\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$"
        	invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
        </span>
       
        <span class="un_tercio">
        <strong>Y*</strong><br>
        <input id="yCalle" name="yCalle" maxlength="150" size="150" value="${domicilio.yCalle}" onBlur="cambiarMayusculas(this)"
        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\w\D\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$"
        	invalidMessage="Dato Invalido" trim="true"/>
        
        </span>
        
	</div>  
    
    <div class="entero">
    <br>
    <p><br><span><strong>Prefiero candidatos de las siguientes ubicaciones</strong></span></p>
  
  </div>
  <div class="entero">
   <span class="un_tercio">
		<strong>Entidad federativa*</strong>
		<input type="hidden" name="prefiereCandidatosEntidad" id="prefiereCandidatosEntidad" value="" />
		<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore2" urlPreventCache="true" clearOnClose="true" ></div>
		<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore2" id="idEntidadSelect2" required="true" disabled="disabled" autoComplete="false"></select>
      </span>
      <span class="un_tercio">
        <strong>Municipio*</strong>
		<input type="hidden" name="prefiereCandidatosMunicipio" id="prefiereCandidatosMunicipio" value="" />
		<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore2" urlPreventCache="true" clearOnClose="true" ></div>
		<select dojotype="dijit.form.FilteringSelect" store="municipioStore2" id="idMunicipioSelect2" required="true" disabled="disabled" autoComplete="false"></select>				        
      </span>
      
      <span class="un_tercio">
   		<a href="javascript:ligaUbicaciones()">Ubicaciones adicionales</a>
   		</span>
    		</div>
    
    <div class="entero">
     <span class="entero"><a href="http://maps.google.com.mx/" onclick="window.open(this.href, 'popupwindow', 'width=1000,height=450,scrollbars,resizable'); return false;">Mostrar mapa de ubicación de la oferta</a>
     <input type="text"  name="mapaUbicacion" value='<jsp:getProperty property="mapaUbicacion" name="ofertaVO"/>' style=" width: 410px;"/>
     
    
     </span>
	</div>
    
    <div class entero><span class="un_tercio"></span>
    <span class="un_tercio">
    <input type="button" value="Continuar" class="boton" name="Guardar" onclick="doSubmit('editarUbicacion');" />
	<input type="button" value="Cancelar" class="boton" onClick="cancelaRegistro(${usuario});"/>
	<span class="un_tercio"></span>
    </span></div>
    
    <div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none">    
               <table width="500px" height="50px" >
                  <tr align="center">
                  <td><div id ="mensaje" style="width:500px; height:50px; overflow:auto; vertical-align:middle; "></div>&nbsp;</td>                   
                  </tr>
                </table>                                
    </div>

    
    </form>



<script type="text/javascript">




dojo.addOnLoad(function() {
	//FECHA FIN DE VIGENCIA
	var inidate =dijit.byId('vigenciainicio').get('value');
	var findate = new Date(inidate);
	findate.setDate(inidate.getDate()+30);
	dijit.byId('vigenciaFin').constraints.max = findate;

	
//INICIA DOMICILIO 1	
	var vCodigoPostal = dojo.byId('codigoPostal').value;
	

		dijit.byId('idEntidadSelect').disabled=false;
		dijit.byId('idMunicipioSelect').disabled=false;
		dijit.byId('idColoniaSelect').disabled=false;
		

	
    entidadFederativaStore.url = "${context}ofertaEdicion.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
	entidadFederativaStore.close();          

   	entidadFederativaStore.fetch({
         	onComplete: function(items, request) {
         		if (items.length == 0) return;
         		dijit.byId('idEntidadSelect').attr('value', items[0].value);
         	}
   	});	
	
	municipioStore.url = "${context}ofertaEdicion.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
	municipioStore.close();

   	municipioStore.fetch({
         	onComplete: function(items, request) {
         		if (items.length == 0) return;
         		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
         	}
   	});

   	coloniaStore.url = "${context}ofertaEdicion.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
   	coloniaStore.close();	
	
   	coloniaStore.fetch({
         	onComplete: function(items, request) {
         		if (items.length == 0) return;
         		dijit.byId('idColoniaSelect').attr('value', items[0].value);
         	}
   	});
   		
   		
	
        dojo.connect(document.registroUbicacionFormBean.btnValidar, 'onclick', function() {
 			if(dijit.byId('codigoPostal').get('value') == '') {
 			   alert("El código postal es necesario.");
 			   return;
			}
 			if(dijit.byId('codigoPostal').value.length <5){
  			   alert("El código postal debe tener 5 dígitos.");
  			   return;
 			}			
			

            if(dijit.byId('Ctrl').value == 1) {
				
				var vCodigoPostal = dijit.byId('codigoPostal').get('value');
				var vMsjNotF = 0;
				document.registroUbicacionFormBean.method.value = 'obtenerEntidadJSON';
				
				dijit.byId('idEntidadSelect').disabled=false;
				dijit.byId('idMunicipioSelect').disabled=false;
				dijit.byId('idColoniaSelect').disabled=false;
				
            	entidadFederativaStore.url = "${context}ofertaEdicion.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	entidadFederativaStore.close();
            				
            	entidadFederativaStore.fetch({
                  	onComplete: function(items, request) {
                  		vMsjNotF = items.length;
                  		dijit.byId('idEntidadSelect').attr('value', items[0].value);
                  		if (items.length == 0) {                  			
        					alert("El código no se encontró o es inválido.");                  			
                  			return;	
                  		} else {
                  		    dojo.byId('codigoPostalShow').value = vCodigoPostal;
                  		}        
                  		
                  		dijit.byId('idEntidadSelect').attr('value', items[0].value);
                  	}
            	});	

            	municipioStore.url = "${context}ofertaEdicion.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	municipioStore.close();

            	municipioStore.fetch({
                  	onComplete: function(items, request) {                  	
                  		if (items.length == 0) return;                    	
                  		dijit.byId('idMunicipioSelect').attr('value', items[0].value);
                  	}
            	});

            	coloniaStore.url = "${context}ofertaEdicion.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	coloniaStore.close();

            	coloniaStore.fetch({
                  	onComplete: function(items, request) {                  	
                  		if (items.length == 0) return;                    	
                  		dijit.byId('idColoniaSelect').attr('value', items[0].value);
                  	}
            	});   
            	
				if(vMsjNotF>0){
					dojo.byId('codigoPostalShow').value = dijit.byId("codigoPostal").value;
					dijit.byId("codigoPostal").setAttribute('disabled', true);						
				} else {
					dijit.byId("codigoPostal").setAttribute('disabled', false);
				}
				
			}
			
        });
	
        dojo.connect(dijit.byId("idEntidadSelect"), "onChange", function() {

        document.registroUbicacionFormBean.idEntidad.value= dijit.byId('idEntidadSelect').get('value');	
        	
            if(dijit.byId('Ctrl').value == 0){
            	
            	dijit.byId('idMunicipioSelect').reset();
            	dijit.byId('idColoniaSelect').reset();	
            	
				var vEntidad = dijit.byId('idEntidadSelect').get('value');
					
            	dijit.byId('idMunicipioSelect').disabled=false;
            		
            	municipioStore.url = "${context}ofertaEdicion.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
            	municipioStore.close();
            	            	
			}
        });
	
        dojo.connect(dijit.byId("idMunicipioSelect"), "onChange", function() {
			
        	document.registroUbicacionFormBean.idMunicipio.value= dijit.byId('idMunicipioSelect').get('value');
        	
            if(dijit.byId('Ctrl').value == 0){
            	dijit.byId('idColoniaSelect').reset();

				var vEntidad   = dijit.byId('idEntidadSelect').get('value');
				var vMunicipio = dijit.byId('idMunicipioSelect').get('value');            		
            	coloniaStore.url = "${context}ofertaEdicion.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
            	coloniaStore.close();
            	
			}
        });	
        
        
        dojo.connect(dijit.byId("idColoniaSelect"), "onChange", function() {

        	document.registroUbicacionFormBean.idColonia.value= dijit.byId('idColoniaSelect').get('value');
        	
            if(dijit.byId('Ctrl').value == 0){

            	dijit.byId('codigoPostal').disabled=false;

				dojo.byId('idEntidad').value   = dijit.byId('idEntidadSelect').get('value');
				dojo.byId('idMunicipio').value = dijit.byId('idMunicipioSelect').get('value');
				dojo.byId('idColonia').value   = dijit.byId('idColoniaSelect').get('value');
                
				dojo.byId('method').value="obtenerCodigoPostal";				

	
				dojo.xhrPost(
				{
				  url: 'ofertaEdicion.do',
				  form:'registroUbicacionFormBean',
				  timeout:180000, 
				  load: function(data)
				  {
					var res = dojo.fromJson(data);
					dojo.byId('codigoPostal').value = res.codigoPostal;
					dojo.byId('codigoPostalShow').value = res.codigoPostal;
					dijit.byId("codigoPostal").setAttribute('disabled', true);
				  }
				});
			}			
        });        

        
        var checkBox = new dijit.form.CheckBox({
            name: "checkCtrl",
            value: 1,
            checked: false,
            onChange: function(b) {
                if(b == true){
					dijit.byId('Ctrl').value = 0;
			        var wCP = dijit.byId('codigoPostal');
			        wCP.attr('value','');	
			        wCP.attr('disabled', true);  
            		var wColonia = dijit.byId('idColoniaSelect');
            		wColonia.reset();
            		var wMunicipio = dijit.byId('idMunicipioSelect');
            		wMunicipio.reset();          
					var wEntidad = dijit.byId('idEntidadSelect');
					wEntidad.attr('disabled', false);
					wEntidad.reset();            		  					        				
            		entidadFederativaStore.url = "${context}ofertaEdicion.do?method=obtenerEntidad";
            		entidadFederativaStore.close(); 
					var vValidar = document.getElementById('btnValidar');
					vValidar.disabled = true;            		
            		           		
                }
                if(b == false){
                	dijit.byId('Ctrl').value = 1;

			        var wCP = dijit.byId('codigoPostal');
			        wCP.attr('value','');
			        wCP.attr('disabled', false);       
					var wColonia = dijit.byId('idColoniaSelect');
					wColonia.reset();		
					var wMunicipio = dijit.byId('idMunicipioSelect');
					wMunicipio.reset();			
					var wEntidad = dijit.byId('idEntidadSelect');
					wEntidad.reset();
					wEntidad.attr('disabled', true);	
					dijit.byId("idMunicipioSelect").setAttribute('disabled', true);
					dijit.byId("idColoniaSelect").setAttribute('disabled', true);	
					var vValidar = document.getElementById('btnValidar');
					vValidar.disabled = false;																	                 	

                }
            }
        }, "checkCtrl");
        
        
        var vAreaS = ${ofertaVO.idAreaLaboral};
        dijit.byId('areaSelect').disabled=false;
        areaStore.url = "${context}ofertaEdicion.do?method=obtenerAreas";
    	areaStore.close();     
    	
    	dijit.byId('ocupacionSelect').disabled=false;
    	ocupacionStore.url = "${context}ofertaEdicion.do?method=obtenerOcupaciones" + "&" + "idArea="+ vAreaS;
    	ocupacionStore.close();

    	
    	
    	dojo.connect(dijit.byId("areaSelect"), "onChange", function() {

    		if(document.registroUbicacionFormBean.flagArea.value=="1"){
    			dijit.byId('ocupacionSelect').reset();
    			document.registroUbicacionFormBean.ocupacion.value="-1";
    			}
    			else
    			{
    			document.registroUbicacionFormBean.flagArea.value="1";
    			}

    		var vArea = dijit.byId('areaSelect').get('value');
    			
        	dijit.byId('ocupacionSelect').disabled=false;
        		
        	ocupacionStore.url = "${context}ofertaEdicion.do?method=obtenerOcupaciones" + "&" + "idArea="+ vArea;
        	ocupacionStore.close();
    	
        
        document.registroUbicacionFormBean.area.value=dijit.byId('areaSelect').get('value');
    	});
    	
    	dojo.connect(dijit.byId("ocupacionSelect"), "onChange", function() {
    		var vArea = dijit.byId('ocupacionSelect').get('value');
        	document.registroUbicacionFormBean.ocupacion.value=dijit.byId('ocupacionSelect').get('value');
    	});


    	dojo.connect(dijit.byId("sectorSelect"), "onChange", function() {
    		document.registroUbicacionFormBean.sector.value=dijit.byId('sectorSelect').get('value');
    	});
    	
    	areaStore.fetch({
    	      	onComplete: function(items, request) {       
    	      		var areaV = ${ofertaVO.idAreaLaboral};
    	      		if (items.length == 0) return;                    	
    	      		dijit.byId('areaSelect').attr('value', areaV);
    	      		document.registroUbicacionFormBean.area.value=dijit.byId('areaSelect').get('value');
    	      		
    	      		
    	      		
    	      	}
    		});
    	
    	 
    	 ocupacionStore.fetch({
    	      	onComplete: function(items, request) {
    	      		var ocupacionV = ${ofertaVO.idOcupacion};
    	      		if (items.length == 0) return;                    	
    	      		dijit.byId('ocupacionSelect').attr('value', ocupacionV);
    	      		document.registroUbicacionFormBean.ocupacion.value=dijit.byId('ocupacionSelect').get('value');
    	      	}
    		});
    	 
    	
    	 dojo.connect(dijit.byId("sectorSelect"), "onChange", function() {
    	     document.registroUbicacionFormBean.sector.value=dijit.byId('sectorSelect').get('value');


    	});  
        
//TERMINA DOMICILIO 1
		
    	 var entidadV = ${entidad1.entidad};

    		dijit.byId('idEntidadSelect2').disabled=false;
    	    entidadFederativaStore2.url = "${context}ofertaEdicion.do?method=obtenerCatEntidades";
    		entidadFederativaStore2.close();          

    		dijit.byId('idMunicipioSelect2').disabled=false;
    		municipioStore2.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ entidadV;
    		municipioStore2.close();
		
		
    		dojo.connect(dijit.byId("idEntidadSelect2"), "onChange", function() {

    			if(document.registroUbicacionFormBean.flagEntidad2.value=="1"){
    			dijit.byId('idMunicipioSelect2').reset();
    			document.registroUbicacionFormBean.prefiereCandidatosMunicipio.value="-1";
    			}
    			else
    			{
    			document.registroUbicacionFormBean.flagEntidad2.value="1";
    			}
    			
    				var vEntidad3 = dijit.byId('idEntidadSelect2').get('value');
    				
    				
    		    	dijit.byId('idMunicipioSelect2').disabled=false;
    		    		
    		    	municipioStore2.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad3;
    		    	municipioStore2.close();
    			
    		    	
    		    	
    		    document.registroUbicacionFormBean.prefiereCandidatosEntidad.value=dijit.byId('idEntidadSelect2').get('value');
    		});

    		 dojo.connect(dijit.byId("idMunicipioSelect2"), "onChange", function() {
    		     document.registroUbicacionFormBean.prefiereCandidatosMunicipio.value=dijit.byId('idMunicipioSelect2').get('value');


    		});  
    		 
    		 entidadFederativaStore2.fetch({
    		      	onComplete: function(items, request) {       
    		      		var entidadV = ${entidad1.entidad};
    		      		if (items.length == 0) return;                    	
    		      		dijit.byId('idEntidadSelect2').attr('value', entidadV);
    		      		document.registroUbicacionFormBean.prefiereCandidatosEntidad.value=dijit.byId('idEntidadSelect2').get('value');
    		      		
    		      		
    		      	}
    			});
    		
    		 municipioStore2.fetch({
    		      	onComplete: function(items, request) {
    		      		var minicipioV = ${entidad1.municipio};
    		      		if (items.length == 0) return;                    	
    		      		dijit.byId('idMunicipioSelect2').attr('value', minicipioV);
    		      		document.registroUbicacionFormBean.prefiereCandidatosMunicipio.value=dijit.byId('idMunicipioSelect2').get('value');
    		      	}
    			});
    		
		
		
});  

</script>

