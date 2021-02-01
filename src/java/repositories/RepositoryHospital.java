package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Hospital;
import oracle.jdbc.OracleDriver;

public class RepositoryHospital {

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        return cn;
    }

    public ArrayList<Hospital> getHospitales() throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from hospital";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Hospital> lista = new ArrayList<>();
        while (rs.next()) {
            int idhospital = rs.getInt("HOSPITAL_COD");
            String nombre = rs.getString("NOMBRE");
            String direccion = rs.getString("DIRECCION");
            String tlf = rs.getString("TELEFONO");
            int camas = rs.getInt("NUM_CAMA");
            Hospital h = new Hospital(idhospital, nombre,
                    direccion, tlf, camas);
            lista.add(h);
        }
        rs.close();
        cn.close();
        return lista;
    }

    private int getMaxIdHospital() throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select max(hospital_cod) + 1 as MAXIMO from hospital";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        int maximo = rs.getInt("MAXIMO");
        rs.close();
        cn.close();
        return maximo;
    }

    public void insertarHospital(String nombre, String direccion,
            String tlf, int camas) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "insert into hospital values (?,?,?,?,?)";
        PreparedStatement pst = cn.prepareStatement(sql);
        int maximo = this.getMaxIdHospital();
        pst.setInt(1, maximo);
        pst.setString(2, nombre);
        pst.setString(3, direccion);
        pst.setString(4, tlf);
        pst.setInt(5, camas);
        pst.executeUpdate();
        cn.close();
    }
}
