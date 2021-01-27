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
        <h1>Detalles departamento</h1>
        <form method="post">
            <label>Departamentos </label>
            <select name="selectdepartamento">
                <%=controllerdept.getSelectDepartamentos()%>
            </select>
            <button type="submit">
                Ver detalle
            </button>
        </form>
        <%
        String dato = request.getParameter("selectdepartamento");
        if (dato != null){
            int deptno = Integer.parseInt(dato);
            %>
            <%=controllerdept.getDetallesDepartamento(deptno)%>
            <%
        }
        %>
    </body>
</html>
