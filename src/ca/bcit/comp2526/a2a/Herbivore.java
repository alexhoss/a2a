package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Herbivore class represents a herbivore.
 *
 * @author alexhosseini
 * @version 1.0
 */
public class Herbivore extends Animal implements OmnEdible, CarnEdible {
    private static final Color BG = Color.YELLOW;
    private static final int MATES_REQ = 2;
    private static final int FOOD_REQ = 1;
    private static final int EMPTY_REQ = 2;
    private static final int DEATH = 10;
    /**
     * Creates a herbivore in the provided cell.
     *
     * @param loc to place herbivore
     */
    public Herbivore(Cell loc) {
        super(loc);

    }

    /**
     * Initialize a herbivore animal.
     */
    public void init() {
        setMatesReq(MATES_REQ);
        setEmptyReq(EMPTY_REQ);
        setFoodReq(FOOD_REQ);
        setBG(BG);
        setDeath(DEATH);
    }

    /**
     * Detects if animal can give birth.
     * @return true if herb birth conditions met
     */
    public boolean detectBirth() {
        ArrayList<Cell> neighbors = getNeighbors();

        int food = 0;
        int herbs = 0;
        int empty = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof HerbEdible) {
                food++;
            } else if (c.getLife() instanceof Herbivore) {
                herbs++;
            } else if (c.getLife() == null) {
                empty++;
            }
        }
        return canBirth(herbs, food, empty);

    }

    /**
     * Detects if food is nearby herb.
     * @return true if herb food found
     */
    public boolean detectFood() {
        ArrayList<Cell> neighbors = getNeighbors();
        int plants = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof HerbEdible) {
                plants++;
            }
        }
        return plants >= 1;
    }

    /**
     * Chooses a random cell with food for herb.
     * @return a cell with food
     */
    public Cell getFood() {
        ArrayList<Cell> neighbors = getNeighbors();
        ArrayList<Cell> plantCells = new ArrayList<>();

        int plants = 0;
        for (Cell c : neighbors) {
            if (c.getLife() instanceof HerbEdible) {
                plantCells.add(c);
                plants++;
            }
        }
        return plantCells.get(RandomGenerator.nextNumber(plants));
    }




    /**
     * Gives birth to new carnivore.
     * @param newborn the cell to place the newborn in
     */
    public void giveBirth(Cell newborn) {

        newborn.setLife(new Herbivore(newborn));
        newborn.getLife().init();
        newborn.getLife().setCanBirth(false);
        newborn.getLife().setCanMove(false);

    }



}


