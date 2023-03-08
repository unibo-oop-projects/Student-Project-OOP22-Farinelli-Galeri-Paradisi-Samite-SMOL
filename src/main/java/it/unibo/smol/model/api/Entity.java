package it.unibo.smol.model.api;

import java.util.Optional;

import it.unibo.smol.model.Type;
import it.unibo.smol.model.impl.HealthComponent;

/**
 *  Functional interface for object and entities in the game.
 */
public interface Entity {
    /**
     * Get the current x coordinate of the object.
     * @return {@code x}
     */
    int getCurrentX();

    /**
     * Get the current x coordinate of the object.
     * @return {@code y}
     */
    int getCurrentY();

    /**
     * Getter for the world. 
     * @return the world
     */
    World getWorld();

    /**
     * Add the new x coordinate to the current X.
     * @param x : the new x to add in the object
     */
    void moveX(int x);

    /**
     * Add the new y coordinate to the current Y.
     * @param y : the new y to add in the object
     */
    void moveY(int y);

    /**
     * Set the current world.
     * @param w the world to set
     */
    void setWorld(World w);

    /**
     * Getter for the field type.
     * @return the {@link Type} of the entity
     */
    Type getType();

    /**
     * Getter for the HealthComponent.
     * @return The healthcomponent
     */
    Optional<HealthComponent> getHealthComp();

    /**
     * Update all the component of the object.
     */
    void update();
}
