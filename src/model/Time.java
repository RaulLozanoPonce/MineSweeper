package model;

import model.display.Display;

import java.awt.*;
import java.util.Timer;

public class Time {
    private Display[] timerDisplay;
    private int time;
    private Timer timer;

    public Time() {
        this.time = 0;
        this.timerDisplay = new Display[3];
        this.timerDisplay[0] = new Display(170,10,20,30, Color.red);
        this.timerDisplay[1] = new Display(200,10,20,30, Color.red);
        this.timerDisplay[2] = new Display(230,10,20,30, Color.red);
        this.timer = new Timer();
    }

    public Display[] getTimerDisplay() {
        return timerDisplay;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
