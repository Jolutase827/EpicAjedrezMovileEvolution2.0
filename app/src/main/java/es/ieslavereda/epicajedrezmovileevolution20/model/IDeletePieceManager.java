package es.ieslavereda.epicajedrezmovileevolution20.model;


/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public interface IDeletePieceManager {

    void addPiece(Piece p);

    int count(Piece.Type pt);

    Piece removeLast();
}
