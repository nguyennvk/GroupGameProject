package MovePackage;

import AdventureModel.Player;
import javafx.scene.control.Button;

/**
 * MoveUp move player up and update new player position
 */
public class MoveUp implements Move{

    Player player;//player that change direction
    Button button;//button that display player

    /**
     * MoveUp Constructor
     * @param player hold the position of player
     * @param button display the player
     */
    public MoveUp(Player player, Button button){
        this.player = player;
        this.button = button;
    }

    /**
     * method that executes the player movement
     */
    public void execute(){
        if (this.player.getCurrentPos()[1]+20>=0){
        this.player.getCurrentPos()[1] -= setting.moveSpeed;
        transition.setByY(-setting.moveSpeed);
        transition.play();}

    }


}
