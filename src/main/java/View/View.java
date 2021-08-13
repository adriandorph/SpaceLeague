package View;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class View extends Scene {
    MainMenu mainMenu;
    PlayMenu playMenu;
    QuickMatchMenu quickMatchMenu;

    public View(){
        super(new Pane());
        getStylesheets().add("styling/menuButtons.css");

        initializeMenus();
        setRoot(mainMenu);
    }

    private void initializeMenus(){
        mainMenu = new MainMenu();
        playMenu = new PlayMenu();
        quickMatchMenu = new QuickMatchMenu();
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
}
