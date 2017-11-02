package ca.bcit.comp2526.a2a;

import java.awt.*;

public interface Life {

    void init();
    void setCell(Cell location);
    Color getBg();
    void move(Cell oldLocation, Cell location);
    void incTurns();


}
