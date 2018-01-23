package com.example.pedro.ahorcado;

public class Palabra {
    private String palabra;
    private String pista;
    private String[] palabraOculta;
    private String[] palabraLetraALetra;

    public Palabra(String palabra, String pista) {
        this.palabra = palabra;
        this.pista = pista;

        setPalabraOculta();
    }

    public boolean contieneLetra(String letra) {
        if (palabra.contains(letra)) {
            descubrirPalabra(letra);
            return true;
        }
        return false;
    }

    private void descubrirPalabra(String letra) {
        for (int i = 0; i < palabraLetraALetra.length; i++) {
            if (palabraLetraALetra[i].equals(letra)) {
                palabraOculta[i] = letra;
            }
        }
    }

    private boolean palabraDescubierta(){
        //for (int i)
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

    public String getPalabra() {
        return palabra;
    }

    public String getPista() {
        return pista;
    }

    public String getPalabraOculta() {
        String aux = "";
        for (String caracter : palabraOculta) {
            aux += caracter + " ";
        }
        return aux.trim();
    }
}
