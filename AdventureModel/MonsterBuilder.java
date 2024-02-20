package AdventureModel;

import java.util.List;

/**
 * MonsterBuilder is used to build new monsters that are deployed in some rooms.
 */
public class MonsterBuilder {
    private int health; //health of the monster
    private int attack; //attack of the monster
    private int defense; //defense of the monster
    private List<SpecialAbility> specialAbilities; //list of special abilities

    private String name; //name of the monster

    private int mp; //mana of the monster

    /**
     * MonsterBuilder Constructor
     * @param health health of the monster
     *
     */
    public MonsterBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    /**
     * this method build basic monster
     * @return
     */
    public BasicMonster buildBasicMonster() {
        return new BasicMonster(name,health, attack, defense, mp, specialAbilities);
    }

}
