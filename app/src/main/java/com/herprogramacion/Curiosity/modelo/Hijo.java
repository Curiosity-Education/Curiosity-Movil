package com.herprogramacion.Curiosity.modelo;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Gerson on 05/04/16.
 */
public class Hijo {

    private String nombre_completo;
    private String promedio;
    private String actividad;

    public Hijo() {
    }

    public Hijo(String nombre_completo, String promedio,String actividad) {
        this.nombre_completo = nombre_completo;
        this.promedio = promedio;
        this.actividad = actividad;
    }

    public static  List<Hijo> hijos = new ArrayList<Hijo>();
    LinkedList<String> spinnerArray = new LinkedList<String>();

    static {


    }

    public String getNombre_completo() { return nombre_completo;}

    public String getMaxPts() { return promedio; }

    public String getActividad() { return actividad; }

    public void addToList(){
        hijos.add(this);
    }
    public void addListAdapter(String text){
        spinnerArray.add(text);
    }
    public LinkedList<String> getlistaAdapter(){
        return spinnerArray;
    }

}
