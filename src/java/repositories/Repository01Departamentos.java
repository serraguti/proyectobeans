package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Departamento;
import oracle.jdbc.OracleDriver;

public class Repository01Departamentos {

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        return cn;
    }

    //QUE NECESITA EL CONTROLLER PARA DIBUJAR DEPARTAMENTOS???
    public ArrayList<Departamento> getDepartamentos() throws SQLException {
        ArrayList<Departamento> lista = new ArrayList<>();
        Connection cn = this.getConnection();
        String sql = "select * from dept";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            //CREAMOS UN NUEVO OBJETO DEPARTAMENTO
            Departamento dept = new Departamento();
            int numero = rs.getInt("DEPT_NO");
            String nombre = rs.getString("DNOMBRE");
            String localidad = rs.getString("LOC");
            dept.setNumero(numero);
            dept.setNombre(nombre);
            dept.setLocalidad(localidad);
            //AÑADIMOS CADA DEPARTAMENTO A LA LISTA
            lista.add(dept);
        }
        rs.close();
        cn.close();
        return lista;
    }

    public Departamento buscarDepartamento(int deptno) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from dept where dept_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, deptno);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int numero = rs.getInt("DEPT_NO");
        String nombre = rs.getString("DNOMBRE");
        String localidad = rs.getString("LOC");
        Departamento dept = new Departamento();
        dept.setNumero(numero);
        dept.setNombre(nombre);
        dept.setLocalidad(localidad);
        rs.close();
        cn.close();
        return dept;
    }

    public void eliminarDepartamento(int deptno) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "delete from dept where dept_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, deptno);
        pst.executeUpdate();
        cn.close();
    }

    public void insertarDepartamento(int deptno, String nombre, String localidad) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "insert into dept values (?,?,?)";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, deptno);
        pst.setString(2, nombre);
        pst.setString(3, localidad);
        pst.executeUpdate();
        cn.close();
    }

    public void modificarDepartamento(int deptno, String nombre, String localidad) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "update dept set dnombre=?, loc=? where dept_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, nombre);
        pst.setString(2, localidad);
        pst.setInt(3, deptno);
        pst.executeUpdate();
        cn.close();
    }
}
