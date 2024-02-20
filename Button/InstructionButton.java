package Button;

import AdventureModel.AdventureGame;
import AdventureModel.Setting;
import Controller.SettingController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import views.MenuView;

import java.util.Set;

/**
 * InstructionButton is the Button that show the instruction for the player
 */
public class InstructionButton extends GameButton{
    ImageView imageView;//image of the button
    ImageView imageViewHover;//image of the button when hover
    MenuView menuView;//the menu that this button is on
    Scene instructionScene;//scene to create instruction
    Stage popUpStage;//new popup stage
    VBox instructionRoot;//the root for the instruction display
    GameButton backButton;// button to exit and close the popup stage

    /**
     * InstructionButton Constructor
     * @param menuView menu that the button is on
     * @param setting setting that hold that value of button
     * @param settingController controller to control the flow of the button
     * @param adventureGame the model
     */

    public InstructionButton(MenuView menuView, Setting setting, SettingController settingController, AdventureGame adventureGame)
    {
        super(setting);
        //get all attribute and set up the popup stage
        this.menuView = menuView;
        this.imageView = new ImageView(new Image("Button/InstructionButton.png"));
        this.imageView.setFitWidth(this.setting.buttonWidth);
        this.imageView.setFitHeight(this.setting.buttonHeight);
        this.imageViewHover = new ImageView(new Image("Button/InstructionButtonHover.png"));
        this.imageViewHover.setFitHeight(this.setting.buttonHeight);
        this.imageViewHover.setFitWidth(this.setting.buttonWidth);
        this.setGraphic(this.imageView);
        this.makeButtonAccessible("Instruction Button", "Click to examine the instruction for the game");
        this.setStyle("-fx-background-color: transparent;");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showInstructionDetail();
            }
        });
        this.setTranslateY(250);
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


        //Create scene for instruction
        this.instructionRoot = new VBox(5);
        this.popUpStage = new Stage(StageStyle.TRANSPARENT);
        this.popUpStage.initOwner(this.menuView.getStage());
        this.popUpStage.initModality(Modality.APPLICATION_MODAL);
        TextArea instructionText = new TextArea();
        String instruction = adventureGame.getInstructions();
        instructionText.setText(instruction);
        instructionText.setWrapText(true);
        instructionText.setPrefSize(setting.instructionWidth, setting.instructionHeight);
        this.instructionRoot.setPrefSize(setting.instructionWidth, setting.instructionHeight);
        instructionText.setEditable(false);
        this.instructionRoot.getChildren().add(instructionText);
        this.instructionRoot.setAlignment(Pos.CENTER);
        this.backButton = new BackToMenuButton(this.popUpStage, this.menuView.getStackPane(), settingController);
        this.instructionRoot.getChildren().add(this.backButton);
        settingController.addPane(this.instructionRoot);
        settingController.updateBrightness();
        this.instructionScene = new Scene(this.instructionRoot, Color.TRANSPARENT);
        this.instructionScene.getStylesheets().add(getClass().getResource("Instruction.css").toExternalForm());




    }

    /**
     * setter method that set the image of the button
     * @param imageView image of button
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        this.setGraphic(imageView);
    }

    /**
     * setter method that set the image of the button while hover
     * @param imageView image of hover button
     */
    public void setImageViewHover(ImageView imageView)
    {
        this.imageViewHover = imageView;
    }

    /**
     * method that change the button image while hover
     */
    private void enterHover()
    {
        this.setGraphic(this.imageViewHover);
    }

    /**
     * method that reset the image of the button
     */
    private void exitHover()
    {
        this.setGraphic(this.imageView);
    }

    /**
     * method that show the new popup scene
     */
    private void showInstructionDetail()
    {
        this.popUpStage.setScene(this.instructionScene);
        this.popUpStage.show();
    }
}
