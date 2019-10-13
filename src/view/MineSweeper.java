package view;

import controller.MineSweeperController;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class MineSweeper extends JFrame {
    private Game game;
    private ScorePanel scorePanel;
    private BoardPanel boardPanel;
    private int[] parameters;

    public MineSweeper() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.black);

        initMenu();

        parameters = new int[]{10,10,10};
        game = new Game(parameters[2]);
        scorePanel = new ScorePanel(this);
        scorePanel.setBounds(10, 10, 25*parameters[0], 50);
        scorePanel.setBackground(Color.black);
        scorePanel.setLayout(null);
        getContentPane().add(scorePanel);
        boardPanel = new BoardPanel(this, parameters[0], parameters[1]);
        boardPanel.setBounds(10, 70, 25*parameters[0], 25*parameters[1]);
        boardPanel.setLayout(null);
        getContentPane().add(boardPanel);
        getContentPane().setPreferredSize(new Dimension(25*parameters[0] + 20,25*parameters[1] + 80));
        pack();
    }

    public Game getGame() {
        return game;
    }

    public int[] getParameters() {
        return parameters;
    }

    public void newGame(int[] parameters) {
        MineSweeperController.stopTimer(game.getTime());
        getContentPane().remove(boardPanel);
        this.parameters = parameters;
        game = new Game(parameters[2]);
        scorePanel.setBounds(10, 10, 25*parameters[0], 50);
        boardPanel = new BoardPanel(this, parameters[0], parameters[1]);
        boardPanel.setBounds(10, 70, 25*parameters[0], 25*parameters[1]);
        boardPanel.setLayout(null);
        getContentPane().add(boardPanel);
        getContentPane().setPreferredSize(new Dimension(25*parameters[0] + 20,25*parameters[1] + 80));
        pack();
        repaint();
    }

    private void initMenu(){
        JMenuBar jMenuBar = new JMenuBar();


        JMenu gameMenu = new JMenu();
        gameMenu.setText("Game");

        JMenuItem newGame = new JMenuItem();
        newGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newGame.setText("New Game");
        newGame.addActionListener(evt -> newGame(parameters));
        gameMenu.add(newGame);



        JMenu optionsMenu = new JMenu();
        optionsMenu.setText("Options");



        JMenu helpMenu = new JMenu();
        helpMenu.setText("Help");



        jMenuBar.add(gameMenu);
        jMenuBar.add(optionsMenu);
        jMenuBar.add(helpMenu);
        setJMenuBar(jMenuBar);
    }

    public static void main(String[] args) { new MineSweeper().setVisible(true); }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }
}
