<%-- 
    Document   : propuesta-nueva
    Created on : Feb 25, 2017, 11:31:58 PM
    Author     : kecc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nueva Propuesta de Grado</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <div class="container">
            <h2>Diligencia tu propuesta de grado</h2>
            <div class="well">
            <form action="POST">
                <div class="form-group">
                    <label for="modalidadesList" class="control-label">Modalidad de grado</label>
                    <select id="modalidadesList" class="form-control" name="modalidadesList">
                        <option value="">Selecciona tu modalidad de grado</option>
                        <option value="1">Monograf&iacute;a</option>
                        <option value="2">Asistencia de Investigaci&&oacute;n</option>
                        <option value="3">Trabajo de Investicaci&oacute;n</option>
                        <option value="4">Opci&oacute;n Emprendimiento</option>
                        <option value="5">Otros</option>
                    </select>
                </div>
                <label class="control-label">Por favor diligencia el siguiente formato</label>
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <a href="/SistemaTrabajoGrados/resources/formato-propuesta.docx" target="_blank" class="btn btn-primary btn-block">Formato Propuesta de grado</a>
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <label for="">Selecciona el formato diligenciado de tu ordenador</label>
                    <input type="file" class="form-control file" data-show-preview="false">
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Enviar propuesta de grado" name="btnEnviar" />
            </form>
            </div>
        </div>
    </body>
</html>
