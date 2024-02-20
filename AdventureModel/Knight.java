package AdventureModel;

import java.util.List;

/**
 * Class that extends BasicMonster and implement appropriate type of interaction
 */
public class Knight extends BasicMonster {
    /**
     * Constructor for Knight class
     * @param name name of the monster
     * @param health health of the monster
     * @param attack attack of the monster
     * @param defense defense of the monster
     * @param mp mana of the monster
     */
    public Knight(String name, int health, int attack, int defense,int mp, List<SpecialAbility> specialAbilities) {
        super(name,health, attack, defense, mp,specialAbilities);
    }

    /**
     * this method will make knight interact with player
     * @param player the main player
     */
    @Override
    public void interactWithPlayer(Player player) {
        // Specific interaction logic for Knight
        if (this.health < 50 && this.mp >= 15) {
            this.defense += (int)(this.defense * 0.5);
            this.mp -= 15;
        }

    }

}
