package com.example.pedro.ahorcado;


import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.VideoView;

public class Reproductor implements MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener{

    VideoView vv;
    View pg;
    MediaPlayer mp;
    Context m;

    int[] errorSounds={R.raw.error_1,R.raw.error_2};
    int acierto = R.raw.acierto;
    int derrota = R.raw.derrota;
    int victoria = R.raw.victoria;
    int ultimoIntento = R.raw.ultimo_intento;

    public Reproductor(VideoView vv, View pg, Context m){
        this.vv = vv;
        this.pg = pg;
        this.m = m;
    }

    public void reproducirSonido(int sonido){
        mp = MediaPlayer.create(m,sonido);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp = null;
            }
        });
    }

    public void reproducirFallo(){
        int random = (int) (Math.random()*errorSounds.length);
        mp = MediaPlayer.create(m,errorSounds[random]);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp = null;
            }
        });
    }


    public void reproducirPista(String url){
        vv.setVisibility(View.VISIBLE);
        pg.setVisibility(View.VISIBLE);
        vv.setVideoURI(Uri.parse(url));
        vv.requestFocus();
        vv.setOnPreparedListener(this);
        vv.setOnCompletionListener(this);
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
       mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                if(percent==100){
                    pg.setVisibility(View.GONE);
                    vv.start();
                }
            }
        });


    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        vv.stopPlayback();
        vv.setVisibility(View.GONE);
    }
}
