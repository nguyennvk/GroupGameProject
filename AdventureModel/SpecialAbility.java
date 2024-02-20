package AdventureModel;

/**
 * SpecialAbility class for player and monster to perform
 */
public class SpecialAbility {
    private String name;// name of the ability
    private Effect effect; //effect of the ability

    /**
     * SpecialAbility Constructor
     * @param name name of the ability
     * @param effect effect of the ability
     */
    public SpecialAbility(String name, Effect effect) {
        this.name = name;
        this.effect = effect;
    }

    /**
     * getter method that get the effect
     * @return returns the effect
     */
    public Effect getEffect() {return effect;
    }

}
