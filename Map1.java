import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class Map1 extends MapGame { // Desert Map

    public Map1() {
        super("Desert Map");
        MainUserInterface mui = new MainUserInterface();
        //this.add(mui, BorderLayout.CENTER); 

       // MainUserInterface gUI = new MainUserInterface();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Desert background color
        g.setColor(new Color(237, 201, 175)); // Sandy desert color
        g.fillRect(mapX, mapY, 2000, 2000);

        // Center label
        g.setColor(Color.BLACK);
        g.drawString("Desert Map Center", mapX + 1000, mapY + 1000);
    }
}
