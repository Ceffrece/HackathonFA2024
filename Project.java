import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Project extends JPanel implements MouseListener, MouseMotionListener {

    private BufferedImage mapImage;  // The map image to display
    private int mapX = 0;  // X position of the map
    private int mapY = 0;  // Y position of the map
    private Point lastMousePosition;  // Last mouse position to detect dragging

    public Project(){
        // Load or generate a placeholder map
        mapImage = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = mapImage.createGraphics();
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, mapImage.getWidth(), mapImage.getHeight());
        g.setColor(Color.BLACK);
        g.drawString("Map Center", mapImage.getWidth() / 2, mapImage.getHeight() / 2);
        g.dispose();

        // Add mouse listeners
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the map at the current offset
        g.drawImage(mapImage, mapX, mapY, this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePosition = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currentMousePosition = e.getPoint();
        int dx = currentMousePosition.x - lastMousePosition.x;
        int dy = currentMousePosition.y - lastMousePosition.y;
        mapX += dx;
        mapY += dy;
        lastMousePosition = currentMousePosition;

        // Repaint to reflect the new map position
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastMousePosition = null;
    }

    // Unused MouseListener and MouseMotionListener methods
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Map Game");
        Project mapGame = new Project();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mapGame);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
