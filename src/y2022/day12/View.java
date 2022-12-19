package y2022.day12;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class View {

    private JFrame frame;
    private final BufferedImage background;
    private BufferedImage visited;
    private final int WIDTH, HEIGHT;

    private BufferStrategy bufferStrategy;

    public View(BufferedImage background, BufferedImage visited) {
        this.background = background;
        this.visited = visited;

        WIDTH = background.getWidth() * 4;
        HEIGHT = background.getHeight() * 4;

        createAndShowGUI();
    }

    public void setVisited(BufferedImage visited) {
        this.visited = visited;
    }

    private void createAndShowGUI() {
        // Sim View
        frame = new JFrame("Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = (JPanel) frame.getContentPane();

        panel.setSize(WIDTH, HEIGHT);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        Canvas canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
    }

    public void render(double time) {
        sleep(time);
        //if (bufferStrategy == null) return; // View not ready yet
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.drawImage(background, 0, 0, background.getWidth() * 4, background.getHeight() * 4, frame);
        g.drawImage(visited, 0, 0, background.getWidth() * 4, background.getHeight() * 4, frame);
        g.dispose();
        bufferStrategy.show();
    }

    private void sleep(double seconds) {
        try {
            Thread.sleep(Math.round(seconds * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
