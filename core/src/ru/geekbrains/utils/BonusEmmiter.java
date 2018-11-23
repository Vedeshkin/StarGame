package ru.geekbrains.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BonusAction;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.BonusPool;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.Bonus;
import ru.geekbrains.sprite.Bullet;
import ru.geekbrains.sprite.Enemy;
import ru.geekbrains.sprite.MainShip;

public class BonusEmmiter {


    private Rect worldBounds;
    private BonusPool bp;
    private final EnemyPool enemyPool;
    private final BulletPool bulletPool;
    private final MainShip mainShip;
    private static final Vector2 v0 = new Vector2(0, -0.2f);

    private static final TextureRegion Heal100texture = new TextureRegion(new Texture(Gdx.files.internal("pill_red.png")));
    private static final TextureRegion Heal10texture = new TextureRegion(new Texture(Gdx.files.internal("pill_green.png")));
    private static final TextureRegion Heal50texture = new TextureRegion(new Texture(Gdx.files.internal("pill_blue.png")));
    private static final TextureRegion killAlltexture = new TextureRegion(new Texture(Gdx.files.internal("bolt_bronze.png")));

    private float generateInterval = 10f;
    private float generateTimer;

    public BonusEmmiter(Rect worldBounds, BonusPool bp, EnemyPool enemyPool, BulletPool bulletPool, MainShip mainShip) {
        this.worldBounds = worldBounds;
        this.bp = bp;
        this.enemyPool = enemyPool;
        this.bulletPool = bulletPool;
        this.mainShip = mainShip;
    }


    public void generateBonus(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;

            BonusAction killAll  = ( enemyPool,  mainShip,  bulletPool) -> {

                    for (Enemy enemy : enemyPool.getActiveObjects()) {
                        enemy.destroy();
                    }
                    for (Bullet bullet : bulletPool.getActiveObjects()) {
                        bullet.destroy();
                    }

            };

            BonusAction heal50 = (enemyPool, mainShip, bulletPool) -> mainShip.addHP(50);
            BonusAction heal10 = (enemyPool, mainShip, bulletPool) -> mainShip.addHP(10);
            BonusAction heal100 = (enemyPool, mainShip, bulletPool) -> mainShip.addHP(100);

            Bonus bonus = bp.obtain();


            float type = (float) Math.random();
            if (type < 0.5f)
            {

                bonus.setAction(heal10);
                bonus.setTextureRegion(Heal10texture);
            } else if (type < 0.7f)
            {
                bonus.setAction(killAll);
                bonus.setTextureRegion(killAlltexture);
            }
            else if (type < 0.8f)
            {
                bonus.setAction(heal50);
                bonus.setTextureRegion(Heal50texture);
            }
            else if (type < 0.9f)
            {
                bonus.setAction(heal100);
                bonus.setTextureRegion(Heal100texture);
            }


            bonus.setBulletPool(bulletPool);
            bonus.setWorldBounds(worldBounds);
            bonus.setV(v0);
            bonus.setHeightProportion(0.05f);
            bonus.setEnemyPool(enemyPool);
            bonus.setMainShip(mainShip);
            bonus.setBottom(worldBounds.getTop());
            bonus.pos.x = Rnd.nextFloat(worldBounds.getLeft() + bonus.getHalfWidth(), worldBounds.getRight() - bonus.getHalfWidth());


    }
}

}
