<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controllerhospital"
             class="controllers.ControllerHospital"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista Hospitales MVC</h1>
        <a href="webcontroller07insertarhospital.jsp">
            Nuevo hospital
        </a>
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                    <th>Camas</th>
                    <td></td>
                    <td></td>
                </tr>
            </thead>
            <tbody>
                <%=controllerhospital.getHospitales()%>
            </tbody>
        </table>
    </body>
</html>
