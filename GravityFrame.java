

import java.awt.event.*;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GravityFrame extends JFrame implements ActionListener
{
   /*
    * Static variables for class OrbiterFrame
    * Each individual Java Swing element
    */
   public static JFrame frame;
   private static GravityPanel bp;
   private static JPanel subPanel; 
   private static JButton start, stop, addOrbiters, addMeteors, reset;
   /*
   * constructor for objects of OrbiterFrame
   * Creates entire window
   * Create menu with speed, gravity, and motion options
   * Add the OrbiterPanel with the actual moving orbiters
   * Have a subpanel with buttons Start Stop and Add Orbiters to change orbiters
   * Add frame to the window
   */
   public GravityFrame()
   {
        frame = new JFrame( "Gravity" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );    
        bp = new GravityPanel();
        subPanel = new JPanel();
        subPanel.setBackground(Color.gray);
    
        start = new JButton("Start");
        stop = new JButton("Stop");
        addOrbiters = new JButton("Add Orbiters");
        addMeteors = new JButton("Add Meteors");
        reset = new JButton("Reset Frame");
 
        stop.addActionListener(this);
        start.addActionListener(this);
        addOrbiters.addActionListener(this);
        addMeteors.addActionListener(this);
        reset.addActionListener(this);

        subPanel.add(start);
        subPanel.add(stop);
        subPanel.add(addOrbiters);
        subPanel.add(addMeteors);
        subPanel.add(reset);
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
    * Stop button stops the orbiterPanel
    * Start resumes the orbiterPanel action
    * addOrbiters button adds orbiter to the orbiterPanel
    */
   public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stop){
            bp.setStopped();
        }
        else if (e.getSource() == start){
            bp.setStart();
        }
        else if(e.getSource() == addOrbiters){
            bp.addOrbiter();
        }
        else if(e.getSource() == addMeteors){
            bp.addMeteor();
        }
        else if(e.getSource() == reset){
            bp.reset();
        }
   }
}