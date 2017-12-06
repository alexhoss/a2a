package ca.bcit.comp2526.a2a;


import java.awt.Color;
import java.util.ArrayList;

/**
 * Carnivore class represents a Carnivore.
 *
 * @author alexhosseini
 * @version 1.0
 */
public class Carnivore extends Animal implements OmnEdible {
    private static final Color BG = Color.MAGENTA;
    private static final int MATES_REQ = 1;
    private static final int FOOD_REQ = 2;
    private static final int EMPTY_REQ = 2;
    private static final int DEATH = 7;

    /**
     * Creates a Carnivore in the provided cell.
     *
     * @param loc to place Carnivore
     */
    public Carnivore(Cell loc) {
        super(loc);

    }

    /**
     * Initialize a carnivore.
     */
    public void init() {
        setMatesReq(MATES_REQ);
        setEmptyReq(EMPTY_REQ);
        setFoodReq(FOOD_REQ);
        setBG(BG);
        setDeath(DEATH);
    }

    /**
     * Detects if a carnivore can give birth.
     * @return true if birth conditions met
     */
    public boolean detectBirth() {
        ArrayList<Cell> neighbors = getNeighbors();

        int food = 0;
        int neighbs = 0;
        int empty = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof CarnEdible) {
                food++;
            } else if (c.getLife() instanceof Carnivore) {
                neighbs++;
            } else if (c.getLife() == null) {
                empty++;
            }
        }
        return canBirth(neighbs, food, empty);

    }

    /**
     * Detects if food is nearby this.
     * @return true if food is detected
     */
    public boolean detectFood() {
        ArrayList<Cell> neighbors = getNeighbors();
        int plants = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof CarnEdible) {
                plants++;
            }
        }
        return plants >= 1;
    }

    /**
     * Chooses a cell with food to eat.
     * @return A randomly chosen cell to eat from.
     */
    public Cell getFood() {
        ArrayList<Cell> neighbors = getNeighbors();
        ArrayList<Cell> foodCells = new ArrayList<>();

        int food = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof CarnEdible) {
                foodCells.add(c);
                food++;
            }
        }
        return foodCells.get(RandomGenerator.nextNumber(food));
    }


    /**
     * Gives birth to new carnivore.
     *
     * @param newborn the cell to place the newborn in
     */
    public void giveBirth(Cell newborn) {

        newborn.setLife(new Carnivore(newborn));
        newborn.getLife().init();
        newborn.getLife().setCanBirth(false);
        newborn.getLife().setCanMove(false);

    }


}
