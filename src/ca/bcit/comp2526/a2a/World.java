package ca.bcit.comp2526.a2a;

import java.io.Serializable;

/**
 * Class to represent a world.
 *
 * @author alexhosseini
 * @version 1.0
 */
public class World implements Serializable {
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
     * Method to kill all Life forms.
     */
    public void kill() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (cells[i][j].getLife() != null) {
                    cells[i][j].removeLife();
                    cells[i][j].repaint();
                }
            }
        }

    }

    /**
     * Method to reset game state.
     */
    public void reset() {
        RandomGenerator.reset();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                cells[i][j].init();

            }
        }
    }


    /**
     * Method to load a saved game state.
     *
     * @param loadCells the cells saved
     */
    public void loadGame(Cell[][] loadCells) {
        this.kill();
        RandomGenerator.reset();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (loadCells[i][j].getLife() != null) {
                    cells[i][j].setLife(loadCells[i][j].getLife());
                    cells[i][j].getLife().setLocation(cells[i][j]);
                }
                cells[i][j].repaint();
            }
        }
    }

    /**
     * Method to load a game state using the doubly linked list.
     * @param loadCells doubley linked list cells
     */
    public void loadGameLL(DoublyLinkedList<Cell> loadCells) {
        this.kill();
        RandomGenerator.reset();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                try {
                    cells[i][j].setLife(loadCells.removeFromFront().getLife());
                    if (cells[i][j].getLife() != null) {
                        cells[i][j].getLife().setLocation(cells[i][j]);
                    }
                } catch (CouldNotRemoveException e) {
                    e.printStackTrace();
                }

                cells[i][j].repaint();
            }

        }
    }


    /**
     * Advance world state.
     */
    public void takeTurn() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (getCellAt(i, j).getLife() != null) {
                    final Life life = getCellAt(i, j).getLife();

                    if (life instanceof Animal) {

                        final Animal animal = (Animal) life;
                        if (animal.getTurns() == animal.getDeath()) {
                            getCellAt(i, j).removeLife();
                            continue;
                        }
                        if (!(animal.isCanMove())) {
                            continue;
                        }
                        animal.takeTurn();


                    } else if (life instanceof Plant) {
                        ((Plant) life).takeTurn();


                    }
                }
            }
        }
        resetAndInc();


    }

    /**
     * Return cells of the world.
     *
     * @return cells
     */
    public Cell[][] getCells() {
        return cells;
    }


    /**
     * Reset move bool and increment turns of herbivores.
     */
    private void resetAndInc() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                Life life = cells[i][j].getLife();
                if (life != null) {
                    life.incTurns();
                    life.setCanMove(true);
                    life.setCanBirth(true);
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
