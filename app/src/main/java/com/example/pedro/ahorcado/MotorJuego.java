package com.example.pedro.ahorcado;

import android.content.Context;
import android.widget.ImageView;

public class MotorJuego {

    int[] imagenesError = {R.drawable.fallo_0, R.drawable.fallo_1, R.drawable.fallo_2,
            R.drawable.fallo_3, R.drawable.fallo_4, R.drawable.fallo_5, R.drawable.fallo_6};

    Palabra p;
    Biblioteca b;
    Context main;
    ImageView img;
    //Reproductor r;
    int errores;
    int puntuacion;

    public MotorJuego(ImageView img, Context main) {
        this.img = img;
        this.main = main;
        b = new Biblioteca();
        p = b.getPalabra();
        errores = 0;
        setImagen();
        puntuacion = 0;
    }


    public void setImagen() {
        if (errores == 6) {
            partidaPerdida();
        }
        img.setImageResource(imagenesError[errores]);
    }

    public void partidaPerdida() {

    }

    public void partidaGanada(){

    }

    public void iniciarPartida() {
        p = b.getPalabra();
        errores = 0;
        setImagen();
    }



}
