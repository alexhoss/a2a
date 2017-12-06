package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Omnivore class represents a Omnivore.
 *
 * @author alexhosseini
 * @version 1.0
 */
public class Omnivore extends Animal implements CarnEdible {
    private static final Color BG = Color.BLUE;
    private static final int MATES_REQ = 1;
    private static final int FOOD_REQ = 3;
    private static final int EMPTY_REQ = 3;
    private static final int DEATH = 2;

    /**
     * Creates a Omnivore in the provided cell.
     *
     * @param loc to place Omnivore
     */
    public Omnivore(Cell loc) {
        super(loc);

    }

    /**
     * Initializes an omnivore animal.
     */
    public void init() {
        setMatesReq(MATES_REQ);
        setEmptyReq(EMPTY_REQ);
        setFoodReq(FOOD_REQ);
        setBG(BG);
        setDeath(DEATH);
    }

    /**
     * Detects if omnivore can give birth.
     * @return true if omni coniditons met
     */
    public boolean detectBirth() {
        ArrayList<Cell> neighbors = getNeighbors();

        int food = 0;
        int neighbs = 0;
        int empty = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof OmnEdible) {
                food++;
            } else if (c.getLife() instanceof Omnivore) {
                neighbs++;
            } else if (c.getLife() == null) {
                empty++;
            }
        }
        return canBirth(neighbs, food, empty);

    }

    /**
     * Detecs if omnivore has nearby food.
     * @return true if food detected
     */
    public boolean detectFood() {
        ArrayList<Cell> neighbors = getNeighbors();
        int plants = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof OmnEdible) {
                plants++;
            }
        }
        return plants >= 1;
    }

    /**
     * Gets a random cell with food to eat.
     * @return a randomly chosen food cell
     */
    public Cell getFood() {
        ArrayList<Cell> neighbors = getNeighbors();
        ArrayList<Cell> foodCells = new ArrayList<>();

        int food = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof OmnEdible) {
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

        newborn.setLife(new Omnivore(newborn));
        newborn.getLife().init();
        newborn.getLife().setCanBirth(false);
        newborn.getLife().setCanMove(false);

    }


}
