<%-- 
    Document   : presentar-trabajo
    Created on : Feb 28, 2017, 5:55:11 PM
    Author     : kecc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Presentar Trabajo Grado</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        
        <div class="container">
            <h2>Presenta tu trabajo de grado</h2>
            <div class="well">
                
                        <%
                            java.util.List<models.Propuesta> propuestas = (java.util.List<models.Propuesta>)request.getAttribute("propuestas");
                            if(propuestas != null){%>
                                <form action="CrearTrabajoGrado" method="GET">
                                    <select class="form-control" name="propuesta"> 
                                <%for(models.Propuesta p : propuestas){ %>
                            <option value="<%= p.getId()%>"><%= p.getTematica()%></option>
                                <%}%>
                                    </select>
                                    <button type="submit" class="btn btn-primary btn-xs">Buscar <i class="fa fa-search fa-fw"></i></button>
                                </form>
                            <%} else{
                                models.Propuesta propuesta = (models.Propuesta) request.getAttribute("propuesta");
                                if(propuesta != null){ %>
                                <form action="CrearTrabajoGrado" method="POST" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label">C&oacute;digo Propuesta</label>
                                                <input class="form-control" name="idpropuesta" value="<%= propuesta.getId()%>" readonly/>
                                            </div>
                                        </div>
                                        <div class="col-md-10">
                                            <div class="form-group">
                                                <label class="control-label">Tem&aacute;tica Propuesta</label>
                                                <input class="form-control" name="tempropuesta" value="<%= propuesta.getTematica()%>" readonly/>
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-md-offset-4">
                                            <div class="form-group text-center">
                                                <label class="control-label">Fecha vencimiento</label>
                                                <input class="form-control text-center" name="idpropuesta" value="<%= propuesta.getFechaVencimiento().toString()%>" readonly/>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-3">
                                            <a href="/SistemaTrabajoGrados/resources/formato-propuesta.docx" target="_blank" class="btn btn-primary btn-block"><i class="fa fa-download fa-fw"></i> Formato Documento Final</a>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="form-group">
                                        <label for="">Selecciona el documento final de tu ordenador</label>
                                        <input type="file" class="form-control file" name="file-documento" data-show-preview="false" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block">Enviar <i class="fa fa-send fa-fw"></i></button>
                                </form>
                                <%}
                            }%>
                
            </div>
        </div>
    </body>
</html>

