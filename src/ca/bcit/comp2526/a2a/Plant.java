package ca.bcit.comp2526.a2a;

import javax.swing.JPanel;
import java.awt.Color;

/**
 * Class to represent a plant.
 *
 * @author alexhosseini
 * @version 1.0
 */
public class Plant extends JPanel implements Life {
    private static final Color BG = Color.green;

    private Cell loc;
    
    /**
     * Create a plant in the param cell.
     * @param location to place to plant
     */
    public Plant(Cell location) {
        this.loc = location;

    }

    /**
     * Sets plants location.
     * @param location cell holding this
     */
    public void setCell(Cell location) {
        this.loc = location;

    }


    @Override
    public void move(Cell oldLocation, Cell location) {

    }

    @Override
    public Color getColor() {
        return BG;

    }


}

