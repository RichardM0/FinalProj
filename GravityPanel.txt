
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.RadialGradientPaint;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;
public class GravityPanel extends JPanel implements ActionListener
{
   /*
    * Instance and Static variables for OrbiterPanel class
    * Includes timer, speed variables, motion variables, and arraylist for orbiters, 
    */
   private int delay = 10;
   private static final double G = 6.67430e-11;
   protected Timer timer;
   private ArrayList<Orbiter> orbiters = new ArrayList<Orbiter>();
   private ArrayList<Meteor> meteors = new ArrayList<Meteor>();
   private GravityPointer gp = new GravityPointer(90,90);
   private int orbitDist = 2*gp.getRadius()+10;
   private boolean isStopped = true;
   private int factor = 1;
   private int count = 0;
   private int yOffset = 0;
   private int gradRadius = 3*gp.getRadius()/4;
   public static String speed = "low";
   /*
    * constructor for objects of OrbiterPanel
    * create an "original" orbiter
    * Start a timer to keep refreshing screen
    */
   public GravityPanel()
   {
      timer = new Timer(delay, this);
      timer.start();
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
    * Paints every orbiter to the screen
    * Checks every orbiter's positions and boundaries to change dX, dY
    * Move the orbiter if stop button was not pressed
    */
   public void paintComponent( Graphics g )
   {
       super.paintComponent( g );
       
       Graphics2D g2d = (Graphics2D) g.create();
       //g2d.drawImage(gifIcon.getImage(), (int)gp.getX(), (int)gp.getY(), 2*gp.getRadius(), 2*gp.getRadius(), this);
       //g.fillOval((int)gp.getX(), (int)gp.getY(), gp.getRadius()*2, gp.getRadius()*2);
       Point2D center = new Point2D.Float((int)(gp.getX() + gp.getRadius()), (int)(gp.getY() + gp.getRadius()));
       float[] dist = {0.0f, 1.0f};
       Color[] colors = {new Color(217, 217, 217), Color.BLACK};
       RadialGradientPaint gradient = new RadialGradientPaint(center, gradRadius, dist, colors);

       g2d.setPaint(gradient);
       g2d.fill(new Ellipse2D.Double(gp.getX(), gp.getY(), 2*gp.getRadius(), 2*gp.getRadius()));
       
       meteorCollision();
       for(Meteor b : meteors){
           if(!isStopped){
               b.move();
           }
           g2d.setColor(new Color(102, 51, 0));
           g2d.fill(new Ellipse2D.Double((int)b.getX(), (int)b.getY(), b.getRadius()*2, b.getRadius()*2));
       }
       for(Orbiter b : orbiters){
           g.setColor(b.getColor());
           if(b.getX()>this.getWidth() - b.getRadius() || b.getX()<b.getRadius()){
               b.setX(b.getX()>this.getWidth() - b.getRadius() ? this.getWidth() - b.getRadius() : b.getRadius());
               b.setDx(-b.getDx());
           }
           if(b.getY()>this.getHeight() - b.getRadius() || b.getY()<0 + b.getRadius()){
               b.setY(b.getY()>this.getHeight() - b.getRadius() ? this.getHeight() - b.getRadius() : b.getRadius());
               b.setDy(-b.getDy());
           }
           if(!isStopped){
               b.move((int)gp.getX()+gp.getRadius() - 10, (int)gp.getY()+gp.getRadius() - 10, orbitDist);
               //b.setX(gp.getX()-b.getDistX());
               //b.setY(gp.getY()-b.getDistY());
           }
           g.fillOval((int)b.getX(), (int)b.getY(), b.getRadius()*2, b.getRadius()*2);
       }
       if(!isStopped){
           attraction();
       }
       g2d.dispose();
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
   }
   public void setOffset(int y){
       yOffset = y;
   }
   public void dragGP(int mX, int mY){
       if((mX>gp.getX() && mX<gp.getX()+2*gp.getRadius()) && (mY-yOffset>gp.getY() && mY-yOffset<gp.getY()+2*gp.getRadius())){
           gp.setX(mX-gp.getRadius());
           gp.setY(mY-gp.getRadius() - yOffset);
       }
   }
   public void reset(){
       orbiters.clear();
       meteors.clear();
       gp.setMass(1e14);
       gp.setRadius(35);
       orbitDist = 90;
   }
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
   public void meteorCollision(){
       try{
           for(Meteor b : meteors){
               double mX = b.getX();
               double mY = b.getY();
               int r = b.getRadius();
               if((mX+r>gp.getX() && mX+r<gp.getX()+2*gp.getRadius()) && (mY+r>gp.getY() && mY+r<gp.getY()+2*gp.getRadius())){
                   meteors.remove(b);
                   if(gp.getRadius()>60 || gp.getMass() > 5e14){
                       continue;
                   }
                   gp.setRadius(gp.getRadius() + 1);
                   gp.setMass(gp.getMass() + 1e13);
                   orbitDist += 1;
               }
           }
       }catch(Exception e){;}
   }
   /*
    * setStopped method and setStart method
    * setStopped stops motion
    * setStart starts motion
    */
   public void setStopped(){
       isStopped = true;
   }
   public void setStart(){
       isStopped = false;
   }
   public void addMeteor(){
       if(isStopped){
           return;
       }
       Random rand = new Random();
       int width = this.getWidth();
       int height = this.getHeight();
       int x = 30 + rand.nextInt(30 + 3*width/4);
       int y = 30 + rand.nextInt(30 + 3*height/4);
       while((x>gp.getX() - 2*gp.getRadius() && x<gp.getX() + 2*gp.getRadius()) || y>gp.getY() - 2*gp.getRadius() && y<gp.getY() + 2*gp.getRadius()){
           x = 30 + rand.nextInt(30 + 3*width/4);
           y = 30 + rand.nextInt(30 + 3*height/4);
       }
       Meteor m = new Meteor(x,y,20);
       meteors.add(m);
   }

   /*
    * addOrbiter method
    * Adds orbiter to the OrbiterPanel
    * Uses java.util.Random to randomize position and Color
    * Sets speed of orbiter according to the selected speed
    * add orbiter to arrayList orbiters
    */
   public void addOrbiter(){
        if(orbiters.size() >= 16 || isStopped){
            return;
        }
        int x = 0;
        int y = 0;
        Orbiter b = new Orbiter(x, y, 10, (int)gp.getX() - x, (int)gp.getY() - y);
        b.setColor(Color.red);
        orbiters.add(b);
   }
   public void gpTurn(){
        int width = this.getWidth();
        int height = this.getHeight();
        if(gp.getMoving() == 1 && gp.getX() >= width-orbitDist-2*gp.getRadius()){
            gp.setMoving(2);
        }
        else if(gp.getMoving() == 2 && gp.getY() >= height-orbitDist-2*gp.getRadius()){
            gp.setMoving(3);
        }
        else if(gp.getMoving() == 3 && gp.getX() <= orbitDist){
            gp.setMoving(4);
        }
        else if(gp.getMoving() == 4 && gp.getY() <= orbitDist){
            gp.setMoving(1);
        }
   }
}