<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controller01departamentos"
             class="controllers.Controller01Departamentos"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Departamentos Controller</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Número</th>
                    <th>Nombre</th>
                    <th>Localidad</th>
                </tr>
            </thead>
            <tbody>
                <%=controller01departamentos.getTablaDepartamentos()%>
            </tbody>
        </table>
    </body>
</html>
