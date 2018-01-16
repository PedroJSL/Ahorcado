package com.example.pedro.ahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private static final String[] letras = {"A","B","C","D","E","F","G","H","I","J","K","L","M","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
TableLayout teclado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teclado = findViewById(R.id.teclado);
        crearTeclado();
    }

    public void pulsarLetra(View v){

    }

    private void crearTeclado(){
        TableRow fila = null;
        TextView bLetra;
        int ancho = getResources().getConfiguration().screenWidthDp;
        int tvFila = ancho/50;
        int cont = 0;
        int contLetra = 0;

        while (contLetra<letras.length) {
            if(cont==0){
                fila = new TableRow(this);
                fila.setGravity(Gravity.CENTER);
                fila.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT));
                cont++;
            }else if(cont>0&&cont<tvFila+2){
                bLetra = (TextView)getLayoutInflater().inflate(R.layout.textview_abecedario,null);
                bLetra.setText(letras[contLetra]);
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
