package com.example.pedro.ahorcado;

public class Palabra {

    private String palabra;
    private String pista;
    String[] palabraOculta;
    String[] palabraLetraALetra;

    public Palabra(String palabra, String pista) {
        this.palabra = palabra;
        this.pista = pista;
        setPalabraOculta();
    }

    public boolean contieneLetra(String letra) {
        if (palabra.contains(letra)) {
            return true;
        }
        return false;
    }

    private void setPalabraOculta() {
        palabraLetraALetra = new String[palabra.length()];
        for (int i = 0; i < palabraLetraALetra.length; i++) {
            palabraLetraALetra[i] = String.valueOf(palabra.charAt(i));
        }
        palabraOculta = new String[palabraLetraALetra.length];
        for (int i = 0; i < palabraLetraALetra.length; i++) {
            palabraOculta[i] = "_";
        }
    }

    public String getPalabraOculta() {
        String aux = "";
        for (String caracter : palabraOculta) {
            aux += caracter + " ";
        }
        return aux.trim();
    }

    public String getPista() {
        return pista;
    }
}
