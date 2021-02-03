<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controllerhospital"
             class="controllers.ControllerHospital"
             scope="request"/>
<%
    controllerhospital.setSession(session);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String iddoctor = request.getParameter("eliminar");
        if (iddoctor != null){
            controllerhospital.eliminarDoctorSession(iddoctor);
        }
        %>
        <h1 style="color:red">Mostrar doctores v2</h1>
        <a href="webcontroller08almacenarsessiondoctoresv2.jsp">
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
                <%=controllerhospital.getDoctoresSession()%>        
            </tbody>
        </table>
    </body>
</html>
