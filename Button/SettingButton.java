package Button;

import AdventureModel.Setting;
import Controller.SettingController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import views.MenuView;

/**
 * SettingButton extends the button and display the setting scene when clicked
 */
public class SettingButton extends GameButton{
    ImageView imageView;//image of the button
    ImageView imageViewHover;//image of button while hover
    ImageView settingBackground;//background image of setting scene
    AnchorPane settingRoot;//root of the setting pane
    Stage settingStage;//new popup stage
    Scene settingScene;//scene of the setting
    Stage stage;//old stage
    Pane pane;//current pane of the button
    BackToMenuButton backButton;//button to go back to menu
    Slider volumeSlider;//slider for volume changing
    Slider brightnessSlider;//slier for brightness changing
    SettingController settingController;//controller for setting


    /**
     * SettingButton Constructor
     * @param setting setting that hold the value of the button
     * @param stage old stage that SettingButton is on
     * @param pane old pane that the SettingButton is on
     * @param imageView image of the button
     * @param imageViewHover image of the button while hovering
     */
    public SettingButton(Setting setting, Stage stage, Pane pane, ImageView imageView, ImageView imageViewHover)
    {
        super(setting);
        //get all the attribute
        this.settingController = new SettingController();
        this.stage = stage;
        this.pane = pane;
        this.imageView = imageView;
        this.imageViewHover = imageViewHover;
        this.setGraphic(this.imageView);
        this.setStyle("-fx-background-color: transparent;");
        this.makeButtonAccessible("Setting Button", "Click to modify the setting of the game");

        // Display setting menu with slider, label
        this.settingRoot = new AnchorPane();
        this.settingStage = new Stage(StageStyle.TRANSPARENT);
        this.settingStage.initOwner(this.stage);
        this.settingStage.initModality(Modality.APPLICATION_MODAL);
        this.settingRoot.setStyle("-fx-background-color: transparent;");
        this.settingBackground = new ImageView(new Image("Button/SettingBase.png"));
        this.settingBackground.setFitWidth(this.setting.settingWidth);
        this.settingBackground.setFitHeight(this.setting.settingHeight);
        this.settingRoot.getChildren().add(this.settingBackground);
        this.settingRoot.setPrefSize(this.setting.settingWidth, this.setting.settingHeight);
        this.backButton = new BackToMenuButton(this.settingStage, this.pane, settingController);
        this.backButton.setLayoutX(this.setting.settingWidth/2-34);
        this.backButton.setTranslateY(480);
        this.settingRoot.getChildren().add(this.backButton);
        this.settingScene = new Scene(this.settingRoot, Color.TRANSPARENT);
        this.settingScene.getStylesheets().add(getClass().getResource("Slider.css").toExternalForm());

        // Create slider for volume
        this.settingController.addPane(this.settingRoot);
        this.settingController.addPane(this.pane);
        Label volumeLabel = new Label();
        this.volumeSlider = new VolumeSlider(volumeLabel, this.settingController, this.settingRoot);
        this.volumeSlider.setAccessibleRole(AccessibleRole.SLIDER);
        this.volumeSlider.setAccessibleRoleDescription("Volume slider");
        this.volumeSlider.setAccessibleText("Use arrow keys left and right to modify the volume");
        this.volumeSlider.setFocusTraversable(true);
        Label brightnessLabel = new Label();
        this.brightnessSlider = new BrightnessSlider(brightnessLabel, this.settingController, this.settingRoot);
        this.brightnessSlider.setAccessibleRole(AccessibleRole.SLIDER);
        this.brightnessSlider.setAccessibleRoleDescription("Volume slider");
        this.brightnessSlider.setAccessibleText("Use arrow keys left and right to modify the volume");
        this.brightnessSlider.setFocusTraversable(true);
        // Create togglebutton for mode change;
        Label normalLabel = new Label();
        Label accessLabel = new Label();
        ToggleButton normalMode = new NormalCheckBox(this.settingController, normalLabel, this.settingRoot);
        normalMode.setAccessibleRole(AccessibleRole.TOGGLE_BUTTON);
        normalMode.setAccessibleRoleDescription("Toggle Button for normal mode");
        normalLabel.setAccessibleText("Turn on to enter normal mode.");
        ToggleButton acessibilityMode = new AccessibilityCheckBox(this.settingController, accessLabel, this.settingRoot);
        this.settingController.setToggleButton(normalMode, acessibilityMode);
        this.settingRoot.getChildren().addAll(this.volumeSlider, this.brightnessSlider, volumeLabel, brightnessLabel, normalMode, acessibilityMode);


        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displaySetting();
            }
        });
        this.setTranslateY(175);
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                enterHover();
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitHover();
            }
        });
    }

    /**
     * method that change the image of the button while hover
     */
    private void enterHover()
    {
        this.setGraphic(this.imageViewHover);
    }

    /**
     * method that reset the image
     */
    private void exitHover()
    {
        this.setGraphic(this.imageView);
    }

    /**
     * method that displays the menu when the button is clicked
     */
    public void displaySetting(){
        this.settingStage.setScene(this.settingScene);
        this.settingStage.show();

    }

    /**
     * setter method for button imag
     * @param imageView image of button
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        this.setGraphic(imageView);
    }

    /**
     * setter method that set image of button while hover
     * @param imageView image of button while hovering
     */
    public void setImageViewHover(ImageView imageView)
    {
        this.imageViewHover = imageView;
    }

    /**
     * setter method that set the stage of the button
     * @param stage stage that button is in
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * setter method that set the root of the button
     * @param pane the root of the button
     */
    public void setPane(Pane pane)
    {
        this.pane = pane;
        this.backButton.setPane(pane);
    }

    /**
     * getter method to get the controller of the button
     * @return
     */
    public SettingController getSettingController()
    {
        return this.settingController;
    }
}

