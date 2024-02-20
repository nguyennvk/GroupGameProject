package Controller;

import AdventureModel.AdventureGame;
import AdventureModel.Setting;
import Button.GameButton;
import Button.InstructionButton;
import Button.SettingButton;
import Button.StartButton;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.MenuView;

/**
 * MenuController controls everything in the menu, it controls the flow, and update the scene
 */
public class MenuController {

    AdventureGame gameModel;//game model
    MenuView menuView;//class that holds all the button for the menu
    Setting setting;//setting contains all the value for menu components
    SettingButton settingButton;// setting button
    InstructionButton instructionButton;//instruction button

    /**
     * MenuController Constructor
     * @param gameModel game model
     * @param menuView menuview that holds every component of the menu
     * @param setting setting that holds the value of menu
     */
    public MenuController(AdventureGame gameModel, MenuView menuView, Setting setting)
    {
        //get all attribute
        this.gameModel = gameModel;
        this.menuView = menuView;
        this.setting = setting;
    }

    /**
     * method that displays start button
     */
    public void showStart()
    {
        GameButton startButton = new StartButton(this.menuView, this.setting);
        this.menuView.getStackPane().getChildren().add(startButton);
    }

    /**
     * method that displays instruction button
     */
    public void showInstruction()
    {
        InstructionButton instructionButton = new InstructionButton(this.menuView, this.setting, settingButton.getSettingController(), this.gameModel);
        this.menuView.getStackPane().getChildren().add(instructionButton);
        this.instructionButton = instructionButton;
    }

    /**
     * method that displayes setting button
     */
    public void showSetting()
    {
        //get image of the button
        ImageView imageView = new ImageView(new Image("Button/SettingButton.png"));
        imageView.setFitWidth(this.setting.buttonWidth);
        imageView.setFitHeight(this.setting.buttonHeight);
        ImageView imageViewHover = new ImageView(new Image("Button/SettingButtonHover.png"));
        imageViewHover.setFitHeight(this.setting.buttonHeight);
        imageViewHover.setFitWidth(this.setting.buttonWidth);
        SettingButton settingButton = new SettingButton(this.setting, this.menuView.getStage(), this.menuView.getStackPane(), imageView, imageViewHover);
        this.settingButton = settingButton;
        this.menuView.getStackPane().getChildren().add(settingButton);
    }

    /**
     * methods that show an entire menu
     */
    public void showMenu()
    {
        this.menuView.show();
        this.showStart();
        this.showSetting();
        this.showInstruction();

    }

    /**
     * getter method that get the setting button
     * @return
     */
    public SettingButton getSettingButton()
    {
        return this.settingButton;

    }

    /**
     * getter method that get the instruction button
     * @return
     */
    public InstructionButton getInstructionButton(){
        return this.instructionButton;
    }
}
