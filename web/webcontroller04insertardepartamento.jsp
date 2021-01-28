<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controllerdept"
             class="controllers.Controller01Departamentos"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Insertar departamento</h1>
        <form method="post">
            <label>Número: </label>
            <input type="number" name="cajanumero" required/><br/>
            <label>Nombre: </label>
            <input type="text" name="cajanombre" required/><br/>
            <label>Localidad: </label>
            <input type="text" name="cajalocalidad" required/><br/>
            <button type="submit">Insertar</button>            
        </form>
        <%
        String dato = request.getParameter("cajanumero");
        if (dato != null){
            int deptno = Integer.parseInt(dato);
            String nombre = request.getParameter("cajanombre");
            String localidad = request.getParameter("cajalocalidad");
            controllerdept.insertarDepartamento(deptno, nombre, localidad);
        }
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Número</th>
                    <th>Nombre</th>
                    <th>Localidad</th>
                </tr>
            </thead>
            <tbody>
                <%=controllerdept.getTablaDepartamentos()%>
            </tbody>
        </table>
    </body>
</html>
