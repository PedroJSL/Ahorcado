package com.example.pedro.ahorcado;

import android.widget.ImageView;
import android.widget.TextView;

public class MotorJuego {

    int[] imagenesError = {R.drawable.fallo_0, R.drawable.fallo_1, R.drawable.fallo_2,
            R.drawable.fallo_3, R.drawable.fallo_4, R.drawable.fallo_5, R.drawable.fallo_6};

    Palabra p;
    Biblioteca b;
    ImageView img;
    //Reproductor r;
    TextView tvPalabraOculta;
    TextView tvPuntos;
    boolean partidaEnCurso = false;
    int errores;
    int puntuacion;

    public MotorJuego(ImageView img, TextView palabraOculta, TextView tvPuntos) {
        this.img = img;
        this.tvPalabraOculta = palabraOculta;
        this.tvPuntos = tvPuntos;
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
            if(palabraDescubierta()){
                partidaGanada();
            }
            return true;
        }
        return false;
    }

    public boolean palabraDescubierta() {
        for (int i = 0; i < p.palabraLetraALetra.length; i++) {
            if (p.palabraLetraALetra[i].equals(p.palabraOculta[i])) {
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
            img.setImageResource(imagenesError[errores]);
        }
    }

    public void partidaPerdida() {
        partidaEnCurso = false;

    }

    public void partidaGanada() {
        puntuacion += 100;
        partidaEnCurso = false;
        setPuntuacion();
    }

    public void iniciarPartida() {
        p = b.getPalabra();
        partidaEnCurso = true;
        errores = 0;
        setImagen();
    }

    public void mostrarPalabraOculta() {
        tvPalabraOculta.setText(p.getPalabraOculta());
    }

    public void setPuntuacion() {
        tvPuntos.setText(puntuacion);
    }


}
