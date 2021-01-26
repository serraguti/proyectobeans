<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bean06"
             class="beans.Bean06EmpleadosDepartamentoLista"
             scope="request"/>
<jsp:useBean id="bean05"
             class="beans.Bean05EmpleadosDepartamento"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .rojo {
                    color:red;
            }                
            .azul {
                color:blue;
            }
        </style>
    </head>
    <body>
        <h1>Empleados departamento</h1>
        <ul>
            <%=bean06.getDepartamentosSelect()%>
        </ul>
        <%
        String dato = request.getParameter("deptno");
        if (dato != null){
            int iddepartamento = Integer.parseInt(dato);
            %>
            <table border="1" class="azul">
                <thead>
                    <tr>
                        <th>APELLIDO</th>
                        <th>OFICIO</th>
                        <th>SALARIO</th>
                    </tr>
                </thead>
                <tbody>
                    <%=bean05.getEmpleadosTabla(iddepartamento)%>
                </tbody>
            </table>
            <%
        }
        %>
    </body>
</html>
