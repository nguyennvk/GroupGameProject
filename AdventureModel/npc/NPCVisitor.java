package AdventureModel.npc;

/**
 An interface implemented by all entities that are able to interact with NPCs.
 */
public interface NPCVisitor {

    /**
    Visit the given npc, which invokes an interaction between the NPCVisitor and the VisitableNPC

     @param npc the NPC to visit
     */
    public void visitNPC(VisitableNPC npc);

    /**
    Set npc as the npc the visitor is currently visiting

     @param npc the NPC to set as the one that is interacting with the visitor
     */
    public void setInteractingNPC(VisitableNPC npc);

    /**
    Getter for the npc this visitor is currently interacting with

     @return the NPC this visitor is interacting with
     */
    public VisitableNPC getInteractingNPC();
}