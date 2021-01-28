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
        <h1>Eliminar departamentos</h1>
        <form method="post">
            <label>Número departamento: </label>
            <input type="number" name="cajanumero" required/>
            <button type="submit">Eliminar departamento</button>
        </form>
        <%
        String dato = request.getParameter("cajanumero");
        if (dato != null){
            int deptno = Integer.parseInt(dato);
            controllerdept.eliminarDepartamento(deptno);
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
