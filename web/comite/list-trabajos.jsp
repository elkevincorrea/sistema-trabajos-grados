<%-- 
    Document   : list-trabajos
    Created on : Mar 8, 2017, 9:02:17 AM
    Author     : kecc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List,models.Trabajo"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trabajos de Grado</title>
        <%@include file="/header.html" %>
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
        <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="well">
                <h4>Trabajos de Grado Registrados</h4>
                <form  action="ListTrabajos" method="GET">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>Director: </label>
                                <select class="form-control input-sm" name="director">
                                    <option value="">Seleccione un director</option>
                                    <option value="Director 1">Director 1</option>
                                    <option value="Director 2">Director 2</option>
                                    <option value="Director 3">Director 3</option>
                                    <option value="Director 4">Director 4</option>
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
                            <button type="submit" class="btn btn-primary btn-block">Filtrar <i class="fa fa-filter fa-fw"></i></button>
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
                                    <th>Director</th>
                                    <th>Fecha Vencimiento</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Trabajo> propuestas = (List<Trabajo>) request.getAttribute("listTrabajos");
                                    if(propuestas != null){
                                        for(Trabajo t : propuestas){ %>
                                        <tr>
                                            <th><%=t.getId()%></th>
                                            <th><%=t.getDirector() == null ? "No asignado" : t.getDirector()%></th>
                                            <th><%=t.getFechaVencimiento() != null ? t.getFechaVencimiento().toString() : ""%></th>
                                            <th><%=t.getEstado()%></th>
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
