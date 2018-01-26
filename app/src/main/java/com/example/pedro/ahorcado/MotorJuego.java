package com.example.pedro.ahorcado;

import android.view.View;

public class MotorJuego {

    private int[] imagenesError = {R.drawable.fallo_0, R.drawable.fallo_1, R.drawable.fallo_2,
            R.drawable.fallo_3, R.drawable.fallo_4, R.drawable.fallo_5, R.drawable.fallo_6};

    private Palabra p;
    private Biblioteca b;
    boolean partidaEnCurso = false;
    private int errores;
    private int puntuacion;
    private int vidas;
    private MainActivity m;

    public MotorJuego(MainActivity main) {
        this.m = main;
        vidas = Integer.parseInt(m.tvVidas.getText().toString());
        b = new Biblioteca();
        iniciarPartida();
    }

    public boolean descubrirPalabra(String letra) {
        if (p.contieneLetra(letra)) {
            for (int i = 0; i < p.palabraLetraALetra.length; i++) {
                if (p.palabraLetraALetra[i].equals(letra)) {
                    p.palabraOculta[i] = letra;
                }
            }
            if (palabraDescubierta()) {
                partidaGanada();
            }
            return true;
        }
        errores++;
        return false;
    }

    private boolean palabraDescubierta() {
        for (int i = 0; i < p.palabraLetraALetra.length; i++) {
            if (!p.palabraLetraALetra[i].equals(p.palabraOculta[i])) {
                return false;
            }
        }
        return true;
    }


    public void setImagen() {
        if (errores < imagenesError.length) {
            if (errores == 6) {
                partidaPerdida();
            }
            m.img.setImageResource(imagenesError[errores]);
        }
    }

    private void partidaPerdida() {
        partidaEnCurso = false;
        vidas -= 1;
        m.tvVidas.setText(String.valueOf(vidas));
        m.bAyuda.setVisibility(View.GONE);
        if (vidas <= 0) {
            finJuego();
        }else{
            m.bSiguientePalabra.setVisibility(View.VISIBLE);
        }

    }

    private void partidaGanada() {
        puntuacion += 100;
        partidaEnCurso = false;
        m.tvPuntos.setText(String.valueOf(puntuacion));
        if(!b.palabras.isEmpty()){
            m.bAyuda.setVisibility(View.GONE);
            m.bSiguientePalabra.setVisibility(View.VISIBLE);
        }else{
            m.bAyuda.setVisibility(View.GONE);
            m.bNuevaPartida.setVisibility(View.VISIBLE);
        }

    }

    public void iniciarPartida() {
        p = b.getPalabra();
        partidaEnCurso = true;
        errores = 0;
        setImagen();
    }

    public void mostrarPalabraOculta() {
        m.tvPalabraOculta.setText(p.getPalabraOculta());
    }


    public void mostrarAyuda() {
    }

    public void nuevaPartida() {
    }

    private void finJuego() {
        m.bNuevaPartida.setVisibility(View.VISIBLE);
    }
}
