package ca.bcit.comp2526.a2a;

import java.awt.*;

public class Herbivore implements Life {
    private Color bg = Color.YELLOW;
     int turns = 0;
    private Cell location;

    //TODO
    public Herbivore(Cell location) {
        this.location = location;
    }

    public void init() {
        bg = Color.YELLOW;
    }

    ;

    public void setCell(Cell location) {
        this.location = location;
    }

    @Override
    public Color getBg() {
        return bg;
    }

    ;

    public void move(Cell oldLocation, Cell newLocation) {

       // Cell old = this.location;


        //oldLocation.life = null;

        //this spot -> new spot
        this.location = newLocation;
        newLocation.life = this;
        //newLocation.life = this;
        oldLocation.removeLife();



    }

    ;


    public void eat(Cell location) {


        //System.out.println(location.toString() + " was eaten");
        location.removeLife();

        this.move(this.location, location);

    }

    public void incTurns(){
        this.turns++;
    }
    public int getTurns(){
        return turns;
    }

    ;
}


