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
        String dato = request.getParameter("eliminar");
        if (dato != null){
            int deptno = Integer.parseInt(dato);
            controllerdept.eliminarDepartamento(deptno);
        }
        %>
        <h1>Eliminar departamento?</h1>
        <form method="post" id="formeliminar">
            <table border="1">
                <thead>
                    <tr>
                        <th>Número</th>
                        <th>Nombre</th>
                        <th>Localidad</th>
                        <th></th>
                    </tr>                
                </thead>
                <tbody>
                    <%=controllerdept.getTablaEliminarDepartamento()%>
                </tbody>
            </table>
        </form>
        <script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function(){
                $("#formeliminar").submit(function(){
                    var respuesta = 
                            confirm("¿Desea eliminar el departamento?");
                    //DEBEMOS DETENER EL ENVIO SI NO QUIERE ELIMINAR
                    if (respuesta == false){
                        return false;
                    }
                });
            });
        </script>
    </body>
</html>
