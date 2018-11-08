package ru.geekbrains.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;


public class MainShip extends Sprite {

    private Vector2 v0 = new Vector2(0.5f, 0);
    private Vector2 v = new Vector2();

    private boolean pressedLeft;
    private boolean pressedRight;

    private BulletPool bulletPool;

    private TextureAtlas atlas;

    private Rect worldBounds;
    private Sound shootSound;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sound/Shoot.wav"));
        this.atlas = atlas;
        setHeightProportion(0.15f);
        this.bulletPool = bulletPool;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);

        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }

    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    private static final int INVALID_POINTER = -1;
    private int rightPoint = INVALID_POINTER;
    private int leftPoint = INVALID_POINTER;

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPoint != INVALID_POINTER) return false;
            leftPoint = pointer;
            moveLeft();
        } else {
            if (rightPoint != INVALID_POINTER) return false;
            rightPoint = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (pointer == leftPoint) {
            leftPoint = INVALID_POINTER;
            if (rightPoint != INVALID_POINTER) moveRight();
            else stop();
        } else if (pointer == rightPoint) {
            rightPoint = INVALID_POINTER;
            if (leftPoint != INVALID_POINTER) moveLeft();
            else stop();

        }
        return false;
}


    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
        return false;
    }


    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    private void moveRight() {

        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, atlas.findRegion("bulletMainShip"), pos, new Vector2(0, 0.5f), 0.01f, worldBounds, 1);
        shootSound.play();
    }
}
