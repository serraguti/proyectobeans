package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import models.DetalleHospital;
import models.Doctor;
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
            html += "<td>";
            html += "<a href='webcontroller07detalleshospital.jsp?idhospital=";
            html += h.getIdHospital() + "'>Detalles</a>";
            html += "</td>";
            html += "<td>";
            html += "<a href='webcontroller07doctoreshospital.jsp?idhospital=";
            html += h.getIdHospital() + "'>Doctores</a>";
            html += "</td>";
            html += "</tr>";
        }
        return html;
    }

    public void insertarHospital(String nombre, String direccion,
            String tlf, int camas) throws SQLException {
        this.repo.insertarHospital(nombre, direccion, tlf, camas);
    }

    public String getDetalleHospital(int idhospital) throws SQLException {
        DetalleHospital detalle = this.repo.getDetallesHospital(idhospital);
        String html = "<h1>Personas: " + detalle.getPersonas() + "</h1>";
        html += "<h1>Suma salarial: " + detalle.getSumaSalarial() + "</h1>";
        html += "<h1>Media salarial: " + detalle.getMediaSalarial() + "</h1>";
        return html;
    }

    public String getFilasDoctores(int idhospital) throws SQLException {
        ArrayList<Doctor> doctores = this.repo.getDoctores(idhospital);
        String html = "";
        for (Doctor doc : doctores) {
            html += "<tr>";
            html += "<td>" + doc.getApellido() + "</td>";
            html += "<td>" + doc.getEspecialidad() + "</td>";
            html += "<td>" + doc.getSalario() + "</td>";
            html += "<td>" + doc.getIdHospital() + "</td>";
            html += "</tr>";
        }
        return html;
    }

    public void incrementarSalarioDoctores(int incremento, int idhospital)
            throws SQLException {
        this.repo.incrementarSalarioDoctores(incremento, idhospital);
    }

    public String getFilasDoctores() throws SQLException {
        ArrayList<Doctor> doctores = this.repo.getDoctores();
        String html = "";
        for (Doctor doc : doctores) {
            html += "<tr>";
            html += "<td>" + doc.getApellido() + "</td>";
            html += "<td>" + doc.getEspecialidad() + "</td>";
            html += "<td>" + doc.getSalario() + "</td>";
            html += "<td>" + doc.getIdHospital() + "</td>";
            html += "</tr>";
        }
        return html;
    }
}
