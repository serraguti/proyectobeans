package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import models.DetalleHospital;
import models.Doctor;
import models.Hospital;
import repositories.RepositoryHospital;

public class ControllerHospital {

    private HttpSession session;
    RepositoryHospital repo;

    public ControllerHospital() {
        this.repo = new RepositoryHospital();
    }

    public void setSession(HttpSession session) {
        this.session = session;
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
            html += "<td>";
            html += "<a href='webcontroller08almacenarsessiondoctoresv1.jsp?iddoctor=";
            html += doc.getIdDoctor() + "'>Almacenar</a>";
            html += "</td>";
            html += "</tr>";
        }
        return html;
    }

    public String getFilasDoctoresv2() throws SQLException {
        ArrayList<Doctor> doctores = this.repo.getDoctores();
        ArrayList<String> doctoressesion = (ArrayList) session.getAttribute("DOCTORES");
        String html = "";
        for (Doctor doc : doctores) {
            html += "<tr>";
            html += "<td>" + doc.getApellido() + "</td>";
            html += "<td>" + doc.getEspecialidad() + "</td>";
            html += "<td>" + doc.getSalario() + "</td>";
            html += "<td>" + doc.getIdHospital() + "</td>";
            if (doctoressesion == null) {
                //DIBUJAMOS Almacenar
                html += "<td>";
                html += "<a href='webcontroller08almacenarsessiondoctoresv2.jsp?iddoctor=";
                html += doc.getIdDoctor() + "'>Almacenar</a>";
                html += "</td>";
                //SI EL DOCTOR NO EXISTE EN LA SESION, DIBUJAMOS ALMACENAR
            } else if (doctoressesion.contains(String.valueOf(doc.getIdDoctor())) == false) {
                //DIBUJAMOS Almacenar
                html += "<td>";
                html += "<a href='webcontroller08almacenarsessiondoctoresv2.jsp?iddoctor=";
                html += doc.getIdDoctor() + "'>Almacenar</a>";
                html += "</td>";
            }
            html += "</tr>";
        }
        return html;
    }

    public String getDoctoresSession(ArrayList<String> codigos)
            throws SQLException {
        if (codigos.size() == 0) {
            //NO TENEMOS NADA ALMACENADO
            return "NO EXISTEN DOCTORES";
        }
        ArrayList<Doctor> doctores = this.repo.getDoctoresHospital(codigos);
        String html = "";
        for (Doctor doc : doctores) {
            html += "<tr>";
            html += "<td>" + doc.getApellido() + "</td>";
            html += "<td>" + doc.getEspecialidad() + "</td>";
            html += "<td>" + doc.getSalario() + "</td>";
            html += "</tr>";
        }
        return html;
    }

    public String almacenarDoctorSession(String iddoctor) {
        //MANEJAMOS LA SESSION MANUALMENTE
        ArrayList<String> codigos;
        //PREGUNTAMOS SI EXISTE ALGO EN SESSION
        if (session.getAttribute("DOCTORES") == null) {
            codigos = new ArrayList<>();
        } else {
            codigos = (ArrayList) session.getAttribute("DOCTORES");
        }
        //AÑADIMOS EL NUEVO DOCTOR A LA SESSION SI NO EXISTE
        if (codigos.contains(iddoctor) == false) {
            codigos.add(iddoctor);
            //ALMACENAMOS EL NUEVO VALOR DE SESSION
            session.setAttribute("DOCTORES", codigos);
        }
        return "Doctores almacenados: " + codigos.size();
    }

    public String getDoctoresSession()
            throws SQLException {
        //PREGUNTAMOS SI TENEMOS DATOS EN SESSION
        if (session.getAttribute("DOCTORES") == null) {
            //NO TENEMOS NADA ALMACENADO
            return "";
        }
        ArrayList<String> codigos = (ArrayList) session.getAttribute("DOCTORES");
        ArrayList<Doctor> doctores = this.repo.getDoctoresHospital(codigos);
        String html = "";
        for (Doctor doc : doctores) {
            html += "<tr>";
            html += "<td>" + doc.getApellido() + "</td>";
            html += "<td>" + doc.getEspecialidad() + "</td>";
            html += "<td>" + doc.getSalario() + "</td>";
            html += "<td>";
            html += "<a href='webcontroller08doctoressessionv2.jsp?eliminar=";
            html += doc.getIdDoctor() + "'>Quitar Session</a>";
            html += "</td>";
            html += "</tr>";
        }
        return html;
    }

    public void eliminarDoctorSession(String iddoctor) {
        //RECUPERAMOS LA LISTA DE IDs DOCTORES DE SESSION
        ArrayList<String> doctores = (ArrayList) session.getAttribute("DOCTORES");
        //ELIMINAMOS EL ID DOCTOR DE LA LISTA
        doctores.remove(iddoctor);
        //COMPROBAMOS SI YA NO TENEMOS DOCTORES EN LA LISTA
        if (doctores.size() == 0) {
            //SI NO HAY DOCTORES, BORRAMOS LA SESION
            session.setAttribute("DOCTORES", null);
        } else {
            //ALMACENAMOS LA NUEVA LISTA (SIN DOCTOR) EN SESSION
            session.setAttribute("DOCTORES", doctores);
        }
    }
}
