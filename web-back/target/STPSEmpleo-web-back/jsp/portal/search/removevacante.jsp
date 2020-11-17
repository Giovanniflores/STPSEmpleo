<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="gob.mx.stps.empleo.search.*" contentType="text/html" pageEncoding="UTF-8"%>
<%!    public static final VacanteSearch s = new VacanteSearch();
%>
<%
            try
            {
                if (request.getParameter("id") != null)
                {
                    //s.remove(Integer.parseInt(request.getParameter("id")),false);
                }
            }
            catch (RuntimeException e)
            {
                response.sendError(500, e.getMessage());
            }
            catch (Exception e)
            {
                response.sendError(500, e.getMessage());
            }

%>
