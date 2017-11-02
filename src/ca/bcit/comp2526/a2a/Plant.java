package ca.bcit.comp2526.a2a;

import javax.swing.*;
import java.awt.*;

public class Plant extends JPanel implements Life  {
    private Color bg = Color.GREEN;
    private Point p;
    //TODO:

    public Plant(Cell location){
        Point loc = location.getLocation();
        p = loc;
    }

    public void init(){
        setBackground(bg);
    };
    public void setCell(Cell location){
        Point loc = location.getLocation();
        p = loc;
     }

    @Override
    public String toString() {
        return "Plant{" +
                "p=" + p +
                '}';
    }

    public Color getBg() {
        return bg;
    }

    @Override
    public void move(Cell oldLocation, Cell location) {

    }

    @Override
    public void incTurns() {

    }


}

