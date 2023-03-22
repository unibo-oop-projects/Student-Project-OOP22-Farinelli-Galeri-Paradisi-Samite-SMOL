package it.unibo.smol.model.impl.graphiccomponent;

import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.view.LoadImgs;

/**
 * The implementation of the {@link GraphicComponent} rappresenting the Weapon graphic.
 */
public class WeaponGraphicComponent extends GraphicComponent {

    private boolean attacking;

    /**
     * 
     * @param width
     * @param height
     */
    public WeaponGraphicComponent(final double width, final double height) {
        super(width, height);
    }

    /**
     * 
     */
    @Override
    public void render() {
        super.update();
        if (attacking) {
            super.getGraphic().drawSprite(super.getImage(), super.getEntity());
        }
    }

    /**
     * 
     */
    @Override
    public void setAnimation() {
        if (attacking) {
            super.setImage(LoadImgs.getSprites(LoadImgs.MOLE));
        }
    }

    /**
     * 
     */
    @Override
    public void updateAnimation() {
        if (attacking/*is Hammer SMashed == true */) {
            attacking = true;
        } else {
            attacking = false;
        }
    }
}
