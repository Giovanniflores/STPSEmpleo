<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">

  dojo.require('dijit.Dialog');
  function keySubmit(e) {
    if (e.keyCode == 13) {
      document.loginForm.submit();
    }
  }
  var userclean = true;
  var pswclean = true;

  function clean(field, numfield) {

    if (numfield==1){
      if (userclean){
        field.value='';
        userclean = false;
      }
    }

    if (numfield==2){
      if (pswclean) {
        field.value='';
        pswclean = false;
      }
    }
  }




</script>
<c:if test="${empty USUARIO_APP}">
  <div class="row">
    <!-- div buscador -->
    <div class="col-sm-8">
      <div class="panel panel-buscador">
        <div class="panel-heading">
          <h3 class="panel-title">
            Tenemos más de <strong>1,440,000</strong> ofertas de empleo esperando por ti
          </h3>
        </div>
        <div class="panel-body">
          <div class="row">
            <form name="ocp" id="ocp" action="<c:url value="/ocupate.do"/>" method="get">
              <input type="hidden" name="method" value="init" />
              <input type="hidden" name="searchQ" value="" />
              <div class="col-md-6">
                <div class="form-group">
                  <label for="searchTopic" class="t_buscador b2">¿Qué empleo buscas?</label>
                  <input name="searchTopic" id="searchTopic" class="form-control" value="" type="text" />
                </div>
                <span class="help-block">Puedes indicar un puesto, carrera u oficio</span>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label for="searchPlace" class="t_buscador">¿Dónde?</label>
                  <select name="searchPlace" id="searchPlace" class="form-control">
                    <option value="" selected="selected"></option>
                    <option value="1">Aguascalientes</option>
                    <option value="2">Baja California</option>
                    <option value="3">Baja California Sur</option>
                    <option value="4">Campeche</option>
                    <option value="5">Coahuila</option>
                    <option value="6">Colima</option>
                    <option value="7">Chiapas</option>
                    <option value="8">Chihuahua</option>
                    <option value="9">Distrito Federal</option>
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
                <span class="help-block text-right"><a href="<c:url value="/jsp/empleo/herramientasDelSitio/usoBuscador.jsp"/>">¿Cómo utilizar el buscador?</a></span>
              </div>
              <div class="col-sm-8 col-sm-offset-2">
                <input type="button" name="bt_buscador" id="bt_buscador" onclick="employ()" onkeypress="employ()" value="Buscar"
                       class="btn btn-buscador form-control"/>
              </div>
            </form>
          </div>
        </div>
        <div class="panel-footer text-right">
          <span>También puedes realizar una <a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar"/>">búsqueda específica</a></span>
        </div>
      </div>
    </div>
    <!-- /div buscador -->


    <!-- div Inicio sesion -->
    <div class="col-sm-4">
      <div class="panel panelInicioSesion">
        <div class="panel-heading">
          <h3 class="panel-title">Inicio de sesión</h3>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-sm-12">

              <form id="loginForm" name="loginForm" action="login.do?method=login" method="post">
                <div class="form-group">
                  <label for="username">Usuario / Correo electrónico</label>
                  <input type="text" name="username" id="username" class="form-control"
                         value="Escribe tu nombre de usuario" onfocus="clean(this, 1)" />
                </div>
                <div class="form-group">
                  <label for="password">Contraseña</label>
                  <input type="password" name="password" id="password" class="form-control"
                         value="contrasena" onfocus="clean(this, 2)"
                         onkeypress="keySubmit(event)" />
                </div>
                <p id="ayudas">
                  <a href="<c:url value="/recupera_contrasena.do"/>"
                     class="recuperar">¿Olvidaste tu usuario o contraseña?</a>
                </p>
                <p class="text-center">
                  <input type="button" value="Iniciar Sesión" class="btn btn-sesion form-control"
                         onclick="javascript:document.loginForm.submit();" />
                </p>
              </form>

            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- /div Inicio sesion -->
  </div>

  <!-- div ayuda -->
  <div class="row">
    <div class="col-md-3 col-sm-6 col-xs-12">
      <div class="panel panel-contactoSWB">
        <div class="ayuda">
          <a href="https://qa.empleo.gob.mx/swb/empleo/Necesitas_ayuda">
            ¿Necesitas ayuda? Inicia una asesoría</a>
        </div>
      </div>
    </div>

    <div class="col-md-3 col-sm-6 col-xs-12">
      <div class="panel panel-contactoSWB">
        <div class="atencion">
          <a href="https://qa.empleo.gob.mx/swb/empleo/contacto">Atención telefénica 01 800 841 2020</a>
        </div>
      </div>
    </div>

    <div class="col-md-3 col-sm-6 col-xs-12">
      <div class="panel panel-contactoSWB">
        <div class="quejas">
          <a onclick="window.open(this.href, this.target, &#39;toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700&#39;); return false;" target="popUp" href="<c:url value="/suggestion.do?method=init"/>">Quejas <br> y sugerencias</a>
        </div>
      </div>
    </div>

    <div class="col-md-3 col-sm-6 col-xs-12 hidden-xs">
      <div class="panel panel-registroCandidtoEmpresa text-center">
        <p>¿No estás dado de alta?</p>
        <p>Regístrate como
          <a class="candidato" href="<c:url value="/registro_candidato.do"/>">Candidato</a><span> o </span>
          <a class="empresa" href="<c:url value="/registroEmpresa.do?method=init"/>">Empresa</a> </p>
      </div>
    </div>
  </div>
  <!-- div ayuda -->

  <!-- div tres columnas -->
  <div class="row seccionApp">
    <div class="col-md-4 col-sm-6 col-xs-12">
      <h2 class="titulosh2">Solicita una cita</h2>
      <div class="panel panel-default mod_solicita">
        <div class="panel-body">
          <div class="col-xs-8 col-xs-offset-4">
            <p class="sub-titulo">Utiliza nuestro servicio de citas</p>
            <p class="appText">El Servicio Nacional de Empleo (SNE) pone a tu disposición el servicio gratuito de citas, con el objetivo de brindarte una mejor atención.</p>
            <input class="btn btn-green btn-xs pull-right " type="button" value="Ingresa aqu&iacute;" alt="Ingresa aqu?" onclick="window.open(&#39;<c:url value="/citas" />&#39;,&#39;_self&#39;)">
          </div>
        </div>
      </div>
    </div>


    <div class="col-md-4 col-sm-6 col-xs-12">
      <h2 class="titulosh2">App Portal del empleo</h2>
      <div class="panel panel-default mod_instalar_app">
        <div class="panel-body text-center">
          <p>&iexcl;Conoce la nueva APP del</br> Portal de Empleo!</br><strong>Disponible para </strong></p>
          <div class="app-panel">
            <img alt="Get it on Google Play"
                 src="https://developer.android.com/images/brand/en_generic_rgb_wo_45.png" />
          </div>
        </div>
      </div>
    </div>


    <div class="col-md-4 col-sm-12 col-xs-12">
      <h2 class="titulosh2">Mejora tu empleabilidad</h2>
      <div class="panel panel-default">
        <div class="panel-body mod_empleabilidad">
          <ul>
            <li><a href="<c:url value="/jsp/empleo/candidatos/bolsasEmpleoAsociadas.jsp"/>">Busca empleo en otras bolsas</a></li>
            <li><a href="<c:url value="/ofertasRivieraMaya.do?method=init"/>">Ofertas de empleo en la Riviera Maya</a></li>
            <li><a href=<c:url value="/jsp/empleo/candidatos/habilidadesBusquedaEmpleo.jsp"/>"><span>Habilidades</span> para la búsqueda de empleo</a></li>
            <li class="no-line"><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/capacitacionAutoempleoBecate.jsp"/>">Capacitación para el autoempleo</a></li>
          </ul>
        </div>
      </div>

    </div>
  </div>
  <!-- /div tres columnas -->

  <jsp:include page="../layout/ofertasEmpleo.jsp" />

  <div class="row">
    <div class="col-md-8">
      <!-- slide -->
      <div id="carousel-example-captions" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carousel-example-captions" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-captions" data-slide-to="1" class=""></li>
          <li data-target="#carousel-example-captions" data-slide-to="2" class=""></li>
          <li data-target="#carousel-example-captions" data-slide-to="3" class=""></li>
          <li data-target="#carousel-example-captions" data-slide-to="4" class=""></li>
          <li data-target="#carousel-example-captions" data-slide-to="5" class=""></li>
          <li data-target="#carousel-example-captions" data-slide-to="6" class=""></li>
          <li data-target="#carousel-example-captions" data-slide-to="7" class=""></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <div class="item active">
            <a href="http://cns.gob.mx/portalWebApp/wlp.c?__c=8bd" target="_new">
              <img alt="" src="<%=request.getContextPath()%>/bannerHome/convocatoriaPoliciaFederal.jpg"></img>
            </a>
            <div class="carousel-caption hidden-xs">
              <p>Policía Federal Reclutamiento 2015 - Consulta la convocatoria completa en www.cns.gob.mx </p>
            </div>
          </div>
          <div class="item">
            <a href="http://www.cns.gob.mx/portalWebApp/ShowBinary?nodeId=/BEA%20Repository/708020//archivo" target="_new">
              <img alt="" src="<%=request.getContextPath()%>/bannerHome/banner_guardas_escoltas2.jpg"></img>
            </a>
            <div class="carousel-caption hidden-xs">
              <p>Participa en la convocatoria de nuevo ingreso para guarda o escolta.</p>
            </div>
          </div>
          <div class="item">
            <a href="#" target="_new">
              <img alt="" src="<%=request.getContextPath()%>/bannerHome/03banner_masOf.jpg"></img>
            </a>
            <div class="carousel-caption hidden-xs">
              <p>Cada día más ofertas y más candidatos. ¡Registrate! </p>
            </div>
          </div>
          <div class="item">
            <a href="https://abriendoespacios.gob.mx/">
              <img alt="" src="<%=request.getContextPath()%>/bannerHome/08Bannerabriendo_espacios_V2.png"></img>
            </a>
            <div class="carousel-caption hidden-xs">
              <p>Abriendo Espacios. Estrategia para personas con discapacidad y adultos mayores </p>
            </div>
          </div>
          <div class="item">
            <a href="https://www.facebook.com/empleogobmx" target="_new">
              <img alt="" src="<%=request.getContextPath()%>/bannerHome/banner_redes_facebook.png"></img>
            </a>
            <div class="carousel-caption hidden-xs">
              <p>Búscanos en Facebook. facebook.com/empleogobmx</p>
            </div>
          </div>
          <div class="item">
            <a href="https://twitter.com/empleogob_mx" target="_new">
              <img alt="" src="<%=request.getContextPath()%>/bannerHome/banner_redes_twitter.png"></img>
            </a>
            <div class="carousel-caption hidden-xs">
              <p>Síguenos en @empleogob_mx </p>
            </div>
          </div>
          <div class="item">
            <a href="http://www.observatoriolaboral.gob.mx/swb/" target="_new">
              <img alt="" src="<%=request.getContextPath()%>/bannerHome/07banner_ola.jpg"></img>
            </a>
            <div class="carousel-caption hidden-xs">
              <p>Observatorio Laboral. Conoce las características y comportamiento de las ocupaciones y profesiones más representativas en México. La información debeida para una decisión de vida</p>
            </div>
          </div>
          <div class="item">
            <a href="http://qa.ferias.empleo.gob.mx/content/common/home.jsf" target="_new">
              <img alt="" src="<%=request.getContextPath()%>/bannerHome/04banner_feriasEmp.jpg"></img>
            </a>
            <div class="carousel-caption hidden-xs">
              <p>Participa en las Ferias de Empleo. ¡Una opción más para encontrar empleo! </p>
            </div>
          </div>
        </div>
        <a class="left carousel-control" href="#carousel-example-captions" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-captions" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
      <!-- /slide -->

      <!-- Articulos de Interes -->

      <h2 class="titulosh2">Artículos de interés para ti</h2>
      <div class="panel panel-default">
        <div class="panel-body">
          <table id="notas_destacadas">
            <tbody>
            <logic:iterate id="crearArticulosForm" name="inicioForm" property="articulosFormList">
              <tr>
                <td>
                  <p><a href="<bean:write name="crearArticulosForm"  property="redirect"></bean:write>"><bean:write name="crearArticulosForm"  property="titulo"></bean:write></a></p>
                </td>
              </tr>

            </logic:iterate>

            </tbody>
          </table>
        </div>
        <div class="panel-footer text-center">
          <a id="noticias_anteriores" class="btn btn-green" href="<c:url value="/articuloDeInteress.do"/>">Ver m&aacute;s articulos</a>
        </div>
      </div>
      <!-- /Articulos de Interes -->

    </div>
    <div class="col-md-4">
      <!-- Proximos eventos -->
      <h2 class="titulosh2">Conoce los pr&oacute;ximos eventos</h2>
      <div id="registroEventos" name="REGISTROS_EVENTOS" >
        <jsp:include page="/registroseventos.do?method=proximosEventos" />
      </div>
      <div class="panel-footer text-center">
        <a class="btn btn-green" href="<c:url value="/jsp/empleo/herramientasDelSitio/eventos.jsp"/>">Ver todos los eventos</a>
      </div>
    </div>
    <!-- /Proximos eventos -->
  </div>
  </div>


  <c:if test="${not empty errmsg}">
    <script>
      message('${errmsg}');
      <%session.removeAttribute("errmsg");%>
    </script>
  </c:if>
</c:if>