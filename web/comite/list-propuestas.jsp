<%-- 
    Document   : list-propuestas.jsp
    Created on : Mar 7, 2017, 5:27:27 PM
    Author     : kecc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List,models.Propuesta"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Propuestas</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <div class="container">
            <div class="well">
                <h4>Propuestas Registradas</h4>
                <form>
                    
                </form>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Tematica</th>
                                    <th>Fecha Vencimiento</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Propuesta> propuestas = (List<Propuesta>) request.getAttribute("listPropuestas");
                                    if(propuestas != null){
                                        for(Propuesta p : propuestas){ %>
                                        <tr>
                                            <th><%=p.getId()%></th>
                                            <th><%=p.getTematica()%></th>
                                            <th><%=p.getFechaVencimiento() != null ? p.getFechaVencimiento().toString() : ""%></th>
                                            <th><%=p.getEstado()%></th>
                                        </tr>
                                        <%}
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
