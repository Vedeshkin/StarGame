package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.Sprite;

public class CaptionGameOver extends Sprite {

    public CaptionGameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        setBottom(0.0010f);
        setHeightProportion(0.06f);
    }
}
