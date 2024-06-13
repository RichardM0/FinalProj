public class Orbiter
{
    /*
     * Instance variables for class Orbiter
     * Variables have basic properties of the Orbiter
     * x,y coordinates, radius, and angle to handle circular motion
     */
    private double x, y;
    private int radius;
    private double angle;

    /*
    * constructor for objects of class Orbiter
    * initializes instance variables
    */
 
    public Orbiter(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;

    }
    /*
     * Getter methods for class Orbiter
     * retrieves x,y coordinates and radius
     * used in GravityPanel
     */
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getRadius(){
        return radius;
    }
    /*
     * move Method
     * uses equation for unit circle to model circular motion
     * cx is center of rotation in x
     * cy is center of rotation in y
     * r is radius (distance) away from center
     * increment angle for rotation
     */
    public void move(int cx, int cy, int r){
       x = cx + r*Math.cos(angle);
       y = cy + r*Math.sin(angle);
       angle+=Math.PI/256;
    }
}