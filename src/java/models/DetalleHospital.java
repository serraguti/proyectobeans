package models;

public class DetalleHospital {

    private int personas;
    private int sumaSalarial;
    private int mediaSalarial;

    public DetalleHospital() {
    }

    public DetalleHospital(int personas, int suma, int media) {
        this.personas = personas;
        this.sumaSalarial = suma;
        this.mediaSalarial = media;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public int getSumaSalarial() {
        return sumaSalarial;
    }

    public void setSumaSalarial(int sumaSalarial) {
        this.sumaSalarial = sumaSalarial;
    }

    public int getMediaSalarial() {
        return mediaSalarial;
    }

    public void setMediaSalarial(int mediaSalarial) {
        this.mediaSalarial = mediaSalarial;
    }
}
