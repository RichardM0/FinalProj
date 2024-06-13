import java.awt.event.*;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GravityFrame extends JFrame implements ActionListener
{
   /*
    * Static variables for class OrbiterFrame
    * Each individual Java Swing element
    */
   public static JFrame frame;
   private static GravityPanel bp;
   private static JPanel subPanel; 
   private static JButton addMeteors, reset, help;
   /*
   * constructor for objects of OrbiterFrame
   * Creates entire window
   * Add the GravityPanel with Orbiter, meteors, GravityPointer
   * Have a subpanel with buttons addMeteor and reset to add meteor or reset frame
   * Add frame to the window
   */
   public GravityFrame()
   {
        frame = new JFrame( "Gravity Simulation" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );    
        bp = new GravityPanel();
        subPanel = new JPanel();
        subPanel.setBackground(Color.gray);
    
        addMeteors = new JButton("Add Meteors");
        reset = new JButton("Reset");
        help = new JButton("Instructions");
        
        addMeteors.addActionListener(this);
        reset.addActionListener(this);
        help.addActionListener(this);
        subPanel.add(addMeteors);
        subPanel.add(reset);
        subPanel.add(help);
        frame.addMouseMotionListener(new MouseAdapter() {  
          public void mouseDragged(MouseEvent me){
              bp.dragGP(me.getX(), me.getY());
          }
        }); 

        frame.getContentPane().add(subPanel, BorderLayout.SOUTH);
        bp.setBackground(Color.darkGray);
        frame.add( bp );
        frame.setSize(900, 720); 
        frame.setVisible( true );
   }
   /*
    * main Method
    * Allows the user to access and view the GUI
    */
   public static void main( String args[] )
   {
        GravityFrame run = new GravityFrame();
        int offset = subPanel.getHeight();
        bp.setOffset(offset);
   }
   /*
    * actionPerformed method
    * Listens for which button was pressed:
    * addMeteors button adds meteors
    * reset resets frame to default settings
    */
   public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addMeteors){
            bp.addMeteor();
        }
        else if(e.getSource() == reset){
            bp.reset();
        }
        else if(e.getSource() == help){
            JOptionPane.showMessageDialog(frame, "Meteors attract to pulsing star.\nClick add Meteors to add a meteor\nClick reset to return objects to default state.\nHold mouse on star to move around");
        }
   }
}
