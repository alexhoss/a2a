package ca.bcit.comp2526.a2a;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TurnListener extends MouseAdapter {
    GameFrame gf;
    public TurnListener(GameFrame gameFrame) {
        gf = gameFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gf.takeTurn();

    }
}
