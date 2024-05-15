package com.mygdx.game;

import com.mygdx.game.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainMenuScreen;

public class MainGame extends Game {
	private SpriteBatch batch;
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		this.getScreen().render(Gdx.graphics.getDeltaTime());
    }


	public SpriteBatch getBatch() {
		return this.batch;
	}


}
