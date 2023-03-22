package es.ieslavereda.epicajedrezmovileevolution20.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhiteRook extends Rook implements Serializable {
    /**
     * Constructor WhiteRook
     * @param celda Celda en la que va a ir la pieza
     */
    public WhiteRook(Celda celda){
        super(Piece.Type.WHITE_ROOK,celda);
    }
}
