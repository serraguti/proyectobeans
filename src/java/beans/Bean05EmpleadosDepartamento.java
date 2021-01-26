package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.OracleDriver;

public class Bean05EmpleadosDepartamento {

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        return cn;
    }

    //QUEREMOS LOS DEPARTAMENTOS
    //DIBUJADOS EN UN SELECT
    public String getDepartamentosSelect() throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from dept";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String html = "";
        while (rs.next()) {
            String numero = rs.getString("DEPT_NO");
            String nombre = rs.getString("DNOMBRE");
            html += "<option value='" + numero + "'>" + nombre + "</option>";
        }
        rs.close();
        cn.close();
        return html;
    }

    //QUEREMOS LOS EMPLEADOS DE UN DEPARTAMENTO
    //DIBUJADOS CON UNA TABLA
    public String getEmpleadosTabla(int iddepartamento) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from emp where dept_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, iddepartamento);
        ResultSet rs = pst.executeQuery();
        String html = "";
        while (rs.next()) {
            String apellido = rs.getString("APELLIDO");
            String oficio = rs.getString("OFICIO");
            String salario = rs.getString("SALARIO");
            html += "<tr>";
            html += "<td>" + apellido + "</td>";
            html += "<td>" + oficio + "</td>";
            html += "<td>" + salario + "</td>";
            html += "</tr>";
        }
        rs.close();
        cn.close();
        return html;
    }
}
