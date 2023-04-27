package es.ieslavereda.epicajedrezmovileevolution20;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import es.ieslavereda.epicajedrezmovileevolution20.model.ColoresTablero;

public class Menu extends AppCompatActivity {

    private Button jugar,opciones,salir;
    private ColoresTablero coloresTablero;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        coloresTablero=new ColoresTablero(R.color.cellBlack,R.color.cellWhite);
        jugar = findViewById(R.id.jugar);
        opciones = findViewById(R.id.opciones);
        salir = findViewById(R.id.salir);
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_CANCELED) {

                    }else if (result.getResultCode() == Activity.RESULT_OK) {
                        coloresTablero = (ColoresTablero)result.getData().getExtras().getSerializable("colores");
                    }
                });
        opciones.setOnClickListener(view -> {
            Intent i = new Intent(this, Opciones.class);
            someActivityResultLauncher.launch(i);
        });
        jugar.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MainActivity.class);
            i.putExtra("colores",coloresTablero);
            view.getContext().startActivity(i);
        });
        salir.setOnClickListener(view -> {finish();});
    }
}