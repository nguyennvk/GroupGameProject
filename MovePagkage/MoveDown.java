package MovePackage;

import AdventureModel.Player;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * MoveDown make the player move down and update the position of the player
 */
public class MoveDown implements Move{
    Player player;//player that change direction
    Button button;//button that display player

    /**
     * MoveDown Constructor
     * @param player player holds the possition of the player
     * @param button button that display the player
     */
    public MoveDown(Player player, Button button){
        this.player = player;
        this.button = button;
    }

    /**
     * method that execute the move action
     */
    public void execute(){
        if (this.player.getCurrentPos()[1]<=setting.height- setting.playerHeight){
        this.player.getCurrentPos()[1] += setting.moveSpeed;
        transition.setByY(setting.moveSpeed);
        transition.play();}
    }
}
