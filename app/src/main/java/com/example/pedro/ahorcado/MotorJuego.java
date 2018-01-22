package com.example.pedro.ahorcado;

/**
 * Created by pedro on 16/01/2018.
 */

public class MotorJuego {
    Palabra p;
    Biblioteca b;
    //Reproductor r;

    public MotorJuego(){
        b = new Biblioteca();
        p = b.getPalabra();
    }
}
