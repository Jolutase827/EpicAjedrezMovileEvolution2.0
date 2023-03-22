package es.ieslavereda.epicajedrezmovileevolution20.model;

import java.io.Serializable;
import java.util.Set;

import static com.diogonunes.jcolor.Ansi.colorize;

import es.ieslavereda.epicajedrezmovileevolution20.R;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public abstract class Piece implements Serializable {
    private Type type;
    private Celda celda;

    private boolean move;

    public Piece(Type shape, Celda celda){
        this.type =shape;
        this.celda = celda;
        move = false;
        putInYourPlace();
    }

    public void moveTo(Cordenada cordenada){
        Tablero t = getCelda().getTablero();
        if (t.getCelda(cordenada)!=null){
            move = true;
            getCelda().setPiece(null);
            Celda celda = t.getCelda(cordenada);
            celda.setPiece(this);
            this.celda = celda;
        }
    }

    public boolean isMove(){
        return move;
    }

    public void falseMoveto(Cordenada cordenada){
        Tablero t = getCelda().getTablero();
        if (t.getCelda(cordenada)!=null){
            getCelda().setPiece(null);
            Celda celda = t.getCelda(cordenada);
            celda.setPiece(this);
            this.celda = celda;
        }
    }

    public Type getType() {
        return type;
    }

    public Celda getCelda(){
        return celda;
    }



    public Color getColor(){
        return type.color;
    }

    public void putInYourPlace(){
        celda.setPiece(this);
    }


    public abstract Set<Cordenada> getNextMovements();


    public enum Type {
        BLACK_PAWN(R.mipmap.ic_n_pawn_foreground, Color.BLACK),
        BLACK_ROOK(R.mipmap.ic_n_rook_foreground, Color.BLACK),
        BLACK_BISHOP(R.mipmap.ic_n_bishop_foreground, Color.BLACK),
        BLACK_KING(R.mipmap.ic_n_king_foreground, Color.BLACK),
        BLACK_QUEEN(R.mipmap.ic_n_queen_foreground, Color.BLACK),
        BLACK_KNIGHT(R.mipmap.ic_n_knight_foreground, Color.BLACK),

        WHITE_PAWN(R.mipmap.ic_b_pawn_foreground, Color.WHITE),
        WHITE_ROOK(R.mipmap.ic_b_rook_foreground, Color.WHITE),
        WHITE_BISHOP(R.mipmap.ic_b_bishop_foreground, Color.WHITE),
        WHITE_KING(R.mipmap.ic_b_king_foreground, Color.WHITE),
        WHITE_QUEEN(R.mipmap.ic_b_queen_foreground, Color.WHITE),
        WHITE_KNIGHT(R.mipmap.ic_b_knight_foreground, Color.WHITE);

        Color color;
        int imagen;
        Type(int imagen, Color color){
            this.imagen = imagen;
            this.color= color;
        }



        public int getShape(){
            return imagen;
        }

        public Color getColor() {
            return color;
        }
    }



}


