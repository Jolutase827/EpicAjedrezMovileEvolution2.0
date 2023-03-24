package es.ieslavereda.epicajedrezmovileevolution20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import es.ieslavereda.epicajedrezmovileevolution20.model.Celda;
import es.ieslavereda.epicajedrezmovileevolution20.model.Cordenada;
import es.ieslavereda.epicajedrezmovileevolution20.model.Tablero;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Cordenada cordenadaPieza;
    private Tablero tableLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = (Tablero) findViewById(R.id.tableLayaut);

        tableLayout.setCellsOnClickListener(this::onClick);

    }


    @Override
    public void  onClick(View view) {
        if (view instanceof Celda){
            Celda celda = (Celda) view;
            if (!celda.isEmpty()&&!celda.isHighLight()) {
                tableLayout.resetColors();
                tableLayout.hightlight(celda.getPiece().getNextMovements(),celda);
                cordenadaPieza = celda.getCordenada();
            }else if (celda.isHighLight()){
                tableLayout.movePiece(cordenadaPieza,celda.getCordenada());
                tableLayout.resetColors();
            }else
                tableLayout.resetColors();
        }
    }
}