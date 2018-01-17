package com.example.pedro.ahorcado;

/**
 * Created by pedro on 16/01/2018.
 */

public class Palabra {
    private String palabra;
    private String pista;
    private String palabraOculta;

    public Palabra(String palabra, String pista){
        this.palabra = palabra;
        this.pista = pista;

        setPalabraOculta();
    }

    public boolean contieneLetra(String letra){
        if(palabra.contains(letra)){

            return true;
        }
        return false;
    }


    public void setPalabraOculta(){
        String[] palabraLetraALetra = palabra.split("");
        String aux = "";
        for (int i = 0; i < palabraLetraALetra.length ; i++) {
            aux += "_ ";
        }
        palabraOculta = aux.trim();
    }

    public String getPalabra() {
        return palabra;
    }

    public String getPista() {
        return pista;
    }

    public String getPalabraOculta() {
        return palabraOculta;
    }
}
