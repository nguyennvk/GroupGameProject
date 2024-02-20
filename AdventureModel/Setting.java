package AdventureModel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import AdventureModel.AdventureLoader;

/**
 * General Setting class that holds the value for all attribute of the Game
 */
public class Setting {
    public final double width = 1280; // width of the game screen
    public final double height = 720; // height of the game screen
    public final double playerWidth = 150; // width of the player
    public final double playerHeight = 300;// height of the player
    public final int moveSpeed = 20;// move speed of the player
    public ImageView frontView; // front view image of the player
    public ImageView backView;// back view image of the player

    public ImageView leftView; // left view image of the player

    public ImageView rightView;// right view image of the player

    public int buttonWidth = 270; // width of the menu button
    public int buttonHeight = 72; // height of the menu button
    public double instructionWidth = 600; //width of the instruction button
    public double instructionHeight = 400;// height of the instruction button
    public double settingWidth = 600;// width of setting button
    public double settingHeight = 600;// height of setting button
    /**
     * Setting Constructor
     * @param dir directory of file
     */
    public Setting(String dir)
    {
        this.frontView = new ImageView(new Image(dir+"/player-images/character-front.png"));
        this.backView = new ImageView(new Image(dir+"/player-images/character-back.png"));

        this.rightView = new ImageView(new Image(dir+"/player-images/character-left.png"));

        this.leftView = new ImageView(new Image(dir+"/player-images/character-right.png"));

    }

    /**
     * Setting Constructor
     */
    public Setting(){}
}
