package ca.bcit.comp2526.a2a;


/**
 * Interface to represent a life form.
 * @author alexhosseini
 * @version 1.0
 */
public interface Life {

    /**
     * Sets the location(cell) which holds this.
     * @param location cell holding this
     */
    void setCell(Cell location);

    /**
     * Move the life form to the new cell location.
     * @param oldLocation from cell
     * @param location to cell
     */
    void move(Cell oldLocation, Cell location);

}
