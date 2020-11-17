<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="java.util.List, java.util.ArrayList, mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraVO, mx.gob.stps.portal.persistencia.vo.OfertasRecientesVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    ServletContext context = request.getSession().getServletContext();
	List<OfertasRecientesVO> ofertasRecientes = (List<OfertasRecientesVO>)application.getAttribute("recientes");
    List<OfertasRecientesVO> ofertasDestacadas = (List<OfertasRecientesVO>)application.getAttribute("destacadas");
    List<OfertasRecientesVO> ofertasGendarmeria = (List<OfertasRecientesVO>)application.getAttribute("gendarmeria");
    //List<OfertasRecientesVO> ofertasPet = (List<OfertasRecientesVO>)application.getAttribute("pet");
    List<OfertaExtranjeraVO> externalOffers = (List<OfertaExtranjeraVO>)application.getAttribute("externalOffers");
    //Canada
    //ofertasCanada.add(new OfertasRecientesVO(0,"Jardinero paisajista","Estados Unidos - Westbury, N.Y.","06/06/2017",35000,"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-westbury-NY-001.jsp"));
    //ofertasCanada.add(new OfertasRecientesVO(0,"Ingeniero en Automatización","Alemania","27/04/2017",75000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-001.jsp"));
    //ofertasCanada.add(new OfertasRecientesVO(0,"Ingeniero Eléctrico","Alemania","27/04/2017",75000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-002.jsp"));
    //ofertasCanada.add(new OfertasRecientesVO(0,"Asistente Médico","Alemania","27/04/2017",68000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-003.jsp"));
    //ofertasCanada.add(new OfertasRecientesVO(0,"Enfermeras en Cuidado de Adultos Mayores","Alemania","27/04/2017",34000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-004.jsp"));
    //ofertasCanada.add(new OfertasRecientesVO(0,"Farmacólogo","Alemania","27/04/2017",60000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-005.jsp"));
    //ofertasCanada.add(new OfertasRecientesVO(0,"Trabajadores en Preparación de Alimentos","Estados Unidos - Montauk, N.Y","27/04/2017",45000,"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-Montauk-NY-001.jsp"));
    //ofertasCanada.add(new OfertasRecientesVO(0,"Recepcionista de Hotel","Estados Unidos - Montauk, N.Y","27/04/2017",50000,"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-Montauk-NY-002.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Mucama/Lavandería","Estados Unidos - Montauk, N.Y","29/05/2017",35000,"/jsp/empleo/candidatos/ofertas-EEUU-Canada/oferta-Montauk-NY-003.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Arquitectura de Software","Alemania","02/05/2017",64000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-006.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Desarrollador  de Software","Alemania","02/05/2017",64000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-007.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Probador de Software","Alemania","02/05/2017",64000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-008.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Desarrolladores de redes de interfaz y/o servidores","Alemania","02/05/2017",64000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-009.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Enfermeras","Alemania","02/05/2017",38000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-010.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Asistente de Radiología","Alemania","02/05/2017",46000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-011.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Terapeuta Físico","Alemania - Baden-Württemberg","09/05/2017",40000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-012.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Ingeniero de Proyecto","Alemania","09/05/2017",75000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-013.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Ingeniero Industrial","Alemania","10/05/2017",75000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-014.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Ingeniero Mecánico","Alemania","10/05/2017",75000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-015.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Científicos de Datos / Analistas de Datos","Alemania","10/05/2017",64000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-016.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Desarrollador de Bases de Datos","Alemania","10/05/2017",64000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-017.jsp"));
	//ofertasCanada.add(new OfertasRecientesVO(0,"Ingeniero de Desarrollo en Pruebas","Alemania","17/05/2017",64000,"/jsp/empleo/candidatos/ofertas-Alemania/oferta-Alemania-018.jsp"));
    
	int count = 0;
	String st = "";
%>
<!-- div tres Ofertas -->
  <div class="row">
    <div class="col-md-12">

      <style type="text/css">
        .oculto {
           position: absolute !important;
           top: -9999px !important;
           left: -9999px !important;
        }
      </style>
  
      <script type="text/javascript">

      function cargarContenido(tipoOferta){
        if(tipoOferta==1){ //RECIENTES
          
          document.getElementById('ofertasRecientes')   .className = '';
          document.getElementById('ofertasCanada')      .className = 'oculto';
          document.getElementById('ofertasDestacadas')  .className = 'oculto';
          document.getElementById('ofertasGendarmeria') .className = 'oculto';
          
          document.getElementById('aOfertasRecientes')  .className = 'ofertas_recientes current';
          document.getElementById('aOfertasCanada')   .className = 'ofertas_canada';
          document.getElementById('aOfertasDestacadas') .className = 'ofertas_destacadas';
          document.getElementById('aOfertasGendarmeria').className = 'ofertas_recientes';
          
          document.getElementById('verRecientes')     .className = 'btn-green btn-sm';
          document.getElementById('verDestacadas')    .className = 'oculto';
          document.getElementById('verCanada')      .className = 'oculto';
          document.getElementById('verGendarmeria')   .className = 'oculto';
        }
        if(tipoOferta==2){ //DESTACADAS
          
          document.getElementById('ofertasRecientes')   .className = 'oculto';
          document.getElementById('ofertasCanada')    .className = 'oculto';
          document.getElementById('ofertasDestacadas')  .className = '';
          document.getElementById('ofertasGendarmeria') .className = 'oculto';    
          
          document.getElementById('aOfertasRecientes')  .className = 'ofertas_recientes';
          document.getElementById('aOfertasCanada')   .className = 'ofertas_canada';
          document.getElementById('aOfertasDestacadas') .className = 'ofertas_destacadas current';
          document.getElementById('aOfertasGendarmeria').className = 'ofertas_recientes';
          
          document.getElementById('verRecientes')     .className = 'oculto';
          document.getElementById('verDestacadas')    .className = 'btn-green btn-sm';
          document.getElementById('verCanada')      .className = 'oculto';
          document.getElementById('verGendarmeria')   .className = 'oculto';
        }
        if(tipoOferta==3){//CANADA
          
          document.getElementById('ofertasRecientes')   .className = 'oculto';
          document.getElementById('ofertasCanada')    .className = '';
          document.getElementById('ofertasDestacadas')  .className = 'oculto';
          document.getElementById('ofertasGendarmeria') .className = 'oculto';
          
          document.getElementById('aOfertasRecientes')  .className = 'ofertas_recientes';
          document.getElementById('aOfertasCanada')   .className = 'ofertas_canada current';
          document.getElementById('aOfertasDestacadas') .className = 'ofertas_destacadas';
          document.getElementById('aOfertasGendarmeria').className = 'ofertas_recientes';   

          document.getElementById('verRecientes')     .className = 'oculto';
          document.getElementById('verDestacadas')    .className = 'oculto';
          document.getElementById('verCanada')        .className = 'btn-green btn-sm';
          document.getElementById('verGendarmeria')   .className = 'oculto';    
        }
        if(tipoOferta==4){//GENDERAMERIA
          
          document.getElementById('ofertasRecientes')   .className = 'oculto';
          document.getElementById('ofertasCanada')    .className = 'oculto';
          document.getElementById('ofertasDestacadas')  .className = 'oculto';
          document.getElementById('ofertasGendarmeria') .className = '';
          
          document.getElementById('aOfertasRecientes')  .className = 'ofertas_recientes';
          document.getElementById('aOfertasCanada')   .className = 'ofertas_canada';
          document.getElementById('aOfertasDestacadas') .className = 'ofertas_destacadas';
          document.getElementById('aOfertasGendarmeria').className = 'ofertas_recientes current';   
          
          document.getElementById('verRecientes')     .className = 'oculto';
          document.getElementById('verDestacadas')    .className = 'oculto';
          document.getElementById('verCanada')      .className = 'oculto';
          document.getElementById('verGendarmeria')   .className = 'btn-green btn-sm';      
        }
      }

      </script>
      <form name="ofertasEmpleoForm" id="ofertasEmpleoForm" method="get" action="">
        <div id="ofertas">
          <h2 class="titulosh2">Ofertas de empleo</h2>
          <input type="hidden" id="prueba" value="">
          <div class="tabsOfertas">
            <a id="aOfertasRecientes" class="ofertas_recientes current" href="javascript:cargarContenido(1);" >Ofertas recientes</a> 
            <a id="aOfertasDestacadas" href="javascript:cargarContenido(2);" >Ofertas destacadas </a> 
            <a id="aOfertasCanada" href="javascript:cargarContenido(3);" >Ofertas en el extranjero</a>
            <a id="aOfertasGendarmeria" href="javascript:cargarContenido(4);" >Ofertas en Gendarmeria</a>
          </div>
          
          <table id="ofertasRecientes">
          <%if(ofertasRecientes.size()>0){ %>
            <caption></caption>
            <tbody>            
            <%for(OfertasRecientesVO vo: ofertasRecientes){
            	count++;
            	st = count % 2 ==0?"":"odd";
            	%>              
              <tr class='<%=st%>'>
                <td><a href="<%=response.encodeURL(request.getContextPath()+"/ofertasRecientes.do?method=detalleoferta&id_oferta_empleo=" + vo.getIdOfertaEmpleo() + "&nombre_trabajo=" + vo.getTituloOferta())%>"><%=vo.getTituloOferta()%></a></td>
                <td><%=vo.getUbicacion()%></td>
                <td><%=vo.getVigencia() %></td>
              </tr>
              <%} %>           
            </tbody>
             <%} %>
          </table>  
          <table id="ofertasDestacadas" class="oculto">
            <caption></caption>
            <%if(ofertasDestacadas.size()>0){%>
            <tbody>
            <% st = ""; count=0;%>
            <%for(OfertasRecientesVO vo: ofertasDestacadas){
            	count++;
            	st = count % 2 == 0?"":"odd";
            	%>
            
              <tr class='<%=st%>'>
                <td><a href="<%=response.encodeURL(request.getContextPath()+"/ofertasRecientes.do?method=detalleoferta&id_oferta_empleo=" + vo.getIdOfertaEmpleo()+ "&nombre_trabajo=" + vo.getTituloOferta())%>"><%=vo.getTituloOferta()%></a></td>
                <td><%=vo.getUbicacion()%></td>
                <td><%=vo.getVigencia()%></td>
              </tr>
              <%} %>              
            </tbody>
            <%} %>
          </table>    
          <table id="ofertasCanada" class="oculto">
            <caption></caption>
              <% if (externalOffers.size() > 0) {%>
              	<tbody>
            		<% st = ""; count=0;%>
            		<% for (OfertaExtranjeraVO vo: externalOffers) {
		            	count++;
		            	String url = response.encodeURL(request.getContextPath()+"/ofertasExtranjeras.do?method=detailHome&id="+vo.getIdOfertaExtranjera()+"&tl="+vo.getTituloOferta()+"&cy="+vo.getPais());
		            	st = count % 2 == 0?"":"odd";
	            	%>
			              <tr class='<%=st%>'>
			                <td><strong><a href="<%=url%>"><%=vo.getTituloOferta()%></a></strong></td>
			                <td><%=vo.getPais()%></td>                
			              </tr>
              		<% 
              				if (count > 5) break;
              			} %>              
            	</tbody>
              <% }else { %>
              	<tbody>
			              <tr>
			                <td></td>
			                <td></td>                
			              </tr>		               	
			              <tr class='odd'>
			                <td></td>
			                <td></td>                
			              </tr>
						  <tr>
			                <td></td>
			                <td></td>                
			              </tr>
			              <tr class='odd'>
			                <td></td>
			                <td></td>                
			              </tr>
			              <tr>
			                <td></td>
			                <td></td>                
			              </tr>
			   </tbody>
			 <% } %>
          </table>
          <table id="ofertasGendarmeria" class="oculto">
            <caption></caption>
          <%if(ofertasGendarmeria.size()>0){ %>
            <tbody>
          <%for(OfertasRecientesVO vo: ofertasGendarmeria){
        	  count++;
        	  st = count % 2 == 0?"":"odd";
        	  %>          
              <tr class='<%=st%>'>
                <td><a href="<%=response.encodeURL(request.getContextPath()+"/ofertasRecientes.do?method=detalleoferta&id_oferta_empleo=" + vo.getIdOfertaEmpleo()+ "&nombre_trabajo=" + vo.getTituloOferta())%>"><%=vo.getTituloOferta()%></a></td>             
                <td><%=vo.getUbicacion() %></td>
                <td><%=vo.getEmpresa() %></td> 
                <td><%=vo.getVigencia() %></td>
                <c:set var="salario" value="<%=vo.getSalario() %>"/>    
                <fmt:setLocale value="es_MX"/>                
                <td><fmt:formatNumber value="${salario}" pattern="$ #,##0.00"/></td>                                
              </tr>
              <%} %>                                   
            </tbody>
            <%} %>
          </table>  
          
            
          <noscript>
            &lt;table&gt;
              &lt;caption&gt;Ofertas Destacadas&lt;/caption&gt;
              
                &lt;tbody&gt;
                  &lt;tr&gt;
                    &lt;th&gt;&lt;/th&gt;
                    &lt;th&gt;&lt;/th&gt;
                    &lt;th&gt;&lt;/th&gt;                     
                  &lt;/tr&gt;       
                  
                  
                  &lt;tr class='odd'&gt;
                    &lt;td&gt;
                      &lt;strong&gt; 
                        &lt;a href="<%=request.getContextPath()%>/ofertasRecientes.do?method=detalleoferta&amp;amp;id_oferta_empleo=558336"&gt;Medico pediatra&lt;/a&gt;
                      &lt;/strong&gt;
                    &lt;/td&gt;
                    &lt;td&gt;Nayarit, Tepic&lt;/td&gt;
                    &lt;td&gt;05 de Noviembre 2013 - 05 de Diciembre 2013&lt;/td&gt;
                  &lt;/tr&gt;
                  
                  &lt;tr class=''&gt;
                    &lt;td&gt;
                      &lt;strong&gt; 
                        &lt;a href="<%=request.getContextPath()%>/ofertasRecientes.do?method=detalleoferta&amp;amp;id_oferta_empleo=563868"&gt;Supervisor de servicios generales&lt;/a&gt;
                      &lt;/strong&gt;
                    &lt;/td&gt;
                    &lt;td&gt;Distrito Federal, Cuauhtï¿½moc&lt;/td&gt;
                    &lt;td&gt;05 de Noviembre 2013 - 05 de Diciembre 2013&lt;/td&gt;
                  &lt;/tr&gt;
                  
                  &lt;tr class='odd'&gt;
                    &lt;td&gt;
                      &lt;strong&gt; 
                        &lt;a href="<%=request.getContextPath()%>/ofertasRecientes.do?method=detalleoferta&amp;amp;id_oferta_empleo=561671"&gt;Gerente de ventas en alimentos&lt;/a&gt;
                      &lt;/strong&gt;
                    &lt;/td&gt;
                    &lt;td&gt;Distrito Federal, Cuauhtï¿½moc&lt;/td&gt;
                    &lt;td&gt;05 de Noviembre 2013 - 05 de Diciembre 2013&lt;/td&gt;
                  &lt;/tr&gt;
                  
                  &lt;tr class=''&gt;
                    &lt;td&gt;
                      &lt;strong&gt; 
                        &lt;a href="<%=request.getContextPath()%>/ofertasRecientes.do?method=detalleoferta&amp;amp;id_oferta_empleo=560228"&gt;Gerente de ventas&lt;/a&gt;
                      &lt;/strong&gt;
                    &lt;/td&gt;
                    &lt;td&gt;Quintana Roo, Benito Juï¿½rez&lt;/td&gt;
                    &lt;td&gt;05 de Noviembre 2013 - 26 de Noviembre 2013&lt;/td&gt;
                  &lt;/tr&gt;
                  
                  &lt;tr class='odd'&gt;
                    &lt;td&gt;
                      &lt;strong&gt; 
                        &lt;a href="<%=request.getContextPath()%>/ofertasRecientes.do?method=detalleoferta&amp;amp;id_oferta_empleo=556019"&gt;Director o gerente de ventas&lt;/a&gt;
                      &lt;/strong&gt;
                    &lt;/td&gt;
                    &lt;td&gt;Chihuahua, Delicias&lt;/td&gt;
                    &lt;td&gt;05 de Noviembre 2013 - 05 de Diciembre 2013&lt;/td&gt;
                  &lt;/tr&gt;
                  
                  &lt;tr class=''&gt;
                    &lt;td&gt;
                      &lt;strong&gt; 
                        &lt;a href="<%=request.getContextPath()%>/ofertasRecientes.do?method=detalleoferta&amp;amp;id_oferta_empleo=563224"&gt;Administrador general&lt;/a&gt;
                      &lt;/strong&gt;
                    &lt;/td&gt;
                    &lt;td&gt;Distrito Federal, Cuajimalpa de Morelos&lt;/td&gt;
                    &lt;td&gt;05 de Noviembre 2013 - 05 de Diciembre 2013&lt;/td&gt;
                  &lt;/tr&gt;
                  
                &lt;/tbody&gt;
              
            &lt;/table&gt;
            &lt;table&gt;
              &lt;caption&gt;Proyectos PET&lt;/caption&gt;
              
              &lt;tbody&gt;
                &lt;tr&gt;
                  &lt;th&gt;&lt;/th&gt;
                  &lt;th&gt;&lt;/th&gt;
                &lt;/tr&gt;
                
                 
                  &lt;tr class='odd'&gt;
                  &lt;td&gt;
                    &lt;strong&gt; 
                      &lt;a href="https://empleo.gob.mx/es_mx/empleo/programa_empleo_temporal"&gt;Construcciï¿½n de 180m2 de andador&lt;/a&gt;
                    &lt;/strong&gt;
                  &lt;/td&gt;
                  &lt;td&gt;Campeche&lt;/td&gt;
                  &lt;/tr&gt;
                   
                  &lt;tr class=''&gt;
                  &lt;td&gt;
                    &lt;strong&gt; 
                      &lt;a href="https://empleo.gob.mx/es_mx/empleo/programa_empleo_temporal"&gt;Construcciï¿½n de 300 metros lineales de andador&lt;/a&gt;
                    &lt;/strong&gt;
                  &lt;/td&gt;
                  &lt;td&gt;Campeche&lt;/td&gt;
                  &lt;/tr&gt;
                   
                  &lt;tr class='odd'&gt;
                  &lt;td&gt;
                    &lt;strong&gt; 
                      &lt;a href="https://empleo.gob.mx/es_mx/empleo/programa_empleo_temporal"&gt;Rehabilitaciï¿½n de Bodega receptora de maï¿½z&lt;/a&gt;
                    &lt;/strong&gt;
                  &lt;/td&gt;
                  &lt;td&gt;Campeche&lt;/td&gt;
                  &lt;/tr&gt;
                  
              &lt;/tbody&gt;
                      
            &lt;/table&gt;
            &lt;table&gt;
              &lt;caption&gt;Ofertas Gendarmer&amp;iacute;a&lt;/caption&gt;
              
                &lt;tbody&gt;
                  &lt;tr&gt;
                    &lt;td&gt;&lt;/td&gt;
                  &lt;/tr&gt;
                &lt;/tbody&gt;
              
            &lt;/table&gt;      
          </noscript>         
          
              
          <div class="borde_inferior text-center">
            <div>
              <a class="btn btn-green btn-sm" href="<c:url value="/ofertasRecientes.do?method=totalOfertasRecientes"/>" id="verRecientes">Ver m&aacute;s empleos</a> 
              <a href="<c:url value="/ofertasRecientes.do?method=totalOfertasDestacadas"/>" id="verDestacadas" class="oculto btn-green btn-sm">Ver m&aacute;s empleos</a>
              <a href="http://goo.gl/oc8ZM8" id="verCanada" class="oculto btn-green btn-sm">Ir al micrositio</a>
              <a href="<c:url value="/ofertasRecientes.do?method=ofertasGendarmeriaTodas"/>" id="verGendarmeria" class="oculto btn-green btn-sm">Ver m&aacute;s empleos</a>
              </div>
          </div>
        </div>
      </form>
    </div>
  </div>
  <!-- /div tres Ofertas -->
  
  </br>