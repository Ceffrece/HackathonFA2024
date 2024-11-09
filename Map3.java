import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class Map3 extends MapGame { // Spring Map

    public Map3() {
        super("Spring Map");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Spring background color
        g.setColor(new Color(144, 238, 144)); // Light green grass color
        g.fillRect(mapX, mapY, 2000, 2000);

        // Center label
        g.setColor(Color.BLACK);
        g.drawString("Spring Map Center", mapX + 1000, mapY + 1000);
    }
}