<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.vo.ContactoVO"%>
<%@ page import="mx.gob.stps.portal.core.oferta.vo.TerceraEmpresaVO"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<% 
List<CatalogoOpcionVO> listaEmpresaOfrece= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_EMPRESA_OFRECE_EMPLEO");	
List<CatalogoOpcionVO> listaContactoEmpresa= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_NOMBRE_CONTACTO_EMPRESA");	
List<CatalogoOpcionVO> listaMedioContacto= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_MEDIO_CONTACTO");	
List<CatalogoOpcionVO> listaDuracionAproximada= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_DURACION_APROXIMADA");	
List<CatalogoOpcionVO> listaHorarioContacto= (List<CatalogoOpcionVO>)request.getAttribute("CATALOGO_OPCION_HORARIO_CONTACTO");	

ArrayList<TerceraEmpresaVO> listaTercerasEmpresas= (ArrayList<TerceraEmpresaVO>)request.getAttribute("tercerasEmpresas");
ArrayList<TerceraEmpresaVO> empresas= (ArrayList<TerceraEmpresaVO>)request.getAttribute("Empresa");
TerceraEmpresaVO empresa=empresas.get(0);
ArrayList<ContactoVO> listaContactos=(ArrayList<ContactoVO>)request.getAttribute("contactos");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title><tiles:getAsString name="title" /></title>
<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />

<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />

<link href="${PATH_CSS_SWB_APP}estilos_canal.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />

<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_genericos.css" rel="stylesheet" type="text/css" />

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

<script type="text/javascript">
    dojo.require("dijit.Dialog");
    
function doSubmit(method){
	document.registroContactoFormBean.method.value=method;
	document.registroContactoFormBean.Guardar.disabled=true;
	if(validaDatos()){
		document.registroContactoFormBean.submit();
		return true;
	   }
	else{
		document.registroContactoFormBean.Guardar.disabled=false;
		return false;
		
	}
	
}

function cancelaRegistro() {

	if(confirm("Los datos no guardados se perderán ¿Continuar?")) {
		document.registroContactoFormBean.action='<c:url value="/OfertaNavegacion.do?method=init" />';
		document.registroContactoFormBean.submit();
	} 
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
 	
	if(document.registroContactoFormBean.nombreEmpresa.value == "-2" ){
		document.registroContactoFormBean.nombreEmpresa.focus();
		mensaje("El siguiente campo muestra datos invalidos: Nombre de la empresa.");
		
	return false;
	}
	if(document.registroContactoFormBean.nombreEmpresa.value == "-2" ){
		document.registroContactoFormBean.nombreEmpresa.focus();
		mensaje("El siguiente campo muestra datos invalidos: Nombre de la empresa.");
		
	return false;
	}
	if(document.registroContactoFormBean.nombreContacto.value == "-1" ){
		document.registroContactoFormBean.nombreContacto.focus();
		mensaje("El siguiente campo muestra datos invalidos: Nombre de Contacto.");
		
	return false;
	}
	
	if(document.registroContactoFormBean.teleconoContacto.checked==false &&
    		document.registroContactoFormBean.correoContacto.checked==false 
    				){
    		document.registroContactoFormBean.teleconoContacto.focus();
			mensaje("El siguiente campo muestra datos invalidos: Medio para contactar la oferta");
		return false;
    	}
	
	
	if(document.registroContactoFormBean.domingo.checked==false &&
    		document.registroContactoFormBean.lunes.checked==false &&
    		document.registroContactoFormBean.martes.checked==false &&
    		document.registroContactoFormBean.miercoles.checked==false &&
    		document.registroContactoFormBean.jueves.checked==false &&
    		document.registroContactoFormBean.viernes.checked==false &&
    		document.registroContactoFormBean.sabado.checked==false
    				){
    		document.registroContactoFormBean.domingo.focus();
			mensaje("El siguiente campo muestra datos invalidos: Horario de entrevista, días");
		return false;
    	}
	
	if(document.registroContactoFormBean.horaAtencionInicio.value == "-1" ){
		document.registroContactoFormBean.horaAtencionInicio.focus();
		mensaje("El siguiente campo muestra datos invalidos: Hoario de atención, inicio.");
		
	return false;
	}
	if(document.registroContactoFormBean.horaAtencionFin.value == "-1" ){
		document.registroContactoFormBean.horaAtencionFin.focus();
		mensaje("El siguiente campo muestra datos invalidos: Horario de atencion, fin.");
		
	return false;
	}
	
	
	
	
	return true;
}


function mensaje(mensaje){
	dojo.byId('mensaje').innerHTML = mensaje;
	dijit.byId('MensajeAlert').show();		
}

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
  


<form name="registroContactoFormBean" action="ofertaRegistroContacto.do"	method="post">
<input type="hidden" name="method"	value="guardaContactoInit" />
<div class="div_tab">
			
			<input type="button" class="boton_divisor" value=" " />
			<input type="button" class="boton_tab${thisTabId == 1? '_sel':'' }" value="${thisTabId == 1? thisTabName:'1' }"  />
			<input type="button" class="boton_separador" value="  " />
			<input type="button" class="boton_tab${thisTabId == 2? '_sel':'' }" value="${thisTabId == 2? thisTabName:'2' }"  />
			<input type="button" class="boton_separador" value="  " />
			<input type="button" class="boton_tab${thisTabId == 3? '_sel':'' }" value="${thisTabId == 3? thisTabName:'3. Datos de contacto' }" />
			<input type="button" class="boton_separador" value="  " />
			<input type="button" class="boton_divisor_fin" value="" />
			
	</div>	
	
	
	
	
	
	<% 
	String nombreEmpresa=null;
	if(empresa.getTipo()==Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona())
		nombreEmpresa=empresa.getNombre();
	else
		nombreEmpresa=empresa.getRazonSocial();
	%>
	
    <div class="entero">
         <p><strong><label for="nombreEmpresa">Nombre de la empresa que ofrece la oferta</label>* </strong> <br><select name="nombreEmpresa" id="nombreEmpresa" style="width: 400px">
         <option value="-2" selected>Seleccione  </option>
         <option value="0" > <%=nombreEmpresa%> </option>
    <%for (TerceraEmpresaVO catalogoOpcionVO : listaTercerasEmpresas){
    	if(catalogoOpcionVO.getTipo()==Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona())
    		nombreEmpresa=catalogoOpcionVO.getNombre(); 
    	else
    		nombreEmpresa=catalogoOpcionVO.getRazonSocial();
    %>
	<option value="<%=catalogoOpcionVO.getIdTerceraEmpresa() %>" ><%=nombreEmpresa %></option>
    <%}%>  
		</select>
         </p>
    </div>
    <div class="entero">
         <p><span><strong><label for="nombreContacto">Nombre del contacto</label>*  </strong><br><select name="nombreContacto" id="nombreContacto" style="width: 400px">
		<option value="-1">Seleccione </option>
    		<%for (ContactoVO catalogoOpcionVO : listaContactos){%>
			<option value="<%=catalogoOpcionVO.getIdContacto() %>"  ><%=catalogoOpcionVO.getNombreContacto() %></option>
    		<%}%>  
		</select></span>
         </p>
         </div>
         <div class="entero">
         
         <span ><strong>Medio para contactar la oferta*</strong> </span>
          <span ><label for="teleconoContacto">Teléfono</label><input type=checkbox id="teleconoContacto" name="teleconoContacto" /></span>
         <span ><label for="correoContacto">Correo electónico</label><input type=checkbox id="correoContacto" name="correoContacto" /></span>
		 
         
         
    </div>
    <h3></h3>
    <div class="entero">
    <p><strong>Horario de entrevista.</strong></p>
    </div>
    <br><br>
    
    <div class="entero">
    <p>
	<span>Días* </span> 
	<span><label>Dom<input type=checkbox id="domingo" name="domingo"/ ></label></span>
    <span><label>Lun<input type=checkbox id="lunes" name="lunes" /></label></span>
	<span><label>Mar<input type=checkbox id="martes" name="martes" /></label></span>
	<span><label>Mie<input type=checkbox id="miercoles" name="miercoles"/></label></span>
	<span><label>Jue<input type=checkbox id="jueves" name="jueves" /></label></span>
	<span><label>Vie<input type=checkbox id="viernes" name="viernes" /></label></span>
	<span><label>Sab<input type=checkbox id="sabado" name="sabado" /></label></span>
	</p>
	</div>
    
    
     <div class="entero">
         <p><span><label for="horaAtencionInicio">Horario*  de</label>:<select name="horaAtencionInicio" id="horaAtencionInicio" >
          <option value="-1" selected>Seleccione  </option>
           <%for (CatalogoOpcionVO catalogoOpcionVO : listaHorarioContacto){%>
		    <option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>" ><%=catalogoOpcionVO.getOpcion() %></option>
    	    <%}%>  
         </select>
         <label for="horaAtencionFin">a</label>:
         <select name="horaAtencionFin" id="horaAtencionFin" >
          <option value="-1" selected>Seleccione  </option>
         <%for (CatalogoOpcionVO catalogoOpcionVO : listaHorarioContacto){%>
		 <option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  ><%=catalogoOpcionVO.getOpcion() %></option>
    	 <%}%>  </select>
          </span></p> 
   </div>
    
    
    
     <div class="entero">
         <p><label for="duracionAtencion">Duración aproximada</label> <select name="duracionAtencion" id="duracionAtencion" >
          <option value="-1" selected>Seleccione  </option>
         <%for (CatalogoOpcionVO catalogoOpcionVO : listaDuracionAproximada){%>
		    <option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  ><%=catalogoOpcionVO.getOpcion() %></option>
    	    <%}%>  
	`	 </select>
         </p>
    </div>
    
     <div class="entero">
     <span class="un_tercio"></span>
	 <span class="un_tercio">
     <input type="button" value="Guardar" name="Guardar"	class="boton"	onclick="doSubmit('guardarContacto');" />
     <input type="button" value="Cancelar" class="boton" onClick="cancelaRegistro();"/>
     </span>
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
    </body>




<%

if(request.getAttribute("requisitosGuardados")!=null){
	String mensaje="Los datos han sido guardados exitosamente";
	%>
	<script type="text/javascript">
	alert('<%=mensaje%>');
	</script> 
	 
<% 
} 
%>

