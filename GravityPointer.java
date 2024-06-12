

/**
 * Write a description of class GravityPointer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GravityPointer
{
    // instance variables - replace the example below with your own
    private double x, y;
    private int radius = 35;
    private double mass;

    public GravityPointer(int x, int y)
    {
        this.x = x;
        this.y = y;
        mass = 5e13;
    }
    public void setX(double newX){
        x = newX;
    }
    public void setY(double newY){
        y = newY;
    }
    public void setMass(double newMass){
        mass = newMass;
    }
    public double getMass(){
        return mass;
    }
    public void setRadius(int r){
        radius = r;
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
}