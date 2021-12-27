package agh.ics.oop;

import java.util.*;

abstract public class AbstractWorldMap implements IWorldMap,IPositionChangeObserver {
    public Map<Vector2d,List<Animal>> animalsat = new HashMap<>();
    public List<Animal> animals=new ArrayList<>();
    protected Vector2d bottomLeft;
    protected Vector2d topRight;
    protected Vector2d junglebotLeft;
    protected Vector2d jungletopRight;
    @Override
    public boolean place(Animal animal) {
        List<Animal> animalsatpos=new ArrayList<>();
        animalsatpos.add(animal);
        animalsat.put(animal.getPos(), animalsatpos);
        animals.add(animal);
        return true;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return (objectAt(position) instanceof Animal);
    }
    @Override
    public Object objectAt(Vector2d position) {
        if(animalsat.get(position)!=null && animalsat.get(position).size()>0){
               return animalsat.get(position).get(0);
        }
        return null;
    }
    @Override
    public void positionChanged(Animal animal,Vector2d oldPosition, Vector2d newPosition) {
        animalsat.get(oldPosition).remove(animal);
        if(animalsat.get(newPosition)!=null && animalsat.get(newPosition).size()>0){
            animalsat.get(newPosition).add(animal);
        }
        else{
            List<Animal> animalsatpos=new ArrayList<>();
            animalsatpos.add(animal);
            animalsat.put(newPosition, animalsatpos);
        }
    }
    public List<Animal> findstrongest(Vector2d position){
        List<Animal> anims=new ArrayList<>();
        int maxen1=-1;
        int maxen2=-2;
        for(Animal animal:animals){
                if (animal.getPos().equals(position)) {
                    if (anims.size() == 0) {
                        maxen1 = animal.energy;
                        anims.add(animal);
                    } else {
                        if (animal.energy > maxen1) {
                            anims.set(0, animal);
                            maxen1 = animal.energy;
                        }
                        else {
                            if (anims.size() == 1) {
                                anims.add(animal);
                                maxen2 = animal.energy;
                            }
                            else {
                                if (animal.energy >= maxen2) {
                                    anims.set(1, animal);
                                    maxen2 = animal.energy;
                                }
                            }
                        }

                    }
            }
        }
        return anims;
    }
    @Override
    public void clearcorpses(){
        for(List<Animal> animalList: animalsat.values()){
            animalList.removeIf(animal -> animal.energy <= 0);
        }
        animals.removeIf(animal -> animal.energy <= 0);
    }
    @Override
    public Vector2d getLeftBottomCorner(){
        return bottomLeft;
    }
    public Vector2d getRightTopCorner(){
        return topRight;
    }
    @Override
    public String toString(){
        return new MapVisualizer(this).draw(getLeftBottomCorner(), getRightTopCorner());
    }


    public List<Animal> getAnimals() {
        return animals;
    }
}
