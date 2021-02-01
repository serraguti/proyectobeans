package models;

public class Hospital {

    private int idHospital;
    private String nombre;
    private String direccion;
    private String telefono;
    private int camas;

    public Hospital() {
    }

    public Hospital(int id, String nombre, String dir,
             String tlf, int camas) {
        this.idHospital = id;
        this.nombre = nombre;
        this.direccion = dir;
        this.telefono = tlf;
        this.camas = camas;
    }

    public int getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCamas() {
        return camas;
    }

    public void setCamas(int camas) {
        this.camas = camas;
    }
}
