//necesitas cargar tambien messageHelper

function validateFechaExiste(dia,mes,anio){
	var d = new Date(anio+"/"+mes+"/"+dia);
	if(d.getDate() != dia || d.getDate() == 0){
		
		return false;
	}
	if((d.getMonth()+1) != mes || (d.getMonth()+1)==0){
	
		return false;
	}
	if(d.getFullYear() != anio || d.getFullYear()==0){
		
		return false;
	}
	return isValidDate(d);
	
}

function validateFechaExisteDijit(diaSelect,mesSelect,anioSelect){
	var dia = dijit.byId(diaSelect).get('value');
	var mes = dijit.byId(mesSelect).get('value');
	var anio = dijit.byId(anioSelect).get('value');
	return validateFechaExiste(dia,mes,anio);
}

function isValidDate(d) {
	  if ( Object.prototype.toString.call(d) !== "[object Date]" )
	    return false;
	  return !isNaN(d.getTime());
}


//pasar los parametros del select dia, mes y anio
function validateFromCombos(diaSelect, mesSelect, anioSelect) {

	var dia = dijit.byId(diaSelect).get('value');
	var mes = dijit.byId(mesSelect).get('value');
	var anio = dijit.byId(anioSelect).get('value');

	if (dia == '0' || dia == 'Día') {
		
		dijit.byId(diaSelect).focus();
		dojo.byId(diaSelect).blur();
		message(dijit.byId(diaSelect).get("missingMessage"));
		return 0;
	}

	if (mes == '0' || mes == 'Mes') {
		
		dijit.byId(mesSelect).focus();
		dojo.byId(mesSelect).blur();
		message(dijit.byId(mesSelect).get("missingMessage"));
		return 0;
	}

	if (anio == '0' || anio == 'Año') {
		
		dijit.byId(anioSelect).focus();
		dojo.byId(anioSelect).blur();
		message(dijit.byId(anioSelect).get("missingMessage"));
		return 0;
	}
	
	if (!validateFecha(diaSelect, mesSelect, anioSelect,true)) {
		return false;
	}

	var fhBuscaEmp = new Date();
	return fhBuscaEmp.setFullYear(anio, --mes, dia);
}

//pasamos la fecha contre el dia de hoy y message de error
function validateDateToHoy(fhBuscaEmp, errormessage) {
	var hoy = new Date();

	if (fhBuscaEmp > hoy) {
		//alertMsg('La fecha en que comenzaste a buscar trabajo no puede ser mayor al dia de hoy.');
		message(errormessage);
		return false;
	}
	return true;

}

function validateDate1ToDate2(date1, date2, errormessage) {
	if (date1 > date2) {
		message(errormessage);
		return false;
	}
	return true;
}

function validateData() {
	var count = 0;
	var none = false;
	count = countSelected('idOtrosMedios');
	/*var media = document.registroCandidatoForm.idOtrosMedios;
	
	
	if (media && media.length) {
		for (var i = 0; i < media.length; i++) {
			if (media[i].checked) {
				count++;
				if (media[i].value == 1)
					none = true;
			}
		}
	}
*/
	if (count < 1) {
		
		message('Seleccione al menos un medio que haya utilizado para buscar trabajo');
		return false;
	}
	if (none && count > 1) {
		message('Si selecciona ninguno no puede seleccionar otro medio');
		return false;
	}
	return true;
}

//validar si los campos estan llenados
function validateFecha(diaSelect, mesSelect, anioSelect, requerido) {
	//Validar si los campos son validos
	if (!validaCampoSelect(diaSelect))
		return false;
	if (!validaCampoSelect(mesSelect))
		return false;
	if (!validaCampoSelect(anioSelect))
		return false;
	if(requerido){
		if(!validateFechaExisteDijit(diaSelect,mesSelect,anioSelect)){
			dijit.byId(diaSelect).focus();
	 		message("Seleciona una fecha válida");
			return false;
		}
	}
	else
	{
		if(selecionado(diaSelect,mesSelect,anioSelect)){
			if(!validateFechaExisteDijit(diaSelect,mesSelect,anioSelect)){
				dijit.byId(diaSelect).focus();
		 		message("Seleciona una fecha válida");
				return false;
			}
		}
	
	}
	return true

}

function selecionado(diaSelect,mesSelect,anioSelect){
	var dia = dijit.byId(diaSelect).get('value');
	var mes = dijit.byId(mesSelect).get('value');
	var anio = dijit.byId(anioSelect).get('value');
	if(dia == '0' && mes == '0' && anio =='0'){
		return false;
	}
	return true;
}

