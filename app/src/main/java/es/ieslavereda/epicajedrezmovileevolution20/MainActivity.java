package es.ieslavereda.epicajedrezmovileevolution20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import es.ieslavereda.epicajedrezmovileevolution20.model.Celda;
import es.ieslavereda.epicajedrezmovileevolution20.model.Color;
import es.ieslavereda.epicajedrezmovileevolution20.model.ColoresTablero;
import es.ieslavereda.epicajedrezmovileevolution20.model.Cordenada;
import es.ieslavereda.epicajedrezmovileevolution20.model.Tablero;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Color turno;
    private Cordenada cordenadaPieza;
    private Tablero tableLayout;
    private Button  volvj, salir;
    private TextView textGana;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = (Tablero) findViewById(R.id.tableLayaut);
        tableLayout.setColoresTablero((ColoresTablero) getIntent().getExtras().getSerializable("colores"));
        turno = Color.WHITE;
        volvj = findViewById(R.id.volvjugar);
        salir = findViewById(R.id.salir);
        textGana = findViewById(R.id.textoganador);

        tableLayout.setCellsOnClickListener(this::onClick);

    }


    @Override
    public void  onClick(View view) {
        if (view instanceof Celda){
            Celda celda = (Celda) view;
            if (!celda.isEmpty()&&!celda.isHighLight()&&turno.equals(celda.getPiece().getColor())) {
                tableLayout.resetColors();
                tableLayout.hightlight(celda.getPiece().getNextMovements(),celda);
                cordenadaPieza = celda.getCordenada();
            }else if (celda.isHighLight()){
                tableLayout.movePiece(cordenadaPieza,celda.getCordenada());
                tableLayout.resetColors();
                turno = turno.next();
                if (tableLayout.movementsValid(turno).size()==0) {
                    textGana.setText(((turno.equals(Color.WHITE)) ? "NEGRAS GANAN " : "BLANCAS GANAN"));
                    volvj.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tableLayout.reiniciarTablero();
                            tableLayout.resetDeadCamp();
                            turno = Color.WHITE;
                            volvj.setVisibility(View.GONE);
                            salir.setVisibility(View.GONE);
                            textGana.setVisibility(View.GONE);
                        }
                    });
                    salir.setOnClickListener(view1 -> {
                        finish();
                    });
                    volvj.setVisibility(View.VISIBLE);
                    salir.setVisibility(View.VISIBLE);
                    textGana.setVisibility(View.VISIBLE);
                }
            }else
                tableLayout.resetColors();

            if (tableLayout.oneColorJake(turno))
                tableLayout.getCelda(tableLayout.getKingPosition(turno)).hightLightHake();
        }
    }
}