package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import models.Hospital;
import repositories.RepositoryHospital;

public class ControllerHospital {

    RepositoryHospital repo;

    public ControllerHospital() {
        this.repo = new RepositoryHospital();
    }

    public String getHospitales() throws SQLException {
        ArrayList<Hospital> hospitales = this.repo.getHospitales();
        String html = "";
        for (Hospital h : hospitales) {
            html += "<tr>";
            html += "<td>" + h.getNombre() + "</td>";
            html += "<td>" + h.getDireccion() + "</td>";
            html += "<td>" + h.getTelefono() + "</td>";
            html += "<td>" + h.getCamas() + "</td>";
            html += "</tr>";
        }
        return html;
    }

    public void insertarHospital(String nombre, String direccion,
            String tlf, int camas) throws SQLException {
        this.repo.insertarHospital(nombre, direccion, tlf, camas);
    }
}
