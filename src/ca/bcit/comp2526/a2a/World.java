package ca.bcit.comp2526.a2a;

import java.util.ArrayList;


/**
 * Class to represent a world.
 * @author alexhosseini
 * @version 1.0
 */
public class World {
    private Cell[][] cells;
    private int rows;
    private int col;
    private static final int EIGHT = 8;
    private static final int DEATH = 10;
    private static final int THREE = 3;

    /**
     * Create the world with rows and col.
     * @param rows num of rows
     * @param col num of cols
     */
    public World(int rows, int col) {
        this.rows = rows;
        this.col = col;
    }

    /**
     * Initialize the world cells with cell objects.
     */
    public void init() {
        cells = new Cell[rows][col];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                Cell k = new Cell(this, i, j);
                k.init();
                cells[i][j] = k;

            }
        }
    }

    /**
     * Advance world state.
     */
    public void takeTurn() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {

                if (getCellAt(i, j).getLife() instanceof Herbivore) {

                    if (((Herbivore) getCellAt(i, j).getLife()).turns == DEATH) {
                        getCellAt(i, j).removeLife();
                        continue;
                    }
                    if (!(((Herbivore) getCellAt(i, j).getLife()).canMove)) {
                        continue;
                    }

                    System.out.println(((Herbivore) getCellAt(i, j).getLife()).turns);

                    ArrayList<Cell> neighbours = getCellAt(i, j).getAdjacentCells();


                    int rand = RandomGenerator.nextNumber(EIGHT);

                    if (neighbours.size() > rand && neighbours.get(rand).getLife() instanceof Plant) {
                        ((Herbivore) getCellAt(i, j).getLife()).eat(neighbours.get(rand));
                    } else if (neighbours.size() > rand && !(neighbours.get(rand).getLife() instanceof Herbivore)) {
                        getCellAt(i, j).getLife().move(this.getCellAt(i, j), neighbours.get(rand));
                    }


                } else if (getCellAt(i, j).getLife() instanceof Plant) {
                    int empty = 0;
                    int plants = 0;
                    ArrayList<Integer> emptyCells = new ArrayList<>();
                    ArrayList<Cell> neighbours = getCellAt(i, j).getAdjacentCells();

                    for (Cell c : neighbours) {
                        if (c.getLife() instanceof Plant)  {
                            plants++;
                        } else if (c.getLife() == null) {
                            emptyCells.add(neighbours.indexOf(c));
                            empty++;
                        }
                    }
                    if (plants >= 2 && empty >= THREE) {
                        int rand = RandomGenerator.nextNumber(empty - 1);
                        int ind =  emptyCells.get(rand);
                        neighbours.get(ind).setPlant();

                    }
                }
            }

        }
        resetAndInc();

    }

    /**
     * Reset move bool and increment turns of herbivores.
     */
    private void resetAndInc() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (cells[i][j].getLife() instanceof Herbivore) {
                    ((Herbivore) cells[i][j].getLife()).canMove = true;
                    ((Herbivore) cells[i][j].getLife()).turns++;
                }
            }
            }
    }

    /**
     * Return rows of world.
     * @return rows int
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Return cols of world.
     * @return col int
     */
    public int getColumnCount() {
        return col;
    }

    /**
     * Returns the cell at the specified row,col.
     * @param row first index
     * @param colu second index
     * @return the cell at this index
     */
    public Cell getCellAt(int row, int colu) {
        return cells[row][colu];

    }
}
