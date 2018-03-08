package com.example.pedro.ahorcado;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;


public class MotorJuego {

    private int[] imagenesError = {R.drawable.fallo_0, R.drawable.fallo_1, R.drawable.fallo_2,
            R.drawable.fallo_3, R.drawable.fallo_4, R.drawable.fallo_5, R.drawable.fallo_6};

    private int imgVictoria = R.drawable.victoria;

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
        r = new Reproductor(m.getApplicationContext());
        iniciarPartida();
    }

    public boolean descubrirPalabra(String letra) {
        if (p.contieneLetra(letra)) {
            for (int i = 0; i < p.palabraLetraALetra.length; i++) {
                if (p.palabraLetraALetra[i].equals(letra)) {
                    if(p.palabraOculta[i].contains(letra)){
                        return false;
                    }
                    p.palabraOculta[i] = letra;
                }
            }
            if (palabraDescubierta()) {
                palabraAcertada();
            }
            mostrarPalabraOculta();
            return true;
        }
        if(errores<4){
        r.reproducirFallo();
        }else if (errores==4){
            r.reproducirSonido(r.ultimoIntento);
        }
        errores++;
        mostrarPalabraOculta();
        return false;
    }

    private boolean palabraDescubierta() {
        for (int i = 0; i < p.palabraLetraALetra.length; i++) {
            if (!p.palabraLetraALetra[i].equals(p.palabraOculta[i])) {
                r.reproducirSonido(r.acierto);
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
        r.reproducirSonido(r.derrota);
        partidaEnCurso = false;
        m.imgDefinicion.setVisibility(View.VISIBLE);
        vidas = vidas - 1;
        m.tvVidas.setText(String.valueOf(vidas));
        m.bPista.setVisibility(View.GONE);
        if (vidas <= 0) {
            m.bNuevaPartida.setVisibility(View.VISIBLE);
        } else {
            m.bSiguientePalabra.setVisibility(View.VISIBLE);
        }
    }

    private void palabraAcertada() {
        r.reproducirSonido(r.victoria);
        m.img.setImageResource(imgVictoria);
        puntuacion += 100;
        m.imgDefinicion.setVisibility(View.VISIBLE);
        partidaEnCurso = false;
        m.tvPuntos.setText(String.valueOf(puntuacion));
        b.palabras.remove(p);
        if (!b.palabras.isEmpty()) {
            m.bPista.setVisibility(View.GONE);
            m.bSiguientePalabra.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(m.getApplicationContext(), "No quedan mas palabras, has ganado!", Toast.LENGTH_LONG).show();
            m.bPista.setVisibility(View.GONE);
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
        m.imgDefinicion.setVisibility(View.GONE);
        setImagen();
        mostrarPalabraOculta();
        m.crearTeclado();
        m.tvVidas.setText(String.valueOf(vidas));
        m.tvPuntos.setText(String.valueOf(puntuacion));
        m.bPista.setVisibility(View.VISIBLE);
        m.bNuevaPartida.setVisibility(View.GONE);
        m.bSiguientePalabra.setVisibility(View.GONE);
    }

    public void mostrarPalabraOculta() {
        m.tvPalabraOculta.setText(p.getPalabraOculta());
    }


    public void mostrarPista() {
        int random =(int) (Math.random() * p.palabraOculta.length);
        boolean pista =descubrirPalabra(p.palabraLetraALetra[random]);
        if(!pista){
            mostrarPista();
        }
    }

    public void mostrarDefinicion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(m);
        builder.setTitle("Definición: ");
        builder.setMessage(p.getDefinicion());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void siguientePalabra() {
        p = b.getPalabra();
        partidaEnCurso = true;
        errores = 0;
        m.imgDefinicion.setVisibility(View.GONE);
        setImagen();
        mostrarPalabraOculta();
        m.crearTeclado();
        m.bPista.setVisibility(View.VISIBLE);
        m.bSiguientePalabra.setVisibility(View.GONE);
    }
}
