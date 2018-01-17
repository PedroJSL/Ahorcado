package com.example.pedro.ahorcado;

/**
 * Created by pedro on 16/01/2018.
 */

public class Biblioteca {
    private static final String[] palabrasString = {"ANDROID"};
    private static final String[] pista = {""};
    private static int i = 0;
    private Palabra[] palabras;

    public Biblioteca(){
        for (int j = 0; j < palabrasString.length; j++) {
            Palabra p = new Palabra(palabrasString[j],pista[j]);
            palabras[j]=p;
        }
    }

    public Palabra getPalabra(){
        Palabra palabra = palabras[i];
        i++;
        return palabra;
    }
}
