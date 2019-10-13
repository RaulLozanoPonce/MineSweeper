package model;

public class Game {
    private int maxMines;
    private Mines mines;
    private Time time;
    private boolean isEnd;
    private boolean isInitGame;

    public Game(int maxMines){
        this.maxMines = maxMines;
        this.mines = new Mines(maxMines);
        this.time = new Time();
        this.isEnd = false;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isInitGame() {
        return isInitGame;
    }

    public void setInitGame(boolean initGame) {
        isInitGame = initGame;
    }

    public int getMaxMines() {
        return maxMines;
    }

    public Time getTime() {
        return time;
    }
}
