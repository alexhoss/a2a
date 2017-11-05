package ca.bcit.comp2526.a2a;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Drives the program.
 *
 * @author alexhosseini
 * @version 1.0
 */
public final class Main {
    private static final Toolkit TOOLKIT;
    private static final float EIGHTY = 0.80f;
    private static final float HUNDRED = 100.0f;
    private static final int TWENTYFIVE = 25;

    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * Drives the game.
     */
    private Main() {
    }

    /**
     * Creates the game world and frame and initializes it.
     * @param argv arg
     */
    public static void main(final String[] argv) {
        final GameFrame frame;
        final World world;

        RandomGenerator.reset();
        world = new World(TWENTYFIVE, TWENTYFIVE);
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Sets frames position and size.
     *
     * @param frame the frame to set
     */
    private static void position(final GameFrame frame) {
        final Dimension size;

        size = calculateScreenArea(EIGHTY, EIGHTY);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     * Centres the frame on the screen upon run.
     *
     * @param size the size of the screen
     * @return the centre point
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2,
                (screenSize.height - size.height) / 2));
    }

    /**
     * Calculates the area of screen to take up.
     *
     * @param widthPercent  width
     * @param heightPercent height
     * @return the dimension of the screen area
     */
    public static Dimension calculateScreenArea(
            final float widthPercent, final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > HUNDRED)) {
            throw new IllegalArgumentException(
                    "widthPercent cannot be "
                            + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > HUNDRED)) {
            throw new IllegalArgumentException(
                    "heightPercent cannot be "
                            + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
