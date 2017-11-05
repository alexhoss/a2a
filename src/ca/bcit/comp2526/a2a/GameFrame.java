package ca.bcit.comp2526.a2a;

import java.awt.GridLayout;
import javax.swing.JFrame;


/**
 * Class to represent a game frame.
 *
 * @author alexhosseini
 * @version 1.0
 */
public class GameFrame extends JFrame {
    private final World world;

    /**
     * Construct the frame which holds the world.
     *
     * @param w world to hold
     */
    public GameFrame(final World w) {
        world = w;
    }

    /**
     * Initliaze the layout to display cells.
     */
    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));

            }
        }


        addMouseListener(new TurnListener(this));
    }

    /**
     * Take turn in world and repaint.
     */
    public void takeTurn() {
        world.takeTurn();
        repaint();
    }
}
