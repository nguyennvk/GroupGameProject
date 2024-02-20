package MovePackage;

import AdventureModel.Player;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * MoveRight moves player and display player to new position
 */
public class MoveRight implements Move{
    Player player;//player that change direction
    Button button;//button that display player

    /**
     * MoveRight Constructor
     * @param player holds the position of the player
     * @param button display the player
     */
    public MoveRight(Player player, Button button){
        this.player = player;
        this.button = button;
    }

    /**
     * method that execute the player movement
     */
    public void execute(){
        if (this.player.getCurrentPos()[0]<setting.width-4*setting.moveSpeed){
        this.player.getCurrentPos()[0] += setting.moveSpeed;
        transition.setByX(setting.moveSpeed);
        transition.play();}
    }
}
