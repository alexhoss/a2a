package ca.bcit.comp2526.a2a;

import javax.swing.*;
import java.awt.*;

/**
 * Class to represent a plant
 * @author alexhosseini
 * @version 1.0
 */
public class Plant extends JPanel implements Life  {
    private Cell location;


    public Plant(Cell location) {
        this.location = location;

    }

    public void setCell(Cell location) {
        this.location = location;

     }


    @Override
    public void move(Cell oldLocation, Cell location) {

    }



}

