package com.example.pedro.ahorcado;

import java.util.ArrayList;

public class Biblioteca {
    private static final String[] palabrasString = {"ANDROID","BELINGO"};
    private static final String[] pista = {"SO del movil en el que juegas","Party, fiesta YUJU!"};
    ArrayList<Palabra> palabras;

    public Biblioteca(){
        palabras = new ArrayList<>();
        for (int j = 0; j < palabrasString.length; j++) {
            Palabra p = new Palabra(palabrasString[j],pista[j]);
            palabras.add(p);
        }
    }

    public Palabra getPalabra(){
        int random =(int) (Math.random()*palabras.size());
        Palabra p = palabras.get(random);
        return p;
    }
}
