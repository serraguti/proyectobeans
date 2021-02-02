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
        <h1 style="color:red">Almacenar doctores v2</h1>
        <a href="webcontroller08doctoressessionv2.jsp">
            Mostrar doctores session
        </a>
        <%
        String iddoctor = request.getParameter("iddoctor");
        if (iddoctor != null){
            //ALMACENARLO EN SESSION
            controllerhospital.almacenarDoctorSession(iddoctor);
        }
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Apellido</th>
                    <th>Especialidad</th>
                    <th>Salario</th>
                    <th>Hospital</th>
                </tr>
            </thead>
            <tbody>
                <%=controllerhospital.getFilasDoctoresv2()%>
            </tbody>
        </table>   
    </body>
</html>