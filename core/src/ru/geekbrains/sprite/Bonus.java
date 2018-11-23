package ru.geekbrains.sprite;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BonusAction;
import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.EnemyPool;

public class Bonus extends Sprite {

    private TextureRegion textureRegion;
    private Rect worldBounds;
    private Vector2 v = new Vector2();
    private EnemyPool enemyPool;
    private MainShip mainShip;
    private BulletPool bulletPool;
    private BonusAction action;

    public void setWorldBounds(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    public void setV(Vector2 v) {
        this.v = v;
    }

    public void setEnemyPool(EnemyPool enemyPool) {
        this.enemyPool = enemyPool;
    }

    public void setMainShip(MainShip mainShip) {
        this.mainShip = mainShip;
    }

    public void setBulletPool(BulletPool bulletPool) {
        this.bulletPool = bulletPool;
    }


    public void setAction(BonusAction action) {
        this.action = action;
    }

    public Bonus() {
        super();
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }
    public void doAction(){
        action.performAction(enemyPool,mainShip,bulletPool);
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        regions = new TextureRegion[1];
        regions[0]  = textureRegion;
    }
}
