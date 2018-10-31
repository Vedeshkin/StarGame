package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import ru.geekbrains.base.Base2DScreen;


public class GameScreen extends Base2DScreen {

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
    }

    public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
    }


    @Override
    public void dispose() {
        super.dispose();
    }
}
