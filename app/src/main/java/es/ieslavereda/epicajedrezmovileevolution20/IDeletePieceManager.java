package es.ieslavereda.epicajedrezmovileevolution20;


import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public interface IDeletePieceManager {

    void addPiece(Piece p);

    int count(Piece.Type pt);

    Piece removeLast();
}
