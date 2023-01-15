package visualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {

    private Panel panel;

    public GameOfLife(int[] state) {
        this.panel = new Panel(state);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }
}

class Panel extends JPanel implements ActionListener {

    private static final int SIZE = 500;
    private static final int N_SQUARES = 100 * 100;
    private static final int DELAY = 25;
    private final int square;
    private final int side;
    private int[] state;
    private final Color alive = Color.yellow;
    private final Color dead = Color.blue;
    private Timer timer;

    public Panel(int[] state) {

        this.state = state;
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        timer = new Timer(DELAY, this);
        timer.start();
        square = SIZE / (int) Math.sqrt(N_SQUARES);
        side = (int) Math.sqrt(N_SQUARES);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int x, y;
        for (int i = 0; i < N_SQUARES; i++) {
            x = (i % side) * square;
            y = (i / side) * square;
            g2d.setPaint(state[i] == 1 ? alive : dead);
            g2d.fillRect(x, y, square, square);
        }
    }

    private int checkSquare(int i) {
        int neighbors = 0;
        neighbors += (i / side != 0 && i % side != 0) ? state[i - 1 - side] : 0;
        neighbors += (i / side != 0) ? state[i - side] : 0;
        neighbors += (i / side != 0 && i % side != side - 1) ? state[i + 1 - side] : 0;
        neighbors += (i % side != 0) ? state[i - 1] : 0;
        neighbors += (i % side != side - 1) ? state[i + 1] : 0;
        neighbors += (i % side != 0 && i / side != side - 1) ? state[i - 1 + side] : 0;
        neighbors += (i / side != side - 1) ? state[i + side] : 0;
        neighbors += (i / side != side - 1 && i % side != side - 1) ? state[i + 1 + side] : 0;
        return neighbors;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int[] next = new int[state.length];
        for (int i = 0; i < N_SQUARES; i++) {
            int n = checkSquare(i);
            if (state[i] == 1 && (n == 2 || n == 3)) next[i] = 1;
            if (state[i] == 0 && n == 3) next[i] = 1;
        }
        state = next;
        repaint();
    }
}
