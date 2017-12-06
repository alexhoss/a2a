package ca.bcit.comp2526.a2a;

/**
 * Abstract class to represent an animal.
 * @author alexhosseini
 * @version 1.0
 */
public abstract class Animal extends Life {
    private int death;

    /**
     * Default constructor for an animal.
     * @param loc the cell to reside in
     */
    public Animal(Cell loc) {
        super(loc);
    }

    /**
     * Void method to satisfy abstract init.
     */
    public void init() {
    }
    /**
     * Set the turn to kill the animal.
     * @param d length of turns it has
     */
    public void setDeath(int d) {
        this.death = d;
    }

    /**
     * Return the turn to kil the animal at.
     * @return death int
     */
    public int getDeath() {
        return death;
    }

    /**
     * Set the location of the animal.
     * @param location cell to reside in
     */
    public void setCell(Cell location) {
        setLocation(location);
    }

    /**
     * Moves animal from old cell to new cell.
     *
     * @param newLocation to cell
     */
    public void move(Cell newLocation) {

        Cell oldLocation = this.getLocation();

        this.setCell(newLocation);
        newLocation.setLife(this);
        oldLocation.removeLife();
        this.setCanMove(false);
    }

    /**
     * Eat plant in location.
     *
     * @param loc of plant
     */
    public void eat(Cell loc) {
        loc.removeLife();
        this.resetTurns();
        this.move(loc);


    }

    /**
     * abstract get food method.
     * @return a random cell with food
     */
    public abstract Cell getFood();

    /**
     * abstract get birth method.
     * @return true if the birth conditions are met
     */
    public abstract boolean detectBirth();

    /**
     * General take turn method for any animal.
     */
    public void takeTurn() {
        if (this.isCanMove() && detectFood()) {
            this.eat(getFood());
        } else if (this.isCanMove() && this.detectEmpty()) {
            this.move(getEmpty());
        }
        this.setCanMove(false);

        if (this.isCanBirth() && detectBirth()) {
            giveBirth(getEmpty());
        }
        this.setCanBirth(false);
    }
}
