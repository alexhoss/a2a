package ca.bcit.comp2526.a2a;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to represent a cell on the frame or world.
 */
public class Cell extends JPanel {
    private static final  int EIGHTY = 80;
    private static final int FIFTY = 50;
    private static final int HUNDRED = 100;
    private Point p;
    private World world;
    private Type type;
    private Life life;


    private static HashMap Worldcells = new HashMap<Point, Cell>();

    public Cell(World world, int row, int col) {
        this.world = world;
        p = new Point(row, col);
        setBackground(Color.white);
    }

    /**
     * Initialize the cells and set up herbivore or plant or none.
     */
    public void init() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        int rand = RandomGenerator.nextNumber(HUNDRED);

        if (rand >= EIGHTY) {
            this.life = new Herbivore(this);


        } else if (rand >= FIFTY) {
            this.life = new Plant(this);


        }
        Worldcells.put(this.getLocation(), this);


    }

    /**
     * Return location of the cell.
     *
     * @return p the location of the cell
     */
    public Point getLocation() {
        return p;
    }

    /**
     * Returns the adjacent cells of this.
     * @return cells neighbouring this
     */
    public ArrayList<Cell> getAdjacentCells() {
        ArrayList<Cell> cells = new ArrayList<>();
        int x = this.getLocation().x;
        int y = this.getLocation().y;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                Point loc = new Point(i, j);
                if (Worldcells.get(loc) == null) {
                    continue;
                }
                cells.add((Cell) Worldcells.get(loc));
            }
            cells.remove(this);
        }
        return cells;

    }

    /**
     * Create a new plant in the cell.
     */
    public void setPlant() {
        life = new Plant(this);
    }

    /**
     * Set the cells life to a lifeform.
     *
     * @param lifeform the lifeform to belong to cell
     */
    public void setLife(Life lifeform) {
        this.life = lifeform;
    }

    /**
     * Returns the life form in the cell. Can be null
     *
     * @return the life form in the cell
     */
    public Life getLife() {
        return life;
    }

    /**
     * Set the life in the cell to be null.
     */
    public void removeLife() {

        this.life = null;


    }

    /**
     * Manages repainting the cells the correct colours.
     *
     * @param g graphics
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.getLife() instanceof Plant) {
            this.setBackground(Color.GREEN);
        } else if (this.getLife() instanceof Herbivore) {
            this.setBackground(Color.YELLOW);
        } else this.setBackground(Color.white);

    }
}
