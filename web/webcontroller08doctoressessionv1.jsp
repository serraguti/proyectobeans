<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controllerhospital"
             class="controllers.ControllerHospital"
             scope="request"/>
<jsp:useBean id="controllersession"
             class="controllers.ControllerSession"
             scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mostrar doctores</h1>
        <a href="webcontroller08almacenarsessiondoctoresv1.jsp">
            Almacenar doctores
        </a>
        <table border="1">
            <thead>
                <tr>
                    <th>Apellido</th>
                    <th>Especialidad</th>
                    <th>Salario</th>
                </tr>
            </thead>
            <tbody>
                <%=controllerhospital.getDoctoresSession(controllersession.getListadoctores())%>        
            </tbody>
        </table>
    </body>
</html>
