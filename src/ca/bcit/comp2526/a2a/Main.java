package ca.bcit.comp2526.a2a;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private static final int GAMEBOUNDS = 25;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 300;
    private static final int MIN = 100;
    private static final int MAX = 1000;
    private static final int VALUE = 500;
    private static final int SPACING = 200;
    private static File saves = new File("saves.txt");
    private static DoublyLinkedList<Cell> saveState = new DoublyLinkedList<>();


    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * Drives the game.
     */
    private Main() {
    }

    /**
     * Creates the frame and start the game up.
     *
     * @param argv args
     * @throws Exception thrown if runtime exceptions caught
     */
    public static void main(final String[] argv) throws Exception {
        final GameFrame frame;
        final World world;
        Frame f = new JFrame();

        RandomGenerator.reset();
        world = new World(GAMEBOUNDS, GAMEBOUNDS);
        world.init();
        frame = new GameFrame(world);
        position(frame);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, VALUE);

        Timer startTime = new Timer(slider.getValue(), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.takeTurn();
            }
        });
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                startTime.setDelay(source.getValue());
            }
        });
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame(world);


            }
        });

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loadGame(world);
            }
        });


        JButton startButton = new JButton("start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime.start();
            }
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime.stop();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.kill();
                world.reset();
            }
        });
        setFrame(f, slider, saveButton, loadButton,
                startButton, stopButton, resetButton);
        frame.init();
        f.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        f.setVisible(true);

    }

    /**
     * Add buttons and other options.
     *
     * @param f           frame to set
     * @param slider      slide to add
     * @param saveButton  slidebutton to add
     * @param loadButton  load button to add
     * @param startButton start button to add
     * @param stopButton  stop button
     * @param resetButton reset button
     */
    private static void setFrame(Frame f, JSlider slider,
                                 JButton saveButton, JButton loadButton,
                                 JButton startButton,
                                 JButton stopButton, JButton resetButton) {
        f.setLayout(new GridLayout());
        slider.setMajorTickSpacing(SPACING);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        f.add(slider);
        f.add(startButton);
        f.add(stopButton);
        f.add(resetButton);
        f.add(saveButton);
        f.add(loadButton);


    }


    /**
     * Helper method to load a game.
     *
     * @param world world to load in
     */
    private static void loadGame(World world) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(saves);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            world.loadGameLL((DoublyLinkedList<Cell>) ois.readObject());
            System.out.println("gamed loaded");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Helper method to save a game state.
     *
     * @param world world to save
     */
    private static void saveGame(World world) {
        saveState = null;
        saveState = new DoublyLinkedList<Cell>();
        for (int i = 0; i < world.getRowCount(); i++) {
            for (int j = 0; j < world.getColumnCount(); j++) {
                try {
                    saveState.addToEnd(world.getCellAt(i, j));
                } catch (CouldNotAddException e) {
                    e.printStackTrace();
                }
            }
        }
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(saves);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            oos.writeObject(saveState);
            System.out.println("game saved");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
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
