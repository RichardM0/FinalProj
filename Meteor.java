public class Meteor
{
    /*
     * Instance variables for class Meteor
     * Variables have basic properties of the Meteor
     * x,y coordinates, spped, radius, and mass
     */
    private double x, y, dx, dy;
    private int radius;
    private double mass;

    /*
    * constructor for objects of class Meteor
    * initializes each instance variable
    */
 
    public Meteor(int x, int y, int radius){
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        mass=25;
        this.radius = radius;
    }
    /*
     * Getter methods for class Meteor
     * get x coordinate, y coordinate, radius, or mass
     * used from GravityPanel class
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
    public double getMass(){
        return mass;
    }
    /*
     * Setter methods for class Meteor
     * set x coordinate, y coordinate, radius, or mass
     * used from GravityPanel class
     */
    public void setX(double a){
        x = a;
    }
    public void setY(double a){
        y = a;
    }

    /*
     * updateVel and move method
     * updateVel passes in the calculated forces from GravityPanel's attraction method
     * takes x and y components
     * uses Newton's second law to find acceleration
     * adds acceleration to change in x/y
     * move method updates position of each meteor
     */
    public void updateVel(double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        
        dx += ax;
        dy += ay;
    }
    public void move(){
        x+=dx;
        y+=dy;
    }
}