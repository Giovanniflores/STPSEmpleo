<%@page import="mx.gob.stps.portal.web.security.filter.Captcha"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<html>
<body>
	<script type="text/javascript" src="//www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
	<script type="text/javascript">
	var recomDivId = 'recommendi1_55';
	function validateCaptcha(frm) {
		  var msg = [];
		  if( isEmpty(frm.txtFromName.value) )
		    msg.push('El nombre del remitente es requerido.');
		  if( !isValidEmail(frm.txtFromEmail.value) )
		    msg.push('El correo electrónico del remitente es requerido.');
		  if( isEmpty(frm.txtToName.value) )
		    msg.push('El nombre del destinatario es requerido.');
		  if( !isValidEmail(frm.txtToEmail.value) )
		    msg.push('El correo electrónico del destinatario es requerido.');
		  if( isEmpty(document.getElementById('recaptcha_challenge_field').value))
			 	msg.push('El código de seguridad es requerido');	  
		
		  if( msg.length==0 ) {
		    var xhrArgs = {
		          form: frm,
		          handleAs: "text",
		          load: function(data) {
		        	  if(data=='false'){
		          	  	alert('Su recomendacion fue enviada con exito');
		                removeModal(recomDivId);          	  	
		        	  }
		        	  else{
		                alert(data);
		                removeModal(recomDivId); 
		                openRecommendationModal('recommendi1_55','#000000', 80);		                
		        	  }
		          },
			      error: function(error){
	                javascript:Recaptcha.reload();			    	  
		    		alert(error);
		    	  }
		    };
		    var deferred = dojo.xhrPost(xhrArgs);
		    return true;
		  }else {
		    alert(msg.join('\n'));
		    return false;
		  }
	}
	function openRecommendationModal(divId, bgcolor, opacity) {
	    if (document.getElementById(divId) != undefined) {
	        return;
	    }
	    var newDiv = createModal(divId, bgcolor, opacity);
	    var recomContainer=document.createElement('div');
	    var s = new String('');
	    s = document.getElementById('captchaFrm').innerHTML;
        javascript:Recaptcha.reload();	
	    recomContainer.innerHTML = s;
	    var cwidth=650;
	    var cheight=350;
	    recomContainer.id='s_'+divId;
	    recomContainer.style.zIndex=1001;
	    recomContainer.style.position='absolute';
	    recomContainer.style.top='50%';
	    recomContainer.style.left='50%';
	    recomContainer.style.marginLeft=-cwidth/2+'px';
	    recomContainer.style.marginTop=-cheight/2+'px';
	    recomContainer.style.width=cwidth+'px';
	    recomContainer.style.height=cheight+'px';
	    document.body.appendChild(recomContainer);
	  }
	function removeModal(divId) {
	    var layer=document.getElementById(divId);
	    var superlayer=document.getElementById('s_'+divId);
	    if(layer && superlayer) {
	        document.body.removeChild(superlayer);
	        document.body.removeChild(layer);
	    }
	  }
	function createModal(divId, bgcolor, opacity) {
	    var layer=document.createElement('div');
	    layer.id=divId;
	    layer.style.width='100%';
	    layer.style.height='100%';
	    layer.style.backgroundColor=bgcolor;
	    layer.style.position='fixed';
	    layer.style.top=0;
	    layer.style.left=0;
	    layer.style.zIndex=1000;
	    layer.style.filter='alpha(opacity='+opacity+')';
	    layer.style.opacity=opacity/100;
	    document.body.appendChild(layer);
	    return layer;
	}	
	
	
		function showRecaptchaModal(nombreDiv){
				Recaptcha.create("6Le1ZeMSAAAAAJUZu1g9iswjIkiuVQ_k2XVtuTB3",
				    			 nombreDiv,
				    			 {
									custom_translations : {
													instructions_visual : "Escribe las dos palabras:",
													instructions_audio : "Escribe lo que escuchas",
													play_again : "Reproducir sonido de nuevo",
													cant_hear_this : "Descargar sonido en formato MP3",
													visual_challenge : "Enfrentar un desafío visual",
													audio_challenge : "Enfrentar un desafío de audio",
										            refresh_btn : "Enfrentar un nuevo desafío",
										            help_btn : "Ayuda",
				 					},
							        theme: 'white',
							        lang: 'es',
							        callback: Recaptcha.focus_response_field
							     }
				);
		}
		
		var RecaptchaOptions = {
				 custom_translations : {
					 instructions_visual : "Escribe las dos palabras:",
					 instructions_audio : "Escribe lo que escuchas",
					 play_again : "Reproducir sonido de nuevo",
					 cant_hear_this : "Descargar sonido en formato MP3",
					 visual_challenge : "Enfrentar un desafío visual",
					 audio_challenge : "Enfrentar un desafío de audio",
		            refresh_btn : "Enfrentar un nuevo desafío",
		            help_btn : "Ayuda",
				 },
		   theme : 'white',
		   lang : 'es',
		};		
	 </script>
 
	<div class="swb-recommend">
		<h3>Recomendar</h3>
		<div id="validCaptcha"></div>
	<!-- 	<form class="swb-rcmnd-frm" method="post" action="http://www.empleo.gob.mx/es_mx/empleo/solicitantes/_rid/i1_55/_mto/3/_mod/send" id="frmContact"> -->
		<form class="swb-rcmnd-frm" method="post" action="recomendar.do?method=verificarDatosRecomendar" id="frmContact">
			<p class="swb-rcmnd-in">      
	    		<label for="txtFromName">Remitente:*</label>    
	    		<input type="text" id="txtFromName" name="txtFromName" value="" maxlength="100"/>    
	   		</p>   
	   		<p class="swb-rcmnd-in">      
	    		<label for="txtFromEmail">Correo del remitente:*</label>      
	    		<input type="text" id="txtFromEmail" name="txtFromEmail" value="" maxlength="80"/>   
	   		</p>    
	   		<p class="swb-rcmnd-in">     
	    		<label for="txtToName">Destinatario:*</label>     
	    		<input type="text" id="txtToName" name="txtToName" maxlength="100" />    
	   		</p>    
	   		<p class="swb-rcmnd-in">     
	    		<label for="txtToEmail">Correo del destinatario:*</label>     
	    		<input type="text" id="txtToEmail" name="txtToEmail" maxlength="80"/>   
	   		</p>  
	   		<p class="swb-rcmnd-in">     
	    		<label for="tarMsg">Mensaje adicional:</label>      
	    		<textarea rows="5" cols="40" id="tarMsg" name="tarMsg"></textarea> 
	   		</p>
	   		<div class="swb-captcha-imagen">  
		   		<p>
					<%
					ReCaptcha captcha = ReCaptchaFactory.newReCaptcha("6Le1ZeMSAAAAAJUZu1g9iswjIkiuVQ_k2XVtuTB3", "6Le1ZeMSAAAAAAXSYw_mo65yIrRUBpQU4PjKNbIW", false);
						out.print(captcha.createRecaptchaHtml(null, null));
					%>
		   		</p> 
			</div>
			<p class="swb-rcmnd-cmd">      
	  			<input type="button" value="Cancelar" onclick="removeModal(recomDivId);"/>     
	  			<input type="reset" value="Limpiar"/>     
	  			<input type="button" value="Enviar" onclick="validateCaptcha(this.form);"/>   
			</p> 	
		</form>
		<p class="swb-rcmnd-warn">* Dato requerido</p>
	</div>
</body>
</html>