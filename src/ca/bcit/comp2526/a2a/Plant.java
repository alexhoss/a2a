package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Class to represent a plant.
 *
 * @author alexhosseini
 * @version 1.0
 */
public class Plant extends Life implements HerbEdible, OmnEdible {
    private static final Color BG = Color.green;
    private static final int MATES_REQ = 2;
    private static final int FOOD_REQ = 0;
    private static final int EMPTY_REQ = 3;
    /**
     * Create a plant in the param cell.
     * @param loc to place to plant
     */
    public Plant(Cell loc) {
        super(loc);

    }


    /**
     * Initialize a plant cell.
     */
    public void init() {
        setMatesReq(MATES_REQ);
        setEmptyReq(EMPTY_REQ);
        setFoodReq(FOOD_REQ);
        setBG(BG);

    }

    /**
     * General taketurn method for a plant.
     */
    void takeTurn() {
        if (this.isCanBirth() && detectBirth()) {
            giveBirth(getEmpty());
        }
    }

    /**
     * Detects food for plant. Always false in thsi version.
     * @return false
     */
    public boolean detectFood() {
        return false;
    }

    /**
     * Return true if plant birth conditions met.
     * @return true if plant can birth
     */
    public boolean detectBirth() {
        ArrayList<Cell> neighbors = getNeighbors();

        int plants = 0;
        int empty = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof Plant) {
                plants++;
            } else if (c.getLife() == null) {
                empty++;
            }
        }
        return canBirth(plants, 0, empty);
    }


    /**
     * Give birth to a new plant cell.
     * @param newBorn a randomly chosen cell
     */
    public void giveBirth(Cell newBorn) {
        newBorn.setLife(new Plant(newBorn));
        newBorn.getLife().init();
        newBorn.getLife().setCanBirth(false);
        newBorn.getLife().setCanMove(false);

    }

}

