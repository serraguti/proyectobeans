<%-- 
Dibujar todos los enfermos al iniciar la página.
Una caja para escribir la inscripción.
Si pulsamos sobre un botón eliminar, borramos al enfermo.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="beanenfermos"
             class="beans.Bean04EliminarEnfermos"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Eliminar enfermos Bean</h1>
        <form method="post">
            <label>Inscripción: </label>
            <input type="text" name="cajainscripcion" required/>
            <button type="submit">Eliminar enfermo</button>
        </form>
        <%
        String dato = request.getParameter("cajainscripcion");
        if (dato != null){
            int inscripcion = Integer.parseInt(dato);
            beanenfermos.eliminarEnfermo(inscripcion);
        }
        %>
        
        <%=beanenfermos.getListaEnfermos()%>
    </body>
</html>
