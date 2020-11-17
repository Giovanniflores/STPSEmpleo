<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.List, java.util.Iterator, java.util.ArrayList, mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO"%>
<%
	String context = request.getContextPath() +"/";
	String vProxy = context + "SpellCheck.do?method=gogiespell&lang=";
%>

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>

<script type="text/javascript">
    dojo.require("dijit.dijit"); // loads the optimized dijit layer
    dojo.require('dijit.Dialog'); 
    dojo.require("dijit.form.Form");    
    dojo.require("dijit.form.Textarea");
    dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require('dijit.Dialog');
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
	function doDeleteSkill(idSkill) {
		if (confirm('Esta usted seguro de eliminar habilidad')) {
			deleteSkill(idSkill);
		}	
	}
	function deleteSkill(idSkill) {
		dojo.xhrGet({url: 'conocsHabs.do?method=deleteSkill&idSkill='+idSkill, form:'escolaridadForm', sync: true, timeout:180000, 
					  load: function(data) {
						dojo.byId('habilidades bloque').innerHTML=data;
						
						estableceCorrectoresSkill();
					  }});
	}
	function isValidSkill() {
		var knowledge = dojo.byId('conocimientoAddSkill');
    	if (!knowledge.value) {
			alert('Es necesario indicar la habilidad.');
			knowledge.focus();
			return false;
		}
    	
    	var idExperienciaSelectAddSkill = document.getElementById('idExperienciaSelectAddSkill');
		if (!idExperienciaSelectAddSkill || idExperienciaSelectAddSkill.value == 0) {
    		alert('Debe seleccionar experiencia de la habilidad.');
			return false;
    	}
		var skill = document.getElementById('descripcionSkillAdd');
		if (!skill.value) {
			alert('Es necesario indicar la descripción de la habilidad.');
			skill.focus();
			return false;
		}
    	return true;
    }
    function toggleSkill() {
		document.getElementById('skillsadd').style.display = 'block';
		document.getElementById('btnAgregarHabilidad').style.display = 'none';		
    }
	function doAddSkill() {
    	if(isValidSkill()) {
	    	var idExperienciaSelectAddSkill = document.getElementById('idExperienciaSelectAddSkill').value;
	    	var descripcionSkillAdd = document.getElementById('descripcionSkillAdd').value;
	    	var conocimientoAddSkill = dojo.byId('conocimientoAddSkill').value;
	    	var post = encodeURIComponent(descripcionSkillAdd)+'&conocimientoAddSkill='+encodeURIComponent(conocimientoAddSkill);
	    	dojo.xhrGet( {
				url: 'conocsHabs.do?method=saveAddSkill&idExperienciaSelectAddSkill='+idExperienciaSelectAddSkill+'&descripcionSkillAdd='+post,
			  	form:'escolaridadForm',
			  	timeout:180000,
			  	load: function(data){
					document.escolaridadForm.idExperienciaSelectAddSkill.value='';
					document.escolaridadForm.descripcionSkillAdd.value='';
					document.escolaridadForm.conocimientoAddSkill.value='';
					dojo.byId('habilidades bloque').innerHTML=data;
			    
					estableceCorrectoresSkill();
			  	}
			 });
		 }
    }
	
	function estableceCorrectorSkill(control){
		var vSpellCon = new GoogieSpell("googiespell/", '<%=vProxy%>');
 		vSpellCon.setLanguages({'es': 'Español'});
		vSpellCon.hideLangWindow();
		vSpellCon.decorateTextarea(control);
	}
	
	function estableceCorrectoresSkill(){
		if (dojo.byId('descripcionSkillAdd'))
			estableceCorrectorSkill('descripcionSkillAdd');
		
		if (dojo.byId('descripcionSkillAdd1'))
			estableceCorrectorSkill('descripcionSkillAdd1');
		
		if (dojo.byId('descripcionSkillAdd2'))
			estableceCorrectorSkill('descripcionSkillAdd2');
	}
	
	dojo.addOnLoad(function() {
		estableceCorrectoresSkill();
	});

  	function maximaLongitud(texto,maxlong)
  	{
  	var tecla, int_value, out_value;

  	if (texto.value.length > maxlong)
  	{
  	/*con estas 3 sentencias se consigue que el texto se reduzca
  	al tamaño maximo permitido, sustituyendo lo que se haya
  	introducido, por los primeros caracteres hasta dicho limite*/
  	in_value = texto.value.replace(/^\s+|\s*[\r\n][\r\n \t]*/g, "");
  	
  	out_value = in_value.substring(0,maxlong);
  	texto.value = out_value;
  	
  	return false;
  	}
  	return true;
  	}	
	
</script>
	<div id="skillsadd" class="habilidades bloque" style="background-color:#E0ECF8; display:none">
        	<h3><span>Agregar otra habilidad</span></h3>
            <p class="un_medio fl"><strong><label for="conocimientoAddSkill">Habilidad</label></strong><br />
                <input class="sugerido" type="text" name="conocimientoAddSkill" id="conocimientoAddSkill" value="" style="width:25em;" maxlength="50" regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,50}$" 
	        		dojoType="dijit.form.ValidationTextBox" required="true"/>
            </p>
            <p class="un_medio fl"><strong><label for="idExperienciaSelectAddSkill">Experiencia</label></strong><br />
            	<div dojoType="dojo.data.ItemFileReadStore" jsId="experienceKnowStoreAddSkill" urlPreventCache="true" clearOnClose="true" ></div>
	        	<select class="sugerido" id="idExperienciaSelectAddSkill" required="true" autocomplete="true">
	        		<option value="0"></option>
                    <option value="2" >6m - 1 año</option>
                    <option value="3" >1 - 2 años</option>
                    <option value="4" >2 - 3 años</option>
                    <option value="5" >3 - 4 años</option>
                    <option value="6" >4 - 5 años</option>
                    <option value="7">Más de 5 años</option>
	        	</select>	 	        	
            </p>
            <br clear="all" /> 
            <div class="campoTexto">
                <p><label for="descripcionSkillAdd" class="un_tercio fl"><strong>Descripci&oacute;n</strong></label><span class="bt_ortografia un_tercio fl"></span></p>
                <p class="tres_cuartos fl">
                	<textarea class="sugerido" rows="4" cols="70" style="width: 90%;" name="descripcionSkillAdd" id="descripcionSkillAdd" 
	        			onkeypress="return caracteresValidos(event);"
	        			trim="true" onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)"
	        			maxlength="2000" style="width:550px;min-height:150px;_height:200px;" 
	        			required="false" 
	        			regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$"></textarea>
                </p>
            	<p class="fl" style="margin: 40px 0 0;"><input type="button" class="enviar" onclick="javascript:doAddSkill();" title="Guardar habilidad" value="Enviar" /></p>
            	<br clear="all" />
            </div>
    	</div>
<%
	int index = 1;
	String idDominioSelectAddSkill = "idDominioSelectAddSkill";
	String idExperienciaSelectAddSkill = "idExperienciaSelectAddSkill";
	String descripcionSkillAdd = "descripcionSkillAdd";
	String conocimientoAddSkill = "conocimientoAddSkill";
	String idCandidatoSkillAdd = "idCandidatoSkillAdd";
	List<ConocimientoHabilidadVO> skills = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("SKILLS_LIST");
	if (null == skills) skills = new ArrayList<ConocimientoHabilidadVO>();
	Iterator<ConocimientoHabilidadVO> itKnows = skills.iterator();
	while (itKnows.hasNext()) {
		ConocimientoHabilidadVO skill = itKnows.next();
		if (skill.getPrincipal() == 2) {
%> 
		<div class="habilidades bloque">
			<input type="hidden" name="<% out.print(idCandidatoSkillAdd + index); %>" id="<% out.print(idCandidatoSkillAdd + index); %>" value="<%=skill.getIdCandidatoConocimHabilidad() %>" />
            <p class="un_medio fl"><strong><label for="<% out.print(conocimientoAddSkill + index); %>">Habilidad</label></strong><br />
                <input class="sugerido" type="text" name="<% out.print(conocimientoAddSkill + index); %>" id="<% out.print(conocimientoAddSkill + index); %>" value="<%=skill.getConocimientoHabilidad() %>" 
	        		style="width:25em;" maxlength="50" regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&ntilde;&Ntilde;]{1,50}$" dojoType="dijit.form.ValidationTextBox" required="true"/>
            </p>
            <p class="un_medio fl"><strong><label for="<% out.print(idExperienciaSelectAddSkill + index); %>">Experiencia</label></strong><br />
	        	<select class="sugerido" id="<% out.print(idExperienciaSelectAddSkill + index); %>" name="<% out.print(idExperienciaSelectAddSkill + index); %>" required="true" autocomplete="true">
           			<option value="0"></option>
                    <option value="2" <% if (skill.getIdExperiencia() == 2) out.print(" selected "); %>>6m - 1 año</option>
                    <option value="3" <% if (skill.getIdExperiencia() == 3) out.print(" selected "); %>>1 - 2 años</option>
                    <option value="4" <% if (skill.getIdExperiencia() == 4) out.print(" selected "); %>>2 - 3 años</option>
                    <option value="5" <% if (skill.getIdExperiencia() == 5) out.print(" selected "); %>>3 - 4 años</option>
                    <option value="6" <% if (skill.getIdExperiencia() == 6) out.print(" selected "); %>>4 - 5 años</option>
                    <option value="7" <% if (skill.getIdExperiencia() == 7) out.print(" selected "); %>>Más de 5 años</option>
	        	</select>
            </p>
            <br clear="all" /> 
            <div class="campoTexto">
                <p><label for="<% out.print(descripcionSkillAdd + index); %>" class="un_tercio fl"><strong>Descripci&oacute;n</strong></label><span class="bt_ortografia un_tercio fl"></span></p>
                
                <p class="tres_cuartos fl">
                	<textarea class="sugerido" rows="4" cols="70" style="width: 90%;" name="<% out.print(descripcionSkillAdd + index); %>" id="<% out.print(descripcionSkillAdd + index); %>" 
	        			onkeypress="return caracteresValidos(event);"
	        			trim="true" onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)"
	        			maxlength="2000" style="width:550px;min-height:150px;_height:200px;" 
	        			required="false" 
	        			regExp="^[\w\d\s\.&aacute;&eacute;&iacute;&oacute;&uacute;&Aacute;&Eacute;&Iacute;&Oacute;&Uacute;&Ntilde;&ntilde;]{1,2000}$"><%=skill.getDescripcion() %></textarea>
                </p>
            	
            	<p class="fl" style="margin: 40px 0 0;">
            		<input type="button" class="eliminar" onclick="javascript:doDeleteSkill(<%=skill.getIdCandidatoConocimHabilidad() %>);"
            		       title="Eliminar habilidad" value="Eliminar" />
            	</p>
            	<br clear="all" />
            </div>
    	</div>
 <% 
 			index++;
 		}		
	}
	if (skills.size() < 3) {
%>
	    <p class="add_ph">
	    	<input type="button" id="btnAgregarHabilidad" name="btnAgregarHabilidad" class="agregar"
	    		   onclick="javascript:toggleSkill();" title="Agregar habilidad" value="Agregar" />
	    </p>
<%  
	}else {
%>
		<div class="entero">
	    						
		</div>
<%  
	}
%> 
<script>
	dojo.addOnLoad(function() {
		experienceKnowStoreAddSkill.url = "<%=context%>conocsHabs.do?method=cargarExperiencia";
    	experienceKnowStoreAddSkill.close();
    	experienceKnowStoreAddSkill.fetch({
          	onComplete: function(items, request) {                  	
          		if (items.length == 0) return;                    	
          		dijit.byId('idExperienciaSelectAddSkill').attr('value', items[0].value);
          	}
    	})
    });
</script>