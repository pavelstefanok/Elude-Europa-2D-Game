// Game.java
package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Input.Controls;
import PaooGame.States.PlayState;
import PaooGame.States.MenuState;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable
{
    private GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;
    private State currentState;
    private State menuState;
    private State playState;
    private Graphics g;
    private Tile tile;
    private int nivelCurent = 0;

    public State getPlayState() { return playState; }
    public State getMenuState() { return menuState; }
    public void setCurrentState(State state) { currentState = state; }

    public Game() {
        wnd = new GameWindow();
        runState = false;
    }

    private void InitGame() {
        Assets.Init();
        wnd.BuildGameWindow();
        Controls controls = new Controls();
        wnd.GetCanvas().addKeyListener(controls);
        wnd.GetCanvas().addMouseListener(controls);
        wnd.GetCanvas().addMouseMotionListener(controls);
        menuState = new MenuState(this);
        playState = new PlayState(this,1);
        currentState = menuState;
    }

    public void resetNivel() {

        playState = new PlayState(this,1);
        setCurrentState(playState);
    }

    public void run() {
        InitGame();
        long oldTime = System.nanoTime();
        long curentTime;

        final int framesPerSecond = 60;
        final double timeFrame = 1000000000.0 / framesPerSecond;

        while (runState) {
            curentTime = System.nanoTime();
            double elapsedTime = (curentTime - oldTime) / 1000000000.0;
            if (elapsedTime >= timeFrame / 1000000000.0) {
                Update();
                Draw();
                oldTime = curentTime;

                long sleepTime = (long) ((timeFrame - (System.nanoTime() - curentTime)) / 1000000);
                if (sleepTime > 0) {
                    try { Thread.sleep(sleepTime); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        }
    }

    public synchronized void StartGame() {
        if(!runState) {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public synchronized void StopGame() {
        if(runState) {
            runState = false;
            try { gameThread.join(); }
            catch(InterruptedException ex) { ex.printStackTrace(); }
        }
    }

    private void Update() {
        if (currentState != null)
            currentState.Update();
    }

    private void Draw() {
        bs = wnd.GetCanvas().getBufferStrategy();
        if(bs == null) {
            try {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) { e.printStackTrace(); }
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        if (currentState != null)
            currentState.Draw(g);
        bs.show();
        g.dispose();
    }
}
