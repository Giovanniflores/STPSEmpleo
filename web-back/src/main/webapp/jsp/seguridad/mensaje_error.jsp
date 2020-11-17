<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty errmsg}">
	<script>
		alert('${errmsg}');
		<%session.removeAttribute("errmsg");%>
	</script>
</c:if>