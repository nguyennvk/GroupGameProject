package AdventureModel;

import java.util.List;
/**
 * Class that extends BasicMonster and implement appropriate type of interaction
 */
public class Mage extends BasicMonster {

    /**
     * Mage class Constructor
     * @param name name of the monster
     * @param health health of the monster
     * @param attack attack of the monster
     * @param defense defense of the monster
     * @param mp mana of the monster
     * @param specialAbilities list of special ability that this monster has
     */
    public Mage(String name,int health, int attack, int defense,int mp, List<SpecialAbility> specialAbilities) {
        super(name,health, attack, defense, mp, specialAbilities);
    }

    /**
     * Method to interact between monster and player
     * @param player
     */
    @Override
    public void interactWithPlayer(Player player) {
        if (this.mp >= 25) {
            this.attack += (int)(this.attack * 0.3);
            this.mp -= 25;
        }
    }

}
