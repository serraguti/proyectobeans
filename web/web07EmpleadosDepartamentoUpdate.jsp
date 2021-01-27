<%-- 
Recuperar de cada Bean (anterior) un dibujo.
En el nuevo Bean, creamos un método para incrementar el 
salario de los empleados por un número de departamento.
Queremos dibujar un desplegable con los departamentos.
Una caja para el incremento.
Al pulsar el botón, vemos los empleados 
del departamento con su incremento.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="beandibujos"
             class="beans.Bean05EmpleadosDepartamento"
             scope="request"/>
<jsp:useBean id="beanaccion"
             class="beans.Bean07EmpleadosDepartamentoUpdate"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reutilización Beans</h1>
        <form method="post">
            <label>Departamento: </label>
            <select name="selectdepartamento">
                <%=beandibujos.getDepartamentosSelect()%>    
            </select><br/>
            <label>Incremento salarial: </label>
            <input type="number" name="cajaincremento"/><br/>
            <button type="submit">Incrementar salarios</button>
        </form>
        <%
        String dato = request.getParameter("selectdepartamento");
        if (dato != null){
            int deptno = Integer.parseInt(dato);
            String datoincremento = request.getParameter("cajaincremento");
            int incremento = Integer.parseInt(datoincremento);
            beanaccion.IncrementarSalariosEmpleados(deptno, incremento);
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>Apellido</th>
                        <th>Oficio</th>
                        <th>Salario</th>
                    </tr>
                </thead>
                <tbody>
                    <%=beandibujos.getEmpleadosTabla(deptno)%>        
                </tbody>
            </table>
            <%
        }    
        %>
    </body>
</html>
