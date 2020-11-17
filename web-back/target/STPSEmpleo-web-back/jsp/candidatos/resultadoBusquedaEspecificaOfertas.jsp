
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="ISO-8859-1" %>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- <link href="${PATH_CSS_SWB}css_aplicacion/otras-bolsas.css" rel="stylesheet" type="text/css" />--%>
	<style type="text/css">
		.redFont{
			color: #FF0000;
		}
		.Font80{
			font-size: 80%;
		}
	</style> 
	<script src="https://maps.googleapis.com/maps/api/js"></script>  
	<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
	 
    <script type="text/javascript">
		var entidad="mexico";
		var domicilioMapa="";
	    dojo.require("dojo.NodeList-traverse");
    
	    function doSubmitPager(method) {
	  	  dojo.xhrPost({url: 'busquedaEspecificaOfertas.do?method='+method, form:'paginationForm', timeout:180000, 
			  load: function(data){
			    dojo.byId('tabla').innerHTML=data;
		  }});
	    }
	    
	    function doSubmitPagina(pagina, dif) {
    		dojo.xhrPost({url: 'busquedaEspecificaOfertas.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
				load: function(dataCand){
    	 			dojo.byId('tabla').innerHTML=dataCand;
			}});
    	 }
	    
	    /* Ordena tabla por columna */
		function orderBy(orderType, columnNumber){
			dojo.xhrPost({url: 'busquedaEspecificaOfertas.do?method=orderByColumn&orderType=' + orderType + '&columnNumber=' + columnNumber, timeout:180000, 
			  load: function(jobOffersData){
			      dojo.byId('tabla').innerHTML = jobOffersData;
			      // TODO: Change to Dojo methods
			      if(columnNumber == '1' && orderType == 'asc'){
						document.getElementById("jobOfferNameAsc").style.display = 'none';
				  		document.getElementById("jobOfferNameDesc").style.display = 'inline';
				  }else if(columnNumber == '1' && orderType == 'desc'){
				  		document.getElementById("jobOfferNameDesc").style.display = 'none';
				  		document.getElementById("jobOfferNameAsc").style.display = 'inline';
				  }else if(columnNumber == '2' && orderType == 'asc'){
				  		document.getElementById("locationAsc").style.display = 'none';
				  		document.getElementById("locationDesc").style.display = 'inline';
				  		entidad=document.getElementById("locationDesc").value;
			  	  }else if(columnNumber == '2' && orderType == 'desc'){
				  		document.getElementById("locationDesc").style.display = 'none';
				  		document.getElementById("locationAsc").style.display = 'inline';
				  		entidad=document.getElementById("locationAsc").value;
				  }else if(columnNumber == '3' && orderType == 'asc'){
				  		document.getElementById("enterpriseNameAsc").style.display = 'none';
				  		 document.getElementById("enterpriseNameDesc").style.display = 'inline';
				  }else if(columnNumber == '3' && orderType == 'desc'){
				  		document.getElementById("enterpriseNameDesc").style.display = 'none';
				  		document.getElementById("enterpriseNameAsc").style.display = 'inline';
				  }else if(columnNumber == '4' && orderType == 'asc'){
				  		document.getElementById("netSalaryAsc").style.display = 'none';
				  		document.getElementById("netSalaryDesc").style.display = 'inline';
				  }else if(columnNumber == '4' && orderType == 'desc'){
				 		document.getElementById("netSalaryDesc").style.display = 'none';
				  		document.getElementById("netSalaryAsc").style.display = 'inline';
				  }else if(columnNumber == '5' && orderType == 'asc'){
				  		document.getElementById("publicationDateAsc").style.display = 'none';
				  		document.getElementById("publicationDateDesc").style.display = 'inline';
				  }else if(columnNumber == '5' && orderType == 'desc'){
				  		document.getElementById("publicationDateDesc").style.display = 'none';
				  		document.getElementById("publicationDateAsc").style.display = 'inline';
				  }
			  }});
		 }
	    
		function toggleTr(evt){
			var target = dojo.query(event.currentTarget); // list node which dispatched the action
			var parent = target.closest("tr"); // <tr> list
			var sibling = parent.next(); // <tr> sibling list
			var nodeWanted = sibling.children().children("div")[0]; // <div> node
			
					    
		    if(   dojo.style(nodeWanted, "display") == "none"){
		        dojo.style(nodeWanted, "display", "block");
		    } else {
		        dojo.style(nodeWanted, "display", "none");
		    }
		}
		
		function gotoJobOffer(evt) {
			// TODO: Implement method
		}
	    
   
var myCenter=new google.maps.LatLng(51.508742,-0.120850);

function domicilio(calle,entidad) {
	document.getElementById("searchForm").submit();
	domicilioMapa=calle.concat(" ",entidad);
    console.log(domicilioMapa);
    initializeLink();
    
  }

<%@ page import="java.util.List" %>
    
   
var geocoder = new google.maps.Geocoder();
var codigosLista= [];
var arr_limpio = [];
var val_eliminados = [];
var conteos=[];
var bounds = new google.maps.LatLngBounds();
var generateIconCache = {}; 
var estado;
var lat=0,longitud=0;

function validaMapa(){
	var valida=	document.getElementById('validaMap').value;
	var validaRes=document.getElementById('codigos').value;
	validaRes=validaRes.replace("[","");
	validaRes=validaRes.replace("]","");
	if (valida=="false" || validaRes.length==0){
		var div = document.getElementById('map');
		
		div.parentNode.removeChild(div);
		
	}
}
function llenarCodigos(){
	
	codigosLista=document.getElementById('codigos').value;
	codigosLista=codigosLista.replace("[","");
	codigosLista=codigosLista.replace("]","");
	codigosLista = codigosLista.split(",");
	
}
function ContarRepetidos(valores) {
	obj = codigosLista.EliminarRepetidos();
	for (var a=0; a<arr_limpio.length; a++){
	conteos[a]=0;
	for (var i=0; i<codigosLista.length; i++){
		
		if (codigosLista[i]==arr_limpio[a]){
		conteos[a]++;
		}

		}
		}

	}
	

Array.prototype.EliminarRepetidos = function () {
var cantidad = {};
for(var i = 0; i < this.length; i++){
if(!(this[i] in cantidad)) {
cantidad[this[i]] = 0;
arr_limpio.push(this[i]); 
cantidad[this[i]]++; 
} else {
val_eliminados.push(this[i]);
cantidad[this[i]]++; 
}
}

return(cantidad)
}

function catalogoCP(cod){
	if (cod.substr(0,1)==0 || cod.substr(0,1)==1){
	estado="Mexico";
	} else {
		var digito= cod.substr(0,2);
		 if (digito>=44 && digito<=49){
		 estado="Jalisco";
		 }
			if (digito==20){
		 estado="Aguascalientes";
		 }	
		 if (digito>=21 && digito<=23){
		 estado="Baja California";
		 }	
		 if (digito==24){
		 estado="Mexico";
		 }	
		 if (digito>=25 && digito<=27){
		 estado="Coahuila";
		 }	
		 if (digito==28){
		 estado="Colima";
		 }	
		 if (digito>=29 && digito<=30){
		 estado="Chiapas";
		 }	
		 
		 if (digito>=31 && digito<=33){
		 estado="Chihuahua";
		 }	
		 if (digito>=34 && digito<=35){
		 estado="Durango";
		 }	
		 if (digito>=36 && digito<=38){
		 estado="Guanajuato";
		 }	
		 if (digito>=39 && digito<=41){
		 estado="Guerrero";
		 }	
		 if (digito>=42 && digito<=43){
		 estado="Hidalgo";
		 }	
			if (digito>=50 && digito<=57){
		 estado="Mexico";
		 }	
			if (digito>=58 && digito<=61){
		 estado="Michoacan";
		 }
			if (digito==62){
		 estado="Morelos";
		 }
			if (digito==63){
		 estado="Nayarit";
		 }
				if (digito>=64 && digito<=67){
		 estado="Nuevo Leon";
		 }
					if (digito>=68 && digito<=71){
		 estado="Oaxaca";
		 }
						if (digito>=72 && digito<=75){
		 estado="Puebla";
		 }
		 if (digito==76){
		 estado="Queretaro";
		 }	
		 if (digito==77){
		 estado="Quintana Roo";
		 }	
							if (digito>=78 && digito<=79){
		 estado="San Luis Potosi";
		 }
		 if (digito>=80 && digito<=82){
		 estado="Sinaloa";
		 }
				if (digito>=83 && digito<=85){
		 estado="Sonora";
		 }
		 if (digito==86){
		 estado="Tabasco";
		 }	

				if (digito>=87 && digito<=89){
		 estado="Tamaulipas";
		 }
		 if (digito==90){
		 estado="Tlaxcala";
		 }	
			if (digito>=91 && digito<=96){
		 estado="Veracruz";
		 }
		 if (digito==97){
		 estado="Yucatan";
		 }	
				if (digito>=98 && digito<=99){
		 estado="Zacatecas";
		 }
	}

	}



function initialize()
{
	for (var b=0; b<codigosLista.length; b++){
		var codigoEspacio=codigosLista[b];
		codigosLista[b]=codigoEspacio.trim();
		
		 }
	
	validaMapa();
	llenarCodigos();	
	ContarRepetidos();
	var marker;
	var infowindow = new google.maps.InfoWindow();
	
	
	var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 10,
    
  });
var a=0,b=0;
for (i = 0; i < arr_limpio.length; i++) {  
	catalogoCP(arr_limpio[i]);
	geocoder.geocode({'address': estado+" "+arr_limpio[i]}, function(results,status) {
		if (status == google.maps.GeocoderStatus.OK) {
  
	   map.setCenter(results[0].geometry.location);
	   lat=results[0].geometry.location.lat();
	   longitud=results[0].geometry.location.lng();
       generateIcon(conteos[a], function(src) {
		a++;
		  var pos = new google.maps.LatLng(lat, longitud);
		
	  	bounds.extend(pos);
	
		  marker= new google.maps.Marker({
		  	position: pos,
			  map: map,
			  icon: src
		  });
		  google.maps.event.addListener(marker, 'click', (function(marker, i) {
          return function() {
        	  infowindow.setContent(arr_limpio[b]);
              infowindow.open(map, marker);
    			document.getElementById('cod').value =arr_limpio[b].toString();
    			document.getElementById("searchForm").submit();
    			initialize();
    			
    			
			
          }
        })(marker, i));
		});
		
		b=b+1;
		}
	});
	map.fitBounds(bounds);
	}
	}
 
function generateIcon(number, callback) {
	  if (generateIconCache[number] !== undefined) {
	    callback(generateIconCache[number]);
	  }

	  var fontSize = 16,
	    imageWidth = imageHeight = 35;

	  if (number >= 1000) {
	    fontSize = 10;
	    imageWidth = imageHeight = 55;
	  } else if (number < 1000 && number > 100) {
	    fontSize = 14;
	    imageWidth = imageHeight = 45;
	  }

	  var svg = d3.select(document.createElement('div')).append('svg')
	    .attr('viewBox', '0 0 54.4 54.4')
	    .attr('width', imageWidth+'px')
	    .attr('height', imageHeight+'px')
	    .append('g')

	  var circles = svg.append('circle')
	    .attr('cx', '27.2')
	    .attr('cy', '27.2')
	    .attr('r', '21.2')
	    .style('fill', '#38C243');

	  var path = svg.append('path')
	    .attr('d', 'M27.2,0C12.2,0,0,12.2,0,27.2s12.2,27.2,27.2,27.2s27.2-12.2,27.2-27.2S42.2,0,27.2,0z M6,27.2 C6,15.5,15.5,6,27.2,6s21.2,9.5,21.2,21.2c0,11.7-9.5,21.2-21.2,21.2S6,38.9,6,27.2z')
	    .attr('fill', '#FFFFFF');

	  var text = svg.append('text')
	    .attr('dx', 27)
	    .attr('dy', 32)
	    .attr('text-anchor', 'middle')
	    .attr('style', 'font-size:' + fontSize + 'px; fill: #FFFFFF; font-family: Arial, Verdana; font-weight: bold')
	    .text(number);

	  var svgNode = svg.node().parentNode.cloneNode(true),
	    image = new Image();

	  d3.select(svgNode).select('clippath').remove();

	  var xmlSource = (new XMLSerializer()).serializeToString(svgNode);

	  image.onload = (function(imageWidth, imageHeight) {
	    var canvas = document.createElement('canvas'),
	      context = canvas.getContext('2d'),
	      dataURL;

	    d3.select(canvas)
	      .attr('width', imageWidth)
	      .attr('height', imageHeight);

	    context.drawImage(image, 0, 0, imageWidth, imageHeight);

	    dataURL = canvas.toDataURL();
	    generateIconCache[number] = dataURL;

	    callback(dataURL);
	  }).bind(this, imageWidth, imageHeight);

	  image.src = 'data:image/svg+xml;base64,' + btoa(encodeURIComponent(xmlSource).replace(/%([0-9A-F]{2})/g, function(match, p1) {
	    return String.fromCharCode('0x' + p1);
	  }));
	}


function initializeLink()
{

var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 8,
    center: {lat: -34.397, lng: 150.644}
  });
  var geocoder = new google.maps.Geocoder();
  geocodeAddress(geocoder, map);
  document.getElementById('submit').addEventListener('click', function() {
  geocodeAddress(geocoder, map);
    
    
    	 
  });
}


function geocodeAddress(geocoder, resultsMap) {
  var address = domicilioMapa;
  geocoder.geocode({'address': address}, function(results, status) {
    if (status === google.maps.GeocoderStatus.OK) {
      resultsMap.setCenter(results[0].geometry.location);
      var marker = new google.maps.Marker({
        map: resultsMap,
        position: results[0].geometry.location
      });
    } else {
      alert('Geocode was not successful for the following reason: ' + status);
    }
  });
}


google.maps.event.addDomListener(window, 'load', initialize);

</script>
	<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
		<div id="cont-resultados" class="contenedor_contenido">
		
			<html:messages id="errors">
				<span class="redFont Font80"><bean:write name="errors"/></span><br/>
			</html:messages>
			<html:messages id="messages" message="true">
				<span class="Font80"><bean:write name="messages"/></span><br/>
			</html:messages>
			<form id="searchForm" name="searchForm" class="formApp" method="post" action="<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=encontrar">
			<div class="form-group">
								
								<div class="input-group">
									<input type="hidden" id="cod" class="form-control" name="codigoPostal" value="" dojoType="dijit.form.NumberTextBox" constraints="{min:15, max:100, places:0, pattern:'##'}" /> 
									
								</div>
							</div>
			</form>
			<logic:equal name="BusquedaEspecificaOfertasForm" property="candidateLogged" value="true">
				<span class="entero">
					<a href="<%=request.getContextPath()%>/miEspacioCandidato.do">Regresar a mi espacio</a>
				</span>
			</logic:equal>

<div class="row">
	<div class="col-sm-12">
		<div class="page-header">
			<h3>Resultados de b&uacute;squeda espec&iacute;fica de ofertas </h3>
		</div>
	</div>
	<div class="col-sm-12">
		<bean:define name="BusquedaEspecificaOfertasForm" property="jobOffersSize" id="searchSize"/>
		<logic:equal name="searchSize" value="0">
			<bean:define id="p_class" value="alerta"/>
		</logic:equal>
		<p id="busqueda_concepto" class="${p_class}">
			<logic:equal name="searchSize" value="0">
				<span class="label label-warning">No se encontraron ofertas para la b&#250;squeda:</span>
			</logic:equal>
			<logic:notEqual name="searchSize" value="0">
				<span class="label label-success">
					<bean:write name="totalNumeroPlazas"/>
				
				plazas disponibles de</span> 
					<bean:write name="searchSize"/>
					
				 resultados encontrados para:
			</logic:notEqual>
			<strong class="c_naranja">
			<bean:write name="BusquedaEspecificaOfertasForm" property="selectedFilters" filter="false"/>
			<input type="hidden" id="codigos" name="codigos" value="<%=request.getSession(false).getAttribute("CODIGOS")%>">
			<input type="hidden" id="validaMap" name="validaMap" value="<%=request.getSession(false).getAttribute("FILTRO_GEO")%>">
			<input type="hidden" id="validaMapRes" name="validaMapRes" value="<bean:define name="BusquedaEspecificaOfertasForm" property="jobOffersSize" id="searchSize"/>">
			</strong>
			
		</p>
		
		<div id="map" style="width:100%;height:400px;" align="center"></div>
		</br>
		<div class="form_nav text-center">
			<div class="form-group">
				<a href="<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=buscar" class="btnPE" id="nueva-busqueda">Nueva&nbsp;b&uacute;squeda</a>
			</div>
		</div>
		
	</div>
	

<div id="demo" ></div>

	<div class="clearfix"></div>
	<div class="col-sm-12">
		<div id="tabla">
			<!-- The content will be generated via AJAX -->
		</div>
		
	</div>
</div>
			
			
			

			

			
			
			
<%-- 			<a href="<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=buscar" class="boton_naranja nueva-busqueda" id="nueva-busqueda">Nueva&nbsp;b&uacute;squeda</a> --%>
			
			<form name="paginationForm" id="paginationForm" method="post" action="busquedaEspecificaOfertas.do" dojoType="dijit.form.Form">
				<input type="hidden" name="method" id="method" value="encontrar"/> <!-- indica el metodo a invocar -->
				<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
			</form>

		</div><!-- cont-resultados -->
		
	


<script>
	doSubmitPager('page');
</script>
