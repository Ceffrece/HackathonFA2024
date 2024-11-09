import java.awt.Color;
import java.awt.Graphics;

class Map1 extends MapGame {

    public Map1() {
        super("Map 1");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.CYAN);
        g.fillRect(mapX, mapY, 2000, 2000); // Large cyan background
        g.setColor(Color.BLACK);
        g.drawString("Map 1 Center", mapX + 1000, mapY + 1000);
    }
}