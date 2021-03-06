<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="regexpnumext">^[0-9]*</c:set>
<c:set var="regexpsalario">^[+]?\d{1,6}(\.\d{1,2})?$</c:set>
<c:set var="regexpnumdecimales">^(\d|)?(\d|,)*\.?\d*$</c:set>

<script type="text/javascript">
var depGrado  = new Array(); //Declara arreglo de dependencias de catalogo Grado de Estudios
var depIdioma = new Array(); //Declara arreglo de dependencias de catalogo idiomas
</script>
<c:if test="${not empty busquedaCandidatosForm.gradosDependientes}">
	<script type="text/javascript">
		<c:forEach var="gradoDep" items="${busquedaCandidatosForm.gradosDependientes}" varStatus="indexGr">
			depGrado[parseInt('${indexGr.count - 1}')] = '${gradoDep}';
		</c:forEach>
	</script>
</c:if>

<c:if test="${not empty busquedaCandidatosForm.idiomasDependientes}">
	<script type="text/javascript">
		<c:forEach var="idiomaDep" items="${busquedaCandidatosForm.idiomasDependientes}" varStatus="indexIdi">
			depIdioma[parseInt('${indexIdi.count - 1}')] = '${idiomaDep}';
		</c:forEach>
	</script>
</c:if>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");
	dojo.require("dijit.form.MultiSelect");
	dojo.require("dijit.form.CheckBox");

	dojo.addOnLoad(function() {
				entidadFederativaStore.url = "${context}domicilio.do?method=obtenerEntidad";
				entidadFederativaStore.close();
				areaLaboralStore.url = "${context}busquedaEspecificaCandidatos.do?method=areaLaboraList";
				areaLaboralStore.close();
			});
	
	function doSubmit(method) {
		if (dijit.byId('busquedaCandidatosForm').isValid()) {
			if (validarFormulario()) {
				var area = dojo.byId('idAreaSelect').value
				var subarea = dojo.byId('idSubAreaSelect').value
				var idarea = dijit.byId('idAreaSelect').get('value');
				var idsubarea = dijit.byId('idSubAreaSelect').get('value');
				//var idSubprograma = dijit.byId('idSubprogramaSelect').get('value');
				//if (idSubprograma != '') {
					//dojo.byId('idSubprograma').value = idSubprograma;
				//}
				dojo.byId('areaLaboral').value = area;
				dojo.byId('subAreaLaboral').value = subarea;
				dojo.byId('idAreaLaboral').value = idarea;
				dojo.byId('idSubAreaLaboral').value = idsubarea;
				document.busquedaCandidatosForm.method.value = method;
				document.busquedaCandidatosForm.submit();
			}
		}else{
			alert('Los campos marcados en rojo no son validos');
		}
	}
	
	function validarFormulario() {
		var count = 0;
		var entidadCount = 0;
		var area = dojo.byId('idAreaSelect').value;
		var subarea = dojo.byId('idSubAreaSelect').value;
		var sueldoMinimo = dojo.byId('edadDe').value;
		var sueldoMaximo = dojo.byId('edadA').value;
		var entidad = dijit.byId('idEntidadSelect').attr('value');
		
		if (area == '') {
			alert('Debe seleccionar \u00e1rea laboral');
			return false;
		}
		if (subarea == '') {
			alert('Debe seleccionar sub\u00e1rea laboral');
			return false;
		}
		if (entidad == '') {
			alert('Debe seleccionar entidad federativa');
			return false;
		}
		if (entidad != '')
			entidadCount++;
		if (entidadCount == 0){
			alert('Debe seleccionar una entidad federativa');
			return false;
		}
		if(sueldoMinimo == '' && sueldoMaximo != ''){
			alert('El sueldo m\u00ednimo es requerido');
			return false;
		}else if(sueldoMinimo != '' && sueldoMaximo == ''){
			alert('El sueldo m\u00e1ximo es requerido');
			return false;
		}
		return true;	
	}

	function displayAgregar(tipoAgregar,agregarUno,agregarDos,agregarTres) {
		var idOcupacionDeseadaUno = null;
		var idOcupacionDeseadaDos = null;
		var idOcupacionDeseadaTres = null;
		var bloqueSeleccion = null;
		
		if(tipoAgregar == 'ocupaciones'){
			idOcupacionDeseadaUno = dojo.byId('idOcupacionDeseadaUno').value;
			idOcupacionDeseadaDos = dojo.byId('idOcupacionDeseadaDos').value;
			idOcupacionDeseadaTres = dojo.byId('idOcupacionDeseadaTres').value;
			
			bloqueSeleccion = dojo.byId('bloqueOcupaciones');
			
		}else if(tipoAgregar == 'municipios'){
			idOcupacionDeseadaUno = dojo.byId('municipioUno').value;
			idOcupacionDeseadaDos = dojo.byId('municipioDos').value;
			idOcupacionDeseadaTres = dojo.byId('municipioTres').value;
			
			bloqueSeleccion = dojo.byId('comboMunicipio');
		}else if(tipoAgregar == 'carreras'){
			idOcupacionDeseadaUno = dojo.byId('carreraUno').value;
			idOcupacionDeseadaDos = dojo.byId('carreraDos').value;
			idOcupacionDeseadaTres = dojo.byId('carreraTres').value;
			
			bloqueSeleccion = dojo.byId('comboCarreras');
		}else if(tipoAgregar == 'idiomas'){
			idOcupacionDeseadaUno = dojo.byId('idiomaUno').value;
			idOcupacionDeseadaDos = dojo.byId('idiomaDos').value;
			idOcupacionDeseadaTres = dojo.byId('idiomaTres').value;
			
			bloqueSeleccion = dojo.byId('comboIdioma');
		}else if(tipoAgregar == 'habilidades'){
			idOcupacionDeseadaUno = dojo.byId('habilidadUno').value;
			idOcupacionDeseadaDos = dojo.byId('habilidadDos').value;
			idOcupacionDeseadaTres = dojo.byId('habilidadTres').value;
			
			bloqueSeleccion = dojo.byId('comboHabilidad');
		}

		if(idOcupacionDeseadaUno != ''){
			if(idOcupacionDeseadaDos == '' && idOcupacionDeseadaTres != '')
				document.getElementById(agregarUno).style.display = 'none';
			else if(idOcupacionDeseadaDos == '')
				document.getElementById(agregarUno).style.display = 'block';
			else
				document.getElementById(agregarUno).style.display = 'none';
		}
		if(idOcupacionDeseadaDos != '' ){
			if(idOcupacionDeseadaUno == '' && idOcupacionDeseadaTres != '')
				document.getElementById(agregarDos).style.display = 'none';
			else if(idOcupacionDeseadaTres == '')
				document.getElementById(agregarDos).style.display = 'block';
			else
				document.getElementById(agregarDos).style.display = 'none';
		}
		
		if(idOcupacionDeseadaTres != ''){
			if(idOcupacionDeseadaDos != '')
				document.getElementById(agregarTres).style.display = 'block';
			else if(idOcupacionDeseadaDos == '')
				document.getElementById(agregarTres).style.display = 'block';
			else
				document.getElementById(agregarTres).style.display = 'none';
		}
		
		if(idOcupacionDeseadaUno == '' && idOcupacionDeseadaDos == '' && idOcupacionDeseadaTres == ''){
			
			bloqueSeleccion.style.display = 'block';
			if(tipoAgregar == 'ocupaciones')
				document.getElementById('labelOcupacion').style.display = 'block';
			
			if(tipoAgregar == 'municipios')
				dijit.byId('idMunicipioSelect').reset();
			else if(tipoAgregar == 'carreras')
				dijit.byId('carreraEspecialidadSelect').reset();
			else if(tipoAgregar == 'idiomas')
				dijit.byId('idiomaSelect').reset();
			else if(tipoAgregar == 'habilidades')
				dijit.byId('idHabilidadSelect').reset();
				
			
			document.getElementById(agregarUno).style.display = 'none';
		}else if(idOcupacionDeseadaUno != '' && idOcupacionDeseadaDos != '' && idOcupacionDeseadaTres != ''){
			document.getElementById(agregarUno).style.display = 'none';
			document.getElementById(agregarDos).style.display = 'none';
			document.getElementById(agregarTres).style.display = 'none';
		}
	}

	//SEGUNDO BLOQUE DE BUSQUEDA
	function actulizaMunicipio() {
		var vEntidad = dijit.byId('idEntidadSelect').get('value');
		var label = dijit.byId('idEntidadSelect').get('displayedValue');
		dojo.byId('entidadDescripcionForm').value = label;
		
		if (vEntidad) {
			municipioStore.url = '';
			municipioStore.close();
			dijit.byId('idMunicipioSelect').reset();
			//dijit.byId('idMunicipioSelect').disabled=false;
			municipioStore.url = "${context}domicilio.do?method=obtenerMunicipio"
					+ "&" + "idEntidad=" + vEntidad;
			municipioStore.close();
			dijit.byId('idMunicipioSelect').set('disabled', false);
			borrarMunicipios();
		}
	}

	function borrarMunicipios() {
		var municipioUno = document.getElementById('pmunicipioUno');
		var municipioDos = document.getElementById('pmunicipioDos');
		var municipioTres = document.getElementById('pmunicipioTres');

		dojo.byId('municipioUno').value = '';
		dojo.byId('descMunicipioUnoForm').value = '';
		document.getElementById('descMunicipioUno').innerHTML = '';
		municipioUno.style.display = 'none';
		document.getElementById('eliminarMunicipioUno').style.display = 'none';

		dojo.byId('municipioDos').value = '';
		dojo.byId('descMunicipioDosForm').value = '';
		document.getElementById('descMunicipioDos').innerHTML = '';
		municipioDos.style.display = 'none';
		document.getElementById('eliminarMunicipioDos').style.display = 'none';

		dojo.byId('municipioTres').value = '';
		dojo.byId('descMunicipioTresForm').value = '';
		document.getElementById('descMunicipioTres').innerHTML = '';
		municipioTres.style.display = 'none';
		document.getElementById('eliminarMunicipioTres').style.display = 'none';

		if (municipioUno.style.display == 'none' && municipioDos.style.display == 'none' 	&& municipioTres.style.display == 'none') {
			dojo.byId('comboMunicipio').style.display = 'block';
		}
	}

	function mostrarComboMunicipio(){
		var valor = dojo.byId('comboMunicipio').style.display;
		
		if(valor == 'none'){
			dojo.byId('comboMunicipio').style.display = 'block';
			dijit.byId('idMunicipioSelect').reset();
		}
		
	}
	function agregarMunicipio() {
		var valor = dijit.byId('idMunicipioSelect').get('value');
		var label = dijit.byId('idMunicipioSelect').get('displayedValue');

		var municipioUno = document.getElementById('pmunicipioUno');
		var municipioDos = document.getElementById('pmunicipioDos');
		var municipioTres = document.getElementById('pmunicipioTres');
		var msje = 'El municipio ya fue agregado';

		if (municipioUno.style.display == 'none' && valor != '') {
			if (dojo.byId('municipioDos').value == valor || dojo.byId('municipioTres').value == valor)
				alert(msje);
			else {
				dojo.byId('municipioUno').value = valor;
				dojo.byId('descMunicipioUnoForm').value = label;
				document.getElementById('descMunicipioUno').innerHTML = label;
				municipioUno.style.display = 'block';
				document.getElementById('eliminarMunicipioUno').style.display = 'block';
				document.getElementById('comboMunicipio').style.display = 'none';

			}

		} else if (municipioDos.style.display == 'none' && valor != '') {
			if (dojo.byId('municipioUno').value == valor
					|| dojo.byId('municipioTres').value == valor)
				alert(msje);
			else {
				dojo.byId('municipioDos').value = valor;
				dojo.byId('descMunicipioDosForm').value = label;
				document.getElementById('descMunicipioDos').innerHTML = label;
				municipioDos.style.display = 'block';
				document.getElementById('eliminarMunicipioDos').style.display = 'block';
				document.getElementById('comboMunicipio').style.display = 'none';
			}

		} else if (municipioTres.style.display == 'none' && valor != '') {
			if (dojo.byId('municipioUno').value == valor
					|| dojo.byId('municipioDos').value == valor)
				alert(msje);
			else {
				dojo.byId('municipioTres').value = valor;
				dojo.byId('descMunicipioTresForm').value = label;
				document.getElementById('descMunicipioTres').innerHTML = label;
				municipioTres.style.display = 'block';
				document.getElementById('eliminarMunicipioTres').style.display = 'block';
				document.getElementById('comboMunicipio').style.display = 'none';
			}
		}
		
		displayAgregar('municipios','agregarMncipioUno','agregarMncipioDos','agregarMncipioTres');

// 		if (municipioUno.style.display == 'block' || municipioDos.style.display == 'block' || municipioTres.style.display == 'block') {
// 			dojo.byId('agregarMncipioUno').style.display = 'block';
// 		}
		
// 		if (municipioUno.style.display == 'block' && municipioDos.style.display == 'block' && municipioTres.style.display == 'block') {
// 			dojo.byId('agregarMncipioUno').style.display = 'none';
// 		}
	}

	function eliminarMunicipio(idEliminar) {
		var municipioUno = document.getElementById('pmunicipioUno');
		var municipioDos = document.getElementById('pmunicipioDos');
		var municipioTres = document.getElementById('pmunicipioTres');

		if (idEliminar == 'eliminarMunicipioUno') {
			dojo.byId('municipioUno').value = '';
			dojo.byId('descMunicipioUnoForm').value = '';
			document.getElementById('descMunicipioUno').innerHTML = '';
			municipioUno.style.display = 'none';
			document.getElementById(idEliminar).style.display = 'none';
		} else if (idEliminar == 'eliminarMunicipioDos') {
			dojo.byId('municipioDos').value = '';
			dojo.byId('descMunicipioDosForm').value = '';
			document.getElementById('descMunicipioDos').innerHTML = '';
			municipioDos.style.display = 'none';
			document.getElementById(idEliminar).style.display = 'none';
		} else if (idEliminar == 'eliminarMunicipioTres') {
			dojo.byId('municipioTres').value = '';
			dojo.byId('descMunicipioTresForm').value = '';
			document.getElementById('descMunicipioTres').innerHTML = '';
			municipioTres.style.display = 'none';
			document.getElementById(idEliminar).style.display = 'none';
		}
		
		displayAgregar('municipios','agregarMncipioUno','agregarMncipioDos','agregarMncipioTres');

// 		if (municipioUno.style.display == 'none' || municipioDos.style.display == 'none' || municipioTres.style.display == 'none') {
// 				dojo.byId('agregarMncipioUno').style.display = 'block';
// 		}
		
// 		if(municipioUno.style.display == 'none' && municipioDos.style.display == 'none' && municipioTres.style.display == 'none'){
// 			dojo.byId('comboMunicipio').style.display = 'block';
// 			dojo.byId('agregarMncipioUno').style.display = 'none';
// 		}
	}
	
	//QUINTO BLOQUE DE BUSQUEDA
	function actualizaCarreras(){
		var vGrado = dijit.byId('idGradoEstudioSelect').get('value');
		var label = dijit.byId('idGradoEstudioSelect').get('displayedValue');
		
		dojo.byId('descEscolaridadForm').value = label;
		var vIdCatDep = depGrado[vGrado];
		
        if(vIdCatDep && vIdCatDep != 0 ) {
        	carreraEspecialidadStore.url = '';
        	carreraEspecialidadStore.close();
        	
        	dijit.byId('carreraEspecialidadSelect').reset();            	
        	carreraEspecialidadStore.url = "${context}registro.do?method=carreras&idCatDep="+ vIdCatDep;
        	carreraEspecialidadStore.close();
        	dijit.byId('carreraEspecialidadSelect').set('disabled', false);
        	borrarCarreras();
        }
	}
	
	function borrarCarreras() {
		var municipioUno = document.getElementById('pcarreraUno');
		var municipioDos = document.getElementById('pcarreraDos');
		var municipioTres = document.getElementById('pcarreraTres');

		dojo.byId('carreraUno').value = '';
		dojo.byId('descCarreraUnoForm').value = '';
		document.getElementById('descCarreraUno').innerHTML = '';
		municipioUno.style.display = 'none';
		document.getElementById('eliminarCarreraUno').style.display = 'none';

		dojo.byId('carreraDos').value = '';
		dojo.byId('descCarreraDosForm').value = '';
		document.getElementById('descCarreraDos').innerHTML = '';
		municipioDos.style.display = 'none';
		document.getElementById('eliminarCarreraDos').style.display = 'none';

		dojo.byId('carreraTres').value = '';
		dojo.byId('descCarreraTresForm').value = '';
		document.getElementById('descCarreraTres').innerHTML = '';
		municipioTres.style.display = 'none';
		document.getElementById('eliminarCarreraTres').style.display = 'none';

		document.getElementById('agregarCarrera').style.display = 'none';
	}
	
	function mostrarCarreras(){
		var valor = dojo.byId('comboCarreras').style.display;
		
		
		if(valor == 'none'){
			dojo.byId('comboCarreras').style.display = 'block';
			dijit.byId('carreraEspecialidadSelect').reset();
		}
	}
	
	function agregarCarreraFuncion(){
		var valor = dijit.byId('carreraEspecialidadSelect').get('value');
		var label = dijit.byId('carreraEspecialidadSelect').get('displayedValue');
		
		var carreraUno = document.getElementById('pcarreraUno');
		var carreraDos = document.getElementById('pcarreraDos');
		var carreraTres = document.getElementById('pcarreraTres');
		var msje = 'La especialidad ya fue agregada';
		
		if(carreraUno.style.display == 'none' && valor != ''){
			if(dojo.byId('carreraDos').value == valor || dojo.byId('carreraTres').value == valor)
				alert(msje);
			else{ 
				dojo.byId('carreraUno').value = valor;
				dojo.byId('descCarreraUnoForm').value = label;
				document.getElementById('descCarreraUno').innerHTML = label;
				carreraUno.style.display = 'block';
				document.getElementById('eliminarCarreraUno').style.display = 'block';
				dojo.byId('comboCarreras').style.display = 'none';
			}
			
		}else if(carreraDos.style.display == 'none' && valor != ''){
			if(dojo.byId('carreraUno').value == valor || dojo.byId('carreraTres').value == valor)
				alert(msje);
			else{
				dojo.byId('carreraDos').value = valor;
				dojo.byId('descCarreraDosForm').value = label;
				document.getElementById('descCarreraDos').innerHTML = label;
				carreraDos.style.display = 'block';
				document.getElementById('eliminarCarreraDos').style.display = 'block';
				dojo.byId('comboCarreras').style.display = 'none';
			}
			
		}else if(carreraTres.style.display == 'none' && valor != ''){
			if(dojo.byId('carreraUno').value == valor || dojo.byId('carreraDos').value == valor)
				alert(msje);
			else{
				dojo.byId('carreraTres').value = valor;
				dojo.byId('descCarreraTresForm').value = label;
				document.getElementById('descCarreraTres').innerHTML = label;
				carreraTres.style.display = 'block';
				document.getElementById('eliminarCarreraTres').style.display = 'block';
				dojo.byId('comboCarreras').style.display = 'none';
			}
		}
		
		displayAgregar('carreras','agregarCarreraUno','agregarCarreraDos','agregarCarreraTres');
		
// 		if(carreraUno.style.display == 'block' || carreraDos.style.display == 'block' || carreraTres.style.display == 'block'){
// 			dojo.byId('agregarCarrera').style.display = 'block';
// 		}
		
// 		if(carreraUno.style.display == 'block' && carreraDos.style.display == 'block' && carreraTres.style.display == 'block'){
// 			dojo.byId('agregarCarrera').style.display = 'none';
// 		}
		
	}
	
	function eliminarCarrera(idEliminar){
		var carreraUno = document.getElementById('pcarreraUno');
		var carreraDos = document.getElementById('pcarreraDos');
		var carreraTres = document.getElementById('pcarreraTres');
		
		if(idEliminar == 'eliminarCarreraUno'){
			dojo.byId('carreraUno').value = '';
			dojo.byId('descCarreraUnoForm').value = '';
			document.getElementById('descCarreraUno').innerHTML = '';
			carreraUno.style.display = 'none';
			document.getElementById(idEliminar).style.display = 'none';
		}else if(idEliminar == 'eliminarCarreraDos'){
			dojo.byId('carreraDos').value = '';
			dojo.byId('descCarreraDosForm').value = '';
			document.getElementById('descCarreraDos').innerHTML = '';
			carreraDos.style.display = 'none';
			document.getElementById(idEliminar).style.display = 'none';
		}else if(idEliminar == 'eliminarCarreraTres'){
			dojo.byId('carreraTres').value = '';
			dojo.byId('descCarreraTresForm').value = '';
			document.getElementById('descCarreraTres').innerHTML = '';
			carreraTres.style.display = 'none';
			document.getElementById(idEliminar).style.display = 'none';
		}
		
		
		displayAgregar('carreras','agregarCarreraUno','agregarCarreraDos','agregarCarreraTres');
		
// 		if(carreraUno.style.display == 'none' || carreraDos.style.display == 'none' || carreraTres.style.display == 'none'){
// 			document.getElementById('agregarCarrera').style.display = 'block';
// 		}
		
// 		if(carreraUno.style.display == 'none' && carreraDos.style.display == 'none' && carreraTres.style.display == 'none'){
// 			dojo.byId('comboCarreras').style.display = 'block';
// 			dojo.byId('agregarCarrera').style.display = 'none';
// 		}
	}
	
	function mostrarComboIdiomas(){
		var valor = dojo.byId('comboIdioma').style.display;
		
		if(valor == 'none'){
			dojo.byId('comboIdioma').style.display = 'block';
			dijit.byId('idiomaSelect').reset();
		}
	}
	
	function agregarIdiomaFuncion(){
		var valor = dijit.byId('idiomaSelect').get('value');
		var label = dijit.byId('idiomaSelect').get('displayedValue');
		
		var idiomaUno = document.getElementById('pidiomaUno');
		var idiomaDos = document.getElementById('pidiomaDos');
		var idiomaTres = document.getElementById('pidiomaTres');
		var msje = 'El idioma ya fue agregado';
		if(idiomaUno.style.display == 'none' && valor != ''){
			if(dojo.byId('idiomaDos').value == valor || dojo.byId('idiomaTres').value == valor)
				alert(msje);
			else{ 
				dojo.byId('idiomaUno').value = valor;
				dojo.byId('descIdiomaUnoForm').value = label;
				document.getElementById('descIdiomaUno').innerHTML = label;
				idiomaUno.style.display = 'block';
				document.getElementById('eliminarIdiomaUno').style.display = 'block';
				document.getElementById('comboIdioma').style.display = 'none';
			}
			
		}else if(idiomaDos.style.display == 'none' && valor != ''){
			if(dojo.byId('idiomaUno').value == valor || dojo.byId('idiomaTres').value == valor)
				alert(msje);
			else{
				dojo.byId('idiomaDos').value = valor;
				dojo.byId('descIdiomaDosForm').value = label;
				document.getElementById('descIdiomaDos').innerHTML = label;
				idiomaDos.style.display = 'block';
				document.getElementById('eliminarIdiomaDos').style.display = 'block';
				document.getElementById('comboIdioma').style.display = 'none';
			}
			
		}else if(idiomaTres.style.display == 'none' && valor != ''){
			if(dojo.byId('idiomaUno').value == valor || dojo.byId('idiomaDos').value == valor)
				alert(msje);
			else{
				dojo.byId('idiomaTres').value = valor;
				dojo.byId('descIdiomaTresForm').value = label;
				document.getElementById('descIdiomaTres').innerHTML = label;
				idiomaTres.style.display = 'block';
				document.getElementById('eliminarIdiomaTres').style.display = 'block';
				document.getElementById('comboIdioma').style.display = 'none';
			}
		}
		
		displayAgregar('idiomas','agregarIdiomaUno','agregarIdiomaDos','agregarIdiomaTres');
		
// 		if(idiomaUno.style.display == 'block' || idiomaDos.style.display == 'block' || idiomaTres.style.display == 'block'){
// 			document.getElementById('agregarIdioma').style.display = 'block';
			
// 		}
// 		if(idiomaUno.style.display == 'block' && idiomaDos.style.display == 'block' && idiomaTres.style.display == 'block'){
// 			document.getElementById('agregarIdioma').style.display = 'none';
			
// 		}
		
		
	}
	
	function eliminarIdioma(idEliminar){
		var idiomaUno = document.getElementById('pidiomaUno');
		var idiomaDos = document.getElementById('pidiomaDos');
		var idiomaTres = document.getElementById('pidiomaTres');
		
		if(idEliminar == 'eliminarIdiomaUno'){
			dojo.byId('descIdiomaUnoForm').value = '';
			dojo.byId('idiomaUno').value = '';
			document.getElementById('descIdiomaUno').innerHTML = '';
			idiomaUno.style.display = 'none';
			document.getElementById(idEliminar).style.display = 'none';
		}else if(idEliminar == 'eliminarIdiomaDos'){
			dojo.byId('idiomaDos').value = '';
			dojo.byId('descIdiomaDosForm').value = '';
			document.getElementById('descIdiomaDos').innerHTML = '';
			idiomaDos.style.display = 'none';
			document.getElementById(idEliminar).style.display = 'none';
		}else if(idEliminar == 'eliminarIdiomaTres'){
			dojo.byId('idiomaTres').value = '';
			dojo.byId('descIdiomaTresForm').value = '';
			document.getElementById('descIdiomaTres').innerHTML = '';
			idiomaTres.style.display = 'none';
			document.getElementById(idEliminar).style.display = 'none';
		}
		
// 		if(idiomaUno.style.display == 'none' || idiomaDos.style.display == 'none' || idiomaTres.style.display == 'none'){
// 			document.getElementById('agregarIdioma').style.display = 'block';
			
// 		}
		
		displayAgregar('idiomas','agregarIdiomaUno','agregarIdiomaDos','agregarIdiomaTres');
		
		
		
// 		if(idiomaUno.style.display == 'none' && idiomaDos.style.display == 'none' && idiomaTres.style.display == 'none'){
// 			dojo.byId('comboIdioma').style.display = 'block';
// 			dojo.byId('agregarIdioma').style.display = 'none';
			
// 		}
	}
	
	//PAGINACION
	function doSubmitPager(method) {
		 dojo.xhrPost({url: 'busquedaEspecificaCandidatos.do?method='+method, timeout:180000,
		                  load: function(data) {
		                        dojo.byId('reporte').innerHTML = data;
		                  }});
		}
	function doSubmitPagina(pagina, dif){
		
	   	dojo.xhrPost({url: 'busquedaEspecificaCandidatos.do?method=goToPage&goToPageNumber='+pagina, timeout:180000, 
					  load: function(dataCand){
					      dojo.byId('reporte').innerHTML=dataCand;
					  }});
	}
	
	function actulizaSubArea() {
		var vArea = dijit.byId('idAreaSelect').get('value');
		if (vArea) {
			subAreaStore.url = '';
			subAreaStore.close();
			dijit.byId('idSubAreaSelect').reset();
			subAreaStore.url = "${context}busquedaEspecificaCandidatos.do?method=subareaLaboraList" + "&" + "idAreaLaboral=" + vArea;
			subAreaStore.close();
		}
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div dojoType="dojo.data.ItemFileReadStore" jsId="entidadFederativaStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="municipioStore" urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="gradoEstudioStore" url="${context}registro.do?method=gradosEstudio"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="carreraEspecialidadStore"  urlPreventCache="true" clearOnClose="true"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="idiomaStore"   url="${context}registro.do?method=idiomas"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="subprogramaStore" url="${context}busquedaEspecificaOfertas.do?method=getSubprogram"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="areaLaboralStore" url="${context}busquedaEspecificaCandidatos.do?method=areaLaboraList"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="subAreaStore" url="${context}busquedaEspecificaCandidatos.do?method=subareaLaboraList"></div>

<form id="busquedaCandidatosForm" name="busquedaCandidatosForm" method="post" action="busquedaEspecificaCandidatos.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init"> 
	
	<!-- SEGUNDO BLOQUE DE BUSQUEDA -->
	<input type="hidden" name="idSubprograma" id="idSubprograma" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="idAreaLaboral" id="idAreaLaboral" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="idSubAreaLaboral" id="idSubAreaLaboral" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="areaLaboral" id="areaLaboral" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="subAreaLaboral" id="subAreaLaboral" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="entidadDescripcionForm" id="entidadDescripcionForm" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="municipioUno" id="municipioUno" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="municipioDos" id="municipioDos" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="municipioTres" id="municipioTres" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="descMunicipioUnoForm" id="descMunicipioUnoForm" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="descMunicipioDosForm" id="descMunicipioDosForm" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="descMunicipioTresForm" id="descMunicipioTresForm" value="" dojoType="dijit.form.TextBox" />
	<!-- QUINTO BLOQUE DE BUSQUEDA -->
	<input type="hidden" name="descEscolaridadForm" id="descEscolaridadForm" value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="carreraUno" 	  id="carreraUno"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="carreraDos" 	  id="carreraDos"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="carreraTres" 	  id="carreraTres"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="descCarreraUnoForm" 	  id="descCarreraUnoForm"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="descCarreraDosForm" 	  id="descCarreraDosForm"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="descCarreraTresForm" 	  id="descCarreraTresForm"    value="" dojoType="dijit.form.TextBox"/>
	
	<input type="hidden" name="idiomaUno" 	  id="idiomaUno"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idiomaDos" 	  id="idiomaDos"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="idiomaTres" 	  id="idiomaTres"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="descIdiomaUnoForm" 	  id="descIdiomaUnoForm"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="descIdiomaDosForm" 	  id="descIdiomaDosForm"    value="" dojoType="dijit.form.TextBox"/>
	<input type="hidden" name="descIdiomaTresForm" 	  id="descIdiomaTresForm"    value="" dojoType="dijit.form.TextBox"/>
	
	<div class="clearfix"></div>
	<div class="formApp">
	<h2>B&uacute;squeda de Candidatos</h2>
	<p><strong>Puedes utilizar una o m&#225;s de las opciones de b&#250;squeda</strong></p>
	<fieldset>
	<legend>Detalles de la b&uacute;squeda</legend>
	<div class="margin_top_30">
		<div style="padding: 40px 0 0">
			<div class="grid1_3 fl">
				<label>
					<strong>Area laboral</strong>
				</label>
				<div id="bloqueOcupaciones" class="margin_top_20">
					<select id="idAreaSelect" name="idAreaSelect" dojotype="dijit.form.FilteringSelect"
						store="areaLaboralStore" required="false" missingMessage="Es necesario indicar el &#225;rea laboral."
						scrollOnFocus="false" maxheight="250" autocomplete="true" lang="es"
						onchange="javascript:actulizaSubArea();">
					</select>
				</div>
			</div>
			<div class="grid1_3 fl">
				<label>
					<strong>Sub&#225;rea laboral</strong>
				</label>
				<div id="bloqueSubAreas" class="margin_top_20">
					<select id="idSubAreaSelect" name="idSubAreaSelect" dojotype="dijit.form.FilteringSelect"
						store="subAreaStore" required="false" missingMessage="Es necesario indicar la sub &#225;rea laboral."
						scrollOnFocus="false" maxheight="250" autocomplete="true" lang="es">
					</select>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<div style="padding: 40px 0 0">
		<label id="labelOcupacion" for="ocupacionDesc" class="c_naranja">Escribe
					y selecciona el perfil laboral que buscas</label>
		<!-- div class="grid1_3 fl">
			<label for="idSubprogramaSelect"><strong>Subprograma</strong></label>
			<select id="idSubprogramaSelect" name="idSubprogramaSelect" dojotype="dijit.form.FilteringSelect" store="subprogramaStore" required="false"
				missingMessage="Es necesario indicar el subprograma." scrollOnFocus="false" maxheight="250" autocomplete="true"  lang="es">
			</select>
		</div  -->
		<div class="grid1_3 fl">
			<label for="idEntidadSelect"><strong>Entidad</strong></label>
			<select id="idEntidadSelect" name="idEntidadSelect"
				dojotype="dijit.form.FilteringSelect" store="entidadFederativaStore"
				required="false" missingMessage="Es necesario indicar la entidad."
				scrollOnFocus="false" maxheight="250" autocomplete="true" 
				lang="es"
				onchange="javascript:actulizaMunicipio();">
			</select>
		</div>
		<div class="grid1_3 fl">
			<label for="idMunicipioSelect">Municipio(s) o Delegaci&#243;n(es)</label>
			<div>
				<div id="pmunicipioUno" class="agregarOpciones" style="display: none;">
					<span id="descMunicipioUno" style="display: block;"></span> 
					<div class="clearfix"></div>
					<a id="eliminarMunicipioUno"
						class="agregar fl" style="display: none; margin: 0 20px 0 0"
						onclick="eliminarMunicipio(this.id);">Eliminar</a>
											
					<a id="agregarMncipioUno" class="agregar fl" onclick="javascript:mostrarComboMunicipio();" style="display: none;">
					Agregar</a>
					<div class="clearfix"></div>
	
				</div>
	
				<div id="pmunicipioDos" class="agregarOpciones margin_top_20" style="display: none;">
					<span id="descMunicipioDos" style="display: block;"></span>
					<div class="clearfix"></div> 
					<a id="eliminarMunicipioDos"
						class="agregar fl" style="display: none; margin: 0 20px 0 0"
						onclick="eliminarMunicipio(this.id);">Eliminar</a>
					<a id="agregarMncipioDos" class="agregar fl" onclick="javascript:mostrarComboMunicipio();" style="display: none;">Agregar</a>
					<div class="clearfix"></div>
				</div>
	
				<div id="pmunicipioTres" class="agregarOpciones margin_top_20" style="display: none; margin-bottom: 20px;">
					<span id="descMunicipioTres"></span>
					<div class="clearfix"></div> 
					<a id="eliminarMunicipioTres"
						class="agregar fl" style="display: none; margin: 0 0 20px"
						onclick="eliminarMunicipio(this.id);">Eliminar</a>
					<a id="agregarMncipioTres" class="agregar fl" onclick="javascript:mostrarComboMunicipio();" style="display: none;">Agregar</a>
					<div class="clearfix"></div>
				</div>
	
				<div id="comboMunicipio" style="display: block;">
					<select id="idMunicipioSelect" name="idMunicipioSelect" 
						dojotype="dijit.form.FilteringSelect" store="municipioStore"
						required="false" missingMessage="Es necesario indicar un municipio."
						scrollOnFocus="true" maxheight="250" autocomplete="true"
						disabled="true" onchange="javascript:agregarMunicipio();">
					</select> 
				</div>
				
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- div class="grid1_3">
		<div class="labeled">
			G&#233;nero
		</div>
		<ul class="generoInput">
			<li><label><input type="radio"	name="genero" value="0" id="indistinto" /><span>Indistinto</span></label></li>
			<li><label><input type="radio" name="genero" value="2" id="femenino" /><span>Femenino</span></label></li>
			<li><label> <input type="radio" name="genero" value="1" id="masculino" /><span>Masculino</span></label></li>
		</ul>
	</div>
	<div style="padding: 20px 0">
		<div class="genEdad grid1_3 fl">
			<label class="edadMin" for="edadMinima"><strong>Edad</strong> M&#237;nima</label>
			<input type="text" name="edadMinima" id="edadMinima" size="2" maxlength="2" dojoType="dijit.form.ValidationTextBox" trim="false" required="false" regExp="${regexpnumext}" invalidMessage="Edad no valida" value=""/>
		</div>
		<div class="grid1_3 fl"> 
			<label class="edadMax" for="edadMaxima">M&#225;xima</label> 
			<input type="text" name="edadMaxima" id="edadMaxima" size="2" maxlength="2" dojoType="dijit.form.ValidationTextBox"" trim="false" required="false" regExp="${regexpnumext}" invalidMessage="Edad no valida" value=""/> 
		</div>
		<div class="clearfix"></div>
		<div>Para usar este filtro es necesario indicar ambas edades, de lo contrario no ser&#225; usado</div>
	</div -->
	<div style="padding: 20 0">
		<div class="grid1_3 fl">
			<label for="edadDe">Sueldo deseado De</label> 
			<input type="text" name="edadDe" id="edadDe" size="10" maxlength="20" dojoType="dijit.form.ValidationTextBox" trim="false" required="false" regExp="${regexpnumdecimales}" invalidMessage="Sueldo no valido" value=""/>
		</div>
		<div class="grid1_3 fl">
			<label for="edadA">A</label> 
			<input type="text" name="edadA" id="edadA" size="10" maxlength="20"  dojoType="dijit.form.ValidationTextBox" trim="false" required="false" regExp="${regexpnumdecimales}" invalidMessage="Sueldo no valido" value="" />
		</div> 
		<div class="clearfix"></div>
		<div>Para usar este filtro es necesario indicar ambos l&#237;mites de sueldo,	de lo contrario no ser&#225; usado</div>
	</div>
	<div style="padding: 20px 0 0">
		<div class="grid1_3 fl">
			<label for="idGradoEstudioSelect"><strong>Escolaridad (nivel
					m&#225;ximo de estudios)</strong>
			</label> <select id="idGradoEstudioSelect" name="idGradoEstudioSelect"
				required="false"
				missingMessage="Es necesario indicar el �ltimo grado de estudios."
				store="gradoEstudioStore" dojotype="dijit.form.FilteringSelect"
				onchange="javascript:actualizaCarreras();">
			</select>
		</div>
		<div class="grid1_3 fl">
			<label for="carreraEspecialidadSelect">Carrera(s)</label>
	
			<div>
				<div id="pcarreraUno" class="agregarOpciones" style="display: none;">
					<span id="descCarreraUno"></span>
					<div class="clearfix"></div>
					<a id="eliminarCarreraUno" class="agregar fl" onclick="eliminarCarrera(this.id);" style="display: none; margin: 0 20px 0 0">Eliminar</a>
					<a id="agregarCarreraUno" class="agregar fl" onclick="mostrarCarreras();" style="display: none;">Agregar</a>
					<div class="clearfix"></div>
				</div>
				<div id="pcarreraDos" class="agregarOpciones" style="display: none;">
					<span id="descCarreraDos"></span>
					<div class="clearfix"></div>
					<a id="eliminarCarreraDos" class="agregar fl" onclick="eliminarCarrera(this.id);" style="display: none; margin: 0 20px 0 0">Eliminar</a>
					<a id="agregarCarreraDos" class="agregar fl" onclick="mostrarCarreras();" style="display: none;">Agregar</a>
					<div class="clearfix"></div>
				</div>
				<div id="pcarreraTres" class="agregarOpciones" style="display: none;">
					<span id="descCarreraTres"></span>
					<div class="clearfix"></div>
					<a id="eliminarCarreraTres" class="agregar fl" onclick="eliminarCarrera(this.id);" style="display: none; margin: 0 20px 0 0">Eliminar</a>
					<a id="agregarCarreraTres" class="agregar fl" onclick="mostrarCarreras();" style="display: none;">Agregar</a>
					<div class="clearfix"></div>
				</div>
				<div id="comboCarreras" style="display: block;">
					<select id="carreraEspecialidadSelect" name="carreraEspecialidadSelect" maxheight="250" 
				        required="false" missingMessage="Es necesario indicar la carrera o especialidad." scrollOnFocus="true"
				        store="carreraEspecialidadStore" dojotype="dijit.form.FilteringSelect" disabled="true" onchange="agregarCarreraFuncion();">
					</select>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		
		<div class="grid1_3 margin_top_20">
			<label for="idiomaSelect"><strong>Idioma(s) adicional(es) al espa&#241;ol</strong></label> 
			<div>
				<div id="pidiomaUno" class="agregarOpciones" style="display: none;">
					<span id="descIdiomaUno"></span>
					<div class="clearfix"></div>
					<a id="eliminarIdiomaUno" class="agregar fl" onclick=" eliminarIdioma(this.id);" style="display: none; margin: 0 20px 0 0">Eliminar</a>
					<a id="agregarIdiomaUno" class="agregar fl" onclick="mostrarComboIdiomas();" style="display: none;">Agregar</a>
					<div class="clearfix"></div>
				</div>
				<div id="pidiomaDos" class="agregarOpciones" style="display: none;">
					<span id="descIdiomaDos"></span>
					<div class="clearfix"></div>
					<a id="eliminarIdiomaDos" class="agregar fl" onclick=" eliminarIdioma(this.id);" style="display: none; margin: 0 20px 0 0">Eliminar</a>
					<a id="agregarIdiomaDos" class="agregar fl" onclick="mostrarComboIdiomas();" style="display: none;">Agregar</a>
					<div class="clearfix"></div>
				</div>
				<div id="pidiomaTres" class="agregarOpciones" style="display: none;">
					<span id="descIdiomaTres"></span>
					<div class="clearfix"></div>
					<a id="eliminarIdiomaTres" class="agregar fl" onclick="eliminarIdioma(this.id);" style="display: none; margin: 0 20px 0 0">Eliminar</a>
					<a id="agregarIdiomaTres" class="agregar fl" onclick="mostrarComboIdiomas();" style="display: none;">Agregar</a>
					<div class="clearfix"></div>
				</div>
				<div id="comboIdioma" style="display: block">
					<select id="idiomaSelect" name="idiomaSelect" store="idiomaStore" dojotype="dijit.form.FilteringSelect" onchange="agregarIdiomaFuncion();" value="" disabled="false" required="false">
					</select>
				</div>
			</div>
		</div>
	<div>
		<!-- div class="grid1_3 dispViajar">
			<div class="labeled">Disponibilidad para viajar</div>
		     <ul>
		     	<li>
		     		<label> 
		     			<input type="radio" name="disponibilidadViajar" value="2" id="si" /><span>S&iacute;</span>
		        	</label>
		        </li>
		     	<li><label>
		        		<input type="radio" name="disponibilidadViajar" value="1" id="no" /><span>No</span>
		        	</label>
		        </li>
		    </ul>
    	</div>
	      <div class="grid1_3 cambiarRes">
		    <div class="labeled">Cambiar de residencia</div>
		    <ul>
		    	<li>
			    	<label>
				        <input type="radio" name="cambiarResidencia" value="2" id="si" />
				        <span>S&iacute;</span>
			        </label>
		        </li>
		    	<li>
			    	<label>
				        <input type="radio" name="cambiarResidencia" value="1" id="no" />
				        <span>No</span>
			        </label>
		        </li>
		    </ul>
	    </div>
	</div -->
	<!-- div class="grid1_3 margin_top_20">
		<label for="idHabilidadSelect">Habilidades y aptitudes</label>
		<div>
			<div id="phabilidadUno" class="agregarOpciones" style="display: none;">
                <span id="descHabilidadoUno"></span>
                <div class="clearfix"></div>
                <a id="eliminarHabilidadUno" class="agregar fl" style="display: none; margin: 0 20px 0 0" onclick="eliminarHabilidad(this.id);">Eliminar</a>
                <a id="agregarHabilidadUno" class="agregar fl" onclick="mostrarComboHabilidades();" style="display: none;">Agregar</a>
                <div class="clearfix"></div>
            </div>
            
            <div id="phabilidadDos" class="agregarOpciones" style="display: none;">
                <span id="descHabilidadDos"></span>
                <div class="clearfix"></div>
                <a id="eliminarHabilidadDos" class="agregar fl" style="display: none; margin: 0 20px 0 0" onclick="eliminarHabilidad(this.id);">Eliminar</a>
                <a id="agregarHabilidadDos" class="agregar fl" onclick="mostrarComboHabilidades();" style="display: none;">Agregar</a>
                <div class="clearfix"></div>
            </div>
            
            <div id="phabilidadTres" class="agregarOpciones" style="display: none;">
                <span id="descHabilidadTres"></span>
                <div class="clearfix"></div>
                <a id="eliminarHabilidadTres" class="agregar fl" style="display: none; margin: 0 20px 0 0" onclick="eliminarHabilidad(this.id);">Eliminar</a>
                <a id="agregarHabilidadTres" class="agregar fl" onclick="mostrarComboHabilidades();" style="display: none;">Agregar</a>
                <div class="clearfix"></div>
            </div>
			<div id="comboHabilidad" style="display: block">
				<select id="idHabilidadSelect" name="idHabilidadSelect" required="false"
				    dojotype="dijit.form.FilteringSelect" store="habilidadStore"  
					maxheight="250" autocomplete="true" onchange="agregarHabilidadFuncion();">
				</select>
			</div>
		</div>
	</div -->
	
	</fieldset>
	</div>
	
	<div class="form_nav"><input type="button" onclick="doSubmit('specificSearch');" class="boton_naranja" value="Buscar"/></div>
</form>
<c:if test="${formaNoValida}">
	<script type="text/javascript">
		alert('Debe ingresar al menos una ocupaci\u00f3n y seleccionar una entidad federativa');
	</script>
</c:if>
<c:if test="${bloquesNoValidos}">
	<script type="text/javascript">
		alert('Debe ingresar al menos una ocupaci\u00f3n y seleccionar una entidad federativa');
	</script>
</c:if>