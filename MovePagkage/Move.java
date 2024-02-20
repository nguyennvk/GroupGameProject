package MovePackage;


import AdventureModel.Setting;
import javafx.animation.TranslateTransition;
/**
 * an interface that all move implement
 */
public interface Move {
    public void execute(); //execute movement
    Setting setting = new Setting(); //setting of the player
    TranslateTransition transition = new TranslateTransition(); //display transition

}
