package com.github.kkysen.supersmashbros.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.github.kkysen.libgdx.util.Renderable;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class Platform implements Renderable {
    
    private static final float DEFAULT_FRICTION = 0.5f; // FIXME
    
    public Rectangle bounds;
    private final Sprite sprite;
    public final float friction;
    
    public Platform(final Sprite sprite, final float friction) {
        bounds = sprite.getBoundingRectangle();
        this.sprite = sprite;
        this.friction = friction;
    }
    
    public Platform(final Sprite sprite) {
        this(sprite, DEFAULT_FRICTION);
    }
    
    @Override
    public void render(final Batch batch) {
        sprite.draw(batch);
    }
    
}
