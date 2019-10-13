package controller;

import model.Game;
import model.Time;
import model.display.Display;
import view.BoardPanel;
import view.BoxPanel;
import view.MineSweeper;
import view.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MineSweeperController {

    private static int[][] positions;

    static{
        positions = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    }

    public static void setBoard(MineSweeper mineSweeper, BoardPanel boardPanel) {
        BoxPanel[][] board = boardPanel.getBoxes();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                BoxPanel boxPanel = new BoxPanel(Color.black, 25, 25);
                board[i][j] = boxPanel;
                boxPanel.setBounds(i*25,j*25,25,25);
                boardPanel.add(boxPanel);
                boxEvent(mineSweeper, boardPanel, boxPanel);
            }
        }
    }

    public static void drawBox(Graphics2D g2d, Color boxColor, Color textColor, String text, int textX, int textY, int width, int height){
        g2d.setColor(boxColor);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(textColor);
        g2d.drawString(text, textX, textY);
    }

    public static void setDisplay(Graphics2D g2d, Display d, int number) {
        g2d.setColor(d.getColor());
        for (int i = 0; i < d.getSegments().length; i++) {
            if(d.getPositions()[number][i]){
                int[] xPoints = new int[d.getSegments()[i].getPoints().size()];
                int[] yPoints = new int[d.getSegments()[i].getPoints().size()];
                for (int j = 0; j < d.getSegments()[i].getPoints().size(); j++) {
                    xPoints[j] = (int) d.getSegments()[i].getPoints().get(j).getX();
                    yPoints[j] = (int) d.getSegments()[i].getPoints().get(j).getY();
                }
                g2d.fillPolygon(xPoints, yPoints, d.getSegments()[i].getPoints().size());
            }
        }
    }

    private static void boxEvent(MineSweeper mineSweeper, BoardPanel boardPanel, BoxPanel boxPanel){
        Game game = mineSweeper.getGame();
        boxPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) { if(!game.isEnd()) boxPanel.setColor(Color.yellow); }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) { if(!game.isEnd()) boxPanel.setColor(Color.black); }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if(!game.isEnd()) {
                    if (SwingUtilities.isRightMouseButton(evt)) {
                        MineSweeperController.rightButton(boxPanel);
                    } else if (SwingUtilities.isLeftMouseButton(evt)) {
                        MineSweeperController.leftButton(mineSweeper, boxPanel);
                    }
                    boardPanel.repaint();
                    if (MineSweeperController.isWin(game, boardPanel.getBoxes())) win(game);
                }
            }
        });
    }

    private static void leftButton(MineSweeper mineSweeper, BoxPanel boxPanel) {
        Game game = mineSweeper.getGame();
        ScorePanel scorePanel = mineSweeper.getScorePanel();
        BoxPanel[][] boxes = mineSweeper.getBoardPanel().getBoxes();
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                if(boxes[i][j].equals(boxPanel)){
                    if(boxPanel.getState().equals("open")){
                        openAdjacentBox(game, boxes, i, j);
                    }else{
                        if (!game.isInitGame()){
                            initGameAction(game, boxes, i, j);
                            initTimer(game.getTime(), scorePanel);
                        }
                        openBox(game, boxes, i, j);
                    }
                }
            }
        }
    }

    private static void rightButton(BoxPanel boxAux) {
        switch (boxAux.getState()) {
            case "close":
                boxAux.setState("flag");
                boxAux.setString("F");
                break;
            case "flag":
                boxAux.setState("quiz");
                boxAux.setString("?");
                break;
            case "quiz":
                boxAux.setState("close");
                boxAux.setString("");
                break;
        }
    }

    private static void openAdjacentBox(Game game, BoxPanel[][] board, int i, int j) {
        int numberFlags = 0;
        for (int[] position : positions) numberFlags += countFlag(board, i + position[0], j + position[1]);
        if(numberFlags == board[i][j].getNumberBoard()){
            for (int[] position : positions)
                if (!isFlag(board, i + position[0], j + position[1])) openBox(game, board, i + position[0], j + position[1]);
        }
    }

    private static void initGameAction(Game game, BoxPanel[][] boxes, int i, int j) {
        game.setInitGame(true);
        int[][] numberBoard = initNumberBoard(i, j, game.getMaxMines(), boxes.length, boxes[0].length);
        for (int k = 0; k < numberBoard.length; k++) {
            for (int l = 0; l < numberBoard[k].length; l++) {
                boxes[k][l].setNumberBoard(numberBoard[k][l]);
            }
        }
    }

    private static void openBox(Game game, BoxPanel[][] board, int i, int j) {
        if(board[i][j].getNumberBoard() == 9){
            lose(game, board);
        }else{
            couldOpen(board, i, j);
        }
    }

    private static int countFlag(BoxPanel[][] board, int i, int j) {
        if(i >= 0 && i < board.length && j >= 0 && j < board[0].length){
            if(board[i][j].getState().equals("flag")) return 1;
        }
        return 0;
    }

    private static boolean isFlag(BoxPanel[][] board, int i, int j) {
        if(i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
            return board[i][j].getState().equals("flag");
        }
        return true;
    }

    private static int[][] initNumberBoard(int noX, int noY, int mines, int width, int height) {
        int[][] boardReturn = new int[width][height];
        int minesAux = mines;

        while(minesAux > 0){
            int x = (int) (width*Math.random());
            int y = (int) (height*Math.random());
            if(boardReturn[x][y] == 0 && x != noX && y != noY){
                boardReturn[x][y] = 9;
                minesAux--;
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(boardReturn[i][j] != 9){
                    boardReturn[i][j] = addNumberMine(boardReturn, i, j);
                }
            }
        }

        return boardReturn;
    }

    private static void lose(Game game, BoxPanel[][] boxes){
        stopTimer(game.getTime());
        game.setEnd(true);
        for (BoxPanel[] boxArray : boxes) {
            for (BoxPanel box : boxArray) {
                if(box.getNumberBoard() == 9){
                    if(!box.getState().equals("flag")){
                        box.setState("open");
                        box.setString("O");
                    }
                }else if(box.getState().equals("flag")){
                    box.setString("X");
                }
            }
        }
        JOptionPane.showMessageDialog(null, "HAS PERDIDO", "HAS PERDIDO", JOptionPane.ERROR_MESSAGE);
    }

    private static void win(Game game){
        stopTimer(game.getTime());
        game.setEnd(true);
        JOptionPane.showMessageDialog(null, "HAS GANADO!", "HAS GANADO", JOptionPane.WARNING_MESSAGE);
    }

    private static void couldOpen(BoxPanel[][] board, int x, int y) {
        if(x >= 0 && x < board.length && y >= 0 && y < board[0].length){
            if(!board[x][y].getState().equals("open") && !board[x][y].getState().equals("flag")){
                board[x][y].setState("open");
                if(board[x][y].getNumberBoard() == 0){
                    for (int[] position : positions) couldOpen(board, x + position[0], y + position[1]);
                    board[x][y].setString("");
                }else{
                    board[x][y].setString(Integer.toString(board[x][y].getNumberBoard()));
                }
            }
        }
    }

    private static int addNumberMine(int[][] board, int posX, int posY) {
        int result = 0;
        for (int[] position : positions)
            result += addSingleNumber(board, posX + position[0], posY + position[1]);
        return result;
    }

    private static int addSingleNumber(int[][] board, int x, int y){
        if(x >= 0 && x < board.length && y >= 0 && y < board[0].length){
            if(board[x][y] == 9) return 1;
        }
        return 0;
    }

    private static boolean isWin(Game game, BoxPanel[][] boxes) {
        int numberFlags = 0;
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                if (boxes[i][j].getState().equals("flag") ||
                        boxes[i][j].getState().equals("close") ||
                        boxes[i][j].getState().equals("quiz")) numberFlags++;
            }
        }
        return numberFlags == game.getMaxMines();
    }

    private static void initTimer(Time time, ScorePanel scorePanel) {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                time.setTime(time.getTime() + 1);
                scorePanel.repaint();
            }
        };
        time.getTimer().schedule(tt, 0, 1000);
    }

    public static void stopTimer(Time time) {
        time.getTimer().cancel();
        time.setTimer(new Timer());
    }
}