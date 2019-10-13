package view;

import controller.MineSweeperController;
import model.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ScorePanel extends JPanel {

    private JButton restartButton;
    private JTextField rowTextField;
    private JTextField columnTextField;
    private JTextField minesTextField;
    private String buttonText;
    private MineSweeper ms;

    public ScorePanel(MineSweeper ms){
        this.ms = ms;
        buttonText = ":)";
        restartButton = new JButton();
        restartButton.addActionListener(this::restartButtonActionPerformed);
        add(restartButton);

        rowTextField = new JTextField();
        rowTextField.setBounds(0,0,100,15);
        rowTextField.setText(Integer.toString(ms.getParameters()[0]));
        add(rowTextField);
        columnTextField = new JTextField();
        columnTextField.setBounds(0,15,100,15);
        columnTextField.setText(Integer.toString(ms.getParameters()[0]));
        add(columnTextField);
        minesTextField = new JTextField();
        minesTextField.setBounds(0,30,100,15);
        minesTextField.setText(Integer.toString(ms.getParameters()[0]));
        add(minesTextField);
    }

    private void restartButtonActionPerformed(ActionEvent actionEvent) {
        ms.newGame(getParameters());
    }

    private int[] getParameters() {
        int[] parameters = new int[3];
        parameters[0] = isNumber(rowTextField.getText()) ? Integer.parseInt(rowTextField.getText()) : 10;
        parameters[1] = isNumber(columnTextField.getText()) ? Integer.parseInt(columnTextField.getText()) : 10;
        parameters[2] = isNumber(minesTextField.getText()) ? Integer.parseInt(minesTextField.getText()) : 10;
        return parameters;
    }

    private boolean isNumber(String number){
        boolean isNumber = true;
        try { Integer.parseInt(number); }
        catch (NumberFormatException e) { isNumber = false; }
        return isNumber && !"".equals(number);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        restartButton.setBounds(getWidth()/2 - 25,0,50,50);
        restartButton.setText(buttonText);
        Time time = ms.getGame().getTime();
        MineSweeperController.setDisplay(g2d, time.getTimerDisplay()[0], time.getTime() / 100);
        MineSweeperController.setDisplay(g2d, time.getTimerDisplay()[1], (time.getTime() / 10) % 10);
        MineSweeperController.setDisplay(g2d, time.getTimerDisplay()[2], time.getTime() % 10);
    }
}
