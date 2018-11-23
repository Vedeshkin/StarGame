package ru.geekbrains.utils;

import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.BonusPool;
import ru.geekbrains.sprite.HealPack;

public class BonusEmmiter {

    private Rect worldBounds;
    private BonusPool bp;
    private static final Vector2 v0 = new Vector2(0, -0.2f);

    private float generateInterval = 4f;
    private float generateTimer;
    public BonusEmmiter(Rect worldBounds, BonusPool bp) {
        this.worldBounds = worldBounds;
        this.bp = bp;
    }

    public void spawnHealBonus(float delta){
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;

            HealPack hp = bp.obtain();
            hp.set(v0, 0.05f, worldBounds, 50);
            hp.setBottom(worldBounds.getTop());
            hp.pos.x = Rnd.nextFloat(worldBounds.getLeft() + hp.getHalfWidth(), worldBounds.getRight() - hp.getHalfWidth());
        }
    }

}
