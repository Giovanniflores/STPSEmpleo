<%@page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@page import="mx.gob.stps.portal.web.security.vo.UsuarioWebVO"%>
<%@page import="mx.gob.stps.portal.core.oferta.registro.vo.OfertaIdiomaVO"%>
<%@page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@page import="mx.gob.stps.portal.core.infra.vo.ContactoVO"%>
<%@page import="mx.gob.stps.portal.core.oferta.vo.TerceraEmpresaVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

//UsuarioWebVO usuario =(UsuarioWebVO) request.getSession().getAttribute("USUARIO_APP");
//long idEmpresa =usuario.getIdPropietario();
//long perfil =usuario.getIdTipoUsuario();
long IdEmpresa=Long.parseLong(request.getSession().getAttribute("idEmpresa").toString());
long perfil=Long.parseLong(request.getSession().getAttribute("perfil").toString());

String guardar="Guardar";
if(perfil==(long)Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario())
{
	guardar="Autorizar";
}
%>

<jsp:useBean id="ofertaVO" scope="session" class="mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO" />

<%
if(ofertaVO.getDiasEntrevista()==null)
	ofertaVO.setDiasEntrevista("0000000");
%>

<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_genericos.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	#dialogDetalle_underlay { background-color:gray; }
</style>

<script type="text/javascript">
    dojo.require("dijit.Dialog");
    
function doSubmit(method){
	document.registroContactoFormBean.method.value=method;
	document.registroContactoFormBean.Guardar.disabled=true;
	if(validaDatos()){
		document.registroContactoFormBean.submit();
		return true;
	   }
	else
		{
		document.registroContactoFormBean.Guardar.disabled=false;
		return false;
		}
	
}

function cancelaRegistro(usuario) {

	if(confirm("Los datos no guardados se perderán ¿Continuar?")) {
		
		if(usuario=='200')
    		document.registroContactoFormBean.action='<c:url value="/OfertaNavegacion.do?method=init"/>';
    	if(usuario=='100')
        	document.registroContactoFormBean.action="autorization.do?method=init";
        		
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


<form name="registroContactoFormBean" action="ofertaEdicionContacto.do" method="post">

	<input type="hidden" name="method" value="guardaContactoInit"/>
	<input type="hidden" name="idOferta" value='<%=request.getAttribute("idOferta")%>'/>
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
	
	<c:if test="${not empty USUARIO_APP and (USUARIO_APP.publicador or USUARIO_APP.administrador)}">
    <div class="entero" style="text-align: left;">
		<input type="button" value="Detalle Empresa" class="boton" onClick="showDetalleEmpresa(${ofertaVO.idEmpresa});"/>
    </div>    
    </c:if>

	<% 
	String nombreEmpresa=null;
	if(empresa.getTipo()==Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona())
		nombreEmpresa=empresa.getNombre();
	else
		nombreEmpresa=empresa.getRazonSocial();
	%>
	
    <div class="entero">
         <p><strong>Nombre de la empresa que ofrece la oferta* </strong> <br><select name="nombreEmpresa" id="nombreEmpresa" style="width: 400px" >
         <option value="-2" selected>Seleccione  </option>
         <option value="0" <%=ofertaVO.getIdTerceraEmpresa()==0?"selected":"" %>> <%=nombreEmpresa%> </option>
    <%for (TerceraEmpresaVO catalogoOpcionVO : listaTercerasEmpresas){
    	if(catalogoOpcionVO.getTipo()==Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona())
    		nombreEmpresa=catalogoOpcionVO.getNombre(); 
    	else
    		nombreEmpresa=catalogoOpcionVO.getRazonSocial();
    %>
	<option value="<%=catalogoOpcionVO.getIdTerceraEmpresa() %>" <%=ofertaVO.getIdTerceraEmpresa()==catalogoOpcionVO.getIdTerceraEmpresa()?"selected":"" %>><%=nombreEmpresa %></option>
    <%}%>  
		</select>
         </p>
    </div>
    <div class="entero">
         <p><span><strong>Nombre del contacto*  </strong><br><select name="nombreContacto" id="nombreContacto" style="width: 400px">
		<option value="-1">Seleccione </option>
    		<%
    		if (listaContactos!=null){
    		for (ContactoVO catalogoOpcionVO : listaContactos){%>
			<option value="<%=catalogoOpcionVO.getIdContacto() %>"  <%=ofertaVO.getIdContacto()==catalogoOpcionVO.getIdContacto()?"selected":"" %>><%=catalogoOpcionVO.getNombreContacto() %></option>
    		<%}
    		}%>  
		</select></span>
         </p>
         </div>
         <div class="entero">
         
         <span ><strong>Medio para contactar la oferta*</strong> </span>
          <span >Teléfono<input type=checkbox id="teleconoContacto" name="teleconoContacto" <%=ofertaVO.getContactoTel()>1?"checked":"" %>/></span>
         <span >Correo electónico<input type=checkbox id="correoContacto" name="correoContacto" <%=ofertaVO.getContactoCorreo()>1?"checked":"" %>/></span>
		 
         
         
    </div>
    <h3></h3>
    <div class="entero">
    <strong>Horario de entrevista.</strong>
    </div>
    <br><br>
    
    <div class="entero">
    <p>
	<span>Días* </span> 
	<span>Dom<input type=checkbox id="domingo" name="domingo"/ <%=ofertaVO.getDiasEntrevista().charAt(0)=='1'?"checked":"" %>></span>
    <span>Lun<input type=checkbox id="lunes" name="lunes" <%=ofertaVO.getDiasEntrevista().charAt(1)=='1'?"checked":"" %>/></span>
	<span>Mar<input type=checkbox id="martes" name="martes" <%=ofertaVO.getDiasEntrevista().charAt(2)=='1'?"checked":"" %>/></span>
	<span>Mie<input type=checkbox id="miercoles" name="miercoles" <%=ofertaVO.getDiasEntrevista().charAt(3)=='1'?"checked":"" %>/></span>
	<span>Jue<input type=checkbox id="jueves" name="jueves" <%=ofertaVO.getDiasEntrevista().charAt(4)=='1'?"checked":"" %>/></span>
	<span>Vie<input type=checkbox id="viernes" name="viernes" <%=ofertaVO.getDiasEntrevista().charAt(5)=='1'?"checked":"" %>/></span>
	<span>Sab<input type=checkbox id="sabado" name="sabado" <%=ofertaVO.getDiasEntrevista().charAt(6)=='1'?"checked":"" %>/></span>
	</p>
	</div>
    
   
     <div class="entero">
         <p><span>Horario*  de:<select name="horaAtencionInicio" id="horaAtencionInicio" >
          <option value="-1" selected>Seleccione  </option>
           <%for (CatalogoOpcionVO catalogoOpcionVO : listaHorarioContacto){%>
		    <option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=catalogoOpcionVO.getIdCatalogoOpcion()==ofertaVO.getIdHorarioDe()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
    	    <%}%>  
         </select>
         a:
         <select name="horaAtencionFin" id="horaAtencionFin" >
          <option value="-1" selected>Seleccione  </option>
         <%for (CatalogoOpcionVO catalogoOpcionVO : listaHorarioContacto){%>
		 <option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=catalogoOpcionVO.getIdCatalogoOpcion()==ofertaVO.getIdHorarioA()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
    	 <%}%>  </select>
          </span></p> 
   </div>
    
    
    
     <div class="entero">
         <p>Duración aproximada <select name="duracionAtencion" id="duracionAtencion" >
          <option value="-1" selected>Seleccione  </option>
         <%for (CatalogoOpcionVO catalogoOpcionVO : listaDuracionAproximada){%>
		    <option value="<%=catalogoOpcionVO.getIdCatalogoOpcion() %>"  <%=catalogoOpcionVO.getIdCatalogoOpcion()==ofertaVO.getIdDuracionAproximada()?"selected":"" %>><%=catalogoOpcionVO.getOpcion() %></option>
    	    <%}%>  
	`	 </select>
         </p>
    </div>
    
     <div class="entero">
     <span class="un_tercio"></span>
	 <span class="un_tercio">
     <input type="button" value=<%=guardar %> name="Guardar" class="boton" onclick="doSubmit('guardarContacto');" />
     <input type="button" value="Cancelar" class="boton" onClick="cancelaRegistro(${usuario});"/>
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
