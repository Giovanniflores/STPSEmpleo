<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<fmt:setBundle basename="portal-web" var="messages"/>

<%
String context = (String)application.getAttribute("DOMAIN_PORTAL");
String contextApp=request.getContextPath();

if (null != request.getSession().getAttribute("FROM_CIL"))
	context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
%>

<div id="columna_articulos">

<ul id="navegacion_local">
<li>
<a href="<%=context %>/en_mx/empleo/becas_cap" class="swb-menumap-act">Becas a la capacitación del SNE</a>
</li>
<li class="swb-menumap-cur">Cursos de formación para el trabajo de la SEP</li>
<li>
<a href="<%=context %>/en_mx/empleo/credito_fonacot_para_formacion_y_capacitacion" class="swb-menumap-act">Crédito FONACOT para la capacitación y formación educativa</a>
</li>
<li>
<a href="<%=context %>/en_mx/empleo/becas_manpower_tdc" class="swb-menumap-act">Becas Manpower TDC</a>
</li>
<li>
<a href="<%=context %>/en_mx/empleo/Capacitacion_a_distancia_para_trabajadores" class="swb-menumap-act">Capacitación a distancia para trabajadores</a>
</li>
<li>
<a href="<%=context %>/en_mx/empleo/CONOCER_general" class="swb-menumap-act">CONOCER</a>
</li>
<li>
<a href="<%=context %>/en_mx/empleo/Programa_Tecnico_Superior_Universitario_IBERO" class="swb-menumap-act">Programa "Técnico Superior Universitario" IBERO</a>
</li>
</ul>

                  <div id="ofertas">
                  <p>Recibe ofertas via <a href="<%=context %>/swb/empleo/Movil">SMS</a> o <a href="<%=context %>/swb/empleo/Correo_Electronico">correo electrónico</a>
</p>
                  </div>
<ul>
<li><div class="otras"><a href="<%=context %>/swb/empleo/Bolsas_de_empleo_asociadas1"><span>Otras</span> bolsas de empleo</a></div></li>
<li><div class="tips">
<a href="<%=context %>/swb/empleo/Tips_para_conseguir_empleo" ><span>Tips</span> para conseguir empleo</a>
</div></li>
<li><div class="foro">

<a href="<fmt:message key='app.context.url' bundle='${messages}'/><c:url value="ofertasRivieraMaya.do?method=init"/>" ><span>Ofertas</span> en la Riviera Maya</a></div></li>
</ul>

 <div class="relacionados">
<h4>Recomendaciones</h4>
<ul>
<li><a href="<%=context %>/en_mx/empleo/cursos_de_formacion_para_el_trabajo" title="ir a Cursos de formación para el trabajo de la SEP">Cursos de formación para el trabajo de la SEP</a></li>
<li><a href="<%=context %>/en_mx/empleo/becas_manpower_tdc" title="ir a Becas Manpower TDC">Becas Manpower TDC</a></li>
<li><a href="<%=context %>/en_mx/empleo/credito_fonacot_para_formacion_y_capacitacion" title="ir a Crédito FONACOT para la capacitación y formación educativa">Crédito FONACOT para la capacitación y formación educativa</a></li>
</ul>
</div>

                <div class="relacionados">
<h4>Conoce más sobre</h4>
<ul>
   <li><a href="<%=context %>/swb/empleo/vacantes_en_la_administracion_publica">Ofertas en la Administración Pública</a></li>
   <li><a href="<%=context %>/swb/empleo/autoempleo">Capacitación para el autoempleo</a></li>
   <li><a href="<%=context %>/swb/empleo/credito_fonacot_para_formacion_y_capacitacion">Crédito FONACOT para la capacitación</a></li>
</ul>
 
                </div>
                <div class="relacionados">
               
                  <div id="twitter_widget">
<script src="http://widgets.twimg.com/j/2/widget.js" type="text/javascript" ></script>
<script type="text/javascript" >
new TWTR.Widget({
  version: 2,
  type: 'profile',
  rpp: 3,
  interval: 6000,
  width: 265,
  height: 300,
  theme: {
    shell: {
      background: '#D7D7D7',
      color: '#000'
    },
    tweets: {
      background: '#fff',
      color: '#000',
      links: '#FF6600'
    }
  },
  features: {
    scrollbar: false,
    loop: false,
    live: false,
    hashtags: true,
    timestamp: true,
    avatars: false,
    behavior: 'all'
  }
}).render().setUser('empleogob_mx').start();
</script>

                  </div>
                </div>
        </div>