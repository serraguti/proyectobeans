/*
create or replace procedure insertarhospital
(p_nombre hospital.nombre%type
, p_direccion hospital.direccion%type
, p_telefono hospital.telefono%type
, p_camas hospital.num_cama%type)
as
  v_maximo hospital.hospital_cod%type;
begin
  select max(hospital_cod) + 1 into v_maximo
  from hospital;
  insert into hospital values (v_maximo, p_nombre
  , p_direccion, p_telefono, p_camas);
  commit;
end;
 */
package repositories;

import java.sql.CallableStatement;
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
        String sql = "{ call insertarhospital(?,?,?,?) }";
        CallableStatement cst = cn.prepareCall(sql);
        cst.setString(1, nombre);
        cst.setString(2, direccion);
        cst.setString(3, tlf);
        cst.setInt(4, camas);
        cst.executeUpdate();
        cn.close();
    }
}
