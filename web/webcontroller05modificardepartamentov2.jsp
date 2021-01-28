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
        <%
        String dato = request.getParameter("cajanumero");
        if (dato != null){
            int deptno = Integer.parseInt(dato);
            String nombre = request.getParameter("cajanombre");
            String localidad = request.getParameter("cajalocalidad");
            controllerdept.modificarDepartamento(deptno, nombre, localidad);
        }
        %>
        <h1>Modificar departamento v.2</h1>
        <form method="post">
            <label>Seleccione departamento: </label>
            <select name="selectdepartamento">
                <%=controllerdept.getSelectDepartamentos()%>
            </select>
            <button type="submit">Editar</button>
        </form>
        <%
        String datodept = request.getParameter("selectdepartamento");
        if (datodept != null){
            int deptno = Integer.parseInt(datodept);
            %>
            <form method="post">
                <%=controllerdept.getFormularioDepartamentos(deptno)%>
                <button type="submit">Modificar</button>
            </form>
            <%
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
