/*
Dibujar todos los enfermos al iniciar la página.
Una caja para escribir la inscripción.
Si pulsamos sobre un botón eliminar, borramos al enfermo.
 */
package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.OracleDriver;

public class Bean04EliminarEnfermos {

    //QUE FUNCIONALIDAD DE LA BBDD NECESITAMOS??
    //DIBUJAR ENFERMOS (String)
    //ELIMINAR ENFERMOS  (void)
    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        return cn;
    }

    public String getListaEnfermos() throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from enfermo";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String html = "<ul>";
        while (rs.next()) {
            String apellido = rs.getString("APELLIDO");
            String inscripcion = rs.getString("INSCRIPCION");
            html += "<li>" + apellido + ", " + inscripcion + "</li>";
        }
        html += "</ul>";
        rs.close();
        cn.close();
        return html;
    }

    public void eliminarEnfermo(int inscripcion) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "delete from enfermo where inscripcion=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, inscripcion);
        pst.executeUpdate();
        cn.close();
    }
}
