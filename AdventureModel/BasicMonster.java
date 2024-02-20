package AdventureModel;

import java.util.List;

/**
 * This class is the parent class that every Monster extends
 */
public class BasicMonster implements Monster {
    protected int mp; //mana of a monster
    protected int health; //health of a monster
    protected int attack; //attack of a monster
    protected int defense; //defense of a monster
    protected List<SpecialAbility> specialAbilities; //list of special ability

    private int originalHealth; //the max health of monster
    private int originalMana;//the max mana of monster

    public String name; //name of a monster
    /**
     * BasicMonster Constructor
     * __________________________
     * Initializes attributes
     *
     * @param name the name of the monster
     * @param health health of the monset
     * @param attack attack of the player
     * @param defense defense of the player
     * @param mp mana of the player
     * @param specialAbilities list of special ability that the player has
     */
    public BasicMonster(String name,int health, int attack, int defense, int mp, List<SpecialAbility> specialAbilities) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.specialAbilities = specialAbilities;
        this.mp = mp;
        this.originalHealth = this.health/* 初始生命值 */;
        this.originalMana = this.mp;
        this.name = name;
    }

    /**
     * This method help monster interact with player
     * @param player
     */
    @Override
    public void interactWithPlayer(Player player) {
        // Default interaction logic, can be overridden by subclasses
    }

    /**
     * monster attack player with certain amount
     * @param player
     */
    // Common methods like takeDamage(), performAttack(), etc.
    @Override
    public void attack(Player player) {
        // Implement attack logic
        int damageDealt = this.attack - player.getDefense();
        if (damageDealt < 0){
            player.setDefense(-damageDealt);}
        else { player.setDefense(0);
        }
        if (damageDealt < 0) damageDealt = 0;
        player.takeDamage(damageDealt);
        //int damageDealt = this.attack - player.getDefense();
        //if (damageDealt < 0) damageDealt = 0;
        //player.takeDamage(damageDealt);
    }

    /**
     * this monster is taken damge from the player
     * @param damage
     */
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    /**
     * this methid check if the monster is defeated
     * @return
     */
    @Override
    public boolean isDefeated() {
        return this.health <= 0;
    }

    /**
     * getter method for health
     * @return
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * getter method for mana
     * @return
     */
    @Override
    public int getMana() {
        return this.mp;
    }

    /**
     * getter method for defense
     * @return
     */
    @Override
    public int getDefense() {
        return this.defense;
    }

    /**
     * check if the health of the monster is above 0
     * @return returns whether the monster is alive or not
     */
    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    /**
     * this method reset the state of the monster after being defeated
     */
    @Override
    public void resetState() {
        this.health = this.originalHealth;
        this.mp = this.originalMana;
    }

    /**
     * setter method for defense
     * @param i defense value
     */
    public void setDefense(int i) {this.defense = i;
    }

    /**
     * getter method for name
     * @return the name of the monster
     */
    @Override
    public String getname() {
        return this.name;
    }

    /**
     * setter method for attack
     * @param i the value that attack get
     */
    protected void setAttack(int i) {this.attack = i;
    }
}
