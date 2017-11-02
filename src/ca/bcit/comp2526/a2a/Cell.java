package ca.bcit.comp2526.a2a;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Cell extends JPanel {
    private Point p;
    World world;
    Type type;
    Life life;


    public static ArrayList plants = new ArrayList();
    private static ArrayList herbivores = new ArrayList();

    private static HashMap Worldcells = new HashMap<Point, Cell>();

    public Cell(World world, int row, int col) {
        this.world = world;
        p = new Point(row, col);
        setBackground(Color.white);
    }

    public void init() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        int rand = RandomGenerator.nextNumber(100);

        if (rand >= 80) {
            this.life = new Herbivore(this);



        } else if (rand >= 50) {
            this.life = new Plant(this);


        }
        Worldcells.put(this.getLocation(), this);


    }

    @Override
    public String toString() {
        return "Cell{" +
                "p=" + p +
                ", life=" + life +
                '}';
    }


    public Point getLocation() {
        return p;
    }

    ;

    //TODO: Return adjacent cells(3 f corner, 5 f sides, else 8)

    public ArrayList<Cell> getAdjacentCells() {
        ArrayList<Cell> cells = new ArrayList<>();
        int x = this.getLocation().x;
        int y = this.getLocation().y;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                Point loc = new Point(i, j);
                if (Worldcells.get(loc) == null) continue;
                cells.add((Cell) Worldcells.get(loc));
            }
            cells.remove(this);
        }
        return cells;

    }

    public void setLife(String type) {
        if (type.equals("p")) life = new Plant(this);
        else life = new Herbivore(this);
    }

    ;

    public Life getLife() {
        return life;
    }


    public void removeLife() {
        life = null;


    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.getLife() instanceof Plant){
            this.setBackground(Color.GREEN);
        }
        else if (this.getLife() instanceof Herbivore){
            this.setBackground(Color.YELLOW);
        }
        else this.setBackground(Color.white);

    }
}
