package model;

import model.display.Display;

public class Mines {
    private Display[] minesDisplay;
    private int nMines;

    public Mines(int nMines) {
        this.nMines = nMines;
        this.minesDisplay = new Display[3];
        this.minesDisplay[0] = new Display(0,0,0,0,null);
        this.minesDisplay[1] = new Display(0,0,0,0,null);
        this.minesDisplay[2] = new Display(0,0,0,0,null);
    }
}
