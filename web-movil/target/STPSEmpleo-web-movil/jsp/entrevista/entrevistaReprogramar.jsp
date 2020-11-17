<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%String context = request.getContextPath() +"/";%>
<html>
	<head>
		
		<link href="images/portal-movil.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/dojo/1.7/dojo/dojo.js" data-dojo-config="parseOnLoad: true, isDebug: true, locale: 'es'" ,></script>
        <script type="text/javascript" src="js/dateMobile.js"></script>
		<link href="http://download.dojotoolkit.org/release-1.7.0b2/dojo-release-1.7.0b2/dojox/mobile/themes/common/SpinWheel.css" rel="stylesheet">
		
	

	<script language="JavaScript" type="text/javascript">
		require([
			"dojox/mobile",
			"dojo/date/locale",// This is a mobile app.
			"dojox/mobile/compat",		// This mobile app supports running on desktop browsers
			"dojox/mobile/parser",		// This mobile app uses declarative programming with fast mobile parser
			"dojox/mobile/Tooltip",
			"dojox/mobile/Button",
			"dojox/mobile/SpinWheelTimePicker"
			
		]);

		function acetpar(){
			var w = dijit.byId("spin1");
				var date;
				try {
					date = w.slots[0].getValue()+ "-" + w.slots[1].getValue() + "-" + w.slots[2].getValue();
				} catch (e) {
					return;
				}
				var myDate = new Date(date);  
				var month = myDate.getMonth()+1;
				if (month<10)month = "0"+month;
				document.getElementById("fecha").value=myDate.getDate()+"/"+month+"/"+myDate.getFullYear();
				document.getElementById("fechaEntrevista").value=myDate.getDate()+"/"+month+"/"+myDate.getFullYear();
				
				dijit.byId('calPicker').hide(true);
			}
		
		function showCal(e){
			dijit.byId("timePicker").hide(true);
			var w = dijit.byId("spin1");
			var fecha = document.getElementById("fecha").value;
			var elem = fecha.split('/');
			var now = new Date(elem[1]+"-"+elem[0]+"-"+elem[2]);
			var monthStr = dojo.date.locale.format(now, {datePattern:"MMM", selector:"date"});
			w.setValue([now.getDate(), monthStr, now.getFullYear()]);
			dijit.byId('calPicker').show(e, ['above-centered','below-centered','after','before']);
			
		}
		
		function acetparTime(){
			var w = dijit.byId("spin2");
				var hora;
				try {
					hora = w.slots[0].getValue()+ ":" + w.slots[1].getValue();
				} catch (e) {
					return;
				}
				document.getElementById("hora").value=hora;
				document.getElementById("horaEntrevista").value=hora;
				
				dijit.byId('timePicker').hide(true);
			}
		
		function showTimer(e){
			dijit.byId("calPicker").hide(true);
			var w = dijit.byId("spin2");
			var hora = document.getElementById("hora").value;
			var elem = hora.split(':');
			w.setValue([elem[0],elem[1]]);
			dijit.byId('timePicker').show(e, ['above-centered','below-centered','after','before']);
			
		}
		
		
		function logout(){
			
			document.location = "<%=request.getContextPath()%>/logout.m";
			
		}
		
		function doSubmit(){
			if(validar72hr()){
			document.entrevistaEmpresaForm.method.value='reprogramar';
			document.entrevistaEmpresaForm.submit();
			}
		}
		
		  var formatDate = function (formatDate, formatString) {
				if(formatDate instanceof Date) {
					var months = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
					var yyyy = formatDate.getFullYear();
					var yy = yyyy.toString().substring(2);
					var m = formatDate.getMonth() + 1;
					var mm = m < 10 ? "0" + m : m;
					var mmm = months[m];
					var d = formatDate.getDate();
					var dd = d < 10 ? "0" + d : d;
					
					var h = formatDate.getHours();
					var hh = h < 10 ? "0" + h : h;
					var n = formatDate.getMinutes();
					var nn = n < 10 ? "0" + n : n;
					var s = formatDate.getSeconds();
					var ss = s < 10 ? "0" + s : s;

					formatString = formatString.replace(/yyyy/i, yyyy);
					formatString = formatString.replace(/yy/i, yy);
					formatString = formatString.replace(/mmm/i, mmm);
					formatString = formatString.replace(/mm/i, mm);
					formatString = formatString.replace(/m/i, m);
					formatString = formatString.replace(/dd/i, dd);
					formatString = formatString.replace(/d/i, d);
					formatString = formatString.replace(/hh/i, hh);
					formatString = formatString.replace(/h/i, h);
					formatString = formatString.replace(/nn/i, nn);
					formatString = formatString.replace(/n/i, n);
					formatString = formatString.replace(/ss/i, ss);
					formatString = formatString.replace(/s/i, s);

					return formatString;
					} else {
						return "";
					}
			    };
		
	    function validar72hr(){    
	        var Hr72 = 3;
	    	var fecha72 = new Date();
	    		fecha72.setDate(fecha72.getDate() + Hr72 );
	    	var resultado72	= formatDate(fecha72,'dd/mm/yyyy');
	    	var horaArreglo = document.getElementById("hora").value.split(':');
	    	var horaBox 	= horaArreglo[0]+horaArreglo[1];
	    	var horaActual 	= formatDate(fecha72,'hhnn');
	    	    if(resultado72 == document.getElementById('fecha').value){    		
	    		    	if(horaBox > horaActual){		    	      	  
	    		    	   return true;   	 
	    		    	}else{
	    		    	 	alert('La fecha de entrevista deben ser mayores a 72 horas naturales con respecto a la fecha actual.');	
	    		    		return false;
	    		    	}
	        	}else{
	        		return true;
	        	}
	    	}
		
	</script>
	
	<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36829222-1']);
  _gaq.push(['_setDomainName', 'empleo.gob.mx']);
  _gaq.push(['_setAllowLinker', true]);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
	
</head>
<body>
	

	<div id="entrevista" dojoType="dojox.mobile.View" selected="true">
	
	<form id="entrevistaEmpresaForm" name="entrevistaEmpresaForm" method="post" action="entrevistaProgramada.m">
		<input type="hidden" id="method" name="method" value="init">
		<input type="hidden" id="idEntrevista" name="idEntrevista" value="${entrevistaProgramadaForm.idEntrevista}">
		<input type="hidden" id="tituloTipo" name="tituloTipo" value="${entrevistaProgramadaForm.tituloTipo}">
		<input type="hidden" id="idCandidato" name="idCandidato" value="${entrevistaProgramadaForm.idCandidato}">
		<input type="hidden" id="fechaEntrevista" name="fechaEntrevista" value="">
		<input type="hidden" id="horaEntrevista" name="horaEntrevista" value="">
	</form>
		<h1 dojoType="dojox.mobile.Heading">
				<div dojoType="dojox.mobile.ToolBarButton" class="mblToolbarButton mblArrowButtonText mblColorDefault" icon="images/ico_rwd.jpg" moveTo="bar" onclick="history.go(-1)" transitionDir="-1"></div>
				<div dojoType="dojox.mobile.ToolBarButton" class="mblToolbarButton mblArrowButtonText mblColorDefault" icon="images/ico_exit.jpg" style="width:45px;float:right;" onclick="logout()"></div>
		</h1>
			
			<h2 dojoType="dojox.mobile.RoundRectCategory">Espacio para Empresas</h2>
			<div dojoType="dojox.mobile.RoundRect" >
				<div class="mblVariableHeight" style="font-size: small;">
					<jsp:include page="/empresaEspacio.m" flush="true">
						<jsp:param name="method" value="detalleEmpresa" />
					</jsp:include>
				</div>
			</div>
		<div dojoType="dojox.mobile.RoundRect" >
			<div style="text-align: center;" class="titNar">Reprogramar entrevista</div>
			<div class="mblVariableHeight" style="font-size: small;">
				<b>Oferta:</b>    ${entrevistaProgramadaForm.tituloOferta}<br/>
				<b>Candidato:</b> ${entrevistaProgramadaForm.nombreCandidato}<br/>
				<b>Fecha:</b>     <input type="text" id="fecha" name="fechaEntrevista" readOnly value="${entrevistaProgramadaForm.fechaEntrevista}" onclick="showCal(this)" ><br/>
				<b>Hora:</b>      <input type="text" id="hora" name="horaEntrevista" readOnly value="${entrevistaProgramadaForm.horaEntrevista}" onclick="showTimer(this)">
			</div>
		</div>
	
	
	<div id="calPicker" data-dojo-type="dojox.mobile.Tooltip" >
		<h1 dojoType="dojox.mobile.Heading" label="Fecha">
			<div dojoType="dojox.mobile.ToolBarButton" label="Done"  class="mblToolBarButtonToolTip" style="position:absolute;width:45px;right:0;"
				onclick="acetpar();"></div>
			<div dojoType="dojox.mobile.ToolBarButton" label="Cancel" class="mblToolBarButtonToolTip" style="position:absolute;width:45px;left:0;"
				onclick="dijit.byId('calPicker').hide(true)"></div>
		</h1>
	<div id="spin1" dojoType="dojox.mobile.SpinWheelDatePicker"></div>
		
	</div>
		<div id="timePicker" data-dojo-type="dojox.mobile.Tooltip">
		<h1 dojoType="dojox.mobile.Heading" label="Hora">
			<div dojoType="dojox.mobile.ToolBarButton" label="Done" class="mblToolBarButtonToolTip" style="position:absolute;width:45px;right:0;"
				onclick="acetparTime();"></div>
			<div dojoType="dojox.mobile.ToolBarButton" label="Cancel" class="mblToolBarButtonToolTip" style="position:absolute;width:45px;left:0;"
				onclick="dijit.byId('timePicker').hide(true)"></div>
		</h1>
	<div id="spin2" dojoType="dojox.mobile.SpinWheelTimePicker"></div>
		
	</div>

		<div id="listaAcciones" dojoType="dojox.mobile.View" selected="true" style="text-align: center;">
			<button id="btn1" dojoType="dojox.mobile.Button" class="mblButton mblBlueButton" style="width:120px" onclick="javascript:history.go(-1);">Cancelar</button>
			<button id="btn2" dojoType="dojox.mobile.Button" class="mblButton mblBlueButton" style="width:120px"  onclick="javascript:doSubmit();">Aceptar</button>
			
		</div>
		
		<div id="bar" dojoType="dojox.mobile.View"></div>

</div>
</body>
</html>