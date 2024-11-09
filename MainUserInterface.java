import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUserInterface extends JPanel{
    private JButton button;

    public MainUserInterface(){
        this.setLayout(new BorderLayout());

        JButton buildApartmentButton = new JButton("Apartment");
        JButton buildHouseButton = new JButton("House");
        JButton buildFactoryButton = new JButton("Factory");
        JButton buildSchoolButton = new JButton("School");

        this.add(buildApartmentButton);
        this.add(buildHouseButton);
        this.add(buildFactoryButton);
        this.add(buildSchoolButton);

        // Set the size of the window
        this.setPreferredSize(new Dimension(256,64));

         // Initialize UI components
         //this.initializeComponents();
         
 
         // Add event listeners
         //this.addEventListeners();

         this.setVisible(true);
    }  
}