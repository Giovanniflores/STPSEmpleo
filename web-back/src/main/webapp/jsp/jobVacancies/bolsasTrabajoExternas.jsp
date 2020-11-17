<%@ page import="java.util.List,
	mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO,
	mx.gob.stps.portal.web.infra.utils.Constantes,
	mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO,
	java.util.ArrayList;"
		 language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String context = request.getContextPath() +"/";
	pageContext.setAttribute("context", context);
%>

<link href="${PATH_CSS_SWB}css_aplicacion/estilos_tabla.css" rel="stylesheet" type="text/css" />

<!--  detectar internet explorer -->
<!--[if lt IE 7 ]> <html class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class=""> <!--<![endif]-->

<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="js/misDatos/messagesValidate.js"></script>
<script type="text/javascript" src="googiespell/AJS.js"></script>
<script type="text/javascript" src="googiespell/googiespell.js"></script>
<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
<link   href="googiespell/googiespell.css" rel="stylesheet" type="text/css" />

<style type="text/css">
#OfertasPorParametrosForm .escolaridad .dijitReset.dijitInputField.dijitButtonText {
	width: 46px;
}
</style>
<%
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
	request.getSession().setAttribute("entidad1", entidad1);
%>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.RadioButton");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.FilteringSelect");

	function doSubmit(method){
		var selectRadio;
		if(validaRadioButton()){
			// TODO: Change logic
//			for(i=1; i<=10; i++){//usca el valor del RadioButton seleccionado
			dojo.query("input[type=radio]").forEach(function(e){
//				var radioButton = dijit.byId("r" + i);
				var radioButton = dijit.byId(e.getAttribute("id"));
				if(radioButton.attr('checked')){
					selectRadio = radioButton.get('value');
				}
			});//Ofertas de la administración pública
			if(selectRadio == "8"){
				//OCULTAR EN QA if(dijit.byId('bolsasTrabajoForm').isValid() && dijit.byId('ocupacionr').get('item')!=null && dijit.byId('ocupacionr').get('item').value != '-1'){
//			if(dijit.byId('bolsasTrabajoForm').isValid()){
				document.bolsasTrabajoForm.method.value='buscarSFP';
				document.bolsasTrabajoForm.busquedaen.value = selectRadio;
				//OCULTAR EN QA document.bolsasTrabajoForm.idOcupacion.value = dijit.byId('ocupacionr').get('item').value;
				document.bolsasTrabajoForm.submit();
//			}else {
//				if (!dijit.byId('palabra').isValid()){
//					displayErrorMsg(dijit.byId('palabra'), dijit.byId('palabra').get("missingMessage"));
//				}
//
//				if (!dijit.byId('entidadFederativaSelect2').isValid()){
//					displayErrorMsg(dijit.byId('entidadFederativaSelect2'), dijit.byId('entidadFederativaSelect2').get("missingMessage"));
//				}
//			}
			} else if (selectRadio == "5") {	// Hispavista
				document.bolsasTrabajoForm.method.value = 'buscarVacantesHispavista';
				document.getElementById('hispavistaState').value = dijit.byId('hispavistaCountryStatesSelect').get('item').name;
				document.getElementById('hispavistaIdState').value = dijit.byId('hispavistaCountryStatesSelect').get('item').value;
//				document.getElementById('hispavistaIdArea').value = dijit.byId('hispavistaAreasSelect').get('item').value;
//				document.getElementById('hispavistaIdProfession').value = dijit.byId('hispavistaAreaProfessionsSelect').get('item').value;
				document.bolsasTrabajoForm.busquedaen.value = selectRadio;
				document.bolsasTrabajoForm.submit();
			} else{
				if(selectRadio == "9"){
					document.bolsasTrabajoForm.method.value='buscarVacantesDisyEmp';
					document.bolsasTrabajoForm.busquedaen.value = selectRadio;
					document.bolsasTrabajoForm.submit();
				}
				else{//Ofertas externas
//				if(dijit.byId('bolsasTrabajoForm').isValid()&& dijit.byId('palabra').value!=''
					// TODO: Just valid visible fields
					if( dijit.byId('palabra').value != ''
							&& dijit.byId('palabra').value != null
							&& dijit.byId('fechaAlta').get('item') != null ) {
						document.bolsasTrabajoForm.method.value = method;
						document.bolsasTrabajoForm.busquedaen.value = selectRadio;
						document.bolsasTrabajoForm.fecha.value = dijit.byId('fechaAlta').get('item').value;
						document.bolsasTrabajoForm.submit();
					}else {
						if (!dijit.byId('palabra').isValid()){
							displayErrorMsg(dijit.byId('palabra'), dijit.byId('palabra').get("missingMessage"));
						}

						if (!dijit.byId('entidadFederativaSelect2').isValid()){
							displayErrorMsg(dijit.byId('entidadFederativaSelect2'), dijit.byId('entidadFederativaSelect2').get("missingMessage"));
						}
					}
				}
			}
		}else {
			alert("Seleccione alguna de las bolsas de trabajo");
		}
	}

	function validaRadioButton(){
		var count = 0;
//		for(i=1; i<=10; i++){
		dojo.query("input[type=radio]").forEach(function(e){
			var radioButton = dijit.byId(e.getAttribute("id"));
//			var radioButton = dijit.byId("r" + i);
			if(radioButton.attr('checked')){
				count++;
			}
		});

		if(count == 1)
			return true;
		else
			return false;
	}

	function doSubmitx(method){
		document.bolsasTrabajoForm.method.value=method;
		document.bolsasTrabajoForm.submit();
	}

	function inhabilitar(valor){
		console.log(valor);

		if (valor == false) {
			return;
		}

		if (valor != "5") {	// No Hispavista
			console.log("NO Hispavista " + valor);

			dojo.attr(dojo.byId("hispavista"), "class" ,"hide");	// add class
			dojo.attr(dojo.byId("otras_bolsas"), "class" ,"");	// remove class

			dijit.byId('fechaAlta').setDisabled(false);
			dijit.byId('entidadFederativaSelect2').setDisabled(false);
		}

		//console.log(valor);
		if (valor == "5") {	// Hispavista
			dijit.byId('palabra').reset();
			dijit.byId('palabra').setDisabled(false);
			dijit.byId('fechaAlta').setDisabled(true);
			dijit.byId('entidadFederativaSelect2').setDisabled(true);

			dojo.attr(dojo.byId("hispavista"), "class" ,"");	// remove class
			dojo.attr(dojo.byId("otras_bolsas"), "class" ,"hide");	// add class

			dojo.byId('avisoSinFiltros').style.display='none';
		} else if(valor == "8"){	// FSP
			dijit.byId('palabra').reset();
			dijit.byId('entidadFederativaSelect2').reset();
			dijit.byId('fechaAlta').reset();

			dijit.byId('palabra').setDisabled(true);
			dijit.byId('entidadFederativaSelect2').setDisabled(true);
			dijit.byId('fechaAlta').setDisabled(true);

			dojo.byId('avisoSinFiltros').style.display='block';
		} else if(valor == "9"){
			dijit.byId('palabra').reset();
			dijit.byId('entidadFederativaSelect2').reset();
			dijit.byId('fechaAlta').reset();

			dijit.byId('palabra').setDisabled(true);
			dijit.byId('entidadFederativaSelect2').setDisabled(true);
			dijit.byId('fechaAlta').setDisabled(true);

			dojo.byId('avisoSinFiltros').style.display='block';

		} else if(valor != "8" && valor != false){
			dijit.byId('palabra').setDisabled(false);
			dijit.byId('entidadFederativaSelect2').setDisabled(false);

			if(valor == "4"){ // Adecco
				dijit.byId('fechaAlta').reset();
				dijit.byId('fechaAlta').setDisabled(true);
				dojo.byId('fecha_alta').style.display='none';	// hide
			} else {
				dijit.byId('fechaAlta').setDisabled(false);
				dojo.byId('fecha_alta').style.display='block';	// show
			}

			dojo.byId('avisoSinFiltros').style.display='none';
		}

	}

	function displayErrorMsg(textBox, errmsg){
		var originalValidator = textBox.validator;
		textBox.validator = function() {return false;};
		textBox.validate();
		textBox.validator = originalValidator;

		textBox.focus();

		dijit.showTooltip(
				//textBox.get("invalidMessage"),
				errmsg,
				textBox.domNode,
				textBox.get("tooltipPosition"),
				!textBox.isLeftToRight()
		);
	}

	dojo.addOnLoad(function() {

		var entidadV = ${entidad1.entidad};
		entidadFederativaStore2.url = "${context}domicilio.do?method=obtenerEntidad";
		entidadFederativaStore2.close();
		entidadFederativaStore2.fetch({
			onComplete: function(items, request) {
				var entidadV = ${entidad1.entidad};
				if (items.length == 0) return;
				dijit.byId('entidadFederativaSelect2').attr('value', entidadV);
				document.registroUbicacionFormBean.prefiereCandidatosEntidad.value=dijit.byId('entidadFederativaSelect2').get('value');
			}
		});

		//  Read hispavistaCountryStates via ajax
		hispavistaCountryStatesStore.url = "${context}bolsasTrabajo.do?method=obtainHispavistaCountryStatesViaAjax";
		hispavistaCountryStatesStore.close();
		hispavistaCountryStatesStore.fetch({
			onComplete: function(items, request) {
				if (items.length == 0) {
					return;
				}
//			dijit.byId('hispavistaCountryStatesSelect').attr('value', entidadV);
//			document.registroUbicacionFormBean.prefiereCandidatosEntidad.value=dijit.byId('entidadFederativaSelect2').get('value');
			}
		});

		//  Read hispavistaAreas via ajax
		hispavistaAreasStore.url = "${context}bolsasTrabajo.do?method=obtainHispavistaAreasViaAjax";
		hispavistaAreasStore.close();
		hispavistaAreasStore.fetch({
			onComplete: function(items, request) {
				if (items.length == 0) {
					return;
				}
//			dijit.byId('hispavistaCountryStatesSelect').attr('value', entidadV);
//			document.registroUbicacionFormBean.prefiereCandidatosEntidad.value=dijit.byId('entidadFederativaSelect2').get('value');
			}
		});

		dijit.byId("hispavistaAreasSelect").on('change', function(evt){
			//  Read hispavistaAreas via ajax
			var idArea = dijit.byId('hispavistaAreasSelect').get('value');
			hispavistaAreaProfessionsStore.url = "${context}bolsasTrabajo.do?method=obtainHispavistaAreaProfessionsViaAjax&hispavistaIdArea=" + idArea;
			hispavistaAreaProfessionsStore.close();
			hispavistaAreaProfessionsStore.fetch({
				onComplete: function(items, request) {
					if (items.length == 0) {
						return;
					}
					dijit.byId('hispavistaAreaProfessionsSelect').attr('value', '');
//			document.registroUbicacionFormBean.prefiereCandidatosEntidad.value=dijit.byId('entidadFederativaSelect2').get('value');
				}
			});
		});
	});

</script>
<style type="text/css">
	.oculto {
	   position: absolute !important;
	   top: -9999px !important;
	   left: -9999px !important;
	}
	#busquedas {
		padding: 40px 0 0 20px;
	}
	#busquedas li {
		list-style: none;
		padding: 0;
	}
	#busquedas label span {
		color: #666666;
	    display: block;
	    font-weight: normal;
	    height: 50px;
	    padding: 0 0 10px 25px;
	}
	.claro .dijitSelect, .claro .dijitSelect .dijitButtonContents, .claro .dijitTextBox, .claro .dijitTextBox{
		background-color: #fff !important;
	    border: 1px solid #ccc !important;
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	    color: #555 !important;
	    display: block;
	    font-size: 14px !important;
	    height: 34px !important;
	    line-height: 1.42857 !important;
	    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
	    width: 100% !important;
	    -webkit-border-radius: 4px;
		-moz-border-radius: 4px;
		border-radius: 4px;
	}
	.claro .dijitSelect .dijitInputField, .claro .dijitTextBox .dijitInputField {
    	padding: 6px 6px 7px !important;
    	-webkit-border-top-right-radius: 7px;
		-webkit-border-bottom-right-radius: 7px;
		-moz-border-radius-topright: 7px;
		-moz-border-radius-bottomright: 7px;
		border-top-right-radius: 7px;
		border-bottom-right-radius: 7px;
	}
	.bolsasCampos > div {
		padding-bottom: 20px;
	}
	.botonBuscar input[type="button"] {
		background-color: #6e8d3a;
		border: 1px solid transparent;
    	color: #fff !important;
    	cursor: pointer;
    	font-weight: bold;
    	line-height: 12px;
    	padding: 7px 20px !important;
    	-webkit-border-radius: 4px;
		-moz-border-radius: 4px;
		border-radius: 4px;
	}
	#avisoSinFiltros.advertencias {
		background-color: #fefeef;
		background-image: url(images/arrow-down.png);
		background-position: center 109px;
    	background-repeat: no-repeat;
	    border: 1px solid #dfdfdf;
	    padding: 20px;
	    position: absolute;
	    top: 43px;
	    left: 272px;
	    text-align: center;
	    width: 297px;
		-webkit-border-radius: 4px;
		-moz-border-radius: 4px;
		border-radius: 4px;
		-webkit-box-shadow: 0 1px 5px rgba(0,0,0,0.4);
		-moz-box-shadow: 0 1px 5px rgba(0,0,0,0.4);
		box-shadow: 0 1px 5px rgba(0,0,0,0.4);
	}
	.hide {
		display: none;
	}
</style>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div class="formApp miEspacio">
	<c:if test="${not empty USUARIO_APP && (USUARIO_APP.candidato)}">
		<h2>Mi espacio</h2>
			<div class="tab_block">
        		<div align="left" id="returnME" style="display:block;">
            		<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
						<strong>Ir al inicio de Mi espacio</strong></a>
        		</div>
        		<div class="Tab_espacio">
        			<h3>Encontrar ofertas de empleo</h3>
            		<div class="subTab">
                		<ul>
                    		<li><a href="<c:url value="/ofertasPerfiles.do?method=init&tipoConsulta=1"/>">Ofertas de acuerdo a mi perfil</a></li>                    
                    		<li><a href="<c:url value="/ofertasPorParametros.do?method=init"/>">Búsqueda parametrizable</a></li>                    
                    		<li class="curSubTab"><span>Buscar en otras bolsas de trabajo</span></li>
                		</ul>
                		<div class="clearfix"></div>
            		</div>
        		</div>
        		<div class="sublevelTitle">
            		Buscar en otras bolsas de trabajo
        		</div>
    		</div>
    	</c:if>
	<p>Para comenzar a buscar, selecciona una bolsa de trabajo. A continuación, ingresa los datos requeridos y realiza la búsqueda.</p>

	<form dojoType="dijit.form.Form" name="bolsasTrabajoForm" id="bolsasTrabajoForm" action="bolsasTrabajo.do" method="post">

		<input type="hidden" name="method" value="init"/>
		<input type="hidden" name="idOcupacion"/>
		<input type="hidden" name="idOferta" id="idOferta"/>
		<input type="hidden" name="fecha"/>
		<input type="hidden" name="busquedaen"/>
		<input type="hidden" name="idEntidad" id="idEntidad"  />
		<input type="hidden" name="fromPortal" id="fromPortal" value="${bolsasTrabajoForm.fromPortal}"  />
		<input type="hidden" name="hispavistaState" id="hispavistaState"/>
		<input type="hidden" name="hispavistaIdState" id="hispavistaIdState"/>
		<input type="hidden" name="hispavistaIdArea" id="hispavistaIdArea"/>
		<input type="hidden" name="hispavistaIdProfession" id="hispavistaIdProfession">

		<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore2" urlPreventCache="true" clearOnClose="true"></div>

		<div dojoType="dojo.data.ItemFileReadStore" jsId="hispavistaCountryStatesStore" urlPreventCache="true" clearOnClose="true"></div>
		<%--<div dojoType="dojo.data.ItemFileReadStore" jsId="hispavistaAreasStore" urlPreventCache="true" clearOnClose="true"></div>--%>
		<%--<div dojoType="dojo.data.ItemFileReadStore" jsId="hispavistaAreaProfessionsStore" urlPreventCache="true" clearOnClose="true"></div>--%>

		<div class="gris">
			<ul id="busquedas">
				<li class="col-xs-12 col-sm-6 col-md-4">
					<label for="r1">
						<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r1" value="1" class="radios" onchange="inhabilitar(this.get('value'));"/>
						<img class="img-responsive" alt="" src="images/otras-occ.png">
						<span>OCC</span>
					</label>
				</li>
				<li class="col-xs-12 col-sm-6 col-md-4">
					<label for="r4">
						<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r4" value="4" class="radios" onchange="inhabilitar(this.get('value'));"/>
						<img class="img-responsive" alt="" src="images/otras-adecco.png">
						<span>Adecco</span>
					</label>
				</li>
				<%--<li class="horizontal">--%>
				<%--<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r6" value="6" class="radios" onchange="inhabilitar(this.get('value'));"/>--%>
				<%--<img src="images/logo-zonajobs.png" alt="logotipo Zona Jobs" />--%>
				<%--<label for="r6">Zona Jobs</label>--%>
				<%--</li>--%>
				<li class="col-xs-12 col-sm-6 col-md-4">
					<label class="txt2" for="r8">
						<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r8" value="8" class="radios" onchange="inhabilitar(this.get('value'));"/>
						<img class="img-responsive" alt="" src="images/otras-trabajaen.png">
						<span>Ofertas en la administración pública</span>
					</label>
				</li>
				<!-- <li class="col-xs-12 col-sm-6 col-md-4">
					<label for="r2">
						<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r2" value="2" class="radios" onchange="inhabilitar(this.get('value'));"/>
						<img class="img-responsive" alt="" src="images/otras-bumeran.png">
						<span>Bumerano</span>
					</label>
				</li> -->
				<li class="col-xs-12 col-sm-6 col-md-4">
					<label for="r5">
						<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r5" value="5" class="radios" onchange="inhabilitar(this.get('value'));"/>
						<img class="img-responsive" alt="" src="images/otras-Trabajos-MX.png">
						<span>TrabajosMX</span>
					</label>
				</li>
				<li class="col-xs-12 col-sm-6 col-md-4">
					<label class="txt2" for="r9">
						<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r9" value="9" class="radios" onchange="inhabilitar(this.get('value'));"/>
						<img class="img-responsive" alt="" src="images/otras-discapacidadyempleo.png">
						<span>Discapacidad y empleo</span>
					</label>
				</li>
				<li class="col-xs-12 col-sm-6 col-md-4">
					<label for="r3">
						<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r3" value="3" class="radios" onchange="inhabilitar(this.get('value'));"/>
						<img class="img-responsive" alt="" src="images/otras-manpower.png">
						<span>Manpower</span>
					</label>
				</li>
				<li class="col-xs-12 col-sm-6 col-md-4">
					<label for="r7">
						<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r7" value="7" class="radios" onchange="inhabilitar(this.get('value'));"/>
						<img class="img-responsive" alt="" src="images/otras-turijobs.png">
						<span>Turijobs</span>
					</label>
				</li>
				<li class="col-xs-12 col-sm-6 col-md-4">
					<label class="" for="r10">
						<input type="radio" dojoType="dijit.form.RadioButton" name="buscaen" required = "true" id="r10" value="10" class="radios" onchange="inhabilitar(this.get('value'));"/>
						<img class="img-responsive" alt="" src="images/otras-superchamba.png">
						<span>SuperChamba</span>
					</label>
				</li>
			</ul>
			<div class="clearfix"></div>

		</div>

		<div class="gris" style="position: relative;">
			<div class="bolsasCampos col-xs-8">
				<p class="labeled"><strong>Los campos con <span>*</span> son requeridos</strong></p>
				<p>
					<label for="palabra"><span class="c_naranja">*</span> Palabra clave</label>
					<input dojoType="dijit.form.ValidationTextBox" id="palabra" name="palabra" value="" style="font-weight: normal; height: 20px"
						   required="true" missingMessage="Debe indicar la palabra a buscar"/>
				</p>

				<div id="otras_bolsas">
					<p><label for="entidadFederativaSelect2"><span class="c_naranja">*</span> Entidad federativa</label>
						<select dojotype="dijit.form.FilteringSelect"
								store="entidadFederativaStore2" name="entidad" id="entidadFederativaSelect2"
								required="true" missingMessage="Debe indicar la entidad federativa"
								autoComplete="false" style="font-weight: normal; height: 20px"></select>
					</p>

					<p id="fecha_alta">
						<label for="fechaAlta"><span class="c_naranja">*</span> Publicadas</label>
						<select dojoType="dijit.form.FilteringSelect" name="fechaAlta" id ="fechaAlta" required="true" style="font-weight: normal; height: 20px">
							<option value="1" >Hoy</option>
							<option value="3" >Ultimos 3 d&iacute;as</option>
							<option value="7" >Ultimos 7 d&iacute;as</option>
							<option value="30" >Ultimos 30 d&iacute;as</option>
							<option value="60" >Ultimos 60 d&iacute;as</option>
						</select>
					</p>
				</div>

				<!-- Hispavista -->
				<div id="hispavista" class="hide">
					<p>
						<label for="hispavistaCountryStatesSelect"><span class="c_naranja">*</span> Entidad federativa</label>
						<select dojotype="dijit.form.FilteringSelect"
								store="hispavistaCountryStatesStore" name="hispavistaIdState" id="hispavistaCountryStatesSelect"
								required="true" missingMessage="Debe indicar la entidad federativa"
								autoComplete="false" style="font-weight: normal; height: 20px"></select>
					</p>
					<%--<p>--%>
					<%--<label for="hispavistaAreasSelect"><span class="c_naranja">*</span> Área</label>--%>
					<%--<select dojotype="dijit.form.FilteringSelect"--%>
					<%--store="hispavistaAreasStore" name="hispavistaIdArea" id="hispavistaAreasSelect"--%>
					<%--required="true" missingMessage="Debe indicar la entidad federativa"--%>
					<%--autoComplete="false" style="font-weight: normal; height: 20px"></select>--%>
					<%--</p>--%>
					<%--<p>--%>
					<%--<label for="hispavistaAreaProfessionsSelect"><span class="c_naranja">*</span> Profesión</label>--%>
					<%--<select dojotype="dijit.form.FilteringSelect"--%>
					<%--store="hispavistaAreaProfessionsStore" name="hispavistaIdArea" id="hispavistaAreaProfessionsSelect"--%>
					<%--required="true" missingMessage="Debe indicar la entidad federativa"--%>
					<%--autoComplete="false" style="font-weight: normal; height: 20px"></select>--%>
					<%--</p>--%>
				</div>

			</div>

			<div id="avisoSinFiltros" class="advertencias" style="display: none;">
				<p>Esta bolsa de trabajo no requiere el ingreso de datos.</p>

				<p><strong>Haz clic en el botón "Buscar"</strong></p>
			</div>

		</div>
		<div  id="buscar" class="botonBuscar col-xs-8">
			<input type="button" id="buscarCesar" class="pull-right" value="Buscar" name="buscar" onclick="javascript:doSubmit('buscar'); "/>
		</div>

	</form>

</div>
