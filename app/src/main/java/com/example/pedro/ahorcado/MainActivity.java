package com.example.pedro.ahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private static final String[] letrasESP = {"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Ñ","Z","X","C","V","B","N","M"};
TableLayout teclado;
TextView tvPalabraOculta;
MotorJuego mj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teclado = findViewById(R.id.teclado);
        crearTeclado();
        tvPalabraOculta = findViewById(R.id.palabraOculta);
        mj = new MotorJuego();
        mostrarPalabraOculta();
    }

    private void mostrarPalabraOculta(){
        tvPalabraOculta.setText(mj.p.getPalabraOculta());
    }

    public void pulsarLetra(View v){
        TextView aux = (TextView) v;
        if(aux.getCurrentTextColor()==getResources().getColor(R.color.colorAccent)) {
            if (mj.p.contieneLetra(aux.getText().toString())) {
                aux.setTextColor(getResources().getColor(R.color.acierto));
                mostrarPalabraOculta();
            } else {
                aux.setTextColor(getResources().getColor(R.color.error));
            }

        }


    }

    private void crearTeclado(){
        TableRow fila = null;
        TextView bLetra;
        int ancho = getResources().getConfiguration().screenWidthDp;
        int tvFila = ancho/50;
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
