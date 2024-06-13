import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.RadialGradientPaint;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
public class GravityPanel extends JPanel implements ActionListener
{
   /*
    * Instance and Static variables for OrbiterPanel class
    * Includes timer, gravitational constant, objects to paint, animation variables, and coordinate variables. 
    */
   private int delay = 10;
   private final double G = 6.67430e-11;
   protected Timer timer;
   private ArrayList<Meteor> meteors = new ArrayList<Meteor>();
   private GravityPointer gp = new GravityPointer(90,90);
   private Orbiter orbiter = new Orbiter(0, 0, 10);
   private int factor = 1;
   private int count;
   private int yOffset;
   private int gradRadius;
   /*
    * constructor for objects of OrbiterPanel
    * Start a timer to keep refreshing screen
    * initialize gradRadius used to create pulsing effect
    */
   public GravityPanel()
   {
      timer = new Timer(delay, this);
      timer.start();
      gradRadius = 3*gp.getRadius()/4;
   }
   /*
    * actionPerformed method
    * repaints orbiter to screen constantly
    */
   public void actionPerformed(ActionEvent e)
   {
       repaint();
   }
   /*
    * paintComponent method
    * Paints every element to the screen
    * Pulsing animation displayed for GravityPointer
    * runs all collisions, movements, etc.
    */
   public void paintComponent( Graphics g )
   {
       super.paintComponent( g );
       
       Graphics2D g2d = (Graphics2D) g.create();
       Point2D center = new Point2D.Float((int)(gp.getX()+gp.getRadius()), (int)(gp.getY() + gp.getRadius()));
       float[] dist = {0.0f, 1.0f};
       Color[] colors = {new Color(207, 207, 207), Color.darkGray};
       RadialGradientPaint gradient = new RadialGradientPaint(center, gradRadius, dist, colors);

       g2d.setPaint(gradient);
       g2d.fill(new Ellipse2D.Double(gp.getX(), gp.getY(), 2*gp.getRadius(), 2*gp.getRadius()));

       Color[] colors2 = {new Color(50, 25, 0), new Color(80, 40, 0)};
       
       for(Meteor b : meteors){
           center = new Point2D.Float((int)(b.getX()+b.getRadius()), (int)(b.getY() + b.getRadius()));
           gradient = new RadialGradientPaint(center, (int)(1.5*b.getRadius()), dist, colors2);
           b.move();
           g2d.setPaint(gradient);
           g2d.fill(new Ellipse2D.Double(b.getX(), b.getY(), 2*b.getRadius(), 2*b.getRadius()));
       }
      
       orbiter.move((int)(gp.getX() + gp.getRadius() - orbiter.getRadius()), (int)(gp.getY() + gp.getRadius() - orbiter.getRadius()), 2*gp.getRadius());

       center = new Point2D.Float((int)(orbiter.getX()+orbiter.getRadius()), (int)(orbiter.getY() + orbiter.getRadius()));
       Color[] colors3 = {new Color(120, 120, 120), Color.black};
       gradient = new RadialGradientPaint(center, orbiter.getRadius(), dist, colors3);
       g2d.setPaint(gradient);
       g2d.fill(new Ellipse2D.Double((int)orbiter.getX(), (int)orbiter.getY(), orbiter.getRadius()*2, orbiter.getRadius()*2));
       g2d.dispose();

       g.setFont(new Font("Serif", Font.BOLD, 16));
       g.setColor(Color.white);
       g.drawString("Click reset to return to starting traits", this.getWidth()-270, 30);
       g.drawString("Click and drag star to move", this.getWidth()-270, 60);
       if(count%7==0){
           gradRadius+=factor;
       }
       if(gradRadius>=gp.getRadius()){
           factor = -1;
       }
       else if(gradRadius<3*gp.getRadius()/4){
           factor = 1;
       }
       count++;
       meteorCollision();
       attraction();
   }
   /*
    * setOffset dragGP, and reset method
    * setOffset inputs the subPanel height and passes it into this class for calculations
    * dragGP allows user to click and drag gravity pointer to move
    * reset clears all meteors, resets gravity pointer to default
    */
   public void setOffset(int y){
       yOffset = y;
   }
   public void dragGP(int mX, int mY){
       boolean outsideOfScreen = (mX<gp.getRadius() || mX>this.getWidth()-gp.getRadius() || mY<2*gp.getRadius() || mY>this.getHeight());

       if((mX>gp.getX() && mX<gp.getX()+2*gp.getRadius()) && (mY-yOffset>gp.getY() && mY-yOffset<gp.getY()+2*gp.getRadius()) && !outsideOfScreen){
           gp.setX(mX-gp.getRadius());
           gp.setY(mY-gp.getRadius() - yOffset);
       }
   }
   public void reset(){
       meteors.clear();
       gp.setMass(1e14);
       gp.setRadius(35);
   }
   /*
    * attraction method
    * loops through each meteor and applys Newton's law of gravitational force
    * Uses distance formula to get distance between meteor and gravity pointer
    * updates velocity of each meteor (acceleration)
    */
   public void attraction(){
       for(Meteor b : meteors){
           double dx = (gp.getX()+gp.getRadius())-(b.getX()+b.getRadius());
           double dy = (gp.getY()+gp.getRadius())-(b.getY()+b.getRadius());
           double distance = Math.sqrt(dx*dx + dy*dy);
           double fx = 0;
           double fy = 0;

           if (distance > b.getRadius() + gp.getRadius()) { 
               double force = (G * b.getMass() * gp.getMass()) / (distance * distance);
               fx += force * (dx / distance);
               fy += force * (dy / distance);
           }
           b.updateVel(fx, fy);
       }
   }
   /*
    * meteorCollision method
    * loops through each meteor and checks if its within the boundaries of GravityPointer
    * if it does collide, increase radius and mass, then delete the meteor
    */
   public void meteorCollision(){
       try{
           for(Meteor b : meteors){
               double mX = b.getX();
               double mY = b.getY();
               int r = b.getRadius();
               if((mX+r>gp.getX() && mX+r<gp.getX()+2*gp.getRadius()) && (mY+r>gp.getY() && mY+r<gp.getY()+2*gp.getRadius())){
                   meteors.remove(b);
                   if(gp.getRadius()>70 || gp.getMass() > 5e14){
                       continue;
                   }
                   gp.setRadius(gp.getRadius() + 1);
                   gp.setMass(gp.getMass() + 1e12);
               }
           }
       }catch(Exception e){;}
   }
   /*
    * addMeteor method
    * when clicked on add Meteor button, adds a meteor to screen
    * random position away from the Gravity Pointer
    * adds newly created meteor to meteors ArrayList
    */
   public void addMeteor(){
       Random rand = new Random();
       int width = this.getWidth();
       int height = this.getHeight();
       int x = 30 + rand.nextInt(30 + 3*width/4);
       int y = 30 + rand.nextInt(30 + 3*height/4);
       while((x>gp.getX() - 2*gp.getRadius() && x<gp.getX() + 4*gp.getRadius()) || y>gp.getY() - 2*gp.getRadius() && y<gp.getY() + 4*gp.getRadius()){
           x = 30 + rand.nextInt(30 + 3*width/4);
           y = 30 + rand.nextInt(30 + 3*height/4);
       }
       Meteor m = new Meteor(x,y,20);
       meteors.add(m);
   }

}