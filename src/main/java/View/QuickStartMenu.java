package View;

import Controller.Controller;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuickStartMenu extends VBox {
    private boolean freeforAll;
    private BorderPane individualAndTeamTogglePane;
    private ToggleGroup individualAndTeamToggleGroup;
    private ToggleButton individualToggle;
    private ToggleButton teamToggle;

    public QuickStartMenu(Controller controller, boolean freeforAll){
        individualAndTeamTogglePane = new BorderPane();
        individualAndTeamToggleGroup = new ToggleGroup();

        individualToggle = new ToggleButton("Individual");
        teamToggle = new ToggleButton("Teams");

        individualToggle.setToggleGroup(individualAndTeamToggleGroup);
        individualToggle.setSelected(true);

        teamToggle.setToggleGroup(individualAndTeamToggleGroup);

        double fontSize = 17*(Controller.windowHeight/720);
        String fontSizeStyle = "-fx-font-size: "+fontSize+"px;";
        Font font = new Font(fontSize);
        individualToggle.setFont(font);
        individualToggle.setStyle(fontSizeStyle);
        teamToggle.setFont(font);
        teamToggle.setStyle(fontSizeStyle);


        individualAndTeamTogglePane.setLeft(individualToggle);
        individualAndTeamTogglePane.setRight(teamToggle);

    }

}
