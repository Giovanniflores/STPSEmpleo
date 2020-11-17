<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<?xml version="1.0" encoding="ISO-8859-1" ?>


 <div class="clearfix"></div>


	<div class="row">
	
		<!-- div buscador -->
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-buscador inicial">

				<div class="panel-footer text-right">
					<span>También puedes realizar una <a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar"/>"> búsqueda específica</a></span>
				</div>
			</div>
			

				
				
			</div>
			<!-- div ayuda -->
		</div>
		<!-- /div buscador -->
		
		 <div class="clearfix"></div>

 <%--    <div class="row">
    <!-- div buscador -->
      <div class="col-sm-12">
        <div class="panel panel-buscador">
<!--           <div class="panel-heading"> -->
<!--             <h3 class="panel-title"> -->
<!--               Tenemos más de <strong>1,440,000</strong> ofertas de empleo esperando por ti -->
<!--             </h3> -->
<!--           </div> -->
          <div class="panel-body">
            <div class="row">
              <form name="ocp" id="ocp" action="<%=response.encodeURL(request.getContextPath()+"/ocupate.do")%>" method="get">
                <input type="hidden" name="method" value="init">
                <input type="hidden" name="searchQ" value="">
                <div class="col-md-5">
                    <div class="form-group">
                      <label for="searchTopic">¿Qué empleo buscas? </label>
                      <input id="searchTopic" name="searchTopic" value="" type="text" class="form-control">
                    </div>
                     <span class="help-block">Puedes indicar un puesto, carrera u oficio</span> 
                </div>
                <div class="col-md-5">
                  <div class="form-group">
                    <label for="searchPlace" class="t_buscador">¿Dónde?</label>
                    <select id="searchPlace" name="searchPlace" class="form-control">
                            <option value="" selected="selected"></option>
                            <option value="1">Aguascalientes</option>
                            <option value="2">Baja California</option>
                            <option value="3">Baja California Sur</option>
                            <option value="4">Campeche</option>
                            <option value="5">Coahuila</option>
                            <option value="6">Colima</option>
                            <option value="7">Chiapas</option>
                            <option value="8">Chihuahua</option>
                            <option value="9">Ciudad de México</option>
                            <option value="10">Durango</option>
                            <option value="11">Guanajuato</option>
                            <option value="12">Guerrero</option>
                            <option value="13">Hidalgo</option>
                            <option value="14">Jalisco</option>
                            <option value="15">México</option>
                            <option value="16">Michoacán</option>
                            <option value="17">Morelos</option>
                            <option value="18">Nayarit</option>
                            <option value="19">Nuevo León</option>
                            <option value="20">Oaxaca</option>
                            <option value="21">Puebla</option>
                            <option value="22">Querétaro</option>
                            <option value="23">Quintana Roo</option>
                            <option value="24">San Luis Potosí</option>
                            <option value="25">Sinaloa</option>
                            <option value="26">Sonora</option>
                            <option value="27">Tabasco</option>
                            <option value="28">Tamaulipas</option>
                            <option value="29">Tlaxcala</option>
                            <option value="30">Veracruz</option>
                            <option value="31">Yucatán</option>
                            <option value="32">Zacatecas</option>
                    </select>
                  </div>
                   <span class="help-block text-right"><a href="<%=response.encodeURL(request.getContextPath() +"/jsp/empleo/herramientasDelSitio/usoBuscador.jsp")%>">¿Cómo utilizar el buscador?</a></span> 
                </div>
                <div class="col-md-2">
                  <span class="hidden-xs blockBtn"></span>
                   <input id="bt_buscador" class="btn btn-buscador form-control" type="button" name="bt_buscador" onclick="employ()" onkeypress="employ()" value="Buscar"/>
                </div>
              </form>
            </div>
          </div>
          <div class="panel-footer text-right">
            <span>También puedes realizar una <a href="<%=response.encodeURL(request.getContextPath()+"/busquedaEspecificaOfertas.do?method=buscar")%>">búsqueda específica</a></span>
          </div>
        </div>
    </div>
      <!-- /div buscador -->
    </div> --%>