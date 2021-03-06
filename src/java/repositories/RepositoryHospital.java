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
--------------------------------------------------------------
create or replace procedure detalleshospital
(p_hospitalcod hospital.hospital_cod%type
, v_personas out int, v_suma out int, v_media out int)
as
begin
  select count(doctor_no), sum(salario), avg(salario)
  into v_personas, v_suma, v_media from doctor
  where hospital_cod=p_hospitalcod;
end;
--------------------------------------------------------------------
create or replace procedure incrementarsalariodoctores
(p_incremento int
, p_hospitalcod doctor.hospital_cod%type)
as
begin
  update doctor set salario = salario + p_incremento
  where hospital_cod = p_hospitalcod;
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
import models.DetalleHospital;
import models.Doctor;
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

    public DetalleHospital getDetallesHospital(int idhospital) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "{ call detalleshospital(?, ?, ?, ?) }";
        CallableStatement cst = cn.prepareCall(sql);
        cst.setInt(1, idhospital);
        cst.registerOutParameter(2, java.sql.Types.INTEGER);
        cst.registerOutParameter(3, java.sql.Types.INTEGER);
        cst.registerOutParameter(4, java.sql.Types.INTEGER);
        cst.execute();
        int personas = cst.getInt(2);
        int suma = cst.getInt(3);
        int media = cst.getInt(4);
        DetalleHospital detalle
                = new DetalleHospital(personas, suma, media);
        cn.close();
        return detalle;
    }

    public ArrayList<Doctor> getDoctores(int idhospital) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from doctor where hospital_cod=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, idhospital);
        ResultSet rs = pst.executeQuery();
        ArrayList<Doctor> lista = new ArrayList<>();
        while (rs.next()) {
            int iddoctor = rs.getInt("DOCTOR_NO");
            String ape = rs.getString("APELLIDO");
            String espe = rs.getString("ESPECIALIDAD");
            int sal = rs.getInt("SALARIO");
            int hospitalcod = rs.getInt("HOSPITAL_COD");
            Doctor doc = new Doctor(iddoctor, ape, espe, sal, hospitalcod);
            lista.add(doc);
        }
        rs.close();
        cn.close();
        return lista;
    }

    public void incrementarSalarioDoctores(int incremento, int idhospital) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "{ call incrementarsalariodoctores (?, ?) }";
        CallableStatement cst = cn.prepareCall(sql);
        cst.setInt(1, incremento);
        cst.setInt(2, idhospital);
        cst.executeUpdate();
        cn.close();
    }

    public ArrayList<Doctor> getDoctores() throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from doctor";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Doctor> lista = new ArrayList<>();
        while (rs.next()) {
            int iddoctor = rs.getInt("DOCTOR_NO");
            String ape = rs.getString("APELLIDO");
            String espe = rs.getString("ESPECIALIDAD");
            int sal = rs.getInt("SALARIO");
            int hospitalcod = rs.getInt("HOSPITAL_COD");
            Doctor doc = new Doctor(iddoctor, ape, espe, sal, hospitalcod);
            lista.add(doc);
        }
        rs.close();
        cn.close();
        return lista;
    }

    public ArrayList<Doctor> getDoctoresHospital(ArrayList<String> codigosdoctor)
            throws SQLException {
        //CONCATENAMOS TODOS LOS CODIGOS SEPARADOS POR COMAS
        String datos = "";
        for (String cod : codigosdoctor) {
            datos += cod + ",";
        }
        int ultimacoma = datos.lastIndexOf(",");
        datos = datos.substring(0, ultimacoma);
        Connection cn = this.getConnection();
        String sql = "select * from doctor where doctor_no in (" + datos + ")";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Doctor> lista = new ArrayList<>();
        while (rs.next()) {
            int iddoctor = rs.getInt("DOCTOR_NO");
            String ape = rs.getString("APELLIDO");
            String espe = rs.getString("ESPECIALIDAD");
            int sal = rs.getInt("SALARIO");
            int hospitalcod = rs.getInt("HOSPITAL_COD");
            Doctor doc = new Doctor(iddoctor, ape, espe, sal, hospitalcod);
            lista.add(doc);
        }
        rs.close();
        cn.close();
        return lista;
    }

}
