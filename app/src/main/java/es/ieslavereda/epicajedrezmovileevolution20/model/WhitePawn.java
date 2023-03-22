package es.ieslavereda.epicajedrezmovileevolution20.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhitePawn extends Pawn implements Serializable {


    /**
     * Constructor WhitePawn
     * @param celda Celda en la que va a ir la pieza
     */
    public WhitePawn(Celda celda){
        super(Type.WHITE_PAWN,celda);
    }
    @Override
    public void trasnform() {
        new WhiteQueen(getCelda());
    }

    @Override
    public void getNextMovementsPawn(){
        Cordenada position = getCelda().getCordenada();
        Cordenada cordenada;

        cordenada = position.up();
        check(cordenada);
        if (position.getFila()==7){
            cordenada = cordenada.up();
            check(cordenada);
        }

        cordenada = position.upLeft();
        checkEnemy(cordenada);

        cordenada = position.upRight();
        checkEnemy(cordenada);

    }



}
