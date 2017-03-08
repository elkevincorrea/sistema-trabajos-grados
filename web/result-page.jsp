<%-- 
    Document   : result-page
    Created on : Feb 28, 2017, 5:22:17 PM
    Author     : kecc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title><%
            String title = (String) request.getAttribute("title");
            if(title != null){
                out.print(title);
            }else{
                out.print("Resultado");
            }
            %></title>
            <%@include file="header.html" %>
    </head>
    <body>
        <div class="well">
            <%
                String resultado = (String) request.getAttribute("result");
                if(resultado.equals("Éxito")){%>
                <i class="fa fa-check fa-4x"></i> 
                <%} else{ %>
                <i class="fa fa-warning fa-4x"></i>     
                <%}
                %>
            <h2><%
                out.print(resultado);
                %></h2>
            <p><%
                String message = (String) request.getAttribute("message");
                out.print(message);
                %></p>
            <a href="/SistemaTrabajoGrados">Inicio</a>
        </div>
    </body>
</html>
