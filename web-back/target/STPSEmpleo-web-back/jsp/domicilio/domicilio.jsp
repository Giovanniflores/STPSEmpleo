<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
  String context = request.getContextPath() +"/";
%>


<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
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
</script>

<script type="text/javascript">
	
function doSubmit(method){
	document.domicilioForm.method.value=method;
	document.domicilioForm.submit();		
}



function cambiarMayusculas(element) {
	var myElementValue = document.getElementById(element.id).value;
	document.getElementById(element.id).value = myElementValue.toUpperCase();
}

              
</script>

<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_registro_Empresa.css" rel="stylesheet" type="text/css" />





<form name="domicilioForm" id="domicilioForm" method="post" action="domicilio.do" dojoType="dijit.form.Form">

	<input type="hidden" name="method" id="method" value="init"/>
	<input type="hidden" name="cpnuevo" id="cpnuevo" value="0"/>
	<input type="hidden" name="Ctrl" id="Ctrl" dojoType="dijit.form.TextBox" value="1"/>
	
	<div id="registro_empresa">
				
			<!-- DOMICILIO -->
		<h3>Domicilio</h3>	

        <p class="entero">
        <span class="un_medio"><strong><label for="codigoPostal">C�digo postal</label>*</strong></span>
        <span class="un_medio">
        <input id="codigoPostal" name="codigoPostal" maxlength="6" size="6" value="${domicilioForm.codigoPostal}"
        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[0-9]{1,6}" 
        	invalidMessage="Dato inv�lido" trim="true" />        
        <label for="codigoPostal"></label>	
        </span>

        <span class="un_medio">
		<input type="checkBox" id="checkCtrl">
		<label for="checkCtrl">No conozco mi c�digo postal</label>        
        </span>


		<span class="un_medio" style="text-align:center; margin:40px 0; ">
			<div id="btnValidar" dojoType="dijit.form.Button">Validar CP</div>
		</span>
		</p>		



        <p class="entero">
        <span class="un_medio"><strong><label for="pais">Pa�s</label></strong></span>
        <span class="un_medio">
        <input name="pais" id="pais" maxlength="10" size="6" value="M�xico" disabled />
        <label for="pais"></label>	
        </span>
        </p>

        <p class="entero">
		<strong><label for="entidadFederativaSelect">Entidad federativa</label>*</strong>
				<input type="hidden" name="idEntidad" id="idEntidad" value="" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore" id="entidadFederativaSelect" required="true" disabled="true" autoComplete="false"></select>
        </p>

        <p class="entero">
        <strong><label for="municipioSelect">Municipio</label>*</strong>
				<input type="hidden" name="idMunicipio" id="idMunicipio" value="" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="municipioStore" id="municipioSelect" required="true" disabled="true" autoComplete="false"></select>
        </p>

        <p class="entero">
        <strong><label for="coloniaSelect">Colonia</label>*</strong>
				<input type="hidden" name="idColonia" id="idColonia" value="" />
				<div dojoType="dojo.data.ItemFileReadStore" jsId="coloniaStore" urlPreventCache="true" clearOnClose="true" ></div>
				<select dojotype="dijit.form.FilteringSelect" store="coloniaStore" id="coloniaSelect" required="true" disabled="true" autoComplete="false"></select>
        </p>

        <p class="entero">
        <span class="un_medio"><strong><label for="calle">Calle</label>*</strong></span>
        <span class="un_medio">
        <input id="calle" name="calle" maxlength="60" size="60" value="${domicilioForm.calle}" onBlur="cambiarMayusculas(this)"
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[\D\s\.������������]{1,150}$"
        	invalidMessage="Dato inv�lido" missingMessage="Dato requerido" trim="true"/>
        </span>
        <label for="calle"></label>
        </p>

        <p class="entero">
        <span class="un_medio"><strong><label for="numeroExterior">N�mero exterior</label>*</strong></span>
        <span class="un_medio">
        <input id="numeroExterior" name="numeroExterior" maxlength="6" size="4" value="${domicilioForm.numeroExterior}"
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,4}" 
        	invalidMessage="Dato inv�lido" trim="true" />        
        <label for="numeroExterior"></label>	
        </span>
        </p>

        <p class="entero">
        <span class="un_medio"><strong><label for="numeroInterior">N�mero interior</label></strong></span>
        <span class="un_medio">
        <input id="numeroInterior" name="numeroInterior" maxlength="6" size="4" value="${domicilioForm.numeroInterior}"
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[0-9]{1,4}" 
        	invalidMessage="Dato inv�lido" trim="true" />        
        <label for="numeroInterior"></label>	
        </span>
        </p>

        <p class="entero">
        <span class="un_medio"><strong><label for="entreCalle">Entre calles</label>*</strong></span>
        <span class="un_medio">
        <input id="entreCalle" name="entreCalle" maxlength="60" size="60" value="${domicilioForm.entreCalle}" onBlur="cambiarMayusculas(this)"
        	dojoType="dijit.form.ValidationTextBox" required="true" regExp="^[\D\s\.������������]{1,150}$"
        	invalidMessage="Dato inv�lido" missingMessage="Dato requerido" trim="true"/>
        </span>
        <label for="entreCalle"></label>
        </p>

        <p class="entero">
        <span class="un_medio"><strong><label for="yCalle">Y</label>*</strong></span>
        <span class="un_medio">
        <input id="yCalle" name="yCalle" maxlength="60" size="60" value="${domicilioForm.yCalle}" onBlur="cambiarMayusculas(this)"
        	dojoType="dijit.form.ValidationTextBox" required="false" regExp="^[\D\s\.������������]{1,150}$"
        	invalidMessage="Dato inv�lido" trim="true"/>
        <label for="yCalle"></label>
        </span>
        </p>

	<div id="message"></div>            
</form>

  <script type="text/javascript">

        dojo.addOnLoad(function() {



		// ***** Inicia Validar Codigo Postal ***** //
        dojo.connect(dijit.byId("btnValidar"), "onClick", function() {

 			if(dijit.byId('codigoPostal').value == ''){
 			   alert("El c�digo postal es necesario.");
 			   return;
			}

            if(dijit.byId('Ctrl').value == 1){

				var vCodigoPostal = dijit.byId('codigoPostal').value;
				
				dojo.byId('method').value="obtenerEntidadJSON";
				// Solicita Validar CP
				dojo.byId('cpnuevo').value = 1;

				dojo.xhrPost( {
					url: 'domicilio.do',
		  			form:'domicilioForm',
		  			timeout:180000,
		  			load: function(data) {
					
						var res = dojo.fromJson(data);
					
						if (res.type == 'err') {
							
							alert(res.message);
							dijit.byId('entidadFederativaSelect').attr('value', "");
							dijit.byId('municipioSelect').attr('value', "");
							dijit.byId('coloniaSelect').attr('value', "");
							
							dijit.byId('entidadFederativaSelect').disabled = true;
							dijit.byId('municipioSelect').disabled = true;
							dijit.byId('coloniaSelect').disabled = true;
							
						} else {

            				entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidadJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            				entidadFederativaStore.close();

							/* ********** */
            				entidadFederativaStore.fetch({
            					onComplete: function(items, request) {                  	
                  				if (items.length == 0) return;                    	
                  				dijit.byId('entidadFederativaSelect').attr('value', items[0].value);
                  			}});
            				/* ---------- */

            				municipioStore.url = "${context}domicilio.do?method=obtenerMunicipioJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            				municipioStore.close();

							/* ********** */
            				municipioStore.fetch({
                  				onComplete: function(items, request) {                  	
                  				if (items.length == 0) return;                    	
                  				dijit.byId('municipioSelect').attr('value', items[0].value);
                  			}});
            				/* ---------- */

            				coloniaStore.url = "${context}domicilio.do?method=obtenerColoniaJSON" + "&" + "codigoPostal="+ vCodigoPostal;
            				coloniaStore.close();
            				
            				/* ********** */
            				coloniaStore.fetch({
                  				onComplete: function(items, request) {                  	
                  				if (items.length == 0) return;                    	
                  				dijit.byId('coloniaSelect').attr('value', items[0].value);
                  			}});
            				/* ---------- */
            				
							dijit.byId('entidadFederativaSelect').disabled=false;
							dijit.byId('municipioSelect').disabled=false;
							dijit.byId('coloniaSelect').disabled=false;

						}
		    		}
				});


			}
			
        });
        // ***** Termina Validar Codigo Postal ***** //

		

        dojo.connect(dijit.byId("entidadFederativaSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

				var vEntidad = dijit.byId('entidadFederativaSelect').get('value');
					
            	dijit.byId('municipioSelect').disabled=false;
            		
            	municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
            	municipioStore.close();
			}
        });


        dojo.connect(dijit.byId("municipioSelect"), "onChange", function() {

            if(dijit.byId('Ctrl').value == 0){

				var vEntidad   = dijit.byId('entidadFederativaSelect').get('value');
				var vMunicipio = dijit.byId('municipioSelect').get('value');
					
            	dijit.byId('coloniaSelect').disabled=false;
            		
            	coloniaStore.url = "${context}domicilio.do?method=obtenerColonia" + "&" + "idEntidad="+ vEntidad + "&" + "idMunicipio="+ vMunicipio;
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
				  url: 'domicilio.do',
				  form:'domicilioForm',
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
					dojo.byId('cpnuevo').value = 0;
					dijit.byId('codigoPostal').disabled=true;
					                        		
            		dijit.byId('entidadFederativaSelect').disabled=false;
            		
            		entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
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


        });        
  </script>
 