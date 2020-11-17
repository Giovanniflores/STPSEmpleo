<?xml version="1.0" encoding="ISO-8859-1" ?>

<%
	String context = (String)application.getAttribute("DOMAIN_PORTAL");
	String contextApp = request.getContextPath();
	String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
%>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '308925772806125',
      xfbml      : true,
      version    : 'v2.7'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
<!-- TK2806  -->
<script src="<%=request.getContextPath()%>/js/co.browsing.js"></script>
<!-- div header -->
	<div class="row">
      <div class="col-sm-7">
        <div class="header">
         <a href="<%=response.encodeURL(request.getContextPath()+"/inicio.do")%>"> 
          <img src="<%=request.getContextPath()%>/css/images/m_empleoGob.png"
			alt="Portal del Empleo : llama al 01 800 841 20 20"
			class="img-responsive">
		 </a>
        </div>
      </div>

      <div class="col-sm-5 hidden-xs"> 
          <div class="col-xs-6 logoSTPS">
        	<a href="http://www.stps.gob.mx">
	          <img src="<%=request.getContextPath()%>/css/images/stps_logo.png"
	            alt="Secretaría del Trabajo y Previsión Social"
	            class="img-responsive">
        	</a>
      	  </div>
	      <div class="col-xs-6 logoSNE"> 
	        <a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp")%>" 
	        	onclick="window.location.href='<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp")%>';return false;">
		          <img src="<%=request.getContextPath()%>/css/images/sne_logo.png"
		            alt="Servicio Nacional de Empleo"
		            class="img-responsive">
	         </a>
	      </div>
	      
      	  <div class="clearfix"></div>   
        
          <div class="text-center nav_rapido">
          
         <!--  TK2806-->
           
           <div class="col-xs-3">
            	<a href="javascript:acquireIO.max()">Ayuda En vivo</a>            
             </div>
            <div class="col-xs-3">
              <a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp")%>">Mapa de sitio</a>
            </div>
            <div class="col-xs-2">
              <a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/herramientasDelSitio/contacto.jsp")%>">Contacto</a>
            </div>
            <div class="col-xs-4 solicitaCita">
            	<img src="<%=request.getContextPath()%>/css/images/bg_agendaCita2.png" alt="">
	            <a href="<%=response.encodeURL(request.getContextPath()+"/miespacionav.do?method=agendaCita")%>">&nbsp;Solicita una cita</a>
            </div> 
      	  </div>
       </div>
	</div>
<!-- /div header -->

<!-- div nav-black -->
<div class="row">
	<div class="col-sm-12">
		<div class="btn-group btn-group-justified nav-black">
			<a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/candidatos/candidatos.jsp")%>" class="btn btn-candidato">Candidatos</a> 
			<a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/empresas/empresas.jsp")%>" class="btn btn-empresa">Empresas</a>
			<a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp")%>" class="btn btn-SNE">Servicio Nacional de Empleo</a>
		</div>
	</div>
</div>
<!-- /div nav-black -->
