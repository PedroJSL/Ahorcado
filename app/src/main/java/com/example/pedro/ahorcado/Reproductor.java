package com.example.pedro.ahorcado;


import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class Reproductor {

    MediaPlayer mp;
    Context m;

    int[] errorSounds = {R.raw.error_1, R.raw.error_2};
    int acierto = R.raw.acierto;
    int derrota = R.raw.derrota;
    int victoria = R.raw.victoria;
    int ultimoIntento = R.raw.ultimo_intento;

    public Reproductor(Context m) {
        this.m = m;
    }

    public void reproducirSonido(int sonido) {
        mp = MediaPlayer.create(m, sonido);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp = null;
            }
        });
    }

    public void reproducirFallo() {
        int random = (int) (Math.random() * errorSounds.length);
        mp = MediaPlayer.create(m, errorSounds[random]);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp = null;
            }
        });
    }

}
