<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu_cel.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Programas disponibles </title>
    </head>
    <body>
        <div class="container">
            <h2> Programas disponibles para postular </h2>
            <p>
                Si necesitas buscar un programa específico, puedes hacerlo
                desde aquí
            </p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <div class="form-group">
                <label class="col-sm-2 control-label">
                    <c:out value="${mensajeResultado}" />
                </label>
            </div>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Nombre </th>
                        <th> Fecha de inicio </th>
                        <th> Fecha de término </th>
                        <th> Estado </th>
                    </tr>
                </thead>
                <tbody id="myTable2">

                    <c:forEach var="pd" items="${programasDisponibles}" >
                        <tr>
                            <td> <c:out value="${pd.nombrePrograma}" /> </td>
                            <td> <fmt:formatDate dateStyle="long" type="date" value="${pd.fechaInicio}" /> </td>
                            <td> <fmt:formatDate dateStyle="long" type="date" value="${pd.fechaTermino}" /> </td>
                            <td>
                                <c:if test="${pd.estado == 1}">
                                    Sin Cel Asignado
                                </c:if>
                            </td>
                            <td>
                                <form method="post" action="inscripciones">
                                    <input type="hidden" name="accion" value="postular"/>
                                    <input type="hidden" name="codigoPrograma" value="<c:out value="${pd.codigo}"/>" />
                                    <button type="submit" class="btn btn-primary">
                                        Postular
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <br/>
        <div class="container">
            <h2>
                Programas a los que estas postulando
            </h2>
            <p>
                Si necesitas buscar un programa a los cuales has postulado
                hazlo aqui:
            </p>
            <input class="form-control" id="myInput3" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Nombre </th>
                        <th> Cantidad de cupos </th>   
                        <th> Fecha de inicio </th>
                        <th> Fecha de término </th>
                        <th> Estado </th>
                        <!--<th> </th>-->
                    </tr>
                </thead>
                <tbody id="myTable">
                    <c:forEach var="pi" items="${programasInscritos}" >
                        <tr>
                            <td> <c:out value="${pi.programa.nombrePrograma}" /> </td>
                            <td> <c:out value="${pi.programa.cupos}" /> </td>
                            <td> <fmt:formatDate dateStyle="long" type="date" value="${pi.programa.fechaInicio}" /> </td>
                            <td> <fmt:formatDate dateStyle="long" type="date" value="${pi.programa.fechaTermino}" /> </td>
                            <td>
                                <c:choose>
                                    <c:when test="${pi.estado == 1}">
                                        Postulando
                                    </c:when>
                                    <c:when test="${pi.estado == 2}">
                                        Inscrito
                                    </c:when>
                                    <c:when test="${pi.estado == 3}">
                                        Rechazado
                                    </c:when>
                                    <c:otherwise>
                                        Definir
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>          
        </div>
        <script>
            $(document).ready(function () {
                $("#myInput").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#myTable tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase()
                                .indexOf(value) > -1);
                    });
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $("#myInput3").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#myTable3 tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase()
                                .indexOf(value) > -1);
                    });
                });
            });
        </script>
    </body>
</html>
