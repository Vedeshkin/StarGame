package ru.geekbrains.pool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.sprite.Bonus;


public class BonusPool extends SpritesPool<Bonus> {

    @Override
    protected Bonus newObject() {
        return new Bonus();
    }
}
