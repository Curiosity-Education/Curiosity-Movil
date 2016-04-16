package com.herprogramacion.Curiosity.modelo;

import java.util.ArrayList;
import java.util.List;
import com.herprogramacion.Curiosity.R;

/**
 * Created by Gerson on 04/04/16.
 */
public class Momento {
    private int imagen;
    private String descripcion;

    public Momento() {
    }

    public Momento(int imagen, String descripcion) {
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public static final List<Momento> moments = new ArrayList<Momento>();

    static {
        moments.add(new Momento(R.drawable.nuevas1,"Ahora si ya puedes recortar tu imagen y darle tu propio enfoque"));
        moments.add(new Momento(R.drawable.nuevas2,"Juegos, diversión y mucho más ya listo para tus niños ganado medallas"));
        moments.add(new Momento(R.drawable.nuevas3,"Disfruta del nuevo material ya en linea, ¿qué esperas?"));
        moments.add(new Momento(R.drawable.nuevas4,"Inteligencias, temas actividades juegos es un poco de lo que nos conforma !!"));
        moments.add(new Momento(R.drawable.nueva6,"Cambiar la información de ti y de tus hijos jamas fue tan fácil"));
    }

    public int getImagen() { return imagen; }

    public String getDescripcion() { return descripcion; }
}

