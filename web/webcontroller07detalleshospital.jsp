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
        <h1>Detalles hospital</h1>
        <a href="webcontroller07listahospitales.jsp">
            Volver a Index
        </a>
        <%
        String dato = request.getParameter("idhospital");
        if (dato != null){
            int idhospital = Integer.parseInt(dato);
            %>
            <%=controllerhospital.getDetalleHospital(idhospital)%>
            <%
        }else {
            %>
            <h1 style="color:red">No hemos recibido hospital</h1>
            <%
        }
        %>
    </body>
</html>
