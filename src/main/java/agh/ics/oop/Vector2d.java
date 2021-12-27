package agh.ics.oop;


import java.util.Objects;

public class Vector2d {
    public final int x,y;
    public Vector2d(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
    public boolean precedes(Vector2d other){
        if(this.x<=other.x)
            if (this.y<=other.y)
                return true;
        return false;
    }
    public boolean follows(Vector2d other){
        if(this.x>=other.x)
            if (this.y>=other.y)
                return true;
        return false;
    }
    public Vector2d upperRight(Vector2d other){
        int maxx;
        int maxy;
        if(this.x>=other.x)
            maxx=this.x;
        else maxx=other.x;
        if(this.y>=other.y)
            maxy=this.y;
        else maxy=other.y;
        return(new Vector2d(maxx,maxy));
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);}
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        if(that.x==x && that.y==y)
            return true;
        return false;
    }
    public Vector2d lowerLeft(Vector2d other){
        int minx;
        int miny;
        if(this.x<=other.x)
            minx=this.x;
        else minx=other.x;
        if(this.y<=other.y)
            miny=this.y;
        else miny=other.y;
        return(new Vector2d(minx,miny));
    }
    public Vector2d add(Vector2d other){
        return(new Vector2d(this.x+other.x,this.y+other.y));
    }
    public Vector2d subtract(Vector2d other){
        return(new Vector2d(this.x-other.x,this.y-other.y));
    }
    public Vector2d opposite(){
        return(new Vector2d(-this.x,-this.y));
    }
    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }
}
