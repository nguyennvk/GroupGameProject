package AdventureModel;

import javafx.scene.layout.AnchorPane;
import views.AdventureGameView;

/**
 * Monster interface that every monster implement
 */
public interface Monster {
    /**
     * method for monster to interact with player
     * @param player main player
     */
    void interactWithPlayer(Player player);

    /**
     * monster is damaged
     * @param damage the amount of damage
     */
    void takeDamage(int damage);

    /**
     * monster attack player
     * @param player the player that monster attack
     */
    void attack(Player player);

    /**
     * getter method to get the monster defense
     *
     */
    int getDefense();

    /**
     * check if the monster is alive
     *
     */
    boolean isAlive();

    /**
     * check if the monster is defeated
     *
     */
    boolean isDefeated();

    /**
     * getter method that get the monster health
     *
     */
    int getHealth();

    /**
     * getter method that get the monster mana
     * @return
     */
    int getMana();

    /**
     * method that reset the monster status after being defeated
     */
    void resetState();

    /**
     * setter method that set the monster defense
     * @param i the amount of defense the monster get
     */
    void setDefense(int i);

    /**
     * getter method that get the name of the monster
     *
     */
    String getname();
}
