package AdventureModel.npc;

/**
This interface, implemented by all visitable NPCs, allows them to interact with entities who
request to visit them.
 */

public interface VisitableNPC {

    /**
     Allow the visitor to visit this NPC, which invokes the start of the interaction.

     @param visitor the visitor to this NPC, typically a player
     */
    public void acceptVisit(NPCVisitor visitor);

    /**
     Return a message to the user. This will continue
     until the NPC has nothing else to say.

     @return a message to the user.
     */
    public String getNextLine();

    /**
     * Setter method for imagePath
     *
     * @return the path to this NPC's image file.
     */
    public String getImagePath();

    /**
     * Have the NPC's monologue start at the beginning, the next time an interaction occurs
     */
    public void resetConversation();

    /**
     * Getter method for NPC's name
     *
     * @return the name of this NPC
     */
    public String getName();
}