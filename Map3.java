import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

class Map3 extends MapGame { // Spring Map

    public Map3() {
        super("Spring Map");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Spring background color
        g.setColor(new Color(144, 238, 144)); // Light green grass color
        g.fillRect(mapX, mapY, MAP_SIZE, MAP_SIZE);
    
        // Center label
        g.setColor(Color.BLACK);
        g.drawString("Spring Map Center", mapX + 1000, mapY + 1000);
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