package es.ieslavereda.epicajedrezmovileevolution20.model;

import java.io.Serializable;

public class ColoresTablero implements Serializable {
    private int coloresWhite;
    private int coloresBlack;

    public ColoresTablero(int coloresBlack, int coloresWhite){
        this.coloresBlack = coloresBlack;
        this.coloresWhite = coloresWhite;
    }

    public int getColoresWhite() {
        return coloresWhite;
    }

    public void setColoresWhite(int coloresWhite) {
        this.coloresWhite = coloresWhite;
    }

    public int getColoresBlack() {
        return coloresBlack;
    }

    public void setColoresBlack(int coloresBlack) {
        this.coloresBlack = coloresBlack;
    }
}
