package MovePackage;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

/**
 * MoveExecution is an invoker that invoke the move
 */
public class MoveExecution {
    /**
     * MoveExecution Constructor
     * @param m move that needed to be executed
     */
    public void execute(Move m)
    {
        m.execute();
    }

}
