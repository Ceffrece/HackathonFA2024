import java.awt.Color;
import java.awt.Graphics;

class Map2 extends MapGame {
    //Russia

    public Map2() {
        super("Map 2");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.PINK);
        g.fillRect(mapX, mapY, 2000, 2000); // Large pink background
        g.setColor(Color.BLACK);
        g.drawString("Map 2 Center", mapX + 1000, mapY + 1000);
    }
}