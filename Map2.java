import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

class Map2 extends MapGame { // Winter Map

    public Map2() {
        super("Winter Map");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Winter background color
        g.setColor(new Color(220, 240, 255)); // Light blue for snow effect
        g.fillRect(mapX, mapY, MAP_SIZE, MAP_SIZE);

        // Center label
        g.setColor(Color.BLACK);
        g.drawString("Winter Map Center", mapX + 1000, mapY + 1000);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }
}
