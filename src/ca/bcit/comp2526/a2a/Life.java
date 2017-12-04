package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Interface to represent a life form.
 *
 * @author alexhosseini
 * @version 1.0
 */
public abstract class Life implements Serializable {
    private boolean canMove;
    private boolean canBirth;
    private int matesReq;
    private int foodReq;
    private int emptyReq;
    private Color backGround;
    private Cell location;
    private int turns;
    private int death;

    /**
     * Default constructor for a life form.
     *
     * @param loc cell the life resides
     */
    public Life(Cell loc) {
        this.location = loc;
    }

    /**
     * Gets the location of the cell.
     * @return the cell the life form is residing in
     */
    public Cell getLocation() {
        return location;
    }

    /**
     * Returns the turns the animal has taken.
     * @return the turns of this animal
     */
    public int getTurns() {
        return turns;
    }


    /**
     * Sets the color of the cell as the life.
     * @param backGround color of the cell
     */
    public void setBackGround(Color backGround) {
        this.backGround = backGround;
    }

    /**
     * Sets the location of the life form.
     * @param location the cell to reside in
     */
    public void setLocation(Cell location) {
        this.location = location;
    }



    /**
     * Set birth of life form.
     *
     * @param b boolean.
     */
    public void setCanBirth(boolean b) {
        this.canBirth = b;
    }


    /**
     * Set can move of life form.
     *
     * @param b boolean
     */
    public void setCanMove(boolean b) {
        this.canMove = b;
    }



    /**
     * Returns the color of the life form.
     *
     * @return the colour of the life form
     */
    Color getColor() {
        return backGround;
    }

    /**
     * Determines if a birth can be given this turn.
     * @param mates nearby similar life
     * @param food nearby food life
     * @param empty nearby empty cells
     * @return true if conditions met for birth
     */
    public boolean canBirth(int mates, int food, int empty) {
        return (mates >= matesReq && food >= foodReq && empty >= emptyReq);
    }

    /**
     * Stub for giving birth.
     * @param loc the cell to give birth in
     */
    public abstract void giveBirth(Cell loc);

    /**
     * Stub for detecting food.
     * @return true if food found
     */
    public abstract boolean detectFood();

    /**
     * stub for detecting birth.
     * @return true if birth conditions met
     */
    public abstract boolean detectBirth();

    /**
     * stub to initiliaze a life form.
     */
    public abstract void init();

    /**
     * Sets the requirements of food for birth.
     * @param foodReq the food req to birth
     */
    public void setFoodReq(int foodReq) {
        this.foodReq = foodReq;
    }

    /**
     * Sets the empty cells req for birth.
     * @param emptyReq empty cells req
     */
    public void setEmptyReq(int emptyReq) {
        this.emptyReq = emptyReq;
    }

    /**
     * Sets the mates req for birth.
     * @param matesReq the
     */
    public void setMatesReq(int matesReq) {
        this.matesReq = matesReq;
    }

    /**
     * Sets the background colour of the life form.
     * @param bg the background color to set
     */
    public void setBG(Color bg) {
        this.backGround = bg;
    }

    /**
     * Detects if there are nearby empty cells.
     * @return true if empty cell found
     */
    public boolean detectEmpty() {
        ArrayList<Cell> neighbors = getNeighbors();
        int empty = 0;
        for (Cell c : neighbors) {
            if (c.getLife() == null) {
                empty++;
            }
        }
        return empty > 0;

    }


    /**
     * Finds empty(life==null) neighbouring cells.
     * @return a randomly chosen empty cell
     */
    public Cell getEmpty() {
        ArrayList<Cell> neighbors = getNeighbors();
        ArrayList<Cell> emptyCells = new ArrayList<>();

        int empty = 0;
        for (Cell c : neighbors) {
            if (c.getLife() == null) {
                emptyCells.add(c);
                empty++;
            }
        }

        return emptyCells.get(RandomGenerator.nextNumber(empty));
    }


    /**
     * Gets the neighbouring lifes of this.
     * @return an arraylist of the neighbouring cells
     */
    public ArrayList getNeighbors() {
        ArrayList<Cell> neighbors = new ArrayList<>();
        int x = this.location.getLocation().x;
        int y = this.location.getLocation().y;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                Point loc = new Point(i, j);

                if (Cell.getWorldCells().get(loc) == null) {
                    continue;
                }
                neighbors.add((Cell) Cell.getWorldCells().get(loc));
            }
            neighbors.remove(this);
        }
        return neighbors;
    }

    /**
     * Increment the turns of this.
     */
    public void incTurns() {
        this.turns++;
    }

    /**
     * Gets if this has given birth this turn or born.
     * @return boolean true if a life can give birth
     */
    public boolean isCanBirth() {
        return canBirth;
    }

    /**
     * Gets if this can move this turn.
     * @return true if the this can move
     */
    public boolean isCanMove() {
        return canMove;
    }

    /**
     * Reset the turns taken of this.
     */
    public void resetTurns() {
        this.turns = 0;
    }


}
