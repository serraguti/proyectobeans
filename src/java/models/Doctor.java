package models;

public class Doctor {

    private int idDoctor;
    private String apellido;
    private String especialidad;
    private int salario;
    private int idHospital;

    public Doctor() {
    }

    public Doctor(int id, String apellido, String espe,
            int sal, int idhospital) {
        this.idDoctor = id;
        this.apellido = apellido;
        this.especialidad = espe;
        this.salario = sal;
        this.idHospital = idhospital;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
}
