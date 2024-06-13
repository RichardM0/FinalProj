public class GravityPointer
{
    /*
     * GravityPointer class and its instance variables
     * x,y for coordinate
     * radius for size
     * mass to handle gravitational force
     */
    private double x, y;
    private int radius = 35;
    private double mass;

    /*
     * constructor for class GravityPointer
     * passes in all instance variables
     */
    public GravityPointer(int x, int y)
    {
        this.x = x;
        this.y = y;
        mass = 5e13;
    }
    /*
     * getter Methods for class GravityPointer
     * retrieves mass, radius, x coordinate, or y coordinate
     * used from GravityPanel class
     */
    public double getMass(){
        return mass;
    }
    public int getRadius(){
        return radius;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    /*
     * setter Methods for class GravityPointer
     * sets mass, radius, x coordinate, or y coordinate
     * used from GravityPanel class
     */
    public void setMass(double newMass){
        mass = newMass;
    }
    public void setRadius(int r){
        radius = r;
    }
    public void setX(double newX){
        x = newX;
    }
    public void setY(double newY){
        y = newY;
    }
}