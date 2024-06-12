import java.awt.Color;
public class Orbiter
{
    /*
     * Instance variables for class Ball
     * Variables have basic properties of the ball
     */
    private double x, y, dx, dy;
    private int radius, distX, distY;
    private double mass;
    private Color color = Color.red;
    private double angle;
    private static double angleCounter = 0;

    /*
    * constructor for objects of class Ball
    * Starts a timer at when it was created 
    */
 
    public Orbiter(int x, int y, int radius, int distX, int distY){
        this.x = x;
        this.y = y;
        dx = 3.0;
        dy = 3.0;
        mass = 25;
        this.radius = radius;
        this.distX = distX;
        this.distY = distY;
        angle = angleCounter;
        angleCounter += Math.PI/8;
    }
    /*
     * Getter and Setter methods for class Ball
     * Get and set basic properties of Ball
     * Getter for X position, Y position, change in X and Y, color, time, and radius
     * Setter for X position, Y position, change in X and Y, and color
     */
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getDx(){
        return dx;
    }
    public double getDy(){
        return dy;
    }
    public Color getColor(){
        return color;
    }
    public int getRadius(){
        return radius;
    }
    public double getMass(){
        return mass;
    }
    public void setX(double a){
        x = a;
    }
    public void setY(double a){
        y = a;
    }
    public void setDx(double a){
        dx = a;
    }
    public void setDy(double a){
        dy = a;
    }
    public void setColor(Color c){
        color = c;
    }
    public void updateVel(double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        
        dx += ax;
        dy += ay;
    }
    public void move(int cx, int cy, int r){
       x = cx + r*Math.cos(angle);
       y = cy + r*Math.sin(angle);
    }
    public int getDistX(){
        return distX;
    }
    public int getDistY(){
        return distY;
    }
}