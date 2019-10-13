package view;

import controller.MineSweeperController;

import javax.swing.*;
import java.awt.*;


public class BoxPanel extends JButton {
    private Color color;
    private int width;
    private int height;
    private String state;
    private int numberBoard;
    private String string;

    public BoxPanel(Color color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.state = "close";
        this.numberBoard = 0;
        this.string = "";
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int posX = 10;
        int posY = 16;
        if(!state.equals("close")) {
            color = state.equals("open") && getNumberBoard() == 9 ? Color.red : Color.white;
            if (state.equals("flag")) color = Color.orange;
            if (state.equals("quiz")) color = Color.cyan;
        }
        MineSweeperController.drawBox(g2d, color, Color.black, string, posX, posY, width, height);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getState() {
        return state;
    }

    public int getNumberBoard() {
        return numberBoard;
    }

    public void setNumberBoard(int numberBoard) {
        this.numberBoard = numberBoard;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setString(String string) {
        this.string = string;
    }
}
