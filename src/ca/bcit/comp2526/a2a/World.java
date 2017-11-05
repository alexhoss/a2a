package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

/**
 * Class to represent a world.
 *
 * @author alexhosseini
 * @version 1.0
 */
public class World {
    private static final int DEATH = 10;
    private static final int THREE = 3;

    private Cell[][] cells;
    private final int rows;
    private final int col;

    /**
     * Create the world with rows and col.
     *
     * @param rows num of rows
     * @param col  num of cols
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

                final Life life = getCellAt(i, j).getLife();

                if (life instanceof Herbivore) {
                    if (((Herbivore) life).getTurns() == DEATH) {
                        getCellAt(i, j).removeLife();
                        continue;
                    }
                    if (!(((Herbivore) life).isCanMove())) {
                        continue;
                    }

                    turnHerb(i, j, life);
                } else if (life instanceof Plant) {
                    turnPlant(i, j);
                }
            }
        }
        resetAndInc();

    }

    /**
     * Helper method to advance a plant(seed).
     *
     * @param i row of cell
     * @param j col of cell.
     */
    private void turnPlant(int i, int j) {
        int empty = 0;
        int plants = 0;
        ArrayList<Integer> emptyCells = new ArrayList<>();
        ArrayList<Cell> neighbours = getCellAt(i, j).getAdjacentCells();

        for (Cell c : neighbours) {
            if (c.getLife() instanceof Plant) {
                plants++;
            } else if (c.getLife() == null) {
                emptyCells.add(neighbours.indexOf(c));
                empty++;
            }
        }
        if (plants >= 2 && empty >= THREE) {
            int rand = RandomGenerator.nextNumber(empty - 1);
            int ind = emptyCells.get(rand);
            neighbours.get(ind).setPlant();

        }
    }

    /**
     * Helper method to advance herbivore.
     *
     * @param i    row of cell
     * @param j    col of cell.
     * @param life the life form to process (herbivore)
     */
    private void turnHerb(int i, int j, Life life) {
        ArrayList<Cell> neighbours = getCellAt(i, j).getAdjacentCells();

        ArrayList<Integer> emptyCells = new ArrayList<>();
        ArrayList<Integer> plantCells = new ArrayList<>();
        int plants = 0;
        int empty = 0;

        for (Cell c : neighbours) {
            if (c.getLife() instanceof Plant) {
                plantCells.add(neighbours.indexOf(c));
                plants++;

            } else if (c.getLife() == null) {
                emptyCells.add(neighbours.indexOf(c));
                empty++;

            }
        }

        if (plants >= 1) {
            int rand = RandomGenerator.nextNumber(plants);
            int plantToEat = plantCells.get(rand);
            ((Herbivore) life).eat(neighbours.get(plantToEat));

        } else if (empty >= 1) {
            int rand = RandomGenerator.nextNumber(empty);
            int emptyToMove = emptyCells.get(rand);
            life.move(this.getCellAt(i, j), neighbours.get(emptyToMove));

        }
    }

    /**
     * Reset move bool and increment turns of herbivores.
     */
    private void resetAndInc() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (cells[i][j].getLife() instanceof Herbivore) {
                    ((Herbivore) cells[i][j].getLife()).setCanMove(true);
                    ((Herbivore) cells[i][j].getLife()).incTurns();
                }
            }
        }
    }

    /**
     * Return rows of world.
     *
     * @return rows int
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Return cols of world.
     *
     * @return col int
     */
    public int getColumnCount() {
        return col;
    }

    /**
     * Returns the cell at the specified row,col.
     *
     * @param row  first index
     * @param colu second index
     * @return the cell at this index
     */
    public Cell getCellAt(int row, int colu) {
        return cells[row][colu];

    }
}
