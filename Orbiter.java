import java.awt.Color;
public class Orbiter
{
    /*
     * Instance variables for class Ball
     * Variables have basic properties of the ball
     */
    private double x, y;
    private int radius;
    private Color color = Color.red;
    private double angle;

    /*
    * constructor for objects of class Ball
    * Starts a timer at when it was created 
    */
 
    public Orbiter(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        angle = 0;
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
    public Color getColor(){
        return color;
    }
    public int getRadius(){
        return radius;
    }
    public void setX(double a){
        x = a;
    }
    public void setY(double a){
        y = a;
    }
    public void move(int cx, int cy, int r){
       x = cx + r*Math.cos(angle);
       y = cy + r*Math.sin(angle);
       angle+=Math.PI/256;
    }
}