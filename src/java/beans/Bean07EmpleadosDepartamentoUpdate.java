/*
Recuperar de cada Bean (anterior) un dibujo.
En el nuevo Bean, creamos un método para incrementar el salario de los empleados por un número de departamento.
Queremos dibujar un desplegable con los departamentos.
Una caja para el incremento.
Al pulsar el botón, vemos los empleados del departamento con su incremento.
 */
package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;

public class Bean07EmpleadosDepartamentoUpdate {

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        return cn;
    }

    public void IncrementarSalariosEmpleados(int deptno, int incremento) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "update emp set salario = salario + ? "
                + "where dept_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, incremento);
        pst.setInt(2, deptno);
        pst.executeUpdate();
        cn.close();
    }
}
