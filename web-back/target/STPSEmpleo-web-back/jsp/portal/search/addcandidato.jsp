<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="gob.mx.stps.empleo.search.*" contentType="text/html" pageEncoding="UTF-8"%>
<%!    public static final CandidatoSearch s = new CandidatoSearch();
%>
<%
// TODO ELIMINAR, NO SE UTILIZAN
int lineas = 0;
            try
            {
                InputStream in = request.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                String line = reader.readLine();
                while (line != null)
                {

                    Candidato candidato = new Candidato();
                    //candidato.fromLine(line);
                    //s.add(candidato, false);
                    lineas++;
                    line = reader.readLine();
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
            if (lineas == 0)
            {
                response.sendError(500, "No se enviaron datos");
            }


%>
