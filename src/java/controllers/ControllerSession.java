package controllers;

import java.util.ArrayList;

public class ControllerSession {

    private ArrayList<String> listadoctores;

    public ControllerSession() {
        this.listadoctores = new ArrayList<>();
    }

    public ArrayList<String> getListadoctores() {
        return listadoctores;
    }

    public void setListadoctores(ArrayList<String> listadoctores) {
        this.listadoctores = listadoctores;
    }
}
