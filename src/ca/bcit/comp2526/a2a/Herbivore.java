package ca.bcit.comp2526.a2a;

import javax.swing.JPanel;
import java.awt.Color;

/**
 * Herbivore class represents a herbivore.
 * @author alexhosseini
 * @version 1.0
 */
public class Herbivore extends JPanel implements Life {
    private static final Color BG = Color.YELLOW;
    private int turns;


    private boolean canMove = true;


    private Cell location;

    /**
     * Creates a herbivore in the provided cell.
     * @param loc to place herbivore
     */
    public Herbivore(Cell loc) {
        this.location = loc;
    }

    /**
     * Set the colour of the herbivore.
     * @return the colour of the herbivore
     */
    public Color getColor() {
        return BG;

    }

    /**
     * Method to to set herb's location.
     * @param loc cell to place
     */
    public void setCell(Cell loc) {
        this.location = loc;
    }


    /**
     * Moves herbivore from old cell to new cell.
     * @param oldLocation from cell
     * @param newLocation to cell
     */
    public void move(Cell oldLocation, Cell newLocation) {
        this.location = newLocation;
        newLocation.setLife(this);
        oldLocation.removeLife();
        this.canMove = false;


    }

    /**
     * Eat plant in location.
     *
     * @param loc of plant
     */
    public void eat(Cell loc) {
        loc.removeLife();
        this.turns = 0;
        this.move(this.location, loc);

    }

    /**
     * Increment the turns of this herbivore.
     */
    public void incTurns() {
        this.turns++;
    }

    /**
     * Return the turns taken by the herbivore.
     * @return the turns taken by the herbivore
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Return true if herb can move; else false.
     * @return canMove of herb
     */
    public boolean isCanMove() {
        return canMove;
    }

    /**
     * Set the canMove of the herbivore.
     * @param b the bool to set
     */
    public void setCanMove(Boolean b) {
        this.canMove = b;
    }





}


