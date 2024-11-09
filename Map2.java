import java.awt.Color;
import java.awt.Graphics;

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

        // Center label
        g.setColor(Color.BLACK);
        g.drawString("Winter Map Center", mapX + 1000, mapY + 1000);
    }
}
