package es.ieslavereda.epicajedrezmovileevolution20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button jugar,opciones,salir;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        jugar = findViewById(R.id.jugar);
        opciones = findViewById(R.id.opciones);
        salir = findViewById(R.id.salir);
        jugar.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(i);
        });
        salir.setOnClickListener(view -> {finish();});
    }
}