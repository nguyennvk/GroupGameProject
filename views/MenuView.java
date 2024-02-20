package views;

import AdventureModel.Setting;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * MenuView organize the menu of the game
 */
public class MenuView{
    Scene scene; //scene of the menu
    String dir;//directory
    Setting setting; //setting that hold the value needs
    StackPane stackPane;//root of the menu
    Stage stage;//stage of menu
    AdventureGameView adventureGameView; //view application
    ImageView backImage;//background image

    /**
     * MenuView Constructor
     * @param scene scene of menu
     * @param dir directory
     * @param setting setting for menu
     * @param stage stage of menu
     * @param adventureGameView view application
     */
    public MenuView(Scene scene, String dir, Setting setting, Stage stage, AdventureGameView adventureGameView) {
        //set up attribute and display images
        this.scene = scene;
        this.dir = dir;
        this.setting = setting;
        this.stage = stage;
        this.adventureGameView = adventureGameView;

        this.backImage = new ImageView(new Image(this.dir+"/background/menuBackground.png"));
        this.backImage.setFitWidth(this.setting.width);
        this.backImage.setFitHeight(this.setting.height);

        this.stackPane = new StackPane(backImage);
        this.stackPane.setPrefSize(this.setting.width, this.setting.height);

        this.scene = new Scene(this.stackPane);
    }

    /**
     * method that show the menu scene
     */
    public void show()
    {
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    /**
     * getter method that get the root of menu
     * @return
     */
    public StackPane getStackPane()
    {
        return this.stackPane;
    }

    /**
     * getter metthod that get view
     * @return
     */
    public AdventureGameView getAdventureGameView()
    {
        return this.adventureGameView;
    }

    /**
     * getter method that get the stage
     * @return
     */
    public Stage getStage()
    {
        return this.stage;
    }


}
