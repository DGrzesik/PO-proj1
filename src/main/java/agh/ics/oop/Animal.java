package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {
     private MapDirections orient;
     private final IWorldMap mapa;
     private Vector2d pos;
     private List<IPositionChangeObserver> observers;
     int energy;
     int[] genes;
     public Animal(IWorldMap map,Vector2d initialPosition,int energy,Genes genes){
         this.mapa=map;
         this.pos=initialPosition;
         this.orient = MapDirections.NORTH;
         this.observers = new ArrayList<>();
         this.energy=energy;
         this.genes=genes.genes;
     }
    public String toString(){
        return switch (orient) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
            case SOUTHWEST -> "SW";
            case SOUTHEAST -> "SE";
            case NORTHWEST -> "NW";
            case NORTHEAST -> "NE";
        };
    }
    public void move(int direction){
         if(mapa instanceof ConstantMap) {
             switch (direction) {
                 case 1 -> {
                     this.orient = MapDirections.NORTHEAST;
                 }
                 case 2 -> {
                     this.orient = MapDirections.EAST;
                 }
                 case 3 -> {
                     this.orient = MapDirections.SOUTHEAST;
                 }
                 case 5 -> {
                     this.orient = MapDirections.SOUTHWEST;
                 }
                 case 6 -> {
                     this.orient = MapDirections.WEST;
                 }
                 case 7 -> {
                     this.orient = MapDirections.NORTHWEST;
                 }

                 case 0 -> {
                     Vector2d new_location = this.pos.add(this.orient.toUnitVector());
                     if ( new_location.precedes(mapa.getRightTopCorner()) && mapa.getLeftBottomCorner().precedes(new_location)) {
                         Vector2d old_location = this.pos;
                         this.pos = new_location;
                         positionChanged(old_location, new_location);
                     }
                 }
                 case 4 -> {
                     Vector2d new_location = this.pos.subtract(this.orient.toUnitVector());
                     if ( new_location.precedes(mapa.getRightTopCorner()) && mapa.getLeftBottomCorner().precedes(new_location)) {
                         Vector2d old_location = this.pos;
                         this.pos = new_location;
                         positionChanged(old_location, new_location);
                     }
                 }
             }
         }
         else{
             switch (direction) {
                 case 1 -> {
                     this.orient = MapDirections.NORTHEAST;
                 }
                 case 2 -> {
                     this.orient = MapDirections.EAST;
                 }
                 case 3 -> {
                     this.orient = MapDirections.SOUTHEAST;
                 }
                 case 5 -> {
                     this.orient = MapDirections.SOUTHWEST;
                 }
                 case 6 -> {
                     this.orient = MapDirections.WEST;
                 }
                 case 7 -> {
                     this.orient = MapDirections.NORTHWEST;
                 }
                 case 0 -> {
                     Vector2d new_location;
                     if (this.pos.add(this.orient.toUnitVector()).x>mapa.getRightTopCorner().x){
                         if(this.pos.add(this.orient.toUnitVector()).y>mapa.getRightTopCorner().y){
                             new_location= mapa.getLeftBottomCorner();
                         }
                         else {
                             if(this.pos.add(this.orient.toUnitVector()).y<mapa.getLeftBottomCorner().y){
                                 new_location= new Vector2d(mapa.getLeftBottomCorner().x, mapa.getRightTopCorner().y);
                             }
                             else {
                                 new_location=new Vector2d(mapa.getLeftBottomCorner().x,this.pos.add(this.orient.toUnitVector()).y);
                             }
                         }

                     }
                     else if(this.pos.add(this.orient.toUnitVector()).x<mapa.getLeftBottomCorner().x){
                         if(this.pos.add(this.orient.toUnitVector()).y>mapa.getRightTopCorner().y){
                             new_location= new Vector2d(mapa.getRightTopCorner().x,mapa.getLeftBottomCorner().y);
                         }
                         else {
                             if(this.pos.add(this.orient.toUnitVector()).y<mapa.getLeftBottomCorner().y){
                                 new_location= mapa.getRightTopCorner();
                             }
                             else {
                                 new_location=new Vector2d(mapa.getRightTopCorner().x,this.pos.add(this.orient.toUnitVector()).y);
                             }
                         }
                     }
                     else{
                         if(this.pos.add(this.orient.toUnitVector()).y>mapa.getRightTopCorner().y){
                             new_location= new Vector2d(this.pos.add(this.orient.toUnitVector()).x,mapa.getLeftBottomCorner().y);
                         }
                         else {
                             if(this.pos.add(this.orient.toUnitVector()).y<mapa.getLeftBottomCorner().y){
                                 new_location= new Vector2d(this.pos.add(this.orient.toUnitVector()).x,mapa.getRightTopCorner().y);
                             }
                             else {
                                 new_location=this.pos.add(this.orient.toUnitVector());
                             }
                         }
                     }
                     Vector2d old_location = this.pos;
                     this.pos = new_location;
                     positionChanged(old_location, new_location);
                 }
                 case 4 -> {
                     Vector2d new_location;
                     if (this.pos.subtract(this.orient.toUnitVector()).x>mapa.getRightTopCorner().x){
                         if(this.pos.subtract(this.orient.toUnitVector()).y>mapa.getRightTopCorner().y){
                             new_location= mapa.getLeftBottomCorner();
                         }
                         else {
                             if(this.pos.subtract(this.orient.toUnitVector()).y<mapa.getLeftBottomCorner().y){
                                 new_location= new Vector2d(mapa.getLeftBottomCorner().x, mapa.getRightTopCorner().y);
                             }
                             else {
                                 new_location=new Vector2d(mapa.getLeftBottomCorner().x,this.pos.subtract(this.orient.toUnitVector()).y);
                             }
                         }

                     }
                     else if(this.pos.subtract(this.orient.toUnitVector()).x<mapa.getLeftBottomCorner().x){
                         if(this.pos.subtract(this.orient.toUnitVector()).y>mapa.getRightTopCorner().y){
                             new_location= new Vector2d(mapa.getRightTopCorner().x,mapa.getLeftBottomCorner().y);
                         }
                         else {
                             if(this.pos.subtract(this.orient.toUnitVector()).y<mapa.getLeftBottomCorner().y){
                                 new_location= mapa.getRightTopCorner();
                             }
                             else {
                                 new_location=new Vector2d(mapa.getRightTopCorner().x,this.pos.subtract(this.orient.toUnitVector()).y);
                             }
                         }
                     }
                     else{
                         if(this.pos.subtract(this.orient.toUnitVector()).y>mapa.getRightTopCorner().y){
                             new_location= new Vector2d(this.pos.subtract(this.orient.toUnitVector()).x,mapa.getLeftBottomCorner().y);
                         }
                         else {
                             if(this.pos.subtract(this.orient.toUnitVector()).y<mapa.getLeftBottomCorner().y){
                                 new_location= new Vector2d(this.pos.subtract(this.orient.toUnitVector()).x,mapa.getRightTopCorner().y);
                             }
                             else {
                                 new_location=this.pos.subtract(this.orient.toUnitVector());
                             }
                         }
                     }
                     Vector2d old_location = this.pos;
                     this.pos = new_location;
                     positionChanged(old_location, new_location);
                     }
                 }
             }
         }

    public Vector2d getPos() {
         return pos;
    }
    public int getEnergy(){return energy;}
    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
         for (IPositionChangeObserver ob:observers){
             ob.positionChanged(this,oldPosition,newPosition);
         }
    }
}
