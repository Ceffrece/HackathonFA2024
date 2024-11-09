import java.awt.Color;
import java.awt.Graphics;

class Map3 extends MapGame {
    //Turkey

    public Map3() {
        super("Map 3");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(mapX, mapY, 2000, 2000); // Large gray background
        g.setColor(Color.BLACK);
        g.drawString("Map 3 Center", mapX + 1000, mapY + 1000);
    }
}