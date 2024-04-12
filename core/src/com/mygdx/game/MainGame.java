package com.mygdx.game;

import Screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {
	private SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
        this.setScreen(new GameScreen(this));
		this.getScreen().show();
	}

	@Override
	public void render () {
		this.getScreen().render(1);
    }


	public SpriteBatch getBatch() {
		return batch;
	}
}
