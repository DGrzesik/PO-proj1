package agh.ics.oop;


import javafx.scene.image.Image;

import java.util.*;

import static java.lang.Math.sqrt;

public class ConstantMap extends AbstractWorldMap{
    private final Map<Vector2d,Grass> tufts;
    public ConstantMap(double jungleratio, int width, int height){
        tufts= new HashMap<>();
        bottomLeft=new Vector2d(0,0);
        topRight=new Vector2d(width,height);
        double junglewidth=width*sqrt(jungleratio/(jungleratio+1));
        double junglehight=height*sqrt(jungleratio/(jungleratio+1));
        junglebotLeft=new Vector2d((int)(width-junglewidth)/2,(int)(height-junglehight)/2);
        jungletopRight=new Vector2d((int)((width-junglewidth)/2+junglewidth),(int)((height-junglehight)/2+junglehight));
        System.out.println(junglebotLeft);
        System.out.println(jungletopRight);
        placegrass();
    }

    public void placegrass(){
        for (int i=0;i<1;i++){
            Vector2d spot=new Vector2d(new Random().nextInt(this.getRightTopCorner().x),new Random().nextInt(this.getRightTopCorner().y));
            if (!isOccupied(spot) && spot.precedes(topRight) && !(spot.precedes(jungletopRight) && spot.follows(junglebotLeft))){
                if (!(objectAt(spot) instanceof Grass)){
                    System.out.println("nowa trawa na sawannie: "+spot);
                    Grass grass = new Grass(spot);
                    this.tufts.put(spot,grass);
                }
            }
            else
            {
                i--;
            }
        }
        int tries=0;
        for (int i=0;i<1;i++){
            //uznałem, że skoro 10 razy nie udało się znaleźć miejsca w dźunglii, to znaczy, że jest już zbyt ciasno
            if(tries>10){
                break;
            }
            Vector2d spot=new Vector2d(new Random().nextInt(this.getRightTopCorner().x),new Random().nextInt(this.getRightTopCorner().y));
            if (!isOccupied(spot) && spot.precedes(topRight) && (spot.precedes(jungletopRight) && spot.follows(junglebotLeft))){
                if (!(objectAt(spot) instanceof Grass)){
                    System.out.println("nowa trawa w dżungli: " + spot);
                    Grass grass = new Grass(spot);
                    this.tufts.put(spot,grass);
                }
            }
            else
            {
                tries+=1;
                i--;
            }
        }
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }
    public void eatandmate(int tuftEnergy,int startEnergy){
        for (int i=0;i<=getRightTopCorner().x;i++){
            for (int j=0;j<=getRightTopCorner().y;j++){
                List<Animal> strongest=super.findstrongest(new Vector2d(i,j));
                if (strongest.size()==1){
                    if (tufts.containsKey(strongest.get(0).getPos())){
                        System.out.println(strongest.get(0).toString()+strongest.get(0).getPos());
                        strongest.get(0).energy+=tuftEnergy;
                        tufts.remove(strongest.get(0).getPos());
                    }
                }
                if (strongest.size()==2){
                    if (tufts.containsKey(strongest.get(0).getPos())){
                        System.out.println(strongest.get(0).toString()+strongest.get(0).getPos());
                        strongest.get(0).energy+=tuftEnergy;
                        tufts.remove(strongest.get(0).getPos());
                    }
                    if (strongest.get(0).energy>0.5*startEnergy && strongest.get(1).energy>0.5*startEnergy){
                        System.out.println("mating at:" + strongest.get(0).getPos());
                        makebaby(strongest.get(0),strongest.get(1));
                    }
                }
            }
        }
    }
    @Override
    public void makebaby(Animal ani1, Animal ani2){
        Genes newgenes=new Genes();
        double strongpart;
        int side;

        if (ani1.energy>ani2.energy){
            strongpart= ani1.energy/(ani1.energy+ani2.energy)*32;
            side=new Random().nextInt(2);
            if (side==0){
                newgenes.makegenes((int)strongpart,ani1.genes,ani2.genes);
            }
            else{
                newgenes.makegenes(32-(int)strongpart,ani2.genes,ani1.genes);
            }
        }
        else{
            strongpart= ani2.energy/(ani1.energy+ani2.energy)*32;
            side=new Random().nextInt(2);
            if (side==0){
                newgenes.makegenes((int)strongpart,ani2.genes,ani1.genes);
            }
            else{
                newgenes.makegenes(32-(int)strongpart,ani1.genes,ani2.genes);
            }
        }
        Animal baby=new Animal(this,ani1.getPos(), (int) (0.25*ani1.energy+0.25*ani2.energy),newgenes);
        ani1.energy=(int) (ani1.energy-0.25*ani1.energy);
        ani2.energy=(int) (ani2.energy-0.25*ani2.energy);
        this.place(baby);
        baby.addObserver(this);
    }
    @Override
    public boolean place(Animal animal) {
        animal.addObserver(this);
        return super.place(animal);

    }
    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) return super.objectAt(position);
        return tufts.get(position);
    }
    public Map<Vector2d,Grass> getTufts(){
        return tufts;
    }

    @Override
    public Image getImage(Animal animal) {
        return switch (animal.toString()) {
            case "N" -> new Image("/up.png");
            case "E" -> new Image("/right.png");
            case "S" -> new Image("/down.png");
            case "W" -> new Image("/left.png");
            case "NE" -> new Image("/upright.png");
            case "SE"->new Image("/downright.png");
            case "SW"-> new Image("/downleft.png");
            case "NW" -> new Image("/upleft.png");
            default -> throw new IllegalArgumentException(animal.toString() + " is not an orientation");
        };
    }
    @Override
    public String toString(){
        return super.toString();
    }

}