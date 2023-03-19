package it.unibo.smol.model.impl;

import javafx.geometry.Point2D;
import java.util.Optional;
import it.unibo.smol.controller.api.InputComponent;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.GameState;
import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.model.api.PhysicsComponent;

/**
 * The implementation that rappresent everything present in the game world.
 */
public class EntityImpl implements Entity {
    private final Type type;
    private final Optional<InputComponent> inputComp;
    private final Optional<HealthComponent> healthComp;
    private final GraphicComponent graphicComp;
    private final PhysicsComponent physicsComp;
    private double currentX;
    private double currentY;
    private GameState gameState;

    /**
     * Constructor for creating entities utilizing the entity factory.
     * @param type
     * @param inputComp
     * @param healthComp
     * @param graphicComp
     * @param physicsComp
     * @param currentX
     * @param currentY
     */
    public EntityImpl(final Type type, final Optional<InputComponent> inputComp,
            final Optional<HealthComponent> healthComp,
            final GraphicComponent graphicComp, final PhysicsComponent physicsComp,
            final double currentX, final double currentY) {
        this.type = type;
        this.inputComp = inputComp;
        this.healthComp = healthComp;
        this.graphicComp = graphicComp;
        this.physicsComp = physicsComp;
        this.currentX = currentX;
        this.currentY = currentY;
        physicsComp.setEntity(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentX() {
        return currentX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentY() {
        return currentY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getCurrentPosition() {
        return new Point2D(currentX, currentY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGameState() {
        return gameState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveX(final double x) {
        currentX += x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveY(final double y) {
        currentY += y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameState(final GameState gs) {
        this.gameState = gs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<HealthComponent> getHealthComp() {
        return healthComp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicsComponent getPhysicsComp() {
        return physicsComp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (inputComp.isPresent()) {
            final InputComponent inputCompPresent = inputComp.orElseThrow();
            if (inputCompPresent.getDirection().isPresent()) {
                physicsComp.receiveMovement(inputCompPresent.getDirection().orElseThrow());
            }
            if (inputCompPresent.getPosition().isPresent()) {
                physicsComp.receiveMovement(inputCompPresent.getPosition().orElseThrow());
            }
            physicsComp.setRigid(inputCompPresent.isHittable());
            this.moveX(physicsComp.getX());
            this.moveY(physicsComp.getY());
        }
        physicsComp.checkCollision();
        if (healthComp.isPresent() && healthComp.get().isDead()) {
            this.gameState.notifyDeath();
        }
        graphicComp.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity copyOf() {
        return new EntityImpl(this.type,
        this.inputComp,
        this.healthComp,
        this.graphicComp,
        this.physicsComp,
        this.currentX,
        this.currentY);
    } 
}
