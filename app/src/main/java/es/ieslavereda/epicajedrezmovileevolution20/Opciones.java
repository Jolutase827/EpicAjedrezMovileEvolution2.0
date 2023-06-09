package es.ieslavereda.epicajedrezmovileevolution20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import es.ieslavereda.epicajedrezmovileevolution20.model.ColoresTablero;
import es.ieslavereda.epicajedrezmovileevolution20.model.Tablero;

public class Opciones extends AppCompatActivity {

    private Button clasic,disierto,sandia,guardar;
    private Tablero tablero;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        tablero = findViewById(R.id.tablero);
        clasic = findViewById(R.id.clasic);
        disierto = findViewById(R.id.desierto);
        sandia = findViewById(R.id.sandia);
        guardar = findViewById(R.id.guardar);

        clasic.setOnClickListener(view -> {
            tablero.setColoresTablero(new ColoresTablero(R.color.cellBlack,R.color.cellWhite));
        });


        disierto.setOnClickListener(view -> {
            tablero.setColoresTablero(new ColoresTablero(R.color.cellDessertBlack,R.color.cellDessertWhite));
        });


        sandia.setOnClickListener(view -> {
            tablero.setColoresTablero(new ColoresTablero(R.color.cellSandiaBlack,R.color.cellSandiaWhite));
        });


        guardar.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("colores",tablero.getColoresTablero());
            setResult(RESULT_OK,intent);
            finish();
        });

    }
}