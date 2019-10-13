package view;

import controller.MineSweeperController;

import javax.swing.*;

public class BoardPanel extends JPanel {
    private BoxPanel[][] boxes;

    public BoardPanel(MineSweeper mineSweeper, int width, int height) {
        this.boxes = new BoxPanel[width][height];
        MineSweeperController.setBoard(mineSweeper, this);
    }

    public BoxPanel[][] getBoxes() {
        return boxes;
    }
}
