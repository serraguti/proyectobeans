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
        <h1>Nuevo hospital</h1>
        <a href="webcontroller07listahospitales.jsp">
            Volver a Index
        </a>
        <form method="post">
            <label>Nombre: </label>
            <input type="text" name="cajanombre" required/><br/>
            <label>Dirección: </label>
            <input type="text" name="cajadireccion" required/><br/>
            <label>Teléfono: </label>
            <input type="text" name="cajatelefono" required/><br/>
            <label>Camas: </label>
            <input type="number" name="cajacamas" required/><br/>
            <button type="submit">
                Insertar hospital
            </button>
        </form>
        <%
        String nombre = request.getParameter("cajanombre");
        if (nombre != null){
            String direccion = request.getParameter("cajadireccion");
            String tlf = request.getParameter("cajatelefono");
            int camas = Integer.parseInt(request.getParameter("cajacamas"));
            controllerhospital.insertarHospital(nombre, direccion, tlf, camas);
            %>
            <jsp:forward page="webcontroller07listahospitales.jsp"/>
            <%
        }
        %>
    </body>
</html>
