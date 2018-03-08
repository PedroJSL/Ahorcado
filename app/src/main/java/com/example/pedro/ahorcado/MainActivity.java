package com.example.pedro.ahorcado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] letrasESP = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ", "Z", "X", "C", "V", "B", "N", "M"};
    TableLayout teclado;
    TextView tvVidas;
    TextView tvPalabraOculta, tvPuntos;
    ImageView img, imgDefinicion;
    MotorJuego mj;
    Button bPista, bSiguientePalabra, bNuevaPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teclado = findViewById(R.id.teclado);
        img = findViewById(R.id.imgHorca);
        tvPuntos = findViewById(R.id.tvPuntuacion);
        tvVidas = findViewById(R.id.tvVidas);
        tvPalabraOculta = findViewById(R.id.tvPalabraOculta);
        bPista = findViewById(R.id.bPista);
        bPista.setOnClickListener(this);
        bSiguientePalabra = findViewById(R.id.bSiguientePalabra);
        bSiguientePalabra.setOnClickListener(this);
        bNuevaPartida = findViewById(R.id.bNuevaPartida);
        bNuevaPartida.setOnClickListener(this);
        imgDefinicion = findViewById(R.id.bDefinicion);
        imgDefinicion.setOnClickListener(this);

        mj = new MotorJuego(this);
        mj.mostrarPalabraOculta();
    }

    public void pulsarLetra(View v) {
        TextView aux = (TextView) v;
        if (aux.getCurrentTextColor() == getResources().getColor(R.color.colorAccent) && mj.partidaEnCurso) {
            if (mj.descubrirPalabra(aux.getText().toString())) {
                aux.setTextColor(getResources().getColor(R.color.acierto));
            } else {
                mj.setImagen();
                aux.setTextColor(getResources().getColor(R.color.error));
            }
        }

    }

    public void crearTeclado() {
        teclado.removeAllViews();
        TableRow fila = null;
        TextView bLetra;
        int tvFila = 9;
        int cont = 0;
        int contLetra = 0;
        while (contLetra < letrasESP.length) {
            if (cont == 0) {
                fila = new TableRow(this);
                fila.setGravity(Gravity.CENTER);
                fila.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
                fila.setPadding(5, 5, 5, 5);
                cont++;
            } else if (cont > 0 && cont < (tvFila + 2)) {
                bLetra = (TextView) getLayoutInflater().inflate(R.layout.textview_abecedario, null);
                bLetra.setText(letrasESP[contLetra]);
                fila.addView(bLetra);
                contLetra++;
                cont++;
            } else {
                teclado.addView(fila);
                cont = 0;
            }
        }
        teclado.addView(fila);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bDefinicion:
                mj.mostrarDefinicion();
                break;
            case R.id.bPista:
                mj.mostrarPista();
                break;
            case R.id.bSiguientePalabra:
                mj.siguientePalabra();
                break;
            case R.id.bNuevaPartida:
                mj.iniciarPartida();
                break;
        }
    }
}
