package visualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GameOfLife extends JFrame {

    public GameOfLife(int[] state) {
        Panel panel = new Panel(state);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }
}

class Panel extends JPanel implements ActionListener {

    private static final int RESOLUTION = 1000;
    private static final int SIDE = 100;
    private static final int N_SQUARES = SIDE * SIDE;
    private static final int DELAY = 50;
    private final int square;
    private final int decayStates = 10;
    private final Color[] colors = new Color[decayStates + 1];
    private int[] state;
    private int iteration = 0;
    private final Color alive = new Color(12, 199, 60);
    private final Color dead = new Color(9, 82, 33);


    public Panel(int[] state) {

        this.state = state;
        this.setPreferredSize(new Dimension(RESOLUTION, RESOLUTION + 12));
        Timer timer = new Timer(DELAY, this);
        timer.start();
        square = RESOLUTION / (int) Math.sqrt(N_SQUARES);
        setBackground(Color.BLACK);
        Color color = dead;
        for (int i = decayStates - 1; i >= 0; i--) {
            colors[i] = color;
            color = new Color(color.getRed() - dead.getRed() / 10, color.getGreen() - dead.getGreen() / 10, color.getBlue() - dead.getBlue() / 10);
        }
        colors[10] = alive;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x, y;
        for (int i = 0; i < N_SQUARES; i++) {
            x = (i % SIDE) * square;
            y = (i / SIDE) * square;
            g2d.setPaint(colors[state[i]]);
            g2d.fillOval(x, y, square, square);
        }
        int population = Arrays.stream(state).filter(s -> s == 10).toArray().length;
        g2d.setColor(alive);
        g2d.drawString(String.format("Population: %10d", population), 0, RESOLUTION + 10);
    }

    private int checkSquare(int i) {
        int neighbors = 0;
        neighbors += (i / SIDE != 0 && i % SIDE != 0) ? state[i - 1 - SIDE] / decayStates : 0;
        neighbors += (i / SIDE != 0) ? state[i - SIDE] / decayStates : 0;
        neighbors += (i / SIDE != 0 && i % SIDE != SIDE - 1) ? state[i + 1 - SIDE] / decayStates : 0;
        neighbors += (i % SIDE != 0) ? state[i - 1] / decayStates : 0;
        neighbors += (i % SIDE != SIDE - 1) ? state[i + 1] / decayStates : 0;
        neighbors += (i % SIDE != 0 && i / SIDE != SIDE - 1) ? state[i - 1 + SIDE] / decayStates : 0;
        neighbors += (i / SIDE != SIDE - 1) ? state[i + SIDE] / decayStates : 0;
        neighbors += (i / SIDE != SIDE - 1 && i % SIDE != SIDE - 1) ? state[i + 1 + SIDE] / decayStates : 0;
        return neighbors;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        iteration++;
        int[] next = new int[state.length];
        for (int i = 0; i < N_SQUARES; i++) {
            int n = checkSquare(i);
            if (state[i] == decayStates && (n == 2 || n == 3)) next[i] = decayStates;
            else if (state[i] != decayStates && n == 3) next[i] = decayStates;
            //else if (i == 0 || i == 99 || i == 100 * 99 || i == 100 * 100 -1) next[i] = 10; //part 2
            else next[i] = Math.max(state[i] - 1, 0);
        }
        state = next;
        repaint();
    }
}
