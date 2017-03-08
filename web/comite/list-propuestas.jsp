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
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="well">
                <h4>Propuestas Registradas</h4>
                <form  action="ListPropuestas" method="GET">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>Estado:</label>
                                <select class="form-control input-sm" name="estado">
                                    <option value="null">Seleccione un estado</option>
                                    <option value="1">En Revisión</option>
                                    <option value="2">Corrección</option>
                                    <option value="3">Aprobada</option>
                                    <option value="4">Rechazada</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>Ordenar por próximos a vencer?</label><br>
                                <input type="checkbox" class="form-control" name="ordenarVencimiento" value="on" data-on="Si" data-off="No" data-toggle="toggle" data-size="small" />
                            </div>
                        </div>
                        <div class="col-md-2 pull-right">
                            <button type="submit" class="btn btn-primary btn-block">Filtrar</button>
                        </div>
                    </div>
                </form>
                <br/>
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
