import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

class Map1 extends MapGame { // Desert Map

    public Map1() {
        super("Desert Map");
        MainUserInterface mui = new MainUserInterface();
        this.add(mui, BorderLayout.SOUTH);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Desert background color
        g.setColor(new Color(237, 201, 175)); // Sandy desert color
        g.fillRect(mapX, mapY, MAP_SIZE, MAP_SIZE);

        // Center label
        g.setColor(Color.BLACK);
        g.drawString("Desert Map Center", mapX + 1000, mapY + 1000);
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