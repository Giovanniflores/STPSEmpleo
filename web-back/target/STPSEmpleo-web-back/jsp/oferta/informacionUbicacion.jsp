<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.controls {
	margin-top: 16px;
	border: 1px solid transparent;
	border-radius: 2px 0 0 2px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	height: 32px;
	outline: none;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#pac-input {
	background-color: #fff;
	padding: 7px 11px 5px 13px;
	width: 400px;
	font-family: Roboto;
	font-size: 15px;
	font-weight: 300;
	text-overflow: ellipsis;
	border: 1px solid #dddede;
	top: 10px !important;
}

#pac-input:focus {
	border-color: #4d90fe;
	margin-left: -1px;
	padding-left: 14px; /* Regular padding-left + 1. */
	width: 401px;
}

.pac-container {
	font-family: Roboto;
}

#type-selector {
	color: #fff;
	background-color: #4d90fe;
	padding: 5px 11px 0px 11px;
}

#type-selector label {
	font-family: Roboto;
	font-size: 13px;
	font-weight: 300;
}

#target {
	width: 345px;
}
#menuAdmin {
    color: #333333;
    font-family: Tahoma,Geneva,sans-serif;
    font-size: 11px;
    margin: auto;
    width: 930px;
}
#menuAdmin ul {
    list-style: outside none none;
    margin-left: 2%;
    margin-right: 2%;
    margin-top: 24px;
}
#menuAdmin ul li {
    float: left;
    height: 36px;
    margin-bottom: 10px;
    margin-left: 5px;
}
#menuAdmin ul li a.adminCerrar {
    background: #fe6a08;
    color: #ffffff;
}
#menuAdmin ul li a {
    border: 1px solid #dbdbdb;
    color: #333333;
    padding: 6px;
    text-align: center;
    text-decoration: none;
    width: auto;
}
.suggestionsBox {
    background-color: transparent;
    border: none !important;
}
#listaOcupaciones li b,
.empresas .miEspacio #registroInformacionUbicacion .datosGenerales #ocupacionesListDiv #listaOcupaciones li b {
	color: #6e8d3a !important;
    background-color: #fff !important;
    font-weight: regular !important;
}
.suggestionList li {
	cursor: pointer;
	padding: 3px;
	background-color: #6e8d3a !important;
    color: #fff !important;
    width: auto;
    margin: 0;
}
.suggestionList li:hover {
	background-color: #659CD8;
}
.datosGenerales #typeProfileMsgDiv.suggestionsBoxMsg b {
	display: block;
	padding: 10px 0;
	font-weight: bold;
	text-style: italic;
}
.datosGenerales #typeProfileListDiv.suggestionsBox #typeProfileMsgs ul#typeProfileList {
	border: 1px solid #9a9a9a !important;
	margin-right: 20px;
	-webkit-box-shadow: 0 1px 5px rgba(0,0,0,0.75);
	-moz-box-shadow: 0 1px 5px rgba(0,0,0,0.75);
	box-shadow: 0 1px 5px rgba(0,0,0,0.75);
}
.datosGenerales #typeProfileListDiv.suggestionsBox #typeProfileMsgs #typeProfileList li {
	background-color: #ffffff !important;
	border-bottom: 1px solid #cccccc;
	padding: 0 !important;
}
.datosGenerales #typeProfileListDiv.suggestionsBox #typeProfileMsgs #typeProfileList li span {
	display: inline-block;
	height: 100%;
	color: #000000 !important;
}
.datosGenerales #typeProfileListDiv.suggestionsBox #typeProfileMsgs #typeProfileList li:first-child {
	background: #666666 !important;
	font-weight: bold;
}
.datosGenerales #typeProfileListDiv.suggestionsBox #typeProfileMsgs #typeProfileList li:first-child span {
	color: #ffffff !important;
}
.datosGenerales #typeProfileListDiv.suggestionsBox #typeProfileMsgs #typeProfileList li:first-child span:first-child {
	background: #666666 !important;
}
.datosGenerales #typeProfileListDiv.suggestionsBox #typeProfileMsgs #typeProfileList li span:first-child {
	background-color: #ffffff !important;
	width: 90%;
	padding: 3px;
}
.datosGenerales #typeProfileListDiv.suggestionsBox #typeProfileMsgs #typeProfileList li span:nth-child(2) {
	text-align: center;
	padding-top: 5px;
}
</style>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>

<%
	String context = request.getContextPath() + "/";
	String vProxy = "http://orangoo.com/newnox?lang=";
	//  String vProxy = context + "SpellCheck.do?method=gogiespell&lang=";
	pageContext.setAttribute("vProxy", vProxy);
%>

<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>

<link href="googiespell/googiespell.css" rel="stylesheet"
	type="text/css" />


<c:set var="regexptitulo">^[\w\D]{1,150}$</c:set>
<c:set var="regexpplazas">^[0-9]{1,3}$</c:set>
<c:set var="regexpedad">^[0-9]{1,3}$</c:set>
<c:set var="regexpsalario">^[+]?\d{1,6}(\.\d{1,2})?$</c:set>

<script>
	
		function initialize() {
	
			var markers = [];
			  
			var mapOptions = {
			            zoom: 15,
			            mapTypeId: google.maps.MapTypeId.ROADMAP,
			            draggable: false,
			            streetViewControl: false,
			            mapTypeControl: false
			        };
			  
			var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
			  			  
			var defaultBounds = null;	
			  
			var vCoordenadas = '${ofertaEmpleoForm.urlUbicacion}';
			  
			if(vCoordenadas!=null && vCoordenadas!=''){
				
				var arregloCoordenadas = vCoordenadas.split(",");
				
				if(arregloCoordenadas.length==2){
					
					defaultBounds = new google.maps.LatLngBounds( new google.maps.LatLng(arregloCoordenadas[0], arregloCoordenadas[1]));
						
					var vLatitude = arregloCoordenadas[0];
					var vLongitude = arregloCoordenadas[1];  			      
					var vMapUrl = vLatitude + "," + vLongitude;
					document.getElementById('urlUbicacion').value = vMapUrl;
					var vDecoratedMapUrl = "http://maps.google.com/?ll="  + vMapUrl + "&z=15";
					document.getElementById('myDecoratedMapUrl').value = vDecoratedMapUrl;						
				}		
				
				map.fitBounds(defaultBounds);	
				
			} else {
				
				defaultBounds = new google.maps.LatLngBounds(
					      new google.maps.LatLng(22.1564699, -100.98554089999999),
					      new google.maps.LatLng(22.1564699, -100.98554089999999));

				map.fitBounds(defaultBounds);	
				
				var listener = google.maps.event.addListener(map, "idle", function() { 
					  if (map.getZoom() > 4) map.setZoom(4); 
					  google.maps.event.removeListener(listener); 
					});
			}
									
			// Create the search box and link it to the UI element.
			var input = /** @type {HTMLInputElement} */(
			    document.getElementById('pac-input'));
			
			map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	
			var service = null;
			var infowindow = new google.maps.InfoWindow({size: new google.maps.Size(150,50)});
			  			  
			var searchBox = new google.maps.places.SearchBox(
			  /** @type {HTMLInputElement} */(input));
			  
			service = new google.maps.places.PlacesService(map);

			// [START region_getplaces]
			// Listen for the event fired when the user selects an item from the
			// pick list. Retrieve the matching places for that item.
			google.maps.event.addListener(searchBox, 'places_changed', function() {
				var places = searchBox.getPlaces();
			  			   	
				 if (places.length == 0) {
				   return;
				 }
				 
				 for (var i = 0, marker; marker = markers[i]; i++) {
				   marker.setMap(null);
				 }
			
				// For each place, get the icon, place name, and location.
			  	markers = [];
				
			  	var bounds = new google.maps.LatLngBounds();
			  	
				for (var i = 0, place; place = places[i]; i++) {
					
				  var image = {
				    url: place.icon,
				    size: new google.maps.Size(71, 71),
				    origin: new google.maps.Point(0, 0),
				    anchor: new google.maps.Point(17, 34),
				    scaledSize: new google.maps.Size(25, 25)
				  };
				
				  // Create a marker for each place.
				  var marker = new google.maps.Marker({
				    map: map,
				    icon: image,
				    title: place.name,
				    position: place.geometry.location
				  });
				
				  markers.push(marker);
				  
				  bounds.extend(place.geometry.location);
				
				  var myLatitude = place.geometry.location.lat();
				  var myLongitude = place.geometry.location.lng();  			      
				  var myMapUrl = myLatitude + "," + myLongitude;
				  document.getElementById('urlUbicacion').value = myMapUrl;
				  var myDecoratedMapUrl = "http://maps.google.com/?ll="  + myMapUrl + "&z=15";
				  document.getElementById('myDecoratedMapUrl').value = myDecoratedMapUrl;
				}
			
				map.fitBounds(bounds);
			  			    
			});
			// [END region_getplaces]			
	
			// Bias the SearchBox results towards places that are within the bounds of the
			// current map's viewport.
			google.maps.event.addListener(map, 'bounds_changed', function() {
			  var bounds = map.getBounds();
			  searchBox.setBounds(bounds);
			});
			
		}
		
		google.maps.event.addDomListener(window, 'load', initialize);
						
	</script>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.SimpleTextarea");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit.form.CheckBox");
	
	
	function actulizaMunicipio(){
		var vEntidad = dijit.byId('idEntidad').get('value');
		var vPais = '${ofertaEmpleoForm.fuente}';
		municipioStore.url="";
		municipioStore.close();	

		if (vEntidad){
			dijit.byId('idMunicipio').focus();	
			dijit.byId('idMunicipio').reset();
			
			if(vPais!=3){
				municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
				municipioStore.close();
			}else{
				
				municipioStore.url = "${context}domicilio.do?method=obtenerCiudadCanada" + "&" + "idEntidad="+ vEntidad;
				municipioStore.close();
			}
		}
	}
	function doSubmit(method,idBoton) {
		dojo.byId(idBoton).disabled= true;
		estableceValores();
		if (!validaCampos()) {
		
			dojo.byId(idBoton).disabled= false;
			return;
		}		
		document.registroInformacionUbicacion.method.value=method;
		document.registroInformacionUbicacion.submit();
	}
	function gotoCancelInfUbi() {
		document.registroInformacionUbicacion.method.value='cancelarEdicionOferta';
		document.registroInformacionUbicacion.submit();
	}
	function doSubmitCancelar() {
		var input = '<input id="btnCnl" class="boton_naranja" type="button" name="btnCnl" onClick="gotoCancelInfUbi();" value="Aceptar"/>';
		messageInput('Los datos no guardados se perderán ¿Continuar?',input);
	}
	function estableceValores(){
		dojo.byId('diasLaborales').value = getDiasLaborales();		
		dojo.byId('discapacidades').value = getDiscapacidades();
	}

	
	function getDiscapacidades(){
		var cadena = '';
		var vFuente = '${ofertaEmpleoForm.fuente}';
		var becate = '${ofertaEmpleoForm.ofertaBecate}';
		
		if(vFuente==9 || becate=="true"){
			if(dojo.byId('discapacidadAuditivaC').checked){
				cadena = cadena +"1";
				document.getElementById('discapacidadAuditiva').value=1;
			} else {
				cadena = cadena +"0";
				document.getElementById('discapacidadAuditiva').value=0;
			}	
			
			if(dojo.byId('discapacidadIntelectualC').checked){
				cadena = cadena +"1";
				document.getElementById('discapacidadIntelectual').value=1;
			} else {
				cadena = cadena +"0";
				document.getElementById('discapacidadIntelectual').value=0;
			}		
			
			if(dojo.byId('discapacidadMentalC').checked){
				cadena = cadena +"1";
				document.getElementById('discapacidadMental').value=1;
			} else {
				cadena = cadena +"0";
				document.getElementById('discapacidadMental').value=0;
			}				
			
			if(dojo.byId('discapacidadMotrizC').checked){
				cadena = cadena +"1";
				document.getElementById('discapacidadMotriz').value=1;
			} else {
				cadena = cadena +"0";
				document.getElementById('discapacidadMotriz').value=0;
			}			
			
			if(dojo.byId('discapacidadVisualC').checked){
				cadena = cadena +"1";
				document.getElementById('discapacidadVisual').value=1;
			} else {
				cadena = cadena +"0";
				document.getElementById('discapacidadVisual').value=0;
			}			
			
		} else {
			cadena = '00000';
		}
		return cadena;
	}
	
	function limpiaDiscapacidades(param){		
		if(param==0){
			dojo.byId('discapacidadAuditivaC').checked = false;
			dojo.byId('discapacidadIntelectualC').checked = false;
			dojo.byId('discapacidadMentalC').checked = false;
			dojo.byId('discapacidadMotrizC').checked = false;
			dojo.byId('discapacidadVisualC').checked = false;
		}else{
			dojo.byId('discapacidadNingunaC').checked=false;
			
		}
	}	
	
	function getDiasLaborales(){
		
		var cadena = '';
		
		if(dojo.byId('domingoC').checked){
			cadena = cadena +"1";
			document.getElementById('domingo').value=1;
		} else {
			cadena = cadena +"0";
			document.getElementById('domingo').value=0;
		}
		
		if(dojo.byId('lunesC').checked){
			cadena = cadena +"1";
			document.getElementById('lunes').value=1;
		} else{ 
			cadena = cadena +"0";
			document.getElementById('lunes').value=0;		
		}
		
		if(dojo.byId('martesC').checked){
			cadena = cadena +"1";
			document.getElementById('martes').value=1;
		} else{ 
			cadena = cadena +"0";
			document.getElementById('martes').value=0;
		}
		
		if(dojo.byId('miercolesC').checked){
			cadena = cadena +"1";
			document.getElementById('miercoles').value=1;
		} else{ 
			cadena = cadena +"0";
			document.getElementById('miercoles').value=0;
		}
		
		if(dojo.byId('juevesC').checked){
			cadena = cadena +"1";
			document.getElementById('jueves').value=1;
		} else{ 
			cadena = cadena +"0";
			document.getElementById('jueves').value=0;
		}
		
		if(dojo.byId('viernesC').checked){
			cadena = cadena +"1";
			document.getElementById('viernes').value=1;
		} else{ 
			cadena = cadena +"0";
			document.getElementById('viernes').value=0;
		}
		
		if(dojo.byId('sabadoC').checked){
			cadena = cadena +"1";
			document.getElementById('sabado').value=1;
		} else{ 
			cadena = cadena +"0";
			document.getElementById('sabado').value=0;
		}		
		return cadena;		
	}
	
	function validaCampos() {
		var vFuente = '${ofertaEmpleoForm.fuente}';
		var becate = '${ofertaEmpleoForm.ofertaBecate}';
		
		if(vFuente==9) {
			if (!dojo.byId('discapacidadAuditivaC').checked &&
					!dojo.byId('discapacidadIntelectualC').checked &&
					!dojo.byId('discapacidadMentalC').checked &&
					!dojo.byId('discapacidadMotrizC').checked &&
					!dojo.byId('discapacidadVisualC').checked &&
					!dojo.byId('discapacidadNingunaC').checked  ){
				messageDojoFocus('Es necesario que seleccione al menos una opción de tipo de discapacidad. Si no acepta ningun tipo de discapacidad, seleccione Ninguna.', 'discapacidadAuditivaC');
				//document.registroInformacionUbicacion.discapacidadAuditivaC.focus();
				return false;
			}else {
				var numeroDiscapacidades = 0;
				if (dojo.byId('discapacidadAuditivaC').checked) {
					numeroDiscapacidades++;
				}
				if (dojo.byId('discapacidadIntelectualC').checked) {
					numeroDiscapacidades++;
				}
				if (dojo.byId('discapacidadMentalC').checked) {
					numeroDiscapacidades++;
				}
				if (dojo.byId('discapacidadMotrizC').checked) {
					numeroDiscapacidades++;
				}
				if (dojo.byId('discapacidadVisualC').checked) {
					numeroDiscapacidades++;
				}				
				if(numeroDiscapacidades>2) {
					alertMsg('Debe elegir máximo 2 discapacidades');
					return false;
				}
			}			
			if (!validaCampo('edadMinima')) return false;
			if (!validaCampo('edadMaxima')) return false;
			var edadMinima = dijit.byId('edadMinima').get('value');
			var edadMaxima = dijit.byId('edadMaxima').get('value');
			if (dojo.byId('discapacidadAuditivaC').checked ||
					dojo.byId('discapacidadIntelectualC').checked ||
					dojo.byId('discapacidadMentalC').checked ||
					dojo.byId('discapacidadMotrizC').checked ||
					dojo.byId('discapacidadVisualC').checked 	) {
				if (parseInt(edadMaxima)<parseInt(edadMinima)) {			
					messageFocus('La edad mínima debe ser menor a la edad máxima requerida.', 'edadMinima');
					return false;
				}
				if (parseInt(edadMinima)<16){			
					messageFocus('La edad mínima permitida es de 16 años.', 'edadMinima');
					return false;
				}			
			} else {
				if (parseInt(edadMaxima)<parseInt(edadMinima)){			
					messageFocus('La edad mínima debe ser menor a la edad máxima requerida.', 'edadMinima');
					return false;
				}
				if (parseInt(edadMinima)<60){			
					messageFocus('La edad mínima permitida es de 60 años.', 'edadMinima');
					return false;
				}				
			}		
		}
		var numeroPlazas = dijit.byId('numeroPlazas').get('value');
		if (parseInt(numeroPlazas)<1) {			
			dijit.byId('numeroPlazas').set('value','');		
		}	
		if (!dijit.byId('registroInformacionUbicacion').isValid()) {
			if (!validaCampo('tituloOferta')) return false;
			if (dojo.byId('idOcupacion') && (dojo.byId('idOcupacion').value=='' || dojo.byId('idOcupacion').value=='0')){
				var msgerr = "Se requiere seleccionar una opción válida del catálogo de ocupaciones";
				messageFocus(msgerr, 'ocupacionDeseada');
				//dijit.byId('ocupacionDeseada').focus();
				displayErrorMsg(dijit.byId('ocupacionDeseada'), msgerr);
				return false;
			}
			if (!validaCampo('ocupacionDeseada')) return false;			
			//if (!validaCampo('ocupacion')) return false;
			if (!validaCampo('numeroPlazas')) return false;	
			if (!validaCampoSelect('idTipoContrato')) return false;
			if (!validaCampoSelect('idVigenciaOferta')) return false;		
			if (!validaCampoSelect('horaEntrada')) return false;
			if (!validaCampoSelect('horaSalida')) return false;
			if (!dojo.byId('domingoC').checked &&
					!dojo.byId('lunesC').checked &&
					!dojo.byId('martesC').checked &&
					!dojo.byId('miercolesC').checked &&
					!dojo.byId('juevesC').checked &&
					!dojo.byId('viernesC').checked &&
					!dojo.byId('sabadoC').checked) {
					messageDojoFocus('Es necesario que indique los días laborales de la oferta', 'domingoC');
					//document.registroInformacionUbicacion.domingoC.focus();
					return false;
			}
			if (!validaCampoSelect('idCausaOferta')) return false;
			if (!validaCampoSelect('idEntidad')) return false;
			if (!validaCampoSelect('idMunicipio')) return false;			
		}
		if (dojo.byId('idOcupacion') && (dojo.byId('idOcupacion').value=='' || dojo.byId('idOcupacion').value=='0')){
			var msgerr = "Se requiere seleccionar una opción válida del catálogo de ocupaciones";
			alertMsg(msgerr);
			dijit.byId('ocupacionDeseada').focus();
			displayErrorMsg(dijit.byId('ocupacionDeseada'), msgerr);
			return false;
		}
		if (document.getElementById('funciones') && document.getElementById('funciones').value==''){
			alertMsg('Favor de registrar las funciones y actividades.');
			document.getElementById('funciones').focus();
			return false;
		}
		if (!validaCampo('salario')) {
			return false;
		}
		if (!validaSalarioMinimoMensual('salario')) {
			return false;
		}

		var ischecked = false;
		var prestacionesChecks = document.registroInformacionUbicacion.idPrestacion;
		if (prestacionesChecks && prestacionesChecks.length) {
			for (var i=0;i<prestacionesChecks.length;i++) {
				if (prestacionesChecks[i].checked) {
					ischecked = true;
					break;
				}
			}			
		}
		if (!ischecked) {
			message('Es necesario seleccionar alguna prestación');
			//dojo.byId(idBoton).disabled= false;
			return false;
		}
		if (document.getElementById('empresaOfrece') && document.getElementById('empresaOfrece').value==''){
			alertMsg('Favor de registrar otras prestaciones.');
			document.getElementById('empresaOfrece').focus();
			dojo.byId(idBoton).disabled= false;
			return;
		}
		
		if(becate=="true"){
			if (!validaCampoSelect('idTipoEmpleo')) return false;			
			if (!validaCampoSelect('idModalidad')) return false;
			if (!validaCampoSelect('idCurso')) return false;
			if (!validaCampoSelect('idHorarioImparticion')) return false;	
			if (!validaCampoSelect('numeroPlazasBecate')) return false;		
			if (!validaCampoSelect('idSalario')) return false;
			if (!validaCampoFecha('fechaInicioBecate')) return false;
			if (!validaCampoFecha('fechaFinBecate')) return false;	
			
			var numeroPlazasBecate = dijit.byId('numeroPlazasBecate').get('value');
			if (parseInt(numeroPlazasBecate)<1) {			
				dijit.byId('numeroPlazasBecate').set('value','');		
			}
			var fechaIni = dijit.byId('fechaInicioBecate').get('value');
			var fechaFin = dijit.byId('fechaFinBecate').get('value');	
			if (new Date(fechaIni) > new Date(fechaFin)) {			
					messageFocus('La fecha inicial no debe ser mayor a la fecha final', 'fechaInicioBecate');
					return false;
			}
			if (!validaDiscBecate()) return false;		
		
		}
		return true;
	}
	
	function validaDiscBecate(){
		if (!dojo.byId('discapacidadAuditivaC').checked &&
					!dojo.byId('discapacidadIntelectualC').checked &&
					!dojo.byId('discapacidadMentalC').checked &&
					!dojo.byId('discapacidadMotrizC').checked &&
					!dojo.byId('discapacidadVisualC').checked && dojo.byId('discapacidadSi').checked){
				messageDojoFocus('Es necesario que seleccione al menos una opción de tipo de discapacidad.', 'discapacidadAuditivaC');
				return false;
			}
			return true;
	}
	
	function validaCampo(campo) {
		var control = dijit.byId(campo);
		if (control && control.value==''){
			messageFocus(control.get("missingMessage"), campo);
			displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("missingMessage"));
			//control.focus();
			document.getElementById(campo).blur();
			//control.focus();
			return false;
		}
		if (!dijit.byId(campo).isValid()){
			messageFocus(control.get("invalidMessage"), campo);
			displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("invalidMessage"));
			//control.focus();
			document.getElementById(campo).blur();
			//control.focus();
			return false;
		}
		return true;
	}

	function validaSalarioMinimoMensual(campo) {
		var control = dijit.byId(campo);
		if (control.get('value') < ${ofertaEmpleoForm.salarioMinimoMensual} )  {
			var invalidMessage = "El salario no puede ser menor a " + ${ofertaEmpleoForm.salarioMinimoMensual};
			messageFocus(invalidMessage, campo);
			displayErrorMsg(dijit.byId(campo), invalidMessage);
			//control.focus();
			document.getElementById(campo).blur();
			return false;
		}
		return true;
	}

	function validaCampoFecha(campo) {
		var control = dijit.byId(campo);
		if (!dijit.byId(campo).isValid()){
			messageFocus(control.get("missingMessage"), campo);
			displayErrorMsg(dijit.byId(campo), dijit.byId(campo).get("missingMessage"));
			document.getElementById(campo).blur();
			return false;
		}
		return true;
	}
	
	function validaCampoSelect(campo) {
		var control = dijit.byId(campo);
		if (control && control.get('value')=='') {
			messageFocus(control.get("missingMessage"), campo);
			dijit.showTooltip(control.get("missingMessage"), control.domNode, control.get("tooltipPosition"), !control.isLeftToRight());
			//control.focus();
			document.getElementById(campo).blur();
			//control.focus();
			return false;
		}
		return true;
	}


	function alertMsg(msg) {
		message(msg);
	}
	function enviarAMisOfertas() {
		document.registroInformacionUbicacion.action = '<c:url value="/OfertaNavegacion.do?method=init"/>';
		document.registroInformacionUbicacion.submit();
	}	
	function messageDojoFocus(msg,id) {
		dojo.byId(id).focus();
		var html =
			'<div id="messageDialgop3" name="messageDialgop3">' +
		'<p style="text-align: center;">'+ msg +'</p>'+
		'<p class="form_nav">'
		+ cerrarMsg()
	    + '</p>'
	    +'</div>';
		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false
	        
	    });		
		dialogMsg.show();
	}
	dojo.addOnLoad(function() {
		var vPais = '${ofertaEmpleoForm.fuente}';
		var becate = '${ofertaEmpleoForm.ofertaBecate}';
		
		if (vPais!=3){
			
			dojo.byId('tEntidad').innerHTML = "Entidad federativa";
			dojo.byId('tMunicipio').innerHTML = "Municipio y/o delegación";
			
			entidadStore.url = "${context}ofertaEmpleo.do?method=entidad";
			entidadStore.close();
			
			entidadStore.fetch({
	         	onComplete: function(items, request) {
	         		if (items.length == 0) return;
	         		dijit.byId('idEntidad').attr('value', '${ofertaEmpleoForm.idEntidad}');
	         	}
	   		});
			
			var vEntidad = ${ofertaEmpleoForm.idEntidad};
			
			municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad="+ vEntidad;
			municipioStore.close();	

		   	municipioStore.fetch({
		         	onComplete: function(items, request) {
		         		if (items.length == 0) return;
		         		dijit.byId('idMunicipio').attr('value', '${ofertaEmpleoForm.idMunicipio}');
		         	}
		   	});				
			
		}else{
			
			dojo.byId('tEntidad').innerHTML = "Provincia";
			dojo.byId('tMunicipio').innerHTML = "Ciudad";
			
			entidadStore.url = "${context}ofertaEmpleo.do?method=provinciasCanada";
			entidadStore.close();
			entidadStore.fetch({
	         	onComplete: function(items, request) {
	         		if (items.length == 0) return;
	         		dijit.byId('idEntidad').attr('value', '${ofertaEmpleoForm.idEntidad}');
	         	}
	   		});
			
			var vEntidad = ${ofertaEmpleoForm.idEntidad};
			
			municipioStore.url = "${context}domicilio.do?method=obtenerCiudadCanada" + "&" + "idEntidad="+ vEntidad;
			municipioStore.close();	

		   	municipioStore.fetch({
		         	onComplete: function(items, request) {
		         		if (items.length == 0) return;
		         		dijit.byId('idMunicipio').attr('value', '${ofertaEmpleoForm.idMunicipio}');
		         	}
		   	});
		}
		setFocusOnFirstControl();
		
		if(becate=="true"){
			dijit.byId('fechaInicioBecate').attr('value', new Date('${ofertaEmpleoForm.fechaInicioBecate}'));
			dijit.byId('fechaFinBecate').attr('value', new Date('${ofertaEmpleoForm.fechaFinBecate}'));	
			document.getElementById('discapacidadNingunaC').style.display = 'none';
  	        document.getElementById('discapacidadNingunaL').style.display = 'none'; 
  	        if (document.getElementById( 'discapacidadNo' ).checked){
  	           	document.getElementById( 'discapacidadAuditivaC' ).disabled = true;
	  	        document.getElementById( 'discapacidadIntelectualC' ).disabled = true;
	   	        document.getElementById( 'discapacidadMentalC' ).disabled = true;
	   	        document.getElementById( 'discapacidadMotrizC' ).disabled = true;
	   	        document.getElementById( 'discapacidadVisualC' ).disabled = true;	 
  	        } 	
		}
		
	}); 
	
	function setFocusOnFirstControl(){
		if (document.getElementById('discapacidadAuditivaC')){
			document.getElementById('discapacidadAuditivaC').focus();
		} else if (document.getElementById('tituloOferta')){
			document.getElementById('tituloOferta').focus();
		}				
	}
	
  	function autocompleteOcupacion(){
  		var search = dojo.byId('ocupacionDeseada').value;
	
  		search = dojo.trim(search);
  		//console.log("--> search:"+ search +" - "+ search.length);
		dojo.byId('idOcupacion').value = 0;
  		if(search.length < 2) {

  			cerrarAutocomplete();

  		} else if(search.length >= 2){

  			dojo.xhrPost({url: '${context}ofertaEmpleo.do?method=ocupaciones&search='+search, handleAs: "text",headers: { "Content-Type": "application/x-www-form-urlencoded; charset=ISO-8859-1" },
  						 load: function(result){
  							   		if(result.length >0) {
  										dojo.byId('ocupacionesListDiv').style.display='block';
  										dojo.byId('listaOcupaciones').innerHTML = result;
  									}else{
  										cerrarAutocomplete();
  									}
  								}
  						});
  		}
  	}

  	function setOcupacion(id,val) {
  		dojo.byId('idOcupacion').value = id;
  		dojo.byId('ocupacionDeseada').value= val;
  		dojo.byId('ocupacionDeseada').focus();
  		cerrarAutocomplete();
  		searchTypeProfile();
  	}
  	
  	function searchTypeProfile() {
  		var idOcupacion = dojo.byId('idOcupacion').value;
  		if (idOcupacion.length > 2) {
  			dojo.xhrPost({url: '${context}ofertaEmpleo.do?method=searchTypeProfile&idOcupacion='+idOcupacion, handleAs: "text",headers: { "Content-Type": "application/x-www-form-urlencoded; charset=ISO-8859-1" },
  				load: function(result) {
  					if (result.length >0) {
  						dojo.byId('typeProfileListDiv').style.display='block';
  						dojo.byId('typeProfileMsgDiv').style.display='block';
  						dojo.byId('typeProfileList').innerHTML = result;
  					}
  				}
  			});
  		}
  	}

  	function cerrarAutocomplete() {
  		dojo.byId('ocupacionesListDiv').style.display='none';
  		dojo.byId('listaOcupaciones').innerHTML = "";
  	}
	
  	function edicionValidadion(){
  		alert('Favor de llenar los campos marcados como obligatorios');  	
  	}
  	
  	function maximaLongitud(texto,maxlong) {
	  	var tecla, int_value, out_value;
	
	  	if (texto.value.length > maxlong) {
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
  	function gotoRequisitos() { 
  		document.registroInformacionUbicacion.informacion.value="false";
  		document.registroInformacionUbicacion.requisitos.value="true";
  		document.registroInformacionUbicacion.method.value='cancelarEdicionOferta';
  		document.registroInformacionUbicacion.submit();
  	}
  	function gotoDatosContacto() { 
  		document.registroInformacionUbicacion.informacion.value="false";
  		document.registroInformacionUbicacion.fin.value="true";
  		document.registroInformacionUbicacion.method.value='cancelarEdicionOferta';
  		document.registroInformacionUbicacion.submit();
  	}
  	function cambiaSeccion(seccion) {
  		var input;
  		if (seccion==5)
  			input = '<input id="btnSec" class="boton_naranja" type="button" name="btnSec" onClick="gotoRequisitos();" value="Aceptar"/>';
  		if (seccion==6)
  			input = '<input id="btnSec" class="boton_naranja" type="button" name="btnSec" onClick="gotoDatosContacto();" value="Aceptar"/>';
  		messageInput('Los datos no guardados se perderán ¿Continuar?',input);
  	}
  	function messageInput(msg,input) {
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
				'<p style="text-align: center;">'+ msg +'</p><br>'+
				'<p class="form_nav">' + input + cerrarMsg() + 
			'</div>';

		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	        hide: function(){
	        	clearWidgetsHtml('messageDialgop2');
	        	dialogMsg.destroy()
	        }
	    });			
		dialogMsg.show();
	}
  	function displayErrorMsg(textBox, errmsg){
  		var originalValidator = textBox.validator;
  		textBox.validator = function() {return false;};
  		textBox.validate();
  		textBox.validator = originalValidator;
  		
  		dijit.showTooltip(
  			    //textBox.get("invalidMessage"),
  			    errmsg,
  			    textBox.domNode, 
  			    textBox.get("tooltipPosition"),
  			    !textBox.isLeftToRight()
  		);
  	}

  	var dialogNotificacion;
  	
  	function showNotificacion(){
  		var html = '<p>'+
  					'Se ha identificado que la oferta de empleo que quiere usar '+
  					'aún no maneja el registro del catálogo de habilidades y '+
  					'actitudes la cual es requerida, favor de revisar sus datos.'+
  				   '</p>'+
  		           '<p class="form_nav">'+
  		           '<button id="btnCerrar" name="btnCerrar" onclick="closeNotificacion();">Cerrar</button>'+
  		           '</p>';

		dialogNotificacion = new dijit.Dialog({
  	        title: 'Notificación',
  	        //href: '${context}conocerconfig.do?method=init&idPropietario=${idCandidato}',
  	        content: html,
  	        style: 'width:480px; height:200px; background: #FFF',
  	        showTitle: false, draggable : false, closable : false
  	    });

		dojo.style(dialogNotificacion.closeButtonNode,"display","none");
		dialogNotificacion.show();
  	}

  	function closeNotificacion() {
  		dialogNotificacion.hide();
  	}
  	
  	function aceptaDisc(rad) { 
  	        var rads = document.getElementsByName( rad.name );
   	        document.getElementById( 'discapacidadAuditivaC' ).disabled = ( rads[1].checked ) ? true : false;
   	        document.getElementById( 'discapacidadIntelectualC' ).disabled = ( rads[1].checked ) ? true : false;
   	        document.getElementById( 'discapacidadMentalC' ).disabled = ( rads[1].checked ) ? true : false;
   	        document.getElementById( 'discapacidadMotrizC' ).disabled = ( rads[1].checked ) ? true : false;
   	        document.getElementById( 'discapacidadVisualC' ).disabled = ( rads[1].checked ) ? true : false;	 
   	        if(rads[1].checked){
   	        	limpiaDiscapacidades(0);
   	        }
    }
    
    function popup(mylink, windowname) { 
		if (! window.focus)return true;
	    var href;
	   	if (typeof(mylink) == 'string') href=mylink;
	   	else href=mylink.href; 
	   	window.open(href, windowname, 'width=600,height=700,left=400,top=100,location=no,scrollbars=yes,resizable=no,menubar=no'); 
	   	return false; 
	}
  
    function setTypeProfile(id) {
    	var params = '&idTypeProfile='+id+'&idOcupacion='+dojo.byId('idOcupacion').value+'&tituloOferta='+dojo.byId('tituloOferta').value+'&ocupacionDeseada='+dojo.byId('ocupacionDeseada').value;
  		closeTypeProfile();
  		popup('${context}ofertaEmpleo.do?method=detailTypeProfile'+params, 'Detalle perfil tipo');
  		//location.replace('${context}ofertaEmpleo.do?method=init'+params);
  	}
    
  	function closeTypeProfile() {
  		dojo.byId('typeProfileListDiv').style.display='none';
  		dojo.byId('typeProfileMsgDiv').style.display='none';
  		dojo.byId('typeProfileList').innerHTML = "";
  	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<c:if
	test="${not empty EMPRESA_NOTIFICACION && EMPRESA_NOTIFICACION == 'OFERTA_SIN_HABILIDADES'}">
	<script type="text/javascript">
	dojo.addOnLoad(function() {  
		showNotificacion();	
	}); 
	</script>
</c:if>


</head>
<body>
	<%--
<div dojoType="dojo.data.ItemFileReadStore" jsId="ocupacionStore"         url="${context}ofertaEmpleo.do?method=ocupaciones"></div>
--%>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="tipoEmpleoStore"            url="${context}ofertaEmpleo.do?method=tipoEmpleo" urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="tipoContratoStore"          url="${context}ofertaEmpleo.do?method=tipoContrato" urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="diasStore"                  url="${context}ofertaEmpleo.do?method=dias" urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesStore"                 url="${context}ofertaEmpleo.do?method=meses" urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosStore"                 url="${context}ofertaEmpleo.do?method=anios" urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="horarioLaboralStore"        url="${context}ofertaEmpleo.do?method=horarioLaboral" urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="causaOfertaStore"           url="${context}ofertaEmpleo.do?method=causaOferta"  urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadStore"               urlPreventCache="true" clearOnClose="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore"             urlPreventCache="true" clearOnClose="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="vigenciaStore"              url="${context}ofertaEmpleo.do?method=vigencia" urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="tipoCapacitacion"           url="${context}ofertaEmpleo.do?method=tipoCapacitacion"  urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="cursosStore"                url="${context}ofertaEmpleo.do?method=cursos" urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="tipoRecurso"                url="${context}ofertaEmpleo.do?method=tipoRecurso"   urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="horarioCapacitacionStore"   url="${context}ofertaEmpleo.do?method=horarioCapacitacion"  urlPreventCache="true"></div>
	<div dojoType="dojo.data.ItemFileReadStore" jsId="salariosStore"              url="${context}ofertaEmpleo.do?method=salarios"  urlPreventCache="true"></div>		

	<div style="clear: both"></div>
	<div class="tab_block">
		<div align="left" style="display: block;" id="returnME">
			<a class="expand"
				href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');">
				<strong>Ir al inicio de Mi espacio</strong>
			</a>
		</div>
		<div class="Tab_espacio">
			<h3>Administrar mis ofertas de empleo</h3>
			<div class="subTab">
				<c:if test="${ofertaEmpleoForm.idOfertaEmpleo>0}">
					<c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
						<div class="nav_miPerfil">
							<ul>
								<li><a href="<c:url value="/OfertaNavegacion.do?method=init"/>">Ver
										mis ofertas de empleo</a></li>
								<li><a href="<c:url value="/dondePublicar.do?method=init"/>">Crear
										oferta de empleo</a></li>
								<li class="curSubTab"><span>Editar oferta de empleo</span>
								</li>
							</ul>
						</div>
					</c:if>

					<div class="nav_misOfertas">
						<ul>
							<li class="curSubTab"><span>Información y ubicación
									de la oferta de empleo</span></li>
							<li class="nav_requisitos"><a
								href="javascript:cambiaSeccion(5);">Requisitos de la oferta
									de empleo</a></li>
							<li class="nav_contacto"><a
								href="javascript:cambiaSeccion(6);">Datos de contacto</a></li>
						</ul>
					</div>
				</c:if>
				<c:if test="${ofertaEmpleoForm.idOfertaEmpleo==0}">
					<c:if test="${not empty USUARIO_APP && USUARIO_APP.empresa}">
						<div class="nav_miPerfil">
							<ul>
								<li class="curSubTab"><span>Crear oferta de empleo</span></li>
								<li><a href="<c:url value="/OfertaNavegacion.do?method=init"/>">Mis
										ofertas de empleo</a></li>
								<li><a href="<c:url value="/RecuperaOfertas.do?method=init"/>">Utiliza
										oferta como plantilla</a></li>
								<li><a
									href="<c:url value="/reporteOfertasEmpresa.do?method=init"/>">Reporte
										de ofertas de empleo</a></li>
							</ul>
						</div>
					</c:if>

				</c:if>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>

		<p class="instruc_01">
			<strong>Crea una oferta de empleo siguiendo tres sencillos
				pasos</strong>
		</p>
		<div class="form_edge">
			<div class="stepApp">
				<h2>
					<img
						alt="Paso 1 de 3. Información y ubicación de la oferta de empleo"
						src="css/images/paso_1c.png">
				</h2>
			</div>
		</div>
		<p class="labeled">
			Los datos marcados con <span>*</span> son obligatorios
		</p>




	</div>


	<form name="registroInformacionUbicacion" id="registroInformacionUbicacion" action="ofertaEmpleo.do" method="post" dojoType="dijit.form.Form">
		<fieldset class="datosGenerales bloque">
			<legend>Datos generales de la oferta de empleo</legend>
			<input type="hidden" name="method" id="method" value="init">
			<input type="hidden" name="fechaVigencia" id="fechaVigencia" value="" dojoType="dijit.form.TextBox" />
			<input type="hidden" name="diasLaborales" id="diasLaborales" value="" dojoType="dijit.form.TextBox" />
			<input type="hidden" name="discapacidades" id="discapacidades" value="" dojoType="dijit.form.TextBox" />
		    <input type="hidden" name="idTipoDiscapacidad" id="idTipoDiscapacidad" value="1" dojoType="dijit.form.TextBox" />
		    <input type="hidden" name="fin" id="fin" value="false">
		    <input type="hidden" name="informacion" id="informacion" value="true">
			<input type="hidden" name="domingo" id="domingo" value="0">
			<input type="hidden" name="lunes" id="lunes" value="0">
			<input type="hidden" name="martes" id="martes" value="0">
			<input type="hidden" name="miercoles" id="miercoles" value="0">
			<input type="hidden" name="jueves" id="jueves" value="0">
			<input type="hidden" name="viernes" id="viernes" value="0">
			<input type="hidden" name="sabado" id="sabado" value="0">
			<input type="hidden" name="requisitos" id="requisitos" value="false">
			<input type="hidden" name="discapacidadAuditiva" id="discapacidadAuditiva" value="0">
			<input type="hidden" name="discapacidadIntelectual" id="discapacidadIntelectual" value="0">
			<input type="hidden" name="discapacidadMental" id="discapacidadMental" value="0">
			<input type="hidden" name="discapacidadMotriz" id="discapacidadMotriz" value="0">
			<input type="hidden" name="discapacidadVisual" id="discapacidadVisual" value="0">
			<input type="hidden" name="discapacidadNinguna" id="discapacidadNinguna" value="0">
			<input type="hidden" name="urlUbicacion" id="urlUbicacion" value="${ofertaEmpleoForm.urlUbicacion}">
			<input type="hidden" name="myDecoratedMapUrl" id="myDecoratedMapUrl" value="">
			<input type="hidden" name="idOcupacion" regExp="\d" id="idOcupacion" value="${ofertaEmpleoForm.idOcupacion}" dojoType="dijit.form.TextBox" />
			<input type="hidden" name="idTypeProfile" id="idTypeProfile" value="" dojoType="dijit.form.TextBox" />

			<!-- ABRIENDO ESPACIOS -->
			<c:if test="${ofertaEmpleoForm.fuente==9}">
				<div id="discapacidadesDiv" name="discapacidadesDiv">
					<jsp:include page="/jsp/oferta/discapacidades.jsp" flush="true" />
				</div>
				<div class="grid1_3 margin_top_10">
					<div class="labeled">
						<span>*</span> Rango de edad
					</div>
					<div class="fl margin-r_20">
						<label for="edadMinima" id="spanDe">De:</label> <input
							type="text" id="edadMinima" name="edadMinima"
							value="${ofertaEmpleoForm.edadMinima}" size="150" maxlength="3"
							trim="true" style="width: 60px"
							dojoType="dijit.form.ValidationTextBox"
							missingMessage="Debe indicar la edad mínima."
							regExp="${regexpedad}"
							invalidMessage="La edad mínima no es válida, favor de verificar" />
					</div>
					<div class="fl">
						<label for="edadMaxima" id="spanA">A:</label> <input type="text"
							id="edadMaxima" name="edadMaxima"
							value="${ofertaEmpleoForm.edadMaxima}" size="150" maxlength="3"
							trim="true" style="width: 60px"
							dojoType="dijit.form.ValidationTextBox"
							missingMessage="Debe indicar la edad máxima."
							regExp="${regexpedad}"
							invalidMessage="La edad máxima es invalida, favor de verificar" />
					</div>
					<div class="clearfix"></div>
				</div>
			</c:if>

			<c:if test="${ofertaEmpleoForm.fuente!=9}">
				<p class="labeled">Información de la oferta de empleo</p>
			</c:if>

			<div>
				<label for="tituloOferta"><span>*</span> Título de la oferta</label>
				<div class="margin_top_10">Por ejemplo: Contador público,
					trabajadores de limpieza, etcétera</div>
				<br> <input type="text" id="tituloOferta" name="tituloOferta"
					value="${ofertaEmpleoForm.tituloOferta}" size="150" maxlength="150"
					trim="true" dojoType="dijit.form.ValidationTextBox" required="true"
					missingMessage="Debe indicar el título de la oferta."
					regExp="${regexptitulo}"
					invalidMessage="El título de la oferta es inválida, favor de verificar" />
			</div>

			<div class="margin_top_20">
				<label for="ocupacionDeseada"><span>*</span> Ocupación
					requerida</label>
				<div>Escribe las primeras letras de la ocupación y a
					continuación selecciona una de las opciones mostradas</div>
				<br> <input type="text" dojoType="dijit.form.ValidationTextBox"
					trim="true" name="ocupacionDeseada" id="ocupacionDeseada"
					value="${ofertaEmpleoForm.ocupacion}" required="true"
					missingMessage="Se requiere seleccionar una opción válida del catálogo de ocupaciones."
					invalidMessage="Se requiere seleccionar una opción válida del catálogo de ocupaciones."
					onkeyup="javascript:autocompleteOcupacion();" />
			</div>

			<div class="suggestionsBox" id="ocupacionesListDiv"
				onblur="javascript:cerrarAutocomplete();" style="display: none">
				<div class="suggestionList" id="areaMensajes">
					<ul id="listaOcupaciones"
						style="max-height: 200px; overflow: auto;">
					</ul>
				</div>
			</div>
			<div class="suggestionsBoxMsg" id="typeProfileMsgDiv" style="display: none">
				<b>Resultados obtenidos de la ocupación capturada. Para visualizar el detalle de la misma, dar clic en el detalle</b>
			</div>
			<div class="suggestionsBox" id="typeProfileListDiv" onmouseleave="javascript:closeTypeProfile();" style="display: none">
				<div class="suggestionList" id="typeProfileMsgs">
					<ul id="typeProfileList" style="max-height: 200px; overflow: auto;">
					</ul>
				</div>
			</div>

			<div class="margin_top_20">
				<label for="numeroPlazas"><span>*</span> Número de plazas</label> <input
					type="text" id="numeroPlazas" name="numeroPlazas"
					value="${ofertaEmpleoForm.numeroPlazas}" size="150" maxlength="3"
					trim="true" style="width: 120px"
					dojoType="dijit.form.ValidationTextBox" required="true"
					missingMessage="Debe indicar el número de plazas."
					regExp="${regexpplazas}"
					invalidMessage="El número de plazas es inválido, favor de verificar" />
			</div>
			<div class="clearfix"></div>

			<div class="campoTexto margin_top_20">
				<label for="funciones"><span>*</span> Funciones y
					actividades a realizar</label>
				<textarea cols="70" rows="4" name="funciones" id="funciones"
					trim="true" onchange="return maximaLongitud(this,2000)"
					onKeyUp="return maximaLongitud(this,2000)"
					onblur="return maximaLongitud(this,2000)" class="textGoogie">${fn:trim(fn:replace(ofertaEmpleoForm.funciones, '\\n|\\r|\\t', ''))}</textarea>
				<script type="text/javascript">
		         		var vSpellFuncion1 = new GoogieSpell("googiespell/", "<%=vProxy%>");
		         		vSpellFuncion1.setLanguages({'es': 'Español'});
	   					vSpellFuncion1.hideLangWindow();
	  					vSpellFuncion1.decorateTextarea("funciones");
					</script>
			</div>

			<div class="grid1_3 margin_top_10 fl">
				<label for="idTipoContrato"><span>*</span> Tipo de contrato</label>
				<select id="idTipoContrato" name="idTipoContrato" required="true"
					missingMessage="Debe indicar el tipo de contrato."
					store="tipoContratoStore" dojotype="dijit.form.FilteringSelect"
					value="${ofertaEmpleoForm.idTipoContrato}">
				</select>
			</div>

			<div class="grid1_3 margin_top_10 fl">
				<label for="idVigenciaOferta"><span>*</span> Vigencia de
					publicación de la oferta</label> <select id="idVigenciaOferta"
					name="idVigenciaOferta" promptMessage="Vigencia" required="true"
					missingMessage="Indicar la vigencia de la oferta."
					value="${ofertaEmpleoForm.idVigenciaOferta}" autocomplete="false"
					dojotype="dijit.form.FilteringSelect" store="vigenciaStore">
				</select>
			</div>

			<div class="grid1_3 margin_top_10 fl">
				<label for="idCausaOferta"><span>*</span> Causa que origina
					la oferta</label> <select id="idCausaOferta" name="idCausaOferta"
					required="true"
					missingMessage="Debe indicar la causa que origina la oferta"
					store="causaOfertaStore" dojotype="dijit.form.FilteringSelect"
					value="${ofertaEmpleoForm.idCausaOferta}" autocomplete="true">
				</select>

			</div>
			<!-- solo becate	 -->
			<c:if test="${ofertaEmpleoForm.ofertaBecate}">
				<div class="clearfix"></div>
				<div class="grid1_3 margin_top_10 fl">
					<label for="idTipoEmpleo"><span>*</span> Tipo de empleo</label> <select
						id="idTipoEmpleo" name="idTipoEmpleo" required="true"
						missingMessage="Debe indicar el tipo de empleo"
						store="tipoEmpleoStore" dojotype="dijit.form.FilteringSelect"
						value="${ofertaEmpleoForm.idTipoEmpleo}" autocomplete="true">
					</select>

				</div>
			</c:if>
			<div class="clearfix"></div>
			<div class="grid1_3 fl">
				<div class="labeled">Horario y días de trabajo</div>
				<div class="fl margin-r_20">
					<label for="horaEntrada"><span>*</span> Entrada</label> <select
						id="horaEntrada" name="horaEntrada" required="true"
						missingMessage="Debe indicar la hora de entrada."
						store="horarioLaboralStore" dojotype="dijit.form.FilteringSelect"
						value="${ofertaEmpleoForm.horaEntrada}" autocomplete="true"
						style="width: 100px">
					</select>
				</div>
				<div class="fl">
					<label for="horaSalida"><span>*</span> Salida</label> <select
						id="horaSalida" name="horaSalida" required="true"
						missingMessage="Debe indicar la hora de salida"
						store="horarioLaboralStore" dojotype="dijit.form.FilteringSelect"
						value="${ofertaEmpleoForm.horaSalida}" autocomplete="true"
						style="width: 100px">
					</select>
				</div>
			</div>
			<div class="rolar margin_top_30 grid1_3 fl">
				<div class="labeled">
					<span>*</span> ¿El horario implica rolar turnos?
				</div>
				<c:if test="${ofertaEmpleoForm.rolarTurnos!=2}">
					<div class="margin_top_10 margin-r_20 fl">
						<input class="fl" type="radio" name="rolarTurnos"
							id="rolarTurnosSi" value="2"> <label class="fl"
							for="rolarTurnosSi">Si</label>
					</div>
					<div class="margin_top_10 fl">
						<input class="fl" type="radio" name="rolarTurnos"
							id="rolarTurnosNo" value="1" checked="checked"> <label
							class="fl" for="rolarTurnosNo">No</label>
					</div>
					<div class="clearfix"></div>
				</c:if>
				<c:if test="${ofertaEmpleoForm.rolarTurnos==2}">
					<div class="margin-r_20 fl">
						<input class="fl" type="radio" name="rolarTurnos"
							id="rolarTurnosSi" value="2" checked="checked"> <label
							class="fl" for="rolarTurnosSi">Si</label>
					</div>
					<div class="fl">
						<input class="fl" type="radio" name="rolarTurnos"
							id="rolarTurnosNo" value="1"> <label class="fl"
							for="rolarTurnosNo">No</label>
					</div>
					<div class="clearfix"></div>
				</c:if>
			</div>
			<div class="clearfix"></div>




			<div class="labeled">
				<span>*</span> Indica los días laborales
			</div>
			<div>Puedes seleccionar más de una opción</div>
			<br>
			<ul class="diasLaborales">
				<li>
					<c:if test="${ofertaEmpleoForm.domingo==1}">
						<input type="checkbox" name="domingoC" id="domingoC" value="1" checked="checked">
					</c:if>
					<c:if test="${ofertaEmpleoForm.domingo!=1}">
						<input type="checkbox" name="domingoC" id="domingoC" value="1">
					</c:if>
					<label for="domingoC">Domingo</label>
				</li>
				<li>
					<c:if test="${ofertaEmpleoForm.lunes==1}">
						<input type="checkbox" name="lunesC" id="lunesC" value="1" checked="checked">
					</c:if>
					<c:if test="${ofertaEmpleoForm.lunes!=1}">
						<input type="checkbox" name="lunesC" id="lunesC" value="1">
					</c:if>
					<label for="lunesC">Lunes</label></li>
				<li>
					<c:if test="${ofertaEmpleoForm.martes==1}">
						<input type="checkbox" name="martesC" id="martesC" value="1" checked="checked">
					</c:if>
					<c:if test="${ofertaEmpleoForm.martes!=1}">
						<input type="checkbox" name="martesC" id="martesC" value="1">
					</c:if>					
					<label for="martesC">Martes</label></li>
				<li>
					<c:if test="${ofertaEmpleoForm.miercoles==1}">
						<input type="checkbox" name="miercolesC" id="miercolesC" value="1" checked="checked">
					</c:if>
					<c:if test="${ofertaEmpleoForm.miercoles!=1}">
						<input type="checkbox" name="miercolesC" id="miercolesC" value="1">
					</c:if>	
					<label for="miercolesC">Miércoles</label></li>
				<li>
					<c:if test="${ofertaEmpleoForm.jueves==1}">
						<input type="checkbox" name="juevesC" id="juevesC" value="1" checked="checked">
					</c:if>
					<c:if test="${ofertaEmpleoForm.jueves!=1}">
						<input type="checkbox" name="juevesC" id="juevesC" value="1">
					</c:if>				
					<label for="juevesC">Jueves</label></li>
				<li>
					<c:if test="${ofertaEmpleoForm.viernes==1}">
						<input type="checkbox" name="viernesC" id="viernesC" value="1"checked="checked">
					</c:if>
					<c:if test="${ofertaEmpleoForm.viernes!=1}">
						<input type="checkbox" name="viernesC" id="viernesC" value="1">
					</c:if>		
					<label for="viernesC">Viernes</label></li>
				<li>
				    <c:if test="${ofertaEmpleoForm.sabado==1}">
						<input type="checkbox" name="sabadoC" id="sabadoC" value="1" checked="checked">
					</c:if>
					<c:if test="${ofertaEmpleoForm.sabado!=1}">
						<input type="checkbox" name="sabadoC" id="sabadoC" value="1">
					</c:if>
					<label for="sabadoC">Sábado</label></li>
			</ul>
			
			<c:if test="${ofertaEmpleoForm.ofertaBecate}">
				<div class="rolar margin_top_30 grid1_3 fl">
					<div class="labeled">
						<span>*</span> ¿Acepta candidatos con discapacidad?
					</div>
					<c:if test="${ofertaEmpleoForm.discapacidad==2}">
						<div class="margin-r_20 fl">
							<input class="fl" type="radio" name="discapacidad"
								id="discapacidadSi" value="2" onclick="aceptaDisc(this);"
								checked="checked">
							<label class="fl" for="discapacidadSi">Si</label>
						</div>
						<div class="fl">
							<input class="fl" type="radio" name="discapacidad"
								id="discapacidadNo" value="1" onclick="aceptaDisc(this);">
							<label class="fl" for="discapacidadNo">No</label>
						</div>
					</c:if>
					<c:if test="${ofertaEmpleoForm.discapacidad!=2}">
						<div class="margin-r_20 fl">
							<input class="fl" type="radio" name="discapacidad"
								id="discapacidadSi" value="2" onclick="aceptaDisc(this);">
							<label class="fl" for="discapacidadSi">Si</label>
						</div>
						<div class="fl">
							<input class="fl" type="radio" name="discapacidad"
								id="discapacidadNo" value="1" onclick="aceptaDisc(this);"
								checked="checked">
							<label class="fl" for="discapacidadNo">No</label>
						</div>
					</c:if>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
				<div id="discapacidadesDiv2">
					<jsp:include page="/jsp/oferta/discapacidades.jsp" flush="true" />
				</div>
			</c:if>
			<br clear="all" />

		</fieldset>

		<fieldset class="empresa_ofrece bloque">
			<legend>La empresa ofrece</legend>

			<p class="labeled">Salario y prestaciones</p>
			<div class="grid1_3">
				<label for="salario"><span>*</span> Salario neto mensual</label>
				<input
					type="text" name="salario" id="salario" required="true"
					missingMessage="Es necesario que indique el salario."
					regExp="${regexpsalario}"
					invalidMessage="Cantidad invalida favor de verificar."
					value="${ofertaEmpleoForm.salario}" maxlength="9"
					style="width: 10em;" dojoType="dijit.form.ValidationTextBox" />
			</div>
			<div>
				<div class="labeled">
					<span>*</span> Prestaciones
				</div>
				<p>Puedes seleccionar más de una opción</p>

				<fmt:formatNumber var="numbloque"
					value="${fn:length(ofertaEmpleoForm.prestaciones) / 3}"
					maxFractionDigits="0" />
				<c:forEach var="prestacion" items="${ofertaEmpleoForm.prestaciones}"
					varStatus="index">
					<c:set var="open"
						value="${index.count==1 || index.count==numbloque+1 || index.count==(numbloque*2)+1}" />
					<c:set var="close"
						value="${index.count==numbloque || index.count==(numbloque*2) || index.count==fn:length(ofertaEmpleoForm.prestaciones)}" />
					<c:set var="checkpres" value="" />

					<c:forEach var="prestacionSaved"
						items="${ofertaEmpleoForm.idPrestacion}">
						<c:if test="${prestacion.idCatalogoOpcion == prestacionSaved}">
							<c:set var="checkpres" value="checked" />
						</c:if>
					</c:forEach>
					<c:if test="${open}">
						<ul class="grid1_3 fl margin_top_10">
					</c:if>
					<li><label><input type="checkbox" id="idPrestacion"
							name="idPrestacion" value="${prestacion.idCatalogoOpcion}"
							${checkpres} /> ${prestacion.opcion}</label></li>
					<c:if test="${close}">
						</ul>
					</c:if>
				</c:forEach>
				<div class="clearfix"></div>
			</div>

			<div class="campoTexto">
				<div class="labeled">Otras prestaciones</div>
				<textarea cols="70" rows="4" name="empresaOfrece" id="empresaOfrece"
					trim="true" onchange="return maximaLongitud(this,2000)"
					onKeyUp="return maximaLongitud(this,2000)"
					onblur="return maximaLongitud(this,2000)" class="textGoogie"
					required="true"
					missingMessage="Favor de registrar otras prestaciones.">${fn:trim(fn:replace(ofertaEmpleoForm.empresaOfrece, '\\n|\\r|\\t', ''))}
			</textarea>
				<script type="text/javascript">
		         		var vSpellFuncion2 = new GoogieSpell("googiespell/", '<%=vProxy%>');
		         		vSpellFuncion2.setLanguages({'es': 'Español'});
	   					vSpellFuncion2.hideLangWindow();
	  					vSpellFuncion2.decorateTextarea("empresaOfrece");
				</script>
			</div>
		</fieldset>

		<fieldset class="ubicacion bloque">

			<legend>Ubicación de la oferta de empleo</legend>

			<div class="grid1_3 margin_top_10 fl">
				<label for="idEntidad"><span>*</span><span id="tEntidad">Entidad
						federativa</span></label> <select id="idEntidad" name="idEntidad" required="true"
					missingMessage="Debe indicar la entidad" store="entidadStore"
					dojotype="dijit.form.FilteringSelect"
					value="${ofertaEmpleoForm.idEntidad}" maxHeight="250"
					onchange="javascript:actulizaMunicipio();" autocomplete="true">
				</select>
			</div>

			<div class="grid1_3 margin_top_10 fl">
				<label for="idMunicipio"><span>*</span><span id="tMunicipio">Municipio
						o delegación</span></label> <select id="idMunicipio" name="idMunicipio"
					required="true"
					missingMessage="Debe indicar el municipio o delegación"
					store="municipioStore" dojotype="dijit.form.FilteringSelect"
					maxHeight="250" value="${ofertaEmpleoForm.idMunicipio}"
					autocomplete="true">
				</select>
			</div>
			<div class="clearfix"></div>


			<p>Mapa de ubicación de la oferta de empleo---</p>
			<p class="margin_top_10">Escribe en el campo de búsqueda la
				ubicación de la oferta de empleo (calle, número, colonia y
				delegación).</p>
			<input id="pac-input" class="controls" type="text"
				placeholder="Proporcione una dirección">
			<div id="map-canvas" class="margin-r_10" style="height: 400px;"></div>
		</fieldset>

		<!-- Oferta Becate --Datos de la capacitacion -->
		<c:if test="${ofertaEmpleoForm.ofertaBecate}">
			<fieldset class="capacitacion bloque">
				<legend>Datos de la Capacitación</legend>

				<div class="fl margin-r_20">
					<label for="folio">Clave de la Oferta</label> <input type="text"
						id="folio" name="folio" disabled="true"
						value="${ofertaEmpleoForm.claveOferta}" size="150" maxlength="15"
						trim="true" style="width: 290px"
						dojoType="dijit.form.ValidationTextBox"/>
				</div>
				<div class="clearfix"></div>

				<div class="grid1_3 margin_top_10 fl">
					<label for="idModalidad"><span>*</span>Tipo de capacitación</label>
					<select id="idModalidad" name="idModalidad" required="true"
						missingMessage="Debe indicar el tipo de capacitación"
						store="tipoCapacitacion" dojotype="dijit.form.FilteringSelect"
						value="${ofertaEmpleoForm.idModalidad}" maxHeight="250">
					</select>
				</div>
				<div class="grid1_3 margin_top_10 fl">
					<label for="idCurso"><span>*</span> Nombre del Curso</label> <select
						id="idCurso" name="idCurso" required="true"
						missingMessage="Debe indicar el nombre del Curso"
						store="cursosStore" dojotype="dijit.form.FilteringSelect"
						value="${ofertaEmpleoForm.idCurso}" maxHeight="250">
					</select>
				</div>
				<div class="clearfix"></div>
				<div class="grid1_3 margin_top_10 fl">
					<!-- 		Para todos la duración será de 30 días -->

					<label for="idDuracion">Duración</label> <input
						type="text" id="idDuracion" name="idDuracion" disabled="true"
						value="30 días" size="150" maxlength="15" trim="true"
						style="width: 290px" dojoType="dijit.form.ValidationTextBox"/>
				</div>
				<div class="grid1_3 margin_top_10 fl">
					<label for="idHorarioImparticion"><span>*</span> Días de
						Capacitación Efectivos</label> <select id="idHorarioImparticion"
						name="idHorarioImparticion" promptMessage="Dias" required="true"
						missingMessage="Debe indicar los días de la Capacitación Efectivos."
						value="${ofertaEmpleoForm.idHorarioImparticion}"
						autocomplete="false" dojotype="dijit.form.FilteringSelect">
<!-- 						por definir catalogo  -->
						<option value="1">20 días</option>
						<option value="2">24 días</option>						
					</select>
				</div>
				<div class="clearfix"></div>
				<div class="margin_top_20">
					<label for="numeroPlazasBecate"><span>*</span> Número de
						plazas</label> <input type="text" id="numeroPlazasBecate"
						name="numeroPlazasBecate"
						value="${ofertaEmpleoForm.numeroPlazasBecate}" size="150"
						maxlength="3" trim="true" style="width: 120px"
						dojoType="dijit.form.ValidationTextBox" required="true"
						missingMessage="Debe indicar el número de plazas."
						regExp="${regexpplazas}"
						invalidMessage="El número de plazas es inválido, favor de verificar" />
				</div>
				<div class="clearfix"></div>
				<div class="fl margin-r_20">
					<label for="idSalario"><span>*</span> Salario</label> <select
						id="idSalario" name="idSalario" promptMessage="Salario"
						required="true"
						missingMessage="Es necesario que indique el salario."
						value="${ofertaEmpleoForm.idSalario}" autocomplete="false"
						dojotype="dijit.form.FilteringSelect" store="salariosStore">
					</select>
				</div>
				<div class="clearfix"></div>
				<div class="fl margin-r_20">
					<label for="fechaInicioBecate"><span>*</span> Fecha Inicial</label>
					<input type="text" name="fechaInicioBecate" id="fechaInicioBecate"
						value="${ofertaEmpleoForm.fechaInicioBecate}"
						dojoType="dijit.form.DateTextBox" required="true" maxlength="10"  constraints="{datePattern:'dd/MM/yyyy'}"
						onChange="dijit.byId('fechaFinBecate').constraints.min = arguments[0]; dijit.byId('fechaFinBecate').disabled=false;"
						missingMessage="Es necesario que indique la fecha inicial."
						style="position: relative; left: 0px;" /><br>
					<br>
				</div>
				<div class="fl">
					<label for="fechaFinBecate"><span>*</span> Fecha Final</label> <input
						type="text" name="fechaFinBecate" id="fechaFinBecate"
						value="${ofertaEmpleoForm.fechaFinBecate}"
						dojoType="dijit.form.DateTextBox" required="true" size="10"
						missingMessage="Es necesario que indique la fecha final."
						style="position: relative; left: 0px;" /><br>
					<br>
				</div>
				<div class="clearfix"></div>

				<div class="grid1_3 fl">
					<div class="labeled">Horario</div>
					<div class="fl margin-r_20">
						<label for="idHorarioEntrada">Entrada</label> <select
							id="idHorarioEntrada" name="idHorarioEntrada"
							store="horarioCapacitacionStore" required="false"
							dojotype="dijit.form.FilteringSelect"
							value="${ofertaEmpleoForm.idHorarioEntrada}" autocomplete="true"
							style="width: 100px">
						</select>
					</div>
					<div class="fl">
						<label for="idHorarioSalida">Salida</label> <select
							id="idHorarioSalida" name="idHorarioSalida"
							store="horarioCapacitacionStore" required="false"
							dojotype="dijit.form.FilteringSelect"
							value="${ofertaEmpleoForm.idHorarioSalida}" autocomplete="true"
							style="width: 100px">
						</select>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="grid1_3 margin_top_10 fl">
					<label for="idTipoRecurso">Tipo de recurso</label> <select
						id="idTipoRecurso" name="idTipoRecurso" required="false"
						store="tipoRecurso" dojotype="dijit.form.FilteringSelect"
						value="${ofertaEmpleoForm.idTipoRecurso}" maxHeight="250">
					</select>
				</div>
				<div class="grid1_3 margin_top_10 fl">
					<label for="monto">Monto</label> <input type="text"  required="false"
						name="monto" id="monto" regExp="${regexpsalario}"
						invalidMessage="Cantidad invalida favor de verificar."
						value="${ofertaEmpleoForm.monto}" maxlength="9"
						style="width: 10em;" dojoType="dijit.form.ValidationTextBox" />
				</div>
				<div class="clearfix"></div>
			</fieldset>
		</c:if>

		<c:if test="${ofertaEmpleoForm.idOfertaEmpleo>0}">
			<div class="form_nav">
				<input type="button" value="Guardar" id="btnGuardar"
					onclick="doSubmit('editarOferta',this.id);" /> <input type="button"
					value="Cancelar" id="btnCancelar" onclick="doSubmitCancelar();" />
			</div>
		</c:if>
		<c:if test="${ofertaEmpleoForm.idOfertaEmpleo==0}">
			<div class="form_nav">
				<input type="button" id="btnContinuar" value="Continuar"
					onclick="doSubmit('toRequisitos',this.id);" />
			</div>
		</c:if>
	</form>
</body>
<c:if test="${ofertaEmpleoForm.idOfertaEmpleo>0}">
	<c:if test="${!ofertaEmpleoForm.datosValidosEdicion}">
		<script type="text/javascript">
			edicionValidadion();
		</script>
	</c:if>
</c:if>
<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function() {
			message('${ERROR_MSG}');
		});
	</script>
</c:if>
<%
	if (request.getSession().getAttribute("mensajeEdicion") != null
			&& request.getSession().getAttribute("mensajeEdicion") != "") {
		String mensaje = String.valueOf(request.getSession()
				.getAttribute("mensajeEdicion"));
%>
<script type="text/javascript">
		var input = '<input id="btnAct" class="boton_naranja" type="button" name="btnAct" onClick="enviarAMisOfertas();" value="Aceptar"/>';
		messageAcept('<%=mensaje%>', input);
	
	function messageAcept(msg, input) {
		var html = '<div id="messageDialgop2" name="messageDialgop2">'
				+ '<p style="text-align: center;">' + msg + '</p><br>'
				+ '<p class="form_nav">' + input + '</div>';
		dialogMsg = new dijit.Dialog({
			title : 'Mensaje',
			content : html,
			style : "width:300px;",
			showTitle : false,
			draggable : false,
			closable : false,
			hide : function() {
				clearWidgetsHtml('messageDialgop2');
				dialogMsg.destroy()
			}
		});
		dialogMsg.show();
	}
</script>
<%
		request.getSession().removeAttribute("mensajeEdicion");
	}
%>
<%
	if (request.getSession().getAttribute("alert4Admin") != null && request.getSession().getAttribute("alert4Admin") != "") {
		String msg = String.valueOf(request.getSession().getAttribute("alert4Admin"));
%>
		<script>message('<%=msg%>');</script>
<%
		request.getSession().removeAttribute("alert4Admin");
	}
%>
</html>