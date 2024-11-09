import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//meant to just hold the buttons for accessing each map
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
               // mapGame.paint(getGraphics()); //
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
    protected ArrayList<Point> buildings; // List to store building positions
    private boolean buildAreaOccupied = false; // Track if a building is already added in the build area

    // Constants for the map boundaries and buildable area
    public static final int MAP_SIZE = 2000;
    private static final int VIEWPORT_WIDTH = 800;
    private static final int VIEWPORT_HEIGHT = 600;
    private static final Rectangle BUILD_AREA = new Rectangle(900, 900, 200, 200); // Central buildable area

    public MapGame(String title) {
        MainUserInterface mui = new MainUserInterface();
        //this.add(mui, GridLayout);
        
        setTitle(title);
        setSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buildings = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
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

        // Update mapX and mapY with boundaries
        mapX = Math.max(Math.min(mapX + dx, 0), VIEWPORT_WIDTH - MAP_SIZE);
        mapY = Math.max(Math.min(mapY + dy, 0), VIEWPORT_HEIGHT - MAP_SIZE);

        lastMousePosition = currentMousePosition;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastMousePosition = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int clickX = e.getX() - mapX;
        int clickY = e.getY() - mapY;

        // Check if the click is within the build area and it's not occupied
        if (BUILD_AREA.contains(clickX, clickY) && !buildAreaOccupied) {
            int result = JOptionPane.showConfirmDialog(this, "Would you like to add a building here?", 
                    "Build Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                buildings.add(new Point(BUILD_AREA.x + BUILD_AREA.width / 2, BUILD_AREA.y + BUILD_AREA.height / 2));
                buildAreaOccupied = true; // Mark the build area as occupied
                repaint();
            }
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        // Draw the build area with a border color based on occupancy
        g.setColor(buildAreaOccupied ? Color.RED : Color.GREEN); // Red if occupied, Green if available
        g.drawRect(BUILD_AREA.x + mapX - 1, BUILD_AREA.y + mapY - 1, BUILD_AREA.width + 2, BUILD_AREA.height + 2); // Border

        // Fill the build area with a distinct color if available
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(BUILD_AREA.x + mapX, BUILD_AREA.y + mapY, BUILD_AREA.width, BUILD_AREA.height);

        // Draw each building in the list
        for (Point building : buildings) {
            drawBuilding(g, building.x + mapX, building.y + mapY);
        }
    }

    // Method to draw a building at a specified location
    protected void drawBuilding(Graphics g, int x, int y) {
        g.setColor(Color.GRAY); // Building color
        g.fillRect(x - 15, y - 15, 30, 30); // Simple square building
        g.setColor(Color.BLACK);
        g.drawRect(x - 15, y - 15, 30, 30); // Building outline
    }
}