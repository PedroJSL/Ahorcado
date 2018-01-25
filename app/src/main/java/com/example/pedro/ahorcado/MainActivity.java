package com.example.pedro.ahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

private static final String[] letrasESP = {"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Ñ","Z","X","C","V","B","N","M"};
TableLayout teclado;
TextView tvPalabraOculta, tvPuntos;
ImageView img;
MotorJuego mj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teclado = findViewById(R.id.teclado);
        img = findViewById(R.id.horca);
        tvPuntos = findViewById(R.id.score);
        crearTeclado();
        tvPalabraOculta = findViewById(R.id.palabraOculta);
        mj = new MotorJuego(img,tvPalabraOculta,tvPuntos);
        mj.mostrarPalabraOculta();
    }

    public void pulsarLetra(View v){
        TextView aux = (TextView) v;
        if(aux.getCurrentTextColor()==getResources().getColor(R.color.colorAccent)&&mj.partidaEnCurso) {
            if (mj.descubrirPalabra(aux.getText().toString())) {
                aux.setTextColor(getResources().getColor(R.color.acierto));
                mj.mostrarPalabraOculta();
            } else {
                mj.errores++;
                mj.setImagen();
                aux.setTextColor(getResources().getColor(R.color.error));
            }
        }
    }

    private void crearTeclado(){
        TableRow fila = null;
        TextView bLetra;
        int tvFila = 10;
        int cont = 0;
        int contLetra = 0;
        while (contLetra< letrasESP.length) {
            if(cont==0){
                fila = new TableRow(this);
                fila.setGravity(Gravity.CENTER);
                fila.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT));
                fila.setPadding(5,5,5,5);
                cont++;
            }else if(cont>0&&cont<tvFila+2){
                bLetra = (TextView)getLayoutInflater().inflate(R.layout.textview_abecedario,null);
                bLetra.setText(letrasESP[contLetra]);
                fila.addView(bLetra);
                contLetra++;
                cont++;
            }else{
                teclado.addView(fila);
                cont = 0;
            }
        }
        teclado.addView(fila);

    }
}
