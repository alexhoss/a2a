package ca.bcit.comp2526.a2a;

import java.awt.*;

/**
 * Herbivore class represents a herbivore
 */
public class Herbivore implements Life {
     int turns = 0;
     boolean canMove = true;

    private Cell location;

    //TODO
    public Herbivore(Cell location) {
        this.location = location;
    }


    public void setCell(Cell location) {
        this.location = location;
    }


    public void move(Cell oldLocation, Cell newLocation) {
        this.location = newLocation;
        newLocation.setLife(this);

        //this.turns = 0;
        oldLocation.getLife();//.setTurns(0);
        oldLocation.removeLife();
        this.canMove = false;



    }

    /**
     * Eat plant in location
     * @param location
     */
    public void eat(Cell location) {
        location.removeLife();
        this.turns = 0;
        this.move(this.location, location);

    }

    public void incTurns(){
        this.turns++;
    }
    public int getTurns(){
        return turns;
    }
    public void setTurns(int a){
        turns = a;
    }

}


