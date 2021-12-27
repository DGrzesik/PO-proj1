package agh.ics.oop;

public class Grass {
    Vector2d pos;
    public Grass(Vector2d grasspos){
        pos=grasspos;
    }

    public Vector2d getPos() {
        return pos;
    }
    public String toString(){
        return "*";
    }
}
