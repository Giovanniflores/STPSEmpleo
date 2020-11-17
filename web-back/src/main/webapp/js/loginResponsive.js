/**
 * Funciones para firmar a un usuario desde una ventana emergente
 */

	function dialogLoginPopup(){

		var idTag = "loginPopupDialog";
		
		if (dijit.byId(idTag) == null){
			var html =
				"<div id='registroLat'>"+
				" <div class='form-group'>"+
				"	<label for='usernamePopup'>Usuario / Correo electr&oacute;nico</label>"+
				"	<input type='text' name='usernamePopup' id='usernamePopup' onfocus='clean(this, 1)' value='Escribe tu usuario' class='form-control'>"+
				" </div>"+
				" <div class='form-group'>"+
				"	<label for='passwordPopup'>Contrase&ntildea</label>"+
				"	<input type='password' id='passwordPopup' name='passwordPopup' onfocus='clean(this, 2)' value='Escribe tu contraseña' class='form-control'>"+
				" </div>"+
				" <div class='form-group text-center'>"+
				"	<a class='naranja'  href='recupera_contrasena.do'>¿Olvidaste tu usuario o contrase&ntildea? </a>"+
				" </div>"+
				" <div class='form-group text-center'>"+
				"	<input class='btn btn-sesion form-control' type='button' onclick='loginPopup(usernamePopup.value, passwordPopup.value);' value='Iniciar sesi&oacute;n' id='ir'>"+
				" </div>"+
				"	<div class='registro text-center hidden-xs'>"+
				"		<p class='alta'>¿No estás dado de alta?</p>"+
				"		<p>Regístrate como"+
				"		<a class='candidato'  href='/registro_candidato.do'>Candidato</a><span> o </span>"+
				"		<a class='empresa'  href='/registro_empresa.do'>Empresa</a>"+
				"		</p>"+
				"	</div>"+
				"</div>"+
				"</div>";		

			dialogLogin = new dijit.Dialog({title: 'Iniciar sesión', id: idTag, style: "width:350px;",
											content: html, showTitle: true, draggable : true, closable : true});			
			//dojo.style(dialogLogin.closeButtonNode,"display","none");
		}

		dijit.byId(idTag).show();
	}

	function loginPopup(login, pwd){
		
		if (!validaUserPwdPopup(login, pwd)){
			return; // Datos erroneos
		}

		dojo.xhrPost({url: '/login.do?method=loginPopup&username='+ login +'&password='+ pwd,
				      timeout:180000,
					  load: function(data){
						  	console.log(data);

		  					var resultVO = dojo.fromJson(data);

		  					if('ext' == resultVO.type){
		  						location.reload();
		  					} else if('err' == resultVO.type){
		  						alert(resultVO.message);
		  					}
					  }
					});
	}

	function validaUserPwdPopup(login, pwd) {
		var valido = true;
		
		if (isEmpty(login) || isBlank(login)){
			alert('Debe proporcionar el nombre de usuario');
			valido = false;
		}

		if (isEmpty(pwd) || isBlank(pwd)){
			alert('Debe proporcionar la contraseña');
			valido = false;
		}
	
		return valido;
	}
	

	function isEmpty(str) {
		return (!str || 0 === str.length);
	}

	function isBlank(str) {
		if (isAllBlank(str)) {
			return false;
		} else {
			return (!str || /^\s*$/.test(str));
		}
	}

	function isAllBlank(str) {
		if (str.replace(/\s/g, "") == "") {
			return true;
		} else {
			return false;
		}
	}
	
	/*function keySubmit(e) {
	    if (e.keyCode == 13) {
	    	document.loginForm.submit();
	    }
	}
	function keycolSubmit(e) {
	    if (e.keyCode == 13) {
	    	document.logincolForm.submit();
	    }
	}
	function keybarSubmit(e) {
	    if (e.keyCode == 13) {
	    	document.loginbarForm.submit();
	    }
	}*/

	var userclean = true;
	var pswclean = true;
	var usercleancol = true;
	var pswcleancol = true;
	var cfmcleancol = true;
	var usercleanbar = true;
	var pswcleanbar = true;

	function clean(field, numfield) {

		if (numfield==1){
			if (userclean){
				field.value='';
				userclean = false;
			}		
		}

		if (numfield==2){
			if (pswclean){
				field.value='';
				pswclean = false;
			}		
		}
		if (numfield==3){
			if (usercleancol){
				field.value='';
				usercleancol = false;
			}		
		}

		if (numfield==4){
			if (pswcleancol){
				field.value='';
				pswcleancol = false;
			}		
		}
		if (numfield==5){
			if (cfmcleancol){
				field.value='';
				cfmcleancol = false;
			}		
		}
		if (numfield==6){
			if (usercleanbar){
				field.value='';
				usercleanbar = false;
			}		
		}

		if (numfield==7){
			if (pswcleanbar){
				field.value='';
				pswcleanbar = false;
			}		
		}
	}
	