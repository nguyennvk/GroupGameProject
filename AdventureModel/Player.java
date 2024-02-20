package AdventureModel;

import AdventureModel.npc.NPC;
import AdventureModel.npc.NPCVisitor;
import AdventureModel.npc.VisitableNPC;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class keeps track of the progress
 * of the player in the game.
 */
public class Player implements Serializable, NPCVisitor {
    /**
     * The current room that the player is located in.
     */
    private Room currentRoom;

    /**
     * The list of items that the player is carrying at the moment.
     */
    public ArrayList<AdventureObject> inventory;

    /**
     * The NPC this player is currently talking to
     */
    private VisitableNPC interactingNPC;
    Setting setting = new Setting();
    private String name;
    public int maxHp;
    public int currentHp;
    public int attack;
    private int defense;
    private int xp;

    public int mp;

    private final double[] currentPos = {(setting.width - setting.playerWidth) / 2, (setting.height - setting.playerHeight) / 2};

    /**
     * Adventure Game Player Constructor
     */
    public Player(Room currentRoom, String name, int maxHp, int attack, int defense, int mp) {
        this.inventory = new ArrayList<AdventureObject>();
        this.currentRoom = currentRoom;
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.xp = 0;
        this.mp = mp;
    }

    /**
     * This method adds an object into players inventory if the object is present in
     * the room and returns true. If the object is not present in the room, the method
     * returns false.
     *
     * @param object name of the object to pick up
     * @return true if picked up, false otherwise
     */
    public boolean takeObject(String object) {
        if (this.currentRoom.checkIfObjectInRoom(object)) {
            AdventureObject object1 = this.currentRoom.getObject(object);
            this.currentRoom.removeGameObject(object1);
            this.addToInventory(object1);
            return true;
        } else {
            return false;
        }
    }

    public void gainXp(int amount) {
        this.xp += amount;
        // Implement leveling up logic if necessary
    }

    public boolean isAlive() {
        return this.currentHp > 0;
    }

    public void attack(Monster monster) {
        int damageDealt = this.attack - monster.getDefense();
        if (damageDealt < 0) {
            monster.setDefense(-damageDealt);
        } else {
            monster.setDefense(0);
        }
        if (damageDealt < 0) damageDealt = 0;
        monster.takeDamage(damageDealt);
    }

    public void defend() {
        // 增加防御力，可以是临时的
        this.defense += 5; // 举例，具体数值可以调整
    }


    /**
     * checkIfObjectInInventory
     * __________________________
     * This method checks to see if an object is in a player's inventory.
     *
     * @param s the name of the object
     * @return true if object is in inventory, false otherwise
     */
    public boolean checkIfObjectInInventory(String s) {
        for (int i = 0; i < this.inventory.size(); i++) {
            if (this.inventory.get(i).getName().equals(s)) return true;
        }
        return false;
    }


    /**
     * This method drops an object in the players inventory and adds it to the room.
     * If the object is not in the inventory, the method does nothing.
     *
     * @param s name of the object to drop
     */
    public AdventureObject dropObject(String s) {
        AdventureObject o = null;
        for(int i = 0; i<this.inventory.size();i++){
            if(this.inventory.get(i).getName().equals(s)) {
                this.currentRoom.addGameObject(this.inventory.get(i));
                o = this.inventory.remove(i);
            }
        }
        return o;
    }

    /**
     * Setter method for the current room attribute.
     *
     * @param currentRoom The location of the player in the game.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * This method adds an object to the inventory of the player.
     *
     * @param object Prop or object to be added to the inventory.
     */
    public void addToInventory(AdventureObject object) {
        this.inventory.add(object);
    }


    /**
     * Getter method for the current room attribute.
     *
     * @return current room of the player.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * This method sets the NPC that the player is currently interacting with
     *
     * @param npc the NPC the player is now talking to
     */

    /**
     * Getter method for string representation of inventory.
     *
     * @return ArrayList of names of items that the player has.
     */
    public ArrayList<String> getInventory() {
        ArrayList<String> objects = new ArrayList<>();
        for (int i = 0; i < this.inventory.size(); i++) {
            objects.add(this.inventory.get(i).getName());
        }
        return objects;
    }
    public void visitNPC(VisitableNPC npc) {
        npc.acceptVisit(this);
    }
    public void setInteractingNPC(VisitableNPC npc) {
        this.interactingNPC = npc;
    }

    public void removeInteractingNPC() {
        this.interactingNPC.resetConversation();
        this.setInteractingNPC(null);
    }

    public VisitableNPC getInteractingNPC() {
        return this.interactingNPC;
    }

    public double[] getCurrentPos () {
        return this.currentPos;
    }


    public Object getAttackPower () {
        return this.attack;
    }

    public int getDefense () {
        return this.defense;
    }

    public void takeDamage ( int damageDealt){
        this.currentHp -= damageDealt;
    }

    public int getHealth () {
        return this.currentHp;
    }

    public int getMana () {
        return this.mp;
    }
    public int getMax() {return this.maxHp;}

    public void setHealth ( int health){
        this.currentHp = health;
    }

    public void setDefense ( int i){
        this.defense = i;
    }
    public void setCurrentPos(double x, double y)
    {
        this.currentPos[0] = x;
        this.currentPos[1] = y;
    }
    public void heal(int i) {
        if (this.mp >= 20) {
            int healAmount = (int)(this.maxHp * 0.2); // 20% max health
            this.currentHp = Math.min(this.currentHp + healAmount, this.maxHp);
            this.mp -= 20;
        }
    }

    public void rapidAttack() {
        if (this.mp >= 30) {
            this.attack += (int)(this.attack * 0.5); // increase attack value
            this.mp -= 30;

        }
    }
    public void useMana(int manaCost) {
        this.mp -= manaCost;
        if (this.mp < 0) {
            this.mp = 0;
        }
    }


    public ArrayList<Integer> getCurrPos(){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add((int)this.getCurrentPos()[0]);
        arr.add((int)this.getCurrentPos()[1]);
        return arr;
    }
}
