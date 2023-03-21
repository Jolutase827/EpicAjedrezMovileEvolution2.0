package es.ieslavereda.epicajedrezmovileevolution20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TableLayout tableLayout;
    private TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        tableLayout = findViewById(R.id.tableLayaut);
        TableRow tableRowAux = new TableRow(this);
        tableLayout.addView(tableRowAux);
        tableRowAux.addView(getTextView(" "));
        for (int x = 0;x<8;x++){
            tableRowAux.addView(getTextView(""+((char)(x+'A'))));
        }
        tableRowAux.addView(getTextView(" "));
        for (int i=0; i<8;i++){
            tableRowAux = new TableRow(this);
            tableLayout.addView(tableRowAux);
            tableRowAux.addView(getTextView(""+(i+1)));
            for (int j = 0; j<8;j++){
                tableRowAux.addView(new Celda(this, null,new Cordenada((char) ('A'+j),1+i)));
            }
            tableRowAux.addView(getTextView(""+(i+1)));
        }
        tableRowAux = new TableRow(this);
        tableLayout.addView(tableRowAux);
        tableRowAux.addView(getTextView(" "));
        for (int x = 0;x<8;x++){
            tableRowAux.addView(getTextView(""+((char)(x+'A'))));
        }
        tableRowAux.addView(getTextView(" "));
    }

    private TextView getTextView(String texto){
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;
        TextView exit = new TextView(this);
        exit.setText(texto);
        exit.setTextColor(getResources().getColor(R.color.white));
        exit.setBackgroundColor(getResources().getColor(R.color.black));
        exit.setWidth(widh/10);
        exit.setTextSize(22);
        exit.setHeight(widh/10);
        exit.setGravity(Gravity.CENTER);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(exit.getText());
            }
        });
        return exit;
    }

    @Override
    public void onClick(View view) {
        textView.setText((CharSequence) ((Celda) view).getCordenada());
    }
}