package AdventureModel.npc;

import views.AdventureGameView;

import java.util.List;

/**
 The basic implementation of an NPC that is inherited by all future NPC variants.
 It has the ability to accept visits from other sources, such as players, which starts
 an interaction between them.
 */
public class NPC implements VisitableNPC {

    private List<String> monologue;
    private String name;
    private String imagePath;
    private int lineNumber;
    private AdventureGameView view;

    /**
     * NPC Constructor, initializes attributes
     * @param name the name of the NPC
     * @param monologue a list of strings containing the NPC's monologue, in order
     * @param imagePath a file path to an image of the NPC
     */
    public NPC(String name, List<String> monologue, String imagePath) {
        this.name = name;
        this.monologue = monologue;
        //this.view =
        this.imagePath = imagePath;
        this.lineNumber = 0;
    }

    public void acceptVisit(NPCVisitor visitor) {
        visitor.setInteractingNPC(this);
    }

    /**
     Return a message to the user.
     When invoked, the next portion of the message will appear. This will continue
     until the NPC has nothing else to say, then the monologue will reset.

     @return a message to the user.
     */
    public String getNextLine() {
        if (this.lineNumber < this.monologue.size()) {
            String nextLine = this.monologue.get(lineNumber);
            this.lineNumber += 1;
            return nextLine;
        } else {
            //restart the conversation after the player prompts again
            this.lineNumber = 0;

            return "RESTART CONVERSATION";
        }
    }

    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public void resetConversation() {
        this.lineNumber = 0;
    }

    public String getName() {
        return this.name;
    }
}
