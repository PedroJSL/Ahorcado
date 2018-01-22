package com.example.pedro.ahorcado;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by pedro on 16/01/2018.
 */

public class Biblioteca {
    private static final String[] palabrasString = {"ANDROID"};
    private static final String[] pista = {""};
    private ArrayList<Palabra> palabras;

    public Biblioteca(){
        palabras = new ArrayList<>();
        for (int j = 0; j < palabrasString.length; j++) {
            Palabra p = new Palabra(palabrasString[j],pista[j]);
            palabras.add(p);
        }
    }

    public Palabra getPalabra(){
        int random =(int) Math.random()*palabras.size();
        Palabra p = palabras.get(random);
        palabras.remove(p);
        return p;
    }
}
