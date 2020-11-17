	function validaCorreoElectronicoUnico() {
		var unico = true;
		var control = dijit.byId('correoElectronico');
		disabled = control.disabled;
	//	alert('correo: ' + dijit.byId('correoElectronico').value +' disabled ' + disabled)
		if (dijit.byId('correoElectronico').value == '') {
			return true; // ya que el correo es OPCIONAL no se valida
		}
		control.disabled = false;
		dojo.byId('method').value = 'validaCorreoElectronicoUnico';

		dojo.xhrPost({
			url : 'registro.do',
			form : 'registroCandidatoForm',
			sync : true,
			load : function(data) {
				var res = dojo.fromJson(data);

				if ('exito' == res.type) {
					if ('unico' == res.message) {
						unico = true;
					} else {
						unico = false;
					}
				} else if ('error' == res.type) {
					unico = false;
				}
			}
		});
		control.disabled = disabled;
		return unico;
	}