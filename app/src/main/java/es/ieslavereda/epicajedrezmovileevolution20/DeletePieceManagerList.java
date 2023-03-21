package es.ieslavereda.epicajedrezmovileevolution20;

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

    @Override
    public String toString() {
        String output="      "+ Colorines.BLACK_BACKGROUND +"             DEAD PIECES            "+Colorines.RESET+"\n      ";
        Piece.Type[] pts = Piece.Type.values();
        for (Piece.Type pt : pts){
            if (pt.getColor()==Color.WHITE){
                output+= colorize(" "+pt.getShape()+" " ,pt.getColor().getAttribute(), Attribute.BACK_COLOR(100, 100, 100));
            }else {
                output += colorize(" "+pt.getShape()+" " , pt.getColor().getAttribute(),Attribute.BACK_COLOR(180, 180, 180));
            }
        }
        output+="\n      ";
        for (Piece.Type pt : pts){
            if (pt.getColor()==Color.BLACK){
                output+= colorize(" "+count(pt)+" " ,pt.getColor().getAttribute(), Attribute.BACK_COLOR(100, 100, 100));
            }else {
                output += colorize(" "+count(pt)+" ",pt.getColor().getAttribute() , Attribute.BACK_COLOR(180, 180, 180));
            }
        }
        output+="\n\n      "+Colorines.BLACK_BACKGROUND+"             LIVE PIECES            "+Colorines.RESET+"\n      ";
        for (Piece.Type pt : pts){
            if (pt.getColor()==Color.WHITE){
                output+= colorize(" "+pt.getShape()+" " ,pt.getColor().getAttribute(), Attribute.BACK_COLOR(100, 100, 100));
            }else {
                output += colorize(" "+pt.getShape()+" " , pt.getColor().getAttribute(),Attribute.BACK_COLOR(180, 180, 180));
            }
        }
        output+="\n      ";
        for (Piece.Type pt : pts){
            if (pt.getColor()==Color.BLACK){
                output+= colorize(" "+(((pt.equals(Piece.Type.BLACK_KING)||pt.equals(Piece.Type.BLACK_QUEEN))?1:(pt.equals(Piece.Type.BLACK_PAWN))?8:2)-count(pt))+" " ,pt.getColor().getAttribute(), Attribute.BACK_COLOR(100, 100, 100));
            }else {
                output += colorize(" "+(((pt.equals(Piece.Type.WHITE_KING)||pt.equals(Piece.Type.WHITE_QUEEN))?1:(pt.equals(Piece.Type.WHITE_PAWN))?8:2)-count(pt))+" ",pt.getColor().getAttribute() , Attribute.BACK_COLOR(180, 180, 180));
            }
        }

        return output;


    }
}
