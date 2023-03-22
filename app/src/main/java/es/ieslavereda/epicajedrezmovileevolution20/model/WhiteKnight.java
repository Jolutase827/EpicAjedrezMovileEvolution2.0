package es.ieslavereda.epicajedrezmovileevolution20.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhiteKnight extends Knight implements Serializable {
    /**
     * Constructor WhiteKnight
     * @param celda Celda en la que va a ir la pieza
     */
    public WhiteKnight(Celda celda){
        super(Type.WHITE_KNIGHT,celda);
    }
}
