package es.ieslavereda.epicajedrezmovileevolution20.model;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.io.Serializable;
import java.security.Identity;
import java.util.*;

import es.ieslavereda.epicajedrezmovileevolution20.R;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public class Tablero extends TableLayout implements Serializable {


    private ColoresTablero coloresTablero;

    private int idDeadWhite;
    private int idDeadBlack;
    private Map<Cordenada, Celda> celdas;

    private IDeletePieceManager deletePieceManager;


    public Tablero(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        coloresTablero = new ColoresTablero(R.color.cellBlack,R.color.cellWhite);
        idDeadBlack = 1;
        idDeadWhite = 20;
        this.deletePieceManager = new DeletePieceManagerList();
        celdas = new LinkedHashMap<>();
        Celda celda;
        TableRow tableRowAux;
        addDeadCamp(1);
        addTextViews();

        for (int i=0; i<8;i++){
            tableRowAux = new TableRow(getContext());
            addView(tableRowAux);

            tableRowAux.addView(getTextView(""+(i+1)));
            for (int j = 0; j<8;j++){
                celda=new es.ieslavereda.epicajedrezmovileevolution20.model.Celda(getContext(), this,new es.ieslavereda.epicajedrezmovileevolution20.model.Cordenada((char) ('A'+j),1+i),coloresTablero);
                tableRowAux.addView(celda);
                celdas.put(celda.getCordenada(),celda);
            }

            tableRowAux.addView(getTextView(""+(i+1)));
        }
        addTextViews();
        addDeadCamp(20);
        placePieces();
    }

    public ColoresTablero getColoresTablero() {
        return coloresTablero;
    }

    public void setColoresTablero(ColoresTablero coloresTablero) {
        this.coloresTablero = coloresTablero;
        for (Celda c: celdas.values()){
            c.setColoresTablero(coloresTablero);
        }
    }

    public void reiniciarTablero(){
        this.deletePieceManager = new DeletePieceManagerList();
        for (Celda c : celdas.values()){
            c.setPiece(null);
            c.resetColor();
        }
        placePieces();
    }

    @SuppressLint("ResourceAsColor")
    public void addDeadCamp(int campo){
        TableRow tableRowAux = new TableRow(getContext());
        addView(tableRowAux);
        int id = campo+0;
        TextView tx = getTextView("");
        tx.setBackgroundColor(0);
        tableRowAux.addView(tx);
        for (int x = 0;x<8;x++){
            tableRowAux.addView(addCamp(id));
            id++;
        }
         tx = getTextView("");
        tx.setBackgroundColor(0);
        tableRowAux.addView(tx);
        tableRowAux = new TableRow(getContext());
        addView(tableRowAux);
        tx = getTextView("");
        tx.setBackgroundColor(0);
        tableRowAux.addView(tx);
        for (int x = 0;x<8;x++){
            tableRowAux.addView(addCamp(id));
            id++;
        }
        tx = getTextView("");
        tx.setBackgroundColor(0);
        tableRowAux.addView(tx);
    }


    public void resetDeadCamp(){
        for (int i=1;i<=16;i++){
            ((ImageView)findViewById(i)).setImageResource(0);
        }
        for (int j=20;j<36;j++){
            ((ImageView)findViewById(j)).setImageResource(0);
        }

    }


    private void addTextViews() {
        TableRow tableRowAux = new TableRow(getContext());
        addView(tableRowAux);
        tableRowAux.addView(getTextView(" "));
        for (int x = 0;x<8;x++){
            tableRowAux.addView(getTextView(""+((char)(x+'A'))));
        }
        tableRowAux.addView(getTextView(" "));
    }

    public void placePieces(){
        new BlackKnight(getCelda(new Cordenada('G',1)));
        new BlackKnight(getCelda(new Cordenada('B',1)));
        new WhiteKnight(getCelda(new Cordenada('B',8)));
        new WhiteKnight(getCelda(new Cordenada('G',8)));
        new WhiteRook(getCelda(new Cordenada('H',8)));
        new WhiteRook(getCelda(new Cordenada('A',8)));
        new BlackRook(getCelda(new Cordenada('A',1)));
        new BlackRook(getCelda(new Cordenada('H',1)));
        new WhiteBishop(getCelda(new Cordenada('C',8)));
        new WhiteBishop(getCelda(new Cordenada('F',8)));
        new BlackBishop(getCelda(new Cordenada('C',1)));
        new BlackBishop(getCelda(new Cordenada('F',1)));
        new WhiteQueen(getCelda(new Cordenada('D',8)));
        new BlackQueen(getCelda(new Cordenada('D',1)));
        new WhiteKing(getCelda(new Cordenada('E',8)));
        new BlackKing(getCelda(new Cordenada('E',1)));
        for (int i = 0; i<8; i++) {
            new BlackPawn(getCelda(new Cordenada((char)('A'+i), 2)));
            new WhitePawn(getCelda(new Cordenada((char)('A'+i), 7)));
        }
    }

    public Celda getCelda(Cordenada cordenada){
        return celdas.get(cordenada);
    }

    /**
     * Comprueba si hay hacke mate hacia un color
     * @param color Color del que queremos comprobar si hay jaque mate
     * @return Si hay jaque mate o sin no en formato boolean
     */
    public boolean oneColorJakeMate(Color color){
        return movementsValid(color).size()==0;
    }

    /**
     * Comprueba que cantidad de movimientos son validos por parte de un color
     * @param color Color del jugador que queremos saber sus movimientos
     * @return Conjunto de movimientos
     */
    public Set<Cordenada> movementsValid(Color color){
        Set<Cordenada> cordenadas= new HashSet<>();
        for (es.ieslavereda.epicajedrezmovileevolution20.model.Cordenada c : celdas.keySet()){
            if (!getCelda(c).isEmpty())
                if (getCelda(c).getPiece().getType().getColor().equals(color))
                    cordenadas.addAll(movementsSalveKing(c,color));
        }
        return cordenadas;
    }


    /**
     * Movimientos que salvan al rey de una pieza
     * @param c Cordenada de la pieza que queremos comprobar sus movimientos
     * @param color Color de la pieza que queremos saber su movimiento
     * @return Conjunto de movimientos que salvan al rey de la pieza
     */
    public Set<Cordenada> movementsSalveKing(Cordenada c, Color color){
        Set<Cordenada> cordenadasExit = new HashSet<>();
        Piece p;
        for (Cordenada aux : getCelda(c).getPiece().getNextMovements()){
            p=null;
            if (!getCelda(aux).isEmpty())
                p = getCelda(aux).getPiece();
            getCelda(c).getPiece().falseMoveto(aux);
            if (!oneColorJake(color))
                cordenadasExit.add(aux);
            resetMovement(c,aux,p);
        }
        return cordenadasExit;
    }

    public void setCellsOnClickListener(View.OnClickListener onClickListener){
        for (Cordenada cordenada : celdas.keySet()){
            celdas.get(cordenada).setOnClickListener(onClickListener);
        }
    }

    /**
     * Las piezas que salvan al rey
     * @param c Cordenada de la pieza que puede salvar al rey
     * @param color Color de la pieza
     * @return Las piezas que salvan al rey
     */
    public Set<Cordenada> piecesSalveKing(Cordenada c, Color color){
        Set<Cordenada> cordenadasExit = new HashSet<>();
        Piece p;
        for (es.ieslavereda.epicajedrezmovileevolution20.model.Cordenada aux : getCelda(c).getPiece().getNextMovements()){
            p=null;
            if (!getCelda(aux).isEmpty())
                p = getCelda(aux).getPiece();
            getCelda(c).getPiece().falseMoveto(aux);
            if (!oneColorJake(color))
                cordenadasExit.add(c);
            resetMovement(c,aux,p);
        }
        return cordenadasExit;
    }

    /**
     * Reseta los movimientos echos
     * @param retorno Donde quiere volver la pieza movida
     * @param movida Donde esta la pieza movida
     * @param p Pieza que hay que revivir si la han matado
     */
    public void resetMovement(Cordenada retorno, Cordenada movida, Piece p){
        getCelda(movida).getPiece().falseMoveto(retorno);
        if (p!=null)
            p.putInYourPlace();
    }


    /**
     * Comprueba si hay jake hacia el rey de un color
     * @param color Color del rey que queremos ver si hay jaque
     * @return Si esta en jaque o no en boolean
     */
    public  boolean oneColorJake(Color color){
        return getAllOneColorMovements(color.next()).contains(getKingPosition(color));
    }

    /**
     * Coge todos los movimientos de un color
     * @param color Color del las piezas que queremos saber sus movimientos
     * @return Conjunto de posibles movimientos
     */
    public Set<Cordenada> getAllOneColorMovements(Color color){
        Set<Cordenada> exit = new HashSet<>();
        for (Cordenada c: celdas.keySet()){
            if (!getCelda(c).isEmpty())
                if (getCelda(c).getPiece().getType().getColor().equals(color))
                    exit.addAll(getCelda(c).getPiece().getNextMovements());

        }
        return exit;
    }

    /**
     * Busca la posición del rey
     * @param color Color del rey que queremos saber la posición
     * @return Cordenada donde se encuentra el rey
     */
    public Cordenada getKingPosition(Color color){
        for (Cordenada c : celdas.keySet()){
            if (!getCelda(c).isEmpty())
                if ((getCelda(c).getPiece().getType().getShape() == R.mipmap.ic_b_king_foreground||getCelda(c).getPiece().getType().getShape() == R.mipmap.ic_n_king_foreground) && getCelda(c).getPiece().getType().getColor().equals(color))
                    return c;
        }
        return null;
    }



    public void hightlight(Set<Cordenada> setcordenada,Celda celda){
        List<Cordenada> listCordenada = new LinkedList<>(setcordenada);
        Set<Cordenada> cordenadasValidas = new HashSet<>(movementsSalveKing(celda.getCordenada(),celda.getPiece().getColor()));
        for (Cordenada c : listCordenada){
            if (cordenadasValidas.contains(c))
                getCelda(c).highLight();
        }
    }

    private TextView getTextView(String texto){
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;
        TextView exit = new TextView(getContext());
        exit.setText(texto);
        exit.setTextColor(getResources().getColor(R.color.white));
        exit.setBackgroundColor(getResources().getColor(R.color.black));
        exit.setWidth(widh/10);
        exit.setTextSize(22);
        exit.setHeight(widh/10);
        exit.setGravity(Gravity.CENTER);
        return exit;
    }
    @SuppressLint("ResourceType")
    public ImageView addCamp(int id){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widh = displayMetrics.widthPixels;
        ImageView exit = new ImageView(getContext());
        exit.setMaxHeight(widh/10);
        exit.setMinimumHeight(widh/10);
        exit.setMaxWidth(widh/10);
        exit.setMinimumWidth(widh/10);
        exit.setPadding(0,0,0,0);
        exit.setScaleType(ImageView.ScaleType.FIT_CENTER);
        exit.setAdjustViewBounds(true);
        exit.setId(id);
        return exit;
    }

    public void resetColors(){
        for (Celda c : celdas.values())
            c.resetColor();
    }

    public boolean kingIsDead(){
        return deletePieceManager.count(Piece.Type.WHITE_KING)>0||deletePieceManager.count(Piece.Type.BLACK_KING)>0;
    }
    @SuppressLint("ResourceType")
    public void movePiece(Cordenada cPiece, Cordenada cMovement) {
        Celda celdaPiece = getCelda(cPiece);
        Celda celdaMovenet = getCelda(cMovement);
        if (celdaPiece != null && celdaMovenet != null) {
            if (!celdaPiece.isEmpty()) {
                if (!celdaMovenet.isEmpty()) {
                    deletePieceManager.addPiece(celdaMovenet.getPiece());
                    if (celdaPiece.getPiece().getColor()==Color.WHITE) {
                        ((ImageView) findViewById(idDeadWhite)).setImageResource(celdaMovenet.getPiece().getType().imagen);
                        idDeadWhite++;
                    }else {
                        ((ImageView) findViewById(idDeadBlack)).setImageResource(celdaMovenet.getPiece().getType().imagen);
                        idDeadBlack++;
                    }
                }
                else if (celdaPiece.getPiece() instanceof King) {
                    if (cMovement.equals(cPiece.left().left())) {
                        getCelda(cMovement.left().left()).getPiece().moveTo(cPiece.left());
                    } else if (cMovement.equals(cPiece.right().right())) {
                        getCelda(cMovement.right()).getPiece().moveTo(cPiece.right());
                    }
                }
                celdaPiece.getPiece().moveTo(cMovement);
            }
        }
    }


}

