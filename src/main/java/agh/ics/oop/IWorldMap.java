package agh.ics.oop;

import javafx.scene.image.Image;

import java.util.List;
import java.util.Map;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */

    boolean place(Animal animal);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */


    boolean isOccupied(Vector2d position);

    /**
     * Return an object at a given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    Object objectAt(Vector2d position);

    Image getImage(Animal animal);

    void makebaby(Animal ani1, Animal ani2);

    void clearcorpses();

    Vector2d getLeftBottomCorner();

    Vector2d getRightTopCorner();

    List<Animal> getAnimals();
    Map<Vector2d,Grass> getTufts();
    void eatandmate(int plantEnergy,int startEnergy);
    void placegrass();
}
