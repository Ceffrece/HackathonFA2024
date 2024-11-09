import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class Map2 extends MapGame { // Winter Map

    public Map2() {
        super("Winter Map");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Winter background color
        g.setColor(new Color(220, 240, 255)); // Light blue for snow effect
        g.fillRect(mapX, mapY, 2000, 2000);

        // Draw snowflakes
        g.setColor(Color.WHITE);
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int x = mapX + rand.nextInt(2000);
            int y = mapY + rand.nextInt(2000);
            g.fillOval(x, y, 5, 5); // Small circles as snowflakes
        }

        // Center label
        g.setColor(Color.BLACK);
        g.drawString("Winter Map Center", mapX + 1000, mapY + 1000);
    }
}