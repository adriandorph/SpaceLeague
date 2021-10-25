package View;

import Model.Player;
import Model.Ships.ShowShip;
import Model.Ships.Stat;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerShipStatBox extends VBox {
    private final Label playerLabel;
    private final Label playerNameLabel;
    private Canvas ship;
    private final Label variantLabel;
    private final Label shipVariantLabel;
    private final Label classLabel;
    private final Label shipClassLabel;
    private final Label accelerationLabel;
    private Canvas acceleration;
    private final Label turningLabel;
    private Canvas turning;
    private final Label shootingRateLabel;
    private Canvas shootingRate;
    private final Label shootingPowerLabel;
    private Canvas shootingPower;

    private static final int statWidth = 60;
    private static final int statHeight = 5;
    private static final int shipSizefactor = 1;
    private static final int shipAngle = 90;

    public PlayerShipStatBox(Player player){
        playerLabel = new Label("Player");
        playerNameLabel = new Label();
        variantLabel = new Label("Ship variant");
        shipVariantLabel = new Label();
        classLabel = new Label("Ship class");
        shipClassLabel = new Label();
        accelerationLabel = new Label("Acceleration");
        turningLabel = new Label("Turning");
        shootingRateLabel = new Label("Shooting rate");
        shootingPowerLabel = new Label("Shooting power");

        updatePlayer(player);

        addAllToVBox();
    }

    public void updatePlayer(Player player){
        ShowShip showShip = player.showShip;
        playerNameLabel.setText(player.name);
        ship = showShip.drawShip(shipSizefactor, shipAngle);
        shipVariantLabel.setText(showShip.name);
        shipClassLabel.setText(showShip.className);
        acceleration = showShip.drawStat(statWidth, statHeight, Stat.Acceleration);
        turning = showShip.drawStat(statWidth, statHeight, Stat.Turning);
        shootingRate = showShip.drawStat(statWidth, statHeight, Stat.ShootingRate);
        shootingPower = showShip.drawStat(statWidth, statHeight, Stat.ShootingPower);
    }

    private void addAllToVBox(){
        this.getChildren().add(playerLabel);
        this.getChildren().add(playerNameLabel);
        this.getChildren().add(ship);
        this.getChildren().add(variantLabel);
        this.getChildren().add(shipVariantLabel);
        this.getChildren().add(classLabel);
        this.getChildren().add(shipClassLabel);
        this.getChildren().add(accelerationLabel);
        this.getChildren().add(acceleration);
        this.getChildren().add(turningLabel);
        this.getChildren().add(turning);
        this.getChildren().add(shootingRateLabel);
        this.getChildren().add(shootingRate);
        this.getChildren().add(shootingPowerLabel);
        this.getChildren().add(shootingPower);
    }
}
