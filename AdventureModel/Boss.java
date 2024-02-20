package AdventureModel;

import java.util.List;

import static AdventureModel.Effect.BUFF;
import static AdventureModel.Effect.CUSTOM;

/**
 * Class that extends BasicMonster and implement appropriate type of interaction
 */
public class Boss extends BasicMonster {

    public String name; //name of the boss

    /**
     * Constructor for the boss
     * @param name name of the boss
     * @param health health of the boss
     * @param attack attack of the boss
     * @param defense defense of the boss
     * @param mp mana of the boss
     * @param specialAbilities specialAbilities of the boss
     */
    public Boss(String name,int health, int attack, int defense,int mp, List<SpecialAbility> specialAbilities) {
        super(name,health, attack, defense, mp,specialAbilities);

    }

    /**
     * this method trigger special ability at certain condition
     * @param player
     */
    @Override
    public void interactWithPlayer(Player player) {
        // Specific interaction logic for Boss
        if (this.health < 30 && this.mp >= 50) {
            this.attack *= 2;
            this.mp -= 50;
        }
    }

}
