package MovePackage;

import AdventureModel.Player;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
/**
 * MoveLeft make the player move down and update the position of the player
 */
public class MoveLeft implements Move{
    Player player;//player that change direction
    Button button;//button that display player

    /**
     * MoveLeft Constructor
     * @param player holds the position of the player
     * @param button display the player
     */
    public MoveLeft(Player player, Button button){
        this.player = player;
        this.button = button;
    }

    /**
     * method that execute the movement
     */
    public void execute(){
        if (this.player.getCurrentPos()[0]>=-2*setting.moveSpeed){
        this.player.getCurrentPos()[0] -= setting.moveSpeed;
        transition.setByX(-setting.moveSpeed);
        transition.play();}
    }
}

