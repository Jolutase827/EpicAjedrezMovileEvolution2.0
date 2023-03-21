package es.ieslavereda.epicajedrezmovileevolution20;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

import static com.diogonunes.jcolor.Ansi.colorize;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public class Celda extends androidx.appcompat.widget.AppCompatImageView implements Serializable {
    private Piece piece;

    private int color;
    private int original;
    private Tablero tablero;
    private Cordenada cordenada;

    public Celda(Context context,  Tablero tablero, Cordenada cordenada) {
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;
        this.cordenada = cordenada;
        this.tablero = tablero;
        this.piece = null;
        this.original=(((cordenada.getFila()-1 + cordenada.getCol() - 'A')%2==0)?R.color.cellWhite:R.color.cellBlack);
        this.color = original;
        setMaxHeight(widh/10);
        setMinimumHeight(widh/10);
        setMaxWidth(widh/10);
        setMinimumWidth(widh/10);
        setBackgroundColor(getResources().getColor(color));
        setPadding(0,0,0,0);
        setScaleType(ScaleType.FIT_CENTER);
        setImageResource(R.mipmap.ic_b_knight_foreground);
        setAdjustViewBounds(true);
    }
    public Celda(Context context, Tablero tablero, Cordenada cordenada,Piece p) {
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

    public int getColor(){
        return color;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
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
            color = (original == R.color.cellBlack)? R.color.hightLight_black_Kill :R.color.hightLight_white_Kill;
        }
        setBackgroundColor(getResources().getColor(color));
    }

    public void hightLightHake(){
        color = (original == R.color.cellBlack)? R.color.HIGHLIGHT_JAKE_BLACK : R.color.HIGHLIGHT_JAKE_WHITE;
    }

    public boolean isHighLight(){
        return original!=color;
    }

}

