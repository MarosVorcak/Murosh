package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainMenuScreen;

public class MainGame extends Game {

    private SpriteBatch batch;
    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
        this.getScreen().show();
    }

    @Override
    public void render() {
        super.render();
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }
}
