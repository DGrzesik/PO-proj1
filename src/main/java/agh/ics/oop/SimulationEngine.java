package agh.ics.oop;

import agh.ics.oop.gui.App;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationEngine extends App implements Runnable{
    private final IWorldMap grsfld;
    private final int movedelay;
    private final App app;
    private final List<Animal> animals=new ArrayList<>();
    private final int consumedpermove;
    private final int plantEnergy;
    private final int startEnergy;
    public SimulationEngine(IWorldMap grassfield,App app,int movedelay,int plantEnergy,int consumedpermove,int startEnergy){
        this.grsfld=grassfield;
        this.movedelay=movedelay;
        this.app=app;
        this.consumedpermove=consumedpermove;
        this.startEnergy=startEnergy;
        this.plantEnergy=plantEnergy;
        this.animals.addAll(grsfld.getAnimals());

    }
    @Override
    public void run() {
        System.out.println("Ilość zwierząt:" + animals.size() +" w dniu " + 0);
        System.out.print(grsfld);
        int day=0;
        for (Animal animal: animals){
            System.out.println(animal +" at " + animal.getPos()+": "+ animal.energy + grsfld.getTufts().containsKey(animal.getPos()));
        }
        app.DrawScene();
        while (true) {
            if (animals.size()>0) {
                try {
                    Thread.sleep(movedelay);
                    grsfld.clearcorpses();
                    animals.clear();
                    this.animals.addAll(grsfld.getAnimals());
                    for (Animal animal : animals) {
                        animal.move(animal.genes[new Random().nextInt(32)]);
                        animal.energy = animal.energy - consumedpermove;
                    }
                    grsfld.eatandmate(plantEnergy, startEnergy);
                    grsfld.placegrass();
                    animals.clear();
                    this.animals.addAll(grsfld.getAnimals());
                    app.DrawScene();
                    day += 1;
                    System.out.println("Ilość zwierząt:" + animals.size() + " w dniu " + day);
                    System.out.print(grsfld);
                    for (Animal animal : animals) {
                        System.out.println(animal + " at " + animal.getPos() + ": " + animal.energy + grsfld.getTufts().containsKey(animal.getPos()));
                    }
                } catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                }
            }
            else{
                break;
            }
        }
    }
}


