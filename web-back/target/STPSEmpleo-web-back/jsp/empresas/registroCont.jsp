<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.core.empresa.vo.RegistroContactoVO"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
  String context = request.getContextPath() +"/";  
  String TELEFONO_CELULAR_ID = request.getSession().getAttribute("TELEFONO_CELULAR_ID").toString();
  String TELEFONO_CELULAR_DES = request.getSession().getAttribute("TELEFONO_CELULAR_DES").toString();
  String TELEFONO_FIJO_ID = request.getSession().getAttribute("TELEFONO_FIJO_ID").toString();
  String TELEFONO_FIJO_DES = request.getSession().getAttribute("TELEFONO_FIJO_DES").toString();
  String TERMINOS_CONDICIONES = request.getSession().getAttribute("TERMINOS_CONDICIONES").toString();
  String CLAVE_TELEFONO_FIJO = request.getSession().getAttribute("CLAVE_TELEFONO_FIJO").toString();
  String CLAVE_TELEFONO_CELULAR = request.getSession().getAttribute("CLAVE_TELEFONO_CELULAR").toString();

  
  String CLAVE_TIPO_PERSONA_FISICA = request.getSession().getAttribute("CLAVE_TIPO_PERSONA_FISICA").toString();
  String CLAVE_TIPO_PERSONA_MORAL = request.getSession().getAttribute("CLAVE_TIPO_PERSONA_MORAL").toString();
  String ESTATUS_ACTIVO_ID = request.getSession().getAttribute("ESTATUS_ACTIVO_ID").toString();
  String ESTATUS_ACTIVO_DES = request.getSession().getAttribute("ESTATUS_ACTIVO_DES").toString(); 
  String ESTATUS_INACTIVO_ID = request.getSession().getAttribute("ESTATUS_INACTIVO_ID").toString(); 
  String ESTATUS_INACTIVO_DES = request.getSession().getAttribute("ESTATUS_INACTIVO_DES").toString();  
  List<RegistroContactoVO> lstTercerasEmpresas = (List<RegistroContactoVO>) request.getSession().getAttribute("LST_REGISTRO_CONTACTO");
  String DES_TIPO_EMPRESA_PADRE = request.getSession().getAttribute("DES_TIPO_EMPRESA_PADRE").toString();
  
%>

<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_registro_Contactos.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.redFont{
		color: #FF0000;
	}
	.Font80{
		font-size: 80%;
	}
</style>
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

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

function doSubmit(method){
	document.regContForm.method.value=method;
	document.regContForm.submit();		
}


function doSubmitAjax(method){

	if (dijit.byId('regContForm').isValid() && checkOtherFields()){		
		dojo.byId('method').value=method;
		dojo.byId('btnEnviar').disabled=true;
		
				
		//DOMICILIO: entidad, municipio, colonia
		if (dijit.byId('entidadFederativaSelect').get('item') && dijit.byId('entidadFederativaSelect').get('item').label){
			dojo.byId('idEntidad').value = dijit.byId('entidadFederativaSelect').get('item').label[0];
		}
		if (dijit.byId('municipioSelect').get('item') && dijit.byId('municipioSelect').get('item').label){
			dojo.byId('idMunicipio').value = dijit.byId('municipioSelect').get('item').label[0];
		}
		if (dijit.byId('coloniaSelect').get('item') && dijit.byId('coloniaSelect').get('item').label){
			dojo.byId('idColonia').value = dijit.byId('coloniaSelect').get('item').label[0];
		}		
			
		dojo.xhrPost(
				 {
				  url: 'regCont.do',
				  form:'regContForm',
				  timeout:180000, 
				  load: function(data){
					    var res = dojo.fromJson(data); 
					    dojo.byId('message').innerHTML=res.message;
					    
						if (res.type!='err'){
					    	dojo.byId('message').style.color='#000066';
					    	dojo.byId('divRegis').style.display='none';
					    }else{
					    	dojo.byId('message').style.color='#FF0000';
					    	dojo.byId('divRegis').style.display='block';
					    }
				    }
				 } );
	} else {
		dojo.byId('message').innerHTML = 'Los siguientes campos muestran datos inválidos - Se muestra(n) el (los) campo(s) con inconsistencias.';
	}	
}

//funcion para indicar que un campo es requerido
function setWidgetRequiredAttributes(baseDomNodeId, requiredValue){
    var widget = dojo.byId(baseDomNodeId);
    widget.required = requiredValue; 
}

//funcion para evitar que avance de un campo si el contenido no es valido
function verifyStop(id){
	var widget = dijit.byId(id);
	if(!widget.isValid()){
		widget.focus();
	}	
}

//funcion para validar campos de manera alterna al DOJO
function checkOtherFields(){
	var vForm = true;
	var vMessage = '';
	var vNombreContacto = dijit.byId('nombreContacto');
	var vCargo = dijit.byId('cargo');
	var vCorreoElectronico = dijit.byId('correoElectronico');
	var vConfirmarCorreoElectronico = dijit.byId('confirmarCorreoElectronico');
	var vTipoTelefono = getRadioValue('idTipoTelefono'); 	
	var vTelefono = dijit.byId('telefono');
   	
	
	if(vCorreoElectronico.value!=vConfirmarCorreoElectronico.value){
		vForm = false;
		vMessage = vMessage + '\nEl correo electrónico y su confirmación deben ser iguales.';
		vCorreoElectronico.focus();
	}	
	if(!vNombreContacto.value){
		vForm = false;
		vMessage = vMessage + '\nDebe proporcionar el Nombre del Contacto de la empresa.';	
		vNombreContacto.focus();
	}
	
	if(!vCargo.value){
		vForm = false;
		vMessage = vMessage + '\nDebe proporcionar el Cargo del Contacto de la empresa.';	
		vCargo.focus();
	}
	
	if(vMessage.length>0){
		alert(vMessage);
	}
	return vForm;
}

//Funcion para validar campos de verificacion
function checkVerificationField(idField1, idField2, lblField1, lblField2){
	var widget1 = dijit.byId(idField1);
	var widget2 = dijit.byId(idField2);
	if(widget1.value != widget2.value){
		alert('Compruebe que el campo ' + lblField1 + ' y el campo ' + lblField2 + ' tengan el mismo valor.');
		widget1.focus();
	}
}


//funcion para obtener el valor de un radiobutton seleccionado
function getRadioValue(idOrName) {
	var value = null;
	var element = document.getElementById(idOrName);
	var radioGroupName = null;  
	
	if (element == null) {
		radioGroupName = idOrName;
	} else {
		radioGroupName = element.name;     
	}
	if (radioGroupName == null) {
		return null;
	}
	var radios = document.getElementsByTagName('input');
	for (var i=0; i<radios.length; i++) {
		var input = radios[ i ];    
		if (input.type == 'radio' && input.name == radioGroupName && input.checked) {                          
			value = input.value;
			break;
		}
	}
	return value;
}

	//DOMICILIO
	//TERMINA DOMICILIO
	
	//TELEFONOS	
	//Desplegar ventana para captura de telefonos adicionales
	function openPhoneWindow(){	
	 	var newWin = window.open("<%=context%>phone.do?method=init", "Teléfonos","height=500,width=900,resizable=yes,scrollbars=yes"); 	
	} 	
	
	//Dependiendo de la seleccion de tipo de telefono, coloca la clave de acceso correspondiente
	function fillUpAccessKey(){
		var tipoTelefonoId = getRadioValue('idTipoTelefono');
		var accesoDes;
        if(tipoTelefonoId==<%=TELEFONO_CELULAR_ID%>){ 
        	accesoDes = '<%=CLAVE_TELEFONO_CELULAR%>';
        } else {
        	accesoDes = '<%=CLAVE_TELEFONO_FIJO%>';
        }      		
        var wAcceso = dijit.byId('acceso');
        wAcceso.attr('value',accesoDes);
        //wAcceso.attr('disabled', 'disabled');
	}	
	
	//funcion para validar que los tamaños de la clave LADA y el telefono correspondan
	function changePhoneSizeRequired(){
		var vClave = document.getElementById('clave');
		var vTelefono = document.getElementById('telefono');
	 
		if(vClave.value.length<2){
			alert('Debe proporcionar una clave LADA de 2 ó 3 caracteres');
		} else if(vClave.value.length==2) {
			if(vTelefono.value.length!=8){
				alert('Debe proporcionar un teléfono de 8 digitos');
			}
		} else if(vClave.value.length==3){
			if(vTelefono.value.length!=7){
				alert('Debe proporcionar un teléfono de 7 digitos');
			}	
		}
		
	}	
	//TERMINA TELEFONOS		
	    
</script>


<html:messages id="errors">
	<span class="redFont Font80"><bean:write name="errors"/></span><br/>
</html:messages>

<html:messages id="messages" message="true">
	<span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<h2>Registro de contactos</h2>
<form name="regContForm" id="regContForm" method="post" action="regCont.do" dojoType="dijit.form.Form">

	<input type="hidden" name="method" id="method" value="init"/>
	<input type="hidden" name="Ctrl" id="Ctrl" dojoType="dijit.form.TextBox" value="1"/>
	<input type="hidden" name="selectContactos" id="selectContactos" value="-1"/>
	
	<!-- CONTACTOS PREVIAMENTE REGISTRADAS -->
	<h3>Contactos registrados</h3>
	<DIV>
    	<DIV class="entero">
	    	<span class="un_medio">
	    	<strong>Contactos registrados - Estatus</strong><br>
			<select id="selectContactos" name="selectContactos" size="5" >
			<%
			Iterator itContactos = lstTercerasEmpresas.iterator();
			while(itContactos.hasNext()){
				RegistroContactoVO contactoVo = (RegistroContactoVO) itContactos.next();
				long idContancto = contactoVo.getIdContacto();
				String strStatus = String.valueOf(contactoVo.getEstatus());
				String strName; 			
				String strStatusLegend;		
				strName = contactoVo.getNombreContacto();
				if(ESTATUS_ACTIVO_ID.equalsIgnoreCase(strStatus)){
					strStatusLegend = ESTATUS_ACTIVO_DES;
				} else {
					strStatusLegend = ESTATUS_INACTIVO_DES;
				}
				
				%>
				<option value="<%=idContancto%>"><%=strName%> - <%=strStatusLegend%></option>
				<%			
			}
			%>			
			</select> 		    	
			</span>        
	    	<span class="un_medio">
			<input type="button" id="btnCambiarEstatus" value="Cambiar estatus" class="boton" onclick="doSubmitAjax('cambiarEstatus');" />	    	
			</span>        			
        </DIV>	
	</DIV>	
	
	
	<h3>Registro de contactos</h3>	
	<h4>Datos generales de contactos</h4>	
	
	<!-- Persona Física onBlur="cambiarMayusculas(this)" -->		

	<DIV class="entero">
	<span class="un_cuarto">		
	<strong>Nombre(s)*</strong>
	<input name="nombreContacto" id="nombreContacto" maxlength="150" size="50" 
	onblur="verifyStop('nombreContacto');" 
	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\D\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$" uppercase="true"
	invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
	<label for="nombreContacto"></label>
	</span>
	</DIV>
	<DIV class="entero">
	<span class="un_cuarto">	    
	<strong>Cargo *</strong>
	<input id="cargo" name="cargo" maxlength="150" size="50" 
	onblur="verifyStop('cargo');"  
	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\D\s\.áéíóúÁÉÍÓÚñÑ]{1,50}$"  uppercase="true"
	invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
	<label for="cargo"></label>
	</span>
	</DIV>

	<!-- DOMICILIO -->
	<h4>Domicilio</h4>
	<DIV class="entero">
	<span class="un_tercio">
	<strong>Código postal*</strong><br>
	<input id="codigoPostal" name="codigoPostal" maxlength="6" size="6" value="${ComRegPartnerCompanyForm.codigoPostal}"
		dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,6}" 
		invalidMessage="Dato Invalido" trim="true" />        
	<label for="codigoPostal"></label>	       			   		
	</span>	
	<span class="un_tercio">
	<input type="checkBox" id="checkCtrl">
	<label for="checkCtrl">No conozco mi código postal</label>               			   
	</span>	
	<span class="un_tercio">
	<div id="btnValidar" dojoType="dijit.form.Button">Validar CP</div>		
	</span>
	</DIV>
			   
	<DIV class="entero">
	<span class="un_tercio"><strong>País</strong><br>			        
	<input name="pais" id="pais" maxlength="10" size="6" value="México" disabled />
	<label for="pais"></label>	
	</span>
	</DIV>
	
	<DIV class="entero">  
	<span class="un_tercio">     			   
	<strong>Entidad federativa*</strong><br>
			<input type="hidden" name="idEntidad" id="idEntidad" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true" ></div>
			<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="entidadFederativaSelect" required="false" disabled="true" autoComplete="false"></select>					
	</span>
	<span class="un_tercio">
	<strong>Municipio*</strong><br>
			<input type="hidden" name="idMunicipio" id="idMunicipio" value="" />
			<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" ></div>
			<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="municipioSelect" required=""false"" disabled="true" autoComplete="false"></select>			        
	</span>
	<span class="un_tercio">
	<strong>Colonia*</strong><br>
	<input type="hidden" name="idColonia" id="idColonia" value="" />
	<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true" ></div>
	<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="coloniaSelect" required=""false"" disabled="true" autoComplete="false"></select>			        
	</span> 
	</DIV>
			   
	<DIV class="entero">  
	<span class="un_tercio"><strong>Calle*</strong><br>
	<input id="calle" name="calle" maxlength="60" size="60" value="${ComRegPartnerCompanyForm.calle}" 
		dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[\D\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$" uppercase="true"
		invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>			             			   
	<label for="calle"></label>
	</span> 
	
	<span class="un_tercio"><strong>Número exterior*</strong><br> 
	<input id="numeroExterior" name="numeroExterior" maxlength="6" size="4" value="${ComRegPartnerCompanyForm.numeroExterior}"
		dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,4}" 
		invalidMessage="Dato Invalido" trim="true" />        
	<label for="numeroExterior"></label>				            			   
	</span> 
	
	<span class="un_tercio"><strong>Número interior</strong><br>
	<input id="numeroInterior" name="numeroInterior" maxlength="6" size="4" value="${ComRegPartnerCompanyForm.numeroInterior}"
		dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,4}" 
		invalidMessage="Dato Invalido" trim="true" />        
	<label for="numeroInterior"></label>				             			   
	</span> 			        
	</DIV>
			   
	<DIV class="entero">  
	<span class="un_tercio"><strong>Entre calles*</strong><br> 
	<input id="entreCalle" name="entreCalle" maxlength="60" size="60" value="${ComRegPartnerCompanyForm.entreCalle}" 
		dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\D\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$" uppercase="true"
		invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"/>
	<label for="entreCalle"></label>			            			   
	</span> 			      
	<span class="un_tercio"><strong>Y*</strong><br>
	<input id="yCalle" name="yCalle" maxlength="60" size="60" value="${ComRegPartnerCompanyForm.yCalle}" 
		dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[\D\s\.áéíóúÁÉÍÓÚñÑ]{1,150}$" uppercase="true"
		invalidMessage="Dato Invalido" trim="true"/>
	<label for="yCalle"></label>     			   
	</span> 			        
	</DIV>
	<!-- TERMINA DOMICILIO --> 

	<h4>Datos de identificación de la cuenta</h4>
	<!-- onchange="verifyStop('correoElectronico');" onblur="verifyStop('correoElectronico');"  -->       			   
	<DIV class="entero">  
	<span class="un_tercio">  
	<strong>Correo electrónico*</strong><br>
	<input id="correoElectronico" name="correoElectronico" maxlength="65" size="65" 
		   dojoType="dijit.form.ValidationTextBox" required="true" 			       
		   regExp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" 
		   invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" />
	<label for="correoElectronico"></label>    			             			   
	</span> 			
	<!-- onBlur="checkVerificationField('correoElectronico','confirmarCorreoElectronico','Correo electrónico','Confirmar correo electrónico');" -->
	<span class="un_tercio">   
	<strong>Confirmar correo electrónico*</strong><br>
	<input id="confirmarCorreoElectronico" name="confirmarCorreoElectronico" maxlength="65" size="65" 
		   dojoType="dijit.form.ValidationTextBox" required="true" 		       
		   regExp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" 		       
		   invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" />    
	<label for="confirmarCorreoElectronico"></label>               			          			   
	</span> 			        			          			                			          
	</DIV>
	
	<!-- TELEFONO -->       			   
	<h4>Teléfono*</h4>       		
	<DIV class="entero">  
	<span class="un_quinto"><strong>Tipo de teléfono*</strong><br>
	<%
	List<RegistroContactoVO> lstTipoTelefono = (List<RegistroContactoVO>) request.getSession().getAttribute("CAT_TIPO_TELEFONO");					
	Iterator itLstTipoTelefono = lstTipoTelefono.iterator();
	while(itLstTipoTelefono.hasNext()){
		CatalogoOpcionVO vo = (CatalogoOpcionVO) itLstTipoTelefono.next();
		%>
	<%=vo.getOpcion()%>&nbsp;
	<input type="radio" id="idTipoTelefono" name="idTipoTelefono" 
	value="<%=String.valueOf(vo.getIdCatalogoOpcion())%>" onClick="fillUpAccessKey();">&nbsp;&nbsp;
	<%					
	}							
	%>			          
	</span> 			 
	<span class="un_quinto"><strong>Acceso*</strong><br>
	 <input id="acceso" name="acceso" maxlength="3" size="3"
		dojoType="dijit.form.ValidationTextBox" required="true" regExp="^(<%=CLAVE_TELEFONO_CELULAR%>|<%=CLAVE_TELEFONO_FIJO%>)$"
		invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" />
	 <label for="acceso"></label>      			        
	</span> 			 
	<span class="un_quinto"><strong>Clave Lada*</strong><br>  
	<input id="clave" name="clave" maxlength="3" size="3"
		dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{2,3}$"
		invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" />	        			        
	</span> 			 
	<span class="un_quinto"><strong>Teléfono*</strong><br>
	<input id="telefono" name="telefono" maxlength="8" size="8" onBlur="changePhoneSizeRequired()"
		dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{7,8}$"			 	
		invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" />
	<label for="telefono"></label>        			          
	</span> 			 
	<span class="un_quinto"><strong>Extensión</strong><br>
	<input id="extension" name="extension" maxlength="8" size="8"
		dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,6}$"
		invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true" />
	<label for="extension"></label>        			           
	<a href="#" class="links" onclick="openPhoneWindow()">Agregar teléfono</a>
	</span>
	</DIV>  
	<!-- TERMINA TELEFONO -->    

				 
	<DIV class="entero" id="divRegis" style="text-align: center;">         			    				   
	<span class="un_medio">                                        
	<input type="button" id="btnGuardar" value="Guardar" class="boton" onclick="doSubmitAjax('salvar');" />
	</span>
	<span class="un_medio">
	<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="doSubmit('init');" />
	</span>
	</DIV>

    <!-- cierra div principal -->
    <div id="message"></div>         
</form>

  <script type="text/javascript">
        dojo.addOnLoad(function() {

        dojo.connect(dijit.byId("btnValidar"), "onClick", function() {

 			if(dijit.byId('codigoPostal').value == ''){
 			   alert("El código postal es necesario.");
 			   return;
			}

            if(dijit.byId('Ctrl').value == 1){

				var vCodigoPostal = dijit.byId('codigoPostal').value;
	
            	entidadFederativaStore.url = "${context}regCont.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	entidadFederativaStore.close();

            	municipioStore.url = "${context}regCont.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	municipioStore.close();

            	coloniaStore.url = "${context}regCont.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            	coloniaStore.close();

				dijit.byId('entidadFederativaSelect').disabled=false;
				dijit.byId('municipioSelect').disabled=false;
				dijit.byId('coloniaSelect').disabled=false;
			}
			
        });


        dojo.connect(dijit.byId("entidadFederativaSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

				var vEntidad = dijit.byId('entidadFederativaSelect').get('value');
					
            	dijit.byId('municipioSelect').disabled=false;
            		
            	municipioStore.url = "${context}regCont.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
            	municipioStore.close();
			}
        });


        dojo.connect(dijit.byId("municipioSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

				var vEntidad   = dijit.byId('entidadFederativaSelect').get('value');
				var vMunicipio = dijit.byId('municipioSelect').get('value');
					
            	dijit.byId('coloniaSelect').disabled=false;
            		
            	coloniaStore.url = "${context}regCont.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
            	coloniaStore.close();
			}
        });


        dojo.connect(dijit.byId("coloniaSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

            	dijit.byId('codigoPostal').disabled=false;

				dojo.byId('idEntidad').value   = dijit.byId('entidadFederativaSelect').get('value');
				dojo.byId('idMunicipio').value = dijit.byId('municipioSelect').get('value');
				dojo.byId('idColonia').value   = dijit.byId('coloniaSelect').get('value');
                
				dojo.byId('method').value="obtenerCodigoPostal";

	
				dojo.xhrPost(
				{
				  url: 'regCont.do',
				  form:'regContForm',
				  timeout:180000, 
				  load: function(data)
				  {
					var res = dojo.fromJson(data);
					dojo.byId('codigoPostal').value = res.codigoPostal;
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
					
					
					dojo.byId('codigoPostal').value="";
					dijit.byId('codigoPostal').disabled=true;
					                        		
            		dijit.byId('entidadFederativaSelect').disabled=false;
            		
            		entidadFederativaStore.url = "${context}regCont.do?method=obtenerEntidad";
            		entidadFederativaStore.close();            		            		                                
                }
                
                if(b == false){

					dijit.byId('Ctrl').value = 1;

					dojo.byId('codigoPostal').value="";
					
					dijit.byId('codigoPostal').disabled=false;                        		
            		dijit.byId('entidadFederativaSelect').disabled=true;
            		dijit.byId('municipioSelect').disabled=true;
            		dijit.byId('coloniaSelect').disabled=true;            		
                }
            }
        },
        "checkCtrl");

        
        var filteringSelect = new dijit.form.FilteringSelect({
            id: "entidadFederativaSelect",
            name: "entidadFederativaSelect",
            value: "",
            store: entidadFederativaStore,
            searchAttr: "value"
        },
        "entidadFederativaSelect");

        var filteringSelect = new dijit.form.FilteringSelect({
            id: "municipioSelect",
            name: "municipioSelect",
            value: "",
            store: municipioStore,
            searchAttr: "value"
        },
        "municipioSelect");

        var filteringSelect = new dijit.form.FilteringSelect({
            id: "coloniaSelect",
            name: "coloniaSelect",
            value: "",
            store: coloniaStore,
            searchAttr: "value"
        },
        "coloniaSelect");

        });                       
  </script>
  <script type="text/javascript">
  changePersonTypeRequired();
  </script>






