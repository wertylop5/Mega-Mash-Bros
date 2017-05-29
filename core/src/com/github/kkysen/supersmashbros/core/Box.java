package com.github.kkysen.supersmashbros.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;
import com.github.kkysen.libgdx.util.Loggable;

/**
 * 
 * 
 * @author Khyber Sen
 */
public abstract class Box implements Poolable, Loggable {
    
    private static final Pool<Rectangle> pool = Pools.get(Rectangle.class);
    
    public final Rectangle bounds = pool.obtain();
    
    private float elapsedTime = 0;
    private final float lifetime;
    
    public Box(final float lifetime) {
        this.lifetime = lifetime;
    }
    
    @Override
    public void reset() {
        pool.free(bounds);
    }
    
    public final boolean overlaps(final Box box) {
        return bounds.overlaps(box.bounds);
    }
    
    public final Rectangle intersect(final Box box) {
        final Rectangle intersection = pool.obtain();
        if (Intersector.intersectRectangles(bounds, box.bounds, intersection)) {
            return intersection;
        } else {
            return null;
        }
    }
    
    public final float intersectionArea(final Box box) {
        final Rectangle intersection = intersect(box);
        return intersection == null ? 0 : intersection.area();
    }
    
    /**
     * @return true if the box still exists, false if it should be removed
     */
    public abstract boolean subUpdate();
    
    public final boolean update() {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (elapsedTime > lifetime) {
            return false;
        }
        return subUpdate();
    }
    
}
