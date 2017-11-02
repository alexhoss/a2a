package ca.bcit.comp2526.a2a;

import java.awt.*;
import java.time.chrono.HijrahEra;
import java.util.ArrayList;
import java.util.Arrays;

public class World {
    private Cell[][] cells;
    private int rows;
    private int col;

    public World(int rows,int col){
        this.rows = rows;
        this.col = col;
    };
    //TODO: Puts cells on world
    public void init(){
        cells = new Cell[rows][col];
        for (int i = 0; i<rows;i++){
            for(int j = 0; j<col; j++){
                Cell k = new Cell(this,i,j);
                k.init();
                cells[i][j] = k;

            }
        }
    };
    //TODO:kill herbivores and seed and move
    public void takeTurn() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {

                if (getCellAt(i, j).getLife() instanceof Herbivore) {
                    //System.out.println( ((Herbivore) getCellAt(i,j).getLife()).getTurns());
                    ((Herbivore) getCellAt(i,j).getLife()).incTurns();
                    System.out.println(((Herbivore) getCellAt(i,j).getLife()).getTurns());
//                    if(((Herbivore) getCellAt(i,j).getLife()).getTurns() >= 10){
//                     //   getCellAt(i,j).removeLife();
//                        System.out.println(((Herbivore) getCellAt(i,j).getLife()).getTurns());
//                    }
                    ArrayList<Cell> neighbours = getCellAt(i, j).getAdjacentCells();

                    int rand = RandomGenerator.nextNumber(8);


                    if(neighbours.size() > rand && neighbours.get(rand).getLife() instanceof Plant)
                        ((Herbivore) getCellAt(i,j).getLife()).eat(neighbours.get(rand));
                    else if (neighbours.size() > rand && !(neighbours.get(rand).getLife() instanceof Herbivore) )
                        getCellAt(i,j).getLife().move(this.getCellAt(i,j), neighbours.get(rand));


                }
                else if (getCellAt(i, j).getLife() instanceof Plant) {
                    int empty = 0;
                    int plants = 0;
                    ArrayList<Integer> emptyCells = new ArrayList<>();
                    ArrayList<Cell> neighbours = getCellAt(i, j).getAdjacentCells();
                    for(Cell c : neighbours){
                        if (c.getLife() instanceof Plant)  plants++;
                        else if (c.getLife() == null) {
                            emptyCells.add(neighbours.indexOf(c));
                            empty++;
                        }
                    }
                    if(plants >= 2 && empty >= 3){
                        int rand = RandomGenerator.nextNumber(empty-1);
                        int ind =  emptyCells.get(rand);
                        neighbours.get(ind).setLife("p");

                    }
                }
            }
        }
    }


    public int getRowCount(){
        return rows;
    };
    public int getColumnCount(){
        return col;
    };

    public Cell getCellAt(int row, int col){
        return cells[row][col];

    }
}
