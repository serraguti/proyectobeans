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
        <h1>Modificar departamentos v.3</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Número</th>
                    <th>Nombre</th>
                    <th>Localidad</th>
                </tr>
            </thead>
            <tbody>
                <%=controllerdept.getTablaEdicionDepartamentos()%>
            </tbody>
        </table>      
        <script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function() {
                
            });
        </script>
    </body>
</html>
