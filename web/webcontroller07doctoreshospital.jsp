<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controllerhospital"
             class="controllers.ControllerHospital"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Doctores</h1>
        <a href="webcontroller07listahospitales.jsp">
            Volver a Hospitales
        </a>
        <%
        String dato = request.getParameter("idhospital");
        if (dato != null){
            int idhospital = Integer.parseInt(dato);

            String cajaincremento = request.getParameter("cajaincremento");
            if (cajaincremento != null){
                int incremento = Integer.parseInt(cajaincremento);
                controllerhospital.incrementarSalarioDoctores(incremento, idhospital);
            }
            %>
            <form method="get">
                <label>Incremento: </label>
                <input type="text" name="cajaincremento" required/><br/>
                <input type="hidden" name="idhospital"
                       value="<%=idhospital%>"/>
                <button type="submit">
                    Modificar salarios
                </button>
            </form>            
            <table border="1">
                <thead>
                    <tr>
                        <th>Apellido</th>
                        <th>Especialidad</th>
                        <th>Salario</th>
                        <th>Hospital</th>
                    </tr>
                </thead>
                <tbody>
                    <%=controllerhospital.getFilasDoctores(idhospital)%>
                </tbody>
            </table>        
            <%
        }
        %>
    </body>
</html>
