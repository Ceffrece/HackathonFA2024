import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MainMenuScreen extends JFrame implements ActionListener {

    public MainMenuScreen() {
        setTitle("Map Game Main Menu");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel titleLabel = new JLabel("Select a Map", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);

        // Add buttons for each map
        JButton map1Button = new JButton("Map 1");
        JButton map2Button = new JButton("Map 2");
        JButton map3Button = new JButton("Map 3");
        map1Button.addActionListener(this);
        map2Button.addActionListener(this);
        map3Button.addActionListener(this);

        panel.add(map1Button);
        panel.add(map2Button);
        panel.add(map3Button);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        MapGame mapGame;

        switch (command) {
            case "Map 1":
                mapGame = new Map1();
                break;
            case "Map 2":
                mapGame = new Map2();
                break;
            case "Map 3":
                mapGame = new Map3();
                break;
            default:
                return;
        }

        mapGame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuScreen mainMenu = new MainMenuScreen();
            mainMenu.setVisible(true);
        });
    }
}

abstract class MapGame extends JFrame implements MouseListener, MouseMotionListener {

    protected int mapX = 0;  // X position of the map
    protected int mapY = 0;  // Y position of the map
    protected Point lastMousePosition;

    public MapGame(String title) {
        setTitle(title);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
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

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastMousePosition = null;
    }

    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}