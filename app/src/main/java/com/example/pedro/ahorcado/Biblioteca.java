package com.example.pedro.ahorcado;

import java.util.ArrayList;

public class Biblioteca {
    private static final String[] palabrasString = {"ANDROID","BELINGO"};
    private static final String[] pista = {"https://ia801500.us.archive.org/34/items/AndroidAndroid/Android-%28Android%29--.mp4","https://s3.amazonaws.com/fancyfootageclips/website/vid/2015-02-02-concert.mp4"};
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
