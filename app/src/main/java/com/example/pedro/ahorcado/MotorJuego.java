package com.example.pedro.ahorcado;

import android.view.View;
import android.widget.Toast;


public class MotorJuego {

    private int[] imagenesError = {R.drawable.fallo_0, R.drawable.fallo_1, R.drawable.fallo_2,
            R.drawable.fallo_3, R.drawable.fallo_4, R.drawable.fallo_5, R.drawable.fallo_6};

    private Palabra p;
    private Biblioteca b;
    private Reproductor r;
    boolean partidaEnCurso = false;
    private int errores;
    private int puntuacion;
    private int vidas;
    private MainActivity m;

    public MotorJuego(MainActivity main) {
        m = main;
        r = new Reproductor(m.vv, m.pg, m.getApplicationContext());
        iniciarPartida();
    }

    public boolean descubrirPalabra(String letra) {
        if (p.contieneLetra(letra)) {
            r.reproducirAcierto();
            for (int i = 0; i < p.palabraLetraALetra.length; i++) {
                if (p.palabraLetraALetra[i].equals(letra)) {
                    p.palabraOculta[i] = letra;
                }
            }
            if (palabraDescubierta()) {
                palabraAcertada();
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
                palabraNoAcertada();
            }
            m.img.setImageResource(imagenesError[errores]);
        }
    }

    private void palabraNoAcertada() {
        partidaEnCurso = false;
        vidas = vidas - 1;
        m.tvVidas.setText(String.valueOf(vidas));
        m.bAyuda.setVisibility(View.GONE);
        if (vidas <= 0) {
            m.bNuevaPartida.setVisibility(View.VISIBLE);
        } else {
            m.bSiguientePalabra.setVisibility(View.VISIBLE);
        }
    }

    private void palabraAcertada() {
        puntuacion += 100;
        partidaEnCurso = false;
        m.tvPuntos.setText(String.valueOf(puntuacion));
        b.palabras.remove(p);
        if (!b.palabras.isEmpty()) {
            m.bAyuda.setVisibility(View.GONE);
            m.bSiguientePalabra.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(m.getApplicationContext(), "No quedan mas palabras, has ganado!", Toast.LENGTH_LONG).show();
            m.bAyuda.setVisibility(View.GONE);
            m.bNuevaPartida.setVisibility(View.VISIBLE);
        }

    }

    public void iniciarPartida() {
        b = new Biblioteca();
        p = b.getPalabra();
        partidaEnCurso = true;
        puntuacion = 0;
        vidas = 3;
        errores = 0;
        setImagen();
        mostrarPalabraOculta();
        m.crearTeclado();
        m.tvVidas.setText(String.valueOf(vidas));
        m.tvPuntos.setText(String.valueOf(puntuacion));
        m.bAyuda.setVisibility(View.VISIBLE);
        m.bNuevaPartida.setVisibility(View.GONE);
        m.bSiguientePalabra.setVisibility(View.GONE);
    }

    public void mostrarPalabraOculta() {
        m.tvPalabraOculta.setText(p.getPalabraOculta());
    }


    public void mostrarAyuda() {
        r.reproducirPista(p.getPista());
    }

    public void siguientePalabra() {
        p = b.getPalabra();
        partidaEnCurso = true;
        errores = 0;
        setImagen();
        mostrarPalabraOculta();
        m.crearTeclado();
        m.bAyuda.setVisibility(View.VISIBLE);
        m.bSiguientePalabra.setVisibility(View.GONE);
    }
}
