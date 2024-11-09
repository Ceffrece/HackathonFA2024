import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class Map3 extends MapGame { // Spring Map: Turkey

    public Map3() {
        super("Spring Map");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Spring background color
        g.setColor(new Color(144, 238, 144)); // Light green grass color
        g.fillRect(mapX, mapY, 2000, 2000);

        // Draw flowers
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            int x = mapX + rand.nextInt(2000);
            int y = mapY + rand.nextInt(2000);
            drawFlower(g, x, y);
        }

        // Center label
        g.setColor(Color.BLACK);
        g.drawString("Spring Map Center", mapX + 1000, mapY + 1000);
    }

    private void drawFlower(Graphics g, int x, int y) {
        g.setColor(Color.PINK); // Flower color
        g.fillOval(x - 5, y - 5, 10, 10); // Flower petal

        g.setColor(Color.YELLOW); // Flower center
        g.fillOval(x - 2, y - 2, 4, 4);
    }
}