package View;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class View extends Scene {
    MainMenu mainMenu;
    PlayMenu playMenu;
    QuickMatchMenu quickMatchMenu;
    QuickMatchMenu localMultiplayerMenu;

    public View(){
        super(new Pane());
        getStylesheets().add("styling/menuButtons.css");

        initializeMenus();
        setRoot(mainMenu);
    }

    private void initializeMenus(){
        mainMenu = new MainMenu();
        playMenu = new PlayMenu();
        quickMatchMenu = new QuickMatchMenu(false);
        localMultiplayerMenu = new QuickMatchMenu(true);
    }

    public void mainMenu(){
        setRoot(mainMenu);
    }

    public void playMenu(){
        setRoot(playMenu);
    }

    public void quickMatchMenu(){
        setRoot(quickMatchMenu);
    }

    public void localMultiplayerMenu(){
        setRoot(localMultiplayerMenu);
    }

    public void viewGame(StackPane gamePane){setRoot(gamePane);}
}
