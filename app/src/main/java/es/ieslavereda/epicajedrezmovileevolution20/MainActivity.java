package es.ieslavereda.epicajedrezmovileevolution20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                tableRowAux.addView(getImageView(i+j));
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
        return exit;
    }

    private ImageView getImageView(int i){
        ImageView exit = new ImageView(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;
        if (i%2!=0){
            exit.setBackgroundColor(getResources().getColor(R.color.cellBlack));
        }else {
            exit.setBackgroundColor(getResources().getColor(R.color.cellWhite));
        }
        exit.setMaxHeight(widh/10);
        exit.setMinimumHeight(widh/10);
        exit.setMaxWidth(widh/10);
        exit.setMinimumWidth(widh/10);
        return exit;
    }
}