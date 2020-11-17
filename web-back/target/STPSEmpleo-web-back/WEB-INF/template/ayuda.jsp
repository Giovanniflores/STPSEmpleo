	<!-- div ayuda -->

      <div class="col-sm-4">
        <div class="panel panel-contactoSWB">
          <div class="atencion">
           <a href="<%=response.encodeURL(request.getContextPath()+"/jsp/empleo/herramientasDelSitio/contacto.jsp")%>">Atenci&oacute;n: 01 800 841 2020</a>
        </div>
      </div>
    </div>

      <div class="col-sm-4">
        <div class="panel panel-contactoSWB">
          <div class="quejas">
            <a onclick="window.open(this.href, this.target, &#39;toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700&#39;); return false;" target="popUp" href="<%=response.encodeURL(request.getContextPath()+"/suggestion.do?method=init")%>">Quejas y sugerencias</a>
          </div>       
        </div>
      </div>
      
      <div class="row">
	      <div class="col-sm-4">
	        <div class="panel panel-contactoSWB act">
	          <div class="ayuda">
	            <a href="#" id="chat">Ayuda en l&iacute;nea</a>
	          </div>       
	        </div>
	      </div>
    </div>
    <!-- div ayuda -->