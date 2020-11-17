<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
    /*<![CDATA[*/
        var MAX_ENTRIES = 3;
        var INDEXED_SUFFIX = ""; //"${BusquedaEspecificaOfertasForm.indexedSuffix}";
        
        var depGrado  = new Array(); //Declara arreglo de dependencias de catalogo Grado de Estudios
       	depGrado[parseInt('0')] = '0';	
       	depGrado[parseInt('1')] = '45';
       	depGrado[parseInt('2')] = '45';
       	depGrado[parseInt('3')] = '45';
       	depGrado[parseInt('4')] = '45';
       	depGrado[parseInt('5')] = '40';
       	depGrado[parseInt('6')] = '40';
       	depGrado[parseInt('7')] = '42';
       	depGrado[parseInt('8')] = '42';
       	depGrado[parseInt('9')] = '43';
       	depGrado[parseInt('10')] = '43';
       	depGrado[parseInt('11')] = '44';
       	depGrado[parseInt('12')] = '44';

        dojo.require("dijit.dijit"); // loads the optimized dijit layer
//         dojo.require("dijit.form.Form");
        dojo.require("dijit.form.TextBox");
		dojo.require("dijit.form.ValidationTextBox");
        dojo.require("dijit.form.Select");
        dojo.require("dijit.form.FilteringSelect");
        dojo.require("dijit.form.NumberTextBox");
        dojo.require("dojo._base.array");
        dojo.require("dojo._base.html");
        dojo.require("dojo.NodeList-traverse");
        dojo.require("dojo.NodeList-html");
        dojo.require("dojo.data.ItemFileReadStore");
        
        function actulizaSubArea(id) {
			var vArea = dijit.byId('idAreaSelect').get('value');
			if (vArea) {
				handleSelectedArea(id, 'area');
				subAreaStore.url = '';
				subAreaStore.close();
				dijit.byId('idSubAreaSelect').reset();
				subAreaStore.url = "${context}busquedaEspecificaCandidatos.do?method=subareaLaboraList" + "&" + "idAreaLaboral=" + vArea;
				subAreaStore.close();
			}
		}
        
        function doSubmit(formId) {
			form = dojo.byId(formId);
			if (checkSalary()) {
    			if (isFormValid()) {
	    			var idarea = dijit.byId('idAreaSelect').get('value');
					var idsubarea = dijit.byId('idSubAreaSelect').get('value');
					dojo.byId('idArea').value = idarea;
					dojo.byId('idSubArea').value = idsubarea;
    				form.submit();
    			}
    		}
    	}
        
        function isFormValid() {
        	var validForm = hasSelectedFilters();
        	var area = dojo.byId('idAreaSelect').value;
			var subarea = dojo.byId('idSubAreaSelect').value;
			if (area == '') {
				alert('Debe seleccionar \u00e1rea laboral');
				return false;
			}
			if (subarea == '') {
				alert('Debe seleccionar sub\u00e1rea laboral');
				return false;
			}
        	return true;
        }
        
    	// Check at least one filter was selected
    	// It's supposed to be all valid id's greater than zero
        function hasSelectedFilters() {    		
        	var selectedFilters = 0;
    		//selectedFilters += parseInt(dojo.query("input[type='hidden'][name^='jobs']").length) == 0 ? 0 : 1;
    		//selectedFilters += parseInt(dojo.byId("subprogram").value) == 0 ? 0 : 1;
    		selectedFilters += parseInt(dojo.byId("locationEntity").value) == 0 ? 0 : 1;
    		selectedFilters += parseInt(dojo.query("input[type='hidden'][name^='locationMunicipalities']").length) == 0 ? 0 : 1;
    		selectedFilters += parseInt(dojo.byId("locationRegion").value) == 0 ? 0 : 1;
    		selectedFilters += parseInt((dojo.byId("minSalary").value) || 0) == 0 ? 0 : 1;
    		selectedFilters += parseInt(dojo.byId("maxSalary").value || 0) == 0 ? 0 : 1;
    		//selectedFilters += parseInt(dojo.byId("age").value || 0) == 0 ? 0 : 1;
    		selectedFilters += parseInt(dojo.byId("educationGrade").value) == 0 ? 0 : 1;
    		selectedFilters += parseInt(dojo.query("input[type='hidden'][name^='educationCareers']").length) == 0 ? 0 : 1;
    		//selectedFilters += parseInt(dojo.byId("employmentType").value) == 0 ? 0 : 1;
    		//selectedFilters += parseInt(dojo.byId("contractType").value) == 0 ? 0 : 1;
    		selectedFilters += parseInt(dojo.query("input[type='hidden'][name^='enterpriseActivities']").length) == 0 ? 0 : 1;
    		selectedFilters += parseInt(dojo.byId("codigoPostal").value || 0) == 0 ? 0 : 1;
    		return selectedFilters > 0;
        }

        function autocompleteOcupacion(widgetId, containerId, listId){
            var searchTxt = dojo.byId(widgetId).value;
            searchTxt = dojo.trim(searchTxt);
            console.log(searchTxt);

            if(searchTxt.length < 2) {
                //cerrarAutocomplete();
            } else if(searchTxt.length >= 2){

                dojo.xhrPost({url: '<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=buscarOcupacionesViaAjax&search=' + searchTxt,
						handleAs : "text",
						headers : {
							"Content-Type" : "application/x-www-form-urlencoded; charset=ISO-8859-1"
						},
						load : function(result) {
							if (result.length > 0) {
								dojo.byId(containerId).style.display = 'block';
								dojo.byId(listId).innerHTML = result;
								//dojo.byId(containerId).focus();
								//dojo.byId(containerId).setAttribute("contenteditable",true);
							} else {
								//cerrarAutocomplete(widgetId, containerId, listId);
							}
						}
					});
		}
	}

	function cerrarAutocomplete(widgetId, containerId, listId) {
		if (containerId != null) {
			dijit.byId(widgetId).reset();
			dojo.byId(containerId).style.display = 'none';
			//dojo.byId(containerId).setAttribute("contenteditable",false);
			dojo.byId(listId).innerHTML = "";
		}
	}

	// This method is meant to be invoked by the returned AJAX invocation
	function setOcupacion(id, value) {

		// It must be known the widgetId, div#job_div & ul#job_ul
		var widgetId = "agregaPerfil";
		var jobDiv = "job_div";
		var jobUl = "job_ul";

		// The container
		dojo.style(dojo.byId(jobDiv), "display", "none");

		// The widget must be hidden, so no value is required
		dojo.byId(widgetId).value = "";
		//            dojo.byId(widgetId).focus();
		//         cerrarAutocomplete(idDivCerrar,idLIstaCerrar);

		var prefix = '${BusquedaEspecificaOfertasForm.jobIndexedPrefix}';
		var addButtonId = "add_" + prefix;

		handleAutocompleteSelectedData(widgetId, prefix, addButtonId, id, value);
	}

	function handleAutocompleteSelectedData(widgetId, prefix, addButtonId,
			hiddenValue, text) {

		var widget = dijit.byId(widgetId); // the 'main input'

		var containerId = prefix + "_wish_list";

		if (countElementChildren(containerId, "p") >= MAX_ENTRIES) {
			// No more jobCatalog allowed"
			return;
		}

		var actionFormField = prefix + INDEXED_SUFFIX; // Must match the ActionForm indexed property
		var lastIndex = obtainLastIndex("hidden", actionFormField); //input[type='hidden'], prefixItemIds
		var newIndex = 0;
		if (lastIndex != undefined) {
			newIndex = parseInt(lastIndex) + 1; // for doing an arithmetic sum and not a concatenation
		}

		var newElementId = prefix + "_add_" + newIndex;
		var delElementId = prefix + "_del_" + newIndex;

		var composedValue = hiddenValue + ":" + text; // id + ":" + txt;

		var _htmlAddJob = "";
		var _htmlJob = "<span>"
				+ text
				+ "</span><a id='"
				+ delElementId
				+ "' class='btn-eliminar' href='javascript:void(0)' widget-id='"
				+ widget.id
				+ "'>Eliminar</a>"
				+ _htmlAddJob
				+ "<input type='hidden' name='" + actionFormField + "[" + newIndex + "]' value='" + composedValue + "' />";
		// TRICK: Notice a non-valid html attribute "widget-id" is created

		var p = dojo.create("p", {
			innerHTML : _htmlJob
		});
		dojo.attr(p, "id", newElementId);
		dojo.attr(p, "class", "agregarOpciones");
		dojo.place(p, containerId);

		juggleButton(containerId, addButtonId);

		// Bind event onclick
		dojo.connect(dojo.byId(delElementId), "onclick", "removeContainer");

		// Reveal or Conceal
		if (countElementChildren(containerId, "p") >= MAX_ENTRIES) {
			dojo.style(dojo.byId(addButtonId), "display", "none");
			widget.domNode.style.visibility = "hidden";
		} else { // Allow more elements
			dojo.style(dojo.byId(addButtonId), "display", "block");
			widget.domNode.style.visibility = "hidden";
		}
	}

	function handleInputData(widgetId, prefix, addButtonId) {
		var widget = dijit.byId(widgetId); // the 'main input'

		var containerId = prefix + "_wish_list";

		if (countElementChildren(containerId, "p") >= MAX_ENTRIES) {
			// No more jobCatalog allowed"
			return;
		}

		var id = widget.get('value');
		var txt = widget.attr('displayedValue');
		var composedValue = id + ":" + txt;

		if (composedValue == ":") {
			// Not a valid selection, therefore
			return;
		}

		var actionFormField = prefix + INDEXED_SUFFIX; // Must match the ActionForm indexed property
		var lastIndex = obtainLastIndex("hidden", actionFormField); //input[type='hidden'], prefixItemIds
		var newIndex = 0;
		if (lastIndex != undefined) {
			newIndex = parseInt(lastIndex) + 1; // for doing an arithmetic sum and not a concatenation
		}

		var newElementId = prefix + "_add_" + newIndex;
		var delElementId = prefix + "_del_" + newIndex;

		var _htmlJob = "<span>"
				+ txt
				+ "</span><a id='"
				+ delElementId
				+ "' class='btn-eliminar' href='javascript:void(0)' widget-id='"
				+ widget.id
				+ "'>Eliminar</a>"
				+ "<input type='hidden' name='" + actionFormField + "[" + newIndex + "]' value='" + composedValue + "' />";
		// TRICK: Notice a non-valid html attribute "widget-id" is created

		var p = dojo.create("p", {
			innerHTML : _htmlJob
		});
		dojo.attr(p, "id", newElementId);
		dojo.attr(p, "class", "agregarOpciones");
		dojo.place(p, containerId);

		juggleButton(containerId, addButtonId);

		// Bind event onclick
		dojo.connect(dojo.byId(delElementId), "onclick", "removeContainer");

		// Reveal or Conceal
		if (countElementChildren(containerId, "p") >= MAX_ENTRIES) {
			dojo.style(dojo.byId(addButtonId), "display", "none");
			widget.domNode.style.visibility = "hidden";
		} else { // Allow more elements
			dojo.style(dojo.byId(addButtonId), "display", "block");
			widget.domNode.style.visibility = "hidden";
		}
	}

	function juggleButton(containerId, addButtonId) {
		var container = dojo.byId(containerId);
		var aElement = dojo.byId(addButtonId); // addButton

		var pElements = dojo.query("p.agregarOpciones", container);
		var pLastChild = pElements.last()[0]; // refNode
		if (pLastChild != undefined) {
			dojo.place(aElement, pLastChild);
		} else {
			dojo.place(aElement, container);
		}
	}

	function handleAddEntry(addButtonId, containerId, widgetId) {
		juggleButton(containerId, addButtonId);

		if (countElementChildren(containerId, "p") < MAX_ENTRIES) { // More Items are allowed
			dojo.style(dojo.byId(addButtonId), "display", "none");
			dijit.byId(widgetId).domNode.style.visibility = "visible";
		}

		//dojo.window.scrollIntoView(widgetId);
	}

	function removeContainer(event) {
		var target = dojo.query(event.currentTarget); // nodeList
		var delButton = target[0]; // <a class='btn-eliminar'>
		var pElement = delButton.parentNode; // <p class='agregarOpciones'>
		var divElement = pElement.parentNode; // <div>

		// Move addButton to the div container
		var addButton = dojo.query("a.agregar", divElement)[0]; // <a class="agregar">
		dojo.place(addButton, divElement);

		dojo.destroy(pElement);

		var containerId = dojo.attr(divElement, "id");
		var addButtonId = dojo.attr(addButton, "id");
		var widgetId = dojo.attr(delButton, "widget-id"); // TRICK: the non-valid widget-id html attribute is read

		juggleButton(containerId, addButtonId);

		var widget = dijit.byId(widgetId);

		if (countElementChildren(containerId, "p") < MAX_ENTRIES) { // Does parent node contains children?
			widget.domNode.style.visibility = "hidden"; // Reveals the widget of parent node whose class is 'opciones'
			dojo.style(addButton, "display", "block"); // Reveals addButton
		}

		if (countElementChildren(containerId, "p") == 0) { // Does parent node contains children?
		//                resetInput(widget);
			widget.domNode.style.visibility = "visible"; // Reveals the widget of parent node whose class is 'opciones'
			dojo.style(addButton, "display", "none"); // Conceals addButton
		}
	}

	function countElementChildren(containerId, type) {
		// Count how many children type has the container
		var children = dojo.query("#" + containerId).children(type);
		return children.length;
	}

	function obtainLastIndex(inputType, prefix) {
		// find all input elements by type and prefix name attribute
		var elements = dojo.query("input[type=" + inputType + "][name^='" + prefix + "']");
		
		if (elements[0] == undefined) {
			return undefined;
		}

		var indexes = [];

		dojo.forEach(elements, function(item, index) {
			var indexValue = readIndex(item);
			indexes.push(indexValue);
		});

		// Sort the array ascending
		indexes.sort(function(a, b) {
			return a - b;
		});

		lastIndex = indexes[indexes.length - 1];

		return lastIndex;
	}

	function readIndex(input) {
		var nameAttr = dojo.attr(input, 'name');
		var idx1 = nameAttr.indexOf("[") + 1;
		var idx2 = nameAttr.indexOf("]");
		return nameAttr.substring(idx1, idx2);
	}

	function buildInputHiddenStr(name, value, index) {
		if (index == undefined) {
			return "<input type='hidden' name='" + name + "' value='" + value + "' />";
		} else { // Indexed field
			return "<input type='hidden' name='" + name + "[" + index + "]' value='" + value + "' />";
		}
	}

	function handleSelectedRegion(widgetId, inputHiddenId, secondaryWidgetId) {
		var widget = dijit.byId(widgetId);
		var region = widget.get('value');
		dijit.byId(secondaryWidgetId).set("disabled", true); // disable
		//dijit.byId("minSalary").set("disabled", true);
		//dijit.byId("maxSalary").set("disabled", true);
		//dijit.byId("age").set("disabled", true);
		//dijit.byId("escolaridad").set("disabled", true);
		//dijit.byId("tipoempleo").set("disabled", true);
		//dijit.byId("tipocontrato").set("disabled", true);
		//dijit.byId("agregaActividadEmpresa").set("disabled", true);
		//dijit.byId("idsubprogram").set("disabled", true);
		if (region == 0) {
			dijit.byId(secondaryWidgetId).set("disabled", false);
			dijit.byId("region").set("disabled", false); // Enable "region" widget
			//dijit.byId("minSalary").set("disabled", false);
			//dijit.byId("maxSalary").set("disabled", false);
			//dijit.byId("age").set("disabled", false);
			//dijit.byId("escolaridad").set("disabled", false);
			//dijit.byId("tipoempleo").set("disabled", false);
			//dijit.byId("tipocontrato").set("disabled", false);
			//dijit.byId("agregaActividadEmpresa").set("disabled", false);
			//dijit.byId("idsubprogram").set("disabled", false);
		}
		handleSelectedValue(widgetId, inputHiddenId);

	}

	function handleSelectedLocation(mainWidgetId, inputHiddenId,
			secondaryWidgetId, containerId) {
		var widget = dijit.byId(mainWidgetId);

		handleSelectedValue(mainWidgetId, inputHiddenId);

		dijit.byId("region").set("disabled", true); // Disable "region" widget
		//dijit.byId("minSalary").set("disabled", true);
		//dijit.byId("maxSalary").set("disabled", true);
		//dijit.byId("age").set("disabled", true);
		//dijit.byId("escolaridad").set("disabled", true);
		//dijit.byId("tipoempleo").set("disabled", true);
		//dijit.byId("tipocontrato").set("disabled", true);
		//dijit.byId("agregaActividadEmpresa").set("disabled", true);
		//dijit.byId("idsubprogram").set("disabled", true);
		dijit.byId(secondaryWidgetId).set("disabled", false); // Enable '#agregaMuni' for multiple choices

		var entityId = widget.get('value');
		var txt = widget.attr('displayedValue');
		var composedValue = entityId + ":" + txt;
		dojo.byId(inputHiddenId).value = composedValue;
		if (entityId == 0) {
			dijit.byId("region").set("disabled", false); // Enable "region" widget
			//dijit.byId("minSalary").set("disabled", false);
			//dijit.byId("maxSalary").set("disabled", false);
			//dijit.byId("age").set("disabled", false);
			//dijit.byId("escolaridad").set("disabled", false);
			//dijit.byId("tipoempleo").set("disabled", false);
			//dijit.byId("tipocontrato").set("disabled", false);
			//dijit.byId("agregaActividadEmpresa").set("disabled", false);
			//dijit.byId("idsubprogram").set("disabled", false);	
		}
		// Make read-only widget
		//widget.set("disabled", true);
		//widget.set("readOnly", true);

		// Remove all children from container by invoking the onClick event, which already has functionality
		dojo.query("#" + containerId).children("p").children("a.btn-eliminar")
				.forEach(function(e) {
					e.click();
				});

		if (dijit.byId(secondaryWidgetId).get("disabled") == true) {
			dijit.byId(secondaryWidgetId).set("disabled", false); // Enable	
		}

		// Load municipalities via AJAX
		if (entityId) {
			municipalitiesStore.url = '';
			municipalitiesStore.close();
			dijit.byId(secondaryWidgetId).reset(); // NOTE: Invoke this method here...
			//widget.reset();
			//widget.disabled=false;
			municipalitiesStore.url = "${context}domicilio.do?method=obtenerMunicipio" + "&" + "idEntidad=" + entityId;
			municipalitiesStore.close();
			//widget.set('disabled', false);
			//cleanAllMunicipalities();
		}
	}

	function handleSelectedEducation(mainWidgetId, inputHiddenId, secondaryWidgetId, containerId) {
		var widget = dijit.byId(mainWidgetId);

		handleSelectedValue(mainWidgetId, inputHiddenId);

		var educationGradeId = widget.get('value');
		var txt = widget.attr('displayedValue');
		var composedValue = educationGradeId + ":" + txt;

		dojo.byId(inputHiddenId).value = composedValue;

		// Make read-only widget
		//widget.set("disabled", true);
		//widget.set("readOnly", true);

		// Remove all children from container by invoking the onClick event, which already has functionality
		dojo.query("#" + containerId).children("p").children("a.btn-eliminar")
				.forEach(function(e) {
					e.click();
				});

		if (dijit.byId(secondaryWidgetId).get("disabled") == true) {
			dijit.byId(secondaryWidgetId).set("disabled", false); // Enable	
		}

		var vGrado = educationGradeId;
		var vIdCatDep = depGrado[vGrado];

		// Load educationCareers via AJAX
		if (vIdCatDep && vIdCatDep != 0) {
			educationCareersStore.url = '';
			educationCareersStore.close();
			dijit.byId(secondaryWidgetId).reset(); // NOTE: Invoke this method here...
			//widget.reset();
			//widget.disabled=false;
			educationCareersStore.url = "${context}registro.do?method=carreras&idCatDep=" + vIdCatDep;
			educationCareersStore.close();
			//widget.set('disabled', false);
		}

	}
	
	function handleSelectedEmploymentType(widgetId, inputHiddenId) {
		handleSelectedValue(widgetId, inputHiddenId);
	}
	
	function handleSelectedContractType(widgetId, inputHiddenId) {
		handleSelectedValue(widgetId, inputHiddenId);
	}
	
	function handleSelectedSubprogram(widgetId, inputHiddenId) {
		handleSelectedValue(widgetId, inputHiddenId);
	}
	
	function handleSelectedArea(widgetId, inputHiddenId) {
		handleSelectedValue(widgetId, inputHiddenId);
	}
	
	function handleSelectedSubArea(widgetId, inputHiddenId) {
		handleSelectedValue(widgetId, inputHiddenId);
	}

	function handleSelectedValue(widgetId, inputHiddenId) {
        var widget = dijit.byId(widgetId);
        var id = widget.value;
        var txt = widget.displayedValue;
		var composedValue = id + ":" + txt;
		// Assign value to input hidden
		dojo.attr(dojo.byId(inputHiddenId), "value", composedValue);
	}

	function checkSalary() {
		var minSalaryWidget = dijit.byId("minSalary");
		var maxSalaryWidget = dijit.byId("maxSalary");

		if (minSalaryWidget.get("value") != "" || maxSalaryWidget.get("value") != "") {
			minSalaryWidget.set("required", true);
			minSalaryWidget.constraints.max = maxSalaryWidget.get('value') || 999999;

			maxSalaryWidget.set("required", true);
			maxSalaryWidget.constraints.min = minSalaryWidget.get('value') || 0;

			minSalaryWidget.validate();
			maxSalaryWidget.validate();

			if (parseInt(minSalaryWidget.get('value')) > parseInt(minSalaryWidget.constraints.max)) {
				//minSalaryWidget._set("state", "Error");
				displayErrorMsg(minSalaryWidget, "Rango incorrecto");
				displayErrorMsg(maxSalaryWidget, "Rango incorrecto");
				return false;
			}
		} else if (minSalaryWidget.get("value") == "" && maxSalaryWidget.get("value") == "") {
			minSalaryWidget.set("required", false);
			minSalaryWidget.validate();

			maxSalaryWidget.set("required", false);
			maxSalaryWidget.validate();
		}
		return true;
	}

	function displayErrorMsg(textBox, errmsg) {
		var originalValidator = textBox.validator;
		textBox.validator = function() {
			return false;
		};
		textBox.validate();
		textBox.validator = originalValidator;

		dijit.showTooltip(
		//textBox.get("invalidMessage"),
		errmsg, textBox.domNode, textBox.get("tooltipPosition"), !textBox.isLeftToRight());
	}

	function addSelectAnOption(widgetId) {
		var o = dojo.create("option", {
			label : "Seleccione una opción",
			value : "0",
			selected : true
		});
		dijit.byId(widgetId).addOption(o);
	}

	function addSelectAnOptionToStore(storeId) {

	}
	
	
	

	/*]]>*/
</script>


	
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>



<html:messages id="errors">
    <span class="redFont Font80" ><bean:write name="errors"/></span><br/>
</html:messages>
<html:messages id="messages" message="true">
    <span class="Font80"><bean:write name="messages"/></span><br/>
</html:messages>

<logic:equal name="BusquedaEspecificaOfertasForm"
	property="candidateLogged" value="true">
	<div class="tab_block" style="margin-top: 0">
		<div id="returnME" style="top: 40px"> 
			<a href="<%=request.getContextPath()%>/miEspacioCandidato.do">Regresar a mi espacio</a>
		</div>
	</div>
</logic:equal>
<div dojoType="dojo.data.ItemFileReadStore" jsId="areaLaboralStore" url="${context}busquedaEspecificaCandidatos.do?method=areaLaboraList"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="subAreaStore" url="${context}busquedaEspecificaCandidatos.do?method=subareaLaboraList"></div>
<form id="searchForm" name="searchForm" class="formApp" method="post" action="<%=response.encodeURL(request.getContextPath() + "/busquedaEspecificaOfertas.do?method=encontrar")%>">
	<input type="hidden" name="idArea" id="idArea" value="" dojoType="dijit.form.TextBox" />
	<input type="hidden" name="idSubArea" id="idSubArea" value="" dojoType="dijit.form.TextBox" />
	<div class="row">
		<div class="col-sm-12">
			<ol class="breadcrumb">
				<li>Ruta de navegación:</li>
				<li><a href="#">Inicio</a></li>
				<li class="active">Herramientas del Sitio</li>
			</ol>
		</div>
		<div class="col-sm-12">
			<div class="page-header">
				<h3>Búsqueda específica <small>Puedes utilizar una o más de las opciones de búsqueda</small></h3>
			</div>
		</div>
		<div class="col-sm-12">
			<fieldset class="panel panel-PE">
				<div class="panel-heading">
					<legend class="panel-title">Detalles de la búsqueda</legend>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<div class="form-group">
									<label class="formInfo">Escribe y selecciona el perfil laboral que buscas</label>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="idAreaSelect">
									<strong>Área laboral</strong>
								</label><br/>
								<select id="idAreaSelect" name="idAreaSelect" dojotype="dijit.form.FilteringSelect" class="form-control"
									store="areaLaboralStore" required="false" missingMessage="Es necesario indicar el &#225;rea laboral."
									scrollOnFocus="false" maxheight="250" autocomplete="true" lang="es"
									onchange="javascript:actulizaSubArea(this.id);">
								</select>
								<html:hidden name="BusquedaEspecificaOfertasForm" property="area" value="0" styleId="area" />
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label>
									<strong>Sub&#225;rea laboral</strong>
								</label><br/>
								<select id="idSubAreaSelect" name="idSubAreaSelect" dojotype="dijit.form.FilteringSelect" class="form-control"
									store="subAreaStore" required="false" missingMessage="Es necesario indicar la sub &#225;rea laboral."
									onchange="handleSelectedSubArea(this.id, 'subarea')" scrollOnFocus="false" maxheight="250" autocomplete="true" lang="es">
								</select>
								<html:hidden name="BusquedaEspecificaOfertasForm" property="subarea" value="0" styleId="subarea" />
							</div>
						</div>
						<div class="clearfix"></div>
						<!--div class="col-sm-4">
							<div class="form-group">
								<label for="idsubprogram"><strong>Subprograma</strong></label>
								<span dojoType="dojo.data.ItemFileReadStore" jsId="subprogramStore" url="<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=getSubprogram"/>
								<select id="idsubprogram" class="form-control" onchange="handleSelectedSubprogram(this.id, 'subprogram')" dojoType="dijit.form.FilteringSelect" store="subprogramStore" value="0">
								</select>
								<html:hidden name="BusquedaEspecificaOfertasForm" property="subprogram" value="0" styleId="subprogram" />
							</div>
						</div-->
						<div class="col-sm-6">
							<div class="form-group" id="location_entities" role="content">
							<!-- Location Entities -->
								<label for="entidad">Entidad</label> 
								<select id="entidad" onchange="handleSelectedLocation(this.id, 'locationEntity', 'agregaMuni', '${BusquedaEspecificaOfertasForm.locationMunicipalityIndexedPrefix}_wish_list')" 
									dojoType="dijit.form.FilteringSelect" class="form-control">
									<option value="0">Seleccione una opci&oacute;n</option>
									<logic:iterate id="locationEntity" name="BusquedaEspecificaOfertasForm" property="locationEntityCatalog">
										<option value="${locationEntity.idCatalogoOpcion}">${locationEntity.opcion}</option>
									</logic:iterate>
								</select>
								<!--ActionForm Field-->
								<html:hidden name="BusquedaEspecificaOfertasForm"
									property="locationEntity" value="0" styleId="locationEntity" />
		
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">	
								<label id="munDel" for="agregaMuni">Municipio(s) o Delegación(es)</label>
								<div class="opciones">
									<div id="${BusquedaEspecificaOfertasForm.locationMunicipalityIndexedPrefix}_wish_list" role="content">
										<!-- Dynamic container -->
										<a id="add_${BusquedaEspecificaOfertasForm.locationMunicipalityIndexedPrefix}" class="agregar" style="display: none" href="javascript:void(0)" onclick="handleAddEntry('add_${BusquedaEspecificaOfertasForm.locationMunicipalityIndexedPrefix}', '${BusquedaEspecificaOfertasForm.locationMunicipalityIndexedPrefix}_wish_list', 'agregaMuni')" role="button">Agregar</a>
									</div>
									<span class="clearfix"></span>
									<div class="entry_point" role="content">
										<!-- Dojo Store -->
										<span dojoType="dojo.data.ItemFileReadStore" jsId="municipalitiesStore" urlPreventCache="true" clearOnClose="true" /> 
										<select id="agregaMuni" class="mainInput form-control" onchange="handleInputData(this.id, '${BusquedaEspecificaOfertasForm.locationMunicipalityIndexedPrefix}', 'add_${BusquedaEspecificaOfertasForm.locationMunicipalityIndexedPrefix}')" dojoType="dijit.form.FilteringSelect" store="municipalitiesStore" disabled="disabled" required="false">
											<!-- option's are loaded via AJAX -->
										</select>
										<span class="clearfix"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<input id="codigoPostal" type="hidden"  class="form-control" name="codigoPostal" type="text"  trim="true" required="false" oncopy="return false;" oncut="return false" onpaste="return false;"  value="" size="6" maxlength="5" />		
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								</br>
								<label class="desactivado" for="region"><strong>Estados por región</strong></label> 
								<select id="region" class="form-control" onchange="handleSelectedRegion(this.id, 'locationRegion', 'entidad')" dojoType="dijit.form.FilteringSelect">
									<option value="0">Seleccione una opci&oacute;n</option>
									<logic:iterate id="locationRegion" name="BusquedaEspecificaOfertasForm" property="locationRegionCatalog">
										<option value="${locationRegion.idCatalogoOpcion}">${locationRegion.opcion}</option>
									</logic:iterate>
								</select>
								<!-- ActionForm Field -->
								<html:hidden name="BusquedaEspecificaOfertasForm" property="locationRegion" value="0" styleId="locationRegion" />
								</br>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="minSalary"><strong>Sueldo deseado</strong> De</label>
								<div class="input-group">
	                                <span class="input-group-addon">$</span> 
									<input type="text" id="minSalary" class="form-control" name="minSalary" value="" onchange="checkSalary()" maxlength="9" dojoType="dijit.form.ValidationTextBox" regExp="^[1]?\d{1,5}(\.\d{1,2})?$" invalidMessage="Cantidad inválida favor de verificar." />
								</div>
								<small>Para usar este filtro es necesario indicar ambos límites de sueldo, de lo contrario no será usado</small>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="maxSalary">A</label> 
								<div class="input-group">
	                                <span class="input-group-addon">$</span>
									<input type="text" id="maxSalary" class="form-control" name="maxSalary" value="" onchange="checkSalary()" maxlength="9" dojoType="dijit.form.ValidationTextBox" regExp="^[1]?\d{1,5}(\.\d{1,2})?$" invalidMessage="Cantidad inválida favor de verificar." />
	                            </div>
							</div>
						</div>
						<div class="col-sm-4">
							<!-- div class="form-group">
							<label for="age"><strong>Edad</strong></label>
							<div class="input-group">
								<input type="text" id="age" class="form-control" name="age" value="" dojoType="dijit.form.NumberTextBox" constraints="{min:15, max:100, places:0, pattern:'##'}" /> 
								<span class="input-group-addon">años</span>
							</div -->
						</div>
					</div>
					<div class="row">
						<div class="clearfix"></div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="escolaridad"><strong>Escolaridad (nivel máximo de estudios)</strong></label>
								<span dojoType="dojo.data.ItemFileReadStore" jsId="educationGradeStore" url="<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=obtainEducationGradeViaAjax"/>
								<select id="escolaridad" class="form-control" onchange="handleSelectedEducation(this.id, 'educationGrade', 'agregaCarrera', '${BusquedaEspecificaOfertasForm.educationCareerIndexedPrefix}_wish_list')" dojoType="dijit.form.FilteringSelect" store="educationGradeStore" value="0">
									<!-- <option value="0">Seleccione una opci&oacute;n</option> -->
									<%-- <logic:iterate id="educationGrade" name="BusquedaEspecificaOfertasForm" property="educationGradeCatalog"> --%>
									<%-- 	<option value='${educationGrade.idCatalogoOpcion}'>${educationGrade.opcion}</option>	 --%>
									<%-- </logic:iterate> --%>
								</select>
								<!-- ActionForm Field&ndash -->
								<html:hidden name="BusquedaEspecificaOfertasForm" property="educationGrade" value="0" styleId="educationGrade" />
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="agregaCarrera"><strong>Carrera(s)</strong></label>
								<div class="opciones">
									<div id="${BusquedaEspecificaOfertasForm.educationCareerIndexedPrefix}_wish_list" role="content">
										<!-- Dynamic container -->
										<a id="add_${BusquedaEspecificaOfertasForm.educationCareerIndexedPrefix}" class="agregar" style="display: none" href="javascript:void(0)" onclick="handleAddEntry('add_${BusquedaEspecificaOfertasForm.educationCareerIndexedPrefix}', '${BusquedaEspecificaOfertasForm.educationCareerIndexedPrefix}_wish_list', 'agregaCarrera')" role="button">Agregar</a>
									</div>
									<span class="clearfix"></span>
									<div class="entry_point" role="content">
										<!-- Dojo Store -->
										<span dojoType="dojo.data.ItemFileReadStore" jsId="educationCareersStore" urlPreventCache="true" clearOnClose="true" />
										<select id="agregaCarrera" class="form-control" onchange="handleInputData(this.id, '${BusquedaEspecificaOfertasForm.educationCareerIndexedPrefix}', 'add_${BusquedaEspecificaOfertasForm.educationCareerIndexedPrefix}')" disabled="disabled" dojoType="dijit.form.FilteringSelect" store="educationCareersStore" required="false"/>
											<!-- option's are loaded via AJAX -->
										</select>
									</div>
								</div>
							</div>
						</div>
						<!-- div class="col-sm-6">
							<div class="form-group">
								<label for="tipoempleo"><strong>Tipo de empleo</strong></label>
								<span dojoType="dojo.data.ItemFileReadStore" jsId="employmentTypeStore" url="<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=obtainEmploymentTypeViaAjax"/>
								<select id="tipoempleo" class="form-control" onchange="handleSelectedEmploymentType(this.id, 'employmentType')" dojoType="dijit.form.FilteringSelect" store="employmentTypeStore" value="0">
									<!-- <option value="0">Selecccione una opci&oacute;n</option> -->
									<%-- <logic:iterate id="employment" name="BusquedaEspecificaOfertasForm" property="employmentCatalog"> --%>
									<%-- 	<option value="${employment.idCatalogoOpcion}">${employment.opcion}</option> --%>
									<%-- </logic:iterate>
								</select>
								<html:hidden name="BusquedaEspecificaOfertasForm" property="employmentType" value="0" styleId="employmentType" />
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="tipocontrato"><strong>Tipo de contrato</strong></label>
								<span dojoType="dojo.data.ItemFileReadStore" jsId="contractTypeStore" url="<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=obtainContractTypeViaAjax"/>
								<select id="tipocontrato" class="form-control" onchange="handleSelectedContractType(this.id, 'contractType')" dojoType="dijit.form.FilteringSelect" store="contractTypeStore" value="0">
									<!-- <option value="0">Seleccione una opci&oacute;n</option> -->
									<%-- <logic:iterate id="contract" name="BusquedaEspecificaOfertasForm" property="contractCatalog"> --%>
									<%-- 	<option value="${contract.idCatalogoOpcion}">${contract.opcion}</option> --%>
									<%-- </logic:iterate>
								</select>
								<!-- ActionForm Field -->
								<html:hidden name="BusquedaEspecificaOfertasForm" property="contractType" value="0" styleId="contractType" />
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label for="agregaActividadEmpresa"><strong>Actividad de la empresa</strong></label>
								<div class="opciones">
									<div id="${BusquedaEspecificaOfertasForm.enterpriseActivityIndexedPrefix}_wish_list" role="content">
										<a id="add_${BusquedaEspecificaOfertasForm.enterpriseActivityIndexedPrefix}" class="agregar" style="display: none" href="javascript:void(0)" onclick="handleAddEntry('add_${BusquedaEspecificaOfertasForm.enterpriseActivityIndexedPrefix}', '${BusquedaEspecificaOfertasForm.enterpriseActivityIndexedPrefix}_wish_list', 'agregaActividadEmpresa')" role="button">Agregar</a>
									</div>
									<span class="clearfix"></span>
									<div class="entry_point" role="content">
										<span dojoType="dojo.data.ItemFileReadStore" jsId="enterpriseActivityStore" url="<%=request.getContextPath()%>/busquedaEspecificaOfertas.do?method=obtainEnterpriseActivityViaAjax"/>
										<select id="agregaActividadEmpresa" class="form-control" onchange="handleInputData(this.id, '${BusquedaEspecificaOfertasForm.enterpriseActivityIndexedPrefix}', 'add_${BusquedaEspecificaOfertasForm.enterpriseActivityIndexedPrefix}')" dojoType="dijit.form.FilteringSelect" store="enterpriseActivityStore" value="0">
											<!-- <option value="0">Seleccione una opci&oacute;n</option> -->
											<%-- <logic:iterate id="enterpriseActivity" name="BusquedaEspecificaOfertasForm" property="enterpriseActivityCatalog"> --%>
											<%-- 	<option value="${enterpriseActivity.idCatalogoOpcion}">${enterpriseActivity.opcion}</option> --%>
											<%-- </logic:iterate>
										</select> 
									</div>
								</div>
							</div>
						</div--%>
					</div>
				</div>
			</fieldset>
		</div>
		
		<div class="form_nav text-center">
			<div class="form-group">
				<br><input type="button" onclick="doSubmit('searchForm')" value="Buscar" class="btnPE" /></brp>
			</div>
		</div>
		
		
	</div>


			

	
<div class="clearfix"></div>	
<h2></h2>
<p></p>
	
	
	<%--JOBS: Loaded dinamically via AJAX--%>
	 <fieldset class="busquedaEspecifica">
	 <legend></legend>
       
	<%--JOBS: Loaded dinamically via AJAX--%>
	
		<div class="labeled margin_top_10  fl">
		<div class="opciones grid1_3">
		
		</div>
	</div>
<div class="clearfix"></div>

	<!--LOCATION-->
	

		<!-- Location Municiplities -->
	
<div class="clearfix"></div>
		<!-- Location Regions -->
		<div class="grid1_3 margin_top_10 fl" id="location_region" role="content">
		
			
		
	</div>
<div class="clearfix"></div>
	<!-- SALARY -->
	<div class="margin_top_10 ubicacion_1">
		<div class="grid1_3 fl">
			 
		</div>	
		<div class="grid_3 fl ubicacion_1">
			 
		</div>
		<div class="clearfix"></div>	
			
		
	</div>

	<%-- AGE --%>
	<div class="margin_top_10 fl">
		<div class="fl">
			
		</div>
	</div>
<div class="clearfix"></div>
	<%-- EDUCATION --%>
	<div class="grid1_3 margin_top_10 fl">
		
			
		
</div>
<div class="grid1_3 margin_top_10 fl">
		
	</div>
<div class="clearfix"></div>
	<%-- EMPLOYMENT --%>
	<div class="grid1_3 margin_top_10 fl">
		
			
</div>
			<!-- Contract Type -->
			<div class="grid1_3 margin_top_10 fl">	
			
	</div>
<div class="clearfix"></div>
	<%-- ENTERPRISE ACTIVITY --%>
	<div class="grid1_3 margin_top_10 fl">
		
	</div>
	</fieldset>
     <div class="form_nav">
		
	</div>

</form>