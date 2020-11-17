<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




      <div class="contentArticulos">

        <div class="cabeceraTema">
          <h2>${crmCreateArticuloForm.titulo}</h2>
          <ul>
            <li>
              <p><strong>Fuente: </strong>${crmCreateArticuloForm.fuente}</p>
              <p>${crmCreateArticuloForm.fecha}</p>
              <p class="titulo"><strong>Temas relacionados:</strong></p>
              <ul class="nombreTema">
                <c:forEach  items="${crmCreateArticuloForm.etiquetaForms}" var="etiquetas">

                  <li><a  href="<%=request.getContextPath()%>/articuloDeInteress.do?temasEmpleo=${etiquetas.etiqueta}">${etiquetas.etiqueta}</a></li>
                </c:forEach>

              </ul>
            </li>
          </ul>
        </div>

        <div class="contentTema">
          ${crmCreateArticuloForm.articulo}
          <input name="Regresar" onclick="window.location = '<c:url value="/articuloDeInteress.do"/>';" type="button" value="Regresar a temas de empleo" alt=""/>
        </div>

      </div>
    </div>




    <div class="relacionados">

    </div>
  </div>
