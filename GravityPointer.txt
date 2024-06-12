

/**
 * Write a description of class GravityPointer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GravityPointer
{
    // instance variables - replace the example below with your own
    private double x, y, dX, dY;
    private int radius = 35;
    private double mass;
    private int quadrant = 0;
    private int moving = 1;
    private double angle = 0;

    public GravityPointer(int x, int y)
    {
        dX = 1.0;
        dY = 0;
        this.x = x;
        this.y = y;
        mass = 1e14;
    }
    public void setX(double newX){
        x = newX;
    }
    public void setY(double newY){
        y = newY;
    }
    public void setDx(double newDX){
        dX = newDX;
    }
    public void setDy(double newDY){
        dY = newDY;
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
    public double getDx(){
        return dX;
    }
    public double getDy(){
        return dY;
    }
    public void updateVel(double fx, double fy){
        double ax = fx/mass;
        double ay = fy/mass;
        
        dX += ax;
        dY += ay;
    }
    public void move(){
        if(moving == 1){
            dX = 1;
            dY = 0;
            x+=dX;
        }
        else if(moving == 2){
            dX = 0;
            dY = 1;
            y+=dY;
        }
        else if(moving == 3){
            dX = -1;
            dY = 0;
            x+=dX;
        }
        else if(moving == 4){
            dX = 0;
            dY = -1;
            y+=dY;
        }
    }
    public void ellipsoidMotion(int w, int h, boolean stopped){
        int r = Math.min(w - radius, h - radius);
        x = Double.valueOf(w/2) + (r/2)*Math.cos(angle);
        y = Double.valueOf(h/2) + (r/4)*Math.sin(angle);
        if(!stopped){
            angle += Math.PI/256;
        }
    }
    public int getMoving(){
        return moving;
    }
    public void setMoving(int i){
        moving = i;
    }
    public int getQuadrant(){
        return quadrant;
    }
    public void setQuadrant(int i){
        quadrant = i;
    }
}