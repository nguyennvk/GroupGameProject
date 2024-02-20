package Button;

import AdventureModel.Setting;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * GameButton extends Button and is the parent class of InstructionButton and SettingButton
 */
public class GameButton extends Button {
    Setting setting; // the setting for button width and height
    ImageView imageView; //image of the button
    ImageView imageViewHover; //image of the button when hover

    /**
     * GameButton Constructor
     * @param setting the setting of the button including the width and height
     */
    public GameButton(Setting setting)
    {
        this.setting = setting;
    }

    /**
     * GameButton Constructor
     */
    public GameButton()
    {}

    /**
     * method that set the accessibility of the button
     * @param name
     * @param shortString
     */
    public void makeButtonAccessible(String name, String shortString) {
        this.setAccessibleRole(AccessibleRole.BUTTON);
        this.setAccessibleRoleDescription(name);
        this.setAccessibleText(shortString);
        this.setFocusTraversable(true);
    }

}
