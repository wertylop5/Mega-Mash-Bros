package com.github.kkysen.megamashbros.core;

import com.badlogic.gdx.graphics.Color;
import com.github.kkysen.megamashbros.actions.Attack;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class Hurtbox extends Box {
    
    private static final float DAMAGE_MULTIPLIER = 0.1f;
    
    public Hurtbox(final Player player, final float width, final float height,
            final float lifetime) {
        super(player, width, height, lifetime);
    }
    
    public Hurtbox(final Player player) {
        this(player, player.width() * 1.25f, player.height() * 1.25f, Float.MAX_VALUE);
    }
    
    @Override
    protected Color getColor() {
        return player.isAI() ? Color.BLUE : Color.GOLD;
    }
    
    @Override
    public String toString() {
        return player + "'s Hurtbox";
    }
    
    public float damageTakenBy(final Hitbox hitbox) {
        return intersectionArea(hitbox) * hitbox.attack.damage * DAMAGE_MULTIPLIER;
    }
    
    public float collide(final Hitbox hitbox) {
        log(this + " collided with " + hitbox);
        return damageTakenBy(hitbox);
    }
    
    /**
     * Damage dealt, simplified:
     * Base dmg (from action) * contact area with hurtbox
     */
    public float damageTakenBy(final Attack attack, final Hitbox hitbox) {
        return intersectionArea(hitbox) * attack.damage/* * DAMAGE_MULTIPLIER*/;
    }
    
    public float collide(final Attack attack, final Hitbox hitbox) {
        final float damage = damageTakenBy(attack, hitbox);
        //hitbox.player.attacked(damage);
        return damage;
    }
    
    @Override
    public boolean subUpdate() {
        bounds.x = player.position.x;
        bounds.y = player.position.y;
        return true;
    }
    
}
