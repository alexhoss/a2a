package ca.bcit.comp2526.a2a;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TurnListener class listens to mouse clicks.
 *
 * @author alexhosseini
 * @version 1.0
 */
public class TurnListener extends MouseAdapter {
    private final GameFrame gf;

    /**
     * Construct and link TurnListener to the game frame.
     *
     * @param gameFrame game frame to listen to
     */
    public TurnListener(GameFrame gameFrame) {
        gf = gameFrame;
    }


    /**
     * Method to call turn when mouse clicked.
     *
     * @param e the mouse event
     */
    public void mouseClicked(MouseEvent e) {
        gf.takeTurn();

    }
}
