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
                <form action="/SistemaTrabajoGrados/propuestas" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-9">
                                <label class="control-label">Estudiantes</label>
                            </div>
                            <div class="col-md-3">
                                <input type="number" class="form-control input-sm" name="numestudiantes" id="inputNumEsts" value="1" readonly/>
                            </div>
                        </div>
                        <div class="row" id="divest1">
                            <div class="col-md-3">
                                <input type="text" name="codestudiante1" id="codestudiante1" class="form-control" placeholder="Código" required>
                            </div>
                            <div class="col-md-9">
                                <input type="text" name="estudiante1" id="estudiante1" class="form-control" placeholder="Nombre" required>
                            </div>
                        </div>
                        <br/>
                        <button type="button" class="btn btn-primary btn-xs" name="agregarEst" id="agregarEstBtn"><i class="fa fa-plus-square fa-fw"></i>  Agregar Estudiante</button>
                    </div>
                    <div class="form-group">
                        <label for="tematica" class="control-label">Tem&aacute;tica a trabajar</label>
                        <input type="text" name="tematica" id="tematica" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="modalidadesList" class="control-label">Modalidad de grado</label>
                        <select id="modalidadesList" class="form-control" name="modalidadesList" required>
                            <option value="">Selecciona tu modalidad de grado</option>
                            <option value="1">Monograf&iacute;a</option>
                            <option value="2">Asistencia de Investigaci&oacute;n</option>
                            <option value="3">Trabajo de Investicaci&oacute;n</option>
                            <option value="4">Opci&oacute;n Emprendimiento</option>
                            <option value="5">Otros</option>
                        </select>
                    </div>
                    <label class="control-label">Por favor diligencia el siguiente formato</label>
                    <div class="row">
                        <div class="col-md-6 col-md-offset-3">
                            <a href="/SistemaTrabajoGrados/resources/formato-propuesta.docx" target="_blank" class="btn btn-primary btn-block"><i class="fa fa-download fa-fw"></i> Formato Propuesta de grado</a>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label for="">Selecciona el formato diligenciado de tu ordenador</label>
                        <input type="file" class="form-control file" name="file-propuesta" data-show-preview="false" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block" name="btnEnviar">Enviar propuesta de grado <i class="fa fa-send fa-fw"></i></button>
                </form>
            </div>
        </div>
        <script>
            $(document).ready(function (){
                var ests = 1;
                $("#agregarEstBtn").click(function() {
                    $("#divest"+ (ests++)).after("<div class=\"row\" id=\"divest" + ests + "\">" +
                            "<div class=\"col-md-3\">" +
                                "<input type=\"text\" name=\"codestudiante" + ests + "\" id=\"codestudiante" + ests + "\" class=\"form-control\" placeholder=\"Código\" required>" +
                            "</div>" +
                            "<div class=\"col-md-9\">" +
                                "<input type=\"text\" name=\"estudiante"+ ests +"\" id=\"estudiante" + ests + "\" class=\"form-control\" placeholder=\"Nombre\" required>" +
                            "</div>" +
                        "</div>");
                    $("#inputNumEsts").val(ests + "");
                });
            });
        </script>
    </body>
</html>
