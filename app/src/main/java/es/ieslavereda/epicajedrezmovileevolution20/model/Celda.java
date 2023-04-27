package es.ieslavereda.epicajedrezmovileevolution20.model;

import java.io.Serializable;

import static com.diogonunes.jcolor.Ansi.colorize;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import es.ieslavereda.epicajedrezmovileevolution20.MainActivity;
import es.ieslavereda.epicajedrezmovileevolution20.R;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public class Celda extends androidx.appcompat.widget.AppCompatImageView implements Serializable {
    private Piece piece;

    private ColoresTablero coloresTablero;
    private int color;
    private int original;
    private Tablero tablero;
    private Cordenada cordenada;

    public Celda(Context context, Tablero tablero, Cordenada cordenada,ColoresTablero coloresTablero) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;
        this.cordenada = cordenada;
        this.tablero = tablero;
        this.piece = null;
        this.original=(((cordenada.getFila()-1 + cordenada.getCol() - 'A')%2==0)? coloresTablero.getColoresWhite():coloresTablero.getColoresBlack());
        this.color = original;
        setMaxHeight(widh/10);
        setMinimumHeight(widh/10);
        setMaxWidth(widh/10);
        setMinimumWidth(widh/10);
        setBackgroundColor(getResources().getColor(color));
        setPadding(0,0,0,0);
        setScaleType(ScaleType.FIT_CENTER);
        setAdjustViewBounds(true);
    }

    public void setColoresTablero(ColoresTablero coloresTablero) {
        this.coloresTablero = coloresTablero;
        original=(((cordenada.getFila()-1 + cordenada.getCol() - 'A')%2==0)? coloresTablero.getColoresWhite():coloresTablero.getColoresBlack());
        setBackgroundColor(original);
    }

    public Celda(Context context, Tablero tablero, Cordenada cordenada, Piece p) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;
        this.cordenada = cordenada;
        this.tablero = tablero;
        this.piece = null;
        this.original=(((cordenada.getFila()-1 + cordenada.getCol() - 'A')%2==0)?R.color.cellWhite:R.color.cellBlack);
        this.color = original;
        setBackgroundColor(getResources().getColor(color));
        piece = p;
        setMaxHeight(widh/10);
        setMinimumHeight(widh/10);
        setMaxWidth(widh/10);
        setMinimumWidth(widh/10);
        setPadding(0,0,0,0);
        setScaleType(ScaleType.FIT_CENTER);
        setAdjustViewBounds(true);
    }

    public Tablero getTablero(){
        return tablero;
    }
    public Cordenada getCordenada(){
        return cordenada;
    }

    public Piece getPiece(){
        return piece;
    }
    public void resetColor(){
        color = original;
        setBackgroundColor(getResources().getColor(color));
    }

    public int getColor(){
        return color;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
        if (piece==null)
            setImageResource(0);
        else
            setImageResource(piece.getType().getShape());
    }

    public boolean isEmpty(){
        return piece==null;
    }
    public void highLight(){
        if (isEmpty()){
            if (original == R.color.cellWhite)
                color = R.color.hightLight_white;
            else
                color = R.color.hightLight_black;

        }else {
            color = ((original == R.color.cellBlack)? R.color.hightLight_black_Kill : R.color.hightLight_white_Kill);
        }
        setBackgroundColor(getResources().getColor(color));
    }

    public void hightLightHake(){
        color = (original == R.color.cellBlack)? R.color.HIGHLIGHT_JAKE_BLACK : R.color.HIGHLIGHT_JAKE_WHITE;
        setBackgroundColor(color);
    }

    public boolean isHighLight(){
        return color==R.color.hightLight_white||color == R.color.hightLight_black || color == R.color.hightLight_black_Kill|| color == R.color.hightLight_white_Kill;
    }


}

