import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class Map1 extends MapGame { // Desert Map

    public Map1() {
        super("Desert Map");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Desert background color
        g.setColor(new Color(237, 201, 175)); // Sandy desert color
        g.fillRect(mapX, mapY, 2000, 2000);

        // Draw random cacti
        g.setColor(new Color(34, 139, 34)); // Cactus green color
        Random rand = new Random();
        for (int i = 0; i < 15; i++) {
            int x = mapX + rand.nextInt(2000);
            int y = mapY + rand.nextInt(2000);
            drawCactus(g, x, y);
        }

        // Center label
        g.setColor(Color.BLACK);
        g.drawString("Desert Map Center", mapX + 1000, mapY + 1000);
    }

    private void drawCactus(Graphics g, int x, int y) {
        g.fillRect(x, y - 30, 10, 30); // Main cactus trunk
        g.fillRect(x - 10, y - 20, 10, 10); // Left arm
        g.fillRect(x + 10, y - 20, 10, 10); // Right arm
    }
}
