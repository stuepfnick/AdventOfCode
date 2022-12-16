package y2022.day14;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class View {

    private JFrame frame;
    private final BufferedImage cave;

    private BufferStrategy bufferStrategy;

    public View(BufferedImage cave) {
        this.cave = cave;
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // Sim View
        frame = new JFrame("Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = (JPanel) frame.getContentPane();

        int WIDTH = cave.getWidth() * 4;
        int HEIGHT = cave.getHeight() * 4;

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

    public void render() {
        if (bufferStrategy == null) return; // View not ready yet
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.drawImage(cave, 0, 0, cave.getWidth() * 4, cave.getHeight() * 4, frame);
        g.dispose();
        bufferStrategy.show();
    }

}
