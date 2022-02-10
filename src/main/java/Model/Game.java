package Model;

import View.GameCanvas;
import javafx.application.Platform;

public class Game implements Runnable {
//https://www.youtube.com/watch?v=4iPEjFUZNsw
    public GameCanvas gameCanvas;
    private final GameField gameField;
    private volatile boolean running;
    private static final double FPS = 1.0/60.0; //60 times per second (60 fps)

    public Game(GameCanvas gameCanvas, GameField gameField){
        this.gameField = gameField;
        this.gameCanvas = gameCanvas;
    }

    public void start(){
        Thread thread = new Thread(this); // Demands that this is Runnable
        thread.start();
    }

    public void stop(){
        running = false;
        System.out.println("Game stopping...");
    }

    public void dispose(){
        Platform.runLater(()-> gameCanvas.gameOver());
    }

    public void run(){

        running = true;
        double lastTime = System.nanoTime() / 1000000000.0; // 1 = 1 second
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;

        while (running){
            boolean render = false;
            double firstTime = System.nanoTime() / 1000000000.0;
            double passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= FPS){
                unprocessedTime -= FPS;
                render = true;
                //update game
                gameField.update(FPS);//I sekunder
                if (stopCondition()) stop();

                if (frameTime >= 1){
                    frameTime = 0;
                    int fpsActual = frames;
                    frames = 0;
                    System.out.println("FPS: "+ fpsActual);
                }
            }

            if (render){
                //render game
                Platform.runLater(() -> gameCanvas.update(gameField.getAllObjects(), gameField.getTeams()));
                frames++;

            } else { //Nothing to do, so wait a bit before running through the running loop again.
                     //Decreases CPU load by around 85%
                try {
                    Thread.sleep(0,500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Game ended");
        dispose();
    }

    protected boolean stopCondition(){//Find ud af anden måde at styre forskellige gamemodes
        return gameField.getGameTime() <= 0.0;
    }
}
