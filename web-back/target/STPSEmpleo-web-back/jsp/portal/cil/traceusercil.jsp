<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Calendar, java.text.SimpleDateFormat"%>
<% 
	Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy HH:mm");
    String today = sdf.format(cal.getTime());
%>
<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");    
    dojo.require("dijit.form.DateTextBox");
    dojo.require("dijit.Dialog");
    dojo.require("dijit.form.FilteringSelect");
    dojo.require("dijit.form.Textarea")
</script>
<script>
	function cancelConfirmation() {
		var answer = confirm("Los datos no guardados se perderán ¿Continuar?");
		if (answer) {
			doSubmit('cancel');
		}
	}
	
	function doSubmitAjax() {
		if (validateData()) {
			var answer = confirm("¿Está seguro de guardar los datos?");
			if (answer) {
				document.ServicesForm.method.value='saveTrace';
				document.ServicesForm.submit();
			}
		}
	}
	
	function validateData() {
		if(getRadioValue('colocado')==2 && !dijit.byId('fechaColocacion').isValid()){
			msgalert("Favor de proporcionar la fecha de colocación.");								
			return false;
		}
		if(getRadioValue('colocado')==1 && dijit.byId('idCausa').get('item').value==0){
			msgalert("Favor de proporcionar la causa de no colocación.");								
			return false;
		}
		return true;
	}
	
	function msgalert(msg){
		dojo.byId('msg').innerHTML = msg;
		dijit.byId('msgAlert').show();		
	}
	
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
</script>
<script language="JavaScript">
     function toggle() {
     	var employed = document.getElementById('employed').style.display;
        if (employed == 'block') {
      		document.getElementById('employed').style.display = 'none';
        } else {
        	document.getElementById('employed').style.display = 'block';
        	document.getElementById('otherCause').style.display = 'none';
        }
        var unemployed = document.getElementById('unemployed').style.display;
        if (unemployed == 'block') {
      		document.getElementById('unemployed').style.display = 'none';
        } else {
        	document.getElementById('unemployed').style.display = 'block';
        	togglesel();
        }
     }
     
     function togglesel() {
     	var idCausa = dijit.byId('idCausa').get('item').value;
     	if (idCausa == 3) {
			document.getElementById('otherCause').style.display = 'block';
        } else {
        	document.getElementById('otherCause').style.display = 'none';
        }
     }
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div class="col-md-6 col-sm-push-3">
<h2>Seguimiento de colocación</h2>
<form name="ServicesForm" id="ServicesForm" method="post" action="seguimientocil.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="saveTrace"/>
	<input type="hidden" name="idAtencion" id="idAtencion" value="${TraceUserForm.attention.idAtencion}"/>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<div class="col-md-6">
			<strong>${TraceUserForm.userName} ${TraceUserForm.userID}</strong>              			           			    					
	</div>
	<div class="col-md-6">${TraceUserForm.msg}</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<c:if test="${empty TraceUserForm.msg}">
	<div class="col-md-6">
			<strong>Fecha de servicio:</strong>               			           			    					
	</div>
	<div class="col-md-6">${TraceUserForm.servicesDate} </div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<div class="col-md-12">
			<strong>Servicios proporcionados:</strong>               			           			    					
	</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<div class="col-md-6">
			<strong>Acceso o captura de curriculum: </strong>               			           			    					
	</div>
	<div class="col-md-6">${TraceUserForm.attention.actualizarCV.contador}</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<div class="col-md-6">
			<strong>Impresión: </strong>               			           			    					
	</div>
	<div class="col-md-6">${TraceUserForm.attention.impresion.contador}</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<div class="col-md-6">
			<strong>Llamada telefónica: </strong>               			           			    					
	</div>
	<div class="col-md-6">${TraceUserForm.attention.llamadas.contador}</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<div class="col-md-6">
			<strong>Fotocopiado: </strong>               			           			    					
	</div>
	<div class="col-md-6">${TraceUserForm.attention.fotocopias.contador}</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<div class="col-md-6">
			<strong>Acceso a portal del empleo: </strong>               			           			    					
	</div>
	<div class="col-md-6">${TraceUserForm.attention.actividadPortal.detalle}</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<div class="col-md-6">
			<strong>Consulta a otras bolsas de trabajo: </strong>               			           			    					
	</div>
	<div class="col-md-6">${TraceUserForm.attention.otrasBolsas.detalle}</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
	<div class="entero">
		<span class="entero">
			<table cellspacing="0" cellpadding="0" border="0" width="70%">
				<tbody>
					<c:if test="${not empty calList}">
						<tr class="temas">
							<th>Telefono</th>
							<th>Llamadas</th>
						</tr>
						<c:forEach var="telefono" items="${calList}" varStatus="rowCounter">
							<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
								<td>${telefono.detalle}</td>
								<td>${telefono.contador}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</span>
	</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
		<span class="un_medio">
			<strong><%=today%></strong>               			           			    					
		</span>	
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 20px;">
		<div class="col-md-6">
			<strong>Colocado</strong></div>
		<div class="col-md-6">
			<input type="radio" name="colocado" id="colocado2" value="2" checked onclick="javascript:toggle();"/>&nbsp;<label for="colocado2">Si</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="colocado" id="colocado2" value="1" onclick="javascript:toggle();"/>&nbsp;<label for="colocado2">No</label>
			</div>              			           			    					
	</div>
	<div id="employed" class="entero" style="display:block;">
		<span class="un_medio">
			<strong><label for="fechaColocacion">Fecha de colocación</label></strong>
			<input type="text" name="fechaColocacion" id="fechaColocacion" value="" dojoType="dijit.form.DateTextBox" required="true" />
			<br/><br/><br/>             			           			    					
		</span>	
	</div>
	<div id="unemployed" class="entero" style="display:none;">
		<span class="un_medio">
			<strong><label for="idCausa">Causa de no colocación</label></strong>
			<select id="idCausa" name="idCausa" dojoType="dijit.form.FilteringSelect" style="width:200px;" onchange="javascript:togglesel();">
				<option value="0">Seleccione</option>
				<option value="1">No cumple perfil</option>
				<option value="2">No cumple expectativas</option>
				<option value="3">Otra causa</option>
			</select>               			           			    					
		</span>
	</div>
	<div id="otherCause" class="entero" style="display:none;">
		<span class="un_medio">
			<strong>Otra causa de no colocación</strong>
			<textarea id="otraCausa" name="otraCausa" dojoType="dijit.form.Textarea" style="width:200px;"></textarea>               			           			    					
		</span>	
	</div>
		<div class="un_medio" id="divRegis" style="text-align: center;">         			    				   
			<span class="un_cuarto">                                        
				<input type="button" id="btnGuardar" value="Guardar" class="boton" onclick="javascript:doSubmitAjax();" />
			</span>
			<span class="un_cuarto">
				<input type="button" id="btnCancel" value="Cancelar" class="boton" onclick="cancelConfirmation();" />
			</span>
		</div>
	</c:if>
	<div class="un_tercio" dojoType="dijit.Dialog" id="msgAlert" title="msg" style="display:none" draggable ="false" >
		  <table class="alertDialog" >
			 <tr align="center">				 	
				 <td><div id ="msg" style="width:250px;height:50px;overflow:auto;vertical-align:middle;"></div>&nbsp;</td>			 
			 </tr>
		 </table>	
	</div>
</form>
</div>