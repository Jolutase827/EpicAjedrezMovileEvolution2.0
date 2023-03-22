package es.ieslavereda.epicajedrezmovileevolution20.model;

import com.diogonunes.jcolor.Attribute;



import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;


/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public class DeletePieceManagerList implements IDeletePieceManager, Serializable {
    private List<Piece> list;

    public DeletePieceManagerList(){
        list = new LinkedList<>();
    }

    @Override
    public void addPiece(Piece p) {
        list.add(p);
    }

    @Override
    public int count(Piece.Type pt) {
        int i=0;
        for (Piece p :list){
            if (p.getType()==pt){
                i++;
            }
        }
        return i;
    }

    @Override
    public Piece removeLast() {
        return list.remove(0);
    }


}
